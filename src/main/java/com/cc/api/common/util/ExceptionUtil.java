package com.cc.api.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: ExceptionUtil
 */
public class ExceptionUtil {
    public static String getException(Throwable e) {
        if (e == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            e.printStackTrace(new PrintStream(baos));
        } finally {
            try {
                baos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return baos.toString();
    }

    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException)e;
        }
        return new RuntimeException(e);
    }
}

