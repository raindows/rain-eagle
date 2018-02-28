package org.rain.eagle.core.patterns.pc;

public class Consumer<T> implements Runnable{

	private Storage<T> storage;
	
	public Consumer(Storage<T> storage) {
		super();
		this.storage = storage;
	}

	@Override
	public void run() {
		consume();
	}

	private T consume() {
		T value = storage.consume();
		return value;
	}

}
