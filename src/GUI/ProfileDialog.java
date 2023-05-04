package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ProfileDialog extends JDialog
{
	public JTextField input_nickname = new JTextField(30);
	public JTextField input_describe = new JTextField(30);
	public JButton submit = new JButton("확인");
	public JButton cancel = new JButton("취소");
	
	private Container contentpane = getContentPane();
	
	public ProfileDialog()
	{	
		JPanel buttons = new JPanel();
		JPanel panel = new JPanel();
		JPanel _panel = new JPanel();
		JPanel __panel = new JPanel();
		
		_panel.add(new JLabel("nickname", JLabel.RIGHT));
		_panel.add(input_nickname);
		
		__panel.add(new JLabel("describe ", JLabel.RIGHT));
		__panel.add(input_describe);
		
		panel.setLayout(new GridLayout(2,1));
		panel.add(_panel);
		panel.add(__panel);
		
		buttons.add(submit);
		buttons.add(cancel);
		
		contentpane.setLayout(new BorderLayout());
		contentpane.add(panel, BorderLayout.CENTER);
		contentpane.add(buttons, BorderLayout.SOUTH);
		contentpane.add(new JPanel(), BorderLayout.NORTH);
		contentpane.add(new JPanel(), BorderLayout.WEST);
		contentpane.add(new JPanel(), BorderLayout.EAST);
		
		setTitle("프로필 설정");
		setVisible(false);
		setResizable(false);
		setSize(450,150);
	}
}
