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
    <link href="${pageContext.request.contextPath }/static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/static/css/ListStyle.css" rel="stylesheet" type="text/css">
    <style type="text/css">
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

    body{
        overflow-y:visible : 
    }
        .width20{
            width: 20%;
        }
        .width40{
            width: 40%;
        }
        .display_div{
            display: flex;
            display: -webkit-flex;
            justify-content: center;
            align-items: center;
        }
        .border_div{
            height: 30px;
            border: 1px solid #5ba2cf;
            border-radius: 4px;
            background-color: white;
            padding: 7px;
            font-size: 16px;
            color: #5ba2cf;
            margin: 5px 15px;
            display: flex;
            display: -webkit-flex;
            justify-content: center;
            align-items: center;

        }
        .border_div img{
            width: 16px;
            height: 16px;
            margin-right: 7px;
        }
        .btnAddCoupons a{
            background-color: #5ba2cf;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 20px;
            color: white;
            font-weight: bold;
            text-decoration-line: none;
        }
        .btnAddCoupons:after{
            clear: both;
            content: '';
            display: block;
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
                <p class="child_goods_p"><a href="${pageContext.request.contextPath }/commodity/list.do" >商品管理</a> </p>
                <!--商品管理子类-->
                <ul class="child goodChild">
                    <li><a href="${pageContext.request.contextPath }/commodity/goAdd.do">新增商品</a></li>
                    <li><a href="${pageContext.request.contextPath }/classification/list.do">分类管理</a></li>
                </ul>
            </li>
            <li class=""><p><a style="color: #5aa3d0" href="${pageContext.request.contextPath }/bussinessactivity/list.do">优惠信息</a></p></li>
            <li><p><a href="${pageContext.request.contextPath }/membershipcard/list.do">会员卡管理</a></p></li>
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


<p class="top">优惠信息>优惠列表</p>
<div class="center_div">
    <div class="btnAddCoupons">
        <a class="rf" onclick="add();">添加优惠信息</a>
    </div>
    
     <table class="table" style="width:100%;">
				<thead>
					<tr style="line-height:50px;">
						<th>序号</th>
						<th>优惠信息</th>
						<th>操作</th>
					</tr>
				</thead>
        
        <tbody>
        <!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty varList}">
						<c:forEach items="${varList}" var="var" varStatus="vs">
						 <tr >
						<td style="vertical-align: middle;text-align: auto;">${vs.index+1}</td>
						<td style="vertical-align: middle;text-align: auto;">${var.ACTIVITYNAME}</td>		
						<td style="width: 200px;vertical-align: middle;text-align: auto;">
			                <p class="border_div">
			                    <img src="${pageContext.request.contextPath }/static/images/bianji.png">
			                    <span onclick="edit('${var.BUSSINESSACTIVITY_ID}');">编辑</span>
			                </p>
			                <p class="border_div">
			                    <img src="${pageContext.request.contextPath }/static/images/shanchu.png">
			                    <span onclick="del('${var.BUSSINESSACTIVITY_ID}');">删除</span>
			                </p>
			            </td>		
						 </tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
					 	<tr>
					 	<td colspan="100" style="vertical-align: middle;text-align: auto;">没有相关数据</td>
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


<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.1.min.js"></script>
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
</div>
    <div class="foot" style="width: 100%;height: 80px;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>		
		<!-- 引入 -->
		<script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath }/static/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath }/static/js/ace.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.1.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.tips.js"></script><!--提示框-->
		<script type="text/javascript">
		
		$(top.hangge());
		
		//检索
		function search(){
			top.jzts();
			$("#Form").submit();
		}
		
		//新增
		function add(){
			 window.location.href= '<%=basePath%>bussinessactivity/goAdd.do';
		}
		
		//删除
		function del(Id){
				if(confirm("确定要删除吗?")) {
					window.location.href = "<%=basePath%>bussinessactivity/delete.do?BUSSINESSACTIVITY_ID="+Id+"&tm="+new Date().getTime();
				}
		}
		
		//修改
		function edit(Id){
			 window.location.href = '<%=basePath%>bussinessactivity/goEdit.do?BUSSINESSACTIVITY_ID='+Id;
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
								url: '<%=basePath%>bussinessactivity/deleteAll.do?tm='+new Date().getTime(),
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
			window.location.href='<%=basePath%>bussinessactivity/excel.do';
		}
		</script>
		
	</body>
</html>

