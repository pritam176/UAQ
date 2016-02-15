package com.uaq.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetInfo;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.command.SearchCommand;
import com.uaq.common.AssetUtil;
import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.controller.mapper.ProjectsDetailMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.PaginationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.ImageVO;
import com.uaq.vo.ProjectsVO;
import com.uaq.vo.SearchResponseVO;

/**
 * This class is used to manage departments related functionality
 * 
 * @author arjun
 * 
 */
@Service(value = "projectsService")
public class ProjectsService implements BaseService<ProjectsVO, List<ProjectsVO>> {

	@Autowired
	@Qualifier("projectsDetailMapper")
	private BaseVOMapper mapper;

	@Autowired
	@Qualifier("imageMapper")
	private BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("getAssetDetailsDAO")
	BaseDAO<Asset, List<Asset>> getAssetDetailsDAO;

	protected static UAQLogger logger = new UAQLogger(ProjectsService.class);

	/**
	 * This method is not used
	 */
	@Override
	public List<ProjectsVO> execute(ProjectsVO projectsVO) throws UAQException {
		List<ProjectsVO> projectsList = new ArrayList<ProjectsVO>();
		AssetsBean assetsBean;
		try {
			assetsBean = AssetUtil.searchAssetbyDefinition("Project", projectsVO.getSite(), "DateAndTime", "desc", projectsVO.getTicketId());
			projectsList = getProjectsVOList(assetsBean, projectsVO);
		} catch (SSOException e) {
			logger.error("Error get Projects List");
		}
		return projectsList;

	}

	public SearchResponseVO getProjectsList(SearchCommand projectsSearchCommand, String sector, Asset assetobj) throws UAQException, SSOException {

		List<Asset> objAsset = null;
		AssetBean assetBean = new AssetBean();
		SearchResponseVO searchResponseVO = new SearchResponseVO();
		ProjectsVO projectsVO = new ProjectsVO();
		ProjectsDetailMapper projectsMapper = new ProjectsDetailMapper();

		int pageSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.pagesize"));
		int paginationSetSize = Integer.valueOf(PropertiesUtil.getProperty("pagination.setsize"));
		projectsSearchCommand.setStartRow((projectsSearchCommand.getCurrentPage() - 1) * pageSize);

		List<ProjectsVO> projectsList = new ArrayList<ProjectsVO>();
		List<ProjectsVO> projectsFinalList= new ArrayList<ProjectsVO>();

		projectsSearchCommand.setPageSize(pageSize);

		objAsset = getAssetDetailsDAO.execute(assetobj);

		for (Asset eachAsset : objAsset) {
			assetBean = AssetUtil.getAssetDetail(sector, "Content_C", eachAsset.getAssetId(), projectsSearchCommand.getTicketId());

			projectsVO = (ProjectsVO) projectsMapper.mapAssetToVO(assetBean);

			if (projectsVO.getImages().size() > 0) {
				for (ImageVO imageVO : projectsVO.getImages()) {

					assetBean = AssetUtil.getAssetDetail(sector, imageVO.getAssetType(), imageVO.getAssetId(), projectsSearchCommand.getTicketId());
					imageVO = (ImageVO) imageMapper.mapAssetToVO(assetBean);

					projectsVO.setImage(imageVO.getTeaserImage());
				}
			}
			projectsList.add(projectsVO);
		}
		
		int startRecord = projectsSearchCommand.getStartRow();		
		int endRecord = (projectsSearchCommand.getPageSize()*projectsSearchCommand.getCurrentPage() - 1);
		if(projectsList != null && projectsList.size() > 0 && endRecord >= projectsList.size())
			endRecord = projectsList.size()-1;
		if(endRecord < 0 ){
			endRecord = 0;
		}

		if (projectsList != null && projectsList.size() > 0) {
			for(int i=startRecord;i <= endRecord; i++){			
				projectsFinalList.add(projectsList.get(i));
			}
			searchResponseVO.setSearchProjectResults(projectsFinalList);
			searchResponseVO.setTotalNumberOfrows(projectsList.size());
			searchResponseVO.setCurrentPage(projectsSearchCommand.getCurrentPage());
			PaginationUtil.updateSearcvhResponseWithPaginationLimits(searchResponseVO, pageSize, paginationSetSize);
		}

		return searchResponseVO;
	}

	/**
	 * This method is used to convert the assetsBean to List of ProjectsVO
	 * 
	 * @param assetsBean
	 * @return
	 * @throws SSOException
	 */
	private List<ProjectsVO> getProjectsVOList(AssetsBean assetsBean, ProjectsVO projectsVO) throws SSOException {

		List<ProjectsVO> projects = new ArrayList<ProjectsVO>();
		for (AssetInfo assetBean : assetsBean.getAssetinfos()) {
			ProjectsVO project = new ProjectsVO();
			project.setAssetId(assetBean.getId().split(":")[1]);
			project.setTicketId(projectsVO.getTicketId());
			project.setSite(projectsVO.getSite());
			project = getProjectDetails(project);
			projects.add(project);
		}

		return projects;
	}

	/**
	 * This method is used to get details for the given project id
	 * 
	 * @param ProjectsVO
	 * @return
	 * @throws SSOException
	 */

	private ProjectsVO getProjectDetails(ProjectsVO projectsVO) throws SSOException {
		ProjectsVO projectsVOReturn;
		AssetBean assetBean = AssetUtil.getAssetDetail(projectsVO.getSite(), "Content_C", projectsVO.getAssetId(), projectsVO.getTicketId());

		projectsVOReturn = (ProjectsVO) mapper.mapAssetToVO(assetBean);

		return projectsVOReturn;
	}

	@Override
	public List<ProjectsVO> executeSites(ProjectsVO inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}

}
