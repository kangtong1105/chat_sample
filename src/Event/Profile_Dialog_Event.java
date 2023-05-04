package Event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import Core.OnlineChat;
import form.Profile;

public class Profile_Dialog_Event implements ActionListener 
{
	public Profile_Dialog_Event()
	{
		OnlineChat.profile_dialog.submit.addActionListener(this);
		OnlineChat.profile_dialog.cancel.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String text = ((JButton)e.getSource()).getText();
		
		switch(text)
		{
		case "확인":
			Profile new_profile = new Profile();
			new_profile.setID(OnlineChat.paricipant_manager.my_profile.getID());
			new_profile.setNickname(OnlineChat.profile_dialog.input_nickname.getText());
			new_profile.setDescribe(OnlineChat.profile_dialog.input_describe.getText());
			
			if(OnlineChat.socket_manager.isServermode())
			{
				OnlineChat.chat_frame.system_message("rename to " + new_profile.getNickname());
				OnlineChat.paricipant_manager.my_profile.setNickname(new_profile.getNickname());
				OnlineChat.paricipant_manager.my_profile.setDescribe(new_profile.getDescribe());
			}
			
			OnlineChat.message_manager.sending("Edit_Profile", new_profile);
			OnlineChat.profile_dialog.setVisible(false);
			break;
		case "취소":
			OnlineChat.profile_dialog.setVisible(false);
			break;
		}
	}
}
