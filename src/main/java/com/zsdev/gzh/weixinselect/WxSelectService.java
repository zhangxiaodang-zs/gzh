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

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

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
            if (report.get("title") != null && !report.get("title").toString().equals("")) {
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
        } else {
            responseJson.put("retcode", "9999");
            responseJson.put("retmsg", "报告删除失败,请联系客服.");
        }
        return responseJson.toJSONString();
    }

    /**
     * 发送邮件.
     */
    public String sendMailService(PaperRequest requestData) throws Exception {

        String myEmailAccount = "cnki_vip@163.com";
        String myEmailPassword = "YDRAXVIYNDYUJSEQ";
        String subject = "欢迎使用学术不端文献检测系统,检测已完成";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");
        String smtpPort = "465";
        props.setProperty("mail.smtp.port", smtpPort);
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        // 创建一封邮件
        MimeMessage message = createMimeMessage(session, myEmailAccount, subject, requestData);

        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

        JSONObject responseData = new JSONObject();
        responseData.put("retcode", "0000");
        responseData.put("retmsg", "邮件发送成功!");

        return responseData.toJSONString();
    }

    private static MimeMessage createMimeMessage(Session session, String sendMail, String subject, PaperRequest requestData) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, subject, "UTF-8"));

        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(requestData.getEmail(), "", "UTF-8"));

        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(subject, "UTF-8");

        // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        //message.setContent("XXXXXXXXXXXX", "text/html;charset=UTF-8");

        StringBuffer msgText = new StringBuffer();
        msgText.append("论文标题：" + requestData.getTitle() + "<br>");
        msgText.append("论文作者：" + requestData.getAuthor() + "<br>");
        msgText.append("检测时间：" + requestData.getTime() + "<br>");
        msgText.append("检测状态：" + requestData.getStatus() + "<br>");
        msgText.append("<a href=\"" + requestData.getUrl() + "\">下载报告</a>");

        MimeBodyPart text = new MimeBodyPart();
        text.setContent(msgText.toString(), "text/html;charset=UTF-8");

        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.setSubType("mixed");

        message.setContent(mm);

        // 6. 设置发件时间
        message.setSentDate(new Date());

        // 7. 保存设置
        message.saveChanges();

        return message;
    }
}
