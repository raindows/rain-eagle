package org.rain.eagle.core.ws;

import java.util.Arrays;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

import cn.dalabs.r.ParamInfo;

public class SoapTest {

	public static void main(String[] args) {
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		Client client = factory.createClient("http://r.dalabs.cn/ReportService.asmx?WSDL");
		HTTPConduit http = (HTTPConduit) client.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(60000);
		httpClientPolicy.setAllowChunking(false);
		httpClientPolicy.setReceiveTimeout(60000);
		http.setClient(httpClientPolicy);
		Object[] objs = null;
		try {
			ParamInfo paramInfo = new ParamInfo();
			paramInfo.setPatientName("test");
			paramInfo.setPatientTel("13888888888");
			paramInfo.setTestCode("3001907");
			paramInfo.setStartDate("2018-01-01");
			paramInfo.setEndDate("2018-07-01");
			objs = client.invoke("GetTestComparison", "杭州迪安测试客户", "0185B48537A84484E050A8C02A005379","","");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (objs != null) {
			System.out.println(Arrays.toString(objs));
			String retJson = (String) objs[0];
			System.out.println(retJson);
		} else {
			System.out.println("结果为空");
		}

	}

}
