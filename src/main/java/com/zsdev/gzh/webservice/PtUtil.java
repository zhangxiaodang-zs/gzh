package com.zsdev.gzh.webservice;

import com.zsdev.gzh.util.Md5Util;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright(C) ShanDongZhiSheng 2020.
 * <p>
 * 毕业之家平台公共方法.
 *
 * @author 张孝党 2020/03/27.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2020/03/27. 张孝党 创建.
 */
@Slf4j
public class PtUtil {

    /**
     * 获取访问token.
     */
    public static String getPtToken(String spam) {

        // 访问token
        StringBuffer token = new StringBuffer();

        token.append(PtConstant.APP_KEY);
        token.append("$");
        token.append(PtConstant.APP_APPSECRET);
        token.append("$");
        token.append(spam);
        log.info("毕业之家平台token计算前的值为：{}", token.toString());

        log.info("------------>{}", Md5Util.encode(token.toString()));

        // MD5加密
        return Md5Util.encode(token.toString());
    }
}
