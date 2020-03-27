package com.zsdev.gzh.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 银方易网通网商贷Dao.
 *
 * @author 张孝党 2019/04/15.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/04/15 张孝党 创建.
 */
@Mapper
@Repository
public interface WsdDao {

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
