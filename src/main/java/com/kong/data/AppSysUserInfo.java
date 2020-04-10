package com.kong.data;


import java.io.Serializable;


import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "sys_user")
public class AppSysUserInfo implements Serializable {
	@Id
	private String id;//主键

    private String userId;
	private String name;
	private String password;
	private String mobile;
	private String registerTime;
	private String certNo;
	private String gesture;
    private String status;
    private String realNameMobile;//实名认证手机号
	private String userType;//用户类型,
	private String source;
	private String weixinId;
    private String clientId;
	private String areaCode;

}