package com.uaq.ws.webservice;

import java.io.File;
import java.util.List;

import com.asprise.imaging.scan.Device;
import com.asprise.imaging.scan.FunctionalUnit;
import com.asprise.imaging.scan.ScanManager;
import com.asprise.imaging.scan.ScanProgressListener;
import com.uaq.ws.pojo.Response;
import com.uaq.ws.util.UAQLogger;
import com.uaq.ws.webservice.interfac.IScannerService;

public class ScannerService implements IScannerService{
	
	private static transient UAQLogger logger = new UAQLogger(ScannerService.class);

	@Override
	public Response scan() {
		
		logger.enter("scan");
			
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
		
		logger.exit("scan");
		
		return null;
	}
	
	
	
}
