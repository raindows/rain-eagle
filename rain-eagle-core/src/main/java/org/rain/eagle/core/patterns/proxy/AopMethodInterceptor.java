package org.rain.eagle.core.patterns.proxy;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class AopMethodInterceptor implements MethodInterceptor {

	private Logger LOG = LoggerFactory.getLogger(AopMethodInterceptor.class);

	private Object obj;

	public AopMethodInterceptor(Object obj) {
		super();
		this.obj = obj;
	}

	@Override
	public Object intercept(Object paramObject, Method paramMethod, Object[] paramArrayOfObject,
			MethodProxy paramMethodProxy) throws Throwable {
		LOG.info("method :{} start time : {}", paramMethod.getName(), System.currentTimeMillis());

		//Object value = paramMethod.invoke(obj, paramArrayOfObject);
		Object value = paramMethodProxy.invokeSuper(obj, paramArrayOfObject);

		LOG.info("method :{} end time : {}\n", paramMethod.getName(), System.currentTimeMillis());
		return value;
	}

}
