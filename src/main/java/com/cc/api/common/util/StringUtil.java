package com.cc.api.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/6/1  06:01
 * describe: StringUtil
 */
public class StringUtil {
    public StringUtil() {
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static boolean isEmpty(String str) {
        return StringUtils.isBlank(str);
    }

    public static String upperCase(String str) {
        return StringUtils.upperCase(str);
    }

    public static String lowerCase(String str) {
        return StringUtils.lowerCase(str);
    }

    public static String deleteWhitespace(String str) {
        str = StringUtils.deleteWhitespace(str);
        return str;
    }

    public static String StringFilte(String str) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static boolean checkEmail(String value) {
        boolean flag = false;
        Pattern p1 = null;
        Matcher m = null;
        p1 = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
        m = p1.matcher(value);
        flag = m.matches();
        return flag;
    }

    public static boolean validPhoneNum(String phoneNum) {
        boolean flag = false;
        Pattern p1 = null;
        Pattern p2 = null;
        Matcher m = null;
        p1 = Pattern.compile("^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1}))+\\d{8})?$");
        p2 = Pattern.compile("^(0[0-9]{2,3}\\-)?([1-9][0-9]{6,7})$");
        if (phoneNum.length() == 11 && p1.matcher(phoneNum).matches() || phoneNum.length() < 16 && p2.matcher(phoneNum).matches()) {
            flag = true;
            return flag;
        } else {
            return false;
        }
    }

    public static boolean IDCardValidate(String IDStr) throws Exception {
        IDStr = IDStr.trim().toUpperCase();
        String errorInfo = "";
        String[] ValCodeArr = new String[]{"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = new String[]{"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            return false;
        } else {
            if (IDStr.length() == 18) {
                Ai = IDStr.substring(0, 17);
            } else if (IDStr.length() == 15) {
                Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
            }

            if (!isNumeric(Ai)) {
                return false;
            } else {
                String strYear = Ai.substring(6, 10);
                String strMonth = Ai.substring(10, 12);
                String strDay = Ai.substring(12, 14);
                if (!isDataFormat(strYear + "-" + strMonth + "-" + strDay)) {
                    return false;
                } else {
                    GregorianCalendar gc = new GregorianCalendar();
                    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
                    if (gc.get(1) - Integer.parseInt(strYear) <= 150 && gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime() >= 0L) {
                        if (Integer.parseInt(strMonth) <= 12 && Integer.parseInt(strMonth) != 0) {
                            if (Integer.parseInt(strDay) <= 31 && Integer.parseInt(strDay) != 0) {
                                Hashtable h = GetAreaCode();
                                if (h.get(Ai.substring(0, 2)) == null) {
                                    return false;
                                } else {
                                    int TotalmulAiWi = 0;

                                    int modValue;
                                    for(modValue = 0; modValue < 17; ++modValue) {
                                        TotalmulAiWi += Integer.parseInt(String.valueOf(Ai.charAt(modValue))) * Integer.parseInt(Wi[modValue]);
                                    }

                                    modValue = TotalmulAiWi % 11;
                                    String strVerifyCode = ValCodeArr[modValue];
                                    Ai = Ai + strVerifyCode;
                                    if (IDStr.length() == 18) {
                                        return Ai.equals(IDStr);
                                    } else {
                                        return true;
                                    }
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
    }

    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static boolean isDataFormat(String str) {
        boolean flag = false;
        String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
        Pattern pattern1 = Pattern.compile(regxStr);
        Matcher isNo = pattern1.matcher(str);
        if (isNo.matches()) {
            flag = true;
        }

        return flag;
    }

    private static Hashtable GetAreaCode() {
        Hashtable hashtable = new Hashtable();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static boolean isIP(String str) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regex = "^" + num + "\\." + num + "\\." + num + "\\." + num + "$";
        return match(regex, str);
    }

    public static boolean IsUrl(String str) {
        String regex = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return match(regex, str);
    }

    public static boolean IsPostalcode(String str) {
        String regex = "^\\d{6}$";
        return match(regex, str);
    }

    public static boolean IsUpChar(String str) {
        String regex = "^[A-Z]+$";
        return match(regex, str);
    }

    public static boolean IsLowChar(String str) {
        String regex = "^[a-z]+$";
        return match(regex, str);
    }

    public static boolean IsLetter(String str) {
        String regex = "^[A-Za-z]+$";
        return match(regex, str);
    }

    public static boolean IsChinese(String str) {
        String regex = "^[一-龥],{0,}$";
        return match(regex, str);
    }
}
