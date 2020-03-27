package com.zsdev.gzh.weixinselect;

/**
 * Copyright(C) ShanDongzhisheng 2019.
 * <p>
 *
 * @author 门海峰 20200327.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 20200327 门海峰 创建.
 */
public abstract class SysRequest<T> {

    // token
    private String token = "";

    // 时间戳
    private String timestamp = "";

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // 报文体
    private T request;

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }
}
