package org.rain.eagle.dubbo;

import org.rain.eagle.dubbo.service.DubboService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		try {
			String[] configLocations = new String[] { "application-dubbo-consumer.xml" };
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
			context.start();
			DubboService dubboService = (DubboService) context.getBean("dubboService");
			long time = dubboService.serverTime();
			System.err.println(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
