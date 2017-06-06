package org.rain.eagle.core.patterns.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringAopImpl implements SpringAop {

	private static final Logger LOG = LoggerFactory.getLogger(SpringAopImpl.class);

	@Override
	public boolean isOpen() {
		LOG.info("method : {} executing time : {} ", "isOpen", System.currentTimeMillis());
		return false;
	}

	@Override
	public void process() {
		LOG.info("method : {} executing time : {} ", "process", System.currentTimeMillis());
		this.isOpen();
	}

}
