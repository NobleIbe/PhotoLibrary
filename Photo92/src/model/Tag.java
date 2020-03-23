package model;

import java.io.Serializable;

public class Tag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6616521806538559206L;
	private  String tagName;
	public String getTagName() {
		return tagName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getTagValue() {
		return tagValue;
	}
	@Override
	public String toString() {
		return "Tag [tagName=" + tagName + ", tagValue=" + tagValue + "]";
	}
	public Tag(String tagName, String tagValue) {
		super();
		this.tagName = tagName;
		this.tagValue = tagValue;
	}
	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	private  String tagValue;
	
}
