package org.rain.eagle.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EagleInvocationHandler implements InvocationHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(EagleInvocationHandler.class);
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long startTime = System.currentTimeMillis();
		//通信
		
		//序列化
		
		//反序列化
		Object value = null;
		long endTime = System.currentTimeMillis();
		LOG.debug("costTime : {}", (endTime-startTime)); 
		return value;
	}

}
