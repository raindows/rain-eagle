package org.rain.eagle.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EagleRpcClient {

	private static final Logger LOG = LoggerFactory.getLogger(EagleRpcClient.class);

	private String ip;
	private int port;

	public EagleRpcClient() {
		super();
	}

	public EagleRpcClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	@SuppressWarnings("unchecked")
	public <T> T newInstance(String className) throws Exception {
		Class<?> clazz = Class.forName(className);
		
		ClassLoader loader = clazz.getClassLoader();
		Class<?>[] interfaces = clazz.getInterfaces();
		InvocationHandler h = new EagleInvocationHandler();
		
		T proxy = (T) Proxy.newProxyInstance(loader, interfaces, h);
		return proxy;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
