<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String OSSUrl = application.getInitParameter("OSSUrl");
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../system/admin/top.jsp"%>
</head>
<body>

<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">

<div class="row-fluid">

<div class="row-fluid"><!-- 检索  -->
<form action="businessuser/ZClist.do" method="post" name="Form" id="Form">
<table>
	<tr>
		<td><span class="input-icon"> <input autocomplete="off"
			id="COMPANYNAME" type="text" name="COMPANYNAME" value="${pd.COMPANYNAME}" placeholder="公司名称" /> <i id="nav-search-icon" class="icon-search"></i> </span></td>
		<td style="vertical-align: top;"><select class="chzn-select" name="ZCFLAG1" id="ZCFLAG1" data-placeholder="请选择" style="vertical-align: top; width: 120px;">
			<option value=""></option>
			<option value="">全部</option>
			<option value="1" <c:if test="${pd.ZCFLAG1=='1'}">selected</c:if>>待审核</option>
			<option value="2" <c:if test="${pd.ZCFLAG1=='2'}">selected</c:if>>审核通过</option>
			<option value="3" <c:if test="${pd.ZCFLAG1=='3'}">selected</c:if>>审核失败</option>
			
		</select></td>
		<td style="vertical-align: top;">
		<button class="btn btn-mini btn-light" onclick="search();" title="检索"><i id="nav-search-icon" class="icon-search"></i></button>
		</td>
		
	</tr>
</table>
<!-- 检索  -->


<table id="table_report"
	class="table table-striped table-bordered 

table-hover">

	<thead>
		<tr>
			<th class="center"><label><input type="checkbox"
				id="zcheckbox" /><span class="lbl"></span></label></th>
			<th class="center">序号</th>
			<th class="center">公司地址</th>
			<th class="center">公司名称</th>
			<th class="center">营业执照</th>
			<th class="center">联系方式</th>
			<th class="center">主营业务</th>
			<!--<th class="center">商家名称</th>
						<th class="center">店铺名称</th>
						<th class="center">店铺地址</th>
						<th class="center">店铺logo</th>
						<th class="center">详情</th>
						<th class="center">入驻时间</th>-->
			<th class="center">状态</th>
			<th class="center">操作</th>
		</tr>
	</thead>

	<tbody>

		<!-- 开始循环 -->
		<c:choose>
			<c:when test="${not empty nameList}">
				<c:if test="${QX.cha == 1 }">
					<c:forEach items="${nameList}" var="var" varStatus="vs">
						<tr>
							<td class='center' style="width: 30px;vertical-align: middle;"><label><input
								type='checkbox' name='ids'
								value="${var.varList.BUSINESSUSER_ID}" /><span class="lbl"></span></label>
							</td>
							<td class='center' style="width: 30px;vertical-align: middle;">${vs.index+1}</td>
							<td class='center' style="vertical-align: middle;">${var.varList.COMPANYADDRESS}</td>
							<td class='center' style="vertical-align: middle;">${var.varList.COMPANYNAME}</td>
							<td class='center' style="vertical-align: middle;"><img
								src="<%=OSSUrl%>${var.varList.BUSINESSLICENSEURL}"
								style="width: 200px"></td>
							<td class='center' style="vertical-align: middle;">${var.varList.TEL}</td>
							<td class='center' style="width: 30px; vertical-align: middle;">${var.nameString}</td>
							<c:if test="${var.varList.ZCFLAG==1}">
								<td class='center' style="vertical-align: middle;">待审核</td>
							</c:if>
							<c:if test="${var.varList.ZCFLAG==2}">
								<td class='center' style="vertical-align: middle;">审核通过</td>
							</c:if>
							<c:if test="${var.varList.ZCFLAG==3}">
								<td class='center' style="vertical-align: middle;">审核失败</td>
							</c:if>
							<!--<td 

class='center' style="vertical-align: middle;">${var.varList.BUSINESSUSERNAME}</td>
										
										
										
										<td 

class='center' style="vertical-align: middle;">${var.varList.SHOPNAME}</td>
										<td 

class='center' style="vertical-align: middle;">${var.varList.SHOPADDRESS}</td>
										<td 

class='center' style="vertical-align: middle;">
										<img 

src="<%=OSSUrl%>${var.varList.SHOPLOGO}" style="width: 200px">
										</td>
										
										
										<td 

class='center' style="vertical-align: middle;"><a class="btn btn-small btn-success" 

onclick="xq('${var.varList.BUSINESSUSER_ID}');">点击查看</a></td>
										<td 

