package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.ProjectsVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author arjun
 * 
 */
@Service(value = "projectsDetailService")
public class ProjectsDetailService implements BaseService<NavigationVO, ProjectsVO> {

	@Autowired
	@Qualifier("projectsDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("contentReferneceDAO")
	GenericDao<NavigationVO, NavigationVO> contentReferneceDAO;

	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;

	protected static UAQLogger logger = new UAQLogger(ProjectsDetailService.class);

	/**
	 * This method is not used
	 */
	@Override
	public ProjectsVO execute(NavigationVO navigationVO) throws UAQException {
		ProjectsVO projectsVOResult = null;
		try {

			NavigationVO navigation = contentReferneceDAO.findByPrimaryKey(navigationVO);
			navigationVO.setAssetId(navigation.getAssetId());
			projectsVOResult = getProjectDetails(navigationVO);
		} catch (SSOException e) {
			logger.error("Error get Projects List");
		}
		return projectsVOResult;

	}

	/**
	 * This method is used to get details for the given project id
	 * 
	 * @param ProjectsVO
	 * @return
	 * @throws SSOException
	 */

	private ProjectsVO getProjectDetails(NavigationVO navigationVO) throws SSOException {
		ProjectsVO projectsVOReturn;
		AssetBean assetBean = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", navigationVO.getAssetId(), navigationVO.getTicketId());
		List<ImageVO> projectImageVo = new ArrayList<ImageVO>();
		projectsVOReturn = (ProjectsVO) mapper.mapAssetToVO(assetBean);
		if (projectsVOReturn.getImages().size() > 0) {
			for (ImageVO imageVO : projectsVOReturn.getImages()) {

				AssetBean imageAsset = AssetUtil.getAssetDetail(navigationVO.getSite(), "Content_C", imageVO.getAssetId(), navigationVO.getTicketId());
				ImageVO images = getImageAsset(imageAsset);
				projectImageVo.add(images);
			}

			projectsVOReturn.setImages(projectImageVo);
		}

		return projectsVOReturn;
	}

	private ImageVO getImageAsset(AssetBean assetBean) {
		ImageVO newsImageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);
		return newsImageVO;
	}

	@Override
	public ProjectsVO executeSites(NavigationVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
