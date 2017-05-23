package com.vip.rpc.test;

import com.vip.rpc.service.Req;
import com.vip.rpc.service.Resp;
import com.vip.rpc.service.RpcDemoService;

public class RpcDemoServiceCallable extends RpcDemoCallable<Req, Resp> {

	private RpcDemoService rpcDemoService;

	public RpcDemoServiceCallable(RpcDemoService rpcDemoService, Req q) {
		super(q);
		this.rpcDemoService = rpcDemoService;
	}

	@Override
	protected Resp execute(Req q) throws Exception {
		return rpcDemoService.execute(q);
	}

}
