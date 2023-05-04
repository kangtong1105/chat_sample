package Connection;

import java.io.Serializable;
import java.net.Socket;

import Core.OnlineChat;
import form.Message;

public class Client_connection extends Connection implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void run()
	{
		try
		{
			OnlineChat.chat_frame.system_message("Request to " + OnlineChat.socket_manager.getDestAddress() + ":" + OnlineChat.socket_manager.getPort());
			socket = new Socket(OnlineChat.socket_manager.getDestAddress(), OnlineChat.socket_manager.getPort());
			OnlineChat.chat_frame.system_message("Connect!");
			
			OnlineChat.message_manager.message_reader((Message)receiveMessage(), this);
			
			OnlineChat.message_manager.message_reader((Message)receiveMessage(), this);
			
			sendMessage(new Message(OnlineChat.paricipant_manager.my_profile.getID(), "user_profile", OnlineChat.paricipant_manager.my_profile));
			OnlineChat.paricipant_manager.people.add(OnlineChat.paricipant_manager.my_profile);
			
			for(;!end_flag;)
				OnlineChat.message_manager.message_reader((Message)receiveMessage(), this);
			
		}
		catch(Exception e)
		{
			OnlineChat.chat_frame.system_message(e.getMessage());
		}
	}
}
