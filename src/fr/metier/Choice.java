package fr.metier;

import java.io.Serializable;
import java.util.List;

public class Choice implements Serializable,Comparable<Choice>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Flux> listFlux;	
	
	private String title;
	
	private String subtitle;
	
	private String intitule;
	
	private String image;

	public Choice(List<Flux> listFlux, String title, String subtitle, String intitule, String image) {
		super();
		this.listFlux = listFlux;
		this.title = title;
		this.subtitle = subtitle;
		this.intitule = intitule;
		this.image = image;
	}

	/**
	 * @return the listFlux
	 */
	public List<Flux> getListFlux() {
		return listFlux;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}

	/**
	 * @return the intitule
	 */
	public String getIntitule() {
		return intitule;
	}


	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	@Override
	public int compareTo(Choice another) {
		return this.title.compareTo(another.title);
	}
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Choice)) {
	        return false;
	    }
		Choice that = (Choice) other;
	    return this.title==that.title;
	}

}
