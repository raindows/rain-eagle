package org.rain.eagle.dubbo.service.impl;

import org.rain.eagle.dubbo.service.DubboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DubboServiceImpl implements DubboService {

	private static final Logger LOG = LoggerFactory.getLogger(DubboServiceImpl.class);

	@Override
	public long serverTime() {
		long time = System.currentTimeMillis();
		LOG.info("server time : {}", time);
		return time;
	}

}
