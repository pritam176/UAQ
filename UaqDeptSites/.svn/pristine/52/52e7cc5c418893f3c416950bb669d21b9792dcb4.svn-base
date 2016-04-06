package com.uaq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.uaq.logger.UAQLogger;
import com.uaq.vo.AttachmentVO;

@Repository
public class ServiceAttachmentDAO {

	private static transient UAQLogger logger = new UAQLogger(ServiceAttachmentDAO.class);

	public List<AttachmentVO> getAllattachmentByRequestId(String requestId) {

		logger.enter("getAllattachmentByRequestId");

		List<AttachmentVO> attachmentVOList = new ArrayList<AttachmentVO>();

		String sql = "SELECT ATTACH.ATT_DOC_ID, ATTACH.FILE_NAME, ATTACH.VIEWURL, ATTACH.downloadurl ,ATTACH.UCM_DID, " + "	ATTACH.ATTACHMENT_ID ,t2.ATT_DOCUMENT_NAME_AR,t2.ATT_DOCUMENT_NAME_EN "
				+ "	FROM SERVICE_ATTACHMENTS ATTACH ,  " + "	(SELECT REQUEST_ID, ATT_DOC_ID, MAX(CREATED_DATE) AS MAXDATETIME   FROM SERVICE_ATTACHMENTS "
				+ "	WHERE REQUEST_ID = ?      GROUP BY REQUEST_ID, ATT_DOC_ID) LATEST_ATTACH ," + "	ATTACHMENT_DOC_TYPE_LOOKUP t2" + "	where ATTACH.created_date = LATEST_ATTACH.MAXDATETIME "
				+ "	AND ATTACH.REQUEST_ID = LATEST_ATTACH.REQUEST_ID " + "	AND ATTACH.ATT_DOC_ID = LATEST_ATTACH.ATT_DOC_ID" + "	and LATEST_ATTACH.ATT_DOC_ID=t2.ATT_DOC_ID ";

		DAOManager daoManager = new DAOManager();
		try {
			Connection con = daoManager.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, requestId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				AttachmentVO attachment = new AttachmentVO();
				attachment.setAttachmentType(rs.getString("ATT_DOC_ID"));
				attachment.setAttachmentName(rs.getString("FILE_NAME"));
				attachment.setAttachmentType_EN(rs.getString("ATT_DOCUMENT_NAME_EN"));
				attachment.setAttachmentType_AR(rs.getString("ATT_DOCUMENT_NAME_AR"));
				attachment.setDownloadURL(rs.getString("downloadurl"));
				attachment.setViewURL(rs.getString("VIEWURL"));
				attachmentVOList.add(attachment);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		} finally {
			daoManager.closeConnection();

		}
		
		logger.exit("getAllattachmentByRequestId No of Record retrive = "+attachmentVOList.size());
		return attachmentVOList;

	}

}
