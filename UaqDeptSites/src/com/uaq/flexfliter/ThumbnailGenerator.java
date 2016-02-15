package com.uaq.flexfliter;

import static com.uaq.flexfliter.FlexConstants.FILTER_INPUT_ATTRIBUTE;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_1;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_2;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_3;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_4;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_5;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_SIZE_1;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_SIZE_2;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_SIZE_3;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_SIZE_4;
import static com.uaq.flexfliter.FlexConstants.FILTER_OUTPUT_ATTRIBUTE_SIZE_5;
import static com.uaq.flexfliter.FlexConstants.IMAGE_SPLITTER;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import magick.MagickException;
import COM.FutureTense.Interfaces.FTVAL;
import COM.FutureTense.Interfaces.FTValList;
import COM.FutureTense.Interfaces.Utilities;

import com.openmarket.basic.interfaces.AssetException;
import com.openmarket.basic.interfaces.IListBasic;
import com.openmarket.gator.interfaces.IFilter;
import com.openmarket.gator.interfaces.IFilterDependencies;
import com.openmarket.gator.interfaces.IFilterDescription;
import com.openmarket.gator.interfaces.IFilterEnvironment;
import com.openmarket.gator.interfaces.IFilterableAssetInstance;
import com.openmarket.xcelerate.interfaces.IReplicate;

// Referenced classes of package com.fatwire.firstsite.filter:
//            FileType

/**
 * This class is called for ThumbnailGenerator filter which resize the uploaded
 * image in five different sizes
 * 
 * @author nsharma
 * 
 */

public class ThumbnailGenerator implements IFilter {

	
	private static Map<String, String> ATTRIBUTE_NAME_VALUES;

	/**
	 * ThumbnailGenerator Constructor
	 * 
	 */
	public ThumbnailGenerator(FTValList configurationParameters) {
		String inputAttribute = configurationParameters.getValString(FILTER_INPUT_ATTRIBUTE);
		System.out.println((new StringBuilder()).append("Instantiating ThumbnailGenerator Filter running on ").append(new Date())
				.append(inputAttribute != null ? (new StringBuilder()).append(" for attribute named ").append(inputAttribute).toString() : "").toString());
	}

	/**
	 * @Overide "getFilterEditURL" method
	 * 
	 * @return String
	 */

	public String getFilterEditURL(IFilterEnvironment ifilterenvironment, String s, String s1, FTValList ftvallist) throws AssetException {
		return null;
	}

	/**
	 * @Overide "filterAsset" method
	 * 
	 *          This method is called for the storing the resized images in the
	 *          output attributes
	 * 
	 * @param IFilterEnvironment
	 *            , filterIdentifier, args(List of attributes),
	 *            ifilterableassetinstance
	 * @return void
	 */
	public void filterAsset(IFilterEnvironment ifilterenvironment, String filterIdentifier, FTValList args, IFilterableAssetInstance ifilterableassetinstance) throws AssetException {

		ArrayList<String> filesToBeDeleted = new ArrayList<String>();

		System.out.println("Filtering asset using ThumbnailGenerator filter");
		String inputAttributeId = getAttributeID(ifilterenvironment, args, FILTER_INPUT_ATTRIBUTE);
		IListBasic ilistbasic = ifilterableassetinstance.getAttribute(inputAttributeId);

		try {
			String urlval = ilistbasic.getValue("urlvalue");

			System.out.println("URL is " + urlval);
			FileType filetype = new FileType(urlval);
			FTVAL ftval = ilistbasic.getFileContents("urlvalue");

			String ext = filetype.getFileext();
			String file = fileFromFTVAL(ftval, ext);

			filesToBeDeleted.add(file);
			Iterator<Map.Entry<String, String>> attributeIterator = ATTRIBUTE_NAME_VALUES.entrySet().iterator();
			while (attributeIterator.hasNext()) {
				Map.Entry<String, String> attributeValue = (Map.Entry<String, String>) attributeIterator.next();
				if (!(FILTER_INPUT_ATTRIBUTE).equals(attributeValue.getKey())) {
					String outputAttributeID = getAttributeID(ifilterenvironment, args, attributeValue.getKey());
					filesToBeDeleted.add(createThumbnail(ifilterenvironment, file, ext, outputAttributeID, ifilterableassetinstance, filterIdentifier, attributeValue.getValue()));
				}
			}
		} catch (NoSuchFieldException exception) {
			throw new AssetException((new StringBuilder()).append("Unexpected exception querying list in ThumbnailGenerator.filterAsset(): ").append(exception.toString()).toString());
		}

		finally {
			System.out.println("Deleting Files");
			deleteAllTempFiles(filesToBeDeleted);

		}
	}

