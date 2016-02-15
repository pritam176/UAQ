package com.uaq.common;

import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.WordUtils;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.AssetsBean;
import com.fatwire.rest.beans.NavigationBean;
import com.fatwire.wem.sso.SSOException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.uaq.logger.UAQLogger;
import com.uaq.util.UrlTransliterationUtil;

public final class AssetUtil {

	private AssetUtil() {
		// not called
	}

	private static transient UAQLogger logger = new UAQLogger(AssetUtil.class);

	private static Client client = Client.create();

	/**
	 * This Method takes the asset Type and Id and returns the corresponding
	 * AssetBean.
	 * 
	 * @param assetType
	 *            asset type
	 * @param assetId
	 *            asset ID
	 * @return AssetBean for the asset type and asset Id.
	 * @throws SSOException
	 */
	public static String getAssetNameGlobal(String url) throws SSOException {
		String assetName = null;
		assetName = url.split("/")[2].split("\\.")[0];
		return WordUtils.capitalize(assetName);
	}

	/**
	 * This Method takes the asset Type and Id and returns the corresponding
	 * AssetBean.
	 * 
	 * @param assetType
	 *            asset type
	 * @param assetId
	 *            asset ID
	 * @return AssetBean for the asset type and asset Id.
	 * @throws SSOException
	 */
	public static String getAssetName(String url) throws SSOException {
		String assetName = null;
		assetName = url.split("/")[3].split("\\.")[0];
		return WordUtils.capitalize(assetName);
	}

	/**
	 * This Method takes the asset Type and Id and returns the corresponding
	 * AssetBean.
	 * 
	 * @param assetType
	 *            asset type
	 * @param assetId
	 *            asset ID
	 * @return AssetBean for the asset type and asset Id.
	 * @throws SSOException
	 */
	public static AssetBean getAssetDetail(String sites, String assetType, String assetId, String multiticket) throws SSOException {
		AssetBean resultAsset = null;
		WebResource webResource = client.resource(PropertiesUtil.getProperty("csUrl") + "/REST");
		webResource = webResource.queryParam("multiticket", multiticket);
		webResource = webResource.path("sites").path(PropertiesUtil.getProperty(sites + "_csSiteName")).path("types").path(assetType).path("assets").path(assetId);
		Builder builder = webResource.accept(MediaType.APPLICATION_XML);
		try {
			resultAsset = builder.get(AssetBean.class);
		} catch (Exception e) {
			logger.error("Error getting Asset Details", e);
		}
		return resultAsset;
	}

	/**
	 * This Method takes the asset Type and Id and returns the corresponding
	 * AssetBean.
	 * 
	 * @param assetType
	 *            asset type
	 * @param assetId
	 *            asset ID
	 * @return AssetBean for the asset type and asset Id.
	 * @throws SSOException
	 */
	public static AssetsBean searchAsset(String assetName, String multiticket) throws SSOException {
		AssetsBean resultAsset = null;
		WebResource webResource = client.resource(PropertiesUtil.getProperty("csUrl") + "/REST");
		webResource = webResource.queryParam("multiticket", multiticket);
		webResource = webResource.queryParam("field:name:equals", assetName);
		webResource = webResource.path("sites").path(PropertiesUtil.getProperty("csSiteName")).path("search");
		Builder builder = webResource.accept(MediaType.APPLICATION_XML);
		try {
			resultAsset = builder.get(AssetsBean.class);
		} catch (Exception e) {
			logger.error("Error while seraching for the asset with asset name: " + assetName, e);
		}
		return resultAsset;
	}

	/**
	 * This Method takes the asset Type and Id and returns the corresponding
	 * AssetBean.
	 * 
	 * @param assetType
	 *            asset type
	 * @param assetId
	 *            asset ID
	 * @return AssetBean for the asset type and asset Id.
	 * @throws SSOException
	 */
	public static AssetBean getAssetFromURL(String url, String multiticket) throws SSOException {
		AssetBean resultAsset = null;
		WebResource webResource = client.resource(url);
		webResource = webResource.queryParam("multiticket", multiticket);
		Builder builder = webResource.accept(MediaType.APPLICATION_XML);
		try {
			resultAsset = builder.get(AssetBean.class);
		} catch (Exception e) {
			logger.error("Error getting Asset from URL: " + url, e);
		}
		return resultAsset;
	}

