package com.gd.model;

/**
 * description: Response <br>
 * date: 2022-10-17 15:07 <br>
 * author: DaTao <br>
 * version: 1.0 <br>
 */
public class Response {

    private Integer code;
    private String status;
    private Object data;

    public Response() {
    }

    public Response(Integer code, String status) {
        this.code = code;
        this.status = status;
    }

    public Response(Integer code, String status, Object data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
