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
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<title></title>
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.tips.js"></script>
		<link href="${pageContext.request.contextPath }/static/css/basicStyle.css" rel="stylesheet" type="text/css">
   		<link href="${pageContext.request.contextPath }/static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
    	<link href="${pageContext.request.contextPath }/static/css/ListStyle.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .header_account{
            height: 40px;
        }
        .header_account_left{
            color: white;
            font-size: 16px;
            text-align: center;
            line-height: 40px;
            height: 100%;
            width: 150px;
            background-color: #5ba2cf;
        }
        .header_account_right{
            color: #5ba2cf;
            font-size: 16px;
            font-weight: bold;
            border: 1px solid #5ba2cf;
            border-radius: 4px;
            height: 34px;
            width: 100px;
            line-height: 34px;
            text-align: center;

        }
        .header_account:after{
            clear: both;
            content: '';
            display: block;
        }

        .width23{
            width: 23%;
        }
        .width31{
            width: 31%;
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
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#SUPERIOR").val()==""){
			$("#SUPERIOR").tips({
				side:3,
	            msg:'请输入上级ID',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#SUPERIOR").focus();
			return false;
		}
		if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入分类名称',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
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
            <li class=""><p><a href="${pageContext.request.contextPath }/bussinessactivity/list.do">优惠信息</a></p></li>
            <li><p><a href="${pageContext.request.contextPath }/membershipcard/list.do">会员卡管理</a></p></li>
            <li>
                <p class="child_store_p"><a style="color: #5aa3d0">店铺设置</a></p>
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



<form action="businessuser/list.do" name="Form" id="Form" method="post">
<p class="top"></p>
<div class="center_div">
    <div class="header_account">
        <div class="header_account_left lf">已开通账号</div>
        <div class=" header_account_right rf"><a style="color: #5ba2cf;" onclick="add();"> 新增账号</a></div>
    </div>
    <table class="table" style="width:100%;">
				<thead>
					<tr style="line-height:50px;">
						<th>账号</th>
						<th>开通时间</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
        <!-- 开始循环 -->	
        		<c:if test="${pd.FBUSINESSUSER_ID=='0' }">
					<c:choose>
					<c:when test="${not empty varList}">
						
						<c:forEach items="${varList}" var="var" varStatus="vs">
						 <tr>
							
								<td style="vertical-align: middle;text-align: auto;">${var.TEL}</td>
					            <td style="vertical-align: middle;text-align: auto;">${var.CREATIME}</td>
					            <td style="vertical-align: middle;text-align: auto;">
					            	<c:if test="${var.FFLAG==1}">
										正常
									</c:if>
									<c:if test="${var.FFLAG==2}">
										禁用
									</c:if>
					            </td>
					            <c:if test="${var.FFLAG==1}">
									<td style="vertical-align: middle;text-align: auto;" onclick="edit('${var.BUSINESSUSER_ID}','2');">
									<a class="btn btn-small btn-danger">禁用</a>
									</td>
								</c:if>
								<c:if test="${var.FFLAG==2}">
									<td style="vertical-align: middle;text-align: auto;" onclick="edit('${var.BUSINESSUSER_ID}','1');">
									<a class="btn btn-small btn-success">激活</a>
									</td>
								</c:if>
					            
						 </tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
							<tr><td colspan="100" style="vertical-align: middle;text-align: auto;">没有相关数据</td></tr>
					</c:otherwise>
				</c:choose>	
				</c:if>
				<c:if test="${pd.FBUSINESSUSER_ID!='0' }">
					<tr><td colspan="100" style="vertical-align: middle;text-align: auto;">暂无权限</td></tr>
				</c:if>
				
    </tbody>
    </table>

    <!--分页-->
    <div class="pages">
    <c:if test="${pd.FBUSINESSUSER_ID=='0' }">${page.pageStr}</c:if>
    <!--
        <ul>
        
            <li class="pages_item">首页</li>
            <li class="pages_item">上一页</li>
            <li class="pages_item">1</li>
            <li class="pages_item pages_item_sel">2</li>
            <li class="pages_item">3</li>
            <li class="pages_item">4</li>
            <li class="pages_item">下一页</li>
            <li class="pages_item">尾页</li>
        </ul>
    --></div>
</div>
 <div class="foot" style="margin-top: 50px;width: 100%;height: 80px;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.1.min.js"></script>
<!-- 引入 -->
		<script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
		<script src="${pageContext.request.contextPath }/static/js/ace-elements.min.js"></script>
		<script src="${pageContext.request.contextPath }/static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery.tips.js"></script><!--提示框-->
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


	</form>
		<!-- 引入 -->
		<script type="text/javascript">
		//修改
		function add(){
			window.location.href='<%=basePath%>businessuser/goAddZC.do';
		}
		//修改
		function edit(Id,FFLAG){
			window.location.href='<%=basePath%>businessuser/edit.do?BUSINESSUSER_ID='+Id+'&FFLAG='+FFLAG;
		}
		</script>
		
</body>
</html>