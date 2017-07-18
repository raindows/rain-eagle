package org.rain.eagle.core.http;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class SauronAccessTokenResp implements Serializable {

	private static final long serialVersionUID = 808065374452460018L;

	private int code;

	@JSONField(name = "code_description")
	private String codeDescription;

	private String message;

	@JSONField(name = "data")
	private AccessTokenData accessTokenData;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AccessTokenData getAccessTokenData() {
		return accessTokenData;
	}

	public void setAccessTokenData(AccessTokenData accessTokenData) {
		this.accessTokenData = accessTokenData;
	}

	@Override
	public String toString() {
		return "SauronAccessTokenResp [code=" + code + ", codeDescription=" + codeDescription + ", message=" + message
				+ ", accessTokenData=" + accessTokenData + "]";
	}

}
class AccessTokenData implements Serializable {

	private static final long serialVersionUID = -1145761174720463514L;

	@JSONField(name = "access_token")
	private String accessToken;

	@JSONField(name = "company_account")
	private String companyAccount;

	@JSONField(name = "expire_in")
	private long expireIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getCompanyAccount() {
		return companyAccount;
	}

	public void setCompanyAccount(String companyAccount) {
		this.companyAccount = companyAccount;
	}

	public long getExpireIn() {
		return expireIn;
	}

	public void setExpireIn(long expireIn) {
		this.expireIn = expireIn;
	}

	@Override
	public String toString() {
		return "AccessTokenData [accessToken=" + accessToken + ", companyAccount=" + companyAccount + ", expireIn="
				+ expireIn + "]";
	}

}
