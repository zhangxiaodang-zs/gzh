$(document).ready(function(){
    // isWeiXin();
  //  getBaseInfos();
});




if(isWeiXin()){
    console.log(" 来自微信内置浏览器");
    var code=localStorage.getItem("get_code");//获取code
    if(code){
        getOpenId(code);//code存在，获取openid
    }else{
        getBaseInfos();//否则，获取code
    }
}else{
    console.log("不是来自微信内置浏览器")

}

//判断是否为微信登录
function isWeiXin(){
    var ua = window.navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return true;
    } else {
        return false;
    }
}


/*————————————获取code————————————————*/
/*appid  必须 公众号的唯一标识
redirect_url 必须 授权后跳转页面 请使用urlEncode对链接进行处理
response_type 必须 直接填code
scope 必须 应用授权作用域 后面详细讲解
state 非必须 参数 返回时会是 state=XXXXXXXXX
#wechat_redirect 必须 无论直接打开还是做页面302重定向时候，必须带此参数*/
var WX_APPID="";
function getBaseInfos(){
    var url_code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WX_APPID+"&redirect_uri="+当前页面url+"&response_type=code&scope=snsapi_base&state=1&connect_redirect=1#wechat_redirect";
    window.location.href = url_code;//打开这个链接，你的url后面就会跟上code的参数
    var get_code = getUrlParam("code");
    localStorage.setItem("code", get_code);//储存code
}

/*————————————获取openid————————————————*/
function getOpenId(code) {
    var data={"code":code};
    $.ajax({
        type: "post",
        contentType: false,
        processData:false,
        async: true,
        // url: userRightUrl + "useredit",
        url: "http://192.168.10.14:8090/yfywt/wxcxindex",
        data: data,
        dataType: "json",
        success: function (result) {
            //返回openid
            console.log("result:"+result)
            console.log("获取openid成功")
        },
        error: function (errorMsg) {
            console.log("获取失败")
        }
    });


}


/*————————————查询报告接口————————————————*/
$("#search").click(function () {
    console.log($("#order_number").val())
    var data={"tbid":$("#order_number").val()};
    $.ajax({
        type: "post",
        contentType: false,
        processData:false,
        async: true,           //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        // url: userRightUrl + "useredit",    //请求发送到TestServlet处
        url: "http://192.168.10.14:8090/yfywt/wxselect",    //请求发送到TestServlet处
        data: data,
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            console.info("result:" + JSON.stringify(result));
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

        }
    });
})




/*————————————微信授权————————————————*/
// var center = {
//     init: function(){
//     },
//     enterWxAuthor: function(){
//         var wxUserInfo = localStorage.getItem("wxUserInfo");
//         if (!wxUserInfo) {
//             var code = common.getUrlParameter('code');
//             if (code) {
//                 // common.getWxUserInfo();
//                 getWxUserInfo();
//                 center.init();
//             }else{
//                 //没有微信用户信息，没有授权-->> 需要授权，跳转授权页面
//                 window.location.href = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid='+ WX_APPID +'&redirect_uri='+ window.location.href +'&response_type=code&scope=snsapi_userinfo#wechat_redirect';
//             }
//         }else{
//             center.init();
//         }
//     }
// }
//
// //授权后获取用户的基本信息
//  function getWxUserInfo(par){
//     var code = common.getUrlParameter("code");
//     if (par) code = par;
//     $.ajax({
//         async: false,
//         data: {code:code},
//         type : "GET",
//         url : WX_ROOT + "wechat/authorization",
//         success : function(json) {
//             if (json){
//                 try {
//                     //保证写入的wxUserInfo是正确的
//                     var data = JSON.parse(json);
//                     if (data.openid) {
//                         localStorage.setItem('wxUserInfo',json);//写缓存--微信用户信息
//                     }
//                 } catch (e) {
//                     // TODO: handle exception
//                 }
//             }
//         }
//     });
// }





//获取url参数
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}





















