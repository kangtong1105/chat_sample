package GUI;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Core.OnlineChat;

public class ShowPeopleDialog extends JDialog
{
	private JPanel panel = new JPanel();
	
	private Container contentpane = getContentPane();
	
	public ShowPeopleDialog()
	{
		contentpane.add(new JScrollPane(panel));
		
		panel.setLayout(new GridLayout(0,1));
		
		setSize(500,300);
		setTitle("Participant");
		setResizable(false);
		setVisible(false);
	}
	
	public void openWindow()
	{
		if(this.isVisible()) return;
		
		panel.removeAll();
		
		for(int i = 0; i < OnlineChat.paricipant_manager.people.size(); i++)
		{
			JPanel new_participant = new JPanel();
			new_participant.add(new JLabel("이름 : " + OnlineChat.paricipant_manager.people.elementAt(i).getNickname()));
			new_participant.add(new JLabel("ID : " + OnlineChat.paricipant_manager.people.elementAt(i).getID()));
			new_participant.add(new JLabel("소개 : " + OnlineChat.paricipant_manager.people.elementAt(i).getDescribe()));
			panel.add(new_participant);
		}
		
		setVisible(true);
	}
}
