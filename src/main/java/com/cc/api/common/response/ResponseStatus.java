package com.cc.api.common.response;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: ResponseStatus
 */
public enum ResponseStatus {
    /**
     * success
     */
    SUCCESS,
    /**
     * fail
     */
    FAIL,
    /**
     * no_mobile
     */
    NO_MOBILE,
    /**
     * no_data
     */
    NO_DATA,
    /**
     * params_error
     */
    PARAMS_ERROR,
    /**
     * repeat
     */
    REPEAT,
    /**
     * busy
     */
    BUSY,
    /**
     * forbiden
     */
    FORBIDEN,
    /**
     * too_many
     */
    TOO_MANY,
    /**
     * no_checked
     */
    NO_CHECKED,
    /**
     * no_login
     */
    NO_LOGIN,
    /**
     * user_exist
     */
    USER_EXIST,
    /**
     * copy_right_error
     */
    COPY_RIGHT_ERROR;

    private ResponseStatus() {
    }

    /**
     * static
     * @param rs rs
     * @return String
     */
    public static String getStatus(ResponseStatus rs) {
        switch(rs) {
            case SUCCESS:
                return "SUCCESS";
            case FAIL:
                return "FAIL";
            case NO_MOBILE:
                return "NO_MOBILE";
            case NO_DATA:
                return "NO_DATA";
            case PARAMS_ERROR:
                return "PARAMS_ERROR";
            case REPEAT:
                return "REPEAT";
            case FORBIDEN:
                return "FORBIDEN";
            case BUSY:
                return "BUSY";
            case TOO_MANY:
                return "TOO_MANY";
            case NO_CHECKED:
                return "NO_CHECKED";
            case NO_LOGIN:
                return "NO_LOGIN";
            case USER_EXIST:
                return "USER_EXIST";
            case COPY_RIGHT_ERROR:
                return "COPY_RIGHT_ERROR";
            default:
                return "UNKNOWN";
        }
    }

    public static String getMsg(ResponseStatus rs) {
        switch(rs) {
            case SUCCESS:
                return  "请求成功";
            case FAIL:
                return  "请求失败";
            case NO_MOBILE:
                return  "未绑定手机号";
            case NO_DATA:
                return "无返回数据";
            case PARAMS_ERROR:
                return "参数错误";
            case REPEAT:
            default:
                return "未知错误";
            case FORBIDEN:
                return "无权限";
            case BUSY:
                return "系统繁忙";
            case TOO_MANY:
                return "当前系统人数较多";
            case NO_CHECKED:
                return "无匹配";
            case NO_LOGIN:
                return "请重新登录";
            case USER_EXIST:
                return "用户名已存在";
            case COPY_RIGHT_ERROR:
                return "版权信息错误";
        }
    }
}
