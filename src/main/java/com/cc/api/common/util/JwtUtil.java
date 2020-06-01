package com.cc.api.common.util;


import com.cc.api.common.constant.TimeUnitEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: JwtUtil
 */
public class JwtUtil {
    public JwtUtil() {
    }

    public static String getJwtToken(int expire, String issuer, String audience, String secret) {
        return getJwtToken(expire, (TimeUnitEnum)null, issuer, audience, secret, (Map)null);
    }

    public static String getJwtToken(int expire, TimeUnitEnum timeUnit, String issuer, String audience, String secret) {
        return getJwtToken(expire, timeUnit, issuer, audience, secret, (Map)null);
    }

    public static String getJwtToken(int expire, TimeUnitEnum timeUnit, String issuer, String audience, String secret, Map<String, Object> params) {
        Integer calendarTimeUnit = null;
        if (timeUnit != null) {
            switch(timeUnit) {
                case YEAR:
                    calendarTimeUnit = 1;
                    break;
                case MONTH:
                    calendarTimeUnit = 2;
                    break;
                case DATE:
                    calendarTimeUnit = 5;
                    break;
                case HOUR:
                    calendarTimeUnit = 10;
                    break;
                case SECOND:
                    calendarTimeUnit = 13;
                    break;
                default:
                    calendarTimeUnit = 12;
            }
        } else {
            calendarTimeUnit = 12;
        }

        Date iatDate = new Date();
        Calendar now = Calendar.getInstance();
        now.add(calendarTimeUnit, expire);
        Date expiresDate = now.getTime();
        Claims claims = Jwts.claims();
        claims.setIssuer(issuer);
        claims.setIssuedAt(iatDate);
        claims.setAudience(audience);
        claims.setExpiration(expiresDate);
        if (!CollectionUtils.isEmpty(params)) {
            Iterator var11 = params.keySet().iterator();

            while(var11.hasNext()) {
                String key = (String)var11.next();
                claims.put(key, params.get(key));
            }
        }

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public static Claims parseJwtToken(String token, String secret) throws Exception {
        return (Claims)Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public static String getCurrentUserId(HttpServletRequest request, String secret) throws Exception {
        String token = request.getHeader("auth");
        return getCurrentUserId(token, secret);
    }

    public static String getCurrentUserId(String token, String secret) throws Exception {
        Claims c = parseJwtToken(token, secret);
        return c.get("user_id").toString();
    }

    public static void main(String[] a) {
        Map<String, Object> m = new HashMap();
        m.put("testkey", "11");
        String token = getJwtToken(10, TimeUnitEnum.DATE, "service", "app", "f931f3c3dd634721bdca04bd233c2b371", m);
        System.out.println(token);

        try {
            Claims c = parseJwtToken(token, "f931f3c3dd634721bdca04bd233c2b37");
            System.out.println(c.getExpiration());
            System.out.println(c.getAudience());
            System.out.println(c.getIssuer());
            System.out.println(c.getIssuedAt());
            System.out.println(c.get("testkey"));
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }
}
