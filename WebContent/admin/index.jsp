<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html class="no-js fixed-layout">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>客服管理后台</title>
    <meta name="description" content="这是一个 index 页面">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="assets/css/admin.css">
</head>

<body>

    <header class="am-topbar am-topbar-inverse admin-header">
        <div class="am-topbar-brand">
            <strong>客服系统</strong> <small>后台管理</small>
        </div>

        <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span
                class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>     
    </header>

    <div class="am-cf admin-main">
        <!-- 侧边栏列表开始 -->
        <div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
            <div class="am-offcanvas-bar admin-offcanvas-bar">
                <ul class="am-list admin-sidebar-list">
                    <li><a href="javascript:void(0)" onclick="iframe1.location='./fwqxx.html'"><span class="am-icon-home"></span>
                            首页</a></li>
                    <li><a href="javascript:void(0)" onclick="iframe1.location='./kflb.html'"><span class="am-icon-table"></span>
                            客服管理</a></li>
                    <li><a href="javascript:void(0)" onclick="iframe1.location='./log.html'"><span class="am-icon-calendar"></span>
                            系统日志</a></li>
                    <li class="admin-parent">
                        <a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-file"></span>
                            管理员管理 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                        <ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
                            <li><a href="javascript:void(0)" onclick="iframe1.location='./glylist.html'"><span class="am-icon-calendar"></span>
                                    管理员列表</a></li>
                            <li><a href="javascript:void(0)" class="am-cf" onclick="iframe1.location='./glygl.html'"><span
                                        class="am-icon-check"></span> 管理员管理<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
                        </ul>
                    </li>
                    <li><a href="http://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/kfxt/admin/OutLoginServlet"><span class="am-icon-sign-out"></span> 注销</a></li>
                </ul>
                <!-- 文本面板开始 -->
                <div class="am-panel am-panel-default admin-sidebar-panel" id="fwqxxpenal">
                    <div class="am-panel-bd">
                        <p><span class="am-icon-bookmark"></span> 当前服务器信息</p>
                        <p>服务器IP：10.172.15.164</p>
                        <p>操作系统：</p>
                        <p>CPU占用率：</p>
                        <p>内存占用率：</p>
                        <p>在线人数：</p>
                        <p>当前服务器时间：</p>
                    </div>
                </div>
                <!-- 文本面板结束 -->
            </div>
        </div>
        <!-- 侧边栏列表结束 -->

        <!-- 内容页开始 -->
        <iframe style="width:86%;height:97%;" name="iframe1" src="./fwqxx.html"></iframe>
        <!-- 内容页结束 -->

    </div>

    <a href="#" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"></a>
</body>
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>

<script>
	var localurl123 = "http://"+"${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}"+"/kfxt";

    // 定时刷新服务器数据
    setInterval(function () {
        var url = localurl123+"/admin/serverStatus";
        $.ajax({
            type: "post",
            url: url,
            data: {},
            cache: false,
            async: false,
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                // var obj = eval('(' + data.data + ')');
                addPealMsg(data);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("ajax请求失败！");
            }
        });
    }, 4000);
    function addPealMsg(msg) {
       var box = $("#fwqxxpenal");
       box.find('p').eq(1).html("服务器IP："+null);
       box.find('p').eq(2).html("操作系统："+msg.osName);
       box.find('p').eq(3).html("CPU占用率："+Number(msg.cpuRation)+"%");
       box.find('p').eq(4).html("内存占用率："+Number(msg.ramRation)+"%");
       box.find('p').eq(5).html("在线人数："+msg.onineSum);
       box.find('p').eq(6).html("当前服务器时间"+msg.time);
    }
</script>

</html>