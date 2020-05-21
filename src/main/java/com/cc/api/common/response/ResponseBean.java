package com.cc.api.common.response;


/**
 * * * * * * * * * * *
 * Here be dragons   *
 * * * * * * * * * * *
 *
 */
public class ResponseBean<T> {
    private String status;
    private String msg;
    private T data;

    public ResponseBean() {
        this.status = ResponseStatus.getStatus(ResponseStatus.SUCCESS);
        this.msg = ResponseStatus.getMsg(ResponseStatus.SUCCESS);
    }

    public ResponseBean(ResponseStatus status) {
        this.status = ResponseStatus.getStatus(status);
        this.msg = ResponseStatus.getMsg(status);
    }

    public ResponseBean(ResponseStatus status, T data) {
        this.status = ResponseStatus.getStatus(status);
        this.msg = ResponseStatus.getMsg(status);
        this.data = data;
    }

    public ResponseBean(ResponseStatus status, String msg) {
        this.status = ResponseStatus.getStatus(status);
        this.msg = msg;
    }

    public ResponseBean(ResponseStatus status, String msg, T data) {
        this.status = ResponseStatus.getStatus(status);
        this.msg = msg;
        this.data = data;
    }

    public Boolean hasError() {
        return !"SUCCESS".equals(this.getStatus());
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}