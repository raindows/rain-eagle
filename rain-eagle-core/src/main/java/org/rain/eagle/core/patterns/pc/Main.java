package org.rain.eagle.core.patterns.pc;

import java.util.concurrent.TimeUnit;

import org.rain.eagle.core.patterns.pc.awaitsignal.StorageLock;
import org.rain.eagle.core.patterns.pc.queue.StorageQueue;
import org.rain.eagle.core.patterns.pc.waitnotify.StorageSync;

public class Main {

	public static void main(String[] args) {

		Storage<String> storage = null;

		storage = new StorageLock<String>(10);
		storage = new StorageQueue<String>(10);
		storage = new StorageSync<String>(10);

		testStorage(storage);

	}

	private static void testStorage(Storage<String> storage) {

		for (int i = 0; i < 15; i++) {
			new Thread(new Producer<String>(storage, "V#" + i)).start();
		}

		try {
			TimeUnit.SECONDS.sleep(1);
			System.err.println("-----------sleep 1s start-----------");
			TimeUnit.SECONDS.sleep(30);
			System.err.println("-----------sleep 30s end-----------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 20; i++) {
			new Thread(new Consumer<String>(storage)).start();
		}
		
		try {
			TimeUnit.SECONDS.sleep(1);
			System.err.println("-----------sleep 1s start-----------");
			TimeUnit.SECONDS.sleep(30);
			System.err.println("-----------sleep 30s end-----------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		for (int i = 15; i < 25; i++) {
			new Thread(new Producer<String>(storage, "V#" + i)).start();
		}
	}

}
