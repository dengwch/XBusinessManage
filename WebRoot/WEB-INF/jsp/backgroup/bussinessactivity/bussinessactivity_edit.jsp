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
			if($("#CONDITIONVALUE").val()==""){
			$("#CONDITIONVALUE").tips({
				side:3,
	            msg:'请输入条件数',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONDITIONVALUE").focus();
			return false;
		}
		if($("#DISCOUNTVALUE").val()==""){
			$("#DISCOUNTVALUE").tips({
				side:3,
	            msg:'请输入优惠值',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#DISCOUNTVALUE").focus();
			return false;
		}
		if($("#TYPE").val()==""){
			$("#TYPE").tips({
				side:3,
	            msg:'请输入优惠类型1：满钱包邮；2：满件数包邮；3：买几送几',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TYPE").focus();
			return false;
		}
		if($("#CREATETIME").val()==""){
			$("#CREATETIME").tips({
				side:3,
	            msg:'请输入创建时间',
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
	<form action="bussinessactivity/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="BUSSINESSACTIVITY_ID" id="BUSSINESSACTIVITY_ID" value="${pd.BUSSINESSACTIVITY_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
		<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">优惠名称:</td>
				<td><input type="text" name="ACTIVITYNAME" id="ACTIVITYNAME" value="${pd.ACTIVITYNAME}" maxlength="32" placeholder="这里输入优惠名称" title="优惠名称"/></td>
			</tr>
			<!--<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">条件值:</td>
				<td><input type="text" name="CONDITIONVALUE" id="CONDITIONVALUE" value="${pd.CONDITIONVALUE}" maxlength="32" placeholder="这里输入条件数" title="条件数"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">优惠值:</td>
				<td><input type="text" name="DISCOUNTVALUE" id="DISCOUNTVALUE" value="${pd.DISCOUNTVALUE}" maxlength="32" placeholder="这里输入优惠值" title="优惠值"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">优惠类型</td>
				<td>
				<select name="TYPE" id="TYPE" >
				<c:if test="${pd.TYPE==1}">
					<option value="1" selected="selected">满钱包邮</option>
					</c:if>
					<c:if test="${pd.TYPE!=1}">
					<option value="1" >满钱包邮</option>
					</c:if>
					<c:if test="${pd.TYPE==2}">
					<option value="2" selected="selected">满件数包邮</option>
					</c:if>
					<c:if test="${pd.TYPE!=2}">
					<option value="2" >满件数包邮</option>
					</c:if>
					<c:if test="${pd.TYPE==3}">
					<option value="3" selected="selected">买几送几</option>
					</c:if>
					<c:if test="${pd.TYPE!=3}">
					<option value="3">买几送几</option>
					</c:if>
				</select>
				</td>
			</tr>
			
			--><tr>
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