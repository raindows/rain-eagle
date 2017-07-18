package org.rain.eagle.core.patterns.pc.queue;

import java.util.concurrent.LinkedBlockingQueue;

import org.rain.eagle.core.patterns.pc.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageQueue<E> implements Storage<E> {

	private static final Logger LOG = LoggerFactory.getLogger(StorageQueue.class);

	private int maxSize;

	private LinkedBlockingQueue<E> queue = null;

	public StorageQueue(int maxSize) {
		super();
		if (maxSize <= 0) {
			maxSize = DEFAULT_MAX_SIZE;
		}
		this.maxSize = maxSize;
		queue = new LinkedBlockingQueue<E>(this.maxSize);
	}

	@Override
	public void produce(E value) {
		try {
			if (queue.size() == maxSize) {
				LOG.info("produce queue.size:{},value:{}", maxSize, value);
			}
			queue.put(value);
			LOG.info("produce-value:{} queue:{}", value, queue);
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
	}

	@Override
	public E consume() {
		try {
			if (queue.size() == 0) {
				LOG.info("consume queue.size:0");
			}
			E value = queue.take();
			LOG.info("consume-value:{} queue:{}", value, queue);
			return value;
		} catch (InterruptedException e) {
			LOG.error(e.getMessage(), e);
		}
		return null;
	}

}
