package com.kong.dao;

import org.springframework.stereotype.Repository;

import com.kong.data.AppSysUserInfo;
import com.kong.data.User;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserInfoDao extends Mapper<AppSysUserInfo> {

}
