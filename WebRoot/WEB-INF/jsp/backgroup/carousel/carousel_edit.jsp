<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String OSSUrl = application.getInitParameter("OSSUrl");
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
	
function checkTupian(fileId,fileName){
	if (fileId!="") {
		var uploadFileNames = [];
		uploadFileNames = fileId.split(".");
		if (uploadFileNames.length>1&&uploadFileNames[uploadFileNames.length-1]!="jpg"&&uploadFileNames[uploadFileNames.length-1]!="JPG"&&uploadFileNames[uploadFileNames.length-1]!="png"&&uploadFileNames[uploadFileNames.length-1]!="PNG"&&uploadFileNames[uploadFileNames.length-1]!="gif"&&uploadFileNames[uploadFileNames.length-1]!="GIF") {
			alert("友情提示,您"+fileName+"所选择的文件类型不是jpg,png,gif格式的！！！");
			return true;
		} 
	} 
			return false;
}

function checkSize(id){
	var img=document.getElementById(id).files[0];
	if(img!=null ){
		var imagSize =  img.size;
		if(imagSize>1024*1024*3){
			alert("图片不能大于3M");
			return true;
		}
	}
	return false;
}
	
	//保存
	function save(){
		if('${msg}'=='save'){
			if($("#IMGURL").val()==""){
			$("#IMGURL").tips({
				side:3,
	            msg:'请输入轮播图',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IMGURL").focus();
			return false;
		}
		}
		if(checkTupian($("#IMGURL").val(),"轮播图")){
			return false;
		}
		if($("#IMGURL").val()!=""){
			if(checkSize("IMGURL")){
				return false;
			}
		}
		if($("#HREF").val()==""){
			$("#HREF").tips({
				side:3,
	            msg:'请输入链接',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#HREF").focus();
			return false;
		}
		if($("#SORT").val()==""){
			$("#SORT").tips({
				side:3,
	            msg:'请输入排序',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SORT").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="carousel/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="CAROUSEL_ID" id="CAROUSEL_ID" value="${pd.CAROUSEL_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">轮播图:</td>
				<td>
				<input type="file" name="IMGURL" id="IMGURL" />
				<c:if test="${pd.IMGURL!=null&&pd.IMGURL!='' }">
				<img src="<%=OSSUrl %>${pd.IMGURL}" style="width:200px;">
					
				</c:if>
<!-- 				<input type="text" name="IMGURL" id="IMGURL" value="${pd.IMGURL}" maxlength="32" placeholder="这里输入轮播图" title="轮播图"/> -->
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">链接:</td>
				<td><input type="text" name="HREF" id="HREF" value="${pd.HREF}" placeholder="这里输入链接" title="链接"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">排序:</td>
				<td><input type="number" name="SORT" id="SORT" value="${pd.SORT}" maxlength="32" placeholder="这里输入排序" title="排序"/></td>
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