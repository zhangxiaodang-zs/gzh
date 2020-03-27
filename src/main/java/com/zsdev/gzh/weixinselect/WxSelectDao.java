package com.zsdev.gzh.weixinselect;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Copyright(C) ShanDongzhisheng 2019.
 * <p>
 *
 * @author 门海峰 20200327.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 20200327 门海峰 创建.
 */
@Mapper
@Repository
public interface WxSelectDao {

    List<Map<String, Object>> tbQuery(HashMap map);

    /**
     * 添加.
     *
     * @param map 添加信息.
     * @return
     */
    int addhistory(HashMap map);

    /**
     * 删除.
     *
     * @param map 添加信息.
     * @return
     */
    int delhistory(Map<String, String> param);
}
