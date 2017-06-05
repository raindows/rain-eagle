package org.rain.eagle.core.serialize;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

/**
 * @see http://www.protostuff.io/
 * @author xiaoyu04.wang
 *
 * @param <T>
 */
public class ProtostuffSerialize<T> implements SerializeStrategy<T> {

	private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

	private static Objenesis objenesis = new ObjenesisStd(true);

	@Override
	public void serialize(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public byte[] doSerialize(T obj) throws Exception {
		Class<T> cls = (Class<T>) obj.getClass();
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
		try {
			Schema<T> schema = getSchema(cls);
			return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}
	}

	@Override
	public T deserialize() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T doDeserialize(byte[] data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T doDeserialize(byte[] data, Class<T> clazz) throws Exception {
		try {
			T message = (T) objenesis.newInstance(clazz);
			Schema<T> schema = getSchema(clazz);
			ProtostuffIOUtil.mergeFrom(data, message, schema);
			return message;
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> Schema<T> getSchema(Class<T> cls) {
		Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
		if (schema == null) {
			schema = RuntimeSchema.createFrom(cls);
			if (schema != null) {
				cachedSchema.put(cls, schema);
			}
		}
		return schema;
	}

}
