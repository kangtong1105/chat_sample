package form;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Message implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	private String type;
	private int senderID;
	private Object data;
	private LocalDate date;
	private LocalTime time;
	
	public Message(int senderID, String type, Object data)
	{
		date = LocalDate.now();
		time = LocalTime.now();
		
		this.type = type;
		this.senderID = senderID;
		this.data = data;
	}
	
	public int getSenderID()
	{
		return senderID;
	}
	
	public String getType()
	{ 
		return type;
	}
	
	public Object getData()
	{ 
		return data; 
	}
	
	public LocalDate getDate() 
	{ 
		return date; 
	}
	
	public LocalTime getTime() 
	{ 
		return time; 
	}
}
