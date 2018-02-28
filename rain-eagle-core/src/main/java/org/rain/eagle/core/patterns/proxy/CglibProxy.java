package org.rain.eagle.core.patterns.proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * 
 * @see [CGLIB](https://github.com/cglib/cglib)
 */
public class CglibProxy {

	static {
		String key = DebuggingClassWriter.DEBUG_LOCATION_PROPERTY;
		String value = ".\\cglib";
		System.setProperty(key, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getProxyInstance(Class<T> clazz, MethodInterceptor methodInterceptor) {
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(methodInterceptor);
		enhancer.setSuperclass(clazz);
		T proxy = (T) enhancer.create();
		return proxy;
	}
}
