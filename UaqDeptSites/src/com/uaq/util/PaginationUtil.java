package com.uaq.util;

import com.uaq.vo.SearchResponseVO;

public class PaginationUtil {

	/**
	 * @param searchResponseVO
	 */
	public static void updateSearcvhResponseWithPaginationLimits(SearchResponseVO searchResponseVO, long pageSize, long paginationSetSize) {
		long numRows = searchResponseVO.getTotalNumberOfrows();

		long paginationSetNumber;
		if (searchResponseVO.getCurrentPage() % paginationSetSize != 0) {
			paginationSetNumber = searchResponseVO.getCurrentPage() / paginationSetSize + 1;
		} else {
			paginationSetNumber = searchResponseVO.getCurrentPage() / paginationSetSize;
		}
		searchResponseVO.setLowerLimitPagination((paginationSetNumber - 1) * paginationSetSize + 1);
		searchResponseVO.setUpperLimitPagination(paginationSetNumber * paginationSetSize);

		if (numRows % pageSize > 0) {
			searchResponseVO.setTotalNumberOfPages((numRows / pageSize) + 1);

		} else {
			searchResponseVO.setTotalNumberOfPages(numRows / pageSize);
		}
	}

}
