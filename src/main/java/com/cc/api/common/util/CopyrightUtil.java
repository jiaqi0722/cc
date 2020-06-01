package com.cc.api.common.util;

import com.cc.api.common.response.ResponseBean;
import com.cc.api.common.response.ResponseStatus;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: CopyrightUtil
 */
@Component
public class CopyrightUtil {
    private static final Log log = LogFactory.getLog(CopyrightUtil.class);
    private static boolean DEV;

    public CopyrightUtil() {
    }

    public static ResponseBean checkCopyright() {
        log.info("开始版权检查");
        if (DEV) {
            log.info("当前为开发者模式，通过版权检查！");
            return new ResponseBean();
        } else {
            log.info("没有找到版权文件，请将版权文件放到正确的路径下再启动服务！");
            return new ResponseBean(ResponseStatus.COPY_RIGHT_ERROR, "没有找到版权文件，请将版权文件放到正确的路径下再启动服务！");

        }
    }

    @Value("${token.dev}")
    public void setDEV(boolean DEV) {
        CopyrightUtil.DEV = DEV;
    }

}
