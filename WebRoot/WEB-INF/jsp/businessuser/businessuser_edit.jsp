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
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#BUSINESSUSERNAME").val()==""){
			$("#BUSINESSUSERNAME").tips({
				side:3,
	            msg:'请输入商家名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BUSINESSUSERNAME").focus();
			return false;
		}
		if($("#BUSINESSPWD").val()==""){
			$("#BUSINESSPWD").tips({
				side:3,
	            msg:'请输入登录密码',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BUSINESSPWD").focus();
			return false;
		}
		if($("#msg").val()=="edit"){
			
		}else{
			if($("#BUSINESSPWD1").val()==""){
				$("#BUSINESSPWD1").tips({
					side:3,
		            msg:'请输入确认登录密码',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#BUSINESSPWD1").focus();
				return false;
			}
			if($("#BUSINESSPWD").val()!="" && $("#BUSINESSPWD1").val()!=""){
				if($("#BUSINESSPWD").val()!=$("#BUSINESSPWD1").val()){
					$("#BUSINESSPWD1").tips({
						side:3,
			            msg:'确认登录密码错误',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#BUSINESSPWD1").focus();
					return false;
				}
			}
		}
		
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
		if($("#COMPANYADDRESS").val()==""){
			$("#COMPANYADDRESS").tips({
				side:3,
	            msg:'请输入公司地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COMPANYADDRESS").focus();
			return false;
		}
		if($("#COMPANYNAME").val()==""){
			$("#COMPANYNAME").tips({
				side:3,
	            msg:'请输入公司名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#COMPANYNAME").focus();
			return false;
		}
		if($("#BUSINESSLICENSEURL").val()==""){
			$("#BUSINESSLICENSEURL").tips({
				side:3,
	            msg:'请输入营业执照',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#BUSINESSLICENSEURL").focus();
			return false;
		}
		if($("#SHOPNAME").val()==""){
			$("#SHOPNAME").tips({
				side:3,
	            msg:'请输入店铺名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SHOPNAME").focus();
			return false;
		}
		if($("#SHOPADDRESS").val()==""){
			$("#SHOPADDRESS").tips({
				side:3,
	            msg:'请输入店铺地址',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SHOPADDRESS").focus();
			return false;
		}
		if($("#SHOPLOGO").val()==""){
			$("#SHOPLOGO").tips({
				side:3,
	            msg:'请输入店铺logo',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SHOPLOGO").focus();
			return false;
		}
		if($("#WORK").val()==""){
			$("#WORK").tips({
				side:3,
	            msg:'请输入主营业务',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WORK").focus();
			return false;
		}
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
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="businessuser/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="BUSINESSUSER_ID" id="BUSINESSUSER_ID" value="${pd.BUSINESSUSER_ID}"/>
		<input type="hidden" name="msg" id="msg" value="${pd.msg}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">手机号:</td>
				<td><input type="text" name="TEL" id="TEL" value="${pd.TEL}" maxlength="32" placeholder="这里输入手机" title="手机"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">登录密码:</td>
				<td><input type="password" name="BUSINESSPWD" id="BUSINESSPWD" value="" maxlength="32" placeholder="这里输入登录密码" title="登录密码"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">确认密码登录密码:</td>
				<td><input type="password" name="BUSINESSPWD1" id="BUSINESSPWD1" value="" maxlength="32" placeholder="这里输入登录密码" title="登录密码"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">公司名称:</td>
				<td><input type="text" name="COMPANYNAME" id="COMPANYNAME" value="${pd.COMPANYNAME}" maxlength="32" placeholder="这里输入公司名称" title="公司名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">公司地址:</td>
				<td><input type="text" name="COMPANYADDRESS" id="COMPANYADDRESS" value="${pd.COMPANYADDRESS}" maxlength="32" placeholder="这里输入公司地址" title="公司地址"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">联系人姓名:</td>
				<td><input type="text" name="CONTACTSNAME" id="CONTACTSNAME" value="${pd.CONTACTSNAME}" maxlength="32" placeholder="这里输入联系人姓名" title="联系人姓名"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">营业执照:</td>
				<td>
				
				<label style="display:block;width:100%;height:100%;">
	        		<input name="BUSINESSLICENSEURL" id="BUSINESSLICENSEURL" value="${pd.BUSINESSLICENSEURL}" type="hidden"/>
		        	<input id="uploadImg" name="myfile" type="file" onchange="uploadphoto('uploadImg')" style="display:none;"/> 
		        	<c:if test="${pd.BUSINESSLICENSEURL=='' || pd.BUSINESSLICENSEURL==null}">
			        <img class="touXiang" id="shareImg" src="${pageContext.request.contextPath }/plugins/webuploader/image.png"/>
			        </c:if>
			        <c:if test="${pd.BUSINESSLICENSEURL!='' && pd.BUSINESSLICENSEURL!=null}">
			        <img class="touXiang" id="shareImg" src="<%=OSSUrl %>${pd.BUSINESSLICENSEURL}"/>
			        </c:if>
	             </label>
				
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商家名称:</td>
				<td><input type="text" name="BUSINESSUSERNAME" id="BUSINESSUSERNAME" value="${pd.BUSINESSUSERNAME}" maxlength="32" placeholder="这里输入商家名称" title="商家名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">店铺名称:</td>
				<td><input type="text" name="SHOPNAME" id="SHOPNAME" value="${pd.SHOPNAME}" maxlength="32" placeholder="这里输入店铺名称" title="店铺名称"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">店铺地址:</td>
				<td><input type="text" name="SHOPADDRESS" id="SHOPADDRESS" value="${pd.SHOPADDRESS}" maxlength="32" placeholder="这里输入店铺地址" title="店铺地址"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">店铺logo:</td>
				<td>
				
				<label style="display:block;width:100%;height:100%;">
	        		<input name="SHOPLOGO" id="SHOPLOGO" value="${pd.SHOPLOGO}" type="hidden"/>
		        	<input id="uploadImg1" name="myfile" type="file" onchange="uploadphoto1('uploadImg1')" style="display:none;"/> 
		        	
		        	<c:if test="${pd.SHOPLOGO=='' || pd.SHOPLOGO==null}">
			        <img class="touXiang" id="shareImg1" src="${pageContext.request.contextPath }/plugins/webuploader/image.png"/>
			        <span >（请上传大小在100k以下，尺寸为60*60的店铺logo图片）</span>
			        </c:if>
			        <c:if test="${pd.SHOPLOGO!='' && pd.SHOPLOGO!=null}">
			        <img class="touXiang" id="shareImg1" src="<%=OSSUrl %>${pd.SHOPLOGO}"/>
			        <span >（请上传大小在100k以下，尺寸为60*60的店铺logo图片）</span>
			        </c:if>
	             </label>
				
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">主营业务:</td>
				<td>
				<span>已选:${nameString}</span>
				<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center">
						
						</th>
						<th class="center">序号</th>
						<th class="center">选择主营业务</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty workList}">
						
						<c:forEach items="${workList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='WORK' value="${var.WORK_ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
								<td class='center' >${var.WORKNAME}</td>
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