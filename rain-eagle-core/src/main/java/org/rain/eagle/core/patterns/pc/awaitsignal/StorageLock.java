package org.rain.eagle.core.patterns.pc.awaitsignal;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.rain.eagle.core.patterns.pc.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageLock<E> implements Storage<E> {

	private static final Logger LOG = LoggerFactory.getLogger(StorageLock.class);

	private int maxSize;

	private final LinkedList<E> list = new LinkedList<E>();

	private final Lock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	public StorageLock(int maxSize) {
		super();
		if (maxSize <= 0) {
			maxSize = DEFAULT_MAX_SIZE;
		}
		this.maxSize = maxSize;
	}

	public void produce(E value) {
		lock.lock();
		try {

			while (list.size() >= maxSize) {
				LOG.info("produce thread notFull.await,list size:{},value:{}", list.size(),value);
				notFull.await();
			}
			list.add(value);
			notEmpty.signalAll();
			LOG.info("produce notEmpty.signalAll thread,list size:{},produce-value:{},list:{}", list.size(), value,
					list);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			lock.unlock();
		}
	}

	public E consume() {
		lock.lock();
		try {

			while (list.size() == 0) {
				LOG.info("consume thread notEmpty.await,list size:{}", list.size());
				notEmpty.await();
			}
			E value = list.remove();
			notFull.signalAll();
			LOG.info("consume notFull.signalAll thread,list size:{}, consume-value:{},list:{}", list.size(), value,
					list);
			return value;

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		} finally {
			lock.unlock();
		}
		return null;
	}

}
