package cn.xiao.studentsystem.Utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.IOUtils;
import org.springframework.data.redis.serializer.SerializationException;

public class GenericFastJsonRedisSerializer {
    private final static ParserConfig defaultRedisConfig = new ParserConfig();
    static { defaultRedisConfig.setAutoTypeSupport(true);}

    public byte[] serialize(Object object) throws SerializationException {
        // 空，直接返回空数组
        if (object == null) {
            return new byte[0];
        }
        try {
            // 使用 JSON 进行序列化成二进制数组，同时通过 SerializerFeature.WriteClassName 参数，声明写入类全名。
            return JSON.toJSONBytes(object, SerializerFeature.WriteClassName);
        } catch (Exception ex) {
            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
        }
    }

    public Object deserialize(byte[] bytes) throws SerializationException {
        // 如果为空，则返回空对象
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            // 使用 JSON 解析成对象。
            return JSON.parseObject(new String(bytes, IOUtils.UTF8), Object.class, defaultRedisConfig);
        } catch (Exception ex) {
            throw new SerializationException("Could not deserialize: " + ex.getMessage(), ex);
        }
    }
}
