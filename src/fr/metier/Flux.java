package fr.metier;

import java.io.Serializable;

public class Flux implements Serializable,Comparable<Flux>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Console console;

	private String url;

	public Flux(Console console, String url) {
		super();
		this.console = console;
		this.url = url;
	}

	/**
	 * @return the console
	 */
	public Console getConsole() {
		return console;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	@Override
	public int compareTo(Flux another) {
		if(this.console.getName().equals("Toutes machines"))
		{
			if(another.console.getName().equals("Toutes machines"))
			{
				return 0;
			}
			else{
				return -1;
			}
		}
		else if(another.console.getName().equals("Toutes machines")){
			if(this.console.getName().equals("Toutes machines"))
			{
				return 0;
			}
			else{
				return 1;
			}
		}
		else{
			return this.console.getName().compareTo(another.console.getName());
		}
	}


}
