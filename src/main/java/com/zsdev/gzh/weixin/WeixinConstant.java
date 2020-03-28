package com.zsdev.gzh.weixin;

public class WeixinConstant {

    public static final String TOKEN = "61ed0148702211ea9b2a00163e065b3d";

    public static final String APPID = "wx180c84f487eff237";

    public static final String APPSECRET = "0a656b94ede9230b555ce915d447094c";

    // 获取url接口
    public static final String GET_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";

    // 发送消息接口.
    public static final String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token={0}";

    public static final String OA_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={0}&secret={1}&code={2}&grant_type=authorization_code";
}
