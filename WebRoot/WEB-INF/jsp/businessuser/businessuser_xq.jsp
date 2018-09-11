<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
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
	</head>
<body>
	<form action="businessuser/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="BUSINESSUSER_ID" id="BUSINESSUSER_ID" value="${pd.BUSINESSUSER_ID}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">公司名称:</td>
				<td><span>${pd.COMPANYNAME}</span></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">公司地址:</td>
				<td><span>${pd.COMPANYADDRESS}</span></td>
			</tr>
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">营业执照:</td>
				<td>
				
	        		<input name="BUSINESSLICENSEURL" id="BUSINESSLICENSEURL" value="${pd.BUSINESSLICENSEURL}" type="hidden"/>
		        	<c:if test="${pd.BUSINESSLICENSEURL=='' || pd.BUSINESSLICENSEURL==null}">
			        <img class="touXiang" id="shareImg" src="${pageContext.request.contextPath }/plugins/webuploader/image.png"/>
			        </c:if>
			        <c:if test="${pd.BUSINESSLICENSEURL!='' && pd.BUSINESSLICENSEURL!=null}">
			        <img class="touXiang" id="shareImg" src="<%=OSSUrl %>${pd.BUSINESSLICENSEURL}"/>
			        </c:if>
				
				</td>
			</tr>
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商家名称:</td>
				<td><span>${pd.BUSINESSUSERNAME}</span></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">店铺名称:</td>
				<td><span>${pd.SHOPNAME}</span></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">店铺地址:</td>
				<td><span>${pd.SHOPADDRESS}</span></td>
			</tr>
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系电话:</td>
				<td><span>${pd.TEL}</span></td>
			</tr>
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">店铺logo:</td>
				<td>
				
	        		<input name="SHOPLOGO" id="SHOPLOGO" value="${pd.SHOPLOGO}" type="hidden"/>
		        	<c:if test="${pd.SHOPLOGO=='' || pd.SHOPLOGO==null}">
			        <img class="touXiang" id="shareImg1" src="${pageContext.request.contextPath }/plugins/webuploader/image.png"/>
			        </c:if>
			        <c:if test="${pd.SHOPLOGO!='' && pd.SHOPLOGO!=null}">
			        <img class="touXiang" id="shareImg1" src="<%=OSSUrl %>${pd.SHOPLOGO}"/>
			        </c:if>
				
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">主营业务:</td>
				<td>
					<span>${nameString}</span>
				</td>
			</tr>
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">优惠信息:</td>
				<td>
					<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						
						<th class="center">序号</th>
						<th class="center">优惠名称</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty activityList}">
						
						<c:forEach items="${activityList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class='center' >${var.ACTIVITYNAME}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
				</td>
			</tr>
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">会员卡信息:</td>
				<td>
					<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						
						<th class="center">序号</th>
						<th class="center">折扣数</th>
						<th class="center">有效期</th>
						<th class="center">总份数</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty cardList}">
						
						<c:forEach items="${cardList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class='center' >${var.DISCOUNT}折</td>
								<td class='center' >${var.USESTARTTIME}至${var.USEENDTIME}</td>
								<td class='center' >${var.NUMBER}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
				
				</tbody>
			</table>
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