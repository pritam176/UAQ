package com.uaq.vo;

/**
 * VO for Project Listing and Project Details
 * 
 * @author Arjun
 * 
 */
public class ProjectsVO extends BaseVO {

	private String title;

	private String teaserTitle;

	private String body;

	private String teaserText;

	private String dateAndTime;

	private String image;

	private String teaserImage;

	private String mobileImage;
	
	private String landingPageDate;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the teaserTitle
	 */
	public String getTeaserTitle() {
		return teaserTitle;
	}

	/**
	 * @param teaserTitle
	 *            the teaserTitle to set
	 */
	public void setTeaserTitle(String teaserTitle) {
		this.teaserTitle = teaserTitle;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the teaserText
	 */
	public String getTeaserText() {
		return teaserText;
	}

	/**
	 * @param teaserText
	 *            the teaserText to set
	 */
	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}

	/**
	 * @return the dateAndTime
	 */
	public String getDateAndTime() {
		return dateAndTime;
	}

	/**
	 * @param dateAndTime
	 *            the dateAndTime to set
	 */
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

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

	/**
	 * @return the teaserImage
	 */
	public String getTeaserImage() {
		return teaserImage;
	}

	/**
	 * @param teaserImage
	 *            the teaserImage to set
	 */
	public void setTeaserImage(String teaserImage) {
		this.teaserImage = teaserImage;
	}

	/**
	 * @return the mobileImage
	 */
	public String getMobileImage() {
		return mobileImage;
	}

	/**
	 * @param mobileImage
	 *            the mobileImage to set
	 */
	public void setMobileImage(String mobileImage) {
		this.mobileImage = mobileImage;
	}

	/**
	 * @return the landingPageDate
	 */
	public String getLandingPageDate() {
		return landingPageDate;
	}

	/**
	 * @param landingPageDate the landingPageDate to set
	 */
	public void setLandingPageDate(String landingPageDate) {
		this.landingPageDate = landingPageDate;
	}
	
}
