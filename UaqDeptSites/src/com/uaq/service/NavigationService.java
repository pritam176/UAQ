package com.uaq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Association;
import com.fatwire.rest.beans.Navigation;
import com.fatwire.rest.beans.NavigationBean;
import com.fatwire.wem.sso.SSOException;
import com.uaq.common.AssetUtil;
import com.uaq.common.PropertiesUtil;
import com.uaq.controller.mapper.BaseVOMapper;
import com.uaq.dao.BaseDAO;
import com.uaq.dao.GenericDao;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;
import com.uaq.util.UrlTransliterationUtil;
import com.uaq.vo.Asset;
import com.uaq.vo.DepartmentVO;
import com.uaq.vo.ImageVO;
import com.uaq.vo.NavigationVO;
import com.uaq.vo.NewsVO;

/**
 * Service class for login which validates the password provided by the user and
 * the one returned form the database.
 * 
 * @author nsharma
 * 
 */
@Service(value = "navigationService")
public class NavigationService {

	private String homeId;

	private String homeSiteId;

	private String ticketId;

	private String site;

	@Autowired
	@Qualifier("navigationDAO")
	GenericDao<NavigationVO, NavigationVO> navigationDAO;

	@Autowired
	@Qualifier("localizedNavigationDAO")
	GenericDao<NavigationVO, NavigationVO> localizedNavigationDAO;

	@Autowired
	@Qualifier("latestNewsDAO")
	BaseDAO<NewsVO, NewsVO> latestNewsDAO;

	@Autowired
	@Qualifier("imageMapper")
	BaseVOMapper imageMapper;

	@Autowired
	@Qualifier("departmentDetailMapper")
	BaseVOMapper departmentDetailMapper;

	@Autowired
	@Qualifier("getAssetDetailsDAO")
	BaseDAO<Asset, List<Asset>> getAssetDetailsDAO;

	@Autowired
	private MessageSource messageSource;

	protected static UAQLogger logger = new UAQLogger(NavigationService.class);

