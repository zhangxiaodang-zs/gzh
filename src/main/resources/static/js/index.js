var toast = new auiToast();
/*————————————查询报告接口————————————————*/
var IP_url="http://192.168.10.14:9000";
$("#search").click(function (e) {
    e.preventDefault();
    $(".report_list ul").addClass("di-n");
    toast.loading({
        title:"加载中"
    });
    // 订单ID
    var tbid = $("#order_number").val();//获取输入框值
    console.log(tbid)
    localStorage.setItem("tbid",tbid);
    var data = {
        tbid: tbid
    };
    $.ajax({
        type: "POST",
        async: true,
        dataType: "json",
        contentType : "application/octet-stream",
        url: IP_url + "/gzh/java/query",
        data: JSON.stringify(data),
        success: function (result) {
            if(result.retcode=='0000'){
                console.log("请求成功")
                $(".report_list ul").removeClass("di-n");
                $(".report_list .report_have").addClass("di-n");
                $("#title").html(result.title);
                $("#author").html(result.author);
                $("#time").html(result.time);
                $("#status").html(result.status);
                if(result.status=="检测完成"){
                    $(".status_completed").removeClass("di-n");//检测完成按钮显示
                }else{
                    $(".status_completed").addClass("di-n")
                }

            }else if(result.retcode=='0001'){
                $(".report_list .report_have").html(result.retmsg)

            }else{
                $(".report_list .report_have").html("其他异常，请联系客服!")
            }
            //判断苹果还是安卓
            var u = navigator.userAgent;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
            $("#down a").click(function (e) {
                e.preventDefault();
                if(isiOS){
                    alert("苹果手机请登录电脑端进行下载！")
                    return false;
                }else if(isAndroid){
                    window.location.href=result.url;
                }



            })
            toast.hide();
        },
        error: function (errorMsg) {
            toast.hide();
            console.log("未成功"+JSON.stringify(errorMsg))
        }
    });
})

//删除报告
$("#delhistory").click(function (e) {
    e.preventDefault();
    var del_tbid=localStorage.getItem("tbid");
    console.log(del_tbid)
    var data={tbid:del_tbid};
    $.ajax({
        type: "post",
        async: true, //异步请求
        url: IP_url+"gzh/java/delhistory",
        data: JSON.stringify(data),
        dataType: "json",
        success: function (result) {
           // alert("删除成功");
            toast.custom({
                title:"删除成功",
                duration:2000
            });
            if(result.retcode=='0000'){
                $(".status_completed").addClass('di-n');
                $("#status").html("已删除");

            }

        },
        error: function (errorMsg) {

        }
    });
})


















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





















