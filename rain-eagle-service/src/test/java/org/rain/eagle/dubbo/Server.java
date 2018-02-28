package org.rain.eagle.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Server {

	public static void main(String[] args) {
		try {
			String[] configLocations = new String[] { "application-dubbo-provider.xml" };
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(configLocations);
			context.start();
			System.out.println("按任意键退出");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
