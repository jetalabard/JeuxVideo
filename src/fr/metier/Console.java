package fr.metier;

import java.io.Serializable;

public class Console implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private String image;

	public Console(String name, String image) {
		super();
		this.name = name;
		this.image = image;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}


}
