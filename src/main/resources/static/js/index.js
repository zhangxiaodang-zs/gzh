var toast = new auiToast();
var dialog = new auiDialog({})

/*————————————查询报告接口————————————————*/
//var IP_url="http://127.0.0.1:9000/";
var IP_url="http://www.biye.com.cn/";
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
    console.log(tbid);
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
                $(".report_list ul").removeClass("di-n");
                $(".report_list .report_have").addClass("di-n");
                $("#title").html(result.title);
                $("#author").html(result.author);
                $("#time").html(result.time);
                if(result.status == "等待检测中") {
                    $("#status").html("正在检测中");
                } else {
                    $("#status").html(result.status);
                }
                $("#down a").attr("href",result.url);
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
                       // alert("因苹果保护隐私请登录网页去下载！");
                        send_mailbox();//发送到邮箱

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
});

//发送到邮箱
function send_mailbox(){
    var title=$("#title").html();
    var author=$("#author").html();
    var time=$("#time").html();
    var status=$("#status").html();
    var url=$("#down a").attr("href");
    dialog.alert({
        title:"发送到邮箱",
        msg:'这里是内容',
        input:true, //是否有input输入框
        buttons:['取消','确定']
    },function(ret){
        if(ret.buttonIndex==2){
            //正则判断邮箱是否正确
             if(!checkEmail(ret.text)){
                  alert("请输入正确的邮箱地址");
                  return false;
             }
            dialog.close();
            var data = {
                "title":title,
                "author":author,
                "time":time,
                "status":status,
                "url":url,
                "email":ret.text
            }
            toast.loading({
                title:"发送中……"
            });
            $.ajax({
                type: "POST",
                async: true,
                dataType: "json",
                contentType : "application/json",
                url: IP_url+"/gzh/java/email",
                data: JSON.stringify(data),
                success: function (result) {
                    if(result.retcode=='0000'){
                        dialog.alert({
                            title:"提示",
                            msg: "发送成功,请前往邮箱查看",
                            buttons:['确定']
                        });
                    }else{
                        dialog.alert({
                            title:"提示",
                            msg: result.retmsg,
                            buttons:['确定']
                        });
                    }
                    toast.hide();
                },
                error: function (errorMsg) {
                    alert(errorMsg);
                }
            });
        }

    })
}


function checkEmail(str){
    var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
    if(re.test(str)){
        return true;
    }else{
        return false;
    }
}


window.alert = function(name){
    var iframe = document.createElement("IFRAME");
    iframe.style.display="none";
    iframe.setAttribute("src", 'data:text/plain');
    document.documentElement.appendChild(iframe);
    window.frames[0].window.alert(name);
    iframe.parentNode.removeChild(iframe);
};

Window.prototype.alert = function(){
    //创建一个大盒子
    var box = document.createElement("div");
    //创建一个关闭按钮
    var button = document.createElement("button");
    //定义一个对象保存样式
    var boxName = {
        width:"500px",
        height:"180px",
        backgroundColor:"#f8f8f8",
        border:"1px solid #ccc",
        position:"absolute",
        top:"50%",
        left:"50%",
        margin:"-90px 0 0 -250px",
        zIndex:"999",
        textAlign:"center",
        lineHeight:"180px"
    }
    //给元素添加元素
    for(var k in boxName){
        box.style[k] = boxName[k];
    }
    //把创建的元素添加到body中
    document.body.appendChild(box);
    //把alert传入的内容添加到box中
    if(arguments[0]){
        box.innerHTML = arguments[0];
    }
    button.innerHTML = "关闭";
    //定义按钮样式
    var btnName = {
        border:"1px solid #ccc",
        backgroundColor:"#fff",
        width:"70px",
        height:"30px",
        textAlign:"center",
        lineHeight:"30px",
        outline:"none",
        position:"absolute",
        bottom:"10px",
        right:"20px",
    };
    for(var j in btnName){
        button.style[j] = btnName[j];
    }
    //把按钮添加到box中
    box.appendChild(button);
    //给按钮添加单击事件
    button.addEventListener("click",function(){
        box.style.display = "none";
    })
};

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
            if(result.retcode=='0000'){
                $(".status_completed").addClass('di-n');
                $("#status").html("已删除");

                // toast.success({
                //     title:"删除成功",
                //     duration:2000
                // });
            }
            //toast.hide();
        },
        error: function (errorMsg) {
            alert("报告删除异常,请联系客服!");
        }
    });
});
