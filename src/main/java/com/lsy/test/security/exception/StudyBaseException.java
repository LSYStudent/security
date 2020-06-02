package com.lsy.test.security.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 异常返回信息
 * @author lsy
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class StudyBaseException extends RuntimeException {

    /**
     * 状态码
     */
    private int code;

    /**
     * 异常信息
     */
    private String message;

    public StudyBaseException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public StudyBaseException(String message) {
        super(message);
        this.message = message;
    }
}