	/**
	 * @param multiticket
	 * @param client
	 * @param homeVO
	 * @return
	 * @throws SSOException
	 * @throws UAQException
	 */
	public Map<String, List<NavigationVO>> getPortalNavigationDetails(String sites, String multiticket) throws SSOException, UAQException {

		Map<String, List<NavigationVO>> navigationMap;

		NavigationBean navigationBean;
		NavigationVO firstLevelNavigationVOAR;
		NavigationVO secondLevelNavigationVOAR;
		NavigationVO thirdLevelNavigationVOAR;
		NavigationVO firstLevelNavigationVOEN;
		NavigationVO secondLevelNavigationVOEN;
		NavigationVO thirdLevelNavigationVOEN;
		List<NavigationVO> navigationsFirstLevelListAR = new ArrayList<NavigationVO>();
		List<NavigationVO> navigationsSecondLevelListAR = null;
		List<NavigationVO> navigationsThirdLevelListAR = null;
		List<NavigationVO> navigationsFirstLevelListEN = new ArrayList<NavigationVO>();
		List<NavigationVO> navigationsSecondLevelListEN = null;
		List<NavigationVO> navigationsThirdLevelListEN = null;
		navigationMap = new HashMap<String, List<NavigationVO>>();

		navigationBean = AssetUtil.getSitePlan(PropertiesUtil.getProperty(sites + "_csSiteName"), multiticket);
		Long siteId = navigationBean.getNavigation().getPlacedChildren().getchildren().get(0).getId();
		//logger.debug("-------Site  Id" + siteId);
		Navigation rootNavigation = navigationBean.getNavigation().getPlacedChildren().getchildren().get(0).getPlacedChildren().getchildren().get(0);
		Long homePageId = rootNavigation.getId();
		//logger.debug("--First Level Id" + homePageId);
		this.homeId = String.valueOf(homePageId);
		// Iterate through the First Level of the Site Plan
		for (Navigation firstLevelNavigation : rootNavigation.getPlacedChildren().getchildren()) {

			firstLevelNavigationVOAR = getNavigationVO(String.valueOf(firstLevelNavigation.getId()));
			navigationsSecondLevelListAR = new ArrayList<NavigationVO>();
			navigationsSecondLevelListEN = new ArrayList<NavigationVO>();

			// Iterate through the Second Level of the Site Plan
			if (null != firstLevelNavigation.getPlacedChildren()) {
				for (Navigation secondLevelNavigation : firstLevelNavigation.getPlacedChildren().getchildren()) {
					secondLevelNavigationVOAR = getNavigationVO(String.valueOf(secondLevelNavigation.getId()));
					navigationsThirdLevelListAR = new ArrayList<NavigationVO>();
					navigationsThirdLevelListEN = new ArrayList<NavigationVO>();
					// Iterate through the third Level of the Site Plan
					if (null != secondLevelNavigation.getPlacedChildren()) {
						for (Navigation thirdLevelNavigation : secondLevelNavigation.getPlacedChildren().getchildren()) {
							thirdLevelNavigationVOAR = getNavigationVO(String.valueOf(thirdLevelNavigation.getId()));
							navigationsThirdLevelListAR.add(thirdLevelNavigationVOAR);
							// Adding Translated Content of the Page Asset of
							// the Third Level
							thirdLevelNavigationVOEN = getLocalisedNavigationVO(String.valueOf(thirdLevelNavigation.getId()));
							if (null != thirdLevelNavigationVOEN) {
								navigationsThirdLevelListEN.add(thirdLevelNavigationVOEN);
							}
						}
					}
					secondLevelNavigationVOAR.setNavigations(navigationsThirdLevelListAR);
					navigationsSecondLevelListAR.add(secondLevelNavigationVOAR);
					// Adding Translated Content of the Page Asset of the Second
					// Level
					secondLevelNavigationVOEN = getLocalisedNavigationVO(String.valueOf(secondLevelNavigation.getId()), 2);
					if (null != secondLevelNavigationVOEN) {
						secondLevelNavigationVOEN.setNavigations(navigationsThirdLevelListEN);
						navigationsSecondLevelListEN.add(secondLevelNavigationVOEN);

					}

				}
			}
			firstLevelNavigationVOAR.setNavigations(navigationsSecondLevelListAR);
			navigationsFirstLevelListAR.add(firstLevelNavigationVOAR);

			firstLevelNavigationVOEN = getLocalisedNavigationVO(String.valueOf(firstLevelNavigation.getId()), 1);
			if (null != firstLevelNavigationVOEN) {
				firstLevelNavigationVOEN.setNavigations(navigationsSecondLevelListEN);
				navigationsFirstLevelListEN.add(firstLevelNavigationVOEN);
			}
		}

		updateURLForNavigations(navigationsFirstLevelListAR);
		updateURLForNavigations(navigationsFirstLevelListEN);

		navigationMap.put("ar", navigationsFirstLevelListAR);
		navigationMap.put("en", navigationsFirstLevelListEN);
		return navigationMap;
	}

