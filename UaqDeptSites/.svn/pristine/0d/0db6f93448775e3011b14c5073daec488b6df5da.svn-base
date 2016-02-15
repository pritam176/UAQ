package com.uaq.flexfliter;

import static com.uaq.flexfliter.FlexConstants.DIMENSION;
import static com.uaq.flexfliter.FlexConstants.EVENTS_TYPE;
import static com.uaq.flexfliter.FlexConstants.FUNERAL_TYPE;
import static com.uaq.flexfliter.FlexConstants.NEWS_TYPE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import COM.FutureTense.CS.Factory;

import com.fatwire.assetapi.data.AssetData;
import com.fatwire.assetapi.data.AssetDataManager;
import com.fatwire.assetapi.data.AssetId;
import com.fatwire.system.SessionFactory;
import com.openmarket.basic.event.AbstractAssetEventListener;
import com.openmarket.xcelerate.asset.DimensionAssetInstance;
import com.uaq.flexfliter.FlexConstants.ACTION;

/**
 * This class is a custom defined listener whose method gets invoked by Content
 * Server framework on asset modifications. Modified assets then get indexed
 * based on the index configurations.
 * 
 * @author nsharma
 */
public class CustomAssetEventListener extends AbstractAssetEventListener {

	private static AssetDataManager assetManager;

	/**
	 * This static block initializes the AssetDataManager class for loading the
	 * asset informations.
	 */
	static {
		COM.FutureTense.Interfaces.ICS ics = null;
		try {
			ics = Factory.newCS();
			assetManager = (AssetDataManager) SessionFactory.getSession(ics).getManager(AssetDataManager.class.getName());

		} catch (Exception exception) {
		}
	}

	/**
	 * This method gets invoked by the listener on asset creation.
	 */
	@Override
	public void assetAdded(AssetId id) {
		execute(id, ACTION.ADD);
	}

	/**
	 * This method gets invoked by the listener on asset deletion.
	 */
	@Override
	public void assetDeleted(AssetId id) {
		//execute(id, ACTION.DELETE);
	}

	/**
	 * This method gets invoked by the listener class on asset modification.
	 */
	@Override
	public void assetUpdated(AssetId id) {
		execute(id, ACTION.UPDATE);
	}

	/**
	 * This method handles the re-initailaizing the DAO cache objects and the
	 * asset indexing operation.
	 * 
	 * @param id
	 *            Asset id
	 * @param action
	 *            Refers to the asset state. Refer to {@link ACTION}
	 */
	public void execute(AssetId id, ACTION action) {

		String assetType = id.getType();
		if (assetType.equals("Content_C")) {
			try {
				List<AssetId> assetList = new ArrayList<AssetId>();
				assetList.add(id);
				// Populate asset information only on add and update
				// event.
				if (!action.equals(ACTION.DELETE)) {
					System.out.println("Inside Asset Save Method" + id);
					Iterator<AssetData> iterator = assetManager.read(assetList).iterator();
					if (iterator.hasNext()) {
						AssetData assetdata = iterator.next();
						sendNotification(assetdata);
					}

				}

			} catch (Exception e) {
				System.out.println("Exception occured" + e.getMessage());
			}
		}
	}

	private void sendNotification(AssetData assetdata) {
		String subTypeId = null;
		String site = new String();
		site = (String) assetdata.getAttributeData("Publist").getDataAsList().get(0);
		String subType = assetdata.getAssetTypeDef().getSubtype();
		System.out.println("Site Name" + site + "Asset Definition Type" + subType);
		if (site.equals("UAQInternetPortal")) {
			if (subType.equals("News") || subType.equals("Events") || subType.equals("Funeral")) {
				if (subType.equals("News")) {
					subTypeId = NEWS_TYPE;
				} else if (subType.equals("Events")) {
					subTypeId = EVENTS_TYPE;

				} else if (subType.equals("Funeral")) {
					subTypeId = FUNERAL_TYPE;

				}

				if (null != subTypeId && null != assetdata.getAttributeData("PushNotificationMessage") && null != assetdata.getAttributeData("PushNotificationMessage").getDataAsList()
						&& assetdata.getAttributeData("PushNotificationMessage").getDataAsList().size() > 0) {
					String pushNotificationMessage = (String) assetdata.getAttributeData("PushNotificationMessage").getDataAsList().get(0);
					String assetId = String.valueOf(assetdata.getAssetId().getId());
					String lang = getDimension(assetdata);
					String date = "31/08/2015";
					SendPushNotification.postPushNotification(subTypeId, assetId, pushNotificationMessage, lang, date);

				}

			}

		}

	}

	/**
	 * Reads the language of the Asset from the data.
	 * 
	 * @param assetdata
	 *            asset data object for the Asset
	 * @return the language of the Asset
	 */
	@SuppressWarnings("unchecked")
	private String getDimension(AssetData assetdata) {
		String languageName = "";
		if (assetdata.getAttributeData(DIMENSION) != null) {
			HashSet<DimensionAssetInstance> dim = (HashSet<DimensionAssetInstance>) assetdata.getAttributeData(DIMENSION).getData();
			Iterator<DimensionAssetInstance> dimIterator = dim.iterator();
			while (dimIterator.hasNext()) {
				languageName = dimIterator.next().getName();
			}

		}
		return languageName;
	}

}