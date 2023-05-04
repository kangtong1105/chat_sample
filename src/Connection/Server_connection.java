package Connection;

import java.io.IOException;
import java.io.Serializable;

import Core.OnlineChat;
import form.Message;

public class Server_connection extends Connection implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Override
	public void run()
	{	
		try
		{
			sendMessage(new Message(0, "participant_profile", OnlineChat.paricipant_manager.people));
			
			sendMessage(new Message(0, "set_Client_ID", OnlineChat.socket_manager.new_ID++));
			
			OnlineChat.message_manager.message_reader((Message)receiveMessage(), this);
			
			for(;!end_flag;)
				OnlineChat.message_manager.message_reader((Message)receiveMessage(), this);
			
		}
		catch(Exception e)
		{
			OnlineChat.chat_frame.system_message(e.getMessage());
		}
		
		try {
			if(socket != null)
				socket.close();
		} catch (IOException e1) {
			OnlineChat.chat_frame.system_message(e1.getMessage());
		}
	}
}
