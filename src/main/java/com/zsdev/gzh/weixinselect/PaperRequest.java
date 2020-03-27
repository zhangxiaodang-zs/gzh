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
public class PaperRequest {

    private String id;

    private String selecid;

    private String tbid;

    private String papertitle;

    private String paperauthor;

    private String papertime;

    private String paperstatus;

    private String paperpath;

    private String addtime;

    private String updtime;

    private String operator;

    private String currentpage;

    private String startindex;

    private String pagesize;

    private String draw;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSelecid() {
        return selecid;
    }

    public void setSelecid(String selecid) {
        this.selecid = selecid;
    }

    public String getTbid() {
        return tbid;
    }

    public void setTbid(String tbid) {
        this.tbid = tbid;
    }

    public String getPapertitle() {
        return papertitle;
    }

    public void setPapertitle(String papertitle) {
        this.papertitle = papertitle;
    }

    public String getPaperauthor() {
        return paperauthor;
    }

    public void setPaperauthor(String paperauthor) {
        this.paperauthor = paperauthor;
    }

    public String getPapertime() {
        return papertime;
    }

    public void setPapertime(String papertime) {
        this.papertime = papertime;
    }

    public String getPaperstatus() {
        return paperstatus;
    }

    public void setPaperstatus(String paperstatus) {
        this.paperstatus = paperstatus;
    }

    public String getPaperpath() {
        return paperpath;
    }

    public void setPaperpath(String paperpath) {
        this.paperpath = paperpath;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getUpdtime() {
        return updtime;
    }

    public void setUpdtime(String updtime) {
        this.updtime = updtime;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(String currentpage) {
        this.currentpage = currentpage;
    }

    public String getStartindex() {
        return startindex;
    }

    public void setStartindex(String startindex) {
        this.startindex = startindex;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getDraw() {
        return draw;
    }

    public void setDraw(String draw) {
        this.draw = draw;
    }

    @Override
    public String toString() {
        return "PaperRequest{" +
                "id='" + id + '\'' +
                ", selecid='" + selecid + '\'' +
                ", tbid='" + tbid + '\'' +
                ", papertitle='" + papertitle + '\'' +
                ", paperauthor='" + paperauthor + '\'' +
                ", papertime='" + papertime + '\'' +
                ", paperstatus='" + paperstatus + '\'' +
                ", paperpath='" + paperpath + '\'' +
                ", addtime='" + addtime + '\'' +
                ", updtime='" + updtime + '\'' +
                ", operator='" + operator + '\'' +
                ", currentpage='" + currentpage + '\'' +
                ", startindex='" + startindex + '\'' +
                ", pagesize='" + pagesize + '\'' +
                ", draw='" + draw + '\'' +
                '}';
    }
}
