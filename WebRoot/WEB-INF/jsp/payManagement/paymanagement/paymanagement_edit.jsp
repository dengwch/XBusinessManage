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
			
		if($("#APPID").val()==""){
			$("#APPID").tips({
				side:3,
	            msg:'请输入appid',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#APPID").focus();
			return false;
		}
		if($("#APPSECRET").val()==""){
			$("#APPSECRET").tips({
				side:3,
	            msg:'请输入appsecret',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#APPSECRET").focus();
			return false;
		}
		if($("#PARTNER").val()==""){
			$("#PARTNER").tips({
				side:3,
	            msg:'请输入商户id',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PARTNER").focus();
			return false;
		}
		if($("#PARTNERKEY").val()==""){
			$("#PARTNERKEY").tips({
				side:3,
	            msg:'请输入api密钥',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PARTNERKEY").focus();
			return false;
		}
		
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="paymanagement/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="PAYMANAGEMENT_ID" id="PAYMANAGEMENT_ID" value="${pd.PAYMANAGEMENT_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">AppId:</td>
				<td><input type="text" name="APPID" id="APPID" value="${pd.APPID}" maxlength="32" placeholder="这里输入appid" title="appid"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">AppSecret:</td>
				<td><input type="text" name="APPSECRET" id="APPSECRET" value="${pd.APPSECRET}" maxlength="32" placeholder="这里输入appsecret" title="appsecret"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商户号PartnerId:</td>
				<td><input type="text" name="PARTNER" id="PARTNER" value="${pd.PARTNER}" maxlength="32" placeholder="这里输入商户id" title="商户id"/></td>
			</tr>
			<tr>
				<td style="width:115px;text-align: right;padding-top: 13px;">商户密钥PartnerKey:</td>
				<td><input type="text" name="PARTNERKEY" id="PARTNERKEY" value="${pd.PARTNERKEY}" maxlength="32" placeholder="这里输入api密钥" title="api密钥"/></td>
			</tr>
			<tr>
				<td style="width:115px;text-align: right;padding-top: 13px;">上传证书:</td>
				<td>
				  <label style="width:100%;height:100%;">
	        		<!--<input name="CERTIFICATEURL" id="CERTIFICATEURL" value="${pd.CERTIFICATEURL}" type="hidden"/>
		        	--><input id="uploadImg" name="CERTIFICATEURL" type="file" onchange="uploadphoto('uploadImg')"/> 
	             </label>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		//证书上传
		function uploadphoto(fileId){
			//alert("似懂非懂");
		       if(checkFile(fileId)){ 
		        	<%--$.ajaxFileUpload({
		            	url:"${pageContext.servletContext.contextPath}/paymanagement/shangchuan.do",
		            	secureuri:false,                       //是否启用安全提交,默认为false
		            	dataType:"json", 
		            	fileElementId:fileId,           	   //文件选择框的id属性
		            	success:function(data){   
		            	var msg=data.msg;
		            	//alert(data.msg);
		            		
		            		if(data.flag == 0){ 
		                		alert("上传失败");
		                	}else{
		                		$("#CERTIFICATEURL").val(data.msg);
		                	}
		            	}
		        	});--%>
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