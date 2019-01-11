<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${param.pagetype}界面</title>
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
  <!-- 按钮触发器， 需要指定 target -->
  <button class="am-btn am-btn-primary" data-am-offcanvas="{target: '#doc-oc-demo2', effect: 'push'}">显示用户列表</button>
  <div class="am-tabs" data-am-tabs="{noSwipe: 1}" id="doc-tab-demo-1">
    <ul class="am-tabs-nav am-nav am-nav-tabs">
      <!-- 列表条 -->
    </ul>
    <div class="am-tabs-bd">
      <div>
        <!-- 模板 -->
        <div id="panel" style="display:none">
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
              <!-- <button id="send" type="button" class="am-btn am-btn-primary am-btn-block" onclick="sendMsg()">发送</button>
            </div> -->
            </div>
          </div>
        </div>
      </div>
    </div>
    <br />
    <!-- 侧边栏内容 -->
    <div id="doc-oc-demo2" class="am-offcanvas">
      <div class="am-offcanvas-bar">
        <div style="display:none" id="buttonmb">
          <div class="am-offcanvas-content">
            <button type="button" class="am-btn am-btn-primary js-append-tab" onclick="">插入 Tab</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- <button onclick="iSPageUserName('123')">追加</button> -->
</body>
<script type="text/javascript">
var socket = new WebSocket("ws://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/websocket/${param.sendname }");
</script>
<script src="js/test.js"></script>
<script src="js/ymkz.js"></script>
<script src="js/appendhtml.js"></script>

</html>