package com.zsdev.gzh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Slf4j
@CrossOrigin(origins = "*")
@SpringBootApplication
public class GzhApplication {

    public static void main(String[] args) {
        SpringApplication.run(GzhApplication.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "欢迎访问公众号查询接口后台";
    }

    @PostConstruct
    public void init() {

//        // 系统时间戳
//        String spam = Long.toString(System.currentTimeMillis() / 1000L);
//
//        // 获取访问token
//        String token = PtUtil.getPtToken(spam);
//        log.info("token为:{}", token);
//
//        // 调用查询接口
//        WeixinApiService weixinApiService = new WeixinApiService();
//        WeixinApiServiceSoap weixinApiServiceSoap = weixinApiService.getWeixinApiServiceSoap();
//        String queryResult = weixinApiServiceSoap.queryPaperReport(PtConstant.APP_KEY, token, spam,
//                "586701735098682399",
//                "ZW");
//        log.info("查询订单返回的结果为：{}", queryResult);
    }
}
