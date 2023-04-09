package com.gzc.frist.mavenpj.exception;

/**
 * @author: 拿破仑
 * @Date&Time: 2023/04/06  21:18  周四
 * @Project: MavenProjectOne
 * @Write software: IntelliJ IDEA
 * @Purpose: 自定义异常类：登录时异常
 */
public class LoginFailedException extends RuntimeException{

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}