class='center' style="vertical-align: middle;">${var.varList.CREATIME}</td>
										
										-->
							<td style="width: 280px; vertical-align: middle;" class="center">

							<c:if test="${QX.edit != 1 && QX.del != 1 }">
								<span
									class="label label-large label-grey arrowed-in-right arrowed-in"><i
									class="icon-lock" title="无权限"></i></span>
							</c:if> <c:if test="${QX.edit == 1}">

								<a class="btn btn-small btn-success"
									onclick="edit('${var.varList.BUSINESSUSER_ID}');">编辑</a>
								<c:if test="${var.varList.ZCFLAG==1}">
									<a class="btn btn-small btn-danger"
										onclick="gFlag('${var.varList.BUSINESSUSER_ID}','2');">审核通过</a>
									<a class="btn btn-small btn-danger"
										onclick="gFlag('${var.varList.BUSINESSUSER_ID}','3');">审核失败</a>
								</c:if>
								<c:if test="${var.varList.ZCFLAG==2}">
								</c:if>
								<c:if test="${var.varList.ZCFLAG==3}">
								</c:if>
							</c:if> <c:if test="${QX.del == 1 }">
								<a class="btn btn-small btn-danger"
									onclick="del('${var.varList.BUSINESSUSER_ID}');">删除</a>
							</c:if> <!--<div 

class="inline position-relative">
										<button 

class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-

only"></i></button>
										<ul 

class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-

close">
											

<c:if test="${QX.edit == 1 }">
											

<li><a style="cursor:pointer;" title="编辑" onclick="edit

('${var.varList.BUSINESSUSER_ID}');" class="tooltip-success" data-rel="tooltip" title="" 

data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
											

</c:if>
											

<c:if test="${QX.del == 1 }">
											

<li><a style="cursor:pointer;" title="删除" onclick="del('${var.varList.BUSINESSUSER_ID}');" 

class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i 

class="icon-trash"></i></span> </a></li>
											

</c:if>
										</ul>
										</div>
									--></td>
						</tr>

					</c:forEach>
				</c:if>
				<c:if test="${QX.cha == 0 }">
					<tr>
						<td colspan="100" class="center">您无权查看</td>
					</tr>
				</c:if>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="100" class="center">没 有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>


	</tbody>
</table>

<div class="page-header position-relative">
<table style="width: 100%;">
	<tr>
		<td style="vertical-align: top;"><c:if test="${QX.add == 1 }">
			<a class="btn btn-small btn-success" onclick="add();">新增</a>
		</c:if> <c:if test="${QX.del == 1 }">
			<a class="btn btn-small btn-danger"
				onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除"><i
				class='icon-trash'></i></a>
		</c:if></td>
		<td style="vertical-align: top;">
		<div class="pagination"
			style="float: right; padding-top: 0px; margin-top: 0px;">${page.pageStr}</div>
		</td>
	</tr>
</table>
</div>
</form>
</div>




<!-- PAGE CONTENT ENDS HERE --></div>
<!--/row--></div>
<!--/#page-content--></div>
<!--/.fluid-container#main-container-->

<!-- 返回顶部  -->
<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse"> <i
	class="icon-double-angle-up icon-only"></i> </a>

<!-- 引入 -->
<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/ace-elements.min.js"></script>
<script src="static/js/ace.min.js"></script>

<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
<!-- 下拉框 -->
<script type="text/javascript"
	src="static/js/bootstrap-datepicker.min.js"></script>
<!-- 日期框 -->
<script type="text/javascript" src="static/js/bootbox.min.js"></script>
<!-- 确认窗口 -->
<!-- 引入 -->
<script type="text/javascript" src="static/js/jquery.tips.js"></script>
<!--提示框-->
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
			 diag.URL = '<%=basePath%>businessuser/goAdd.do';
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
		//详情
		function xq(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="详情";
			 diag.URL = '<%=basePath%>businessuser/goXQing.do?BUSINESSUSER_ID='+Id;
			 diag.Width = 500;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
				diag.close();
			 };
			 diag.show();
			//window.location.href='<%=basePath%>pictures/excel.do';
		}
		//删除
		function del(Id){
			bootbox.confirm("确定要删除吗?", function(result) {
				if(result) {
					top.jzts();
					var url = "<%=basePath%>businessuser/delete.do?BUSINESSUSER_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		//改变状态
		function gFlag(Id,flag){
			var url = "<%=basePath%>businessuser/ZClist.do?BUSINESSUSER_ID="+Id+"&ZCFLAG="+flag;
				window.location.href=url;
		}
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>businessuser/goEdit.do?BUSINESSUSER_ID='+Id;
			 diag.Width = 450;
			 diag.Height = 355;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					 nextPage(${page.currentPage});
				}
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
								url: '<%=basePath%>businessuser/deleteAll.do?tm='+new Date().getTime(),
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
			window.location.href='<%=basePath%>businessuser/excel.do';
		}
		</script>

</body>
</html>

