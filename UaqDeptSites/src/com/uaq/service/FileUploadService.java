package com.uaq.service;

import java.io.IOException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.rpc.ServiceException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import uaq.service.common.model.CommonServices;
import uaq.service.common.model.CommonServicesPortBindingStub;
import uaq.service.common.model.CommonServicesService;
import uaq.service.common.model.CommonServicesServiceLocator;
import uaq.service.common.model.UserContext;

import com.oracle.xmlns.UAQBusinessProcess.UAQ_DocumentUpload_Download_Ser.Upload_DownloadBpel.FileListPayload;
import com.oracle.xmlns.UAQBusinessProcess.UAQ_DocumentUpload_Download_Ser.Upload_DownloadBpel.FileRecordPayload;
import com.oracle.xmlns.UAQBusinessProcess.UAQ_DocumentUpload_Download_Ser.Upload_DownloadBpel.UploadInputPayload;
import com.oracle.xmlns.UAQBusinessProcess.UAQ_DocumentUpload_Download_Ser.Upload_DownloadBpel.UploadOutputPayload;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.FileOutputVO;

/**
 * Service class for Upload File In WebService WSDL
 * :-http://94.57.252.234:7001/UAQServiceMiddleLayer/GenericServicePort?WSDL
 * 
 * @author Pritam
 * 
 */

@Service(value = "fileUploadService")
public class FileUploadService {

	protected static UAQLogger logger = new UAQLogger(FileUploadService.class);
	private static final SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyhmmss");

	private CommonServicesService service;
	private CommonServices port;
	private CommonServicesPortBindingStub stub;
	private UserContext uc = new UserContext();

	public void createStub() {
		uc.setUsername("uaqdev");
		uc.setPassword("welcome1");
		try {
			service = (CommonServicesService) new CommonServicesServiceLocator();
			port = service.getCommonServicesPort();
			stub = (CommonServicesPortBindingStub) port;
		} catch (ServiceException e) {
			logger.error("Error Creating Stub " + e.getMessage());
			e.printStackTrace();
		}

	}

	public FileOutputVO upLoadFile(MultipartFile file) {

		createStub();
		
		FileOutputVO  fileOutput=new FileOutputVO();
		Date date = new Date();
		String formattedDate = sdf.format(date);
		
		FileListPayload flp = new FileListPayload();
		FileRecordPayload frpFileRecordPayload = new FileRecordPayload();
		try {
			
			//frpFileRecordPayload.setFileContent(Base64.encode(file.getBytes()));
			frpFileRecordPayload.setFileContent(file.getBytes());
		} catch (IOException e) {
			logger.error("Error getting   file " + e.getMessage());
			
		}
		String uplaodaedFilename=file.getOriginalFilename();
		//uplaodaedFilename=uplaodaedFilename.replaceAll("\\s","");
		String fileName=formattedDate +"."+ uplaodaedFilename.split("\\.")[1];
		logger.debug("Time stamp Filename   file- " + fileName+"Actual File Name-"+uplaodaedFilename);
		frpFileRecordPayload.setFilename(fileName);
		flp.setFileRecord(frpFileRecordPayload);

		UploadInputPayload uip = new UploadInputPayload();
		uip.setFileList(flp);
		uip.setAuthorName("uaqdev");
		uip.setDocName(fileName);
		uip.setDocSecurityGroup("Public");
		uip.setDocTitle(fileName);
		uip.setDocType("Document");

		UploadOutputPayload output = null;

		try {
			output = stub.uploadDOcument(uip, uc);
			
			
			if(output !=null){
				fileOutput.setStatus(output.getStatus());
				fileOutput.setFileName(fileName);
				fileOutput.setDid(output.getDid());
				logger.info(output.getMessage_En()+"   |  "+output.getDid());
			}else{
				logger.info(" Failure  |  stub.uploadDOcument(uip, uc); return null");
			}
			
		} catch (RemoteException e) {
			logger.error("Error EXcuting  Stub " + e.getMessage());
			
		}
		

		return fileOutput;
	}
}
