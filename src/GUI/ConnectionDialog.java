package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ConnectionDialog extends JDialog
{
	public JRadioButton toggleServermode = new JRadioButton("Running as Server", true);
	public JTextField s_inputPort = new JTextField(10);
	public JTextField s_inputNickname = new JTextField(10);
	public JTextField c_inputPort = new JTextField(10);
	public JTextField c_inputNickname = new JTextField(10);
	public JTextField c_inputIP = new JTextField(10);
	public JButton connect = new JButton("connect");

	private JPanel center_server = new JPanel();
	private JPanel center_client = new JPanel();
	
	private JPanel north = new JPanel();
	private JPanel center = new JPanel();
	private JPanel south = new JPanel();
	private Container contentpane = getContentPane();
	private CardLayout card = new CardLayout();
	
	public ConnectionDialog()
	{
		center_server.setLayout(new GridLayout(2, 3, 10, 10));
		center_server.add(new JLabel("port", JLabel.RIGHT));
		center_server.add(s_inputPort);
		center_server.add(new JPanel());
		center_server.add(new JLabel("Nickname", JLabel.RIGHT));
		center_server.add(s_inputNickname);
		center_server.add(new JPanel());
		
		center_client.setLayout(new GridLayout(3, 2, 10, 10));
		center_client.add(new JLabel("IPAddress", JLabel.RIGHT));
		center_client.add(c_inputIP);
		center_client.add(new JPanel());
		center_client.add(new JLabel("port", JLabel.RIGHT));
		center_client.add(c_inputPort);
		center_client.add(new JPanel());
		center_client.add(new JLabel("Nickname", JLabel.RIGHT));
		center_client.add(c_inputNickname);
		center_client.add(new JPanel());
		
		center.setLayout(card);
		center.add("Server", center_server);
		center.add("Client", center_client);
		
		north.add(toggleServermode);
		
		south.add(connect);
		
		contentpane.setLayout(new BorderLayout());
		contentpane.add(north, BorderLayout.NORTH);
		contentpane.add(center, BorderLayout.CENTER);
		contentpane.add(south, BorderLayout.SOUTH);
		contentpane.add(new JPanel(), BorderLayout.EAST);
		contentpane.add(new JPanel(), BorderLayout.WEST);
		
		setTitle("»õ ¿¬°á");
		setSize(400,180);
		setVisible(false);
		setResizable(false);
		
		toggleServermode.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if(toggleServermode.isSelected())
				{
					setResizable(true);
					setSize(400,168);
					setResizable(false);
					card.show(center, "Server");
				}
				else
				{
					setResizable(true);
					setSize(400,200);
					setResizable(false);
					card.show(center, "Client");
				}
			}
		});
	}
}
