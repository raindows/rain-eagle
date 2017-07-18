package org.rain.eagle.core.http;

public enum SauronRespCodeEnum {

	COMPANY_ACCOUNT_NOT_EXISTED("4352", "公司帐号不存在,请检查companyAccount的值是否正确"),

	COMPANY_ACCOUNT_LOCKED("4353", "公司帐号已锁定,公司帐号可能由于破坏性调用接口或欠费被锁定,请联系销售"),

	COMPANY_ACCOUNT_NOT_SUPPORT("4354", "账号不支持认证此产品!请使用sauron后缀的账号认证"),
	
	COMPANY_SIGNATURE_INVALID("4355", "公司签名错误,申请公司帐号时提供的密钥,获取原始数据和报告数据令牌时必填参数,如有泄漏或遗失请联系更换"),

	COMPANY_ACCESS_TOKEN_EXCEPTION("4357", "获取机构访问数据令牌异常"),
	
	DATA_ACCESS_TOKEN_IS_ILLEGAL("16896","获取数据的令牌不存在或已失效"),
	
	DATA_SAURON_ACCESS_EXCEPTION("17152","获取数据异常"),
	
	DATA_SAURON_ACCESS_SUCCESS("17153","获取数据成功	"),
	
	DATA_SAURON_IDCARD_INVALID("17154","身份证号码错误"),
	
	DATA_SAURON_NAME_INVALID("17155","姓名错误"),
	
	DATA_SAURON_PHONE_INVALID("17156","手机号码错误");

	private String code;
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private SauronRespCodeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
