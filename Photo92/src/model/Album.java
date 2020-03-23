package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1806552442038448293L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String name;
	private ArrayList<Photo>photos;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(ArrayList<Photo> photos) {
		this.photos = photos;
	}
	@Override
	public String toString() {
		return name;
	}
	public Album(String name) {
		super();
		this.name = name;
		this.photos=new ArrayList<Photo>();
	}
	

	public int sum() {
		return this.photos.size();
	}
}
