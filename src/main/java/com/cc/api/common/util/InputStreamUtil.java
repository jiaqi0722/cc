package com.cc.api.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: InputStreamUtil
 */
public class InputStreamUtil {
    public InputStreamUtil() {
    }

    public static InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    public static final byte[] input2byte(InputStream inStream) throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        boolean var3 = false;

        int rc;
        while((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }

        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
