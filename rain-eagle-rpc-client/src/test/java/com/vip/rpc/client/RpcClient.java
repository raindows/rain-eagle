package com.vip.rpc.client;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcClient<T> {

	private static final Logger LOG = LoggerFactory.getLogger(RpcClient.class);

	private String ip;
	private int port;

	public RpcClient(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	@SuppressWarnings("unchecked")
	public T getRpcInstance(String className) {
		Class<?> initClazz= null;
		try {
			initClazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		final Class<?> clazz = initClazz;
		return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[] { clazz },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Socket socket = null;
						ObjectInputStream ois = null;
						ObjectOutputStream oos = null;
						try {
							socket = new Socket(ip, port);
							LOG.debug("connect to server . ip: {} , port: {}", ip, port);

							// 序列化
							oos = new ObjectOutputStream(socket.getOutputStream());
							oos.writeUTF(clazz.getName());
							oos.writeUTF(method.getName());
							oos.writeObject(method.getParameterTypes());
							oos.writeObject(args);

							// 反序列化
							ois = new ObjectInputStream(socket.getInputStream());
							Object result = ois.readObject();
							LOG.debug("resp result: {}", result);
							return result;
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
							close(ois);
							close(oos);
							close(socket);
						}
						return oos;
					}
				});

	}

	private void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
