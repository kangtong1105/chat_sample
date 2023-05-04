package GUI;

import java.awt.Container;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Core.OnlineChat;

public class ManagePeopleDialog extends JDialog
{
	private JPanel panel = new JPanel();
	
	public Vector<JButton> mute = new Vector<JButton>();
	public Vector<JButton> kick = new Vector<JButton>();
	
	private Container contentpane = getContentPane();
	
	public ManagePeopleDialog()
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
		mute.clear();
		kick.clear();
		
		for(int i = 0; i < OnlineChat.paricipant_manager.people.size(); i++)
		{
			JPanel new_participant = new JPanel();
			JButton new_mute = new JButton("mute:" + OnlineChat.paricipant_manager.people.elementAt(i).getID());
			JButton new_kick = new JButton("kick:" + OnlineChat.paricipant_manager.people.elementAt(i).getID());
			
			mute.add(new_mute);
			kick.add(new_kick);
			
			new_participant.add(new JLabel("ÀÌ¸§ : " + OnlineChat.paricipant_manager.people.elementAt(i).getNickname()));
			new_participant.add(new JLabel("ID : " + OnlineChat.paricipant_manager.people.elementAt(i).getID()));
			new_participant.add(new_mute);
			new_participant.add(new_kick);
			
			panel.add(new_participant);
		}
		
		OnlineChat.manage_dialog_event.set_Event();
		
		setVisible(true);
	}
}
