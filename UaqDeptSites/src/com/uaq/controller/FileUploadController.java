/**
 * 
 */
package com.uaq.controller;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.uaq.logger.UAQLogger;
import com.uaq.service.FileUploadService;
import com.uaq.vo.FileOutputVO;

/**
 * @author WINDOS
 *
 */
@Controller
public class FileUploadController extends BaseController {

	protected static UAQLogger logger = new UAQLogger(FileUploadController.class);

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping(value = "/uaq/testFile.html", method = RequestMethod.GET)
	public String loadTestPage(HttpServletRequest request, ModelMap model) {
		super.handleRequest(request, model);
		return "test.file";

	}

	@RequestMapping(value = "/uaq/uploadFile.html", method = RequestMethod.POST)
	@ResponseBody
	public String handleFIleRequest(MultipartHttpServletRequest request) throws IOException {
		String didName = "";
		Iterator<String> itrator = request.getFileNames();
		MultipartFile multiFile = request.getFile(itrator.next());
		logger.debug("File Name  = " + multiFile.getOriginalFilename());
		if (multiFile != null) {
			FileOutputVO fileOutput = fileUploadService.upLoadFile(multiFile);
			logger.debug("File Upload  = " + fileOutput.getStatus() + fileOutput.getDid());
			didName = fileOutput.getDid() + "-" + fileOutput.getFileName();
		}

		return didName;

	}

}
