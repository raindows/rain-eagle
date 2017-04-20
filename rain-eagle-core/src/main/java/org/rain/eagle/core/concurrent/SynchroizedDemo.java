package org.rain.eagle.core.concurrent;

public class SynchroizedDemo {

	private boolean ready = false;

	private int number = 1;

	private int result = 0;

	public void write(int number) {
		ready = true;
		this.number = number;
	}

	public void read() {
		if (ready) {
			result = number * 3;
		}
		System.out.printf("result: %s threadName: %s \n", result, Thread.currentThread().getName());
	}

	private final class ReadThread extends Thread {
		@Override
		public void run() {
			read();
		}
	}

	private final class WriteThread extends Thread {
		private int num;

		public WriteThread(int num) {
			this.num = num;
		}

		@Override
		public void run() {
			write(num);
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			SynchroizedDemo synchroizedDemo = new SynchroizedDemo();
			synchroizedDemo.new WriteThread(i).start();
			synchroizedDemo.new ReadThread().start();
		}
	}

}
