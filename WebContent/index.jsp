<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户界面</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="alternate icon" type="image/png" href="favicon.png">
    <link rel="stylesheet" href="css/amazeui.min.css" />

    <script src="js/jquery.min.js"></script>
    <script src="js/amazeui.min.js"></script>
</head>

<body>
    <header class="am-topbar am-topbar-fixed-top">
        <div class="am-container">
            <h1 class="am-topbar-brand">
                <a href="#">客服</a>
            </h1>
            <div class="am-collapse am-topbar-collapse" id="collapse-head">
                <ul class="am-nav am-nav-pills am-topbar-nav">
                </ul>

                <div class="am-topbar-right">
                    <button class="am-btn am-btn-primary am-topbar-btn am-btn-sm" onclick="windowclose()">结束当前聊天</button>
                </div>
            </div>
        </div>
    </header>

    <div id="main">
        <!-- 聊天内容展示区域 -->
        <div id="ChatBox" class="am-g am-g-fixed">
            <div class="am-u-lg-12" style="height:400px;border:1px solid #999;overflow-y:scroll;">
                <ul id="chatContent" class="am-comments-list am-comments-list-flip">
                    <li id="msgtmp" class="am-comment" style="display:none;">
                        <div>
                            <header class="am-comment-hd">
                                <div class="am-comment-meta">
                                    <a ff="nickname" href="#link-to-user" class="am-comment-author">某人</a>
                                    <time ff="msgdate" datetime="" title="">2014-7-12 15:30</time>
                                </div>
                            </header>
                            <div ff="content" class="am-comment-bd">此处是消息内容</div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <!-- 聊天内容发送区域 -->
        <div id="EditBox" class="am-g am-g-fixed">
            <input type="text" name="test" style="width:100%;height:140px;" id="inputvalue">
            <button id="send" type="button" class="am-btn am-btn-primary am-btn-block">发送</button>
        </div>

    </div>


</body>
<script type="text/javascript">

    $(function () {
        var nickname = "${param.username }" + Math.random();
        var socket = new WebSocket("ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/websocket/${param.sendname }");
        if (socket==null||socket===undefined) {
			alert("连接失败，请检查是否登录");
		}
        //接收服务器的消息
        socket.onmessage = function (ev) {
            var obj = eval('(' + ev.data + ')');
            addMessage(obj);
        }

        $("#send").click(function () {
            // 获取输入内容
            var inputvalue = document.getElementById("inputvalue").value;
           	// 发送给谁
           	var acceptname = "${param.acceptname }";
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
                    acceptname : acceptname, //发送给谁
                    sendname : "${param.sendname }" // 谁发送的
                });
                // 把数据进行转换
                var tmp = eval('(' + obj + ')');
                // 把要发送的消息添加到面板中
                addMessage(tmp);
                // 发送消息
                socket.send(obj);
                // 清空消息输入框
                document.getElementById("inputvalue").value = "";
            }

        });

    });

    //人名nickname，时间date，是否自己isSelf，内容content
    function addMessage(msg) {
        var box = $("#msgtmp").clone(); 	//复制一份模板，取名为box
        box.show();							//设置box状态为显示
        box.appendTo("#chatContent");		//把box追加到聊天面板中
        box.find('[ff="nickname"]').html(msg.nickname); //在box中设置昵称
        box.find('[ff="msgdate"]').html(msg.date); 		//在box中设置时间
        box.find('[ff="content"]').html(msg.content); 	//在box中设置内容
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
</script>

</html>