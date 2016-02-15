package com.uaq.vo;

import java.io.Serializable;
import java.util.List;

public class NavigationVO extends BaseVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String image;

	private String teaserImage;

	private String titleIcon;

	private String teaserTitle;
	
	private String heading;

	private String showInFooter;

	private String teaserText;

	private String bannerImage;

	private String bannerText;

	private Boolean isCurrent;

	private String displayTypeHome;

	private DepartmentVO departmentVO;

	private List<NavigationVO> navigations;

	private List<NavigationVO> associations;

	private NavigationVO parentPage;
	
	private String video;
	
	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public NavigationVO getParentPage() {
		return parentPage;
	}

	public void setParentPage(NavigationVO parentPage) {
		this.parentPage = parentPage;
	}

	public DepartmentVO getDepartmentVO() {
		return departmentVO;
	}

	public void setDepartmentVO(DepartmentVO departmentVO) {
		this.departmentVO = departmentVO;
	}

	public String getDisplayTypeHome() {
		return displayTypeHome;
	}

	public void setDisplayTypeHome(String displayTypeHome) {
		this.displayTypeHome = displayTypeHome;
	}

	private int level;

	public String getTitleIcon() {
		return titleIcon;
	}

	public void setTitleIcon(String titleIcon) {
		this.titleIcon = titleIcon;
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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<NavigationVO> getAssociations() {
		return associations;
	}

	public void setAssociations(List<NavigationVO> associations) {
		this.associations = associations;
	}

	public List<NavigationVO> getNavigations() {
		return navigations;
	}

	public void setNavigations(List<NavigationVO> navigations) {
		this.navigations = navigations;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	public String getBannerText() {
		return bannerText;
	}

	public void setBannerText(String bannerText) {
		this.bannerText = bannerText;
	}

	public String getTeaserText() {
		return teaserText;
	}

	public void setTeaserText(String teaserText) {
		this.teaserText = teaserText;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public String getShowInFooter() {
		return showInFooter;
	}

	public void setShowInFooter(String showInFooter) {
		this.showInFooter = showInFooter;
	}

}
