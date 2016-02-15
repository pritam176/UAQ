package com.uaq.vo;

import java.util.List;

/**
 * VO for Home Page.
 * 
 * @author nsharma
 * 
 */

public class HomeVO extends BaseVO {

	private List<String> carouselImages;

	private String carouselVideos;

	private String copyrightText;

	private String websiteUpdateText;

	private String browserSupportText;

	private String backgroundImage;

	public String getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(String backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

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

	public String getCarouselVideos() {
		return carouselVideos;
	}

	public void setCarouselVideos(String carouselVideos) {
		this.carouselVideos = carouselVideos;
	}

}
