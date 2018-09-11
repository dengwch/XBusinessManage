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
	
	
	//保存
	function save(){
		if('${msg}'=='save'){
			if($("#IMGURL").val()==""){
				$("#IMGURL").tips({
					side:3,
		            msg:'请输入图片路径',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#IMGURL").focus();
				return false;
			}
		}
		$("#Form").submit();/
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="commodityimg/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="COMMODITYIMG_ID" id="COMMODITYIMG_ID" value="${pd.COMMODITYIMG_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
<!-- 			<tr> -->
<!-- 				<td style="width:70px;text-align: right;padding-top: 13px;">商品ID:</td> -->
<!-- 				<td> -->
				<input type="hidden" name="COMMODITY_ID" id="COMMODITY_ID" value="${pd.COMMODITY_ID}" />
<!-- 				</td> -->
<!-- 			</tr> -->
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">图片路径:</td>
				<td>
<!-- 				<input type="text" name="IMGURL" id="IMGURL" value="${pd.IMGURL}" maxlength="32" placeholder="这里输入图片路径" title="图片路径"/> -->
				<input type="file"  name="IMGURL" id="IMGURL">
				<c:if test="${pd.IMGURL!=null && pd.IMGURL!=''}">
					<img style="width:200px;" src="<%=OSSUrl%>${pd.IMGURL}">
				</c:if>
				
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
<!-- 					<a class="btn btn-mini btn-primary" onclick="save();">保存</a> -->
<input type="submit" value="保存">
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