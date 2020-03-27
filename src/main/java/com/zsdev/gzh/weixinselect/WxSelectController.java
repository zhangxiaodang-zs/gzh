package com.zsdev.gzh.weixinselect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

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
@RestController
public class WxSelectController {

    @Autowired
    private WxSelectService wsdService;

    private String appID = "wxf7fe4ee1579d1e63";
    private String appSecret = "541aae9e7ccc8809f2931e923836f246";

    @RequestMapping("/wxcxindex")
    public String wxcxindex(HttpServletRequest request, Model model) throws Exception {

        String authCode = request.getParameter("code");
        log.info("微信获取的code:" + authCode);

        String openid = wsdService.getOpenId(authCode);
        log.info("获取的openid为{}", openid);
        //String openid = "123";
        model.addAttribute("openid", openid);

        // 返回业务预约主页
        return "gzh/index";
    }
    /**
     * 查询
     * */
    @RequestMapping("/wxselect")
    public String wxselect(HttpServletRequest requestData, HttpServletResponse response) throws Exception {
        log.info("web项目查询---------->传入的参数为：{}", requestData);
        return wsdService.queryPaperHistory(requestData);
    }

//    /**
//     * 增加
//     * */
//    @RequestMapping("/addhistory")
//    public String addhistory(@RequestBody String requestData, HttpServletResponse response) throws Exception {
//        WebRequest<PaperRequest> paperRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<PaperRequest>>() {
//        });
//        log.info("web项目添加---------->传入的参数为：{}", requestData);
//        return wsdService.addHistory(paperRequest);
//    }

    /**
     * 删除
     * */
    @RequestMapping("/delhistory")
    public String delhistory(@RequestBody String requestData, HttpServletResponse response) throws Exception {
        WebRequest<PaperRequest> paperRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<PaperRequest>>() {
        });
        log.info("web项目删除---------->传入的参数为：{}", requestData);
        return wsdService.delHistory(paperRequest);
    }

}
