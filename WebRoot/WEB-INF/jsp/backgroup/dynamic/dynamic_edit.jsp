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
		<script src="ckeditor/ckeditor.js"></script>
<script type="text/javascript">
var editor;

//The instanceReady event is fired, when an instance of CKEditor has finished
//its initialization.
CKEDITOR.on( 'instanceReady', function( ev ) {
	editor = ev.editor;
	// Show this "on" button.
//		document.getElementById( 'readOnlyOn' ).style.display = '';

	// Event fired when the readOnly property changes.
	editor.on( 'readOnly', function() {
//			document.getElementById( 'readOnlyOn' ).style.display = this.readOnly ? 'none' : '';
//			document.getElementById( 'readOnlyOff' ).style.display = this.readOnly ? '' : 'none';
	});
});

function toggleReadOnly( isReadOnly ) {
	// Change the read-only state of the editor.
	// http://docs.ckeditor.com/#!/api/CKEDITOR.editor-method-setReadOnly
//		editor.setReadOnly( isReadOnly );
}
	
	
	//保存
	function save(){
			if($("#TITLE").val()==""){
			$("#TITLE").tips({
				side:3,
	            msg:'请输入标题',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TITLE").focus();
			return false;
		}
		if($("#CONTEXT").val()==""){
			$("#CONTEXT").tips({
				side:3,
	            msg:'请输入内容',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CONTEXT").focus();
			return false;
		}
		if('${msg}'=='save'){
		if($("#PHOTO").val()==""){
			$("#PHOTO").tips({
				side:3,
	            msg:'请选择照片',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PHOTO").focus();
			return false;
		}
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="dynamic/${msg }.do" name="Form" id="Form" method="post" enctype="multipart/form-data">
		<input type="hidden" name="id" id="id" value="${pd.id}"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">标题:</td>
				<td><input type="text" name="TITLE" id="TITLE" value="${pd.TITLE}" maxlength="32" placeholder="这里输入标题" title="标题"/></td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">内容:</td>
				<td>
				<textarea class="ckeditor" rows="30" cols="50" id="editor1" name="CONTEXT">
									${pd.CONTEXT }								
								</textarea>
<!-- 				<input type="text" name="CONTEXT" id="CONTEXT" value="${pd.CONTEXT}" maxlength="32" placeholder="这里输入内容" title="内容"/> -->
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">照片:</td>
				<td>
				<input type="file" name="PHOTO" id="PHOTO" />
				<c:if test="${pd.PHOTO!=null&&pd.PHOTO!='' }">
				<img src="<%=OSSUrl %>${pd.PHOTO }" style="width:200px;">
					
				</c:if>
<!-- 				<input type="text" name="PHOTO" id="PHOTO" value="${pd.PHOTO}" maxlength="32" placeholder="这里输入照片" title="照片"/> -->
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