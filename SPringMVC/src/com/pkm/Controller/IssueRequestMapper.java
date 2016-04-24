package com.pkm.Controller;

import com.pkm.command.RequestFormCommand;
import com.pkm.vo.IssuDetailVO;
import com.pkm.vo.UserDetailVO;

public class IssueRequestMapper {
	
	public static UserDetailVO mapDataToUsder(RequestFormCommand requestFormCommand,String addresId){
		UserDetailVO userDetailVO =new UserDetailVO();
		userDetailVO.setEMAIL(requestFormCommand.getEmail());
		userDetailVO.setMOBILENO(requestFormCommand.getMobileno());
		userDetailVO.setNAME(requestFormCommand.getName());
		userDetailVO.setADDRESS_ID(addresId);
		
		return userDetailVO;
	}

	
	public static IssuDetailVO mpToIssue(RequestFormCommand requestFormCommand,String addresId,String userId,String imageId){
		IssuDetailVO issuDetailVO =new IssuDetailVO();
		issuDetailVO.setADRESS_ID(addresId);
		issuDetailVO.setDESCRIPTION(issuDetailVO.getDESCRIPTION());
		issuDetailVO.setPROUDUCTTYPE(requestFormCommand.getProuducttype());
		issuDetailVO.setPROUDUCTSUBTYPE(requestFormCommand.getProuductSubtype());
		issuDetailVO.setMODELNO(requestFormCommand.getModelno());
		issuDetailVO.setUSER_ID(userId);
		issuDetailVO.setIMAGE_ID(imageId);
		return issuDetailVO;
	}
}
