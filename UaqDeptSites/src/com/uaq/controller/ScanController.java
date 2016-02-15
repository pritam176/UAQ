package com.uaq.controller;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.asprise.imaging.scan.Device;
import com.asprise.imaging.scan.FunctionalUnit;
import com.asprise.imaging.scan.PaperSize;
import com.asprise.imaging.scan.ScanManager;
import com.asprise.imaging.scan.ScanProgressListener;
import com.asprise.imaging.scan.Utils;

@Controller
public class ScanController {

	
	@RequestMapping(value = "/uaq/scan.html", method = RequestMethod.GET)
	public void scan(){
	
	List<Device> devices = ScanManager.getDefaultManager().getDevices(); // list all devices
	FunctionalUnit flatbed = devices.get(0).getFlatbed(); // each device may have multiple functional units

	flatbed.setPixelDataType(FunctionalUnit.PixelDataType.COLOR); // set capabilities
	flatbed.setBitDepth(FunctionalUnit.BitDepth.BIT_DEPTH_24_BITS);
	flatbed.setResolution(10);
	
	
	flatbed.scanOnePage(new File("E:\\test1.jpg"), new ScanProgressListener() {
		  @Override
		  public void started() {
		    // progressBarScanPureJava.setValue(0);
		  }

		  @Override
		  public void progress(final int percent) {
		    // progressBarScanPureJava.setValue(percent);
		  }

		  @Override
		  public void finished(String error) {
		    // progressBarScanPureJava.setValue(100);
		  }
		}, null);
	}
}
