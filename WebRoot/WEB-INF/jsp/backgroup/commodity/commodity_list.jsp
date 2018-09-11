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
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="commodity/list.do" method="post" name="Form" id="Form">
			<input type="hidden" value="${pd.SQFLAG1}" id="SQFLAG2"/>
			<input type="hidden" value="${pd.CLASSIFICATION_ID}" id="CLASSIFICATION_ID1"/>
			<table>
			<tr>
			
			<td style="vertical-align:top;"> 
			 	<select  name="SQFLAG1" id="SQFLAG1" data-placeholder="请选择" style="vertical-align:top;width: 135px;" onchange="findShi()">
					<option value="">请选择一级分类</option>
					<option value="">全部</option>
					
					 <c:forEach items="${listClass}" var="LIclass">
					 
       					<option value ="${LIclass.CLASSIFICATION_ID }" <c:if test="${pd.SQFLAG1==LIclass.CLASSIFICATION_ID}">selected</c:if>>${LIclass.NAME }</option>
      					</c:forEach>
			  	</select>
			</td>
			<td style="vertical-align:top;"> 
			 	<select name="CLASSIFICATION_ID" id="CLASSIFIstr" data-placeholder="请选择" style="vertical-align:top;width: 135px;" >
					<option value="">请选择二级分类</option>
			  	</select>
			</td>
			<td style="vertical-align:top;"> 
			 	<select  name="FLAG" id="FLAG" data-placeholder="请选择状态" style="vertical-align:top;width: 135px;" >
					<option value="">请选择状态</option>
					<option value="">全部</option>
					<option value="1" <c:if test="${pd.FLAG=='1' }">selected</c:if>>下架</option>
					<option value="2" <c:if test="${pd.FLAG=='2' }">selected</c:if>>上架</option>
			  	</select>
			</td>
			<td>
				<span class="input-icon">
					<input autocomplete="off" id="NAME" type="text" name="NAME" value="${pd.NAME}" placeholder="商品名称" />
					<i id="nav-search-icon" class="icon-search"></i>
				</span>
			</td>
			<td style="vertical-align:top;"><button class="btn btn-mini btn-light" onclick="search();"  title="检索"><i id="nav-search-icon" class="icon-search"></i></button></td>
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
						<th class="center">分类ID</th>
						<th class="center">商品名称</th>
						<!--<th class="center">活动类型</th>
						<th class="center">商品货号</th>
						--><th class="center">售价</th>
						<!--<th class="center">重量</th>
						<th class="center">体积</th>
						<th class="center">商品图片</th>
						<th class="center">所在地</th>
						<th class="center">运费模板</th>
						<th class="center">一级分佣比例</th>
						<th class="center">二级分佣比例</th>
						<th class="center">上架模式</th>
						-->
						<th class="center">状态</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					
				<!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;vertical-align: middle;">
									<label><input type='checkbox' name='ids' value="${var.COMMODITY_ID}" /><span class="lbl"></span></label>
								</td>
								<td class='center' style="width: 30px;vertical-align: middle;">${vs.index+1}</td>
									<td class='center' style="vertical-align: middle;">${var.CLASSIFICATION_ID}</td>
									<td class='center' style="vertical-align: middle;">${var.NAME}</td>
									<!--<td>${var.ACTIVITYNAME}</td>
									<td>${var.NUMBER}</td>
									-->
									<td class='center' style="vertical-align: middle;">${var.PRICE}</td>
									<!--<td>${var.WEIGHT}</td>
									<td>${var.VOLUME}</td>
									<td><a href="commodityimg/list.do?COMMODITY_ID=${var.COMMODITY_ID}">查看图片</a></td>
									<td>${var.LOCATION}</td>
									<td>${var.FREIGHTTEMPLATE}</td>
									<td>${var.PERCENTAGECOMMISSIONFIR}</td>
									<td>${var.PERCENTAGECOMMISSIONSEC}</td>
									<td>
										<c:if test="${var.TYPE==0}">
											未上架
										</c:if>
										<c:if test="${var.TYPE==1}">
											已上架
										</c:if>
									</td>
							-->
									<td class='center' style="vertical-align: middle;">
										<c:if test="${var.FLAG==1}">
											下架
										</c:if>
										<c:if test="${var.FLAG==2}">
											上架
										</c:if>
										<c:if test="${var.FLAG==0}">
											暂无操作
										</c:if>
									</td>
								<td style="width: 30px;vertical-align: middle;" class="center">
									<div class='hidden-phone visible-desktop btn-group'>
									
										
										<div class="inline position-relative">
										<button class="btn btn-mini btn-info" data-toggle="dropdown"><i class="icon-cog icon-only"></i></button>
										<ul class="dropdown-menu dropdown-icon-only dropdown-light pull-right dropdown-caret dropdown-close">
											<li><a style="cursor:pointer;" title="编辑" onclick="edit('${var.COMMODITY_ID}');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-edit"></i></span></a></li>
											<c:if test="${var.FLAG=='1' || var.FLAG=='0'}">
												<li><a style="cursor:pointer;" title="上架" onclick="del('${var.COMMODITY_ID}','2');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-arrow-up"></i></span></a></li>
											</c:if>
											<c:if test="${var.FLAG=='2'}">
												<li><a style="cursor:pointer;" title="下架" onclick="del('${var.COMMODITY_ID}','1');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left"><span class="green"><i class="icon-arrow-down"></i></span></a></li>
											</c:if>
											<li><a style="cursor:pointer;" title="删除" onclick="del('${var.COMMODITY_ID}','3');" class="tooltip-error" data-rel="tooltip" title="" data-placement="left"><span class="red"><i class="icon-trash"></i></span> </a></li>
										</ul>
										</div>
									</div>
								</td>
							</tr>
						
						</c:forEach>
						
						<c:if test="${QX.cha == 0 }">
							<tr>
								<td colspan="100" class="center">您无权查看</td>
							</tr>
						</c:if>
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
        				var str = '<option value="">请选择二级分类</option>';
    					$("#CLASSIFIstr").append(str);
        				var list = data.listClassT;
        				var str = "";
        				//alert(list);
        				//alert(list.length);
        				var CLASSIFICATION_ID1=$("#CLASSIFICATION_ID1").val();
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
        					str+="<option value=''>全选</option>";
        					$("#CLASSIFIstr").append(str);
        				}else{
        					str+="<option value='暂无'>暂无</option>";
        					$("#CLASSIFIstr").append(str);
        				}
    					
    					
        			}
        		});
    		}
		
	});
	
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
			 diag.URL = '<%=basePath%>commodity/goAdd.do';
			 diag.Width = 1200;
			 diag.Height = 800;
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
		
		//删除/上架/下架
		function del(Id,FLAG){
			if(FLAG=="3"){
				bootbox.confirm("确定要删除吗?", function(result) {
					if(result) {
						top.jzts();
						var type="gai111";
						var url = "<%=basePath%>commodity/delete.do?COMMODITY_ID="+Id+"&tm="+new Date().getTime()+"&FLAG="+FLAG+"&type="+type;
						$.get(url,function(data){
							nextPage(${page.currentPage});
						});
					}
				});
			}else if(FLAG=="1"){
				bootbox.confirm("确定要下架吗?", function(result) {
					if(result) {
						top.jzts();
						var type="gai";
						var url = "<%=basePath%>commodity/delete.do?COMMODITY_ID="+Id+"&tm="+new Date().getTime()+"&FlAG="+FLAG+"&type="+type;
						$.get(url,function(data){
							nextPage(${page.currentPage});
						});
					}
				});
			}else if(FLAG=="2"){
				bootbox.confirm("确定要上架吗?", function(result) {
					if(result) {
						top.jzts();
						var type="gai";
						var url = "<%=basePath%>commodity/delete.do?COMMODITY_ID="+Id+"&tm="+new Date().getTime()+"&FlAG="+FLAG+"&type="+type;
						$.get(url,function(data){
							nextPage(${page.currentPage});
						});
					}
				});
			}
			
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>commodity/goEdit.do?COMMODITY_ID='+Id;
			 diag.Width = 800;
			 diag.Height = 600;
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
        				$("#CLASSIFIstr").empty(); 
        				var str = '<option value="">请选择二级分类</option>';
    					$("#CLASSIFIstr").append(str);
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
        					str+="<option value=''>全选</option>";
        					$("#CLASSIFIstr").append(str);
        				}else{
        					str+="<option value='暂无'>暂无</option>";
        					$("#CLASSIFIstr").append(str);
        				}
    					
    					
        			}
        		});
    		}
    	}
		
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
								url: '<%=basePath%>commodity/deleteAll.do?tm='+new Date().getTime(),
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
			window.location.href='<%=basePath%>commodity/excel.do';
		}
		</script>
		
	</body>
</html>

