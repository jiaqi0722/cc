package com.cc.api.common.aop;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.lang.reflect.Method;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cc.api.common.annotation.Copyright;
import com.cc.api.common.annotation.Login;
import com.cc.api.common.response.ResponseBean;
import com.cc.api.common.response.ResponseStatus;
import com.cc.api.common.util.CopyrightUtil;
import com.cc.api.common.util.JwtUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: RestAop
 */
@Component
@Aspect
public class RestAop {
    private static final Log log = LogFactory.getLog(RestAop.class);
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Value("${token.secret}")
    private String tokenSecret;

    public RestAop() {
    }

    @Around("execution(public * com.cc.api.controller..*.*(..)) || execution(public * com.cc.api.common.base.controller..*.*(..))")
    public Object AroundRest(ProceedingJoinPoint joinPoint) {
        String target = null;
        Object result = null;
        long timeConsuming = 0L;

        try {
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            Object[] args = joinPoint.getArgs();
            Object[] arguments = new Object[args.length];
            HttpServletRequest request = null;

            for(int i = 0; i < args.length; ++i) {
                if (!(args[i] instanceof HttpServletRequest) && !(args[i] instanceof HttpServletResponse) && !(args[i] instanceof MultipartFile)) {
                    arguments[i] = args[i];
                } else {
                    if (args[i] instanceof HttpServletRequest) {
                        request = (HttpServletRequest)args[i];
                    }

                    arguments[i] = args[i].getClass().getName();
                }
            }

            Method method = signature.getMethod();
            Class<?> targetClass = method.getDeclaringClass();
            target = targetClass.getName() + "#" + method.getName();
            String params = JSONObject.toJSONStringWithDateFormat(arguments, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[]{SerializerFeature.WriteMapNullValue});
            log.info("==> START CONTROLLER: " + target + " PARAMS:" + params);
            if (Objects.nonNull(method.getAnnotation(Copyright.class))) {
                ResponseBean responseBean = CopyrightUtil.checkCopyright();
                if (responseBean.hasError()) {
                    result = responseBean;
                    ResponseBean var14 = responseBean;
                    return var14;
                }
            }

            Object var15;
            if (Objects.nonNull(method.getAnnotation(Login.class))) {
                assert request != null;
                String token = request.getHeader("auth");
                log.info("开始校验token:" + token);

                try {
                    JwtUtil.parseJwtToken(token, this.tokenSecret);
                } catch (Exception var20) {
                    log.info("token已过期:" + token);
                    result = new ResponseBean(ResponseStatus.NO_LOGIN);
                    var15 = result;
                    return var15;
                }
            }

            long start = System.currentTimeMillis();
            result = joinPoint.proceed();
            timeConsuming = System.currentTimeMillis() - start;
            var15 = result;
            return var15;
        } catch (Throwable var21) {
            log.error(var21.getMessage(), var21);
            result = new ResponseBean(ResponseStatus.FAIL);
            Object var7 = result;
            return var7;
        } finally {
            log.info("<== END CONTROLLER: " + target + " RETURN:" + JSONObject.toJSONStringWithDateFormat(result, "yyyy-MM-dd HH:mm:ss", new SerializerFeature[]{SerializerFeature.WriteMapNullValue}) + " COST TIME:" + timeConsuming + "ms");
        }
    }
}