	/**
	 * @param sites
	 * @param multiticket
	 * @return
	 * @throws UAQException
	 * @throws SSOException
	 */
	public Map<String, List<NavigationVO>> getSitesNavigationDetails(String sites, String multiticket) throws SSOException, UAQException {

		Map<String, List<NavigationVO>> navigationMap = getPortalNavigationDetails(sites, multiticket);

		List<NavigationVO> navigationsSitePlanEN = navigationMap.get("en");
		List<NavigationVO> navigationsSitePlanAR = navigationMap.get("ar");

		NavigationBean navigationBean;
		NavigationVO firstLevelNavigationVOEN = null;
		NavigationVO firstLevelNavigationVOAR = null;
		List<NavigationVO> navigationsFirstLevelListAR = new ArrayList<NavigationVO>();
		List<NavigationVO> navigationsFirstLevelListEN = new ArrayList<NavigationVO>();
		navigationMap = new HashMap<String, List<NavigationVO>>();
		navigationMap.put("en-siteplan", navigationsSitePlanEN);
		navigationMap.put("ar-siteplan", navigationsSitePlanAR);
		Boolean assetFound = Boolean.FALSE;

		try {
			navigationBean = AssetUtil.getSitePlan(PropertiesUtil.getProperty(sites + "_csSiteName"), multiticket);
			Long siteId = navigationBean.getNavigation().getPlacedChildren().getchildren().get(0).getId();
			//logger.debug("-------Site  Id" + siteId);
			Navigation rootNavigation = navigationBean.getNavigation().getPlacedChildren().getchildren().get(0).getPlacedChildren().getchildren().get(0);
			Long homePageId = rootNavigation.getId();
			this.homeSiteId = String.valueOf(homePageId);
			String associatedAssetId;
			AssetBean assetBean = AssetUtil.getAssetDetail(sites, "Page", String.valueOf(homePageId), multiticket);
			for (Association association : assetBean.getAssociations().getAssociations()) {
				if (association.getName().equals("RelatedHubItems")) {
					for (String associatedAsset : association.getAssociatedAssets()) {
						assetFound = Boolean.FALSE;
						firstLevelNavigationVOAR = null;
						associatedAssetId = associatedAsset.split(":")[1];

						logger.debug("Step 1: Get the asset details of the associated asset in the page for the asset with asset id: " + associatedAssetId);
						// Iterate through the Site Plan in Aarbic and check for
						// the associated Asset
						for (NavigationVO navigationFirstLevelAR : navigationsSitePlanAR) {
							if (!assetFound) {
								// firstLevelNavigationVOAR = new
								// NavigationVO();
								if (navigationFirstLevelAR.getAssetId().equals(associatedAssetId)) {
									assetFound = Boolean.TRUE;
									firstLevelNavigationVOAR = navigationFirstLevelAR;
								}

								if (!assetFound && null != navigationFirstLevelAR.getNavigations() && navigationFirstLevelAR.getNavigations().size() > 0) {
									// Navigate through the second level of
									// children
									for (NavigationVO navigationSecondLevel : navigationFirstLevelAR.getNavigations()) {
										if (navigationSecondLevel.getAssetId().equals(associatedAssetId)) {
											assetFound = Boolean.TRUE;
											firstLevelNavigationVOAR = navigationSecondLevel;
										}

										if (!assetFound && null != navigationSecondLevel.getNavigations() && navigationSecondLevel.getNavigations().size() > 0) {
											// Navigate through the second level
											// of
											// children
											for (NavigationVO navigationThirdLevel : navigationSecondLevel.getNavigations()) {
												if (navigationThirdLevel.getAssetId().equals(associatedAssetId)) {
													assetFound = Boolean.TRUE;
													firstLevelNavigationVOAR = navigationThirdLevel;
												}
											}
										}

									}
								}
							}

						}

						if (!assetFound) {
							firstLevelNavigationVOAR = getNavigationVO(associatedAssetId);
							firstLevelNavigationVOAR.setUrl(getPageURL(firstLevelNavigationVOAR.getName(), Boolean.TRUE));

						}

						assetFound = Boolean.FALSE;
						firstLevelNavigationVOEN = null;
						NavigationVO translatedAsset = getLocalisedNavigationVO(associatedAssetId);
						if (null != associatedAssetId) {
							String associatedEnglishAssetId = translatedAsset.getAssetId();
							// Iterate through the Site Plan in Aarbic and check
							// for
							// the associated Asset
							for (NavigationVO navigationFirstLevelEN : navigationsSitePlanEN) {
								if (!assetFound) {
									if (navigationFirstLevelEN.getAssetId().equals(associatedEnglishAssetId)) {
										assetFound = Boolean.TRUE;
										firstLevelNavigationVOEN = navigationFirstLevelEN;
									}

									if (!assetFound && null != navigationFirstLevelEN.getNavigations() && navigationFirstLevelEN.getNavigations().size() > 0) {
										// Navigate through the second level of
										// children
										for (NavigationVO navigationSecondLevel : navigationFirstLevelEN.getNavigations()) {
											if (navigationSecondLevel.getAssetId().equals(associatedEnglishAssetId)) {
												assetFound = Boolean.TRUE;
												firstLevelNavigationVOEN = navigationSecondLevel;
											}

											if (!assetFound && null != navigationSecondLevel.getNavigations() && navigationSecondLevel.getNavigations().size() > 0) {
												// Navigate through the second
												// level
												// of
												// children
												for (NavigationVO navigationThirdLevel : navigationSecondLevel.getNavigations()) {
													if (navigationThirdLevel.getAssetId().equals(associatedEnglishAssetId)) {
														assetFound = Boolean.TRUE;
														firstLevelNavigationVOEN = navigationThirdLevel;
													}
												}
											}

										}

									}

								}
							}
						}

						if (!assetFound) {
							firstLevelNavigationVOEN = getLocalisedNavigationVO(associatedAssetId);
							firstLevelNavigationVOEN.setUrl(getPageURL(firstLevelNavigationVOEN.getName(), Boolean.TRUE));

						}

						if (!StringUtils.isEmpty(firstLevelNavigationVOAR.getDisplayTypeHome())) {

							if ((null != firstLevelNavigationVOEN.getDisplayTypeHome() && firstLevelNavigationVOEN.getDisplayTypeHome().equals("MediaTree"))
									|| firstLevelNavigationVOAR.getDisplayTypeHome().equals("MediaTree")) {
								logger.debug("Get Latest News & Navigations");
								NavigationVO navigationNews = getLatestNews("ar", sites, multiticket);
								if (null != navigationNews) {
									Locale locale = new Locale("ar");

									firstLevelNavigationVOAR.getNavigations().get(0).setTeaserImage(navigationNews.getTeaserImage());
									firstLevelNavigationVOAR.getNavigations().get(0).setTitle(messageSource.getMessage("breadcrum.news", null, locale));
									firstLevelNavigationVOAR.getNavigations().get(0).setHeading(navigationNews.getTeaserTitle());
									firstLevelNavigationVOAR.getNavigations().get(0).setTeaserText(navigationNews.getTeaserText());
									firstLevelNavigationVOAR.getNavigations().get(0).setDate(navigationNews.getDate());

								}
								navigationNews = getLatestNews("en", sites, multiticket);
								if (null != navigationNews) {
									Locale locale = new Locale("en");
									firstLevelNavigationVOEN.getNavigations().get(0).setTeaserImage(navigationNews.getTeaserImage());
									firstLevelNavigationVOEN.getNavigations().get(0).setTitle(messageSource.getMessage("breadcrum.news", null, locale));
									firstLevelNavigationVOEN.getNavigations().get(0).setHeading(navigationNews.getTeaserTitle());
									firstLevelNavigationVOEN.getNavigations().get(0).setTeaserText(navigationNews.getTeaserText());
									firstLevelNavigationVOEN.getNavigations().get(0).setDate(navigationNews.getDate());
								}

							} else if ((null != firstLevelNavigationVOEN.getDisplayTypeHome() && firstLevelNavigationVOEN.getDisplayTypeHome().equals("Tree"))
									|| firstLevelNavigationVOAR.getDisplayTypeHome().equals("Tree")) {
								logger.debug("Navigation is already Loaded from Navigation VO as part of Site Plan");
							} else if (firstLevelNavigationVOAR.getDisplayTypeHome().equals("Images")) {
								firstLevelNavigationVOAR.setUrl("media-center/image-gallery.html");
								logger.debug("Images are already loaded from Navigation VO as part of Association");
							} else if (firstLevelNavigationVOAR.getDisplayTypeHome().equals("Image")) {
								logger.debug("Image is already loaded from Navigation VO as part of Association");
								/*firstLevelNavigationVOAR.setUrl("media-center/imagegallery.html");*/
							} else if (firstLevelNavigationVOAR.getDisplayTypeHome().equals("Videos")) {
								logger.debug("Image is already loaded from Navigation VO as part of Association");
							} else if (firstLevelNavigationVOAR.getDisplayTypeHome().equals("Poll")) {
								logger.debug("Poll is already loaded from Navigation VO as part of Association");
							} else if (firstLevelNavigationVOAR.getDisplayTypeHome().equals("Detail")) {
								logger.debug("Detail is already there in Navigation VO");
							} else if ((null != firstLevelNavigationVOEN.getDisplayTypeHome() && firstLevelNavigationVOEN.getDisplayTypeHome().equals("ContactUs"))
									|| firstLevelNavigationVOAR.getDisplayTypeHome().equals("ContactUs")) {
								logger.debug("Loading the Department Asset as part of Contact Us");

								DepartmentVO departmentVO = getDepartmentDetails("ar", sites, multiticket);
								if (null != departmentVO) {
									firstLevelNavigationVOAR.setDepartmentVO(departmentVO);
								}
								departmentVO = getDepartmentDetails("en", sites, multiticket);
								if (null != departmentVO) {
									firstLevelNavigationVOEN.setDepartmentVO(departmentVO);
								}

							} else if (firstLevelNavigationVOAR.getDisplayTypeHome().equals("List")) {
								logger.debug("Association is already loaded from Navigation VO");
							}
						}

						navigationsFirstLevelListAR.add(firstLevelNavigationVOAR);

						if (null != firstLevelNavigationVOEN) {
							navigationsFirstLevelListEN.add(firstLevelNavigationVOEN);
						}
					}

				}
			}

		} catch (SSOException e) {
			logger.error("Exception while loading the asset" + e.getMessage());
		} catch (UAQException uaq) {
			logger.error("Exception while loading the asset" + uaq.getMessage());
		}
		// updateURLForNavigations(navigationsFirstLevelListAR);
		// updateURLForNavigations(navigationsFirstLevelListEN);

		navigationMap.put("ar", navigationsFirstLevelListAR);
		navigationMap.put("en", navigationsFirstLevelListEN);
		return navigationMap;

	}

