package com.example.order_management.utils;



public class Response {

    private String remarks;
    private Object data;

    public static Response getInstance() {
        return new Response();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}