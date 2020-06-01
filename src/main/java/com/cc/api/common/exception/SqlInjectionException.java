package com.cc.api.common.exception;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: SqlInjectionException
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

