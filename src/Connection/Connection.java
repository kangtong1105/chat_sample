package Connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import form.Message;
import form.Profile;

public class Connection extends Thread
{
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public Socket socket = null;
	
	public Boolean end_flag = false;
	
	public Profile dest_profile;
	
	public void sendMessage(Message message)
	{
		if(!this.isAlive()) return;
		
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object receiveMessage() throws IOException, ClassNotFoundException
	{
		if(!this.isAlive()) return null;
		
		in = new ObjectInputStream(socket.getInputStream());
		return in.readObject();
	}
}