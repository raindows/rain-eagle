package org.rain.eagle.core.patterns.pc;

public interface Storage<E> {

	int DEFAULT_MAX_SIZE = 100;

	void produce(E value);

	E consume();

}
