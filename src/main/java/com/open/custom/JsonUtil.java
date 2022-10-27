package com.open.custom;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author liuxiaowei
 * @date 2022年10月27日 13:49
 * @Description JSON工具类(基于JackSon)
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 反序列化：JSON字段中有Java对象中没有的字段时不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 反序列化：不允许基本类型为null
        //objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);

        // 序列化：序列化BigDecimal时不使用科学计数法输出
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        // 序列化：Java对象为空的字段不拼接JSON
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 忽略空Bean转json的错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // 注册一个时间序列化及反序列化的处理模块，用于解决jdk8中localDateTime等的序列化问题
        objectMapper.registerModule(new JavaTimeModule());
        // 设置Date类型的序列化及反序列化格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static <T> T json2Pojo(String json, Class<T> clazz) {
        return parse(json, clazz, null);
    }

    public static <T> T json2Pojo(String json, TypeReference<T> type) {
        return parse(json, null, type);
    }

    /**
     * json => 对象处理方法
     * <br>
     * 参数clazz和type必须一个为null，另一个不为null
     * <br>
     * 此方法不对外暴露，访问权限为private
     *
     * @param json  源json
     * @param clazz 对象类
     * @param type  对象类型
     * @param <T>   泛型
     */
    private static <T> T parse(String json, Class<T> clazz, TypeReference type) {
        T obj = null;
        if (StrUtil.isNotBlank(json)) {
            try {
                if (clazz != null) {
                    obj = objectMapper.readValue(json, clazz);
                } else {
                    obj = objectMapper.readValue(json, type);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return obj;
    }

    public static <T> String toJson(T obj) {
        if (!ObjectUtils.isEmpty(obj)) {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


}
