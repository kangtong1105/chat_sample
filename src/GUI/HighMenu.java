package GUI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Core.OnlineChat;

public class HighMenu extends JMenuBar
{
	public JMenu[] menus = new JMenu[3];
	
	public JMenuItem[][] items = new JMenuItem[3][];
	
	public HighMenu()
	{
		menus[0] = new JMenu("����");
		menus[1] = new JMenu("���μ���");
		menus[2] = new JMenu("������");
		
		items[0] = new JMenuItem[2];
		items[0][0] = new JMenuItem("�� ����");
		items[0][1] = new JMenuItem("���� ����");
		
		items[1] = new JMenuItem[1];
		items[1][0] = new JMenuItem("������ ����");
		
		items[2] = new JMenuItem[2];
		items[2][0] = new JMenuItem("������ ����");
		items[2][1] = new JMenuItem("������ ����");
		
		for(int i = 0; i < menus.length; i++)
		{
			for(int l = 0; l < items[i].length; l++)
				menus[i].add(items[i][l]);
			
			add(menus[i]);
		}
	}
}
