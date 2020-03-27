package com.zsdev.gzh.webservice;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zsdev.gzh.dao.WsdDao;
import com.zsdev.gzh.util.CommonUtil;
import com.zsdev.gzh.util.HttpUtil;
import com.zsdev.gzh.util.StringFormat;
import com.zsdev.gzh.weixin.WeixinConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright(C) ShanDongYinFang 2019.
 * <p>
 * 庆云网贷Service.
 *
 * @author 张孝党 2019/03/28.
 * @version V0.0.1.
 * <p>
 * 更新履历： V0.0.1 2019/03/28 张孝党 创建.
 */
@Slf4j
@Service
public class WsdService {

    @Autowired
    private WsdDao wsdDao;


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

    public String queryPaperHistory(WebRequest<PaperRequest> requestData) {

        HashMap map = SdyfJsonUtil.beanToMap(requestData.getRequest());
        if (requestData.getRequest().getStartindex() != null && !"".equals(requestData.getRequest().getStartindex()) && requestData.getRequest().getPagesize() != null && !"".equals(requestData.getRequest().getPagesize())) {
            //为防止分页查询出错，把这俩个属性强转为int类型
            map.put("startindex", Integer.parseInt(requestData.getRequest().getStartindex()));
            map.put("pagesize", Integer.parseInt(requestData.getRequest().getPagesize()));
            map.put("pagingOrNot", "1");
        }
        //查询这个订单号有没有被查询过
        map.put("tbid",requestData.getRequest().getTbid());
        List<Map<String, Object>> lstData = this.wsdDao.tbQuery(map);
//        if(lstData.size()<0 || null == lstData){
//            //没有
//        }
        //创建接收对象
        WebResponse<PaperResponse> web = new WebResponse<>();
        PaperResponse paperResponse = new PaperResponse();

        paperResponse.setDraw(requestData.getRequest().getDraw());
        paperResponse.setPaperlist(lstData);
        web.setResponse(paperResponse);

        return JSONObject.toJSON(lstData).toString();
    }

    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    @JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
    public String addHistory(WebRequest<PaperRequest> requestData) {
        //对象转换成map集合 并给dao传值
        HashMap map = SdyfJsonUtil.beanToMap("");
        map.put("id", CommonUtil.getUUid());
        map.put("selecid", CommonUtil.getUUid());
        map.put("addtime", Long.toString(System.currentTimeMillis()/1000L));
        map.put("updtime",Long.toString(System.currentTimeMillis()/1000L));

        map.put("operator",requestData.getRequest().getOperator());
        map.put("tbid",requestData.getRequest().getTbid());
        map.put("papertitle",requestData.getRequest().getPapertitle());
        map.put("paperauthor",requestData.getRequest().getPaperauthor());
        map.put("papertime",requestData.getRequest().getPapertime());
        map.put("paperstatus",requestData.getRequest().getPaperstatus());
        map.put("paperpath",requestData.getRequest().getPaperpath());
        int res = this.wsdDao.addhistory(map);


        if(res<=0){
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
    public String delHistory(WebRequest<PaperRequest> requestData) {
        //批量删除  通过，分割  循环
        int result = 0;

        Map<String, String> param = new HashMap<>();
        param.put("id", requestData.getRequest().getId());
        result = this.wsdDao.delhistory(param);
        if (result <= 0) {
            return new SysErrResponse("id:"+requestData.getRequest().getId()+" 删除失败，请重新操作！").toJsonString();
        }
        return new SysResponse().toJsonString();
    }
}
