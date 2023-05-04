package Connection;

import java.net.ServerSocket;

import Core.OnlineChat;

public class Server_Listener extends Thread
{
	public ServerSocket listener;
	
	@Override
	public void run()
	{
		OnlineChat.chat_frame.system_message("Listening...");
		
		for(;OnlineChat.socket_manager.isRunning();)
		{
			try
			{
				listener = new ServerSocket(OnlineChat.socket_manager.getPort());
				Server_connection new_connection;
			
				for(;OnlineChat.socket_manager.isRunning();)
				{
					new_connection = new Server_connection();
					new_connection.socket = listener.accept();
					OnlineChat.socket_manager.s_connector.add(new_connection);
					new_connection.start();
				}
			}
			catch(Exception e)
			{
				if(OnlineChat.socket_manager.isRunning())
				{
					OnlineChat.chat_frame.system_message("EXCEPTION AT LISTENING THREAD : " + e.getMessage());
					OnlineChat.chat_frame.system_message("RESTART LISTENING AFTER 10 SEC");
					try {
						sleep(10000);
					} catch (InterruptedException e1) {
						OnlineChat.chat_frame.system_message(e1.getMessage());
						return;
					}
				}
				
				OnlineChat.chat_frame.system_message("Stop Listener");
			}
		}
	}
}