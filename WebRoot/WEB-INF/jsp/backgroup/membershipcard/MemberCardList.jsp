<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String TEL=(String)request.getSession().getAttribute("TEL");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	
	<link href="${pageContext.request.contextPath }/static/css/basicStyle.css" rel="stylesheet" type="text/css">
    <link href="static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
    <link href="static/css/ListStyle.css" rel="stylesheet" type="text/css">

    <style type="text/css">
        .addMember{
            color: white;
            font-size: 16px;
            font-weight: bold;
            background-color: #5ba2cf;
            border-radius: 4px;
            height: 34px;
            width: 100px;
            line-height: 34px;
            text-align: center;
            margin-bottom: 10px;


        }
        .header_member:after{
            clear: both;
            content: '';
            display: block;
        }
        .width6{
            width: 5%;
        }
        .width10{
            width: 10%;
        }
        .width12{
            width: 15%;
        }
 .btn-success {
    background-color: #5cb85c;
    border-color: #4cae4c;
    color: #fff;
}
.btn {
    -moz-user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
    cursor: pointer;
    display: inline-block;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.42857;
    margin-bottom: 0;
    padding: 6px 12px;
    text-align: center;
    touch-action: manipulation;
    vertical-align: middle;
    white-space: nowrap;
}
.btn-danger {
    background-color: #d9534f;
    border-color: #d43f3a;
    color: #fff;
}
    </style>
</head>
<body>
	<!--导航-->
<div class="header">
    <div class="header_left">
        <img  class="logo" src="${pageContext.request.contextPath }/static/images/logo.png">
        <ul class="header_nav">
            <li class="shopping">
                <p class="child_goods_p"><a href="${pageContext.request.contextPath }/commodity/list.do">商品管理</a> </p>
                <!--商品管理子类-->
                <ul class="child goodChild">
                    <li><a href="${pageContext.request.contextPath }/commodity/goAdd.do">新增商品</a></li>
                    <li><a href="${pageContext.request.contextPath }/classification/list.do">分类管理</a></li>
                </ul>
            </li>
            <li class=""><p><a href="${pageContext.request.contextPath }/bussinessactivity/list.do">优惠信息</a></p></li>
            <li><p><a style="color: #5aa3d0" href="${pageContext.request.contextPath }/membershipcard/list.do">会员卡管理</a></p></li>
            <li>
                <p class="child_store_p"><a >店铺设置</a></p>
                <!--店铺设置-->
                <ul class="child storeChild">
                    <li><a href="${pageContext.request.contextPath }/businessuser/goEditxx.do">店铺信息</a></li>
                    <li><a href="${pageContext.request.contextPath }/businessuser/list.do">子账号管理</a></li>
                </ul>
            </li>
            <li><p><a href="${pageContext.request.contextPath }/commodity/listLL.do">流量信息</a></p></li>
        </ul>
    </div>
    <div class="header_right">
        <span class="userName">您好,<%=TEL %>！</span>
        <span class="shezhi" style="color:#333;border:0;">设置</span>
        <!--店铺设置-->
        <ul class="child child_setting">
            <a href="${pageContext.request.contextPath }/businessuser/goEditPassword.do"><li>修改密码</li></a>
           <a href="${pageContext.request.contextPath }/feedback/goAdd.do"><li>意见反馈</li></a>
            <a href="logout"><li>退出账号</li></a>
        </ul>
    </div>
</div>


<p class="top">会员卡列表</p>
<div class="center_div">

    <div class="header_member">
        <a class="addMember rf" onclick="add();">添加会员卡</a>
    </div>
    <table class="table" style="width:100%;" id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
					<tr style="line-height:50px;">
						<th>序号</th>
						<th>折扣数</th>
						<th>领取时间</th>
						<th>使用时间</th>
						<th>发布时间</th>
						<th>总份数</th>
						<th>已领取</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
        
        
        <!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
							<tr>
								 <td style="vertical-align: middle;text-align: auto;">${vs.index+1}</td>
								 <td style="vertical-align: middle;text-align: auto;">${var.DISCOUNT}折</td>
								 <td style="vertical-align: middle;text-align: auto;">
					                <p class="halfLine">${var.GOSTARTTIME}</p>
					                <p class="halfLine">${var.GOENDTIME}</p>
					            </td>	
					            <td style="vertical-align: middle;text-align: auto;">
					                <p class="halfLine">${var.USESTARTTIME}</p>
					                <p class="halfLine">${var.USEENDTIME}</p>
					            </td>
								<td style="vertical-align: middle;text-align: auto;">${var.CREATETIME}</td>	
								<td style="vertical-align: middle;text-align: auto;">${var.NUMBER}</td>		
								<td style="vertical-align: middle;text-align: auto;">${var.LQNUM}</td>		
								<td style="vertical-align: middle;text-align: auto;">
									<c:if test="${var.FLAG=='1' }">使用中</c:if>
									<c:if test="${var.FLAG=='2' }">已停用</c:if>
								</td>		
								
											
										
										<c:if test="${var.FLAG=='1' }">
											<td style="vertical-align: middle;text-align: auto;" >
											<a class="btn btn-small btn-danger" onclick="del('${var.MEMBERSHIPCARD_ID}','2');">停用</a>
											</td>			
										</c:if>
										<c:if test="${var.FLAG=='2' }">
											<td style="vertical-align: middle;text-align: auto;" >
											<a class="btn btn-small btn-success" onclick="del('${var.MEMBERSHIPCARD_ID}','1');">使用</a>
											</td>			
														<!-- title="使用" onclick="del('${var.MEMBERSHIPCARD_ID}','1');" -->
										</c:if>
								
							</tr>
						
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="100"  >没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>

    </tbody>
    </table>

    <!--分页-->
    <div class="pages">
        <ul>
           ${page.pageStr}
        </ul>
    </div>
</div>
 <div class="foot" style="width: 100%;height: 100px;background: #fff;text-align: center;line-height: 100px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;margin-top: 30px;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>

<script type="text/javascript" src="static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$(".header_right>.shezhi ").mouseover(function(){
        $(".header_right>ul").show();
      });
      $(".header_right>.shezhi ").mouseout(function(){
    	  $(".header_right>ul").fadeOut(8000);
        });
});
</script>
		
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
			
			window.location.href = '<%=basePath%>membershipcard/goAdd.do';
			
		}
		
		//删除/该状态
		function del(Id,FLAG){
			

			if(FLAG==1){
				
					if(confirm("确定要使用吗?使用之后之前使用的会员卡将停用")) {
						window.location.href = "<%=basePath%>membershipcard/delete.do?MEMBERSHIPCARD_ID="+Id+"&tm="+new Date().getTime()+"&FLAG="+FLAG;
						
					}
				
			}else if(FLAG==2){
					if(confirm("确定要停用吗?")) {
						window.location.href = "<%=basePath%>membershipcard/delete.do?MEMBERSHIPCARD_ID="+Id+"&tm="+new Date().getTime()+"&FLAG="+FLAG;
					}
			}else{
					if(confirm("确定要删除吗?")) {
						window.location.href = "<%=basePath%>membershipcard/delete.do?MEMBERSHIPCARD_ID="+Id+"&tm="+new Date().getTime();
					}
			}
			
		}
		
		//修改
		function edit(Id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="编辑";
			 diag.URL = '<%=basePath%>membershipcard/goEdit.do?MEMBERSHIPCARD_ID='+Id;
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
								url: '<%=basePath%>membershipcard/deleteAll.do?tm='+new Date().getTime(),
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
			window.location.href='<%=basePath%>membershipcard/excel.do';
		}
		</script>
		
	</body>
</html>

