package com.zsdev.gzh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zsdev.gzh.webservice.PaperRequest;
import com.zsdev.gzh.webservice.WebRequest;
import com.zsdev.gzh.webservice.WsdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 银方易网通网商贷Controller.
 *
 * @author 张孝党 2019/03/28.
 * @version V0.0.2.
 * <p>
 * 更新履历： V0.0.1 2019/03/28 张孝党 创建.
 * V0.0.2 2019/04/22 张孝党 方法【index】增加打印请求方IP.
 */
@Slf4j
@RestController
public class WsdController {

    @Autowired
    private WsdService wsdService;

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
    public String wxselect(@RequestBody String requestData, HttpServletResponse response) throws Exception {
        WebRequest<PaperRequest> paperRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<PaperRequest>>() {
        });
        log.info("web项目查询---------->传入的参数为：{}", requestData);
        return wsdService.queryPaperHistory(paperRequest);
    }

    /**
     * 增加
     * */
    @RequestMapping("/addhistory")
    public String addhistory(@RequestBody String requestData, HttpServletResponse response) throws Exception {
        WebRequest<PaperRequest> paperRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<PaperRequest>>() {
        });
        log.info("web项目添加---------->传入的参数为：{}", requestData);
        return wsdService.addHistory(paperRequest);
    }

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