	/**
	 * @Overide "describeDerivedAttributes" method
	 * 
	 * @param ifilterenvironment
	 *            , filterIdentifier, ftvallist, defTypeName, groupDefTypeName,
	 *            ifilterdescription
	 * 
	 */
	public void describeDerivedAttributes(IFilterEnvironment ifilterenvironment, String filterIdentifier, FTValList ftvallist, String defTypeName, String groupDefTypeName,
			IFilterDescription ifilterdescription) throws AssetException {
		Iterator<Map.Entry<String, String>> iterator = ATTRIBUTE_NAME_VALUES.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> attributeValue = (Map.Entry<String, String>) iterator.next();
			if (!(FILTER_INPUT_ATTRIBUTE).equals(attributeValue.getKey())) {
				addAttributeDescription(ifilterenvironment, filterIdentifier, ftvallist, ifilterdescription, attributeValue.getKey());
			}
		}
	}

	public boolean addAttributeDescription(IFilterEnvironment ifilterenvironment, String filterIdentifier, FTValList ftvallist, IFilterDescription ifilterdescription, String attributeName)
			throws AssetException {
		String outputAttr1 = getAttributeID(ifilterenvironment, ftvallist, attributeName);
		if (outputAttr1 != null) {
			ifilterdescription.addAttribute(filterIdentifier, outputAttr1, false, true);
		}
		return true;
	}

	/**
	 * Getter method for "output attribute identifier"
	 * 
	 * @param IFilterEnvironment
	 *            , ftvallist
	 * 
	 */
	private String getAttributeID(IFilterEnvironment ifilterenvironment, FTValList ftvallist, String attributeName) throws AssetException {
		String attributeIdentifier = ftvallist.getValString(attributeName);
		if (attributeIdentifier == null) {
			throw new AssetException("Missing argument: ThumbnailGenerator filter argument '" + attributeName + "' is null.");
		} else {
			return ifilterenvironment.getAttributeIdentifier(attributeIdentifier);
		}
	}

	/**
	 * @Overide "getDependencies" method to record dependencies for this filter
	 *          attributes
	 * 
	 * @param ifilterenvironment
	 *            , filterIdentifier, ftvallist, defTypeName, groupDefTypeName,
	 *            ifilterdescription
	 * 
	 */

	public void getDependencies(IFilterEnvironment ifilterenvironment, String s, FTValList ftvallist, String s1, String s2, IFilterDependencies ifilterdependencies) throws AssetException {

		Iterator<Map.Entry<String, String>> iterator = ATTRIBUTE_NAME_VALUES.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> attributeValue = (Map.Entry<String, String>) iterator.next();

			String attrID = getAttributeID(ifilterenvironment, ftvallist, attributeValue.getKey());

			if (attrID != null) {
				ifilterdependencies.addExistsToDeps(ifilterenvironment.getAttributeType(), attrID);
			}

		}
	}

	/**
	 * @Overide "getLegalArguments" method to set the attribute description
	 * 
	 * @param ifilterenvironment
	 *            ,
	 * 
	 */
	public FTValList getLegalArguments(IFilterEnvironment ifilterenvironment, String s) throws AssetException {
		FTValList ftvallist = new FTValList();

		Iterator<Map.Entry<String, String>> iterator = ATTRIBUTE_NAME_VALUES.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> attributeValue = (Map.Entry<String, String>) iterator.next();

			ftvallist.setValString(attributeValue.getKey(), attributeValue.getKey());

		}
		return ftvallist;
	}

	/**
	 * @Overide "getArgumentLegalValues" method
	 * 
	 * @param ifilterenvironment
	 *            ,
	 * 
	 */
	public String[] getArgumentLegalValues(IFilterEnvironment ifilterenvironment, String s, String s1) throws AssetException {
		return null;
	}

	/**
	 * @Overide "replicateArguments" method
	 * 
	 * @param IFilterEnvironment
	 *            , filterIdentifier, filterArguments, replicateObject
	 * 
	 */
	@SuppressWarnings("unchecked")
	public FTValList replicateArguments(IFilterEnvironment env, String filterIdentifier, FTValList filterArguments, IReplicate replicateObject) throws AssetException {
		String srcAssetType = env.getAttributeType();
		FTValList result = new FTValList();
		String basicAssetFieldName;
		String flexAssetAttrName;

		for (Enumeration<String> en = filterArguments.keys(); en.hasMoreElements(); result.setValString(basicAssetFieldName, flexAssetAttrName)) {

			basicAssetFieldName = (String) en.nextElement();
			flexAssetAttrName = filterArguments.getValString(basicAssetFieldName);
			if (flexAssetAttrName != null) {
				String newFlexAssetAttrName = replicateObject.getNewAssetNameForNewAssetType(flexAssetAttrName, srcAssetType);

				StringBuilder msg = new StringBuilder("  Handling basic asset field named ");
				msg.append(basicAssetFieldName).append(".  Source flex asset attribute name is ");
				msg.append(flexAssetAttrName).append(".  ");
				msg.append("Filtered asset will use the name ").append(newFlexAssetAttrName).append(".");
				System.out.println(msg.toString());

				if (newFlexAssetAttrName != null) {
					flexAssetAttrName = newFlexAssetAttrName;
				}

			}

		}

		return result;
	}

	/**
	 * @Overide "checkArguments" method
	 * 
	 * @param IFilterEnvironment
	 *            , filterIdentifier, filterArguments, replicateObject
	 * 
	 */
	public void checkArguments(IFilterEnvironment env, String filterIdentifier, FTValList filterArguments, IReplicate replicateObject) throws AssetException {
		String attrtype = env.getAttributeType();

		Iterator<Map.Entry<String, String>> iterator = ATTRIBUTE_NAME_VALUES.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> attributeValue = (Map.Entry<String, String>) iterator.next();

			String oldValue = filterArguments.getValString((String) attributeValue.getKey());
			if (oldValue != null) {
				replicateObject.checkNewAssetNameInSharedAsset(env.getICS(), oldValue, attrtype);
			}
		}
	}

	/**
	 * This method creates a file for reference containing the uploaded blob
	 * data
	 * 
	 * @param FTVAL
	 *            variable containing the blob data
	 * @param String
	 *            extension of the uploaded blob data
	 * @return String path of the temporary source file for resizing
	 */
	private static String fileFromFTVAL(FTVAL ftval, String ext) throws AssetException {
		FileOutputStream fileoutputstream;
		fileoutputstream = null;
		String path;
		try {
			InputStream inputstream = ftval.getStream();
			File file = new File(FlexConstants.TOMCAT_TEMP_PATH + String.valueOf(Calendar.getInstance().getTimeInMillis()) + "." + ext);
			fileoutputstream = new FileOutputStream(file);
			int j;
			while ((j = inputstream.read()) != -1) {
				fileoutputstream.write(j);
			}
			inputstream.close();
			path = file.getAbsolutePath();
		} catch (Exception exception) {
			throw new AssetException((new StringBuilder()).append("Exception getting file from FTVAL in ImageFilter filter: ").append(exception).toString());
		} finally {
			if (fileoutputstream != null) {
				try {
					fileoutputstream.close();
				} catch (IOException phooey) {
					System.out.println("Failure closing fileoutputstream in Imaging filter" + phooey.getMessage());
				}
			}
		}
		return path;
	}

	/**
	 * This method creates a FTVAL data containing the blob data from the file
	 * at the specified path
	 * 
	 * @param path
	 *            - path of the blob data
	 * @return FTVAL containing the resized blob data
	 */
	private static FTVAL getFTVALBlob(String path) {
		FTValList tmp = new FTValList(1);

		byte[] imageInBytes = null;
		String sourceFilename = path;
		System.out.println("Filename in creating blob is  " + sourceFilename);
		try {
			File file = new File(sourceFilename);
			imageInBytes = new byte[(int) file.length()];
			FileInputStream fis = new FileInputStream(file);
			fis.read(imageInBytes);
		} catch (FileNotFoundException io) {
			System.out.println("Exception " + io.getMessage());
		} catch (IOException io) {
			System.out.println("Exception " + io.getMessage());
		}

		tmp.setValBLOB("x", imageInBytes);
		return tmp.getVal("x");
	}

	/**
	 * This method creates a resized file from the original file based on the
	 * specified size
	 * 
	 * @param IFilterEnvironment
	 *            ,
	 * @param file
	 *            - path of the original file
	 * @param ext
	 *            - ext of the original file
	 * @param attributeName
	 *            - name of the attribute in which to store the resized image
	 * @param iFilterableAssetInstance
	 * @param filterIdentifier
	 * @param size
	 *            containing the dimension for the new image
	 * @return String containing the resized blob data
	 */
	public String createThumbnail(IFilterEnvironment ifilterenvironment, String file, String ext, String attributeName, IFilterableAssetInstance ifilterableassetinstance, String filterIdentifier,
			String size) {

		String[] dimension = size.split(IMAGE_SPLITTER);

		int targetWidth = Integer.parseInt(dimension[0].trim());
		int targetHeight = Integer.parseInt(dimension[1].trim());

		System.out.println("targetHeight is " + targetHeight);
		System.out.println("targetWidth is " + targetWidth);

		String fileExcludingExt = (new File(file)).getParent();
		System.out.println((new StringBuilder()).append("path is: ").append(fileExcludingExt).toString());
		String id = ifilterenvironment.getICS().genID(true);

		if (ext != null) {
			id = (new StringBuilder()).append(id).append(".").append(ext).toString();
		}
		System.out.println((new StringBuilder()).append("the generated filename is: ").append(id).toString());
		String fileAndExt = Utilities.fileName(fileExcludingExt, id);

		System.out.println("fileAndExt is " + fileAndExt);

		ImageResizer imageTest = new ImageResizer();
		try {
			imageTest.convert(file, fileAndExt, targetHeight, targetWidth);
		} catch (MagickException me) {
			System.out.println("Unexpected error occured in Image conversion");
		}
		FTVAL ftval1 = getFTVALBlob(fileAndExt);
		ifilterableassetinstance.addDerivedDataValue(filterIdentifier, attributeName, id, ftval1);

		return fileAndExt;
	}

	/**
	 * This method deletes all the temporary files created as part of the filter
	 * operation
	 * 
	 * @param ArrayList
	 *            <String> array list containing path of the temporary files
	 * 
	 */

	public void deleteAllTempFiles(ArrayList<String> fileList) {

		Iterator<String> iterator = fileList.iterator();

		while (iterator.hasNext()) {
			File file = new File(iterator.next());
			file.delete();
		}
	}

	/**
	 * This static block initializes the ATTRIBUTE_NAME_ARGUMENTS instance
	 * variable of the class.
	 */
	static {

		ATTRIBUTE_NAME_VALUES = new HashMap<String, String>();
		ATTRIBUTE_NAME_VALUES.put(FILTER_INPUT_ATTRIBUTE, FILTER_INPUT_ATTRIBUTE);
		ATTRIBUTE_NAME_VALUES.put(FILTER_OUTPUT_ATTRIBUTE_1, FILTER_OUTPUT_ATTRIBUTE_SIZE_1);
		ATTRIBUTE_NAME_VALUES.put(FILTER_OUTPUT_ATTRIBUTE_2, FILTER_OUTPUT_ATTRIBUTE_SIZE_2);
		ATTRIBUTE_NAME_VALUES.put(FILTER_OUTPUT_ATTRIBUTE_3, FILTER_OUTPUT_ATTRIBUTE_SIZE_3);
		ATTRIBUTE_NAME_VALUES.put(FILTER_OUTPUT_ATTRIBUTE_4, FILTER_OUTPUT_ATTRIBUTE_SIZE_4);
		ATTRIBUTE_NAME_VALUES.put(FILTER_OUTPUT_ATTRIBUTE_5, FILTER_OUTPUT_ATTRIBUTE_SIZE_5);
	}

}