	/**
	 * Created the blob URL taking the blob id and blob type as parameter.
	 * 
	 * @param blobId
	 *            id for the blob asset
	 * @param type
	 *            of the blob asset
	 * @return the blob url.
	 */
	public static String getBolbURL(String url, String type) {

		int i = url.indexOf("blobwhere=");
		String blobId = url.substring(i + 10, i + 23);

		String blobURL;
		if (type.length() > 0) {
			blobURL = "/cs/Satellite?blobkey=id&blobwhere=" + blobId + "&blobheader=" + type + "&blobcol=urldata&blobtable=MungoBlobs";
		} else {
			blobURL = "/cs/Satellite?blobkey=id&blobwhere=" + blobId + "&blobcol=urldata&blobtable=MungoBlobs";
		}
		return blobURL;

	}

	/**
	 * Gets the Site Plan from the root of the Site
	 * 
	 * @param site
	 *            in the CMS
	 * @param multiticket
	 *            for session
	 * @param client
	 *            to call REST APIs
	 * @return Navigation Bean
	 * @throws SSOException
	 *             Exception if not getting the data
	 */
	public static NavigationBean getSitePlan(String site, String multiticket) throws SSOException {
		NavigationBean navigationBean = null;

		WebResource webResource = client.resource(PropertiesUtil.getProperty("csUrl") + "/REST");
		webResource = webResource.queryParam("multiticket", multiticket);
		webResource = webResource.queryParam("depth", "all");
		webResource = webResource.path("sites").path(site).path("navigation");

		Builder builder = webResource.accept(MediaType.APPLICATION_XML);
		try {
			navigationBean = builder.get(NavigationBean.class);
		} catch (Exception e) {
			logger.error("Error getting Asset Details", e);
		}
		return navigationBean;
	}

	/**
	 * Gets the Site Plan from the root of the Site
	 * 
	 * @param site
	 *            in the CMS
	 * @param multiticket
	 *            for session
	 * @param client
	 *            to call REST APIs
	 * @return Navigation Bean
	 * @throws SSOException
	 *             Exception if not getting the data
	 */
	public static NavigationBean getChildren(String pageId, String site, String multiticket) throws SSOException {
		NavigationBean navigationBean = null;

		WebResource webResource = client.resource(PropertiesUtil.getProperty("csUrl") + "/REST");
		webResource = webResource.queryParam("multiticket", multiticket);
		webResource = webResource.path("sites").path(site).path("navigation").path(pageId);
		Builder builder = webResource.accept(MediaType.APPLICATION_XML);
		try {
			navigationBean = builder.get(NavigationBean.class);
		} catch (Exception e) {
			logger.error("Error getting Asset Details", e);
		}
		return navigationBean;
	}

	/**
	 * This method returns all the assets for a particular subtype based on the
	 * site without sorting
	 * 
	 * @param assetType
	 *            asset subtype
	 * @param sector
	 *            site to be searched
	 * @param multiticket
	 *            multiticket for CAS
	 * @param client
	 *            to invoke REST API
	 * @return list of assets as part of Search
	 * @throws SSOException
	 *             exception in case of invoking URL
	 */
	public static AssetsBean searchAssetbyDefinition(String parentAssetType, String assetType, String sector, String multiticket) throws SSOException {
		AssetsBean resultAsset = null;
		WebResource webResource = client.resource(PropertiesUtil.getProperty("csUrl") + "/REST");
		webResource = webResource.path("sites").path(PropertiesUtil.getProperty(sector + "_" + "csSiteName")).path("types").path(parentAssetType).path("search");
		webResource = webResource.queryParam("field:subtype:equals", assetType);
		webResource = webResource.queryParam("multiticket", multiticket);

		Builder builder = webResource.accept(MediaType.APPLICATION_XML);
		try {
			resultAsset = builder.get(AssetsBean.class);
		} catch (Exception e) {
		}
		return resultAsset;
	}

