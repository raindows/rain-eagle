package org.rain.eagle.core.patterns.pc;

public class Producer<T> implements Runnable{

	private Storage<T> storage;
	
	private T value;
	
	public Producer(Storage<T> storage) {
		super();
		this.storage = storage;
	}

	public Producer(Storage<T> storage, T value) {
		super();
		this.storage = storage;
		this.value = value;
	}

	@Override
	public void run() {
		produce();
	}

	private void produce() {
		storage.produce(value);
	}

}
