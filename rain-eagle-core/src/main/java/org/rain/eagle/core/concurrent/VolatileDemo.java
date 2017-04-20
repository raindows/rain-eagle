package org.rain.eagle.core.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.sound.midi.Synthesizer;

public class VolatileDemo {

	private int number;

	public int getNumber() {
		return number;
	}

	public void increase() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(this){
			number++;	
		}
		
	}

	public static void main(String[] args) {
		final VolatileDemo volatileDemo = new VolatileDemo();
		/*for (int i = 0; i < 2000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					volatileDemo.increase();
				}
			}).start();
		}

		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
		System.out.printf("getNumber: %s \n", volatileDemo.getNumber());*/

		ExecutorService executorService = Executors.newFixedThreadPool(100);
		for (int i = 0; i < 500; i++) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					volatileDemo.increase();
				}
			});
		}
		executorService.shutdown();
		while(true){
			if(executorService.isTerminated()){
				System.out.printf("getNumber: %s \n", volatileDemo.getNumber());
				break;
			}
		}
	}
}
