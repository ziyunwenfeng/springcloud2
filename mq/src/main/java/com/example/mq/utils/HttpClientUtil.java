package com.example.mq.utils;

import com.example.mq.entity.HttpResult;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private CloseableHttpClient httpClient = HttpClients.createDefault();


    public HttpResult doGet(String url,Map<String,Object> map) throws Exception{
        URIBuilder builder = new URIBuilder(url);
        if (map != null) {
            for(Map.Entry<String,Object> entry : map.entrySet()){
                builder.setParameter(entry.getKey(),entry.getValue().toString());
            }
        }
        HttpGet httpGet = new HttpGet(builder.build());
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        HttpResult result ;
        if(response.getEntity() != null){
            result = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(),"UTF-8"));
        } else {
            result = new HttpResult(response.getStatusLine().getStatusCode(),"");
        }
        return result;
    }

    public HttpResult doGet(String url) throws Exception{
        HttpResult result = this.doGet(url,null);
        return result;
    }

    public HttpResult doPost(String url,Map<String,Object> map) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        if(map != null){
            List<NameValuePair> params = new LinkedList<>();
            for(Map.Entry<String,Object> entry : map.entrySet()){
                params.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"UTF-8");
            httpPost.setEntity(formEntity);
    }
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        HttpResult result ;
        if(response.getEntity() != null){
            result = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(),"UTF-8"));
        } else {
            result = new HttpResult(response.getStatusLine().getStatusCode(),"");
        }
        return result;
    }
    public HttpResult doPost(String url,String json) throws Exception{
        HttpPost httpPost = new HttpPost(url);
        StringEntity requestEntity = new StringEntity(json,"utf-8");
        requestEntity.setContentEncoding("UTF-8");
        httpPost.setHeader("Content-type", "application/json");
        httpPost.setEntity(requestEntity);
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        HttpResult result ;
        if(response.getEntity() != null){
            result = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(),"UTF-8"));
        } else {
            result = new HttpResult(response.getStatusLine().getStatusCode(),"");
        }
        return result;
    }

    public HttpResult doPost(String url) throws Exception{
//        return this.doPost(url,null);
        return null;
    }

    public HttpResult doPut(String url,Map<String,Object> map) throws Exception{
        HttpPut httpPut = new HttpPut(url);
        if(map != null){
            List<NameValuePair> params = new ArrayList<>();
            for(Map.Entry<String,Object> entry : map.entrySet()){
                params.add(new BasicNameValuePair(entry.getKey(),entry.getValue().toString()));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"UTF-8");
            httpPut.setEntity(formEntity);
        }
        CloseableHttpResponse response = this.httpClient.execute(httpPut);
        HttpResult result ;
        if(response.getEntity() != null){
            result = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(),"UTF-8"));
        } else {
            result = new HttpResult(response.getStatusLine().getStatusCode(),"");
        }
        return result;
    }

    public HttpResult doDelete(String url,Map<String,Object> map) throws Exception{
        URIBuilder builder = new URIBuilder(url);
        if (map != null) {
            for(Map.Entry<String,Object> entry : map.entrySet()){
                builder.setParameter(entry.getKey(),entry.getValue().toString());
            }
        }
        HttpDelete httpGet = new HttpDelete(builder.build());
        CloseableHttpResponse response = this.httpClient.execute(httpGet);
        HttpResult result ;
        if(response.getEntity() != null){
            result = new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(),"UTF-8"));
        } else {
            result = new HttpResult(response.getStatusLine().getStatusCode(),"");
        }
        return result;
    }

}
