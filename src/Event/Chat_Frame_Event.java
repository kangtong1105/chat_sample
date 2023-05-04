package Event;

import Core.OnlineChat;
import GUI.ChatFrame;
import GUI.ConnectionDialog;
import GUI.showImage;
import Manager.MessageManager;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Chat_Frame_Event 
{
	private ChatFrame chat_frame = OnlineChat.chat_frame;
	private ConnectionDialog new_connection_frame = OnlineChat.new_connection_frame;
	
	private MessageManager message_manager = OnlineChat.message_manager;
	
	public Chat_Frame_Event()
	{
		Menu_event menu_event = new Menu_event();
		
		for(int i = 0; i < chat_frame.menu.menus.length; i++)
			for(int l = 0; l < chat_frame.menu.items[i].length; l++)
				chat_frame.menu.items[i][l].addActionListener(menu_event);
		
		Button_event b_event = new Button_event();
		
		chat_frame.inputPanel.SendButton.addActionListener(b_event);
		chat_frame.inputPanel.ImageAttachButton.addActionListener(b_event);
	}
	
	class Button_event implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String button_name = ((JButton)e.getSource()).getName();
			
			JFileChooser chooser = new JFileChooser();
			
			FileNameExtensionFilter filter_image = new FileNameExtensionFilter("JPG / PNG", "jpg", "png");
			
			switch(button_name)
			{
			case "보내기":
				if(OnlineChat.socket_manager.isRunning())
					message_manager.sending("str_message", chat_frame.inputPanel.input.getText());
				chat_frame.inputPanel.input.setText("");
				break;
			case "이미지 첨부":
				chooser.setFileFilter(filter_image);
				
				chooser.showOpenDialog(chooser);
				
				if(chooser.getSelectedFile() == null) 
					return;
				
				ImageIcon image = new ImageIcon(chooser.getSelectedFile().getPath());
				OnlineChat.message_manager.sending("Image", image);
				
				if(OnlineChat.socket_manager.isServermode())
					new showImage(image);
				
				break;
			}
		}
	}
	
	class Menu_event implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String menu_name = ((JMenuItem)e.getSource()).getText();
		
			switch(menu_name)
			{
			case "새 연결":
				new_connection_frame.setVisible(true);
				break;
			case "연결 끊기":
				OnlineChat.socket_manager.stop();
				break;
			case "참가자 보기":
				OnlineChat.show_people_frame.openWindow();
				break;
			case "참가자 관리":
				OnlineChat.manage_people_dialog.openWindow();
				break;
			case "프로필 설정":
				OnlineChat.profile_dialog.setVisible(true);
				break;
			}
		}
	}
}
