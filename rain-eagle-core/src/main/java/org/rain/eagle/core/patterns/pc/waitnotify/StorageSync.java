package org.rain.eagle.core.patterns.pc.waitnotify;

import java.util.LinkedList;

import org.rain.eagle.core.patterns.pc.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageSync<E> implements Storage<E> {

	private static final Logger LOG = LoggerFactory.getLogger(StorageSync.class);

	private int maxSize;

	private final LinkedList<E> list = new LinkedList<E>();

	public StorageSync(int maxSize) {
		super();
		if (maxSize <= 0) {
			maxSize = DEFAULT_MAX_SIZE;
		}
		this.maxSize = maxSize;
	}

	public void produce(E value) {
		synchronized (list) {
			while (list.size() >= maxSize) {
				try {
					LOG.info("produce thread wait,list size:{},value:{}", list.size(), value);
					list.wait();
				} catch (InterruptedException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			list.add(value);
			list.notifyAll();
			LOG.info("produce notifyAll thread,list size:{},produce-value:{},list:{}", list.size(), value, list);
		}
	}

	public E consume() {
		synchronized (list) {
			while (list.size() == 0) {
				try {
					LOG.info("consume thread wait,list size:{}", list.size());
					list.wait();
				} catch (InterruptedException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			E value = list.remove();
			list.notifyAll();
			LOG.info("consume notifyAll thread,list size:{}, consume-value:{},list:{}", list.size(), value, list);
			return value;
		}
	}

}
