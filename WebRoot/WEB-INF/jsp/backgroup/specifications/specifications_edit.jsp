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
		
		<link rel="stylesheet" href="static/css/datepicker.css" /><!-- 日期框 -->
		<script type="text/javascript" src="static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="static/js/jquery.tips.js"></script>
		 <style>
        *{
            margin:0;
            padding:0;
            box-sizing: border-box;
        }

        a{
            color:#000;
            cursor: pointer;
            text-decoration: none;
        }
        input,button,textarea{
            border:none;
            outline: none;
        }
        ul{
            list-style: none;
        }

        .wholeDiv
        {
           /*  width: 500px;
            height: 500px; */
            margin: auto;
            /* background: #eeeeee; */
        }
        .wholeDiv p
        {
            text-align: left;
            height: 40px;
            height:20px;
            line-height:20px;
        }
        .wholeDiv p>input,.wholeDiv p>a
        {
             height:20px;
            line-height:20px;
        }
        #xx{
        display:inline-block;
        background:#268CBB;
        color:#fff;
        padding:3px 10px;
        margin-left:10px;
        position:relative;
        top:-3px;
        }
        .showDiv {
            background: #eee;
            margin-top: 20px;
            /* width: 100%; */
            height: 200px;
            padding: 10px 5px;
        }
        .newDiv
        {
            padding:5px 10px;
   			 background-color:#268CBB;
            /*border:1px solid black;*/
            color:white;
            float:left;
            display: block;
            margin-right: 10px;
            margin-bottom: 10px;
            position: relative;
            line-height: 30px;
            height:30px !important;
        }
        .newDiv a
        {
            position: absolute;
            top: 0;
            right: 0;
            width: 12px;
            height: 12px;
            background: url("static/img/closeRight.png") no-repeat center;
            z-index: 5;
            display: none;
        }
    </style>
<script type="text/javascript">
	
	
	//保存
	function save(){
// 			if($("#COMMODITY_ID").val()==""){
// 			$("#COMMODITY_ID").tips({
// 				side:3,
// 	            msg:'请输入所属商品',
// 	            bg:'#AE81FF',
// 	            time:2
// 	        });
// 			$("#COMMODITY_ID").focus();
// 			return false;
// 		}
		if($("#NAME").val()==""){
			$("#NAME").tips({
				side:3,
	            msg:'请输入规格名',
	            bg:'#AE81FF',
	            time:2
	        });
			$("#NAME").focus();
			return false;
		}
			var pausedCause = '';
		$('.newDiv').each(function() {
			pausedCause+=$(this).text().trim()+ ',';	
		});
		if(pausedCause.length>0){
			pausedCause = pausedCause.substring(0,pausedCause.length-1);
			$("#content").val(pausedCause);
		}
		$("#Form").submit();
		
		$("#zhongxin").hide();
		$("#zhongxin2").show();
	}
	
</script>
	</head>
<body>
	<form action="specifications/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="SPECIFICATIONS_ID" id="SPECIFICATIONS_ID" value="${pd.SPECIFICATIONS_ID}"/>
		<input type="hidden" name="COMMODITY_ID" id="COMMODITY_ID" value="${pd.COMMODITY_ID}"/>
		<input type="hidden" name="content" id="content" value=""/>
		<div id="zhongxin">
		<table id="table_report" class="table table-striped table-bordered table-hover">
<!-- 			<tr> -->
<!-- 				<td style="width:70px;text-align: right;padding-top: 13px;">所属商品:</td> -->
<!-- 				<td><input type="text" name="COMMODITY_ID" id="COMMODITY_ID" value="${pd.COMMODITY_ID}" maxlength="32" placeholder="这里输入所属商品" title="所属商品"/></td> -->
<!-- 			</tr> -->
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">规格名:</td>
				<td><input type="text" name="NAME" style="height:30px !important;" id="NAME" value="${pd.NAME}" maxlength="32" placeholder="这里输入规格名" title="规格名"/></td>
			</tr>
			<tr>
				<td style="width:100px;text-align: right;padding-top: 13px;">规格属性:</td>
				<td>
				<div class="wholeDiv">
    				<p><input type="text" style="height:30px !important;" id="text" placeholder="这里输入规格属性" /><a style="height:25px !important;" id="xx">提交</a></p>
    			<div class="showDiv" style="background-color: white;">
					<c:forEach items="${list }" var="var">
						<p class="newDiv" >
							${var.NAME}<a class="closeA"></a>
						</p>					
					</c:forEach>
    			</div>
				</div>
				</td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="10">
					<a class="btn btn-mini btn-primary" onclick="save();">保存</a>
					<a class="btn btn-mini btn-danger" onclick="top.Dialog.close();">取消</a>
				</td>
			</tr>
		</table>
		</div>
		
		<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><br/><br/><img src="static/images/jiazai.gif" /><br/><h4 class="lighter block green">提交中...</h4></div>
		
	</form>
	
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script><!-- 下拉框 -->
		
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript">
    $("#xx").click(function () {
        var currentText = $("#text").val();
        if (currentText){
//             alert(currentText);
            $('<p class="newDiv" ></p>').html(currentText+'<a class="closeA"></a>')
                    .appendTo($('.showDiv'));
            $("input[id='text']").val("").focus(); // 清空并获得焦点
        }
    });
    $('body').on('mouseenter', '.showDiv .newDiv', function() {
        $(this).find("a").css("display","block");
    });
    $('body').on('mouseleave', '.showDiv .newDiv', function() {
        $(this).find("a").css("display","none");
    });
    $('body').on('click', '.showDiv .newDiv a', function() {
//         alert("delete");
			$(this).parent().remove();
    });
</script>
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			
			//单选框
			$(".chzn-select").chosen(); 
			$(".chzn-select-deselect").chosen({allow_single_deselect:true}); 
			
			//日期框
			$('.date-picker').datepicker();
			
		});
		
		</script>
</body>
</html>