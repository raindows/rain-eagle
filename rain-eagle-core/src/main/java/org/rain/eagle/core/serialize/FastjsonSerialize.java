package org.rain.eagle.core.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @see https://github.com/alibaba/fastjson
 * @see https://github.com/alibaba/fastjson/wiki/enable_autotype
 * @author xiaoyu04.wang
 *
 * @param <T>
 */
public class FastjsonSerialize<T> implements SerializeStrategy<T> {

	private static final SerializerFeature[] serializerFeature = { SerializerFeature.WriteClassName };

	private static final Feature[] features = { Feature.AllowSingleQuotes, Feature.DisableASM };

	static {
		// ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
		ParserConfig.getGlobalInstance().addAccept("org.rain.eagle.core.serialize");
	}

	@Override
	public void serialize(T obj) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public byte[] doSerialize(T obj) throws Exception {
		return JSON.toJSONBytes(obj, serializerFeature);
	}

	@Override
	public T deserialize() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T doDeserialize(byte[] data) throws Exception {
		return (T) JSON.parse(data, features);
	}

	@Override
	public T doDeserialize(byte[] data, Class<T> clazz) throws Exception {
		return JSON.parseObject(data, clazz);
	}

}
