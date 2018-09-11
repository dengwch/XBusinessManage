<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String  IMGURL=(String) request.getSession().getAttribute("IMGURL");
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
		<style>
			 *{
            margin:0;
            padding:0;
            box-sizing: border-box;
        }
        
        
        
		 a{
            color:#000;
            cursor: pointer;
            text-decoration: none;
        }
        input,button,textarea{
            border:none;
            outline: none;
        }
        ul{
            list-style: none;
        }

        body{
            font: 12px/1.5  Arial,'Hiragino Sans GB','Microsoft YaHei', YaHei, Helvetica, sans-serif;
        }
        .msgTable{
            width:80%;
            border-collapse: collapse;
            border-right:1px solid #ccc;
            border-bottom:1px solid #ccc; 
            margin: 30px;
        }
        .msgTable td,.msgTable th{
            text-align: center;
            vertical-align: middle;
            font-size: 16px;
           /*  height:60px; */
            border:none;
            color: rgba(0,0,0,0.7);
        }
        .redB
        {
        color:red;
        }
        .msgTable tr:nth-child(even)
        {
            background: #eee;

        }
        .msgTable td ,.msgTable th
        {
           border-left:1px solid #ccc;
            border-top:1px solid #ccc;
        }
        .msgTable td input[type = number]
        {
            width: 60%;
            margin: 0 10%;
            border:1px solid #ccc;
            padding-left:20px;
            border-radius: 6px;
        }
        .msgTable thead
        {
            border:1px solid #ccc;
            background: #eee;
        }
        .msgTable th a,.msgTable td a
        {
            color: #006DCC;
            display: inline-block;
            padding:0 10px;
            /*background:#006DCC;*/
        }     
          
        .addPic_Kee{
        display:inline-block;
        background:#268CBB;
        height:30px;
        line-height:30px;
        color:#fff;
        padding:0 10px;
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
            /* height: 30px; */
            margin:10px 0 0 0;
        }
        label {font-size:12px;cursor:pointer;}
        label i {font-size:12px;font-style:normal;display:inline-block;width:15px;height:15px;text-align:center;line-height:12px;color:#fff;vertical-align:middle;margin:-2px 2px 1px 0px;border:#2489c5 1px solid;}
        input[type="checkbox"],input[type="radio"] {display:none;}
        input[type="radio"] + i {border-radius:7px;}
        input[type="checkbox"]:checked + i,input[type="radio"]:checked + i {background:#2489c5;}
		</style>
		<style type="text/css">
	
	
	* { margin:0; padding:0;}
#a{ position:fixed; bottom:0; right:0; width:100%; height:20px; text-align: center; }
* html #a{position:absolute;right:18px;}
	</style>
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
			if($("#CLASSIFICATION_ID").val()==""){
			$("#CLASSIFICATION_ID").tips({
				side:3,
	            msg:'请输入分类ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#CLASSIFICATION_ID").focus();
			return false;
		}
		if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入商品名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		/* if($("#NUMBER").val()==""){
			$("#NUMBER").tips({
				side:3,
	            msg:'请输入商品货号',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NUMBER").focus();
			return false;
		} */
		if($("#PRICE").val()=="" || $("#PRICE").val()=="0" || $("#PRICE").val()==null){
			$("#PRICE").tips({
				side:3,
	            msg:'请输入售价',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PRICE").focus();
			return false;
		}else{
			
			
		    var re = /^[0-9]+\.?[0-9]*$/;
		    if (!re.test($("#PRICE").val())) 
		    {
		    	$("#PRICE").tips({
					side:3,
		            msg:'请输入正确售价(正数或者小数)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#PRICE").focus();
				return false;
		    } 
		}
		/*
		if($("#STOCK").val()==""){
			$("#STOCK").tips({
				side:3,
	            msg:'请输入库存',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#STOCK").focus();
			return false;
		}else{
			var re = /^[1-9]*[1-9][0-9]*$/;
		    if (!re.test($("#STOCK").val())){
		    	$("#STOCK").tips({
					side:3,
		            msg:'请输入正确库存(整数)',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#STOCK").focus();
				return false;
		    }
			
		}
		*/
		/*if($("#WEIGHT").val()==""){
			$("#WEIGHT").tips({
				side:3,
	            msg:'请输入重量',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#WEIGHT").focus();
			return false;
		}else{
			 var re = /^[0-9]+\.?[0-9]*$/;
			    if (!re.test($("#WEIGHT").val())) 
			    {
			    	$("#WEIGHT").tips({
						side:3,
			            msg:'请输入正确重量(正数或者小数)',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#WEIGHT").focus();
					return false;
			    }
		}
			
		if($("#VOLUME").val()==""){
			$("#VOLUME").tips({
				side:3,
	            msg:'请输入体积',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#VOLUME").focus();
			return false;
		}else{
			 var re = /^[0-9]+\.?[0-9]*$/;
			    if (!re.test($("#VOLUME").val())) 
			    {
			    	$("#VOLUME").tips({
						side:3,
			            msg:'请输入正确体积(正数或者小数)',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#VOLUME").focus();
					return false;
			    }
		}*/
		if($("#ZK").val()==""){
			$("#ZK").tips({
				side:3,
	            msg:'请输入折扣',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#ZK").focus();
			return false;
		}else{
			 var re = /^[0-9]+\.?[0-9]*$/;
			    if (!re.test($("#ZK").val())) 
			    {
			    	$("#ZK").tips({
						side:3,
			            msg:'请输入正确折扣(正数或者小数)',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#ZK").focus();
					return false;
			    }
		}
		if($("#IMG").val()==""){
			$("#IMG").tips({
				side:3,
	            msg:'请输入商品图片',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#IMG").focus();
			return false;
		}
		/*if($("#LOCATION").val()==""){
			$("#LOCATION").tips({
				side:3,
	            msg:'请输入所在地',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#LOCATION").focus();
			return false;
		}
		if($("#FREIGHTTEMPLATE").val()==""){
			$("#FREIGHTTEMPLATE").tips({
				side:3,
	            msg:'请输入运费模板',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#FREIGHTTEMPLATE").focus();
			return false;
		}
		if($("#PERCENTAGECOMMISSION").val()==""){
			$("#PERCENTAGECOMMISSION").tips({
				side:3,
	            msg:'请输入分佣比例',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PERCENTAGECOMMISSION").focus();
			return false;
		}
		
		if($("#PERCENTAGECOMMISSIONFIR").val()==""){
			$("#PERCENTAGECOMMISSIONFIR").tips({
				side:3,
	            msg:'请输入一级分佣比例',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PERCENTAGECOMMISSIONFIR").focus();
			return false;
		}else{
			 var re = /^[0-9]+\.?[0-9]*$/;
			    if (!re.test($("#PERCENTAGECOMMISSIONFIR").val())) 
			    {
			    	$("#PERCENTAGECOMMISSIONFIR").tips({
						side:3,
			            msg:'请输入正确一级分佣比例(正数或者小数)',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#PERCENTAGECOMMISSIONFIR").focus();
					return false;
			    }
		}
		
		
		if($("#PERCENTAGECOMMISSIONSEC").val()==""){
			$("#PERCENTAGECOMMISSIONSEC").tips({
				side:3,
	            msg:'请输入二级分佣比例',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#PERCENTAGECOMMISSIONSEC").focus();
			return false;
		}else{
			 var re = /^[0-9]+\.?[0-9]*$/;
			    if (!re.test($("#PERCENTAGECOMMISSIONSEC").val())) 
			    {
			    	$("#PERCENTAGECOMMISSIONSEC").tips({
						side:3,
			            msg:'请输入正确二级分佣比例(正数或者小数)',
			            bg:'#AE81FF',
			            time:2
			        });
					$("#PERCENTAGECOMMISSIONSEC").focus();
					return false;
			    }
		}
		
		
		
		
		if($("#TYPE").val()==""){
			$("#TYPE").tips({
				side:3,
	            msg:'请输入上架模式',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#TYPE").focus();
			return false;
		}*/
		var num=$("#num").val();
		for(var i=0;i<num;i++){
			if($("#kucun"+i).val()==""){
				$("#kucun"+i).tips({
					side:3,
		            msg:'请输入库存',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#kucun"+i).focus();
				return false;
			}
			if($("#jg"+i).val()==""){
				$("#jg"+i).tips({
					side:3,
		            msg:'请输入价格',
		            bg:'#AE81FF',
		            time:2
		        });
				$("#jg"+i).focus();
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
	<form action="commodity/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="COMMODITY_ID" id="COMMODITY_ID" value="${pd.COMMODITY_ID}"/>
		<input type="hidden" name="CLASSIFICATION_ID1" id="CLASSIFICATION_ID1" value="${pd.CLASSIFICATION_ID }"/>
		<input type="hidden" name="num" id="num" value="0"/>
		<input type="hidden" name="imgID" id="imgID" value="<%=IMGURL %>"/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">所属分类:</td>
				<td>
<!-- 				<input type="text" name="CLASSIFICATION_ID" id="CLASSIFICATION_ID" value="${pd.CLASSIFICATION_ID}" maxlength="32" placeholder="这里输入分类ID" title="分类ID"/> -->
				<select  name="SQFLAG1" id="SQFLAG1"  style="vertical-align:top;width: 135px;" onchange="findShi()">
					<option value="">一级分类</option>
					 <c:forEach items="${listClass}" var="LIclass">
					 
       					<option value ="${LIclass.CLASSIFICATION_ID }" <c:if test="${LIclass.CLASSIFICATION_ID==pd.SUPERIOR }">selected</c:if>>${LIclass.NAME }</option>
      					</c:forEach>
			  	</select>
				<select name="CLASSIFICATION_ID" id="CLASSIFICATION_ID"  style="vertical-align:top;width: 135px;" >
					<option value="">二级分类</option>
			  	</select>
				
				<!--<select name="CLASSIFICATION_ID" id="CLASSIFICATION_ID">
					<c:forEach items="${listClass }" var="fenlei">
						<option value="${fenlei.CLASSIFICATION_ID }" <c:if test="${fenlei.CLASSIFICATION_ID==pd.CLASSIFICATION_ID }">selected</c:if>>
						${fenlei.NAME }</option>
					</c:forEach>
				</select>
				--></td>
			</tr>
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">商品名称:<b class="redB">*</b></td>
				<td><input type="text" name="NAME" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入商品名称" title="商品名称" style="height:30px !important;"/></td>
			</tr>
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">商品货号:</td>
				<td><input type="number" name="NUMBER" id="NUMBER" value="${pd.NUMBER}" maxlength="32" placeholder="这里输入商品货号" title="商品货号" style="height:30px !important;"/></td>
			</tr>
			
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">商品规格:</td>
				<td>
					<a onclick="toQ1('天津市西青区辛老公路')" id="toQ1"><span id="toQ2">〇</span>统一规格</a>
					<a onclick="duoGG1('2')" id="duoGG1"><span id="duoGG2">〇</span>多规格</a><br/>
					<a style="display: none;" id="duoG" onclick="add1()" class="addPic_Kee">添加</a>
				 <div class="wholeBox" id="guige">

   			 	</div>
				<table  class="msgTable" id="biaoge" cellspacing="1">
				</table>
				</td>
			</tr>
			
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">原价:<b class="redB">*</b></td>
				<td><input type="number" name="PRICE" id="PRICE" value="${pd.PRICE}" maxlength="32" placeholder="这里输入售价" title="售价" style="height:30px !important;"/>元</td>
			</tr>
			
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">折扣:<b class="redB">*</b></td>
				<td><input type="number" name="ZK" id="ZK" value="${pd.ZK }" maxlength="32" placeholder="这里输入折扣" title="折扣" style="height:30px !important;"/>折</td>
			</tr>
			
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">商品图片:</td>
				<td id="imgh">
				 <c:forEach items="${varOList}" var="OList">
					 <div style="height:200px;width:200px;margin-top:10px;"><img src="<%=OSSUrl %>${OList.IMGURL }" /></div>
      			</c:forEach>
				
				<a onclick="add()" class="addPic_Kee">添加图片</a>
				
				</td>
			</tr>
			
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商品简介:</td>
				<td>
						<textarea id="WORDREMARK" name="WORDREMARK">${pd.WORDREMARK}</textarea>
				</td>
			</tr>
			<tr>
				<td style="width:70px;text-align: right;padding-top: 13px;">商品详情:</td>
				<td>
				
				<label style="display:block;width:100%;height:100%;">
	        		<input name="DETAIL" id="DETAIL" value="${pd.DETAIL}" type="hidden"/>
		        	<input id="uploadImg" name="myfile" type="file" onchange="uploadphoto('uploadImg')" style="display:none;"/> 
		        	<c:if test="${pd.DETAIL=='' || pd.DETAIL==null}">
			        <img class="touXiang" id="shareImg" src="${pageContext.request.contextPath }/plugins/webuploader/image.png"/>
			        </c:if>
			        <c:if test="${pd.DETAIL!='' && pd.DETAIL!=null}">
			        <img class="touXiang" id="shareImg" src="<%=OSSUrl %>${pd.DETAIL}"/>
			        </c:if>
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
		<input value="<%=OSSUrl %>" id="ossurl" style="display:none">
		<div id="qrt1" style="display:none"><input type="hidden" id="en1" value="0"/></div>
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
		var we1=0;
		function toQ1(id){
			//window.location.href='${pageContext.request.contextPath}/commodity/findZB.do?address='+id;
				 if(document.getElementById("guige").innerHTML ==""){
					 add1();
					 $("#toQ2").html("√");
						$("#duoGG2").html("〇");
						$("#duoG").hide();
				}else{
					 alert("请先删除已编写的规格");
				}
				
			
			
		}
		
		function duoGG1(id){
			
			//$("#guige").empty();
			 if(document.getElementById("guige").innerHTML !=""){
				 alert("请先删除已编写的规格");
			}else{

				$("#toQ2").html("〇");
				$("#duoGG2").html("√");
				$("#duoG").show();
			}
			
		}
		$(document).ready(function() {
			
			
			var options=$("#SQFLAG1 option:selected");  //获取选中的项
		//alert(options.val());   //拿到选中项的值
			//alert(options.text());   //拿到选中项的文本
			var fatherId = options.val();
    		if (fatherId == "" || null==fatherId) {
    			
    		} else {
    			$.ajax({
        			type : "post",
        			dataType: "json",
        			data : {
        				"id":fatherId
        			},
        			url : "${pageContext.request.contextPath }/commodity/findFenLei.do",
        			success : function(data) {
        				$("#CLASSIFIstr").empty(); 
        				var str = '<option value="">二级分类</option>';
    					$("#CLASSIFIstr").append(str);
        				var list = data.listClassT;
        				var str = "";
        				//alert(list);
        				//alert(list.length);
        				var CLASSIFICATION_ID1=$("#CLASSIFICATION_ID1").val();
        				//alert(CLASSIFICATION_ID1);
        				if(null!=list&&list.length>0){
        					for (var i = 0; i<list.length;i++){
        						//alert(list[i].CLASSIFICATION_ID);
        						//alert(list[i].NAME);
        						str+="<option value='"+list[i].CLASSIFICATION_ID+"'";
        						if(null!=CLASSIFICATION_ID1&&CLASSIFICATION_ID1!=''){
        							if(CLASSIFICATION_ID1==list[i].CLASSIFICATION_ID){
        								str+="selected";
        							}
        						}else{
        							
        						}
        						str+=" >"+ list[i].NAME+ "</option>";
        						
        					}
        					$("#CLASSIFICATION_ID").append(str);
        				}else{
        					str+="<option value='暂无'>暂无</option>";
        					$("#CLASSIFICATION_ID").append(str);
        				}
    					
    					
        			}
        		});
    		}
		
	});
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			findGuige();
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
		                		
		                			$("#DETAIL").val(data.msg);
		                			$("#shareImg").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
		                		
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
	       		<%--$.ajax({
					type : "post",
					dataType: "json",
					data:{"COMMODITY_ID":$("#COMMODITY_ID").val()},
					url : "${pageContext.servletContext.contextPath}/commodityimg/photoNum.do",
					success : function(data) {
						if(data.num>10){
							alert("上传图片不能超过10张！");
				       		return false;
						}
					}
			  	});--%>
	       		return true;
	       	}
       }
		//添加图片
		function add(){
			var ossurl=$("#ossurl").val();
			var COMMODITY_ID='${pd.COMMODITY_ID}';
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>pictures/goAdd.do?COMMODITY_ID='+COMMODITY_ID;
			 diag.Width = 600;
			 diag.Height = 490;
			 diag.CancelEvent = function(){ //关闭事件
				 $.ajax({
						type : "post",
						dataType: "json",
						
						url : "${pageContext.servletContext.contextPath}/pictures/hQSession.do",
						success : function(data) {
							if(data.msg=='1'){
								
								var imgID=data.IMGURL;
								 //alert(imgID);
								 var imgIDs =imgID.split("#");
							    	for(var i=0;i<imgIDs.length;i++){
							    		if(imgIDs[i]!=null && imgIDs[i]!=""){
							    			var str="<div style='height:200px;width:200px;margin-top:10px;'><img src="+ossurl+imgIDs[i]+" /></div>";
								    		$("#imgh").append(str);
								    		if(i==imgIDs.length-1){
								    			$.ajax({
													type : "post",
													dataType: "json",
													
													url : "${pageContext.servletContext.contextPath}/pictures/removeSess.do",
													success : function(data) {
														if(data.msg=='1'){
															
														}
													}
											  	});
								    		}
							    		}
							    		
							    	};
							}
						}
				  	});
				 
			 	
// 				 if('${page.currentPage}' == '0'){
// 					 top.jzts();
// 					 setTimeout("self.location=self.location",100);
// 				 }else{
// 					 nextPage(${page.currentPage});
// 				 }
				diag.close();
			 };
			 diag.show();
		}
		
		//添加规格
		function add1(){
			
			var COMMODITY_ID='${pd.COMMODITY_ID}';
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>specifications/goAdd.do?COMMODITY_ID='+COMMODITY_ID;
			 diag.Width = 600;
			 diag.Height = 455;
			 diag.CancelEvent = function(){ //关闭事件
				 $("#qrt1").empty();
				$("#qrt1").append("<input type='hidden' id='en1' value='0'/>");
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 
				}
				diag.close();
				findGuige();
			 };
			 diag.show();
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>specifications/goEdit.do?SPECIFICATIONS_ID='+Id;
			 diag.Width = 600;
			 diag.Height = 450;
			 diag.CancelEvent = function(){ //关闭事件
// 				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
// 					 nextPage(${page.currentPage});
// 				}
				diag.close();
				findGuige();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			if(confirm("是否确认删除")){
// 					top.jzts();
					var url = "<%=basePath%>specifications/delete.do?SPECIFICATIONS_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
// 						nextPage(${page.currentPage});
						findGuige();
					});
			};
		}
		
		function findGuige(){
			var id='${pd.COMMODITY_ID}';
			$.ajax({
				type : "get",
				dataType:"json",
				url : '${pageContext.request.contextPath}/commodity/findGuige.do?id='+id,
				success : function(data){
					var flag=data.flag;
					if(flag==1){
						var listSpe = data.listSpe;
						var mapList=data.mapList;
						$("#guige").empty();
						$("#biaoge").html("");
						for (var i = 0; i<listSpe.length;i++){
							var str='';
							str+="<p><span>"+listSpe[i].NAME+"</span><a onclick='edit(\""+listSpe[i].SPECIFICATIONS_ID+"\")' class='edit'>编辑</a><a onclick='del(\""+listSpe[i].SPECIFICATIONS_ID+"\")'>删除</a></p>";
		       				 str+="<p>";
	// 						alert(mapList);
// 							alert(mapList[listSpe[i].SPECIFICATIONS_ID]);
		       				 for(var j=0;j<mapList[listSpe[i].SPECIFICATIONS_ID].length;j++){
		       					str+=" <label style='display:inline-block;'><input onchange='findBiaoGe()' name='gg' type='checkbox' value='"+mapList[listSpe[i].SPECIFICATIONS_ID][j].SPECIFICATIONPROPERTIES_ID+"'><i>✓</i>"+mapList[listSpe[i].SPECIFICATIONS_ID][j].NAME+"</label>"; 
		       				 }
		       				 str+="</p>";
// 		       				 alert(str);
							$("#guige").append(str);
							
						}
						if($("#guige p ").size()>2){
							$("#toQ2").html("〇");
							$("#duoGG2").html("√");
							$("#duoG").show();
						}else if($("#guige p ").size()<=2){
							if($("#guige p ").size()>0){
								$("#toQ2").html("√");
								$("#duoGG2").html("〇");
								$("#duoG").hide();
							}
							
						}
						//alert();
					}
				}
				
			});
		
		}
		//chafenlei
		function findShi(){
    		var fatherId = $("#SQFLAG1").val();
    		if (fatherId == "") {
    			
    		} else {
    			$.ajax({
        			type : "post",
        			dataType: "json",
        			data : {
        				"id":fatherId
        			},
        			url : "${pageContext.request.contextPath }/commodity/findFenLei.do",
        			success : function(data) {
        				$("#CLASSIFICATION_ID").empty(); 
        				var str = '<option value="">二级分类</option>';
    					$("#CLASSIFICATION_ID").append(str);
        				var list = data.listClassT;
        				var str = "";
        				//alert(list);
        				//alert(list.length);
        				if(null!=list&&list.length>0){
        					for (var i = 0; i<list.length;i++){
        						//alert(list[i].CLASSIFICATION_ID);
        						//alert(list[i].NAME);
        						str+="<option value='"+list[i].CLASSIFICATION_ID+"' >"+ list[i].NAME+ "</option>";
        						
        					}
        					$("#CLASSIFICATION_ID").append(str);
        				}else{
        					str+="<option value='暂无'>暂无</option>";
        					$("#CLASSIFICATION_ID").append(str);
        				}
    					
    					
        			}
        		});
    		}
    	}
	function findBiaoGe(){
		var id='${pd.COMMODITY_ID}';
		var pausedCause = '';
		$(":checkbox[name='gg'][checked]").each(function(){
			pausedCause += this.value +',';
		});
		if(pausedCause.length>0){
			pausedCause = pausedCause.substring(0,pausedCause.length-1);
			 $.ajax({
					type : "get",
					dataType:"json",
					url : '${pageContext.request.contextPath}/commodity/findBiaoGe.do?pausedCause='+pausedCause+'&id='+id,
					success : function(data){
						$("#biaoge").html("");
							var flag=data.flag;
							$("#num").val(data.num);
							var listSpe =data.listSpe;
							var list=data.list;
// 							alert(listSpe);
							var str='';
							$("#biaoge").html("");
							if(listSpe.length>0){
								str+="<thead><tr>";
								for (var i = 0; i<listSpe.length;i++){
// 									var str='';
									if(listSpe[i].count>0){
										str+="<th>"+listSpe[i].NAME+"</th>";
// 										$("#biaoge").append(str);
									}
								}
								str+="<th>库存</th><th>销售价格</th>";
								str+="</tr></thead><tbody>";
								for(var j=0;j<list.length;j++){
									str+="<tr>";
// 									var str='';
									var listg=list[j];
									var pausedCause = '';
								    for(var g=0;g<listg.length;g++){
								    	pausedCause += listg[g].SPECIFICATIONPROPERTIES_ID +';';								    	
										str+="<td>"+listg[g].NAME+"</td>";
								    }
								    if(pausedCause.length>0){
										 pausedCause = pausedCause.substring(0,pausedCause.length-1);
								    }
								    str+="<input id='proper"+j+"' name='proper"+j+"' type='hidden' value='"+pausedCause+"'  >";
								    str+="<td><input id='kucun"+j+"' name='kucun"+j+"' type='text' value='' min='1' max='999999' style='position:relative;top:5px;' ></td>";
								    str+="<td><input id='jg"+j+"' name='jg"+j+"' type='text' value='' style='position:relative;top:5px;' ></td>";
								    str+="</tr>";
								}
								str+="</tbody>";
										$("#biaoge").html(str);
							}
						}
				});
		}
	}
		
		
		
		
		
		</script>
</body>
</html>