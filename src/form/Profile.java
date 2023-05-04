package form;

import java.io.Serializable;

import Connection.Connection;

public class Profile implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String nickname;
	private String describe;
	private Connection connect;
	
	private int ID;
	
	public void setID(int ID)
	{
		this.ID = ID;
	}
	
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
	
	public void setDescribe(String describe)
	{
		this.describe = describe;
	}
	
	public void setConnect(Connection connect)
	{
		this.connect = connect;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getNickname()
	{
		return nickname;
	}
	
	public String getDescribe()
	{
		return describe;
	}
	
	public Connection getConnect()
	{
		return connect;
	}
}