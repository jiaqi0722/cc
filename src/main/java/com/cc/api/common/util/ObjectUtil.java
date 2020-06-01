package com.cc.api.common.util;

import org.apache.commons.lang3.ObjectUtils;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: ObjectUtil
 */
public class ObjectUtil extends ObjectUtils {
    public ObjectUtil() {
    }

    public static String toString(Object obj) {
        return toString(obj, "");
    }

    public static String toString(Object obj, String defaultVal) {
        return obj == null ? defaultVal : obj.toString();
    }
}
