package com.uaq.vo;

import java.util.List;

/**
 * VO for Home Page.
 * 
 * @author nsharma
 * 
 */

public class LandingPageVO extends NavigationVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> carouselImages;

	private String copyrightText;

	private String websiteUpdateText;

	private String browserSupportText;

	private List<String> bodyAssociatedIds;

	public List<String> getCarouselImages() {
		return carouselImages;
	}

	public void setCarouselImages(List<String> carouselImages) {
		this.carouselImages = carouselImages;
	}

	public String getCopyrightText() {
		return copyrightText;
	}

	public void setCopyrightText(String copyrightText) {
		this.copyrightText = copyrightText;
	}

	public String getWebsiteUpdateText() {
		return websiteUpdateText;
	}

	public void setWebsiteUpdateText(String websiteUpdateText) {
		this.websiteUpdateText = websiteUpdateText;
	}

	public String getBrowserSupportText() {
		return browserSupportText;
	}

	public void setBrowserSupportText(String browserSupportText) {
		this.browserSupportText = browserSupportText;
	}

	/**
	 * @return the bodyAssociatedIds
	 */
	public List<String> getBodyAssociatedIds() {
		return bodyAssociatedIds;
	}

	/**
	 * @param bodyAssociatedIds
	 *            the bodyAssociatedIds to set
	 */
	public void setBodyAssociatedIds(List<String> bodyAssociatedIds) {
		this.bodyAssociatedIds = bodyAssociatedIds;
	}

}
