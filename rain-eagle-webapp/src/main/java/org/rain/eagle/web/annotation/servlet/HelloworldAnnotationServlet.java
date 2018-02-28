package org.rain.eagle.web.annotation.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "helloworld", urlPatterns = { "/s", "/s/*" }, loadOnStartup = 1, initParams = {
		@WebInitParam(name = "init_param", value = "hello world") })
public class HelloworldAnnotationServlet extends HttpServlet {

	private static final long serialVersionUID = 6731900702844254509L;

	private String init_param;

	public HelloworldAnnotationServlet() {
		System.err.println("init");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.err.println(init_param);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title>Request Information Example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h3>Request Information Example</h3>");
		out.println("Method: " + request.getMethod());
		out.println("<br/>Request URI: " + request.getRequestURI());
		out.println("<br/>Protocol: " + request.getProtocol());
		out.println("<br/>PathInfo: " + request.getPathInfo());
		out.println("<br/>Remote Address: " + request.getRemoteAddr());

		out.println("<br/><hr/>");

		Enumeration<String> e = request.getHeaderNames();
		while (e.hasMoreElements()) {
			String name = (String) e.nextElement();
			String value = request.getHeader(name);
			out.println(name + " = " + value);
			out.println("<br/>");
		}

		out.println("<br/><hr/>");

		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie c = cookies[i];
			String name = c.getName();
			String value = c.getValue();
			out.println(name + " = " + value);
			out.println("<br/>");
		}

		// set a cookie

		String name = request.getParameter("cookieName");
		if (name != null && name.length() > 0) {
			String value = request.getParameter("cookieValue");
			Cookie c = new Cookie(name, value);
			response.addCookie(c);
		}

		out.println("<br/><hr/>");

		HttpSession session = request.getSession(true);

		// print session info

		Date created = new Date(session.getCreationTime());
		Date accessed = new Date(session.getLastAccessedTime());
		int maxInactiveInterval = session.getMaxInactiveInterval();
		out.println("ID " + session.getId());
		out.println("<br/>Created: " + created);
		out.println("<br/>Last Accessed: " + accessed);
		out.println("<br/>maxInactiveInterval: " + maxInactiveInterval);

		// set session info if needed

		String dataName = request.getParameter("dataName");
		if (dataName != null && dataName.length() > 0) {
			String dataValue = request.getParameter("dataValue");
			session.setAttribute(dataName, dataValue);
		}

		// print session contents

		Enumeration<String> sessions = session.getAttributeNames();
		while (sessions.hasMoreElements()) {
			String sessionName = (String) e.nextElement();
			String value = session.getAttribute(sessionName).toString();
			out.println(name + " = " + value);
			out.println("<br/>");
		}

		out.println("</body>");
		out.println("</html>");
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
