package fr.jeuxvideo.about;

public class About {
	
	private String idea;
	private String data;
	private String urlData;
	private String facebook;
	private String site;
	private String mess_site;
	private String where;
	
	
	public About(String idea, String data, String urlData, String facebook, String mess,String site, String whereData) {
		super();
		this.idea = idea;
		this.data = data;
		this.urlData = urlData;
		this.facebook = facebook;
		this.mess_site = mess;
		this.site = site;
		this.where = whereData;
	}


	/**
	 * @return the idea
	 */
	public String getIdea() {
		return idea;
	}


	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}


	/**
	 * @return the urlData
	 */
	public String getUrlData() {
		return urlData;
	}


	/**
	 * @return the facebook
	 */
	public String getFacebook() {
		return facebook;
	}


	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}


	public String getMess_site() {
		return mess_site;
	}


	public String getWhere() {
		return where;
	}

	
}
