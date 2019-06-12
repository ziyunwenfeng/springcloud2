package com.example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.test.entity.HttpResult;
import com.example.test.entity.User;
import com.example.test.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {
    private HttpClientUtil httpClientUtil = new HttpClientUtil();
    @Test
    public void contextLoads() {
    }
    @Test
    public void testDoGet(){
        String url = "http://localhost:9005/consumer";
        try {
            HttpResult result = httpClientUtil.doGet(url);
            if(result!=null){
                System.out.println("code :"+ result.getCode());
                System.out.println("context :"+ result.getBody());
            }
            else{
                System.out.println("null :");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoGetbyName(){
        String url = "http://localhost:9005/getByUsername";
        String param = "web";
        Map<String,Object> map = new HashMap<>();
        map.put("username",param);
        try {
            HttpResult result = httpClientUtil.doGet(url,map);
            if(result!=null){
                System.out.println("code :"+ result.getCode());
                System.out.println("context :"+ result.getBody());
            }
            else{
                System.out.println("null :");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoPost(){
        String url = "http://localhost:9005/insertUser";
        String  json = JSON.toJSONString(new User("httpclient"));
        try {
            HttpResult result = httpClientUtil.doPost(url,json);
            if(result!=null){
                System.out.println("code :"+ result.getCode());
                System.out.println("context :"+ result.getBody());
            }
            else{
                System.out.println("null :");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoPut(){
        String url = "http://localhost:9005/updateUser";
        Map<String,Object> map = new HashMap<>();
        map.put("username","httpclient");
        map.put("password","hello");
        try {
            HttpResult result = httpClientUtil.doPut(url,map);
            if(result!=null){
                System.out.println("code :"+ result.getCode());
                System.out.println("context :"+ result.getBody());
            }
            else{
                System.out.println("null :");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoDelete(){
        String url = "http://localhost:9005/deleteUser";
        Map<String,Object> map = new HashMap<>();
        map.put("username","cloud");
        try {
            HttpResult result = httpClientUtil.doDelete(url,map);
            if(result!=null){
                System.out.println("code :"+ result.getCode());
                System.out.println("context :"+ result.getBody());
            }
            else{
                System.out.println("null :");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
