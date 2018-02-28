package org.rain.eagle.web.annotation.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EagleServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.err.println("init servlet context");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.err.println("destroy servlet container");
	}

}
