package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Core.OnlineChat;


public class ChatFrame extends JFrame
{
	public HighMenu menu = new HighMenu();
	public InputPanel inputPanel = new InputPanel();
	
	private JScrollPane chat_window;
	private JTextArea chat_area = new JTextArea(0,0);
	private Container contentpane = getContentPane();
	
	public ChatFrame()
	{
		chat_area.setEditable(false);
		chat_window = new JScrollPane(chat_area);
		
		disableButton();
		
		contentpane.setLayout(new BorderLayout());
		contentpane.add(menu, BorderLayout.NORTH);
		contentpane.add(inputPanel, BorderLayout.SOUTH);
		contentpane.add(chat_window, BorderLayout.CENTER);
		
		setTitle("Online Chat");
		setSize(350,500);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void show_message(String sender, String message, LocalTime time)
	{
		chat_area.setText(chat_area.getText() + "\n[" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + "] " + sender + " : " + message);
	}
	
	public void system_message(String message)
	{
		LocalTime time = LocalTime.now();
		chat_area.setText(chat_area.getText() + "\n[" + time.getHour() + ":"+ time.getMinute() + ":" + time.getSecond() + "] System >> " + message);
	}
	
	public void disableButton()
	{
		menu.items[0][1].setEnabled(false);
		menu.items[1][0].setEnabled(false);
		menu.items[2][0].setEnabled(false);
		menu.items[2][1].setEnabled(false);
		
		inputPanel.ImageAttachButton.setEnabled(false);
	}
	
	public void EnableButton()
	{
		menu.items[0][1].setEnabled(true);
		menu.items[1][0].setEnabled(true);
		menu.items[2][0].setEnabled(true);
		if(OnlineChat.socket_manager.isServermode())
			menu.items[2][1].setEnabled(true);
		
		inputPanel.ImageAttachButton.setEnabled(true);
	}
}