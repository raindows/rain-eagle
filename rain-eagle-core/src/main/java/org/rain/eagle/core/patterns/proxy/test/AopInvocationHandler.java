package org.rain.eagle.core.patterns.proxy.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopInvocationHandler implements InvocationHandler {

	private Logger LOG = LoggerFactory.getLogger(AopInvocationHandler.class);

	private Object obj;

	public AopInvocationHandler(Object obj) {
		super();
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		LOG.info("method :{} start time : {}", method.getName(), System.currentTimeMillis());

		Object value = method.invoke(obj, args);

		LOG.info("method :{} end time : {}\n", method.getName(), System.currentTimeMillis());
		return value;
	}

}
