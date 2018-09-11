<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String TEL=(String)request.getSession().getAttribute("TEL");
	String path = request.getContextPath();
String OSSUrl = application.getInitParameter("OSSUrl");
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
//检验手机格式
function checkMobile(str) {
   var re = /^1\d{10}$/;
   if (re.test(str)) {
	   return true;
   } else {
	   return false;
   }
}
	
	//保存
	function save(){
		if($("#OLDPASS").val()==""){
	            alert('请输入旧密码');
			return false;
		}
		
		if($("#NEWPASS").val()==""){
				alert('请输入新的登录密码');
			return false;
		}
			if($("#BUSINESSPWD1").val()==""){
				alert('请输入确认登录密码');
				return false;
			}
			if($("#NEWPASS").val()!="" && $("#BUSINESSPWD1").val()!=""){
				if($("#NEWPASS").val()!=$("#BUSINESSPWD1").val()){
					alert('确认登录密码错误');
					return false;
				}
			}
				
				$.ajax({
					type : "post",
					data : {
						"OLDPASS":$("#OLDPASS").val(),
						"NEWPASS":$("#NEWPASS").val()
					},
					dataType: "json",
					url : "${pageContext.servletContext.contextPath}/businessuser/modifyPassWord.do",
					success : function(data) {
						if(data.msgCode==-1){
							alert("密码不能为空");
						}else if(data.msgCode==-2){
							alert("旧密码错误");
						}else if(data.msgCode==0){
							alert("修改时发生错误");
						}else if(data.msgCode==1){
							//跳转页面
							alert("修改成功");
							window.location.href="${pageContext.request.contextPath}/businessuser/login_toLogin1.do";
						}
					}
				});
			
	}
	
</script>
	<link href="static/css/basicStyle.css" rel="stylesheet" type="text/css">
    <link href="static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
    <link href="static/css/ListStyle.css" rel="stylesheet" type="text/css">
    <style type="text/css">

        .item span{
            width: 100px;
            text-align: end;
            margin-right: 15px;

        }
        .item{
            display: flex;
            display: -webkit-flex;
            justify-content: center;
            align-items: center;
            margin-bottom: 20px;
            color: #989898;
        }
        input{
            width: 25%;
        }
        .btn_div{
            background-color: #5ba2cf;
            border-radius: 5px;
            padding: 10px 20px;
            margin: 50px 30px;
            color: white;
            width: 150px;
            text-align: center;
            font-size: 16px;

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
                <p class="child_goods_p"><a href="${pageContext.request.contextPath }/commodity/list.do">商品管理</a> </p>
                <!--商品管理子类-->
                <ul class="child goodChild">
                    <li><a href="${pageContext.request.contextPath }/commodity/goAdd.do">新增商品</a></li>
                    <li><a href="${pageContext.request.contextPath }/classification/list.do">分类管理</a></li>
                </ul>
            </li>
            <li class=""><p><a href="${pageContext.request.contextPath }/bussinessactivity/list.do">优惠信息</a></p></li>
            <li><p><a href="${pageContext.request.contextPath }/membershipcard/list.do">会员卡管理</a></p></li>
            <li>
                <p class="child_store_p"><a style="color: #5aa3d0">店铺设置</a></p>
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



    <p class="top">设置>修改密码</p>
    <form action="businessuser/${msg }.do" name="Form" id="Form" method="post">
    <div class="center_div" style="padding-top: 100px;">
        <div class="item">
            <span >旧密码：</span>
            <input type="password" name="OLDPASS" id="OLDPASS" value="" placeholder="">
        </div>
        <div class="item">
            <span>登录密码：</span>
            <input type="password" name="NEWPASS" id="NEWPASS">
        </div>
        <div class="item">
            <span>确认密码：</span>
            <input type="password" name="BUSINESSPWD1" id="BUSINESSPWD1">
        </div>
        <div class="item" style="margin-top: 40px;">
            <p class="btn_div" onclick="save();">保存</p>
            <p class="btn_div" onclick="quxiao();">取消</p>
        </div>
    </div>
    </form>
</div>
<div class="foot" style="width: 100%;height: 80px;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;margin-top: 50px;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>
</body>
<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
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
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/ajaxfileupload.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		//上传
		function uploadphoto(fileId){
			//alert("似懂非懂");
		       if(checkFile(fileId)){ 
		        	$.ajaxFileUpload({
		            	url:"${pageContext.servletContext.contextPath}/businessuser/uploadBPhoto.do",
		            	secureuri:false,                       //是否启用安全提交,默认为false
		            	dataType:"json", 
		            	fileElementId:fileId,//文件选择框的id属性
		            	success:function(data){   
		            	var msg=data.msg;
		            	//alert(data.msg);
		            		
		            		if(data.flag == 0){ 
		                		alert("上传失败");
		                	}else if(data.flag == 1){ 
		                		alert("请选择文件后上传");
		                	}else if(data.flag == 2){ 
		                		alert("上传文件格式不正确");
		                	}else{
		                		
		                			$("#BUSINESSLICENSEURL").val(data.msg);
		                			$("#shareImg").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
		                		
		                	}
		            	}
		        	});
		       }
		    }
		//上传
		function uploadphoto1(fileId){
			//alert("似懂非懂");
		       if(checkFile(fileId)){ 
		        	$.ajaxFileUpload({
		            	url:"${pageContext.servletContext.contextPath}/businessuser/uploadBPhoto1.do",
		            	secureuri:false,                       //是否启用安全提交,默认为false
		            	dataType:"json", 
		            	fileElementId:fileId,//文件选择框的id属性
		            	success:function(data){   
		            	var msg=data.msg;
		            	//alert(data.msg);
		            		
		            	if(data.flag == 0){ 
	                		alert("上传失败");
	                	}else if(data.flag == 1){ 
	                		alert("请选择文件后上传");
	                	}else if(data.flag == 2){ 
	                		alert("上传文件格式不正确");
	                	}else if(data.flag == 3){ 
		                		alert("文件大小不能超过100K");
		                	}else if(data.flag == 4){ 
		                		alert("文件尺寸为60*60");
		                	}else{
		                		
		                			$("#SHOPLOGO").val(data.msg);
		                			$("#shareImg1").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
		                		
		                	}
		            	}
		        	});
		       }
		    }
		function checkFile(fileName) {
	       	var fileImg = document.getElementById(fileName);
	       	if (fileImg.files[0] == undefined) {
	       		alert("请选择文件！");
	       		return false;
	       	} else {
	       		return true;
	       	}
       }
		</script>
</body>
</html>