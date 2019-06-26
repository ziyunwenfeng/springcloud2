package com.example.test.utils;

import java.io.*;

/**
 * @program: test
 * @description: object to byte and byte to object
 * @author: wenfeng
 * @create: 2019-06-26 17:24
 **/
public class BeanUtils {
    private BeanUtils(){}
    /*
     * description: Object To bytes[]
     * @param object
     * @Return: byte[]
     * @Author: wenfeng
     * @Date: 2019/6/26 17:25
     */
    public static byte[] ObjectToByte(Object object){
        byte[] bytes ;
        ByteArrayOutputStream bao = null;
        ObjectOutputStream oo = null;
        try {
            bao = new ByteArrayOutputStream();
            oo = new ObjectOutputStream(bao);
            oo.writeObject(object);
            bytes = bao.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (oo != null){
                try {
                    oo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bao != null){
                try {
                    bao.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
     * description: bytes to object
     * @param bytes
     * @Return: java.lang.Object
     * @Author: wenfeng
     * @Date: 2019/6/26 17:37
     */
    public static Object BytesToObject(byte[] bytes){
        Object object = null;
        ByteArrayInputStream bai = null;
        ObjectInputStream oi = null;
        try {
            bai = new ByteArrayInputStream(bytes);
            oi = new ObjectInputStream(bai);
            object = oi.readObject();
            return object;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (bai != null)
                    bai.close();
                if(oi != null)
                    oi.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
