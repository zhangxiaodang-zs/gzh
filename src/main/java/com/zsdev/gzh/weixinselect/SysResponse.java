package com.zsdev.gzh.weixinselect;

import com.alibaba.fastjson.JSONObject;

/**
 * Copyright(C) ShanDongzhisheng 2019.
 * <p>
 *
 * @author 门海峰 20200327.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 20200327 门海峰 创建.
 */

public class SysResponse {

    // 返回码，默认0000
    private String retCode = "0000";

    // 消息
    private String retMsg = "调用成功";

    public SysResponse() {

    }

    public SysResponse(String retMsg) {
        this.retMsg = retMsg;
    }

    public SysResponse(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    /**
     * 转换为String型的json字符串.
     *
     * @return 字符串.
     */
    public String toJsonString() {

        // 返回报文
        JSONObject response = new JSONObject();
        response.put("retcode", this.retCode);
        response.put("retmsg", this.retMsg);

        return response.toJSONString();
    }
}
