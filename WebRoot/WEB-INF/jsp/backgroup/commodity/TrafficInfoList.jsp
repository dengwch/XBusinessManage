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
        .header_info{
            height: 50px;
        }
        .top_lf{
            border: 1px solid #dddddd;
            border-radius: 4px;
            font-size: 16px;
            color: #bbbbbb;
            padding: 7px 30px;
        }
        .top_rf{
            border-radius: 4px;
            font-size: 16px;
            color: white;
            background-color: #5aa3d0;
            padding: 5px 10px;
            margin-left: 40px;
        }
        .width25{
            width: 25%;
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
                <p class="child_goods_p"><a href="${pageContext.request.contextPath }/commodity/list.do" style="color: #5aa3d0">商品管理</a> </p>
                <!--商品管理子类-->
                <ul class="child goodChild">
                    <li><a href="${pageContext.request.contextPath }/commodity/goAdd.do">新增商品</a></li>
                    <li><a href="${pageContext.request.contextPath }/classification/list.do">分类管理</a></li>
                </ul>
            </li>
            <li class=""><p><a href="${pageContext.request.contextPath }/bussinessactivity/list.do">优惠信息</a></p></li>
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



<form action="commodity/listLL.do" name="Form" id="Form" method="post">
<input type="hidden" value="${pd.SQFLAG1}" id="SQFLAG2"/>
			<input type="hidden" value="${pd.CLASSIFICATION_ID}" id="CLASSIFICATION_ID1"/>
<p class="top">流量信息>流量列表</p>
<div class="center_div">
    <div class="header_info">
        <input id="NAME" type="text" name="NAME" value="${pd.NAME}" placeholder="商品名称">
        <span class="top_rf" onclick="search();">查询</span>
    </div>
     
     <table class="table" style="width:100%;">
				<thead>
					<tr style="line-height:50px;">
						<th>名称</th>
						<th>分类</th>
						<th>售价</th>
						<th>收藏量</th>
						<th>点击量</th>
					</tr>
				</thead>
				<tbody>
        <!-- 开始循环 -->	
				<c:choose>
					<c:when test="${not empty zongList}">
						
						<c:forEach items="${zongList}" var="var" varStatus="vs">
						 <tr>
									<td style="vertical-align: middle;text-align: auto;">
										${var.varList.NAME}
									</td>
									
									<td style="vertical-align: middle;text-align: auto;">
										${var.varList.CLASSIFICATION_ID}
									</td>
									
									<td style="vertical-align: middle;text-align: auto;">
										${var.varList.PRICE}
									</td>
									
									<td style="vertical-align: middle;text-align: auto;">
										${var.countCollection}
									</td>
									<td style="vertical-align: middle;text-align: auto;">
										${var.countVisit}								
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
    	${page.pageStr}
    </div>
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
	
		
		
		//检索
		function search(){
			$("#Form").submit();
		}
		
		//新增
		function add(){
			window.location.href='<%=basePath%>commodity/goAdd.do';
		}
		//批量操作
		function delxs(msg,FLAG){
			if(FLAG=="3"){
				window.location.href = "<%=basePath%>commodity/delete.do?COMMODITY_ID="+Id+"&tm="+new Date().getTime()+"&FLAG="+FLAG;
			}else if(FLAG=="1"){
				window.location.href= "<%=basePath%>commodity/delete.do?COMMODITY_ID="+Id+"&tm="+new Date().getTime()+"&FlAG="+FLAG+"&type="+type;
			}else if(FLAG=="2"){
				window.location.href= "<%=basePath%>commodity/delete.do?COMMODITY_ID="+Id+"&tm="+new Date().getTime()+"&FlAG="+FLAG+"&type="+type;
			}
					
				
			
		}
		//删除/上架/下架
		function del(msg,FLAG){
			var type="gai";
			if(FLAG=="1"){
				var str = '';
				for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
				}
				if(str==''){
					alert("您没有选择任何内容!");
					return;
				}else{
					if(msg == '确定要下架选中的数据吗?'){
						
						$.ajax({
							type: "POST",
							url: '<%=basePath%>commodity/SXAll.do?FlAG='+FLAG,
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
			if(FLAG=="2"){
				var str = '';
				for(var i=0;i < document.getElementsByName('ids').length;i++){
					  if(document.getElementsByName('ids')[i].checked){
					  	if(str=='') str += document.getElementsByName('ids')[i].value;
					  	else str += ',' + document.getElementsByName('ids')[i].value;
					  }
				}
				if(str==''){
					alert("您没有选择任何内容!");
					return;
				}else{
					if(msg == '确定要上架选中的数据吗?'){
						
						$.ajax({
							type: "POST",
							url: '<%=basePath%>commodity/SXAll.do?FlAG='+FLAG,
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
			
		}
		
		//修改
		function edit(Id){
			window.location.href='<%=basePath%>commodity/goEdit.do?COMMODITY_ID='+Id;
		}
		</script>
		
		<script type="text/javascript">
		
		
			
		
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
			
				alert(msg)
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++){
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						alert("您没有选择任何内容!");
						return;
					}else{
						if(msg == '确定要删除选中的数据吗?'){
							
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
		
		
		</script>
</body>
</html>