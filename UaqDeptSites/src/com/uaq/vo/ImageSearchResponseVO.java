package com.uaq.vo;

import java.util.List;

/**
 * @author mraheem
 * 
 */
public class ImageSearchResponseVO extends SearchResponseVO {

	private List<ImageVO> searchResults;

	/**
	 * @return the searchResults
	 */
	public List<ImageVO> getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults
	 *            the searchResults to set
	 */
	public void setSearchResults(List<ImageVO> searchResults) {
		this.searchResults = searchResults;
	}

}
