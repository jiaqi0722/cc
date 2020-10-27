package com.cc.api.common.util;

import java.util.regex.Pattern;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: SqlUtil
 */
public class SqlUtil {

    private static final String CHECK_SQL_INJECTION_REG_STR = "(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|union|join|delete|insert|trancate|char|into|substr|ascii|declare|exec|master|into|drop|execute)\\b)";
    private static final Pattern CHECK_SQL_INJECTION_REG = Pattern.compile("(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|and|or|union|join|delete|insert|trancate|char|into|substr|ascii|declare|exec|master|into|drop|execute)\\b)", 2);

    public SqlUtil() {
    }

    public static Pattern getCheckSqlInjectionReg() {
        return CHECK_SQL_INJECTION_REG;
    }

    public static boolean checkSqlInjection(String str) {
        return CHECK_SQL_INJECTION_REG.matcher(str).find();
    }
}

