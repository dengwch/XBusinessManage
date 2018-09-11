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
	<link rel="stylesheet" href="plugins/zTree/3.5/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="static/js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="plugins/zTree/3.5/jquery.ztree.core-3.5.js"></script>
	</head>
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="classification/list.do" method="post" name="Form" id="Form">
			<table>
				<tr>
					<td>
						<div style="width:80%;">
							<ul id="leftTree" class="ztree"></ul>
						</div>
					</td>
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
						<th class="center">上级ID</th>
						<th class="center">分类名称</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">
									<label><input type='checkbox' name='ids' value="${var.CLASSIFICATION_ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td>
										<c:if test="${var.SUPERIOR==null || var.SUPERIOR==''}">
											顶级分类
										</c:if>
										${var.SUPERIOR}
										</td>
										<td>${var.NAME}</td>
								<td style="width: 30px;" class="center">
								<a style="cursor:pointer;" title="编辑" onclick="edit('${var.CLASSIFICATION_ID}');">编辑</a>
								<a style="cursor:pointer;" title="删除" onclick="del('${var.CLASSIFICATION_ID}');">删除</a>
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
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		
		
		var setting = {
				view: {
					showIcon: showIconForTree
				},
				data: {
					simpleData: {
						enable: true
					}
				}
			};

// 			var zNodes =[
// 				{ id:1, pId:0, name:"父节点1 - 展开", open:true},
// 				{ id:11, pId:1, name:"父节点11 - 折叠"},
// 				{ id:111, pId:11, name:"叶子节点111", url:"http://www.so.com/", target:"treeFrame"},
// 				{ id:112, pId:11, name:"叶子节点112", url:"", target:"treeFrame"},
// 				{ id:113, pId:11, name:"叶子节点113"},
// 				{ id:114, pId:11, name:"叶子节点114"},
// 				{ id:115, pId:114, name:"叶子节点114"},
// 				{ id:116, pId:115, name:"叶子节点114"},
// 				{ id:117, pId:116, name:"叶子节点114"},
// 				{ id:12, pId:1, name:"父节点12 - 折叠",url:"http://www.so.com/", target:"treeFrame"},
// 				{ id:121, pId:12, name:"叶子节点121"},
// 				{ id:122, pId:12, name:"叶子节点122"},
// 				{ id:123, pId:12, name:"叶子节点123"},
// 				{ id:124, pId:12, name:"叶子节点124"},
// 				{ id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true},
// 				{ id:2, pId:0, name:"父节点2 - 折叠"},
// 				{ id:21, pId:2, name:"父节点21 - 展开", open:true},
// 				{ id:211, pId:21, name:"叶子节点211"},
// 				{ id:212, pId:21, name:"叶子节点212"},
// 				{ id:213, pId:21, name:"叶子节点213"},
// 				{ id:214, pId:21, name:"叶子节点214"},
// 				{ id:22, pId:2, name:"父节点22 - 折叠"},
// 				{ id:221, pId:22, name:"叶子节点221"},
// 				{ id:222, pId:22, name:"叶子节点222"},
// 				{ id:223, pId:22, name:"叶子节点223"},
// 				{ id:224, pId:22, name:"叶子节点224"},
// 				{ id:23, pId:2, name:"父节点23 - 折叠"},
// 				{ id:231, pId:23, name:"叶子节点231"},
// 				{ id:232, pId:23, name:"叶子节点232"},
// 				{ id:233, pId:23, name:"叶子节点233"},
// 				{ id:234, pId:23, name:"叶子节点234"},
// 				{ id:235, pId:23, name:"叶子节点232"},
// 				{ id:236, pId:23, name:"叶子节点233"},
// 				{ id:237, pId:23, name:"叶子节点234"},
// 				{ id:238, pId:23, name:"叶子节点232"},
// 				{ id:239, pId:23, name:"叶子节点233"},
// 				{ id:2310, pId:23, name:"叶子节点234"},
// 				{ id:2311, pId:23, name:"叶子节点232"},
// 				{ id:2312, pId:23, name:"叶子节点233"},
// 				{ id:2312, pId:23, name:"叶子节点234"},
// 				{ id:2314, pId:23, name:"叶子节点232"},
// 				{ id:2315, pId:23, name:"叶子节点233"},
// 				{ id:2316, pId:23, name:"叶子节点234"},
// 				{ id:3, pId:0, name:"父节点3 - 没有子节点"}
// 			];
// 	alert('${json}');
	 var zNodes = jQuery.parseJSON( '${json}');
			function showIconForTree(treeId, treeNode) {
				return !treeNode.isParent;
			};

			$(document).ready(function(){
				$.fn.zTree.init($("#leftTree"), setting, zNodes);
			});
			
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
			 diag.URL = '<%=basePath%>classification/goAdd.do';
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
		if(confirm("是否确认删除")){
		
					top.jzts();
					var url = "<%=basePath%>classification/delete.do?CLASSIFICATION_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
			}
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>classification/goEdit.do?CLASSIFICATION_ID='+Id;
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
								url: '<%=basePath%>classification/deleteAll.do?tm='+new Date().getTime(),
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
			window.location.href='<%=basePath%>classification/excel.do';
		}
		</script>
		
	</body>
</html>

