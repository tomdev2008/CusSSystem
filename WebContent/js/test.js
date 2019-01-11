// 控制连接

// $(function () {
    let user_type = GetQueryString("pagetype");
    var nickname = user_type + Math.random();
    let local_url = window.location.host;
    //接收服务器的消息
    socket.onmessage = function (ev) {
        var obj = eval('(' + ev.data + ')');
        iSPageUserName(obj.sendname);// 判断用户是否在列表中
        if(iSUserPanel_1(obj.sendname)){
            addMessage(obj);
        }else{
            // 对消息处理
            // var msg = "'"+ev.data+"'";
            panelNot(obj.sendname,ev.data);
            console.log("当前用户的未打开");
            alert("有新用户接入，请打开面板接收消息");
        }
    }

    function sendMsg(test) {
        // 获取输入内容
        var inputvalue = $('div[value="'+test+'"]').find('[id="inputvalue"]').val();
        // 发送给谁
        var acceptname = test;
        // 获取时间
        var time = new Date().Format("yyyy-MM-dd HH:mm:ss");
        if (false) {  // 判断消息输入框是否为空
            // 消息输入框获取焦点
            // 添加抖动效果
            $('.edui-container').addClass('am-animation-shake');
            setTimeout("$('.edui-container').removeClass('am-animation-shake')", 1000);
        } else {
            //构建一个标准格式的JSON对象
            var obj = JSON.stringify({
                nickname: nickname,
                content: inputvalue,
                date: time,
                acceptname: acceptname, //发送给谁
                sendname : test
            });
            // 把数据进行转换
            var tmp = eval('(' + obj + ')');
            // 把要发送的消息添加到面板中
            addMessage(tmp);
            // 发送消息
            socket.send(obj);
            // 清空消息输入框
            $('div[value="'+test+'"]').find('[id="inputvalue"]').val("");
        }

    }

// });



//把聊天信息追加到聊天页面
function addMessage(msg) {
    // 获取对应的用户面板
    var div_box = $('div[value="'+msg.sendname+'"]');
    var box = div_box.find('[id="msgtmp"]').eq(0).clone(); 	//复制一份模板，取名为box
    box.show();							//设置box状态为显示
    box.find('[ff="nickname"]').html(msg.nickname); //在box中设置昵称
    box.find('[ff="msgdate"]').html(msg.date); 		//在box中设置时间
    box.find('[ff="content"]').html(msg.content); 	//在box中设置内容
    box.appendTo(div_box.find('[id="chatContent"]'));		//把box追加到聊天面板中
    $("#ChatBox div:eq(0)").scrollTop(999999); 	//滚动条移动至最底部
}

//格式化时间
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,//月份
        "d+": this.getDate(),//日
        "H+": this.getHours(),//小时
        "m+": this.getMinutes(),//分
        "s+": this.getSeconds(),//秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S+": this.getMilliseconds()//毫毛
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};

// 获取url参数值
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

