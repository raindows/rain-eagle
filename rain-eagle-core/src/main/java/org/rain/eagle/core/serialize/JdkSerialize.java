package org.rain.eagle.core.serialize;

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
		FileOutputStream fos = new FileOutputStream(new File("tmp.out"));
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(obj);
		close(oos);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T deserialize() throws Exception {
		FileInputStream fis = new FileInputStream(new File("tmp.out"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		T obj = (T) ois.readObject();
		close(ois);
		return obj;
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
