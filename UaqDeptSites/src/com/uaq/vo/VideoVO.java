package com.uaq.vo;

/**
 * VO for Home Page.
 * 
 * @author mraheem
 * 
 */
public class VideoVO extends BaseVO {

	private CategoryVO category;

	private String teaserImage;

	private String image;

	private String video;

	private String teaserTitle;

	private String title;

	/**
	 * @return the category
	 */
	public CategoryVO getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(CategoryVO category) {
		this.category = category;
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
	 * @return the video
	 */
	public String getVideo() {
		return video;
	}

	/**
	 * @param video
	 *            the video to set
	 */
	public void setVideo(String video) {
		this.video = video;
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

}
