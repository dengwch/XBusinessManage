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
		<style>
			 *{
            margin:0;
            padding:0;
            box-sizing: border-box;
        }
        .edit
        {
            margin: 0 10px;
        }
        .wholeBox a
        {
            color: #2489c5;
            cursor: pointer;
        }
        .wholeBox p
        {
            height: 30px;
        }
        label {font-size:12px;cursor:pointer;}
        label i {font-size:12px;font-style:normal;display:inline-block;width:15px;height:15px;text-align:center;line-height:12px;color:#fff;vertical-align:middle;margin:-2px 2px 1px 0px;border:#2489c5 1px solid;}
        input[type="checkbox"],input[type="radio"] {display:none;}
        input[type="radio"] + i {border-radius:7px;}
        input[type="checkbox"]:checked + i,input[type="radio"]:checked + i {background:#2489c5;}
		</style>
<script type="text/javascript">
	
	
	//保存
	function save(){
// 			if($("#AREAID").val()==""){
// 			$("#AREAID").tips({
// 				side:3,
// 	            msg:'请输入地区',
// 	            bg:'#AE81FF',
// 	            time:2
// 	        });
// 			$("#AREAID").focus();
// 			return false;
// 		}
		if($("#FIRSTHEAVY").val()==""){
			$("#FIRSTHEAVY").tips({
				side:3,
	            msg:'请输入首重',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#FIRSTHEAVY").focus();
			return false;
		}
		if($("#FIRSTHEAVYPRICE").val()==""){
			$("#FIRSTHEAVYPRICE").tips({
				side:3,
	            msg:'请输入首重运费',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#FIRSTHEAVYPRICE").focus();
			return false;
		}
		if($("#CONTINUEDHEAVY").val()==""){
			$("#CONTINUEDHEAVY").tips({
				side:3,
	            msg:'请输入续重',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTINUEDHEAVY").focus();
			return false;
		}
		if($("#CONTINUEDHEAVYPRICE").val()==""){
			$("#CONTINUEDHEAVYPRICE").tips({
				side:3,
	            msg:'请输入续重运费',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTINUEDHEAVYPRICE").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="logisticslist/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="LOGISTICSLIST_ID" id="LOGISTICSLIST_ID" value="${pd.LOGISTICSLIST_ID}"/>
		<input type="hidden" name="LOGISTICS_ID" id="LOGISTICS_ID" value="${pd.LOGISTICS_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">地区:</td>
				<td>
<!-- 				<input type="text" name="AREAID" id="AREAID" value="${pd.AREAID}" maxlength="32" placeholder="这里输入地区" title="地区"/> -->
<!-- 					<select name="AREAID" id="AREAID"> -->
					<c:forEach items="${listArea }" var="area">
<!-- 						<option value="${area.AREA_ID}" <c:if test="${area.AREA_ID==pd.AREAID }">selected</c:if>> -->
<!-- 						${area.AREA_NAME } -->
<!-- 						</option> -->
<label style='display:inline-block;'><input  type="checkbox" name="diqu" value="${area.AREA_ID}" <c:if test="${area.flag==1 }">checked="checked"</c:if>><i>✓</i>${area.AREA_NAME }</label>
						
					</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">首重:</td>
				<td><input type="text" name="FIRSTHEAVY" id="FIRSTHEAVY" value="${pd.FIRSTHEAVY}" maxlength="32" placeholder="这里输入首重" title="首重"/>g</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">首重运费:</td>
				<td><input type="text" name="FIRSTHEAVYPRICE" id="FIRSTHEAVYPRICE" value="${pd.FIRSTHEAVYPRICE}" maxlength="32" placeholder="这里输入首重运费" title="首重运费"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">续重:</td>
				<td><input type="text" name="CONTINUEDHEAVY" id="CONTINUEDHEAVY" value="${pd.CONTINUEDHEAVY}" maxlength="32" placeholder="这里输入续重" title="续重"/>g</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">续重运费:</td>
				<td><input type="text" name="CONTINUEDHEAVYPRICE" id="CONTINUEDHEAVYPRICE" value="${pd.CONTINUEDHEAVYPRICE}" maxlength="32" placeholder="这里输入续重运费" title="续重运费"/></td>
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