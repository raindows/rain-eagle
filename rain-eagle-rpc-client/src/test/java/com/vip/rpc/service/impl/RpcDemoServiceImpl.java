package com.vip.rpc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vip.rpc.service.Req;
import com.vip.rpc.service.Resp;
import com.vip.rpc.service.RpcDemoService;

public class RpcDemoServiceImpl implements RpcDemoService {

	private static final Logger LOG = LoggerFactory.getLogger(RpcDemoServiceImpl.class);

	public RpcDemoServiceImpl() {

	}

	@Override
	public Resp execute(Req req) throws Exception {
		LOG.debug("req : {}", req);
		return new Resp("200", "SUCCESS");
	}

}
