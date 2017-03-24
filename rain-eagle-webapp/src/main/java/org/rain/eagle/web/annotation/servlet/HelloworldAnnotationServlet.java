package org.rain.eagle.web.annotation.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloworld", urlPatterns = { "/s", "/s/*" }, loadOnStartup = 1, initParams = {
		@WebInitParam(name = "init_param", value = "hello world") })
public class HelloworldAnnotationServlet extends HttpServlet {

	private static final long serialVersionUID = 6731900702844254509L;

	private String init_param;

	public HelloworldAnnotationServlet() {
		System.err.println("init");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// super.doGet(req, resp);
		System.err.println(init_param);
	}

	@Override
	protected long getLastModified(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return super.getLastModified(req);
	}

	@Override
	protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doHead(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doOptions(req, resp);
	}

	@Override
	protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doTrace(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		return super.getInitParameter(name);
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO Auto-generated method stub
		return super.getInitParameterNames();
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return super.getServletConfig();
	}

	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return super.getServletContext();
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return super.getServletInfo();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		init_param = this.getInitParameter("init_param");
	}

	@Override
	public void log(String msg) {
		// TODO Auto-generated method stub
		super.log(msg);
	}

	@Override
	public void log(String message, Throwable t) {
		// TODO Auto-generated method stub
		super.log(message, t);
	}

	@Override
	public String getServletName() {
		// TODO Auto-generated method stub
		return super.getServletName();
	}

}
