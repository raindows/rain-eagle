package org.rain.eagle.core.serialize;

public class SerializeExecutor<T> {

	private SerializeStrategy<T> serializeStrategy;

	public SerializeExecutor() {
		super();
	}

	public SerializeExecutor(SerializeStrategy<T> serializeStrategy) {
		super();
		this.serializeStrategy = serializeStrategy;
	}

	public void serialize(T obj) throws Exception {
		serializeStrategy.serialize(obj);
	}

	public byte[] doSerialize(T obj) throws Exception {
		return serializeStrategy.doSerialize(obj);
	}

	public T deserialize() throws Exception {
		return serializeStrategy.deserialize();
	}

	public T doDeserialize(byte[] data) throws Exception {
		return serializeStrategy.doDeserialize(data);
	}

	public T doDeserialize(byte[] data, Class<T> clazz) throws Exception {
		return serializeStrategy.doDeserialize(data, clazz);
	}

	public SerializeStrategy<T> getSerializeStrategy() {
		return serializeStrategy;
	}

	public void setSerializeStrategy(SerializeStrategy<T> serializeStrategy) {
		this.serializeStrategy = serializeStrategy;
	}

}
