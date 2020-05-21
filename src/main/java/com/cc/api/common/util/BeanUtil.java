package com.cc.api.common.util;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

/**
 * * * * * * * * * * *
 * Here  be  dragons *
 * * * * * * * * * * *
 *
 * @author 特昂唐  2020/5/19  13:41
 * describe:
 */
public class BeanUtil {
    public BeanUtil() {
    }

    public static void copyNotBlankProperties(Object sourceBean, Object destBean) {
        Field[] fields = sourceBean.getClass().getDeclaredFields();
        Field fieldtemp = null;
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];
            if (!"serialVersionUID".equals(field.getName())) {
                try {
                    field.setAccessible(true);
                    Object obj = field.get(sourceBean);
                    if (obj != null && String.valueOf(obj).trim().length() > 0) {
                        fieldtemp = null;

                        try {
                            fieldtemp = destBean.getClass().getDeclaredField(field.getName());
                        } catch (NoSuchFieldException var10) {
                        }

                        if (null != fieldtemp) {
                            fieldtemp.setAccessible(true);
                            fieldtemp.set(destBean, obj);
                        }
                    }
                } catch (Exception var11) {
                }
            }
        }

    }

    public static void copyAllProperties(Object sourceBean, Object destBean) {
        try {
            if (null != sourceBean && null != destBean) {
                BeanUtils.copyProperties(sourceBean, destBean);
            }
        } catch (Exception var3) {
        }

    }
}

