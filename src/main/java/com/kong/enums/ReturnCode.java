package com.kong.enums;

/**
 *
 * 响应码规范:
 * 00000 成功
 * 第一位,第二位:
 * 00 特殊码  不记录异常，只是提示
 * 01 数据库错误
 * 02 网络错误
 * 03 操作系统错误
 * 04 应用系统错误
 * 05 加解密异常
 * 06 业务逻辑错误
 * 10 数据检查错误
 * 20 配置信息错误
 * 30 工作流错误
 * 40 银行返回错误映射
 * 50 文件读取错误
 * <p>
 * 第三位,系统标识:
 */
public enum ReturnCode {
	success("00000", "成功"),
	fail("99998", "失败"),
	process("00002","处理中"),

	/*01 数据库错误*/
	bvspcs_insert_fail             ("BVSPCS0170001", "mariadb数据库新增失败"),
	bvspcs_update_fail             ("BVSPCS0170002", "mariadb数据库修改失败"),
	bvspcs_delete_fail             ("BVSPCS0170003", "mariadb数据库删除失败"),
	bvspcs_select_fail             ("BVSPCS0170004", "mariadb数据库查询失败"),

	/*10 数据检查错误*/
	bvspcs_request_is_null           ("BVSPCS1070001", "请求对象不能为空"),
	bvspcs_request_param_is_null     ("BVSPCS1070002", "请求参数不能为空"),
	bvspcs_request_param_not_valid   ("BVSPCS1070003", "请求参数未通过校验"),
	bvspcs_request_repeat            ("BVSPCS1070004", "请求重复"),
	bvspcs_response_is_null          ("BVSPCS1070005", "返回结果为空"),
	request_param_is_null   ("NEIGHBOR1070002", "请求参数不能为空"),
	request_param_not_valid ("NEIGHBOR1070003", "请求参数未通过校验"),

	/*04 应用系统错误*/
	bvspcs_sys_error               ("BVSPCS0470001", "系统异常"),
	bvspcs_extsys_error            ("BVSPCS0470002", "外部系统返回错误"),

	/*05 加解密错误*/

	/* 06 业务逻辑错误*/
	bvspcs_not_recorded            ("BVSPCS0670001", "无此记录"),
	bvspcs_inaccuracy_status       ("BVSPCS0670002", "操作状态不正确");






	private String code;
	private String desc;

	ReturnCode(String code, String message) {
		this.code = code;
		this.desc = message;
	}

	public String toCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
