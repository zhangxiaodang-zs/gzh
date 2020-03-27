$(document).ready(function(){
});


/*————————————查询报告接口————————————————*/
var IP_url="http://192.168.10.14:9000/";
$("#search").click(function (e) {
    e.preventDefault();
    var tbid=$("#order_number").val();//获取输入框值
    var data={"tbid":tbid};
    console.log(data);
    $.ajax({
        type: "post",
        async: true, //异步请求
        url: IP_url+"gzh/java/wxselect",
        data: data,
        dataType: "json",
        success: function (result) {
            $(".report_list ul").html('');
            var paperlist=result.response.paperlist;
            if(paperlist){
                for (var i = 0; i <paperlist.length; i++){
                    $(".report_list ul").append(
                        ' <li class="clearfix">'+
                        '  <div class="pull-left list_l">论文标题</div>'+
                        '<div class="pull-left list_r">'+paperlist[i].title+'</div>'+
                        ' </li>'+
                        ' <li class="clearfix">'+
                        '  <div class="pull-left list_l">论文作者</div>'+
                        '<div class="pull-left list_r">'+paperlist[i].author+'</div>'+
                        ' </li>'+
                        ' <li class="clearfix">'+
                        '<div class="pull-left list_l">检测时间</div>'+
                        '<div class="pull-left list_r">'+paperlist[i].time+'</div>'+
                        ' </li>'+
                        ' <li class="clearfix">'+
                        '<div class="pull-left list_l">检测状态</div>'+
                        '<div class="pull-left list_r">'+paperlist[i].status+'</div>'+
                        ' </li>'+
                        ' <li class="clearfix">'+
                        ' <div class="pull-left list_l">操作</div>'+
                        ' <div class="pull-left" style="margin-left: 1.2rem">'+
                        ' <div class="btn1 pull-left">' +
                        '<a href="'+paperlist[i].url+'">下载报告</a>'+
                       // '<a href="'+((paperlist[i].url!=null)?(paperlist[i].url):("#"))+'">下载报告</a>'+
                        '</div>'+
                        '<div class="btn2 pull-left" data-id="'+paperlist[i].tbid+'">删除报告</div>'+
                        '</div>'+
                        ' </li>'
                    )
                }
            }

        },
        error: function (errorMsg) {
            console.log("未成功"+JSON.stringify(errorMsg))
        }
    });
})


//删除报告
$('.report_list ul').on('click', '.btn2', function (e) {
    e.preventDefault();
    var data={"tbid":$(this).attr('data-id')};
    console.log(data)
    $.ajax({
        type: "post",
        async: true, //异步请求
        url: IP_url+"gzh/java/delhistory",
        data: data,
        dataType: "json",
        success: function (result) {
            alert("删除成功");
            window.location.reload();
        },
        error: function (errorMsg) {

        }
    });
});


















// if(isWeiXin()){
//     console.log(" 来自微信内置浏览器");
//     var code=localStorage.getItem("get_code");//获取code
//     if(code){
//         getOpenId(code);//code存在，获取openid
//     }else{
//         getBaseInfos();//否则，获取code
//     }
// }else{
//     console.log("不是来自微信内置浏览器")
//
// }

//判断是否为微信登录
// function isWeiXin(){
//     var ua = window.navigator.userAgent.toLowerCase();
//     if (ua.match(/MicroMessenger/i) == 'micromessenger') {
//         return true;
//     } else {
//         return false;
//     }
// }


/*————————————获取code————————————————*/
/*appid  必须 公众号的唯一标识
redirect_url 必须 授权后跳转页面 请使用urlEncode对链接进行处理
response_type 必须 直接填code
scope 必须 应用授权作用域 后面详细讲解
state 非必须 参数 返回时会是 state=XXXXXXXXX
#wechat_redirect 必须 无论直接打开还是做页面302重定向时候，必须带此参数*/
// var WX_APPID="";
// var ip_url="http://123.232.236.210:9000/";
// function getBaseInfos(){
//     var url_code = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WX_APPID+"&redirect_uri="+ip_url+"gzh/java/wxcxindex"+"&response_type=code&scope=snsapi_base&state=1&connect_redirect=1#wechat_redirect";
//     window.location.href = url_code;//打开这个链接，你的url后面就会跟上code的参数
//     var get_code = getUrlParam("code");
//     localStorage.setItem("code", get_code);//储存code
// }

/*————————————获取openid————————————————*/
// function getOpenId(code) {
//     var data={"code":code};
//     $.ajax({
//         type: "post",
//         contentType: false,
//         processData:false,
//         async: true,
//         // url: userRightUrl + "useredit",
//         url: ip_url+"yfywt/wxcxindex",
//         data: data,
//         dataType: "json",
//         success: function (result) {
//             //返回openid
//             console.log("result:"+result)
//             console.log("获取openid成功")
//         },
//         error: function (errorMsg) {
//             console.log("获取失败")
//         }
//     });
// }


//获取url参数
// function getUrlParam(name) {
//     var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
//     var r = window.location.search.substr(1).match(reg);
//     if (r != null) return unescape(r[2]); return null;
// }





















