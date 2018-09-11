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
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%> 
	</head>
<body>
		
<div class="container-fluid" id="main-container">


<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	<div class="row-fluid">
	
			<!-- 检索  -->
			<form action="ordermanger/orderInfoByOrderId.do" method="post" name="Form" id="Form">
			<!-- 检索  -->
		
			<table id="table_report" class="table table-striped table-bordered table-hover">
				
				<thead>
					<tr>
						<th class="center">序号</th>
						<th class="center">订单号</th>
						<th class="center">订单总价</th>
						<th class="center">订单状态</th>
						<th class="center">操作</th>
					</tr>
				</thead>
										
				<tbody>
					<tr>
					<td></td>
					<td>${exPd.ORDER_ID}</td>
					<td>${exPd.SUMPRICE}</td>
					<td >${exPd.STATE}</td>	
					<td colspan="2">
					<c:if test="${type==1}">
					<a style="cursor:pointer;" title="退款" onclick="updateOrderType('${exPd.ORDER_ID}','7');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left">退款</a>
					</c:if>
					<c:if test="${type==2}">
					<a style="cursor:pointer;" title="同意退货" onclick="updateOrderType('${exPd.ORDER_ID}','2');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left">同意退货</a>
					</c:if>
					<c:if test="${type==3}">
					<a style="cursor:pointer;" title="退款" onclick="updateOrderType('${exPd.ORDER_ID}','4');" class="tooltip-success" data-rel="tooltip" title="" data-placement="left">退款</a>
					</c:if>
					</td>				
					</tr>
				<!-- 开始循环 -->	
				
				<tr>
				<th class="center"></th>
				<th class="center">商品名称</th>
						<th class="center">商品单价</th>
						<th class="center">购买个数</th>
						<th class="center">总价</th>
						
				</tr>
				<c:choose>
					<c:when test="${not empty varOList}">
						<c:forEach items="${varOList}" var="var" varStatus="vs">
							<tr>
								<td class='center' style="width: 30px;">${vs.index+1}</td>
										<td >${var.NAME}</td>
										<td>${var.PRICE}</td>
										<td>${var.NUMBER}</td>
										<td >${var.SUMPRICE}</td>
										
							</tr>
						
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="100" class="center" >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
					<tr>
						<td colspan="2">
							物流信息：
						</td>
						<td colspan="3">
						收货人:${exPd.NAME }<br>
						收货地址:${exPd.PROVINCE }${exPd.CITY }${exPd.AREA }${exPd.ADDRESS }<br>
						快递名称:${exPd.EXPRESSCOMPANYNAME }&nbsp;&nbsp;&nbsp;快递号：${exPd.EXPRESSNO }<br>
						</td>
					</tr>
				<c:if test="${type==1 || type==2 || type==3}">
				<tr>
						<td colspan="2">
							退款	/退货：
						</td>
						<td colspan="3">
						退款	/退货原因:${exPd.RETURN_REMARK }<br>
						<c:if test="${type==3 }">
						快递名称:${exPd.RETURNEXPRESSAGENAME }&nbsp;&nbsp;&nbsp;快递号：${exPd.RETURNEXPRESSAGENO }<br>
						</c:if>
						</td>
					</tr>
					<c:if test="${type==3 }">
					<tr>
						<td colspan="2">
							物流凭证：
						</td>
						<td colspan="3">
						<img style="width:200px;" src="<%=OSSUrl %>${exPd.RETURNEXPRESSAGEURL }">
						</td>
					</tr>
					</c:if>
				</c:if>
				</tbody>
			</table>
			<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
					<c:if test="${QX.add == 1 }">
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
					</c:if>
					<c:if test="${QX.del == 1 }">
					<a class="btn btn-small btn-danger" onclick="makeAll('确定要删除选中的数据吗?');" title="批量删除" ><i class='icon-trash'></i></a>
					</c:if>
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
		<script src="${pageContext.request.contextPath }/static/js/manager.js"></script>	
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
			 diag.URL = '<%=basePath%>ordermanger/goAdd.do';
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
					var url = "<%=basePath%>ordermanger/delete.do?ORDERMANGER_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
						nextPage(${page.currentPage});
					});
				}
			});
		}
		
		//修改订单状态
		var ORDERID_jsp="";
		var type_jsp="";
		function updateOrderType(ORDERID,returnType){
			ORDERID_jsp=ORDERID;
			type_jsp=returnType;
			if(!confirm("确认审批？")){
				return;
			}
		    var param={"ORDER_ID":ORDERID,"RETURN_TYPE":returnType};
		    var path="${pageContext.request.contextPath }/ordermanger/updateReturnType.do";
		    callAPI(path,param,updateOrderType_callback);
		}
		
		
		    function updateOrderType_callback(data){
		    	var result = data.result;
		    	if(result==1){
		    	alert("审批成功！");
		    	var type="";
		    	if(type_jsp==7||type_jsp==4){
		    		type=4;
		    	}else if(type_jsp==2){
		    		type=3;
		    	}
		    	window.location.href='<%=basePath%>ordermanger/orderInfoByOrderId.do?ORDERID='+ORDERID_jsp+'&type='+type;
		    	}
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
		function orderInfo(ORDERID){
			window.location.href='<%=basePath%>ordermanger/orderInfoByOrderId.do?ORDERID='+ORDERID;
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
								url: '<%=basePath%>ordermanger/deleteAll.do?tm='+new Date().getTime(),
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
			window.location.href='<%=basePath%>ordermanger/excel.do';
		}
		</script>
		
	</body>
</html>

