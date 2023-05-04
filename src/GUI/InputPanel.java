package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class InputPanel extends JPanel
{
	private JToolBar tools = new JToolBar();
	
	public JButton ImageAttachButton = new JButton("이미지 첨부");
	public JButton SendButton = new JButton("보내기");
	
	public JTextArea input = new JTextArea();
	
	public InputPanel()
	{
		SendButton.setName("보내기");
		ImageAttachButton.setName("이미지 첨부");
		
		tools.setFloatable(false);
		tools.add(ImageAttachButton);
		
		setLayout(new BorderLayout());
		add(tools, BorderLayout.NORTH);
		add(input, BorderLayout.CENTER);
		add(SendButton, BorderLayout.EAST);
	}
}