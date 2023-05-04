package Manager;

import java.util.Vector;

import Core.OnlineChat;
import form.Message;
import form.Profile;

public class ParticipantManager 
{
	public Profile my_profile = new Profile();
	
	public Vector<Profile> people = new Vector<Profile>();
	public Vector<Integer> muted_ID = new Vector<Integer>();
	
	public Profile searchID(int ID)
	{
		for(int i = 0; i < people.size(); i++)
			if(people.elementAt(i).getID() == ID)
				return people.elementAt(i);
		
		return null;
	}
	
	public void addPeopleList(Vector<Profile> participant)
	{
		for(int i = 0; i < participant.size(); i++)
			people.add(participant.elementAt(i));
	}
	
	public Boolean check_mute(int ID)
	{
		for(int i = 0; i < muted_ID.size(); i++)
			if(muted_ID.elementAt(i) == ID)
				return true;
		
		return false;
	}
	
	public void mute(int ID)
	{
		if(!OnlineChat.socket_manager.isServermode())
			return;
		
		for(int i = 0; i < muted_ID.size(); i++)
			if(muted_ID.elementAt(i) == ID)
			{
				unmute(ID);
				return;
			}
		
		muted_ID.add(ID);
		
		searchID(ID).getConnect().sendMessage(new Message(0, "mute", ""));
	}
	
	public void unmute(int ID)
	{
		if(!OnlineChat.socket_manager.isServermode())
			return;
		
		muted_ID.remove(ID);

		searchID(ID).getConnect().sendMessage(new Message(0, "unmute", ""));
	}
	
	public void kick(int ID)
	{
		if(!OnlineChat.socket_manager.isServermode())
			return;
		
		Profile participant = searchID(ID);
		
		participant.getConnect().sendMessage(new Message(0, "kick", "byebye"));
	}
}

