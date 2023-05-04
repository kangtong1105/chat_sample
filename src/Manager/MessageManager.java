package Manager;

import java.util.Vector;

import javax.swing.ImageIcon;

import Connection.Connection;
import Core.OnlineChat;
import GUI.showImage;
import form.Message;
import form.Profile;

public class MessageManager 
{	
	public void message_reader(Message received, Connection connection)
	{	
		switch(received.getType())
		{
		case "str_message":
			if(OnlineChat.paricipant_manager.check_mute(received.getSenderID()))
				return;
			
			OnlineChat.chat_frame.show_message(OnlineChat.paricipant_manager.searchID(received.getSenderID()).getNickname(), (String)received.getData(), received.getTime());
			
			if(OnlineChat.socket_manager.isServermode())
			{
				connection.sendMessage((new Message(received.getSenderID(), "Ack", received.getData())));
				OnlineChat.socket_manager.broadcast(received);
			}
			
			break;
		case "Ack":
			if(!OnlineChat.socket_manager.isServermode())
				OnlineChat.chat_frame.show_message(OnlineChat.paricipant_manager.my_profile.getNickname(), (String)received.getData(), received.getTime());
			break;
		case "Ack_disconnect":
			OnlineChat.chat_frame.system_message(OnlineChat.socket_manager.getDestAddress() + ":" + OnlineChat.socket_manager.getPort() + " disconnected");
			break;
		case "Ack_kick":
			OnlineChat.chat_frame.system_message("You Kick " + OnlineChat.paricipant_manager.searchID(received.getSenderID()).getNickname());
			break;
		case "Ack_Edit_Profile":
			OnlineChat.chat_frame.system_message("rename to " + ((Profile)received.getData()).getNickname());
			OnlineChat.paricipant_manager.my_profile.setNickname(((Profile)received.getData()).getNickname());
			OnlineChat.paricipant_manager.my_profile.setDescribe(((Profile)received.getData()).getDescribe());
			break;
		case "Ack_Image":
			new showImage((ImageIcon)received.getData());
			break;
		case "File":
			System.out.println("Received file");
			break;
		case "Image":
			if(OnlineChat.paricipant_manager.check_mute(received.getSenderID()))
				return;
			
			if(OnlineChat.socket_manager.isServermode())
			{
				connection.sendMessage((new Message(received.getSenderID(), "Ack_Image", received.getData())));
				OnlineChat.socket_manager.broadcast(received);
			}
			
			new showImage((ImageIcon)received.getData());
			
			break;
		case "set_Client_ID":
			OnlineChat.paricipant_manager.my_profile.setID((int)received.getData());
			break;
		case "user_profile":
			((Profile)received.getData()).setConnect(connection);
			OnlineChat.paricipant_manager.people.add((Profile)received.getData());
			
			if(OnlineChat.socket_manager.isServermode())
			{
				connection.dest_profile = OnlineChat.paricipant_manager.people.lastElement();
				OnlineChat.socket_manager.broadcast(received);
			}
			
			OnlineChat.chat_frame.system_message("WELCOME " + ((Profile)received.getData()).getNickname());
			break;
		case "participant_profile":
			OnlineChat.paricipant_manager.addPeopleList((Vector<Profile>)received.getData());
			break;
		case "connect_close":	
			if(OnlineChat.socket_manager.isServermode())
			{
				connection.end_flag = true;
				connection.sendMessage(new Message(OnlineChat.paricipant_manager.my_profile.getID(), "Ack_disconnect", "Ok."));
				OnlineChat.socket_manager.broadcast(received);
			}
			else 
			{
				if(received.getSenderID() == 0)
				{
					connection.end_flag = true;
					connection.sendMessage(new Message(OnlineChat.paricipant_manager.my_profile.getID(), "Ack_disconnect", "Ok."));
				}
			}
			
			OnlineChat.chat_frame.system_message(OnlineChat.paricipant_manager.searchID(received.getSenderID()).getNickname() + " disconnected");
			OnlineChat.paricipant_manager.people.remove(OnlineChat.paricipant_manager.searchID(received.getSenderID()));
			
			break;
		case "Edit_Profile":
			if(OnlineChat.socket_manager.isServermode())
			{
				OnlineChat.socket_manager.broadcast(received);
				connection.sendMessage(new Message(OnlineChat.paricipant_manager.my_profile.getID(), "Ack_Edit_Profile", received.getData()));
			}
			
			Profile temp = OnlineChat.paricipant_manager.searchID(((Profile)received.getData()).getID());
			
			OnlineChat.chat_frame.system_message(temp.getNickname() + " rename to " + ((Profile)received.getData()).getNickname());
			
			temp.setNickname(((Profile)received.getData()).getNickname());
			temp.setDescribe(((Profile)received.getData()).getDescribe());
			break;
		case "mute":
			OnlineChat.chat_frame.system_message("You mute by server");
			break;
		case "unmute":
			OnlineChat.chat_frame.system_message("Now you unmute");
			break;
		case "kick":
			connection.end_flag = true;
			connection.sendMessage(new Message(OnlineChat.paricipant_manager.my_profile.getID(), "Ack_kick", ""));
			OnlineChat.chat_frame.system_message("You are Kicked by Server");
			
			OnlineChat.socket_manager.stop();
			break;
		}
	}
	
	public void sending(String type, String message)
	{
		Message m = new Message(OnlineChat.paricipant_manager.my_profile.getID(), type, message);
		OnlineChat.socket_manager.send_message(m);
		
		if(OnlineChat.socket_manager.isServermode() && OnlineChat.socket_manager.isRunning())
			OnlineChat.chat_frame.show_message(OnlineChat.paricipant_manager.my_profile.getNickname(), (String)m.getData(), m.getTime());
	}
	
	public void sending(String type, Object message)
	{
		Message m = new Message(OnlineChat.paricipant_manager.my_profile.getID(), type, message);
		OnlineChat.socket_manager.send_message(m);
	}
}