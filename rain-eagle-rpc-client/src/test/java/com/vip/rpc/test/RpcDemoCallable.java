package com.vip.rpc.test;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RpcDemoCallable<Q, P> implements Callable<P> {

	private static final Logger LOG = LoggerFactory.getLogger(RpcDemoCallable.class);

	private Q q;

	private static final AtomicLong count = new AtomicLong();
	private static final AtomicLong successCount = new AtomicLong();
	private static final AtomicLong failCount = new AtomicLong();

	public RpcDemoCallable(Q q) {
		super();
		this.q = q;
	}

	@Override
	public P call() throws Exception {
		long start = System.currentTimeMillis();
		try {
			P p = this.execute(q);
			successCount.incrementAndGet();
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			failCount.incrementAndGet();
		} finally {
			count.incrementAndGet();
			long end = System.currentTimeMillis();
			LOG.debug("time: {}ms , q: {} , count: {} , failCount: {} , successCount ： {} , successRate : {}%",
					(end - start), q, count, failCount, successCount,
					(successCount.longValue() / count.longValue()) * 100);
		}
		return null;
	}

	protected abstract P execute(Q q) throws Exception;

}
