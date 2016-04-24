package com.pkm.service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pkm.Controller.IssueRequestMapper;
import com.pkm.command.RequestFormCommand;
import com.pkm.config.JDBCConnect;
import com.pkm.config.PKMLogger;
import com.pkm.dao.AddressDao;
import com.pkm.dao.IssueDetailDao;
import com.pkm.dao.UserDetailDao;
import com.pkm.vo.IssuDetailVO;
import com.pkm.vo.UserDetailVO;

@Service
public class IssueRequestService {

	@Autowired
	private IssueDetailDao issueDetailDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private UserDetailDao userDetailDao;

	protected PKMLogger logger = new PKMLogger(IssueRequestService.class);

	public long saveAndSendRequest(RequestFormCommand requestFormCommand) {
		JDBCConnect jDBCConnect = new JDBCConnect();

		long issueId = 0;

		try {

			String rootPath = System.getProperty("catalina.home");
			File dir = new File(rootPath + File.separator + "attachments");

			if (!dir.exists()) {
				dir.mkdir();
			}

			long addresId = addressDao.saveAddress(requestFormCommand.getAddress(), jDBCConnect.getConnection());

			UserDetailVO userDetailVO = IssueRequestMapper.mapDataToUsder(requestFormCommand, addresId + "");

			long userId = userDetailDao.saveUser(userDetailVO, jDBCConnect.getConnection());

			String imageId = userId + "-" + requestFormCommand.getDescriptionFile().getOriginalFilename();
			byte[] bytes = requestFormCommand.getDescriptionFile().getBytes();

			File serverFile = new File(dir.getAbsolutePath() + File.separator + imageId);

			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

			stream.write(bytes);
			stream.close();

			logger.info("Server File Location=" + serverFile.getAbsolutePath());

			IssuDetailVO issuDetailVO = IssueRequestMapper.mpToIssue(requestFormCommand, addresId + "", userId + "", imageId);

			issueId = issueDetailDao.saveIssue(issuDetailVO, jDBCConnect.getConnection());

		} catch (Exception e) {
			logger.error(e.getMessage());
			jDBCConnect.rollback();
		} finally {
			try {
				jDBCConnect.commit();
			} catch (SQLException e) {
				logger.error(e.getMessage());
			}
		}

		return issueId;

	}

	private byte[] getdownloadFile(String filename) throws IOException {
		String rootPath = System.getProperty("catalina.home");
		File dir = new File(rootPath + File.separator + "attachments");
		
		File file = new File(dir.getAbsolutePath() + File.separator +filename);
		
		FileInputStream fis=new FileInputStream(file);
		
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		int b;
		byte[] buffer = new byte[1024];
		
		while((b=fis.read(buffer))!=-1){
			   bos.write(buffer,0,b);
			}
			byte[] fileBytes=bos.toByteArray();
			fis.close();
			bos.close();
			return fileBytes;
			
	}

}
