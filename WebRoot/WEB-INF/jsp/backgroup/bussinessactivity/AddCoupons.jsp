<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String TEL=(String)request.getSession().getAttribute("TEL");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
		if($("#ACTIVITYNAME").val()==""){
			alert("请输入优惠");
		}else{
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
	}
	
</script>
	<link href="${pageContext.request.contextPath }/static/css/basicStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/static/css/ListStyle.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .btnAddCoupons{
            background-color: #5ba2cf;
            border-radius: 5px;
            margin:80px auto;
            width: 100px;
            height: 35px;
            line-height: 35px;
            text-align: center;
            color: white;
            font-weight: bold;
        }
        .center_div {
	    background-color: white;
	    margin: 10px 110px;
	    min-height: 430px;
	    max-height:730px;
	    padding: 35px;
}
        .display_div{
        text-align:center;
            flex-direction: column;
            justify-content: space-between;
            align-items: center;
        }
        input{
            width: 30%;
            margin-top: 100px;
        }
    </style>
</head>
<body>
<div>
   <!--导航-->
<div class="header">
    <div class="header_left">
        <img  class="logo" src="${pageContext.request.contextPath }/static/images/logo.png">
        <ul class="header_nav">
            <li class="shopping">
                <p class="child_goods_p"><a href="${pageContext.request.contextPath }/commodity/list.do" >商品管理</a> </p>
                <!--商品管理子类-->
                <ul class="child goodChild">
                    <li><a href="${pageContext.request.contextPath }/commodity/goAdd.do">新增商品</a></li>
                    <li><a href="${pageContext.request.contextPath }/classification/list.do">分类管理</a></li>
                </ul>
            </li>
            
            <li class=""><p><a style="color: #5aa3d0" href="${pageContext.request.contextPath }/bussinessactivity/list.do">优惠信息</a></p></li>
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
        <span class="userName">您好,<%=TEL %>！</span>
       <span class="shezhi" style="color:#333;border:0;">设置</span>
        <!--店铺设置-->
        <ul class="child child_setting">
            <a href="${pageContext.request.contextPath }/businessuser/goEditPassword.do"><li>修改密码</li></a>
            <a href="${pageContext.request.contextPath }/feedback/goAdd.do"><li>意见反馈</li></a>
            <a href="logout"><li>退出账号</li></a>
        </ul>
    </div>
</div>



    <p class="top">优惠信息>添加优惠</p>
    <form action="bussinessactivity/${msg }.do" name="Form" id="Form" method="post">
    <input type="hidden" name="BUSSINESSACTIVITY_ID" id="BUSSINESSACTIVITY_ID" value="${pd.BUSSINESSACTIVITY_ID}"/>
    <div class="center_div display_div">
        <input  type="text" name="ACTIVITYNAME" id="ACTIVITYNAME" value="${pd.ACTIVITYNAME}" placeholder="请输入优惠内容">
        <div class="btnAddCoupons" onclick="save();">确定</div>
    </div>
    </form>
<div class="foot" style="margin-top:50px;width: 100%;height: 80px;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $(".header_right>.shezhi ").mouseover(function(){
        $(".header_right>ul").show();
      });
      $(".header_right>.shezhi ").mouseout(function(){
          $(".header_right>ul").fadeOut(8000);
        });
});
</script>    
</div>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>