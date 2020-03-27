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
public class WebRequest<T> extends SysRequest<T> {

    // 用户ID
    private String userid = "";

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
