package GUI;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class InputPanel extends JPanel
{
	private JToolBar tools = new JToolBar();
	
	public JButton ImageAttachButton = new JButton("�̹��� ÷��");
	public JButton SendButton = new JButton("������");
	
	public JTextArea input = new JTextArea();
	
	public InputPanel()
	{
		SendButton.setName("������");
		ImageAttachButton.setName("�̹��� ÷��");
		
		tools.setFloatable(false);
		tools.add(ImageAttachButton);
		
		setLayout(new BorderLayout());
		add(tools, BorderLayout.NORTH);
		add(input, BorderLayout.CENTER);
		add(SendButton, BorderLayout.EAST);
	}
}