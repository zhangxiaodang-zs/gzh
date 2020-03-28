var toast = new auiToast();
/*————————————查询报告接口————————————————*/
var IP_url="http://127.0.0.1:9000/";
$("#search").click(function (e) {
    e.preventDefault();
    $(".report_list ul").addClass("di-n");
    toast.loading({
        title:"加载中"
    });
    // 订单ID
    var tbid = $("#order_number").val();//获取输入框值
    if(!tbid){
        $(".report_list .report_have").html("请先输入订单号!");
        toast.hide();
        return false;
    }
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
                if(result.status == "检测完成"){
                    //检测完成按钮显示
                    $(".status_completed").removeClass("di-n");
                }else{
                    $(".status_completed").addClass("di-n");
                }
            }else if(result.retcode == '0001'){
                $(".report_list .report_have").html(result.retmsg);

            }else{
                $(".report_list .report_have").html(result.retmsg);
            }
            //判断电脑还是手机
            function IsPC() {
                var userAgentInfo = navigator.userAgent;
                var Agents = ["Android", "iPhone",
                    "SymbianOS", "Windows Phone",
                    "iPad", "iPod"];
                var flag = true;
                for (var v = 0; v < Agents.length; v++) {
                    if (userAgentInfo.indexOf(Agents[v]) > 0) {
                        flag = false;
                        break;
                    }
                }
                return flag;
            }
            //判断苹果还是安卓
            var u = navigator.userAgent;
            var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
            var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
            $("#down a").click(function (e) {
                e.preventDefault();
                if(IsPC()){
                    window.location.href=result.url;
                }else{
                    if(isiOS){
                        alert("苹果手机请登录电脑端进行下载！");
                        return false;
                    }else if(isAndroid){
                        window.location.href=result.url;
                    }
                }
            });
            toast.hide();
        },
        error: function (errorMsg) {
            toast.hide();
            console.log("未成功"+JSON.stringify(errorMsg));
        }
    });
})

//删除报告
$("#delhistory").click(function (e) {
    e.preventDefault();
    var del_tbid=localStorage.getItem("tbid");
    console.log(del_tbid)
    var data = {
        tbid: del_tbid
    };
    $.ajax({
        type: "POST",
        async: true,
        dataType: "json",
        contentType : "application/json",
        url: IP_url+"gzh/java/delhistory",
        data: JSON.stringify(data),
        success: function (result) {
            toast.loading({
                title:"删除成功",
                duration:2000
            });

            if(result.retcode=='0000'){
                $(".status_completed").addClass('di-n');
                $("#status").html("已删除");
            }
            toast.hide();
        },
        error: function (errorMsg) {
            alert("报告删除异常,请联系客服!");
        }
    });
});
