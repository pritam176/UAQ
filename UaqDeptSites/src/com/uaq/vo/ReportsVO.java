package com.uaq.vo;

/**
 * VO for Home Page.
 * 
 * @author nsharma
 * 
 */
public class ReportsVO extends BaseVO {

	private String title;

	private String teaserTitle;

	private String teaserText;

	private String body;

	private String datevalue;

	private String author;

	private String externalLink;

	private String image;
	
	private String landingPageDate;

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	public String getTeaserTitle() {
		return teaserTitle;
	}

	public void setTeaserTitle(String teaserTitle) {
		this.teaserTitle = teaserTitle;
	}

	public String getTeaserText() {
		return teaserText;
	}

	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}

	public String getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDatevalue() {
		return datevalue;
	}

	public void setDatevalue(String datevalue) {
		this.datevalue = datevalue;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLandingPageDate() {
		return landingPageDate;
	}

	public void setLandingPageDate(String landingPageDate) {
		this.landingPageDate = landingPageDate;
	}
	
}
