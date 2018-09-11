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
		<link href="static/css/bootstrap.min.css" rel="stylesheet" />
		<link href="static/css/bootstrap-responsive.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="static/css/font-awesome.min.css" />
		<!-- 下拉框 -->
		<link rel="stylesheet" href="static/css/chosen.css" />
		
		<link rel="stylesheet" href="static/css/ace.min.css" />
		<link rel="stylesheet" href="static/css/ace-responsive.min.css" />
		<link rel="stylesheet" href="static/css/ace-skins.min.css" />
		<link rel="stylesheet" href="static/js/jedate/skin/jedate.css" />
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		<script type="text/javascript" src="static/js/jedate/jedate.js"></script>
		
<script type="text/javascript">
	
	
	//保存
	function save(){
			if($("#DISCOUNT").val()==""){
	          alert('请输入折扣');
			return false;
		}else{
			 var re = /^[0-9]+\.?[0-9]*$/;
			 if($("#DISCOUNT").val()>0){
				 if($("#DISCOUNT").val()<10){
					 if (!re.test($("#DISCOUNT").val())) 
					    {
								alert('请输入正确折扣(正数或者小数)');
							return false;
					    }
					
				 }else{
							alert('请输入正确折扣(正数或者小数)');
						return false;
				 }
			 }else{
						alert('请输入正确折扣(正数或者小数)');
					return false;
			 }
			   
		}
		if($("#GOSTARTTIME").val()==""){
				alert('请输入领取开始时间');
			return false;
		}
		if($("#GOENDTIME").val()==""){
				alert('请输入领取截至时间');
			return false;
		}
		if($("#USESTARTTIME").val()==""){
				alert('请输入使用开始时间');
			return false;
		}
		if($("#USEENDTIME").val()==""){
				alert('请输入使用截至时间');
			return false;
		}
		if($("#NUMBER").val()==""){
				alert('请输入数量');
			return false;
		}else{
			var re = /^[1-9]*[1-9][0-9]*$/;
		    if (!re.test($("#NUMBER").val())){
		            alert('请输入正确数量(整数)');
				return false;
		    }
		}
		
		$("#Form").submit();
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	<link href="static/css/basicStyle.css" rel="stylesheet" type="text/css">
    <link href="static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
    <link href="static/css/ListStyle.css" rel="stylesheet" type="text/css">
    <style type="text/css">
        .item{
            display: flex;
            display: -webkit-flex;
            align-items: center;
            margin-bottom: 20px;
            width: 50%;
            /*color: #989898;*/
        }
        .item span{
            width: 100px;
            text-align: end;
            margin-right: 15px;

        }
        .span_margin{
            margin: 0 10px;
        }
        .input_div1{
            width: 335px;
            margin-right: 10px;
        }
        .input_div2{
            width: 150px;
        }
        .center{
            padding-top: 100px;
            align-items: center;
            display: flex;
            display: -webkit-flex;
            flex-direction: column;
        }
        .btn{
           background-color: #5ba2cf;
            border-radius: 5px;
            width: 150px;
            height: 40px;
            line-height: 40px;
            text-align: center;
            color: white;
            font-weight: bold;
            margin: 50px 0;

        }
        .datepicker table {
    margin: 0;
    width: 210px;
}
.dropdown-menu {
    border-radius: 0;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    width: 220px;
}
    </style>
</head>
<body>
<div>
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



    <p class="top">新增会员卡</p>
    <form action="membershipcard/${msg }.do" name="Form" id="Form" method="post">
    <div class="center_div center">
        <div class="item">
            <span>折扣：</span>
            <input type="number" name="DISCOUNT" id="DISCOUNT" value="${pd.DISCOUNT}" class="input_div1">
            <label>折</label>
        </div>
        <div class="item">
            <span>领取时间：</span>
            <input data-date-format="yyyy-mm-dd hh:ii" class="input_div2" type="text"  name="GOSTARTTIME" id="GOSTARTTIME" value="${pd.GOSTARTTIME}" placeholder=" "><label class="span_margin">至</label><input data-date-format="yyyy-mm-dd hh:ii" class="input_div2"  type="text" name="GOENDTIME" id="GOENDTIME" value="${pd.GOENDTIME}" placeholder=" ">
        </div>
        <div class="item">
            <span>使用时间：</span>
            <input data-date-format="yyyy-mm-dd hh:ii" class="input_div2" type="text" name="USESTARTTIME" id="USESTARTTIME" value="${pd.USESTARTTIME}" placeholder=" "><label class="span_margin">至</label><input data-date-format="yyyy-mm-dd hh:ii" class="input_div2" type="text" name="USEENDTIME" id="USEENDTIME" value="${pd.USEENDTIME}" placeholder=" ">
        </div>
        <div class="item">
            <span>数量：</span>
            <input type="number" name="NUMBER" id="NUMBER" value="${pd.NUMBER}" class="input_div1">
            <label>份</label>
        </div>
        <div class="btn" onclick="save();" style="line-height:25px;">确定</div>
    </div>
    </form>
    <div class="foot" style="margin-top:50px;width: 100%;height: 80px;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
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
</div>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('#GOSTARTTIME').datepicker();
			$('#GOENDTIME').datepicker();
			$('#USESTARTTIME').datepicker();
			$('#USEENDTIME').datepicker();
			
			
			
			
		});
		
		</script>
</body>
</html>