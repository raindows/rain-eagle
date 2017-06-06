package org.rain.eagle.core.patterns.proxy;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.ProxyGenerator;

@SuppressWarnings({ "unchecked", "restriction" })
public class JdkProxy {

	private static final Logger LOG = LoggerFactory.getLogger(JdkProxy.class);

	static {
		// 生成代理类方法一
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		// 生成代理类方法二
		generateProxyClassFile("jdk$proxy", SpringAop.class);
	}

	public static <T> T newProxyInstance(Class<T> clazz, InvocationHandler h) {
		T proxy = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] { clazz }, h);
		return proxy;
	}

	public static <T> T getProxyInstance(Class<T> clazz, InvocationHandler h) {
		Class<T> proxyClazz = (Class<T>) Proxy.getProxyClass(clazz.getClassLoader(), new Class[] { clazz });
		try {
			Constructor<T> constructor = proxyClazz.getConstructor(new Class[] { InvocationHandler.class });
			T proxy = constructor.newInstance(h);
			return proxy;
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

	public static void generateProxyClassFile(String name, Class<?> clazz) {
		try {
			byte[] b = ProxyGenerator.generateProxyClass(name, new Class[] { clazz });
			FileOutputStream fos = new FileOutputStream(new File(name + ".class"));
			fos.write(b);
			fos.close();
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
