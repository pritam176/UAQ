/**
 * 
 */
package com.uaq.util;

import javax.servlet.http.HttpServletRequest;

import com.uaq.command.SearchCommand;

/**
 * @author raheem
 * 
 */
public class SessionUtil {

	public static final String SEARCH_FILTERS_OBJECT = "search_filters_object";

	/**
	 * preserves search filters
	 * 
	 * @param request
	 * @param searchCommand
	 */
	public static void saveSearchFilters(HttpServletRequest request, SearchCommand searchCommand) {
		request.getSession().setAttribute(SEARCH_FILTERS_OBJECT, searchCommand);

	}

	/**
	 * removes search filters from session
	 * 
	 * @param request
	 */
	public static void removeSearchFilters(HttpServletRequest request) {
		request.getSession().removeAttribute(SEARCH_FILTERS_OBJECT);

	}

	/**
	 * update search filters with preserved filters
	 * 
	 * @param request
	 * @param searchCommand
	 */
	public static void updateSearchCommandWithFilters(HttpServletRequest request, SearchCommand searchCommand) {

		SearchCommand sessionSearchCommand = (SearchCommand) request.getSession().getAttribute(SEARCH_FILTERS_OBJECT);

		if (sessionSearchCommand != null) {
			searchCommand.setKeyword(sessionSearchCommand.getKeyword());
			searchCommand.setCategory(sessionSearchCommand.getCategory());
			searchCommand.setStartDate(sessionSearchCommand.getStartDate());
			searchCommand.setEndDate(sessionSearchCommand.getEndDate());
			searchCommand.setSortOrder(sessionSearchCommand.getSortOrder());
		}
	}

}
