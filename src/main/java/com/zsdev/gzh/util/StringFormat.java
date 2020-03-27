package com.zsdev.gzh.util;

/**
 * Copyright(C) ShanDongzhisheng 2019.
 * <p>
 *
 * @author 门海峰 20200327.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 20200327 门海峰 创建.
 */
public class StringFormat {

    /**
     * 模仿C#格式化字符串
     */
    public static String format(String str, String... args) {
        for (int i = 0; i < args.length; i++) {
            str = str.replaceFirst("\\{" + i + "\\}", args[i]);
        }
        return str;
    }
}
