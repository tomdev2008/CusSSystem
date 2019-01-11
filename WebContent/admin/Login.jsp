<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head lang="en">
	<meta charset="UTF-8">
	<title>Login</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="format-detection" content="telephone=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="stylesheet" href="assets/css/amazeui.min.css" />
	<style>
		.header {
      text-align: center;
    }
    .header h1 {
      font-size: 200%;
      color: #333;
      margin-top: 30px;
    }
    .header p {
      font-size: 14px;
    }
  </style>
</head>

<body>
	<div class="am-g">
		<div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
			<h3>登录</h3>
			<hr>
			<br>
			<br>
			<form method="post" class="am-form" action="http://${pageContext.request.getServerName()}:${pageContext.request.getServerPort()}${pageContext.request.contextPath}/kfxt/admin/Login">
				<label>用户名:</label>
				<input type="text" name="user" value="" style="width: 300px">
				<br>
				<label for="password">密码:</label>
				<input type="password" name="pass" id="password" value="" style="width: 300px">
				<br>
				<label>验证码：</label>
				<input type="text" name="yzm" style="width: 300px">
				<td style="width: 50%">
					<img alt="验证码" width="180" height="30" id="img" src="${pageContext.request.contextPath }/kfxt/YZMServlet" class="textinput"
					 height="30">
					<a href="javascript:void(0)" onclick="chengeImage()">看不清换一张</a>
				</td>
				<br>
				<label for="remember-me">
					<input id="remember-me" type="checkbox" value="7" name="freelogin">
					7天免登陆
				</label>
				<br />
				<div class="am-cf">
					<input type="submit" name="" value="登 录" class="am-btn am-btn-primary am-btn-sm am-fl">
				</div>
			</form>
			<hr>
		</div>
	</div>
</body>
<script>
	// 换验证码方法
	function chengeImage() {
		// 找到验证码图片,并设置新的图片路径
		document.getElementById("img").src = "${pageContext.request.contextPath }/kfxt/YZMServlet?time=" + new Date().getTime();
	}
	var msg = "${login_msg}";
	window.onload = function () {
		if(msg!==""){
			alert(msg);
		}
	}
</script>

</html>