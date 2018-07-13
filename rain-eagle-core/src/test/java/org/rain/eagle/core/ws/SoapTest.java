package org.rain.eagle.core.ws;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.service.model.BindingInfo;
import org.apache.cxf.service.model.BindingMessageInfo;
import org.apache.cxf.service.model.BindingOperationInfo;
import org.apache.cxf.service.model.MessagePartInfo;
import org.apache.cxf.service.model.ServiceInfo;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSON;

public class SoapTest {

	public static void main(String[] args) throws Exception {
		method01();
		// method02();
	}

	private static void method01() {
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
			Map<String, String> paramInfo = new HashMap<>();
			paramInfo.put("patientName", "test");
			paramInfo.put("patientTel", "13888888888");
			paramInfo.put("testCode", "3001907");
			paramInfo.put("startDate", "2018-01-01");
			paramInfo.put("endDate", "2018-07-01");
			// objs = client.invoke("GetTestComparison", "杭州迪安测试客户",
			// "0185B48537A84484E050A8C02A005379","","");
			// objs = client.invoke("QueryReportsByHospInfo", "杭州迪安测试客户",
			// "0185B48537A84484E050A8C02A005379",paramInfo);
			objs = client.invoke("QueryReportsByHospInfo", "杭州迪安测试客户", "0185B48537A84484E050A8C02A005379",
					JSON.toJSONString(paramInfo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (objs != null) {
			System.out.println(Arrays.toString(objs) + "\n");
			String retJson = (String) objs[0];
			System.out.println(retJson + "\n");
			System.out.println(Jsoup.parse(retJson).select("PDFREPORTURL").html());
		} else {
			System.out.println("结果为空");
		}
	}

	private static void method02() throws Exception {
		JaxWsDynamicClientFactory factory = JaxWsDynamicClientFactory.newInstance();
		Client client = factory.createClient("http://r.dalabs.cn/ReportService.asmx?WSDL");
		HTTPConduit http = (HTTPConduit) client.getConduit();
		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(60000);
		httpClientPolicy.setAllowChunking(false);
		httpClientPolicy.setReceiveTimeout(60000);
		http.setClient(httpClientPolicy);
		Endpoint endpoint = client.getEndpoint();

		ServiceInfo serviceInfo = endpoint.getService().getServiceInfos().get(0);
		QName bindingName = new QName("http://r.dalabs.cn", "ReportServiceSoap12");
		BindingInfo binding = serviceInfo.getBinding(bindingName);
		QName opName = new QName("http://r.dalabs.cn", "QueryReportsByHospInfo");
		BindingOperationInfo boi = binding.getOperation(opName);
		BindingMessageInfo inputMessageInfo = null;
		if (!boi.isUnwrapped()) {
			inputMessageInfo = boi.getWrappedOperation().getInput();
		} else {
			inputMessageInfo = boi.getUnwrappedOperation().getInput();
		}

		List<MessagePartInfo> parts = inputMessageInfo.getMessageParts();
		for (MessagePartInfo messagePartInfo : parts) {
			System.out.println(messagePartInfo.getTypeClass());
		}
		MessagePartInfo parts2 = parts.get(2);

		Class<?> param2 = parts2.getTypeClass();
		Object param2Obj = param2.newInstance();

		PropertyDescriptor patientNameProp = new PropertyDescriptor("PatientName", param2);
		patientNameProp.getWriteMethod().invoke(param2Obj, "test");
		PropertyDescriptor patientTelProp = new PropertyDescriptor("PatientTel", param2);
		patientTelProp.getWriteMethod().invoke(param2Obj, "13888888888");
		PropertyDescriptor testCodeProp = new PropertyDescriptor("TestCode", param2);
		testCodeProp.getWriteMethod().invoke(param2Obj, "3001907");

		Object[] result = client.invoke(opName, "杭州迪安测试客户", "0185B48537A84484E050A8C02A005379", param2Obj);
		if (result != null) {
			System.out.println(Arrays.toString(result) + "\n");
			String retJson = (String) result[0];
			System.out.println(retJson + "\n");
			System.out.println(Jsoup.parse(retJson).select("PDFREPORTURL").html());
		} else {
			System.out.println("结果为空");
		}
	}

}
