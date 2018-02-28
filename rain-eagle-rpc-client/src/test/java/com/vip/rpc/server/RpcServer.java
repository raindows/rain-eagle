package com.vip.rpc.server;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vip.rpc.service.RpcDemoService;
import com.vip.rpc.service.impl.RpcDemoServiceImpl;

public class RpcServer {

	private static final Logger LOG = LoggerFactory.getLogger(RpcServer.class);

	private AtomicBoolean startFlag = new AtomicBoolean(false);
	private static final ExecutorService executorService;
	private static final Map<Class<?>, Object> map;
	private ServerSocket serverSocket = null;
	private String ip;
	private int port;

	static {
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
		map = new ConcurrentHashMap<>();
		map.put(RpcDemoService.class, new RpcDemoServiceImpl());
	}

	private RpcServer(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public static RpcServer register(String ip, int port) {
		return new RpcServer(ip, port);
	}

	public void start() throws Exception {
		stop();
		serverSocket = new ServerSocket(port);
		startFlag.compareAndSet(false, true);
		LOG.info("socket server started ip: {} port: {}", ip, port);
		while (true) {
			final Socket socket = serverSocket.accept();
			executorService.execute(new Runnable() {
				@SuppressWarnings({ "unchecked", "rawtypes" })
				@Override
				public void run() {
					LOG.debug("server accept a request . time: {}", String.valueOf(System.currentTimeMillis()));
					ObjectInputStream ois = null;
					ObjectOutputStream oos = null;
					try {
						// 反序列化
						InputStream inputStream = socket.getInputStream();
						ois = new ObjectInputStream(inputStream);

						String className = ois.readUTF();
						String methodName = ois.readUTF();
						Class<?>[] parameterTypes = (Class<?>[]) ois.readObject();
						Object[] args = (Object[]) ois.readObject();

						LOG.debug("className: {} , method: {} , parameterTypes：{} , args: {}", className, methodName,Arrays.toString(parameterTypes), args);
						Class clazz = Class.forName(className);
						Method method = clazz.getMethod(methodName, parameterTypes);
						// java.lang.InstantiationException:
						// com.vip.rpc.service.RpcDemoService 接口抽象类无法实例化
						// 找不到具体的实现服务类
						Object result = method.invoke(map.get(clazz), args);

						// 序列化
						OutputStream outputStream = socket.getOutputStream();
						oos = new ObjectOutputStream(outputStream);
						oos.writeObject(result);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						close(ois);
						close(oos);
						close(socket);
					}
				}
			});
		}
	}
	
	private void close(Closeable closeable){
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		if (startFlag.compareAndSet(true, false)) {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
