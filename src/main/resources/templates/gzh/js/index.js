$(document).ready(function(){
     console.log("加载成功");
    center.enterWxAuthor();// 微信授权
});

/*————————————查询报告接口————————————————*/
$("#search").click(function () {
    console.log($("#order_number").val())
    var datas = '{id:"22222"}';
    $.ajax({
        type: "post",
        contentType: false,
        processData:false,
        async: true,           //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        // url: userRightUrl + "useredit",    //请求发送到TestServlet处
        url:"http://127.0.0.1:8090/yfywt/wxselect",
        data:sendMessageEdit('default',datas),
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            console.info("userEdit:" + JSON.stringify(result));
            $(".report_list ul").append(
                ' <li class="clearfix">'+
                    '  <div class="pull-left list_l">论文标题</div>'+
                    '<div class="pull-left list_r">从语素教学角度浅谈对韩汉语词汇 教学</div>'+
                ' </li>'+
                ' <li class="clearfix">'+
                    '  <div class="pull-left list_l">论文作者</div>'+
                    '<div class="pull-left list_r">母丹丹</div>'+
                ' </li>'+
                ' <li class="clearfix">'+
                    '<div class="pull-left list_l">检测时间</div>'+
                    '<div class="pull-left list_r">2020-2-26 11:21:39</div>'+
                ' </li>'+
                ' <li class="clearfix">'+
                    '<div class="pull-left list_l">检测状态</div>'+
                    '<div class="pull-left list_r">检测完成</div>'+
                ' </li>'
            )
        },
        error: function (errorMsg) {
            console.info("userEdit-error:" + JSON.stringify(errorMsg));
            userInfoEditEnd(false, "", USEREDIT);
        }
    });
})
function sendMessageEdit(type, data){
    var head, request;
    head = msgHeadMake(type);
    switch(type){
        case LOGIN:
            head["token"] = "";
            head["userid"] = data[0];
            request = {
                "passwd":data[1]
            };
            break;
        default :
            request = data;
            break;
    }
    request = {
        "request":request
    };
    request = $.extend({},request,head);
    var oJson = request;
    // console.info(oJson);
    return JSON.stringify(oJson);
}
/*————————————微信授权————————————————*/
var center = {
    init: function(){
    },
    enterWxAuthor: function(){
        var wxUserInfo = localStorage.getItem("wxUserInfo");
        if (!wxUserInfo) {
            var code = common.getUrlParameter('code');
            if (code) {
                // common.getWxUserInfo();
                getWxUserInfo();
                center.init();
            }else{
                //没有微信用户信息，没有授权-->> 需要授权，跳转授权页面
                window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid='+ WX_APPID +'&redirect_uri='+ window.location.href +'&response_type=code&scope=snsapi_userinfo#wechat_redirect';
            }
        }else{
            center.init();
        }
    }
}

//授权后获取用户的基本信息
 function getWxUserInfo(par){
    var code = common.getUrlParameter("code");
    if (par) code = par;
    $.ajax({
        async: false,
        data: {code:code},
        type : "GET",
        url : WX_ROOT + "wechat/authorization",
        success : function(json) {
            if (json){
                try {
                    //保证写入的wxUserInfo是正确的
                    var data = JSON.parse(json);
                    if (data.openid) {
                        localStorage.setItem('wxUserInfo',json);//写缓存--微信用户信息
                    }
                } catch (e) {
                    // TODO: handle exception
                }
            }
        }
    });
}

//getUrlParam获取url参数
// function getUrlParam(name) {
//     var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
//     var r = window.location.search.substr(1).match(reg);
//     if (r != null) return unescape(r[2]); return null;
// }





















