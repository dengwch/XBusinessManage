<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.fh.entity.businessuser.Businessuser" %>
<%@page import="com.fh.util.Const" %>


<%
Businessuser businessuser = (Businessuser) request.getSession().getAttribute("user");
String TEL=(String)request.getSession().getAttribute("TEL");
String BUSINESSPWD=businessuser.getBUSINESSPWD();
String TEL1=businessuser.getTEL();
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
 <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/page.js"></script>
 
 
 <style type="text/css">
        .auditResult{
            font-size: 16px;
            color: #5aa3d0;
            line-height: 30px;
            display: -webkit-flex;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;

        }
        .img_fail{
            margin-bottom: 25px;
            width: 40px;
            height: 40px;
        }
        p{
            line-height: 30px;
        }
        
        
        
        
        
        .page{
    list-style: none;
}
.page>li{
    float: left;
    padding: 5px 10px;
    cursor: pointer;
}
.page .pageItem{
    border: solid thin #DDDDDD;
    margin: 5px;
}
.page .pageItemActive{
    border: solid thin #0099FF;
    margin: 5px;
    background-color: #0099FF;
    color:white;
}
.page .pageItem:hover{
    border: solid thin #0099FF;
    background-color: #0099FF;
    color:white;
}
.page .pageItemDisable{
    border: solid thin #DDDDDD;
    margin: 5px;
    background-color: #DDDDDD;
}
    </style>
</head>
<body> 
<input type="hidden" value="<%=BUSINESSPWD %>" id="password"/>
<input type="hidden" value="<%=TEL1 %>" id="loginname"/>
    <!--导航-->
<div class="header">
    <div class="header_left">
        <img  class="logo" src="${pageContext.request.contextPath }/static/images/logo.png">
        <ul class="header_nav">
            <li class="shopping">
                <p class="child_goods_p"><a href="${pageContext.request.contextPath }/commodity/list.do" style="color: #5aa3d0">商品管理</a> </p>
                <!--商品管理子类-->
                <ul class="child goodChild">
                    <li><a href="${pageContext.request.contextPath }/commodity/goAdd.do">新增商品</a></li>
                    <li><a href="${pageContext.request.contextPath }/classification/list.do">分类管理</a></li>
                </ul>
            </li>
            <li class=""><p><a href="${pageContext.request.contextPath }/bussinessactivity/list.do">优惠信息</a></p></li>
            <li><p><a href="${pageContext.request.contextPath }/membershipcard/list.do">会员卡管理</a></p></li>
            <li>
                <p class="child_store_p"><a >店铺设置</a></p>
                <!--店铺设置-->
                <ul class="child storeChild">
                    <li><a href="${pageContext.request.contextPath }/businessuser/goEditxx.do">店铺信息</a></li>
                    <li><a href="${pageContext.request.contextPath }/businessuser/list.do">子账号管理</a></li>
                </ul>
            </li>
            <li><p><a href="${pageContext.request.contextPath }/commodity/listLL.do">流量信息</a></p></li>
        </ul>
    </div>
    <div class="header_right">
        <span class="userName">您好,${pd1.TEL}！</span>
        <span class="shezhi" style="color:#333;border:0;">设置</span>
        <!--店铺设置-->
        <ul class="child child_setting">
            <a href="${pageContext.request.contextPath }/businessuser/goEditPassword.do"><li>修改密码</li></a>
            <a href="${pageContext.request.contextPath }/feedback/goAdd.do"><li>意见反馈</li></a>
            <a href="logout"><li>退出账号</li></a>
        </ul>
    </div>
</div>

<div>
     <p class="top"></p>
        <div class="auditResult center_div">
            <img src="${pageContext.request.contextPath }/static/images/chenggong.png" class="img_fail"/>
            <p>您好<span>${pd1.CONTACTSNAME}</span>，您填写的<span>${pd1.COMPANYNAME}</span>信息<span>正在审核中</span></p>
            <p>请耐心等待</p>
        </div>
    </div>
    <!--
    <ul id="demoContent"></ul>
 	<ul class="page" id="page"></ul>
    -->
    <div class="foot" style="margin-top:50px;width: 100%;height: 80px;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	   $(".header_right>img ").mouseover(function(){
	        $(".header_right>ul").fadeIn();
	      });
	      $(".header_right>img ").mouseout(function(){
	    	  $(".header_right>ul").fadeOut(8000);
	        });
    
    var datas = jQuery.parseJSON( '${json}');
    var options={
    		"id":"page",//显示页码的元素
    		"data":datas,//显示数据
    	    "maxshowpageitem":3,//最多显示的页码个数
    	    "pagelistcount":2//每页显示数据个数
    	    
    	};
    	   page.init(datas.length,1,options);
    	   
    	   
});
function callBack(result){
    var cHtml="";
for(var i=0;i<result.length;i++){
   cHtml+="<li>"+ result[i].WORKNAME+"</li>";//处理数据
}
$("#demoContent").html(cHtml);//将数据增加到页面中
}

//页面显示功能
function viewPage(currentPage,listCount,pagelistcount,data){
       var NUM=listCount%pagelistcount==0?listCount/pagelistcount:parseInt(listCount/pagelistcount)+1;
       if(currentPage==NUM){
           var result=data.slice((currentPage-1)* pagelistcount,data.length);
       }
       else{
           var result=data.slice((currentPage-1)*pagelistcount,(currentPage-1)*pagelistcount+pagelistcount);
       }
       callBack(result);
};

var c=0;
function showLogin(){
	var loginname = $("#loginname").val();
	var password = $("#password").val();
	var code = "qq123456789fh"+loginname+",fh,"+password+"QQ987654321fh"+",fh,"+$("#code").val();
	$.ajax({
		type: "POST",
		url: '${pageContext.request.contextPath }/login_login1.do',
    	data: {KEYDATA:code,tm:new Date().getTime()},
		dataType:'json',
		cache: false,
		success: function(data){
			if("success" == data.result){
				window.location.href="${pageContext.request.contextPath }/commodity/list.do";
			}
		}
	});
}
setInterval("showLogin()","2000");


</script>

</body>
</html>