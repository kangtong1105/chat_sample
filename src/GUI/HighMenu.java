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
		menus[0] = new JMenu("연결");
		menus[1] = new JMenu("개인설정");
		menus[2] = new JMenu("참가자");
		
		items[0] = new JMenuItem[2];
		items[0][0] = new JMenuItem("새 연결");
		items[0][1] = new JMenuItem("연결 끊기");
		
		items[1] = new JMenuItem[1];
		items[1][0] = new JMenuItem("프로필 설정");
		
		items[2] = new JMenuItem[2];
		items[2][0] = new JMenuItem("참가자 보기");
		items[2][1] = new JMenuItem("참가자 관리");
		
		for(int i = 0; i < menus.length; i++)
		{
			for(int l = 0; l < items[i].length; l++)
				menus[i].add(items[i][l]);
			
			add(menus[i]);
		}
	}
}
