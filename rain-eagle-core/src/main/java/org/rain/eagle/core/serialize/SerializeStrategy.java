package org.rain.eagle.core.serialize;

public interface SerializeStrategy<T> {

	public static final String DATA_PERSISTENCE_TEMP_FILE = "tmp.out";

	/**
	 * 序列化：将一个对象转换成一串二进制表示的字节数组，通过保存或转移这些字节数据来达到持久化的目的。
	 * 
	 * @param obj
	 * @throws Exception
	 */
	void serialize(T obj) throws Exception;

	byte[] doSerialize(T obj) throws Exception;

	/**
	 * 反序列化：将字节数组重新构造成对象。
	 * 
	 * @return
	 * @throws Exception
	 */
	T deserialize() throws Exception;

	T doDeserialize(byte[] data) throws Exception;

	T doDeserialize(byte[] data, Class<T> clazz) throws Exception;

}
