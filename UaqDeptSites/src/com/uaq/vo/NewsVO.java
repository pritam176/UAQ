package com.uaq.vo;

import java.util.Map;

/**
 * VO for Home Page.
 * 
 * @author mraheem
 * 
 */
public class NewsVO extends BaseVO {

	private String teaserImage;

	private String image;

	private String author;

	private String date;

	private String body;

	private String title;

	private String teaserTitle;

	private Map<String, Map<String, NewsVO>> relatedNews;

	private CategoryVO category;

	private String teaserText;

	private String postedDate;

	private long rowNum;

	private long count;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTeaserTitle() {
		return teaserTitle;
	}

	public void setTeaserTitle(String teaserTitle) {
		this.teaserTitle = teaserTitle;
	}

	public String getTeaserImage() {
		return teaserImage;
	}

	public void setTeaserImage(String teaserImage) {
		this.teaserImage = teaserImage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, Map<String, NewsVO>> getRelatedNews() {
		return relatedNews;
	}

	public void setRelatedNews(Map<String, Map<String, NewsVO>> relatedNews) {
		this.relatedNews = relatedNews;
	}

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

	public String getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(String postedDate) {
		this.postedDate = postedDate;
	}

	/**
	 * @return the count
	 */
	public long getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @return the rowNum
	 */
	public long getRowNum() {
		return rowNum;
	}

	/**
	 * @param rowNum
	 *            the rowNum to set
	 */
	public void setRowNum(long rowNum) {
		this.rowNum = rowNum;
	}

}
