package model;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
	/**
	 * 
	 */

	private static final long serialVersionUID = 8177923271139908648L;
	private String username;
	private String password;
	private ArrayList<Album> albumList =new ArrayList<Album>();
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;

	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", albumList=" + albumList + "]";
	}
	public ArrayList<Album> getAlbumList() {
		return albumList;
	}
	public void setAlbumList(ArrayList<Album> albumList) {
		this.albumList = albumList;
	}
}
