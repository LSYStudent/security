package com.lsy.test.security.config;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 返回视图
 * @author lsy
 * @param <T>
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonView(value = ModelView.class)
    private int code;

    @JsonView(value = ModelView.class)
    private LocalDateTime timestamp;

    @JsonView(value = ModelView.class)
    private String message;

    @JsonView(value = ModelView.class)
    private T data;

    /**
     * 成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> ok(T data) {
        return resultApi(Constants.SUCCESS, LocalDateTime.now(), null, data);
    }

    public static <T> R<T> ok() {
        return resultApi(Constants.SUCCESS, LocalDateTime.now(), null, null);
    }

    /**
     * 失败
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> failure(String message) {
        return resultApi(Constants.FAILURE, LocalDateTime.now(), message, null);
    }

    private static <T> R<T> resultApi(int code, LocalDateTime timestamp, String message, T data) {
        R<T> api = new R<T>();
        api.setCode(code);
        api.setTimestamp(timestamp);
        api.setMessage(message);
        api.setData(data);
        return api;
    }
}
