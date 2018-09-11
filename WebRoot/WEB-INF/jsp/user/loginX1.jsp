<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/basicStyle.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/NavigationStyle.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/LoginStyle.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.5.1.min.js"></script>

</head>
<body style="background:#fff;">
    <div class="header">
        <div class="header_left">
            <img  class="logo" src="${pageContext.request.contextPath }/static/images/logo.png">
        </div>
        <div class="header_right top_right">
            <a href="${pageContext.request.contextPath }/businessuser/login_toLogin1.do">登录</a>
            <a href="${pageContext.request.contextPath }/businessuser/register1.do">/注册</a>
        </div>
    </div>
    <div class="bg">
        <img src="${pageContext.request.contextPath }/static/images/banner.png">
        <div class="login_border"  style="width:28%;">

            <div class="reg_top">欢迎登录</div>
            <div class="lables">用户名</div>
            <div class="login_item">
                <div class="img_div lf"><img src="${pageContext.request.contextPath }/static/images/yonghu.png"></div>
                <input class="lf login_input"  type="text" name="loginname" id="loginname" value="" placeholder="请输入用户名" style="background:#f7f7f7">
            </div>
            <div class="lables">密码</div>
            <div class="login_item">
                <div class="img_div lf"><img src="${pageContext.request.contextPath }/static/images/pass.png"></div>
                <input class="lf login_input" type="password" name="password" id="password" placeholder="请输入密码" value="" >
            </div>
            <div class="btnLogin" onclick="severCheck();">登录</div>
            <div class="tologin">
                <a href="${pageContext.request.contextPath }/businessuser/register1.do" class="tologin" style="color: #5ba2cf;">没有账号，点此注册</a>
                <a onclick="severCheck1();" class="tologin" style="color: #5ba2cf;margin-left:100px;">忘记密码</a>
            </div>
        </div>
    </div>
 <div class="foot" style="margin-top:-5px;width: 100%;height:10%;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>
	<script type="text/javascript">
	
		//服务器校验
		function severCheck(){
			if(check()){
				
				var loginname = $("#loginname").val();
				var password = $("#password").val();
				var code = "qq123456789fh"+loginname+",fh,"+password+"QQ987654321fh"+",fh,"+$("#code").val();
				$.ajax({
					type: "POST",
					url: '${pageContext.request.contextPath }/login_login.do',
			    	data: {KEYDATA:code,tm:new Date().getTime()},
					dataType:'json',
					cache: false,
					success: function(data){
						if("success" == data.result){
							//saveCookie();
							window.location.href="${pageContext.request.contextPath }/commodity/list.do";
							//window.location.href="${pageContext.request.contextPath }/businessuser/goEditbu.do";
						}else if("daishenhe" == data.result){
							//alert("该用户未审核请联系管理员");
							
							//window.location.href = "${pageContext.request.contextPath }/businessuser/goEditbu.do";
							window.location.href = "${pageContext.request.contextPath }/businessuser/chenggong.do?BUSINESSUSER_ID="+data.BUSINESSUSER_ID;
						}else if("shenheshibai" == data.result){
							//alert("该用户审核失败请联系管理员");
							window.location.href = "${pageContext.request.contextPath }/businessuser/shibai.do?BUSINESSUSER_ID="+data.BUSINESSUSER_ID;
						}else if("tianxie" == data.result){
							window.location.href = "${pageContext.request.contextPath }/businessuser/goEditbu.do";
						}else if("jinyong" == data.result){
							alert("该用户被禁用请联系管理员");
						}else if("xiajia" == data.result){
							alert("该用户被下架请联系管理员");
						}else  if("usererror" == data.result){
							alert("用户名或密码有误");
						}
					}
				});
			}
		}
		function severCheck1(){
			window.location.href="${pageContext.request.contextPath }/businessuser/goforgetPassword.do";
		}
		

	
		function genTimestamp() {
			var time = new Date();
			return time.getTime();
		}

		function changeCode() {
			$("#codeImg").attr("src", "code.do?t=" + genTimestamp());
		}

		//客户端校验
		function check() {

			if ($("#loginname").val() == "") {

				$("#loginname").tips({
					side : 2,
					msg : '用户名不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#loginname").focus();
				return false;
			} else {
				$("#loginname").val(jQuery.trim($('#loginname').val()));
			}

			if ($("#password").val() == "") {

				$("#password").tips({
					side : 2,
					msg : '密码不得为空',
					bg : '#AE81FF',
					time : 3
				});

				$("#password").focus();
				return false;
			}
			return true;
		}

		function savePaw() {
			if (!$("#saveid").attr("checked")) {
				$.cookie('loginname', '', {
					expires : -1
				});
				$.cookie('password', '', {
					expires : -1
				});
				$("#loginname").val('');
				$("#password").val('');
			}
		}

		function saveCookie() {
			if ($("#saveid").attr("checked")) {
				$.cookie('loginname', $("#loginname").val(), {
					expires : 7
				});
				$.cookie('password', $("#password").val(), {
					expires : 7
				});
			}
		}
		function quxiao() {
			$("#loginname").val('');
			$("#password").val('');
		}
		
	
		
	</script>
	<script src="${pageContext.request.contextPath }/static/js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.tips.js"></script>
</body>

</html>