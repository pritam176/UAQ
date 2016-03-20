package com.uaq.service;

import org.springframework.stereotype.Service;

import com.uaq.dao.UserDetailDAO;
import com.uaq.logger.UAQLogger;
import com.uaq.vo.AccountDetailTokenOutputVO;

@Service
public class UserDetailService {
	
	private static transient UAQLogger logger = new UAQLogger(UserDetailService.class);
	
	UserDetailDAO userDetail = new UserDetailDAO();
	
	public AccountDetailTokenOutputVO getUserdetail(String accountId){
		logger.debug("Account Id-"+accountId);
		return userDetail.getUserdetail(accountId);
	}
	
	public AccountDetailTokenOutputVO getUserdetailByUsername(String username) {
		logger.debug("Username -"+username);
		return userDetail.getUserdetailByUsername(username);
	}

}
