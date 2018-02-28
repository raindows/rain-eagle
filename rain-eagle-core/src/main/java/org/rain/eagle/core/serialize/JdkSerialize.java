package org.rain.eagle.core.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JdkSerialize<T> implements SerializeStrategy<T> {

	@Override
	public void serialize(T obj) throws Exception {
		FileOutputStream fos = new FileOutputStream(new File(DATA_PERSISTENCE_TEMP_FILE));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		close(oos);
	}

	@Override
	public byte[] doSerialize(T obj) throws Exception {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
		oos.writeObject(obj);
		close(oos);
		close(byteArrayOutputStream);
		byte[] data = byteArrayOutputStream.toByteArray();
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize() throws Exception {
		FileInputStream fis = new FileInputStream(new File(DATA_PERSISTENCE_TEMP_FILE));
		ObjectInputStream ois = new ObjectInputStream(fis);
		T obj = (T) ois.readObject();
		close(ois);
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T doDeserialize(byte[] data) throws Exception {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
		ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
		T obj = (T) ois.readObject();
		close(ois);
		close(byteArrayInputStream);
		return obj;
	}

	@Override
	public T doDeserialize(byte[] data, Class<T> clazz) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
