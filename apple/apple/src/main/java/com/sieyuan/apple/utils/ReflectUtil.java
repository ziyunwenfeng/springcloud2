package com.sieyuan.apple.utils;

import java.lang.reflect.Field;

/**
 * @program: producer
 * @description: reflect util
 * @author: wenfeng
 * @create: 2019-07-12 14:26
 **/
public class ReflectUtil {
    /*
     * description:利用反射火气指定对象的指定属性
     * @param object 目标对象
     * @param fieldName 目标属性
     * @Return: java.lang.Object 目标属性的值
     * @Author: wenfeng
     * @Date: 2019/7/12 14:39
     */
    public static Object getFieldValue(Object object,String fieldName){
        Object result = null;
        Field field = ReflectUtil.getField(object,fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    /*
     * description:利用反射设置指定对象里的指定属性
     * @param object目标字段
     * @param fieldName目标属性
     * @param fieldValue目标值
     * @Return: void
     * @Author: wenfeng
     * @Date: 2019/7/12 14:42
     */
    public static void setFieldValue(Object object, String fieldName, String fieldValue){
        Field field = ReflectUtil.getField(object,fieldName);
        if (field !=  null) {
            try {
                field.setAccessible(true);
                field.set(object,fieldValue);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
    /*
     * description:利用反射获取指定对象的指定属性
     * @param object目标对象
     * @param fieldName目标属性
     * @Return: java.lang.reflect.Field 目标字段
     * @Author: wenfeng
     * @Date: 2019/7/12 14:43
     */
    public static Field getField(Object object,String fieldName){
        Field field = null;
        for(Class<?> clazz = object.getClass();clazz != Object.class; clazz = clazz.getSuperclass()){
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return field;
    }
}
