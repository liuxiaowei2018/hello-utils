package com.open.custom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author liuxiaowei
 * @date 2022年03月14日 14:01
 * @Description
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("ALL")
public class R<T> implements Serializable {

    private static final long serialVersionUID = 7410889986427304506L;

    /**
     * 响应编码:0-请求处理成功
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 附加数据
     */
    private Map<Object, Object> extra;

    /**
     * 响应时间戳
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp = new Date();

    private R() {}

    @JsonIgnore
    public boolean isSuccess() {
        return Objects.equals(0, code);
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    public static <T> R<T> success() {
        return success(null);
    }

    public static <T> R<T> success(T data) {
        return success(data, "操作成功");
    }

    public static <T> R<T> success(T data, String message) {
        R<T> result = new R<>();
        result.code = 0;
        result.data = data;
        result.message = message;
        return result;
    }

    public static <T> R<T> error(String message) {
        R<T> result = new R<>();
        result.code = 1;
        result.message = message;
        return result;
    }

    public R<T> put(String key, Object value) {
        if (this.extra == null) {
            this.extra = new HashMap<>(16);
        }
        this.extra.put(key, value);
        return this;
    }

    public R<T> putAll(Map<Object, Object> extra) {
        if (this.extra == null) {
            this.extra = new HashMap<>(16);
        }
        this.extra.putAll(extra);
        return this;
    }

}
