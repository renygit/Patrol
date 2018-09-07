package com.git.reny.wallpaper.core;

/**
 * Created by admin on 2017/6/7.
 */

public class ResultException extends RuntimeException {

    public static final int SUCCESSCODE = 0;
    public static final int SERVICE_ERROR = 500;
    public static final int UNKNOWNERROR = 9999;//自定义错误码

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
