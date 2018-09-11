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
			
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="ordermanger/${msg }.do" name="Form" id="Form" method="post">
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">订单号:</td>
				<td><input type="text" name="ORDER_ID" id="ORDER_ID" value="${orderPd.ORDER_ID}" maxlength="32" placeholder="这里输入订单号" title="订单号"/></td>
			</tr>
			<tr>
			
				<td style="width:70px;text-align: right;padding-top: 13px;">订单状态:</td>
				<td>
					<select id="ORDERTYPE" name="ORDERTYPE" >
						<c:if test="${var.ORDERTYPE==0 && var.RETURN_TYPE==0}">
							<option value ="0" selected="selected">待付款</option>
						</c:if>
						<c:if test="${var.ORDERTYPE==2 && var.RETURN_TYPE==0}">
										待发货
										</c:if>
										<c:if test="${var.ORDERTYPE==3 && var.RETURN_TYPE==0}">
										已发货
										</c:if>
										<c:if test="${var.ORDERTYPE==4 && var.RETURN_TYPE==0}">
										确认收货
										</c:if>
										<c:if test="${var.ORDERTYPE==2 && var.RETURN_TYPE==6}">
										申请退款中
										</c:if>
										<c:if test="${var.ORDERTYPE==2 && var.RETURN_TYPE==7}">
										退款成功
										</c:if>
										<c:if test="${var.ORDERTYPE==2 && var.RETURN_TYPE==8}">
										退款失败
										</c:if>
										<c:if test="${var.ORDERTYPE==2 && var.RETURN_TYPE==9}">
										拒绝退款
										</c:if>
										<c:if test="${var.ORDERTYPE==4 && var.RETURN_TYPE==1}">
										申请退货中
										</c:if>
										<c:if test="${var.ORDERTYPE==4 && var.RETURN_TYPE==2}">
										同意退货
										</c:if>
										<c:if test="${var.ORDERTYPE==4 && var.RETURN_TYPE==3}">
										拒绝退货 
										</c:if>
										<c:if test="${var.ORDERTYPE==4 && var.RETURN_TYPE==4}">
										退货成功
										</c:if>
										<c:if test="${var.ORDERTYPE==4 && var.RETURN_TYPE==5}">
										退货失败
										</c:if>
							<c:if test="${orderPd.ORDERTYPE==0 && orderPd.RETURN_TYPE==0}">
							
							</c:if>
							<c:if test="${orderPd.ORDERTYPE!=0 && orderPd.RETURN_TYPE==0}">
							<option value ="0" >待付款</option>
							</c:if>
							<c:if test="${orderPd.ORDERTYPE==2 && orderPd.RETURN_TYPE==0}">
							<option value ="2" selected="selected">待发货</option>
							</c:if>
							<c:if test="${orderPd.ORDERTYPE!=2 && orderPd.RETURN_TYPE==0}">
							<option value ="2" >待发货</option>
							</c:if>
							<c:if test="${orderPd.ORDERTYPE==3 && orderPd.RETURN_TYPE==0}">
							<option value ="3" selected="selected">已发货</option>
							</c:if>
							<c:if test="${orderPd.ORDERTYPE!=3 && orderPd.RETURN_TYPE==0}">
							<option value ="3" >已发货</option>
							</c:if>
							<c:if test="${orderPd.ORDERTYPE==4 && orderPd.RETURN_TYPE==0}">
							<option value ="4" selected="selected">确认收货</option>
							</c:if>
							<c:if test="${orderPd.ORDERTYPE!=4 && orderPd.RETURN_TYPE==0}">
							<option value ="4" >确认收货</option>
							</c:if>
							
					</select>
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