package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Core.OnlineChat;

public class Manage_Dialog_Event implements ActionListener
{
	public void set_Event()
	{
		for(int i = 0; i < OnlineChat.manage_people_dialog.kick.size(); i++)
			OnlineChat.manage_people_dialog.kick.elementAt(i).addActionListener(this);
		
		for(int i = 0; i < OnlineChat.manage_people_dialog.mute.size(); i++)
			OnlineChat.manage_people_dialog.mute.elementAt(i).addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String button_text = ((JButton)e.getSource()).getText().substring(0, 4);
		int ID = Integer.parseInt(((JButton)e.getSource()).getText().substring(5));
		
		switch(button_text)
		{
		case "mute":
			OnlineChat.paricipant_manager.mute(ID);
			break;
		case "kick":
			OnlineChat.paricipant_manager.kick(ID);
			break;
		}
	}
}
