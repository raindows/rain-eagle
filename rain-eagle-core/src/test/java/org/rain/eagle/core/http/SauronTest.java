package org.rain.eagle.core.http;

import org.apache.commons.codec.digest.DigestUtils;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SauronTest {

	private static final String ACCESS_TOKEN_URL = "https://data.hulushuju.com/api/companies/${companyAccount}/access_token";

	private static final String SAURON_URL = "https://ad.hulushuju.com/api/sauron";

	private static final String SAURON_URL_MD5 = "https://ad.hulushuju.com/api/sauron/md5";
	
	private static final String COMPANY_ACCOUNT = "mljr_SAURON";

	public static void main(String[] args) throws UnirestException {
		testAccessToken();
		String accessToken = "7a1114960a24403a916c7877bee16e43";
		//testSauron(accessToken);
		//testSauronMd5(accessToken);
	}

	private static void testSauronMd5(String accessToken) throws UnirestException {
		//?name={name}&md5Phone={md5Phone}&md5IdCard={md5IdCard}&companyAccount={companyAccount}&accessToken={accessToken}
		String md5Phone = DigestUtils.md5Hex("13454875773".getBytes());
		String md5IdCard = DigestUtils.md5Hex("140602198609141034".getBytes());
		
		HttpResponse<String> response = Unirest.get(SAURON_URL_MD5)
				.queryString("name", "张三")
				.queryString("md5Phone", md5Phone)
				.queryString("md5IdCard", md5IdCard)
				.queryString("companyAccount", COMPANY_ACCOUNT)
				.queryString("accessToken", accessToken).asString();
		String jsonResp = response.getBody().toString();
		System.out.println(jsonResp);
		
		SauronAccessTokenResp sauronAccessTokenResp = null;
		sauronAccessTokenResp = com.alibaba.fastjson.JSONObject.parseObject(jsonResp, SauronAccessTokenResp.class);
		
		System.out.println(sauronAccessTokenResp);
		System.out.println(JSON.toJSONString(sauronAccessTokenResp));
	
	}

	private static void testSauron(String accessToken) throws UnirestException {
		// URL:https://sauron.hulushuju.com/sauron?name={name}&phone={phone}&idCard={idCard}&companyAccount={companyAccount}&accessToken={accessToken}

		HttpResponse<String> response = Unirest.get(SAURON_URL)
				.queryString("name", "张三")
				.queryString("phone", "13454875773")
				.queryString("idCard", "140602198609141034")
				.queryString("companyAccount", COMPANY_ACCOUNT)
				.queryString("accessToken", accessToken).asString();
		String jsonResp = response.getBody().toString();
		System.out.println(jsonResp);
	}

	private static void testAccessToken() throws UnirestException {
		final String accessTokenUrl = ACCESS_TOKEN_URL.replace("${companyAccount}", COMPANY_ACCOUNT);
		final String signature = "ccc8012de5ec4932a196dbc705bcc0fb"; 
		HttpResponse<String> response = Unirest.get(accessTokenUrl)
				.queryString("signature", signature).asString();
		System.out.println(JSON.toJSONString(response));
		String jsonResp = response.getBody().toString();
		
		SauronAccessTokenResp sauronAccessTokenResp = com.alibaba.fastjson.JSONObject.parseObject(jsonResp, SauronAccessTokenResp.class);
		
		System.out.println(sauronAccessTokenResp);
		System.out.println(JSON.toJSONString(sauronAccessTokenResp));
		System.out.println(JSON.toJSONString(jsonResp));
	}

}
