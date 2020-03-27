package com.zsdev.gzh.weixinselect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zsdev.gzh.util.CommonUtil;
import com.zsdev.gzh.util.HttpUtil;
import com.zsdev.gzh.util.StringFormat;
import com.zsdev.gzh.webservice.*;
import com.zsdev.gzh.weixin.WeixinConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
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
@Slf4j
@Service
public class WxSelectService {

    @Autowired
    private WxSelectDao wsdDao;

    private String tbid = "";
    /**
     * 获取用户openid.
     */
    public String getOpenId(String code) throws Exception {

        String openid = "";

        String oaTokenUrl = StringFormat.format(WeixinConstant.OA_TOKEN_URL, WeixinConstant.APPID, WeixinConstant.APPSECRET, code);
        log.info("通过code换取网页授权access_token的url为：" + oaTokenUrl);

        // 发送请求
        JSONObject data = JSONObject.parseObject(HttpUtil.getResponseWithGET(oaTokenUrl));
        log.info("通过code换取网页授权access_token的返回值为：" + data.toJSONString());

        if (data.getString("openid").equals("")) {
            log.error("获取用户openid时出错");
        } else {
            openid = data.getString("openid");
            log.info("获取的openid为{}", openid);
        }

        // 返回
        return openid;
    }

    /**
     * 查询
     *
     * @param requestData*/
    public String queryPaperHistory(HttpServletRequest requestData) {

        HashMap map = SdyfJsonUtil.beanToMap("");
        if (requestData.getParameter("tartindex") != null && !"".equals(requestData.getParameter("tartindex")) && requestData.getParameter("pagesize") != null && !"".equals(requestData.getParameter("pagesize"))) {
            //为防止分页查询出错，把这俩个属性强转为int类型
            map.put("startindex", Integer.parseInt(requestData.getParameter("tartindex")));
            map.put("pagesize", Integer.parseInt(requestData.getParameter("tartindex")));
            map.put("pagingOrNot", "1");
        }
        //创建接收对象
        WebResponse<PaperResponse> web = new WebResponse<>();
        PaperResponse paperResponse = new PaperResponse();
        tbid = requestData.getParameter("tbid");
        //查询这个订单号有没有被查询过
        map.put("tbid",tbid);
        List<Map<String, Object>> lstData = this.wsdDao.tbQuery(map);
        if(lstData.size() <= 0 || null == lstData){
            //本地没有
            // 系统时间戳
            String spam = Long.toString(System.currentTimeMillis()/1000L);
            // 获取访问token
            String token = PtUtil.getPtToken(spam);
            log.info("token为:{}", token);

            // 调用查询接口
            WeixinApiService weixinApiService = new WeixinApiService();
            WeixinApiServiceSoap weixinApiServiceSoap = weixinApiService.getWeixinApiServiceSoap();
            String queryResult = weixinApiServiceSoap.queryPaperReport(PtConstant.APP_KEY, token, spam,
                    tbid,
                    "ZW");

            if(!"".equals(queryResult)){
                JSONObject object = JSON.parseObject(queryResult);
                JSONObject objects = JSON.parseObject(String.valueOf(object.get("data")));
                if(!"检测完成".equals(objects.get("status"))){
                    return new SysErrResponse("还未检测完成").toJsonString();
                }else{
                    this.addHistory(queryResult);
                }
            }else {
                return new SysErrResponse("订单号不存在").toJsonString();
            }
            paperResponse.setPaperlist(queryResult);
        }else{
//            有
            paperResponse.setPaperlist(lstData);
        }
        web.setResponse(paperResponse);

        return JSONObject.toJSON(web).toString();
    }

    /**
     * 添加
     * */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String addHistory(String res) {
        JSONObject object = JSON.parseObject(res);
        JSONObject objects = JSON.parseObject(String.valueOf(object.get("data")));
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap("");
        map.put("id", CommonUtil.getUUid());
        map.put("selecid", CommonUtil.getUUid());
        map.put("addTime", Long.toString(System.currentTimeMillis()/1000L));
        map.put("updTime",Long.toString(System.currentTimeMillis()/1000L));
        if(null != objects.get("url") && !"".equals(objects.get("url"))){
            map.put("paperpath", objects.get("url"));
        }
        if(null != objects.get("title") && !"".equals(objects.get("title"))){
            map.put("papertitle", objects.get("title"));
        }
        if(null != objects.get("author") && !"".equals(objects.get("author"))){
            map.put("paperauthor", objects.get("author"));
        }
        if(null != objects.get("time") && !"".equals(objects.get("time"))){
            map.put("papertime", objects.get("time"));
        }
        if(null != objects.get("status") && !"".equals(objects.get("status"))){
            map.put("paperstatus", objects.get("status"));
        }
        map.put("selecid", CommonUtil.getUUid());
        map.put("tbid", tbid);
        int ress = this.wsdDao.addhistory(map);


        if(ress <= 0){
            return new SysErrResponse("添加失败！").toJsonString();
        }

        return new SysResponse().toJsonString();
    }

    /**
     * 删除接口实现.
     *
     * @return Json字符串数据
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String delHistory(HttpServletRequest requestData) {
        //批量删除  通过，分割  循环
        int result = 0;

        Map<String, String> param = new HashMap<>();
        param.put("id", requestData.getParameter("id"));
        result = this.wsdDao.delhistory(param);

        // 系统时间戳
        String spam = Long.toString(System.currentTimeMillis()/1000L);
        // 获取访问token
        String token = PtUtil.getPtToken(spam);
        log.info("token为:{}", token);

        // 调用查询接口
        WeixinApiService weixinApiService = new WeixinApiService();
        WeixinApiServiceSoap weixinApiServiceSoap = weixinApiService.getWeixinApiServiceSoap();
        String queryResult = weixinApiServiceSoap.deleteWeixinPaperInfo(PtConstant.APP_KEY, token, spam,
                tbid,
                "ZW");
        if (result <= 0) {
            return new SysErrResponse("id:"+requestData.getParameter("id")+" 删除失败，请重新操作！").toJsonString();
        }
        return new SysResponse().toJsonString();
    }
}
