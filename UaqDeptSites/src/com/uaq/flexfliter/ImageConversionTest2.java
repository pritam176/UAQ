package com.uaq.flexfliter;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

public class ImageConversionTest2 {

	public void convert() throws MagickException {
		System.out.println("Hello");
		System.out.println("java.library.path is: " + System.getProperty("java.library.path"));
		try {
			ImageInfo info = new ImageInfo("/opt/resizing/test/Chrysanthemum.png");
			MagickImage image = new MagickImage();
			MagickImage bigger = image.scaleImage(1200, 900);
			bigger.setFileName("/opt/resizing/test/test12.png");
			bigger.writeImage(info);
			System.out.println("java.library.path is: " + System.getProperty("java.library.path"));
		} catch (MagickException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ImageConversionTest2 test2 = new ImageConversionTest2();
		try {
			test2.convert();
		} catch (MagickException me) {
			me.printStackTrace();
		}

	}
}
