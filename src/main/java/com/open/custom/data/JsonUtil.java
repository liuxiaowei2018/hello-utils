package com.open.custom.data;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liuxiaowei
 * @date 2022年10月27日 13:49
 * @Description JSON工具类(基于JackSon)
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 私有化构造器
     */
    private JsonUtil() {
    }

    static {
        // 反序列化：JSON字段中有Java对象中没有的字段时不报错
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 反序列化：不允许基本类型为null
        //objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
        // 反序列化:忽略在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, false);

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
        // 设置java.util.Date, Calendar输出为数字（时间戳）
        //objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
    }

    public static <T> T obj2pojo(Object obj, Class<T> clazz) {
        return objectMapper.convertValue(obj, clazz);
    }

    /**
     * obj => json
     *
     * @param obj
     * @return java.lang.String
     * @throws RuntimeException
     */
    public static <T> String obj2Json(T obj) {
        if (!ObjectUtils.isEmpty(obj)) {
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public static <T> String obj2JsonPretty(T obj) {
        if (!ObjectUtils.isEmpty(obj)) {
            try {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    /**
     * 适用于 json转自定义对象
     * 调用示例： User user = JsonUtil.json2Pojo(userJsonStr, User.class);
     * @param json
     * @param clazz
     * @return T
     */
    public static <T> T json2Pojo(String json, Class<T> clazz) {
        return parse(json, clazz, null);
    }

    /**
     * 适用于 json转集合对象
     * 调用示例：List<User> userList = JsonUtil.json2Pojo(userListJson, new TypeReference<List<User>>() {});
     * @param json
     * @param type
     * @return T
     */
    public static <T> T json2Pojo(String json, TypeReference<T> type) {
        return parse(json, null, type);
    }

    /**
     * 适用于 json转自定义集合对象
     * 调用示例：List<User> userListBean2 = JsonUtil.json2Pojo(userListJson, List.class, User.class);
     * @param json
     * @param collectionClazz
     * @return T
     */
    public static <T> T json2Pojo(String json, Class<?> collectionClazz, Class<?>... clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(collectionClazz, clazz);
        try {
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            return null;
        }
    }

    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return objectMapper.convertValue(map, clazz);
    }

    public static <T> Map<String, Object> json2map(String json) {
        return parse(json, Map.class, null);
    }

    public static <T> Map<String, T> json2map(String json, Class<T> clazz) {
        Map<String, T> temp = parse(json, null, new TypeReference<Map<String, T>>() {});
        Map<String, T> result = new HashMap<>();
        for (Map.Entry<String, T> entry : temp.entrySet()) {
            result.put(entry.getKey(), map2pojo(new HashMap(), clazz));
        }
        return result;
    }

    /**
     * json => obj
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


}
