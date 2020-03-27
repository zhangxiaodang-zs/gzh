/*————————————rem布局————————————————*/
(function (doc, win) {
    var docEl = doc.documentElement,
        // 手机旋转事件,大部分手机浏览器都支持 onorientationchange 如果不支持，可以使用原始的 resize
        resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
        recalc = function () {
            //clientWidth: 获取对象可见内容的宽度，不包括滚动条，不包括边框
            var clientWidth = docEl.clientWidth;
            if (!clientWidth) return;
            docEl.style.fontSize = 100*(clientWidth / 750) + 'px';
        };

    recalc();
    //判断是否支持监听事件 ，不支持则停止
    if (!doc.addEventListener) return;
    //注册翻转事件
    win.addEventListener(resizeEvt, recalc, false);

})(document, window);


//格式化时间
function dateTimeFormat(datetime){
    if(datetime == "" || datetime.length < 14) return datetime;
    return datetime.substr(0, 4) + "-" + datetime.substr(4, 2) + "-" +
        datetime.substr(6, 2) + " " + datetime.substr(8, 2) + ":" +
        datetime.substr(10, 2) + ":" + datetime.substr(12, 2);
}
//返回
function go_back() {
    history.go(-1)
}