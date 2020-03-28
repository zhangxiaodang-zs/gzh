package com.zsdev.gzh.weixinselect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private WxSelectService wsdService;

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
            return wsdService.queryReport(requestData);
        } catch (Exception e) {
            log.error("查询报告时异常..........");
            JSONObject json = new JSONObject();
            json.put("retcode", "9999");
            json.put("retmsg", "查询异常,请联系客服!");
            return json.toJSONString();
        }
    }

    /**
     * 删除
     */
    @RequestMapping("/delhistory")
    public String delhistory(@RequestBody String requestData, HttpServletResponse response) throws Exception {
        WebRequest<PaperRequest> paperRequest = JSON.parseObject(requestData, new TypeReference<WebRequest<PaperRequest>>() {
        });
        log.info("web项目删除---------->传入的参数为：{}", requestData);
        return wsdService.delHistory(paperRequest);
    }

}
