package com.uaq.controller.mapper;

import com.fatwire.rest.beans.AssetBean;
import com.uaq.vo.BaseVO;

/**
 * Class to convert Asset bean to View Object.
 * 
 * @author nsharma
 * 
 */
public interface BaseVOMapper {

	/**
	 * Maps asset of type Asset Bean to View Object.
	 * 
	 * @param assetBean
	 *            returned from REST
	 * @return converted object
	 */
	public BaseVO mapAssetToVO(AssetBean assetBean);

}