	/**
	 * @param sites
	 * @param multiticket
	 * @return
	 * @throws UAQException
	 * @throws SSOException
	 */
	private DepartmentVO getDepartmentDetails(String language, String sites, String multiticket) throws UAQException, SSOException {
		DepartmentVO departmentVO = null;
		Asset asset = new Asset();
		asset.setSite(PropertiesUtil.getProperty(sites + "_csSiteName"));
		asset.setAssetType("Department");
		asset.setLanguage(language);
		List<Asset> departments = getAssetDetailsDAO.execute(asset);
		if (null != departments && departments.size() > 0) {
			AssetBean departmentAssetBean = AssetUtil.getAssetDetail(sites, "Content_C", departments.get(0).getAssetId(), multiticket);
			departmentVO = (DepartmentVO) departmentDetailMapper.mapAssetToVO(departmentAssetBean);

		}
		return departmentVO;
	}

	/**
	 * @param sites
	 * @param multiticket
	 * @return
	 * @throws UAQException
	 * @throws SSOException
	 */
	private NavigationVO getLatestNews(String language, String sites, String multiticket) throws UAQException, SSOException {
		NavigationVO newsNavigation = null;
		NewsVO query = new NewsVO();
		query.setLanguage(language);
		query.setSite(sites);

		NewsVO latestNews = latestNewsDAO.execute(query);
		if (null != latestNews) {
			newsNavigation = new NavigationVO();
			newsNavigation.setTeaserTitle(latestNews.getTitle());
			newsNavigation.setTeaserText(latestNews.getTeaserText());
			newsNavigation.setDate(latestNews.getDate());
			if (null != latestNews.getImage()) {
				AssetBean imageAsset = AssetUtil.getAssetDetail(sites, "Content_C", latestNews.getImage(), multiticket);
				ImageVO newsImageVO = (ImageVO) imageMapper.mapAssetToVO(imageAsset);
				newsNavigation.setTeaserImage(newsImageVO.getTeaserImage());

			}

		}
		return newsNavigation;
	}

