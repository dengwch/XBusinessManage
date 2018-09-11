<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>${pd.SYSNAME}</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/basicStyle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/NavigationStyle.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/LoginStyle.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.5.1.min.js"></script>
<style type="text/css">
body{
font-size:'微软雅黑'}</style>
</head>
<body style="background:#fff;">

	<div>
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
        <div class="reg_border" style="width:28%;">

            <div class="reg_top">欢迎注册</div>
            <div class="div_item">
                <div class="div_item_margin">手机号</div>
                <input  placeholder="请输入11位数字手机号码" type="tel" id="phone" name="phone" maxlength="11">
                <div class="div_item div_item_margin">验证码</div>
                <div class="code div_item" style="flex-direction: row;width: 100%;">
                    <input class="item_grow" placeholder="请输入验证码" type="text" id="phonecode" name="phonecode">
                    <p class="getCode" onclick="phoneCode(this)">获取验证码</p>
                </div>
                <div class="div_item_margin">密码</div>
                <input type="password" id="userPassword" name="userPassword" placeholder="请输入密码">
                <div class="div_item_margin">确认密码</div>
                <input type="password" id="userPassword1" name="userPassword1" placeholder="请再次确认密码">
                <div class="btnLogin" onclick="subinfo()">注册</div>
                <a href="${pageContext.request.contextPath }/businessuser/login_toLogin1.do" class="tologin">已有账号，点此登录</a>
            </div>


        </div>
    </div>
</div>
 <div class="foot" style="margin-top:-5px;width: 100%;height: 10%;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>
<script>
	//发送验证码
	var wait;
	function phoneCode(yzm_btn){
		var userPassword=$("#userPassword").val();
		var userPassword1=$("#userPassword1").val();
		var url="${pageContext.servletContext.contextPath}/businessuser/smsCode.do";
		var mobiles="";
		mobiles=$("#phone").val();
		if(mobiles == null || mobiles == ''){
			alert("手机号不能为空");
			return;
		}
		
			
		if(checkMobile(mobiles)==false){
			alert("请输入正确的手机号");
			return;
		}
		var type="1";
		$.ajax({
			type : "post",
			data : {
				"phone":mobiles,
				"type":type
			},
			dataType: "json",
			url : url,
			success : function(data) {
				if(data.msg=="发送成功"){
					wait = 60;
					yzm_btn.disabled = false;
					time2(yzm_btn);
				}else if(data.msg==-1){
					alert("用户已存在");
				}else{
					alert(data.msg);
				}
			}
		});
	}
	function time2(o) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.innerHTML = "获取验证码";
			wait = 60;
		} else {
			o.setAttribute("disabled", true);
			o.innerHTML = wait + "秒后重新获取";
			wait--;
			setTimeout(function() {
				time2(o);
			}, 1000);
		}
	}
    //检验手机格式
function checkMobile(str) {
   var re = /^1\d{10}$/;
   if (re.test(str)) {
	   return true;
   } else {
	   return false;
   }
}
//检验密码格式
function checkpassword(userPassword){
	var re = /^[0-9A-Za-z]{6,20}$/;
	 if (re.test(userPassword)) {
		   return true;
	   } else {
		   return false;
	   }
}


//点击完成注册
function subinfo(){
	var userPassword=$("#userPassword").val();
	var userPassword1=$("#userPassword1").val();
	var mobiles=$("#phone").val();
	if(mobiles == null || mobiles == ''){
		alert("手机号不能为空");
		return;
	}
	if(checkMobile(mobiles)==false){
		alert("请输入正确的手机号");
		return;
	}
	var phonecode=$("#phonecode").val();
	if(phonecode == null || phonecode == ''){
		alert("验证码不能为空");
		return;
	}
	if(userPassword == null || userPassword == '' || userPassword1 == null || userPassword1 == ''){
		alert("密码不能为空");
		return;
	}else if(userPassword1!=userPassword){
		alert("确认密码不正确");
		return;
	}
		$.ajax({
				type : "post",
				data : {
					"code":phonecode,
					"TEL":mobiles,
					"BUSINESSPWD":userPassword
				},
				dataType: "json",
				url : "${pageContext.servletContext.contextPath}/businessuser/save.do",
				success : function(data) {
					if(data.result==0){
						alert("验证码错误");
					}else if(data.result==-1){
						alert("手机已注册");
					}else if(data.result==1){
						alert("注册成功,请填写审核信息");
						window.location.href = "${pageContext.request.contextPath }/businessuser/goEditbu.do";
						//跳转到登录页面
						//window.location.href="${pageContext.request.contextPath}/businessuser/login_toLogin1.do";
					}
				}
			});
}
	</script>
	
	
	<script src="${pageContext.request.contextPath }/static/js/jquery-1.7.2.js"></script>
</body>

</html>