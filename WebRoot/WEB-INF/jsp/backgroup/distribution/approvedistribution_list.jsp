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
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%> 
	</head>
<body>
<!-- 王欢修改   引入js start  select修改成autocomplete  -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
		<!-- 引入jQuery的js文件 -->
		<script type="text/javascript" src="http://code.jquery.com/jquery-1.9.1.js" ></script>
		<!-- 引入jQuery UI的js文件 -->
		<script type="text/javascript" src="http://code.jquery.com/ui/1.10.4/jquery-ui.js" ></script>
		<link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
		<!-- 引入js end -->		
		<script src="${pageContext.request.contextPath }/static/js/manager.js"></script>
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="approvedistribution/list.do" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<span class="input-icon">
						<input type="text" name="PHONE" id="PHONE"  maxlength="50"  placeholder="这里输入手机号" title="手机号"/>
						<%--<input type="text" name="AREA_ID" id="AREA_ID" value="${pd.AREA_ID }"  style="display:none"/> --%>
						</span>
					</td>
					<td><input type="text" name="DISTRIBUTIONNAME" id="DISTRIBUTIONNAME"  maxlength="50"  placeholder="姓名" title="姓名"/></td>
					<td style="vertical-align:top;"> 
					 	<select class="chzn-select" name="APPROVETYPE" id="APPROVETYPE" data-placeholder="请选择状态" style="vertical-align:top;width: 120px;">
							<option value=""></option>
							<option value="">全部</option>
							<option value="1">待审核</option>
							<option value="2">已同意</option>
							<option value="3">已拒绝</option>
					  	</select>
					</td>
					<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
					<c:if test="${QX.cha == 1 }">
					<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
					</c:if>
				</tr>
			</table>
			<!-- 检索  -->
		
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
						</th>
						<th class="center">序号</th>
						<th class="center">手机号</th>
						<th class="center">身份证号</th>
						<th class="center">分销商姓名</th>
						<th class="center">身份证正面照片</th>
						<th class="center">身份证反面照片</th>
						<th class="center">操作</th>
						<th class="center">状态</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.APPROVEDISTRIBUTION_ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>${var.PHONE}</td>
										<td>${var.CARDNO}</td>
										<td>${var.DISTRIBUTIONNAME}</td>
										<td><a onclick="bigImg('${var.CARDZHENGIMG}')">查看身份证正面照</a></td>
										<td><a onclick="bigImg('${var.CARDFANIMG}')">查看身份证反面照</a> </td>
								<td style="width: 60px;" class="center">
									<a  title="同意" onclick="approve('${var.APPROVEDISTRIBUTION_ID}','2');" ><span class="green">同意</span></a>
									<a  title="拒绝" onclick="approve('${var.APPROVEDISTRIBUTION_ID}','3');" ><span class="red">拒绝</span></a>
										
								</td>
								<td>
									<c:if test="${var.APPROVETYPE==1}">
										待审核
									</c:if>
									<c:if test="${var.APPROVETYPE==2}">
										已同意
									</c:if>
									<c:if test="${var.APPROVETYPE==3}">
										已拒绝
									</c:if>
								</td>
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
			
		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='icon-trash'></i></a>
				</td>
				<td style="vertical-align:top;"><div class="pagination" style="float: right;padding-top: 0px;margin-top: 0px;">${page.pageStr}</div></td>
			</tr>
		</table>
		</div>
		</form>
	</div>
 
 
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>approvedistribution/goAdd.do';
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 if('${page.currentPage}' == '0'){
						 top.jzts();
						 setTimeout("self.location=self.location",100);
					 }else{
						 nextPage(${page.currentPage});
					 }
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>approvedistribution/delete.do?APPROVEDISTRIBUTION_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改
		function bigImg(imgUrl){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>approvedistribution/goEdit.do?imgUrl='+imgUrl;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 
				diag.close();
			 };
			 diag.show();
		}
		</script>
		
		<script type="text/javascript">
		
		$(function() {
			
			//下拉框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
			//复选框
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			
			//autocomplete--phone
			var phonelist ='${phoneList}';
			phonelist = JSON.parse(phonelist);
	    	  var resultlist=[];
	    
	       for(var i=0;i<phonelist.length;i++){
	    	   var data=phonelist[i];
	    	 //  var data2={label:data.NAME, value: data.NAME,id:data.CITY_ID };
	    	 var data2={label:data.PHONE, value: data.PHONE};
	           resultlist.push(data2);
	       }
	    	//$("#language").autocomplete(optionsObj);
	    	 $("#PHONE").autocomplete({
	         source: resultlist
	, select: function(e, ui) { 
		//$("#AREA_ID").val(ui.item.id);
	}
	    });
		
	    	 
	    	//autocomplete--name
				var nameList ='${nameList}';
				nameList = JSON.parse(nameList);
		    	  var resultlist_name=[];
		    
		       for(var i=0;i<nameList.length;i++){
		    	   var data=nameList[i];
		    	 //  var data2={label:data.NAME, value: data.NAME,id:data.CITY_ID };
		    	 var data2={label:data.DISTRIBUTIONNAME, value: data.DISTRIBUTIONNAME};
		    	 resultlist_name.push(data2);
		       }
		    	//$("#language").autocomplete(optionsObj);
		    	 $("#DISTRIBUTIONNAME").autocomplete({
		         source: resultlist_name
		, select: function(e, ui) { 
			//$("#AREA_ID").val(ui.item.id);
		}
		    });
			
		});
		
		
		//批量操作
		function makeAll(msg){
			bootbox.confirm(msg, function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							top.jzts();
							$.ajax({
								type: "POST",
								url: '<%=basePath%>approvedistribution/deleteAll.do?tm='+new Date().getTime(),
						    	data: {DATA_IDS:str},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){
									 $.each(data.list, function(i, list){
											nextPage(${page.currentPage});
									 });
								}
							});
						}
					}
				}
			});
		}
		
		//导出excel
		function toExcel(){
			window.location.href='<%=basePath%>approvedistribution/excel.do';
		}
		//审批
		function approve(APPROVEDISTRIBUTION_ID,APPROVETYPE){
			if(!confirm("确认审批？")){
				return;
			}
		    var param={"APPROVEDISTRIBUTION_ID":APPROVEDISTRIBUTION_ID,"APPROVETYPE":APPROVETYPE};
		    var path="${pageContext.request.contextPath }/approvedistribution/edit.do";
		    callAPI(path,param,approve_callback);
		    }
		    function approve_callback(data){
		    	var result = data.result;
		    	if(result==1){
		    	alert("审批成功！");
		    	nextPage(${page.currentPage});
		    	}
		    }
		</script>
		
	</body>
</html>

