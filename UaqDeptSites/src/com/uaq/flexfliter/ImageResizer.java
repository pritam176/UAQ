package com.uaq.flexfliter;

import java.awt.Dimension;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.ResolutionType;

/**
 * This class is used to invoke the Image Magick APIs for image conversion.
 * 
 * @author nshar9
 * 
 */

public class ImageResizer {

	/**
	 * This method is used to read the Image from source location and creates a
	 * resized image at the destination location based on height and width
	 * 
	 * @param source
	 *            Source file path
	 * @param destination
	 *            Destination file path
	 * @param height
	 *            destination height
	 * @param width
	 *            destination width
	 */
	public void convert(String source, String destination, int height, int width) throws MagickException {
		System.out.println("Start Convert image ");
		System.out.println("Source file is  " + source);
		System.out.println("Destination file is  " + destination);
		ImageInfo sourceInfo = new ImageInfo(source);
		ImageInfo destinationInfo = new ImageInfo(destination);
		MagickImage sourceImage = new MagickImage(sourceInfo);
		Dimension sourceDimension = sourceImage.getDimension();

		int columns = height;
		int rows = width;

		if (sourceDimension.height > sourceDimension.width) {
			columns = sourceDimension.height * width / sourceDimension.width;
		} else {
			columns = width;
			rows = height;
		}

		MagickImage scaled = sourceImage.scaleImage(columns, rows);
		scaled = scaled.unsharpMaskImage(1.0, 5.0, 100, 100);
		scaled.setUnits(ResolutionType.PixelsPerCentimeterResolution);

		scaled.setFileName(destination);
		if (scaled.writeImage(destinationInfo)) {
			System.out.println("Image generated successfully");
		} else {
			System.out.println("Problem writing the image");
		}
		System.out.println("End Convert image ");

	}

}
