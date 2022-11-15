package com.open.custom.valid;

/**
 * @author liuxiaowei
 * @date 2022年11月15日 13:10
 * @Description
 */
public class NotValidException extends RuntimeException{

    /**
     * 错误结果代码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMessage;

    public NotValidException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
