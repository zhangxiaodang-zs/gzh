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
public class WebResponse<T> {

    // 响应码
    private String retcode = "0000";

    // 响应内容
    private String retmsg = "调用成功";

    public String getRetcode() {
        return retcode;
    }

    public void setRetcode(String retcode) {
        this.retcode = retcode;
    }

    public String getRetmsg() {
        return retmsg;
    }

    public void setRetmsg(String retmsg) {
        this.retmsg = retmsg;
    }

    // 报文体
    private T response;

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}
