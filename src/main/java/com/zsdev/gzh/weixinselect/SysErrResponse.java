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
public class SysErrResponse extends SysResponse {

    /**
     * 构造方法,默认为异常.
     */
    public SysErrResponse(String errMsg) {
        super("9999", errMsg);
    }

    /**
     * 构造方法,默认为异常.
     */
    public SysErrResponse(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}
