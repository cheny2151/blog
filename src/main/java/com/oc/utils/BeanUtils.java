package com.oc.utils;

import com.oc.exception.BeanReflectException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 反射工具类
 */
public class BeanUtils {

    private final static Logger LOGGER = Logger.getLogger(BeanUtils.class);

    private final static String GET_PRE = "get";

    private final static String SET_PRE = "set";

    private final static String IS_PRE = "is";

    private BeanUtils() {
    }

    /**
     * 反射获取字段值
     */
    public static Object readValue(Object obj, String property) {
        try {
            return getReadMethod(obj.getClass(), property).invoke(obj);
        } catch (Exception e) {
            LOGGER.error(e);
            throw new BeanReflectException("反射获取字段值错误", e);
        }
    }

    /**
     * 获取read方法
     * 找不到对应方法不抛出异常，返回null
     */
    public static Method getReadMethod(Class<?> clazz, String property) {
        if (StringUtils.isEmpty(property)) {
            throw new IllegalArgumentException("property must not empty");
        }
        Method method;
        try {
            method = clazz.getMethod(GET_PRE + toUpperFirstLetter(property));
        } catch (NoSuchMethodException e) {
            method = null;
        }
        if (method != null) {
            return method;
        }
        try {
            method = clazz.getMethod(IS_PRE + toUpperFirstLetter(property));
        } catch (NoSuchMethodException e) {
            LOGGER.error(e);
            throw new BeanReflectException("反射获取方法失败", e);
        }
        return method;
    }

    /**
     * 获取writer方法
     * 找不到对应方法不抛出异常，返回null
     */
    public static Method getWriterMethod(Class<?> clazz, String property, Class<?> propertyType) {
        if (StringUtils.isEmpty(property)) {
            throw new IllegalArgumentException("property must not empty");
        }
        try {
            return clazz.getMethod(SET_PRE + toUpperFirstLetter(property), propertyType);
        } catch (NoSuchMethodException e) {
            LOGGER.error(e);
            throw new BeanReflectException("反射获取方法失败", e);
        }
    }

    public static List<Field> getAllFields(Class clazz, Class stop) {
        ArrayList<Field> fieldList = new ArrayList<>();
        for (; clazz != stop; clazz = clazz.getSuperclass()) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        return fieldList;
    }

    private static String toUpperFirstLetter(String fieldName) {
        return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

}
