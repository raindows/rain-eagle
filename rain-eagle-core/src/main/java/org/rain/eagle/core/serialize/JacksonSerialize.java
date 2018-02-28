package org.rain.eagle.core.serialize;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JacksonSerialize<T> implements SerializeStrategy<T> {

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		objectMapper.setDateFormat(dateFormat);
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);
		objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, false);
		objectMapper.disable(SerializationFeature.FLUSH_AFTER_WRITE_VALUE);
		objectMapper.disable(SerializationFeature.CLOSE_CLOSEABLE);
		objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
	}

	@Override
	public void serialize(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] doSerialize(T obj) throws Exception {
		return objectMapper.writeValueAsBytes(obj);
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
		return objectMapper.readValue(data, clazz);
	}

}