	public static AssetsBean searchAssetbyDefinitionAndAttribute(String attribute, String parentAssetType, String assetType, String sector, String multiticket) throws SSOException {
		AssetsBean resultAsset = null;
		WebResource webResource = client.resource(PropertiesUtil.getProperty("csUrl") + "/REST");
		webResource = webResource.path("sites").path(PropertiesUtil.getProperty(sector + "_" + "csSiteName")).path("types").path(parentAssetType).path("search");
		webResource = webResource.queryParam("field:subtype:equals", assetType);
		if (attribute != null) {
			webResource = webResource.queryParam("field:TeaserText:equals", attribute); // change
																						// TeaserText
																						// to
																						// category
		}
		webResource = webResource.queryParam("multiticket", multiticket);

		Builder builder = webResource.accept(MediaType.APPLICATION_XML);
		try {
			resultAsset = builder.get(AssetsBean.class);
		} catch (Exception e) {
		}
		return resultAsset;
	}

	/**
	 * 
	 * This method returns all the assets for a particular subtype based on the
	 * site with sorting parameter
	 * 
	 * @param assetType
	 *            asset subtype
	 * @param sector
	 *            site to be searched
	 * @param sortField
	 *            field to be sorted on
	 * @param sortOrder
	 *            des|asc
	 * @param multiticket
	 *            multiticket for CAS
	 * @param client
	 *            to invoke REST API
	 * @return list of assets as part of Search
	 * @throws SSOException
	 *             exception in case of invoking URL
	 */
	public static AssetsBean searchAssetbyDefinition(String assetType, String sector, String sortField, String sortOrder, String multiticket) throws SSOException {
		AssetsBean resultAsset = null;
		WebResource webResource = client.resource(PropertiesUtil.getProperty("csUrl") + "/REST");
		webResource = webResource.path("sites").path(PropertiesUtil.getProperty(sector + "_" + "csSiteName")).path("types").path("Content_C").path("search");
		webResource = webResource.queryParam("field:subtype:equals", assetType);
		webResource = webResource.queryParam("sortfield:" + sortField + ":" + sortOrder, "");
		webResource = webResource.queryParam("multiticket", multiticket);

		Builder builder = webResource.accept(MediaType.APPLICATION_XML);
		try {
			resultAsset = builder.get(AssetsBean.class);
		} catch (Exception e) {
		}
		return resultAsset;
	}

	/**
	 * @param string
	 * @param name
	 * @param assetSubType
	 * @return
	 */
	public static String getModuleURL(String name, String assetSubType) {
		StringBuffer url = new StringBuffer();
		url = url.append(assetSubType.toLowerCase());
		url.append("/");
		url.append(UrlTransliterationUtil.getTransliteratedString(name));
		url.append(".html");
		return url.toString();
	}

	/**
	 * @param string
	 * @param name
	 * @param assetSubType
	 * @return
	 */
	public static String getModuleURLSite(String name, String assetSubType) {
		StringBuffer url = new StringBuffer();
		if (assetSubType.equalsIgnoreCase("project") || assetSubType.equalsIgnoreCase("report") || assetSubType.equalsIgnoreCase("service") || assetSubType.equalsIgnoreCase("events")) {
			url = url.append("our-activities");
			url.append("/");
		} else if (assetSubType.equalsIgnoreCase("award") || assetSubType.equalsIgnoreCase("certificate")) {
			url = url.append("aboutus");
			url.append("/");
		} else if (assetSubType.equalsIgnoreCase("news") || assetSubType.equalsIgnoreCase("publication")) {
			url = url.append("media-center");
			url.append("/");
		}

		url = url.append(assetSubType.toLowerCase());
		url.append("/");
		url.append(UrlTransliterationUtil.getTransliteratedString(name));
		url.append(".html");
		return url.toString();
	}

}