	/**
	 * Read the site plan from the page
	 * 
	 * @param pageId
	 *            page id of the page
	 * @param site
	 *            site which has the asset
	 * @param multiticket
	 *            ticket for session
	 * @return list of pages
	 * @throws SSOException
	 *             if exception occurs reading the asset
	 */
	public List<NavigationVO> getLocalizedChildren(List<NavigationVO> childeren) throws SSOException {

		List<NavigationVO> navigationVOs = new ArrayList<NavigationVO>();
		NavigationVO navigationVO = null;
		try {
			for (NavigationVO child : childeren) {
				navigationVO = localizedNavigationDAO.findByPrimaryKey(child);
				if (null != navigationVO) {
					navigationVOs.add(navigationVO);
				}
			}
		} catch (UAQException e) {
			logger.error("Error while getting Localized Content" + e.getMessage());

		}
		return navigationVOs;
	}

	/**
	 * Read the site plan from the page
	 * 
	 * @param pageId
	 *            page id of the page
	 * @param site
	 *            site which has the asset
	 * @param multiticket
	 *            ticket for session
	 * @return list of pages
	 * @throws SSOException
	 *             if exception occurs reading the asset
	 */
	public List<NavigationVO> getChildren(String pageId) throws SSOException {

		List<NavigationVO> navigationVOs = new ArrayList<NavigationVO>();
		NavigationVO navigationVO;

		NavigationBean navigationBean = AssetUtil.getChildren(pageId, PropertiesUtil.getProperty(getSite() + "_csSiteName"), getTicketId());
		if (null != navigationBean.getNavigation().getPlacedChildren() && null != navigationBean.getNavigation().getPlacedChildren().getchildren()
				&& navigationBean.getNavigation().getPlacedChildren().getchildren().size() > 0) {
			for (Navigation navigation : navigationBean.getNavigation().getPlacedChildren().getchildren()) {
				navigationVO = getNavigationVO(String.valueOf(navigation.getId()));
				navigationVO.setTicketId(getTicketId());
				navigationVO.setSite(getSite());
				navigationVOs.add(navigationVO);

			}
		}

		return navigationVOs;
	}

