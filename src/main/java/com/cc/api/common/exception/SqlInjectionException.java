package com.cc.api.common.exception;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author teangtang
 */
public class SqlInjectionException extends RuntimeException {
    private static final String EXCEPTION_MSG = "所传参数中含有SQL注入非法字符";

    public SqlInjectionException() {
        super("所传参数中含有SQL注入非法字符");
    }

    public SqlInjectionException(String message) {
        super(message);
    }
}

