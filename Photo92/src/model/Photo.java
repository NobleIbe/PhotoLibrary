package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.image.Image;

public class Photo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8289960829616987871L;
	private String filename;
	private String caption;
	private Calendar date;
	private ArrayList<Tag>tags;
	private SerializeImg image;
	public Photo(String filename,Calendar date,  Image image) {
		super();
		this.filename = filename;

		this.date = date;

		this.image = new SerializeImg(image);
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	
	public String getDate() {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/YY");
		String strDate=dateFormat.format(date.getTime());
		return strDate;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public ArrayList<Tag> getTags() {
		return tags;
	}
	public void setTags(ArrayList<Tag> tags) {
		this.tags = tags;
	}
	public Image getImage() {
		return image.getImage();
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
