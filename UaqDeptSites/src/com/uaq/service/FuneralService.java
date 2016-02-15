package com.uaq.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Service;

import com.fatwire.rest.beans.AssetBean;
import com.fatwire.rest.beans.Attribute;
import com.fatwire.rest.beans.Attribute.Data;
import com.fatwire.rest.beans.DimensionValue;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.uaq.command.FuneralCommand;
import com.uaq.common.PropertiesUtil;
import com.uaq.exception.UAQException;
import com.uaq.logger.UAQLogger;

/**
 * 
 * @author mraheem
 * 
 */
@Service(value = "funeralService")
public class FuneralService implements BaseService<FuneralCommand, Boolean> {
	
	private static transient UAQLogger logger = new UAQLogger(FuneralService.class);
	
	private static Client client = Client.create();

	@Override
	public Boolean execute(FuneralCommand funeralCommand) throws UAQException {

		Boolean isFuneralSuccessfull = true;

		createFuneralInCMS(funeralCommand);		

		return isFuneralSuccessfull;
	}
	
	private String createFuneralInCMS(FuneralCommand funeralCommand) {

		logger.enter("createFuneralInCMS");
		
		String asetId = null;
				
		if (null != funeralCommand) {
			WebResource webResource = client.resource(PropertiesUtil.getProperty("csUrl") + "/REST");
			webResource = webResource.queryParam("multiticket", funeralCommand.getSessionId());
			webResource = webResource.path("sites").path(PropertiesUtil.getProperty(funeralCommand.getSite() + "_csSiteName")).path("types").path("Content_C").path("assets").path("0");
			Builder builder = webResource.accept(MediaType.APPLICATION_XML);

			// Add the CSRF header
			builder = builder.header("X-CSRF-Token", funeralCommand.getSessionId());
			
			try {
				
				AssetBean sourceAsset = createFuneralAsset(funeralCommand);			
				AssetBean resultAsset = builder.put(AssetBean.class, sourceAsset);
				asetId = resultAsset.getId().split(":")[1];								
			} catch (Exception exception) {				
				logger.error("An exception occured while creating news Asset" + exception.getMessage());
			}
		}
		return asetId;

	}

	/**
	 * @param wamNews
	 * @return
	 * @throws ParseException 
	 */
	private AssetBean createFuneralAsset(FuneralCommand funeralCommand) throws ParseException {
		AssetBean sourceAsset = new AssetBean();
		Attribute sourceAssetAttribute = new Attribute();
		Data sourceAssetAttributeData = new Data();
		
		final SimpleDateFormat formatterDate = new SimpleDateFormat("MM/dd/yyyy");
		
		// Name - mandatory field
		sourceAsset.setName(funeralCommand.getName());

		// Description - optional field
		sourceAsset.setDescription(funeralCommand.getName());
		
				
		// Setting Departee Name
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("DeparteeName");
		sourceAssetAttributeData.setStringValue(funeralCommand.getDeparteeName());
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);
		
		// Setting Funeral Date
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("FuneralDate");		
		sourceAssetAttributeData.setDateValue(formatterDate.parse(funeralCommand.getFuneralDate()));		
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);
/*
		// Setting Prayer Time
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("PrayerTime");
		sourceAssetAttributeData.setStringValue(funeralCommand.getPrayerTime());
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);
		
		// Setting Mother Name
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("MotherName");
		sourceAssetAttributeData.setStringValue(funeralCommand.getMotherName());
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);
		
		// Setting Father Name
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("FatherName");
		sourceAssetAttributeData.setStringValue(funeralCommand.getFatherName());
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);
		
		// Setting Place
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("Place");
		sourceAssetAttributeData.setStringValue(funeralCommand.getPlace());
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);
		
		// Setting Cemetary
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("Cemetary");
		sourceAssetAttributeData.setStringValue(funeralCommand.getCemetary());
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);
		*/
		// Setting Description
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("Description");
		sourceAssetAttributeData.setStringValue(funeralCommand.getDescription());
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);
		
		// Setting PushNotificationMessage
		sourceAssetAttribute = new Attribute();
		sourceAssetAttributeData = new Data();
		sourceAssetAttribute.setName("PushNotificationMessage");
		sourceAssetAttributeData.setStringValue(funeralCommand.getPushNotificationMessage());
		sourceAssetAttribute.setData(sourceAssetAttributeData);
		sourceAsset.getAttributes().add(sourceAssetAttribute);


		// Set Dimension
		DimensionValue dimension = new DimensionValue();
		dimension.setAssettype("Dimension");
		dimension.setGroup("Locale");
		dimension.setName(funeralCommand.getLanguageCode());
		if(funeralCommand.getLanguageCode().equals("ar")){
			dimension.setId(Long.valueOf("1437027843448"));
		} else {
			dimension.setId(Long.valueOf("1437027843430"));
		}

		sourceAsset.getDimensions().add(dimension);

		// Required: Must specify the site(s) for the asset
		sourceAsset.getPublists().add(PropertiesUtil.getProperty(funeralCommand.getSite() + "_csSiteName"));

		// Required: Must specify the sub type for the Flex asset type
		sourceAsset.setSubtype("Funeral");

		return sourceAsset;

	}

	@Override
	public Boolean executeSites(FuneralCommand inputObject) throws UAQException {
		// TODO Auto-generated method stub
		return null;
	}
}
