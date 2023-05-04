package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Core.OnlineChat;
import GUI.ConnectionDialog;
import Manager.SocketManager;

public class Connection_Frame_Event
{
	private SocketManager socket_manager = OnlineChat.socket_manager;
	private ConnectionDialog new_connection_frame = OnlineChat.new_connection_frame;
	
	public Connection_Frame_Event()
	{
		new_connection_frame.connect.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{	
				new_connection_frame.setVisible(false);
				
				if(new_connection_frame.toggleServermode.isSelected())
				{
					OnlineChat.paricipant_manager.my_profile.setNickname(new_connection_frame.s_inputNickname.getText());
					
					socket_manager.setServermode(true);
					socket_manager.setPort(Integer.parseInt(new_connection_frame.s_inputPort.getText()));
					socket_manager.run();
				}
				else
				{
					OnlineChat.paricipant_manager.my_profile.setNickname(new_connection_frame.c_inputNickname.getText());
					
					socket_manager.setServermode(false);
					socket_manager.setPort(Integer.parseInt(new_connection_frame.c_inputPort.getText()));
					socket_manager.setDestAddress(new_connection_frame.c_inputIP.getText());
					socket_manager.run();
				}
			}
		});
	}
}