package com.uaq.vo;

import java.util.List;

/**
 * @author mraheem
 * 
 */
public class VideoSearchResponseVO extends SearchResponseVO {

	private List<VideoVO> searchResults;

	/**
	 * @return the searchResults
	 */
	public List<VideoVO> getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            the searchResults to set
	 */
	public void setSearchResults(List<VideoVO> searchResults) {
		this.searchResults = searchResults;
	}

}