	/**
	 * Get the Navigation VO from the asset by reading it from the CMS
	 * 
	 * @param assetId
	 *            asset to be read from the CMS
	 * @param sites
	 *            site which has the asset
	 * @param multiticket
	 *            ticket for session
	 * @return NavigationVO with data
	 */

	public NavigationVO getNavigationVO(String assetId) {
		//logger.debug("Service | Get the asset details of the asset" + assetId);
		NavigationVO navigationVO = null;
		NavigationVO queryVO = new NavigationVO();
		queryVO.setSite(site);
		queryVO.setTicketId(ticketId);
		queryVO.setAssetId(assetId);
		try {
			navigationVO = navigationDAO.findByPrimaryKey(queryVO);
		} catch (UAQException e) {
			logger.error("Error Gettong Details");
		}
		return navigationVO;
	}

	/**
	 * Get the Localized VO for the Page Asset
	 * 
	 * @param assetId
	 * @param level
	 * @return
	 * @throws UAQException
	 */
	public NavigationVO getLocalisedNavigationVO(String assetId, int level) throws UAQException {
		//logger.debug("Service | Get the asset details of the asset" + assetId);
		NavigationVO navigationVO = null;
		NavigationVO queryVO = new NavigationVO();
		queryVO.setSite(site);
		queryVO.setTicketId(ticketId);
		queryVO.setAssetId(assetId);
		queryVO.setLevel(level);
		navigationVO = localizedNavigationDAO.findByPrimaryKey(queryVO);
		return navigationVO;
	}

	/**
	 * Get the Localized VO for the Page Asset
	 * 
	 * @param assetId
	 * @param level
	 * @return
	 * @throws UAQException
	 */
	public NavigationVO getLocalisedNavigationVO(String assetId) throws UAQException {
		//logger.debug("Service | Get the asset details of the asset" + assetId);
		NavigationVO navigationVO = null;
		NavigationVO queryVO = new NavigationVO();
		queryVO.setSite(site);
		queryVO.setTicketId(ticketId);
		queryVO.setAssetId(assetId);
		navigationVO = localizedNavigationDAO.findByPrimaryKey(queryVO);
		return navigationVO;
	}

	/**
	 * @param navigationsFirstLevelList
	 */
	private void updateURLForNavigations(List<NavigationVO> navigationsFirstLevelList) {

		for (NavigationVO navigationVOFirstLevel : navigationsFirstLevelList) {
			navigationVOFirstLevel.setUrl(getPageURL(navigationVOFirstLevel.getName(), Boolean.TRUE));
			if (null != navigationVOFirstLevel.getNavigations() && navigationVOFirstLevel.getNavigations().size() > 0) {
				for (NavigationVO navigationVOSecondLevel : navigationVOFirstLevel.getNavigations()) {
					navigationVOSecondLevel.setUrl(getPageURL(navigationVOFirstLevel.getName(), Boolean.FALSE) + "/" + getPageURL(navigationVOSecondLevel.getName(), Boolean.TRUE));
					if (null != navigationVOSecondLevel.getNavigations() && navigationVOSecondLevel.getNavigations().size() > 0) {
						for (NavigationVO navigationVOThirdLevel : navigationVOSecondLevel.getNavigations()) {
							navigationVOThirdLevel.setUrl(getPageURL(navigationVOFirstLevel.getName(), Boolean.FALSE) + "/" + getPageURL(navigationVOSecondLevel.getName(), Boolean.FALSE) + "/"
									+ getPageURL(navigationVOThirdLevel.getName(), Boolean.TRUE));

						}

					}

				}

			}
		}

	}

	/**
	 * /**
	 * 
	 * @param currentPageId
	 * @param currentPageName
	 * @return
	 */
	public String getPageURL(String currentPageName, Boolean appendHtml) {
		StringBuffer url = new StringBuffer();
		url.append(UrlTransliterationUtil.getTransliteratedString(currentPageName));
		if (appendHtml) {
			url.append(".html");
		}
		return url.toString();
	}

	/**
	 * Home Page ID
	 * 
	 * @return
	 */
	public String getHomeId() {
		return homeId;
	}

	/**
	 * Set Home Page ID
	 * 
	 * @param homeId
	 */
	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getHomeSiteId() {
		return homeSiteId;
	}

	public void setHomeSiteId(String homeSiteId) {
		this.homeSiteId = homeSiteId;
	}

}
