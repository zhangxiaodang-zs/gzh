package com.zsdev.gzh.weixinselect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright(C) ShanDongzhisheng 2019.
 * <p>
 *
 * @author 门海峰 2020/03/27.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2020/03/27 门海峰 创建.
 */
@Slf4j
@Controller
@CrossOrigin(origins = "*")
public class WxSelectController {

    @Autowired
    private WxSelectService wxSelectService;

    @RequestMapping("/index")
    public String wxcxindex(HttpServletRequest request, Model model) throws Exception {

//        String authCode = request.getParameter("code");
//        log.info("微信获取的code:" + authCode);
//
//        String openid = wsdService.getOpenId(authCode);
//        log.info("获取的openid为{}", openid);
//        //String openid = "123";
//        model.addAttribute("openid", openid);

        // 返回查询页面
        return "index";
    }

    /**
     * 查询
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public String query(@RequestBody String requestData) {

        try {
            log.info("报告查询---------->传入的参数为：{}", requestData);
            return wxSelectService.queryReport(requestData);
        } catch (Exception e) {
            log.error("查询报告时异常..........");
            JSONObject json = new JSONObject();
            json.put("retcode", "9999");
            json.put("retmsg", "报告查询异常,请联系客服!");
            return json.toJSONString();
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delhistory")
    @ResponseBody
    public String delhistory(@RequestBody String requestData) {
        try {
            log.info("报告删除---------->传入的参数为：{}", requestData);
            return wxSelectService.delHistory(requestData);
        } catch (Exception e) {
            log.error("删除报告时异常..........");
            JSONObject json = new JSONObject();
            json.put("retcode", "9999");
            json.put("retmsg", "报告删除异常,请联系客服!");
            return json.toJSONString();
        }
    }

    /**
     * iphone时发送邮件.
     */
    @RequestMapping("/email")
    @ResponseBody
    public String sendMail(@RequestBody String requestData) {

        try {
            log.info("iphone发送邮件接收到的参数为：{}", requestData);
            PaperRequest requestJSON = JSON.parseObject(requestData, new TypeReference<PaperRequest>() {
            });
            return this.wxSelectService.sendMailService(requestJSON);
        } catch (Exception ex) {
            log.error("发送邮件时异常：\n{}", ex.getMessage());
            JSONObject response = new JSONObject();
            response.put("retcode", "9999");
            response.put("retmsg", "邮件发送异常,请联系管理员!");
            return response.toJSONString();
        }
    }
}
