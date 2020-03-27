package com.zsdev.gzh.weixin;

public class WeixinConstant {

    public static final String TOKEN = "38f319bbad2644c88507c7c4ee506269";

    public static final String APPID = "wx89b07b5e5e29176d";

    public static final String APPSECRET = "8db7cfaa6408292e76964a10eb964679";

    // 获取url接口
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

    // 发送消息接口.
    public static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";

    public static final String OA_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
}
