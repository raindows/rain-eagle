package com.vip.rpc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vip.rpc.client.RpcClient;
import com.vip.rpc.service.Req;
import com.vip.rpc.service.Resp;
import com.vip.rpc.service.RpcDemoService;

public class DemoClient {

	private static final Logger LOG = LoggerFactory.getLogger(DemoClient.class);

	public static void main(String[] args) {
		client();
	}

	private static void client() {
		final RpcDemoService rpcDemoService = new RpcClient<RpcDemoService>("127.0.0.1", 8080).getRpcInstance("com.vip.rpc.service.RpcDemoService");

		ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);
		List<RpcDemoServiceCallable> callableTasks = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			callableTasks.add(new RpcDemoServiceCallable(rpcDemoService, getReq(i)));
		}
		try {
			List<Future<Resp>> futures = executorService.invokeAll(callableTasks, 200, TimeUnit.MILLISECONDS);
			for (Future<Resp> future : futures) {
				try {
					Resp resp = future.get();
					LOG.debug("resp: {}", resp);
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static Req getReq(int i) {
		Req req = new Req();
		Map<String, String> headers = new HashMap<>();
		headers.put("protocal", "tcp");
		headers.put("req_time", String.valueOf(System.currentTimeMillis()));
		Map<String, String> bodys = new HashMap<>();
		bodys.put("id", String.valueOf(i));

		req.setHeaders(headers);
		req.setBodys(bodys);
		return req;
	}
}
