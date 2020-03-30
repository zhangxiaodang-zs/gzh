package com.zsdev.gzh.weixinselect;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright(C) ShanDongzhisheng 2019.
 * <p>
 *
 * @author 门海峰 20200327.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 20200327 门海峰 创建.
 */
@Getter
@Setter
public class PaperRequest {

    // 订单号
    private String tbid;

    // 标题
    private String title = "";

    // 作者
    private String author = "";

    // 上传时间
    private String time = "";

    // 状态
    private String status = "";

    // 下载地址
    private String url = "";

    // email
    private String email = "";
}
