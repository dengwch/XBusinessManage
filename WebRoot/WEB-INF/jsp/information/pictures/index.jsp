<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%> 
	
	<!--查看图片插件 -->
	<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/ImgInput/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/ImgInput/css/style.css">
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/ImgInput/css/ssi-uploader.css"/>

	<!--查看图片插件 -->

	</head>
<body>
<div class="container">
	<div class="container">
		<h3>注意：因为演示中没有后台上传程序的支持，点击上传按钮时会返回错误信息！</h3>
		<div class="row">
			<div class="col-md-12">
				<h3>基本演示（支持jpg、gif、txt、png和pdf格式文件）</h3>
				<input type="file" multiple id="ssi-upload"/>
			</div>
		</div>
	</div>
</div>
		
	<script src="${pageContext.servletContext.contextPath}/ImgInput/js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script src="${pageContext.servletContext.contextPath}/ImgInput/js/ssi-uploader.js"></script>
<script type="text/javascript">
var url='';
	$('#ssi-upload').ssi_uploader({url:'${pageContext.servletContext.contextPath}/pictures/save1.do',maxFileSize:6,allowed:['jpg','gif','png','pdf']});
	$('#ssi-upload2').ssi_uploader({url:'#',preview:false,allowed:['jpg','gif','txt','png','pdf']});
	$('#ssi-upload3').ssi_uploader({url:'#',dropZone:false,allowed:['jpg','gif','txt','png','pdf']});
</script>
	</body>
</html>

