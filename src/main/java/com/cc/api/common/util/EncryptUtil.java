package com.cc.api.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/28  16:30
 * describe:
 */
public class EncryptUtil {
    private static final Log log = LogFactory.getLog(EncryptUtil.class);

    public EncryptUtil() {
    }

    public static String encoderPassword(String source, String salt) {
        if (StringUtils.isNotBlank(salt)) {
            if (salt.length() > 1) {
                source = salt.substring(0, salt.length() / 2) + source + salt.substring(salt.length() / 2);
            } else {
                source = salt + source + "lt";
            }
        } else {
            source = "asdfghjkl" + source + "lt";
        }

        source = (new BASE64Encoder()).encode(source.getBytes());
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = source.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var11) {
            var11.printStackTrace();
            return "";
        }
    }

    public static String encodePwd(String source) {
        source = "asdfghjkl" + source + "lt";
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            source = (new BASE64Encoder()).encode(source.getBytes());
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(source.getBytes());
            byte[] md = mdInst.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;

            for(int i = 0; i < j; ++i) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            return new String(str);
        } catch (Exception var9) {
            log.error(var9.getMessage(), var9);
            return "";
        }
    }
}
