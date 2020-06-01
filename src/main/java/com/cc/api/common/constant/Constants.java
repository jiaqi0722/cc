package com.cc.api.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: Constants
 */
@Component
public class Constants {

    public static final String ID_COLUMN_NAME = "id";

    public static final String STATE_COLUMN_NAME = "state";

    public static final String PASSWORD_COLUMN_NAME = "password";

    public static final String USER_NAME_COLUMN_NAME = "user_name";

    public static final String REAL_NAME_COLUMN_NAME = "real_name";

    public static final String SOFT_DEL_COLUMN_NAME = "soft_del";

    public static final String UPDATE_DATE_COLUMN_NAME = "update_date";

    public static final String CREATE_DATE_COLUMN_NAME = "create_date";

    public static final String UPDATE_BY_COLUMN_NAME = "update_by";

    public static final String CREATE_BY_COLUMN_NAME = "create_by";

    public static final String NORMAL_SOFT_DEL_VALUE = "1";

    public static final String DELETE_SOFT_DEL_VALUE = "0";

    public static final String TOKEN_HEADER_KEY = "auth";

    public static final String TOKEN_USER_ID_KEY = "user_id";

    public static final String TOKEN_USER_NAME_KEY = "user_name";

    public static final String TOKEN_REAL_NAME_KEY = "real_name";

    public static Integer TOKEN_EXPIRE;

    public static String TOKEN_SECRET;

    public static String TOKEN_ISSUER;

    public static String TOKEN_AUDIENCE;

    public Constants() {
    }

    @Value("${token.expire}")
    public void setTokenExpire(Integer tokenExpire) {
        TOKEN_EXPIRE = tokenExpire;
    }

    @Value("${token.secret}")
    public void setTokenSecret(String tokenSecret) {
        TOKEN_SECRET = tokenSecret;
    }

    @Value("${token.issuer}")
    public void setTokenIssuer(String tokenIssuer) {
        TOKEN_ISSUER = tokenIssuer;
    }

    @Value("${token.audience}")
    public void setTokenAudience(String tokenAudience) {
        TOKEN_AUDIENCE = tokenAudience;
    }
}
