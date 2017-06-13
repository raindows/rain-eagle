package org.rain.eagle.core.patterns.proxy;

public class Main {

	public static void main(String[] args) {
		// ================================jdk
		SpringAop jdk$ProxyA = JdkProxy.newProxyInstance(SpringAop.class,
				new AopInvocationHandler(new SpringAopImpl()));
		jdk$ProxyA.process();
		jdk$ProxyA.isOpen();

		// ================================jdk
		SpringAop jdk$ProxyB = JdkProxy.getProxyInstance(SpringAop.class,
				new AopInvocationHandler(new SpringAopImpl()));
		jdk$ProxyB.process();
		jdk$ProxyB.isOpen();

		// ================================cglib
		SpringAop cglib$$Proxy = CglibProxy.getProxyInstance(SpringAop.class,
				new AopMethodInterceptor(new SpringAopImpl()));
		cglib$$Proxy.process();
		cglib$$Proxy.isOpen();
	}

}
