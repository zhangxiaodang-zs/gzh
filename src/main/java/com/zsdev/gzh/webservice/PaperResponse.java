package com.zsdev.gzh.webservice;

import java.util.List;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * WEB端机构信息返回报文.
 *
 * @author 门海峰 2020/03/17.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2020/03/17 门海峰 创建.
 */
public class PaperResponse {

    // 请求次数
    private String draw;

    // 总条数（不是本页的条数，是总条数）
    private String totalcount;

    //返回角色信息集合
    private Object paperlist;

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public Object getPaperlist() {
        return paperlist;
    }

    public void setPaperlist(Object paperlist) {
        this.paperlist = paperlist;
    }

    @Override
    public String toString() {
        return "PaperResponse{" +
                "draw='" + draw + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", papertlist=" + paperlist +
                '}';
    }
}
