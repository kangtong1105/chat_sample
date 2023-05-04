package Manager;

import java.io.IOException;
import java.util.Vector;

import Connection.Client_connection;
import Connection.Server_Listener;
import Connection.Server_connection;
import Core.OnlineChat;
import form.Message;

public class SocketManager
{
	private Boolean Servermode = false;
	private Boolean RunningStatus = false;
	
	private String IPAddress;
	private int port;	

	private Server_Listener Listening;
	
	public Client_connection c_connector;
	public Vector<Server_connection> s_connector = new Vector<Server_connection>();
	
	public int new_ID = 1;
	
	public boolean isRunning()
	{
		return RunningStatus; 
	}
	
	public boolean isServermode()
	{
		return Servermode; 
	}
	
	public void setServermode(boolean status)
	{
		if(!RunningStatus)
			Servermode = status;
	}
	
	public void setDestAddress(String address)
	{
		if(!RunningStatus)
			IPAddress = address;
	}
	
	public void setPort(int port)
	{
		if(!RunningStatus)
			this.port = port;
	}
	
	public String getDestAddress()
	{
		return IPAddress;
	}
	
	public int getPort()
	{
		return port;
	}
	
	public void run()
	{
		if(RunningStatus)	
			return;
		
		if(Servermode)
		{
			OnlineChat.paricipant_manager.people.add(OnlineChat.paricipant_manager.my_profile);
			
			Listening = new Server_Listener();
			Listening.start();
		}
		else
		{
			c_connector = new Client_connection();
			c_connector.start();
		}
		
		RunningStatus = true;
		OnlineChat.chat_frame.EnableButton();
	}
	
	public void stop()
	{
		if(!RunningStatus)
			return;
		
		RunningStatus = false;
		OnlineChat.chat_frame.disableButton();
		
		if(Servermode)
		{
			if(Listening.isAlive())
				Listening.interrupt();
			
			for(int i = 0; i < s_connector.size(); i++)
				if(s_connector.elementAt(i).isAlive())
				{
					s_connector.elementAt(i).end_flag = true;
					if(s_connector.elementAt(i).socket.isConnected())
						s_connector.elementAt(i).sendMessage(new Message(OnlineChat.paricipant_manager.my_profile.getID(), "connect_close", null));
				}
			
			try {
				if(Listening.listener != null)
					Listening.listener.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			OnlineChat.chat_frame.system_message("server off");
		}
		else
		{
			if(c_connector.isAlive())
			{
				c_connector.end_flag = true;
				if(c_connector.socket.isConnected())
					c_connector.sendMessage(new Message(OnlineChat.paricipant_manager.my_profile.getID(), "connect_close", null));
			}
			OnlineChat.chat_frame.system_message("disconnected successfully");
		}
	}
	
	public void send_message(Message message)
	{
		if(Servermode)
			broadcast(message);
		else
			c_connector.sendMessage(message);
	}
	
	public void broadcast(Message message)
	{
		if(Servermode)
			for(int i = 0; i < s_connector.size(); i++)
				if(s_connector.elementAt(i).isAlive() && s_connector.elementAt(i).dest_profile.getID() != message.getSenderID())
					s_connector.elementAt(i).sendMessage(message);
	}
}

