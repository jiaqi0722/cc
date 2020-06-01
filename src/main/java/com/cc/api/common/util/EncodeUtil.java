package com.cc.api.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: EncodeUtil
 */
public class EncodeUtil {
    private static final String DEFAULT_URL_ENCODING = "UTF-8";

    public static String encodeUrl(String part) {
        return encodeUrl(part, "UTF-8");
    }

    public static String encodeUrl(String part, String encoding) {
        if (part == null) {
            return null;
        }
        try {
            return URLEncoder.encode(part, encoding);
        } catch (UnsupportedEncodingException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    public static String decodeUrl(String part) {
        return decodeUrl(part, "UTF-8");
    }

    public static String decodeUrl(String part, String encoding) {
        if (part == null) {
            return null;
        }
        try {
            return URLDecoder.decode(part, encoding);
        } catch (UnsupportedEncodingException e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    public static void main(String[] args) {
        String id = "number>=1,username is not null";
        System.out.println(encodeUrl(id));
        System.out.println(decodeUrl(encodeUrl(id)));
    }
}
