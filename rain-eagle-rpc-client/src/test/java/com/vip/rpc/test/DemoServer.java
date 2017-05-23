package com.vip.rpc.test;

import com.vip.rpc.server.RpcServer;

public class DemoServer {
	
	public static void main(String[] args) {
		server();
	}

	private static void server() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					RpcServer.register("localhost", 8080).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
