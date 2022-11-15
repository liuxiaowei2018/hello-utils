package com.open.custom.http;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.gson.GsonBuilder;
import com.open.custom.data.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author liuxiaowei
 * @date 2022年11月15日 12:18
 * @Description 对象转URL参数
 */
public class UrlUtil {

    private final static String CODE_AND = "&";
    private final static String CODE_EQ = "=";
    private final static String CODE_BLANK = "";
    private final static String CODE_QUESTION_MARK = "?";

    /**
     * 对象转URL参数工具类 url参数拼接保留空
     *
     * @param obj obj
     * @return R
     */
    public static String obj2ParamWithBlank(Object obj) {
        String json = new GsonBuilder().serializeNulls().create().toJson(obj);
        Map<String, Object> map = JsonUtil.json2map(json);
        return Joiner.on(CODE_AND)
                .useForNull(CODE_BLANK)
                .withKeyValueSeparator(CODE_EQ)
                .join(map);
    }

    /**
     * 对象转URL参数工具类 url参数拼接保留空
     *
     * @param obj obj
     * @return R
     */
    public static String obj2ParamWithBlank(Map obj) {
        return Joiner.on(CODE_AND)
                .useForNull(CODE_BLANK)
                .withKeyValueSeparator(CODE_EQ)
                .join(obj);
    }

    /**
     * 对象转URL参数工具类 url参数拼接保留空
     *
     * @param obj obj
     * @return R
     */
    public static String obj2ParamWithBlank(String url, Map obj) {
        return url + obj2ParamWithBlank(obj);
    }

    /**
     * 对象转URL参数工具类 url 拼接参数
     *
     * @param obj obj
     * @return R
     */
    public static String obj2Param(Object obj) {
        String json = new GsonBuilder().serializeNulls().create().toJson(obj);
        Map<String, Object> map = JsonUtil.json2map(json);
        Map<String, Object> collect = map.entrySet().stream()
                .filter(it -> !ObjectUtils.isEmpty(it.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (x, y) -> y, LinkedHashMap::new));
        return Joiner.on(CODE_AND)
                .withKeyValueSeparator(CODE_EQ)
                .join(collect);
    }

    /**
     * url参数变map
     * eg
     * name=12&code=
     * 改变后
     * {"name":"12","code":""}
     *
     * @param urlParam
     * @return
     */
    public static Map<String, String> urlParam2Map(String urlParam) {
        Map<String, String> split = Splitter.on(CODE_AND)
                .withKeyValueSeparator(CODE_EQ)
                .split(urlParam);

        Map<String, String> map = new LinkedHashMap<>();
        map.putAll(split);
        return map;
    }

    /**
     * url参数转对象
     *
     * @param urlParam
     * @return
     */
    public static <T> T urlParam2Pojo(String urlParam, Class<T> clazz) {
        Map<String, String> split = Splitter.on(CODE_AND)
                .withKeyValueSeparator(CODE_EQ)
                .split(urlParam);
        return JsonUtil.obj2pojo(split, clazz);
    }

    /**
     * url参数变map
     * eg
     * https://www.baidu.com?name=12&code=
     * 改变后
     * {"name":"12","code":""}
     *
     * @param urlParam
     * @return
     */
    public static Map<String, String> urlParam2MapByUrl(String urlParam) {
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        if (StrUtil.isBlank(urlParam)) {
            return linkedHashMap;
        }
        boolean contains = StringUtils.contains(urlParam, CODE_QUESTION_MARK);
        if (contains) {
            urlParam = StringUtils.substringAfter(urlParam, CODE_QUESTION_MARK);
        }
        Iterable<String> split = Splitter.on(CODE_AND)
                .split(urlParam);
        split.forEach(it -> {
            String key = StringUtils.substringBefore(it, CODE_EQ);
            String value = StringUtils.substringAfter(it, CODE_EQ);
            linkedHashMap.put(key, value);
        });
        return linkedHashMap;
    }

    public static Map<String, String> urlParam2MapByUrlKeyTrim(String urlParam,String splitOn) {
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        if (StrUtil.isBlank(urlParam)) {
            return linkedHashMap;
        }
        boolean contains = StringUtils.contains(urlParam, CODE_QUESTION_MARK);
        if (contains) {
            urlParam = StringUtils.substringAfter(urlParam, CODE_QUESTION_MARK);
        }
        Iterable<String> split = Splitter.on(splitOn)
                .split(urlParam);
        split.forEach(it -> {
            String key = StringUtils.substringBefore(it, CODE_EQ).trim();
            String value = StringUtils.substringAfter(it, CODE_EQ).trim();
            linkedHashMap.put(key, value);
        });
        return linkedHashMap;
    }

    /**
     * 获取URL中的参数名和参数值的Map集合
     *
     * @param url
     * @return
     */
    public static Map<String, String> getUrlPramNameAndValue(String url) {
        Map<String, String> paramMap = new LinkedHashMap<>();
        if (StrUtil.isBlank(url)) {
            return paramMap;
        }
        String regEx = "(\\?|&+)(.+?)=([^&]*)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(url);
        while (m.find()) {
            // 获取参数名
            String paramName = m.group(2);
            String paramVal = m.group(3);
            paramMap.put(paramName, paramVal);
        }
        return paramMap;
    }


}
