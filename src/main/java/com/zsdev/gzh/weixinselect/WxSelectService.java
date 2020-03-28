package com.zsdev.gzh.weixinselect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zsdev.gzh.util.HttpUtil;
import com.zsdev.gzh.util.StringFormat;
import com.zsdev.gzh.webservice.PtConstant;
import com.zsdev.gzh.webservice.PtUtil;
import com.zsdev.gzh.webservice.WeixinApiService;
import com.zsdev.gzh.webservice.WeixinApiServiceSoap;
import com.zsdev.gzh.weixin.WeixinConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright(C) ShanDongzhisheng 2019.
 * <p>
 *
 * @author 门海峰 2020/03/27.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 2020/03/27 门海峰 创建.
 */
@Slf4j
@Service
public class WxSelectService {

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
     * 查询.
     */
    public String queryReport(String requestData) {

        // 传入参数
        JSONObject requestJson = JSONObject.parseObject(requestData);
        // 返回值
        JSONObject responseJson = new JSONObject();

        //创建接收对象
        String tbid = requestJson.getString("tbid").toString();
        // 系统时间戳
        String spam = Long.toString(System.currentTimeMillis() / 1000L);
        // 获取访问token
        String token = PtUtil.getPtToken(spam);
        log.info("token为:{}", token);

        // 调用查询接口
        WeixinApiService weixinApiService = new WeixinApiService();
        WeixinApiServiceSoap weixinApiServiceSoap = weixinApiService.getWeixinApiServiceSoap();
        JSONObject queryResult = JSON.parseObject(
                weixinApiServiceSoap.queryPaperReport(
                        PtConstant.APP_KEY,
                        token,
                        spam,
                        tbid,
                        "ZW"));
        log.info("订单号[{}]的查询结果为:\n{}", tbid, queryResult);
        if (!queryResult.getString("status").equals("success")) {
            return new SysErrResponse(queryResult.getString("message")).toJsonString();
        } else {
            JSONObject report = (JSONObject) queryResult.get("data");
            if(report.get("title") != null && !report.get("title").toString().equals("")) {
                // 标题
                responseJson.put("title", report.getString("title"));
                // 作者
                responseJson.put("author", report.getString("author"));
                // 上传时间
                responseJson.put("time", report.getString("time"));
                // 检测状态
                responseJson.put("status", report.getString("status"));
                // 下载地址
                responseJson.put("url", report.getString("url"));
                responseJson.put("retcode", "0000");
                responseJson.put("retmsg", "查询成功");
            } else {
                responseJson.put("retcode", "0001");
                responseJson.put("retmsg", "订单号不存在!");
            }
        }

        // 返回
        return responseJson.toJSONString();
    }


    /**
     * 删除接口实现.
     *
     * @return Json字符串数据
     */
    public String delHistory(String requestData) {
        int result = 0;
        // 传入参数
        JSONObject requestJson = JSONObject.parseObject(requestData);
        // 返回值
        JSONObject responseJson = new JSONObject();
        String tbid = requestJson.getString("tbid").toString();
        // 系统时间戳
        String spam = Long.toString(System.currentTimeMillis() / 1000L);
        // 获取访问token
        String token = PtUtil.getPtToken(spam);
        log.info("token为:{}", token);
        // 调用删除接口
        WeixinApiService weixinApiService = new WeixinApiService();
        WeixinApiServiceSoap weixinApiServiceSoap = weixinApiService.getWeixinApiServiceSoap();
        JSONObject deleteResult = JSON.parseObject(
                weixinApiServiceSoap.deleteWeixinPaperInfo(
                        PtConstant.APP_KEY,
                        token,
                        spam,
                        tbid,
                        "ZW"));
        if ("success".equals(deleteResult.get("status"))) {
            responseJson.put("retcode", "0000");
            responseJson.put("retmsg", "报告删除成功.");
        }else{
            responseJson.put("retcode", "9999");
            responseJson.put("retmsg", "报告删除失败,请联系客服.");
        }
        return responseJson.toJSONString();
    }
}
