/*
 * Server, Client 둘 다 가능하게 작성하였습니다.
 */

package Core;

import Event.*;
import GUI.*;
import Manager.*;

public class OnlineChat 
{	
	public static ChatFrame chat_frame = new ChatFrame();
	public static ConnectionDialog new_connection_frame = new ConnectionDialog();
	public static ShowPeopleDialog show_people_frame = new ShowPeopleDialog();
	public static ProfileDialog profile_dialog = new ProfileDialog();
	public static ManagePeopleDialog manage_people_dialog = new ManagePeopleDialog();
	
	public static SocketManager socket_manager = new SocketManager();
	public static MessageManager message_manager = new MessageManager();
	public static ParticipantManager paricipant_manager = new ParticipantManager();
	
	public static Connection_Frame_Event connection_frame_event = new Connection_Frame_Event();
	public static Chat_Frame_Event chat_frame_event = new Chat_Frame_Event();
	public static Profile_Dialog_Event profile_dialog_event = new Profile_Dialog_Event();
	public static Manage_Dialog_Event manage_dialog_event = new Manage_Dialog_Event();
	
	public static void main(String[] args)
	{
		System.out.println("Start!");
	}
}
