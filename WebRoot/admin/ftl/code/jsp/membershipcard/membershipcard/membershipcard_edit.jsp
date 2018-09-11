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
			if($("#DISCOUNT").val()==""){
			$("#DISCOUNT").tips({
				side:3,
	            msg:'请输入折扣',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DISCOUNT").focus();
			return false;
		}
		if($("#GOSTARTTIME").val()==""){
			$("#GOSTARTTIME").tips({
				side:3,
	            msg:'请输入领取开始时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#GOSTARTTIME").focus();
			return false;
		}
		if($("#GOENDTIME").val()==""){
			$("#GOENDTIME").tips({
				side:3,
	            msg:'请输入领取截至时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#GOENDTIME").focus();
			return false;
		}
		if($("#USESTARTTIME").val()==""){
			$("#USESTARTTIME").tips({
				side:3,
	            msg:'请输入使用开始时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USESTARTTIME").focus();
			return false;
		}
		if($("#USEENDTIME").val()==""){
			$("#USEENDTIME").tips({
				side:3,
	            msg:'请输入使用截至时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#USEENDTIME").focus();
			return false;
		}
		if($("#NUMBER").val()==""){
			$("#NUMBER").tips({
				side:3,
	            msg:'请输入数量',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NUMBER").focus();
			return false;
		}
		if($("#CREATETIME").val()==""){
			$("#CREATETIME").tips({
				side:3,
	            msg:'请输入创建会员卡时间',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CREATETIME").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="membershipcard/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="MEMBERSHIPCARD_ID" id="MEMBERSHIPCARD_ID" value="${pd.MEMBERSHIPCARD_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">折扣:</td>
				<td><input type="text" name="DISCOUNT" id="DISCOUNT" value="${pd.DISCOUNT}" maxlength="32" placeholder="这里输入折扣" title="折扣"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">领取开始时间:</td>
				<td><input type="text" name="GOSTARTTIME" id="GOSTARTTIME" value="${pd.GOSTARTTIME}" maxlength="32" placeholder="这里输入领取开始时间" title="领取开始时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">领取截至时间:</td>
				<td><input type="text" name="GOENDTIME" id="GOENDTIME" value="${pd.GOENDTIME}" maxlength="32" placeholder="这里输入领取截至时间" title="领取截至时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">使用开始时间:</td>
				<td><input type="text" name="USESTARTTIME" id="USESTARTTIME" value="${pd.USESTARTTIME}" maxlength="32" placeholder="这里输入使用开始时间" title="使用开始时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">使用截至时间:</td>
				<td><input type="text" name="USEENDTIME" id="USEENDTIME" value="${pd.USEENDTIME}" maxlength="32" placeholder="这里输入使用截至时间" title="使用截至时间"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">数量:</td>
				<td><input type="text" name="NUMBER" id="NUMBER" value="${pd.NUMBER}" maxlength="32" placeholder="这里输入数量" title="数量"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">创建会员卡时间:</td>
				<td><input type="text" name="CREATETIME" id="CREATETIME" value="${pd.CREATETIME}" maxlength="32" placeholder="这里输入创建会员卡时间" title="创建会员卡时间"/></td>
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