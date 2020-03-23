 package model;

import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class SerializeImg implements Serializable {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 8560179233789253818L;
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private int width, height;
	    private int[][] data;

	    public SerializeImg(Image image) {
	    	 setImage(image);
	    }
	   
	    public void setImage(Image image) {
	        width = ((int) image.getWidth());
	        height = ((int) image.getHeight());
	        data = new int[width][height];

	        PixelReader r = image.getPixelReader();
	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < height; j++) {
	                data[i][j] = r.getArgb(i, j);
	            }
	        }

	    }

	    public Image getImage() {
	        WritableImage img = new WritableImage(width, height);

	        PixelWriter w = img.getPixelWriter();
	        for (int i = 0; i < width; i++) {
	            for (int j = 0; j < height; j++) {
	                w.setArgb(i, j, data[i][j]);
	            }
	        }

	        return img;
	    }
	
}
