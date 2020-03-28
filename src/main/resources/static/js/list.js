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

// 分页开始
layui.use(['laypage'], function(){
  //  totalcount = localStorage.getItem('totalcount');
    var laypage = layui.laypage;
    //执行一个laypage实例
    laypage.render({
        elem: 'test1' //注意，这里的 test1 是 ID，不用加 # 号
       // ,count:  totalcount //数据总数，从服务端得到
        ,count:  10
        //,limit:pagesize //每页显示的条数。laypage将会借助 count 和 limit 计算出分页数
        ,limit:5
        ,theme: '#a1cf40'
        ,prev: false
        ,next: '<em>→</em>'
        ,jump: function(obj){
            //console.log("当前页："+obj.curr)//得到当前页
            //console.log("每页显示："+obj.limit+" 条");//每页显示的条数
          //  var curr=obj.curr-1;
           // NewsList(typeid,curr,pagesize)

        }

    });
});

