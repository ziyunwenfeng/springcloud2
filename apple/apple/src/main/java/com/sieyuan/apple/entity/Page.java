package com.sieyuan.apple.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: producer
 * @description: 用于分页
 * @author: wenfeng
 * @create: 2019-07-12 14:47
 **/
public class Page<T> {
     private int pageNo;
     private int pageSize;
     private int totalPage;
     private int totalRecords;
     private List<T> results;
     private Map<String, Object> params = new HashMap<>();

    public Page() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        int totalPage = totalRecords/pageSize==0?totalRecords/pageSize:totalRecords/getPageSize()+1;
        this.setTotalPage(totalPage);
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
