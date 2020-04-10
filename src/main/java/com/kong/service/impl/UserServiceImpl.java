package com.kong.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kong.dao.UserInfoDao;
import com.kong.data.AppSysUserInfo;
import com.kong.data.User;
import com.kong.service.UserService;

import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
	protected Log logger = LogFactory.getLog(this.getClass());
	@Autowired
	UserInfoDao userInfoDao;

	public String addUser(User user) {
		// 直接编写业务逻辑
		AppSysUserInfo appSysUserInfo = userInfoDao.selectByPrimaryKey(user.getId());
		logger.info("appSysUserInfo=" + appSysUserInfo);
		return "success";

	}
}
