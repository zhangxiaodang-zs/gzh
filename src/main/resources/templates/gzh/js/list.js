$(document).ready(function(){
    console.log("加载成功")
    //juery_list();
});

/*————————————查询记录接口————————————————*/
function juery_list() {
    $.ajax({
        type: "post",
        contentType: false,
        processData:false,
        async: true,           //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: userRightUrl + "useredit",    //请求发送到TestServlet处
        data: data,
        dataType: "json",        //返回数据形式为json
        success: function (result) {
            console.info("userEdit:" + JSON.stringify(result));
            $("#record_list ul").append(
                ' <li class="record_list_box clearfix">'+
                    ' <div class="pull-left" style="width: 13%">'+
                        '<div class="number">1</div>'+
                    ' </div>'+
                    ' <div class="pull-left" style="width: 64%">'+
                        '<div class="title">从语素教学角度浅谈对韩汉语词汇教学</div>'+
                        '<div class="author">母丹丹</div>'+
                        '<div class="status">监测完成</div>'+
                        ' <div class="time">2020-2-26 11:21:39</div>'+
                    ' </div>'+
                    ' <div class="pull-right">'+
                        ' <div class="report_down">下载报告</div>'+
                        ' <div class="report_del">删除报告</div>'+
                    ' </div>'+
                ' </li>'
            )

        },
        error: function (errorMsg) {
            console.info("userEdit-error:" + JSON.stringify(errorMsg));

        }
    });
}

