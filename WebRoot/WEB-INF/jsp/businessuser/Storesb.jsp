<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String TEL=(String)request.getSession().getAttribute("TEL");
String OSSUrl1 = application.getInitParameter("OSSUrl1");
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
	
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		
<script type="text/javascript">
	
	
//保存
function save(){
		
	
	if($("#TEL").val()==""){
		$("#TEL").tips({
			side:3,
            msg:'请输入手机',
            bg:'#AE81FF',
            time:2
        });
		$("#TEL").focus();
		return false;
	}
	if($("#SHOPADDRESS").val()==""){
		$("#SHOPADDRESS").tips({
			side:3,
            msg:'请输入公司地址',
            bg:'#AE81FF',
            time:2
        });
		$("#SHOPADDRESS").focus();
		return false;
	}
	if($("#SHOPNAME").val()==""){
		$("#SHOPNAME").tips({
			side:3,
            msg:'请输入公司名称',
            bg:'#AE81FF',
            time:2
        });
		$("#SHOPNAME").focus();
		return false;
	}
	/*if($("#BUSINESSLICENSEURL").val()==""){
		$("#BUSINESSLICENSEURL").tips({
			side:3,
            msg:'请输入营业执照',
            bg:'#AE81FF',
            time:2
        });
		$("#BUSINESSLICENSEURL").focus();
		return false;
	}*/
	if($("#CONTACTSNAME").val()==""){
		$("#CONTACTSNAME").tips({
			side:3,
            msg:'请输入联系人姓名',
            bg:'#AE81FF',
            time:2
        });
		$("#CONTACTSNAME").focus();
		return false;
	}
	
	$("#Form").submit();
}

	
</script>
	<link rel="stylesheet" type="text/css" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="static/css/index.css">
	<link href="static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
	<style>
	  li{
	  	list-style: none;
	  }
	</style>
</head>
<body>
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
            <li class=""><p><a href="${pageContext.request.contextPath }/bussinessactivity/list.do">优惠信息</a></p></li>
            <li><p><a  href="${pageContext.request.contextPath }/membershipcard/list.do">会员卡管理</a></p></li>
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


<form action="businessuser/${msg }.do" name="Form" id="Form" method="post">
<input type="hidden" name="BUSINESSUSER_ID" id="BUSINESSUSER_ID" value="${pd.BUSINESSUSER_ID}"/>
		<input type="hidden" name="msg" id="msg" value="${pd.msg}"/>
   <div class="container" style="padding-bottom: 100px;">
		<div class="row clearfix">
			<div class="col-md-12 column">
			<p>店铺名称：<input  style="width:300px;" type="text" name="SHOPNAME" id="SHOPNAME" value="${pd.SHOPNAME}"></p>
			<p>联系人：<input  style="width:300px;" type="text" name="CONTACTSNAME" id="CONTACTSNAME" value="${pd.CONTACTSNAME}"></p>
			<p>联系电话：<input style="width:300px;" name="TEL" id="TEL" value="${pd.TEL}" maxlength="11"></p>
			<p>店铺地址：<input  style="width:300px;" type="text" name="SHOPADDRESS" id="SHOPADDRESS" value="${pd.SHOPADDRESS}"></p>
			<!--<p style="">营业执照：
				<div id="addCommodityIndex">  
		             input-group start
		            <div class="input-group row">
		                	<div id="preview">
		                	
		                	<input name="BUSINESSLICENSEURL" id="BUSINESSLICENSEURL" value="${pd.BUSINESSLICENSEURL}" type="hidden"/>
		        	
		        	<c:if test="${pd.BUSINESSLICENSEURL=='' || pd.BUSINESSLICENSEURL==null}">
			        <img border="0" id="shareImg" src="${pageContext.request.contextPath }/static/images/photo_icon.png" width="90" height="90" onclick="$('#previewImg').click();"/>
			        </c:if>
			       <c:if test="${pd.BUSINESSLICENSEURL!='' && pd.BUSINESSLICENSEURL!=null}">
			        <img border="0"  id="shareImg" src="<%=OSSUrl %>${pd.BUSINESSLICENSEURL}" width="90" height="90" onclick="$('#previewImg').click();"/>
			        </c:if>
		                     </div>         
		                    <input id="previewImg" name="myfile" type="file" onchange="uploadphoto('previewImg')" style="display:none;"/> 
		            </div>
		            input-group end    
				</div>
			</p>
			--><!--<p><span>(请尽量上传100K以下的图片：尺寸为100*100最好)</span></p>
			--><p><button type="button" class="btn btn-primary" data-toggle="button" style="margin-top:50px;margin-left: 100px;width: 200px;" onclick="save();"> 确定</button></p>
			</div>
		</div>
    </div>
    </form>
   
     <div class="foot" style="width: 100%;height: 80px;background: #fff;text-align: center;line-height: 80px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;margin-top: 0px;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>
</body>


		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
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
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/ajaxfileupload.js"></script>
		<script type="text/javascript">
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
		            	url:"${pageContext.servletContext.contextPath}/businessuser/uploadBPhoto2.do",
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
		                		alert("文件尺寸为100*100");
		                }else{
		                		
		                			$("#BUSINESSLICENSEURL").val(data.msg);
		                			if(msg.substring(0,3)=='gzh'){
		                				$("#shareImg").attr("src", "<%=OSSUrl1 %>"+msg+"?r="+Date.parse(new Date()));
		                			}else{
		                				$("#shareImg").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
		                			}
		                		
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
		                		alert("文件尺寸为100*100");
		                }else{
		                		
		                			$("#SHOPLOGO").val(data.msg);
		                			if(msg.substring(0,3)=='gzh'){
		                				$("#shareImg1").attr("src", "<%=OSSUrl1 %>"+msg+"?r="+Date.parse(new Date()));
		                			}else{
		                				$("#shareImg1").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
		                			}
		                		
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