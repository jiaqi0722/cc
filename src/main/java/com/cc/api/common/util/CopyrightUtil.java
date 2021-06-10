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

    private static Log log = LogFactory.getLog(CopyrightUtil.class);
    private static String PATH;
    private static String SECRET;
    private static String ISSUER;
    private static String AUDIENCE;
    private static boolean DEV;

    public CopyrightUtil() {
    }

    public static ResponseBean checkCopyright() {
        log.info("开始版权检查");
        if (DEV) {
            log.info("当前为开发者模式，通过版权检查！");
            return new ResponseBean();
        } else {
            String errorMsg = null;
            File licFile = new File(PATH);
            if (licFile.exists() && licFile.isFile()) {
                String licContent = null;

                try {
                    licContent = new String((new BASE64Decoder()).decodeBuffer(new String((new BASE64Decoder()).decodeBuffer(FileUtil.ReadFileContent(PATH)))));
                } catch (IOException var6) {
                    errorMsg = "版权文件格式无效，请联系系统服务人员！";
                    log.info(errorMsg);
                    var6.printStackTrace();
                    return new ResponseBean(ResponseStatus.COPY_RIGHT_ERROR, errorMsg);
                }

                Claims c = null;

                try {
                    c = JwtUtil.parseJwtToken(licContent, SECRET);
                } catch (Exception var5) {
                    errorMsg = "版权文件已过期，请联系系统服务人员！";
                    log.info(errorMsg);
                    return new ResponseBean(ResponseStatus.COPY_RIGHT_ERROR, errorMsg);
                }

                if (!ISSUER.equals(c.getIssuer())) {
                    errorMsg = "发行商信息不正确，请联系系统服务人员！";
                    log.info(errorMsg);
                    return new ResponseBean(ResponseStatus.COPY_RIGHT_ERROR, errorMsg);
                } else if (!AUDIENCE.equals(c.getAudience())) {
                    errorMsg = "企业信息不正确，请联系系统服务人员！";
                    log.info(errorMsg);
                    return new ResponseBean(ResponseStatus.COPY_RIGHT_ERROR, errorMsg);
                } else {
                    String mac = OSUtil.getMac();
                    if (!mac.equals(c.get("mac"))) {
                        errorMsg = "设备信息不正确，请联系系统服务人员！";
                        log.info(errorMsg);
                        return new ResponseBean(ResponseStatus.COPY_RIGHT_ERROR, errorMsg);
                    } else {
                        log.info("版权检查通过，您的licence将于" + DateUtil.getDateTimeFormat(c.getExpiration(), "YYYY-MM-dd HH:mm:ss") + "过期。");
                        return new ResponseBean();
                    }
                }
            } else {
                errorMsg = "没有找到版权文件，请将版权文件放到正确的路径下再启动服务！";
                log.info(errorMsg);
                return new ResponseBean(ResponseStatus.COPY_RIGHT_ERROR, errorMsg);
            }
        }
    }

    @Value("${lic.path}")
    public void setPATH(String PATH) {
        CopyrightUtil.PATH = PATH;
    }

    @Value("${lic.secret}")
    public void setSECRET(String SECRET) {
        CopyrightUtil.SECRET = SECRET;
    }

    @Value("${lic.issuer}")
    public void setISSUER(String ISSUER) {
        CopyrightUtil.ISSUER = ISSUER;
    }

    @Value("${lic.audience}")
    public void setAUDIENCE(String AUDIENCE) {
        CopyrightUtil.AUDIENCE = AUDIENCE;
    }

    @Value("${lic.dev}")
    public void setDEV(boolean DEV) {
        CopyrightUtil.DEV = DEV;
    }

}
