<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String TEL=(String)request.getSession().getAttribute("TEL");
	String  IMGURL=(String) request.getSession().getAttribute("IMGURL");
	String path = request.getContextPath();
	String OSSUrl = application.getInitParameter("OSSUrl");
	String OSSUrl1 = application.getInitParameter("OSSUrl1");
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta charset="utf-8" />
		<meta name="description" content="overview & stats" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/jPicture.min.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/index.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/swiper.min.css">
		<link href="${pageContext.request.contextPath }/static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
		
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.7.2.js"></script>
  <script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/static/js/swiper.min.js"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/resources/layer/layer.js"></script>
   
	<style>
	  
	
	
	
	
	
	  li{
	  	list-style: none;
	  }
	  a{
	  	text-decoration: none;
	  }
	   #imgBox{ width: 700px; height: 400px; margin-left: auto; margin-right: auto; margin-top: 100px}
	  a:hover{
	  	text-decoration: none;
	  }
	  p{
	  	margin-bottom: 0;
	  }
	  body{
	  	font-size:12px; 
	  }
	  .label_box{display:none;width:430px;margin:20px auto;}

	  .label{height:30px;margin-left:142px;padding:0 10px;}

      .label li{float:left;line-height:30px;margin-right:15px;}
      .col-md-12{    background: #fff;padding: 50px 20% 50px 20%;}
      .guige li{
      	width: 100%;
      	background: #eee;
      	padding: 20px 30px;
      	border:1px dashed #5ba2cf;
      	margin-bottom:20px;
      }
      label{
      word-break:break-all;
}
      .newDiv{
      margin-left:20px;line-height:60px;}
      .newDiv label{
      word-break:break-all;
      width:95%;}
      .newDiv a{
      clear:both;   
      font-size:12px;
      flaot:left;
      }
      .showDiv{overflow-y:scroll;max-height:350px;}
       .swiper-container {
        width: 100%;
        height: 500px;
    }
    .swiper-slide {
        text-align: center;
        font-size: 18px;
        background: #fff;

        /* Center slide text vertically */
        display: -webkit-box;
        display: -ms-flexbox;
        display: -webkit-flex;
        display: flex;
        -webkit-box-pack: center;
        -ms-flex-pack: center;
        -webkit-justify-content: center;
        justify-content: center;
        -webkit-box-align: center;
        -ms-flex-align: center;
        -webkit-align-items: center;
        align-items: center;
    }
    #lunbo1 .item img {
    height: 350px;
}
	</style>
	
	
	<%-- <style type="text/css">  
            BODY {  
                FONT-SIZE: 14px;  
                LINE-HEIGHT: 1.5;  
                TEXT-DECORATION: none;  
                FONT-FAMILY: simsun, Arial, "宋体";  
                background-color: #FFFFFF;  
            }  
            td a:HOVER {  
                color: red;  
                background-color:#89d9fa;  
            }  
               
             #lookImg1 /*新增加大图样式*/  
            {  
                margin: 0 auto;  
                border: 1px solid #0F0F0F;  
                padding: 20px;  
                height:660px;  
                width: 480px;  
                background: #FFFFFF;  
                display: none;  
                position: absolute; /*  这个对显示鼠标的位置很重要，如果不是绝对位置的话，将显示的地方不一样*/  
                vertical-align: middle;  
                z-index:100;
                text-align: center;  
                font-family: Arial;  
            }  
           
    </style>  
        <script src="http://code.jquery.com/jquery-1.11.2.js"></script>  
              
        <script>  
            $(function(){  
                  
                $(".lookImages").mouseover(function (e) {  
                    var p=$(this).attr("src");  
                    var lookImg = "<div class='lookImg1' id = 'lookImg1'>";  
                        lookImg+= "<img src ='"+p+"'  height='460px' width='460px' />";  
                        lookImg+= "</div>";  
                       
                    $("body").append(lookImg);  
                    $("#lookImg1").css({  
                       "bottom": (e.pageY/2) + "px",  
                       "right": (e.pageX/2) + "px"  
                    }).show("slow");   
                }).mouseout(function () {  
                    $("#lookImg1").remove();  
                }).mousemove(function (e) {  
                    $("#lookImg1").css({  
                         "bottom": (e.pageY/2) + "px",  
                         "right": (e.pageX/2) + "px"  
                    });  
                });//----mouseover--end  
            });  
          
        </script>   --%>
	<style type="text/css">
.exampleImg { height:100px; cursor:pointer;}
</style>
<script type="text/javascript">	
	
	//保存
	function save(){
		if(confirm("默认为上架商品是否确认")){
			if($("#CLASSIFICATION_ID").val()==""){
				alert('请输入分类ID');
			return false;
		}
		if($("#NAME").val()==""){
			alert('请输入商品名称');
			return false;
		}
		
		if($("#PRICE").val()=="" || $("#PRICE").val()=="0" || $("#PRICE").val()==null){
			alert('请输入商品售价');
			return false;
		}else{
			
			
		    var re = /^[0-9]+\.?[0-9]*$/;
		    if (!re.test($("#PRICE").val())) 
		    {
		    	alert('请输入正确售价(正数或者小数)');
				return false;
		    } 
		}
		
		if($("#ZK").val()==""){
			
		}else{
			 var re = /^[0-9]+\.?[0-9]*$/;
			    if (!re.test($("#ZK").val())) 
			    {
			    	alert('请输入正确折扣(正数或者小数)');
			    	
					return false;
			    }
		}
		if($(".img_div div ").size()==0){
			alert('请上传商品图片');
			return false;
		}
		if($("#dimg div ").size()==0){
			alert('请上传详情图片');
			return false;
		}
		var num=$("#num").val();
		for(var i=0;i<num;i++){
			if($("#kucun"+i).val()==""){
				alert('请输入库存');
				return false;
			}
			if($("#jg"+i).val()==""){
				alert('请输入价格');
				return false;
			}
		}
		$("#Form").submit();
		}
			
	}
	
</script>
	</head>
<body>
<input type="hidden" value="<%=OSSUrl %>" id="ossder" />
<input type="hidden" value="<%=OSSUrl1 %>" id="ossder1" />
<input type="hidden" value="" id="shType" />
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

	<form action="commodity/${msg }.do" name="Form" id="Form" method="post">
		<input type="hidden" name="COMMODITY_ID" id="COMMODITY_ID" value="${pd.COMMODITY_ID}"/>
		<input type="hidden" name="CLASSIFICATION_ID1" id="CLASSIFICATION_ID1" value="${pd.CLASSIFICATION_ID }"/>
		<input type="hidden" name="num" id="num" value="0"/>
		<input type="hidden" name="imgID" id="imgID" value="<%=IMGURL %>"/>
		<div class="container">
	    <h3 style="font-size: 20px;color: #5ba2cf; ">新增商品</h3>
		<div class="row clearfix">
			<div class="col-md-12 column">
			<p>商品分类：
			    <select name="SQFLAG1" id="SQFLAG1" onchange="findShi()" class="form-control" style="width: 145px;">
			     <option value="">一级分类</option>
			     
    <c:if test="${empty listClass}"></c:if>
    <c:if test="${not empty listClass}">
    
    <c:forEach items="${listClass}" var="LIclass">
					 
       					<option value ="${LIclass.CLASSIFICATION_ID }" <c:if test="${LIclass.CLASSIFICATION_ID==pd.SUPERIOR }">selected</c:if>>${LIclass.NAME }</option>
      					</c:forEach>
    </c:if>
			     
					 
			    </select>
			     <select name="CLASSIFICATION_ID" id="CLASSIFICATION_ID" class="form-control"  style="width: 145px;">
			     <option value="">二级分类</option>
			    </select>
			</p>
			<p>商品名称：<input type="text" name="NAME" id="NAME" value="${pd.NAME}" style="width: 300px;"></p>
			<p>商品简介：<input type="text" name="WORDREMARK" id="WORDREMARK" value="${pd.WORDREMARK}" style="width: 300px;"></p>
			<p class="guige">商品规格： <a  role="button" class="btn btn-primary" data-toggle="modal" onclick="qingk();">新增规格</a></p>
		   <ul class="guige" id="guige1">
		       
	       </ul>
			<p style="padding-left: 28px;">价格：<input type="text" name="PRICE" id="PRICE" value="${pd.PRICE}" style="width: 300px;">元</p>
			<p style="padding-left: 28px;">折扣：<input type="text" name="ZK" id="ZK" value="${pd.ZK }" style="width: 300px;">折</p>
			
			
			<p >商品图片：
				<a onclick="add('tupian')" class="a-upload">
					上传图片
					</a>
				<div  class="img_div">
				 <c:forEach items="${varOList}" var="OList">
				 <c:if test="${fn:substring(OList.IMGURL, 0, 3)=='gzh'}">
				 	<div  class='isImg2'><img onclick="chakanimg('tupian');" src="<%=OSSUrl1 %>${OList.IMGURL }" onclick="javascript:lookBigImg(this)" style="height: 100%; width: 100%;" /><a class="removeBtn" onclick="removeImg(this,'${OList.COMMODITYIMG_ID }','${OList.IMGURL }')">x</a></div>
				 </c:if>
				 <c:if test="${fn:substring(OList.IMGURL, 0, 3)!='gzh'}">
				 	<div  class='isImg2'><img onclick="chakanimg('tupian');" src="<%=OSSUrl %>${OList.IMGURL }" onclick="javascript:lookBigImg(this)" style="height: 100%; width: 100%;" /><a class="removeBtn" onclick="removeImg(this,'${OList.COMMODITYIMG_ID }','${OList.IMGURL }')">x</a></div>
				 </c:if>
					
				</c:forEach>
				</div>	
			</p>
			
			<p style="margin-top:50px;margin-bottom:60px;float:left;width:100%;">商品详情：
				<a onclick="add('xiangqing')" class="a-upload">
					上传图片
					</a>			
				<div id="addCommodityIndex">  
		           <!--  input-group start-->
		             
					<input type="file" name="detailimgFile" onchange="previewImage(this)" style="display: none;" id="previewImg">
		            <div  class="input-group row" id="dimg" style="margin-left:-100px;">
		            
					<c:forEach items="${deIMGList}" var="deIList">
						<div class='isImg3' id="preview" style="" >
			        		 <c:if test="${fn:substring(deIList.IMGURL, 0, 3)=='gzh'}">
							 	<img onclick="chakanimg('xiangqing');" id="imghead" border="0" src="<%=OSSUrl1 %>${deIList.IMGURL}" width="100%" height="100%"/><a class="removeBtn" style="line-height:22px" onclick="removedelImg(this,'${deIList.ORDERCOMMODITYIMG_ID }','${deIList.IMGURL}')">x</a>
							 </c:if>
							 <c:if test="${fn:substring(deIList.IMGURL, 0, 3)!='gzh'}">
							 	<img onclick="chakanimg('xiangqing');" id="imghead" border="0" src="<%=OSSUrl %>${deIList.IMGURL}" width="100%" height="100%"/><a class="removeBtn" style="line-height:22px" onclick="removedelImg(this,'${deIList.ORDERCOMMODITYIMG_ID }','${deIList.IMGURL}')">x</a>
							 </c:if>
	                    </div> 
					</c:forEach>
		            </div>
				</div>
			</p>
			<p><button type="button" class="btn btn-primary" data-toggle="button" style="width:200px;margin-left:100px;" onclick="save();"> 确定</button></p>
			</div>
		</div>
   </div>
    <div class="foot" style="width: 100%;height: 80px;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>
		<input value="<%=OSSUrl %>" id="ossurl" style="display:none">
	</form>
	
	<div class="modal fade" id="modal-container-467791"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
										
					</div>
			</div>
	
	<div class="tanchuang3">
    <div class="xianshi3" style="position:relative">
            <div class="carousel slide" id="carousel-227266">
                <div id="lunbo3" class="carousel-inner">
                   	<c:forEach items="${deIMGList}" var="OList" varStatus="vs">
                   		<c:if test="${vs.index==0}">
              				 <c:if test="${fn:substring(OList.IMGURL, 0, 3)=='gzh'}">
							 	<div class='item active'><img src='<%=OSSUrl1 %>${OList.IMGURL }' onclick='javascript:lookBigImg(this)' style='height: 350px; width: 100%;' /></div>
							 </c:if>
							 <c:if test="${fn:substring(OList.IMGURL, 0, 3)!='gzh'}">
							 	<div class='item active'><img src='<%=OSSUrl %>${OList.IMGURL }' onclick='javascript:lookBigImg(this)' style='height: 350px; width: 100%;' /></div>
							 </c:if>
                   			
                   		</c:if>
                   		<c:if test="${vs.index!=0}">
                   			 <c:if test="${fn:substring(OList.IMGURL, 0, 3)=='gzh'}">
							 	<div class='item'><img src='<%=OSSUrl1 %>${OList.IMGURL }' onclick='javascript:lookBigImg(this)' style='height: 350px; width: 100%;' /></div>
							 </c:if>
							 <c:if test="${fn:substring(OList.IMGURL, 0, 3)!='gzh'}">
							 	<div class='item'><img src='<%=OSSUrl %>${OList.IMGURL }' onclick='javascript:lookBigImg(this)' style='height: 350px; width: 100%;' /></div>
							 </c:if>
                   			
                   		</c:if>
					</c:forEach>
                </div> <a class="left carousel-control" href="#carousel-227266" data-slide="prev" style="line-height:300px;">上一张</a> <a class="right carousel-control" href="#carousel-227266" data-slide="next" style="line-height:300px;">下一张</a>
            </div>
        <a class="guanbi"  style="position:absolute;bottom:0;">关闭</a>
    </div>
</div>
	
	
			
<div class="tanchuang2">
    <div class="xianshi2" style="position:relative">
            <div class="carousel slide" id="carousel-227265">
                <div id="lunbo2" class="carousel-inner">
                   	<c:forEach items="${varOList}" var="OList" varStatus="vs">
                   		<c:if test="${vs.index==0}">
                   			<c:if test="${fn:substring(OList.IMGURL, 0, 3)=='gzh'}">
							 	<div class='item active'><img src='<%=OSSUrl1 %>${OList.IMGURL }' onclick='javascript:lookBigImg(this)' style='height: 350px; width: 100%;' /></div>
							 </c:if>
							 <c:if test="${fn:substring(OList.IMGURL, 0, 3)!='gzh'}">
							 	<div class='item active'><img src='<%=OSSUrl %>${OList.IMGURL }' onclick='javascript:lookBigImg(this)' style='height: 350px; width: 100%;' /></div>
							 </c:if>
                   		</c:if>
                   		<c:if test="${vs.index!=0}">
                   			 <c:if test="${fn:substring(OList.IMGURL, 0, 3)=='gzh'}">
							 	<div class='item'><img src='<%=OSSUrl1 %>${OList.IMGURL }' onclick='javascript:lookBigImg(this)' style='height: 350px; width: 100%;' /></div>
							 </c:if>
							 <c:if test="${fn:substring(OList.IMGURL, 0, 3)!='gzh'}">
							 	<div class='item'><img src='<%=OSSUrl %>${OList.IMGURL }' onclick='javascript:lookBigImg(this)' style='height: 350px; width: 100%;' /></div>
							 </c:if>
                   		</c:if>
					</c:forEach>
                </div> <a class="left carousel-control" href="#carousel-227265" data-slide="prev" style="line-height:300px;">上一张</a> <a class="right carousel-control" href="#carousel-227265" data-slide="next" style="line-height:300px;">下一张</a>
            </div>
        <a class="guanbi"  style="position:absolute;bottom:0;">关闭</a>
    </div>
</div>

			
<div class="tanchuang">
    <div class="xianshi" style="position:relative">
            <div class="carousel slide" id="carousel-227263">
                <div id="lunbo" class="carousel-inner">
                   
                </div> <a class="left carousel-control" href="#carousel-227263" data-slide="prev" style="line-height:300px;">上一张</a> <a class="right carousel-control" href="#carousel-227263" data-slide="next" style="line-height:300px;">下一张</a>
            </div>
        <a class="guanbi"  style="position:absolute;bottom:0;">关闭</a>
    </div>
</div>
<div class="tanchuang1">
    <div class="xianshi1" style="position:relative">
            <div class="carousel slide" id="carousel-227264">
                <div id="lunbo1" class="carousel-inner">
                  
                </div> <a class="left carousel-control" href="#carousel-227264" data-slide="prev" style="line-height:300px;">上一张</a> <a class="right carousel-control" href="#carousel-227264" data-slide="next" style="line-height:300px;">下一张</a>
            </div>
        <a class="guanbi"  style="position:absolute;bottom:0;">关闭</a>
    </div>
</div>
<div class="tanchuang_1">
    <div class="modal-content">
							<div class="modal-header">
								 <button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick="gb()">×</button>
								<h4 class="modal-title" id="myModalLabel">
									新增规格
								</h4>
							</div>
							<input type="hidden" name="SPECIFICATIONS_ID" id="SPECIFICATIONS_ID" value="${pd.SPECIFICATIONS_ID}"/>
							<input type="hidden" name="COMMODITY_ID1" id="COMMODITY_ID1" value="${pd.COMMODITY_ID}"/>
							<input type="hidden" name="content" id="content" value=""/>
							<div class="modal-body">
								<p style="padding-left:20px; ">规格名称：<input style="background: #eee; border: 1px solid #ccc;border-radius: 5px;line-height: 30px; font-size: 12px; margin-right: 10px;" type="text" name="NAME" id="NAME1"></p>
								<div class="label_box">
									<ul class="label"></ul>
								</div>
								<div style="margin:20px auto;">
									<p><a style="margin-left:20px;font-size:12px;font-weight:100;">规格属性：</a>
									<input id="text" style="background: #eee; border: 1px solid #ccc;border-radius: 5px;line-height: 30px; font-size: 12px; margin-right: 10px;" id="kk"type="text" onkeyup="this.style.color='#333';" />
									<a style="height:32px !important;margin-top:-5px"  class="btn btn-primary"  id="xx">提交</a>
									</p>
									<div class="showDiv" style="margin: 0 auto;">
										
										
									</div>
								
								
								</div>
							</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-default" onclick="gb()" data-dismiss="modal">关闭</button> <button type="button"  data-dismiss="modal" aria-hidden="true" class="btn btn-primary" onclick="sva();">保存</button>
							</div>
						</div>
</div>
	
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='${pageContext.request.contextPath }/static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-1.7.2.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/ajaxfileupload.js"></script>
		
		<script>

  		  </script>
<script type="text/javascript">

<%--$(function(){  
    
    $(".lookImages").mouseover(function (e) {  
        var p=$(this).attr("p");  
        var lookImg = "<div id = 'lookImg'>";  
            lookImg+= "<img src ='"+p+"'  height='460px' width='460px' />";  
            lookImg+= "</div>";  
           
        $("body").append(lookImg);  
        $("#lookImg").css({  
           "bottom": (e.pageY/2) + "px",  
           "right": (e.pageX/2) + "px"  
        }).show("slow");   
    }).mouseout(function () {  
        $("#lookImg").remove();  
    }).mousemove(function (e) {  
        $("#lookImg").css({  
             "bottom": (e.pageY/2) + "px",  
             "right": (e.pageX/2) + "px"  
        });  
    });//----mouseover--end  
});  --%>




	 var swiper = new Swiper('.swiper-container', {
	        pagination: '.swiper-pagination',
	        paginationClickable: '.swiper-pagination',
	        nextButton: '.swiper-button-next',
	        prevButton: '.swiper-button-prev',
	        spaceBetween: 30
	    });
	<%--  $(".isImg img").click(function(){
         $(".tanchuang").show();
         $("body").css("overflow","hidden");
       });
	 $("#modal-467791").click(function(){
         
       });

	$(".xianshi .guanbi").click(function(){
	 $(".tanchuang").hide();
	  $("body").css("overflow","scroll");
	});
	 $(".isImg1 img").click(function() {
         $(".tanchuang1").show();
         $("body").css("overflow", "hidden")
     });
     $(".xianshi1 .guanbi").click(function() {
         $(".tanchuang1").hide();
         $("body").css("overflow", "scroll")
     });
     
     $(".isImg2 img").click(function() {
         $(".tanchuang2").show();
         $("body").css("overflow", "hidden")
     });
     $(".xianshi2 .guanbi").click(function() {
         $(".tanchuang2").hide();
         $("body").css("overflow", "scroll")
     });
     $(".isImg3 img").click(function() {
         $(".tanchuang3").show();
         $("body").css("overflow", "hidden")
     });
     $(".xianshi3 .guanbi").click(function() {
         $(".tanchuang3").hide();
         $("body").css("overflow", "scroll")
     });--%>
$(document).ready(function(){
	
	$(".header_right>.shezhi ").mouseover(function(){
        $(".header_right>ul").show();
      });
      $(".header_right>.shezhi ").mouseout(function(){
    	  $(".header_right>ul").fadeOut(8000);
        });
});
function qingk(){
	$("#content").val("");
	$("#NAME1").val("");
	 $(".showDiv").empty(); 
	 
	 $(".tanchuang_1").show();
}
function gb(){
	$(".tanchuang_1").hide();
	$(".modal-backdrop").hide();
	
}
$("#xx").click(function () {
    var currentText = $("#text").val();
    if (currentText){
//         alert(currentText);
        
        var country = "";
        $(".showDiv").find("p label").each(function(){
        	if(currentText!=$(this).text()){
        		if(country==""){
            		country = country + $(this).text();
            	}else{
            		country = country +","+ $(this).text();
            	}
        	}
        		
        	
        });
        if(country==""){
    		country = country + currentText;
    	}else{
    		country = country +","+ currentText;
    	}
    	$("#content").val(country);
    	 $(".showDiv").empty(); 
    	 var countrys = $("#content").val().split(",");
    		for(var i=0;i<countrys.length;i++){
    			$('<p class="newDiv" ></p>').html('<label >'+countrys[i]+'</label><a style=" color:red;"  onclick="remove(this);">删除</a>').appendTo($('.showDiv'));
    		
    	    }
        $("input[id='text']").val("").focus(); // 清空并获得焦点
        
    }
});
function remove (sh){
	$(sh).parent().remove();
	 var country = "";
     $(".showDiv").find("p label").each(function(){
     		if(country==""){
         		country = country + $(this).text();
         	}else{
         		country = country +","+ $(this).text();
         	
     	}
     	
     });
 	$("#content").val(country);
}
var q="";
function sva(){
	
	if(q=="1"){
		var pausedCause = '';
		$('.newDiv').find("label").each(function() {
			pausedCause+=$(this).text().trim()+ ',';	
		});
		if(pausedCause.length>0){
			pausedCause = pausedCause.substring(0,pausedCause.length-1);
			$("#content").val(pausedCause);
		}
		if($("#NAME1").val()==""){
	        alert('请输入规格名');
			return false;
		}else if($("#content").val()==""){
	           alert('请输入规格属性');
			return false;
		}else {
			$.ajax({
				type : "post",
				dataType: "json",
				data : {
					"NAME":$("#NAME1").val(),
					"content":$("#content").val(),
					"COMMODITY_ID":$("#COMMODITY_ID1").val(),
					"SPECIFICATIONS_ID":$("#SPECIFICATIONS_ID").val()
				},
				url : "${pageContext.request.contextPath }/specifications/editx.do",
				success : function(data) {
					if(data.msg=='1'){
						findGuige();
						gb();
					}
				}
			});
		}
	}else{
		
	
		var pausedCause = '';
	$('.newDiv').find("label").each(function() {
		pausedCause+=$(this).text().trim()+ ',';	
	});
	if(pausedCause.length>0){
		pausedCause = pausedCause.substring(0,pausedCause.length-1);
		$("#content").val(pausedCause);
	}
	if($("#NAME1").val()==""){
        alert('请输入规格名');
		return false;
	}else if($("#content").val()==""){
           alert('请输入规格属性');
		return false;
	}else {
		$.ajax({
			type : "post",
			dataType: "json",
			data : {
				"NAME":$("#NAME1").val(),
				"content":$("#content").val(),
				"COMMODITY_ID":$("#COMMODITY_ID1").val(),
				"SPECIFICATIONS_ID":$("#SPECIFICATIONS_ID").val()
			},
			url : "${pageContext.request.contextPath }/specifications/save1.do",
			success : function(data) {
				if(data.msg=='1'){
					findGuige();
					gb();
				}
			}
		});
	}
	}
	}
</script>

		<script type="text/javascript">
		$(document).ready(function() {
			findGuige();
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
        				var str = '<option value="">二级分类</option>';
    					$("#CLASSIFIstr").append(str);
        				var list = data.listClassT;
        				var str = "";
        				//alert(list);
        				//alert(list.length);
        				var CLASSIFICATION_ID1=$("#CLASSIFICATION_ID1").val();
        				//alert(CLASSIFICATION_ID1);
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
        					$("#CLASSIFICATION_ID").append(str);
        				}else{
        					str+="<option value='暂无'>暂无</option>";
        					$("#CLASSIFICATION_ID").append(str);
        				}
    					
    					
        			}
        		});
    		}
		
	});
		$(function() {
			findGuige();
		});
		//上传
		function uploadphoto(fileId){
			//alert("似懂非懂");
		       if(checkFile(fileId)){ 
		        	$.ajaxFileUpload({
		            	url:"${pageContext.servletContext.contextPath}/businessuser/uploadBPhoto.do",
		            	secureuri:false,                       //是否启用安全提交,默认为false
		            	dataType:"json", 
		            	fileElementId:fileId,//文件选择框的id属性
		            	success:function(data){   
		            	var msg=data.msg;
		            	//alert(data.msg);
		            		
		            		if(data.flag == 0){ 
		                		alert("上传失败");
		                	}else if(data.flag == 1){ 
		                		alert("请选择文件后上传");
		                	}else if(data.flag == 2){ 
		                		alert("上传文件格式不正确");
		                	}else{
		                		
		                			$("#DETAIL").val(data.msg);
		                			if(msg.substring(0,3)=='gzh'){
		                				$("#shareImg").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
		                			}else{
		                				$("#shareImg").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
		                			}
		                			
		                		
		                	}
		            	}
		        	});
		       }
		    }
		
		function checkFile(fileName) {
			
	       	var fileImg = document.getElementById(fileName);
	       	if (fileImg.files[0] == undefined) {
	       		alert("请选择文件！");
	       		return false;
	       	} else {
	       		<%--$.ajax({
					type : "post",
					dataType: "json",
					data:{"COMMODITY_ID":$("#COMMODITY_ID").val()},
					url : "${pageContext.servletContext.contextPath}/commodityimg/photoNum.do",
					success : function(data) {
						if(data.num>10){
							alert("上传图片不能超过10张！");
				       		return false;
						}
					}
			  	});--%>
	       		return true;
	       	}
       }
		//添加图片
		function add(type){
			$("#shType").val(type);
			var id='${pd.COMMODITY_ID}';
			var locat1 = (window.location+'').split('/');
        	locat1 =  locat1[0]+'//'+locat1[2]+'/'+locat1[3];
			layer.open({
	    	    type: 2,
	    	    area: ['800px', '500px'],
	    	    shade: false,
	    	    title: false,
	    	    content: '${pageContext.servletContext.contextPath}/pictures/goAdd.do?COMMODITY_ID='+id+'&shType='+type
	        	    //$('#uploader')
	    	});
        	
		}
		//Js判断客户端是否为PC还是手持移动设备
	    function IsPC(){    
	         var userAgentInfo = navigator.userAgent;  
	         var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");    
	         var flag = true;    
	         for (var v = 0; v < Agents.length; v++) {    
	             if (userAgentInfo.indexOf(Agents[v]) > 0) { flag = false; break; }    
	         }    
	         return flag;    
	      }  
		//查看图片
		function chakanimg(type){
			var id='${pd.COMMODITY_ID}';
			var locat1 = (window.location+'').split('/');
        	locat1 =  locat1[0]+'//'+locat1[2]+'/'+locat1[3];
			layer.open({
	    	    type: 2,
	    	    area: ['1300px', '600px'],
	    	    shade: false,
	    	    title: false,
	    	    content: '${pageContext.servletContext.contextPath}/commodityDetailImg/listimg.do?COMMODITY_ID='+id+'&shType='+type
	        	    //$('#uploader')
	    	});
        	
		}
		//修改
		function edit1(Id,ew){
			q=ew;
			$("#SPECIFICATIONS_ID").val(Id);
				$.ajax({
					type : "post",
					dataType: "json",
					data : {
						"SPECIFICATIONS_ID":Id
					},
					url : "${pageContext.request.contextPath }/specifications/goEditx.do",
					success : function(data) {
						if(data.msg=='1'){
							$("#content").val("");
							$("#NAME1").val("");
							 $(".showDiv").empty(); 
							$("#NAME1").val(data.NAME);
							
							
							var list=data.list;
							for (var i = 0; i<list.length;i++){
								$('<p class="newDiv" ></p>').html('<label >'+list[i].NAME+'</label><a  onclick="remove(this);">×</a>').appendTo($('.showDiv'));
							}
							var country="";
							$(".showDiv").find("p label").each(function(){
					     		if(country==""){
					         		country = country + $(this).text();
					         	}else{
					         		country = country +","+ $(this).text();
					         	
					     	}
					     	
					     });
					 	$("#content").val(country);
					 	 $(".tanchuang_1").show();
					 	$(".modal-backdrop").css("","");
					 	
						}
					}
				});
			}
		//删除
		function del1(Id){
			if(confirm("是否确认删除")){
// 					top.jzts();
					var url = "<%=basePath%>specifications/delete.do?SPECIFICATIONS_ID="+Id+"&tm="+new Date().getTime();
					$.get(url,function(data){
// 						nextPage(${page.currentPage});
						findGuige();
					});
			};
		}
		
		function findGuige(){
			var id='${pd.COMMODITY_ID}';
			$.ajax({
				type : "get",
				dataType:"json",
				url : '${pageContext.request.contextPath}/commodity/findGuige.do?id='+id,
				success : function(data){
					var flag=data.flag;
					if(flag==1){
						var listSpe = data.listSpe;
						var mapList=data.mapList;
						 var s=document.getElementById('guige1');
						  var t=s.childNodes.length;
						  for (var i=t-1;i>0;i--){
							  //alert(i);
						    s.removeChild(s.childNodes[i]);
						  }
						 
						//$("#guige1").find("li").remove(); 
						//$("#biaoge").html("");
						for (var i = 0; i<listSpe.length;i++){
							var str="";
							str+="<li>";
							str+=" <p>规格名称："+listSpe[i].NAME+" <a href='#modal-container-467791' data-toggle='modal' class='btn btn-default' style='float: right;border:1px solid #5ba2cf;'  onclick='edit1(\""+listSpe[i].SPECIFICATIONS_ID+"\",1)'><img src='${pageContext.request.contextPath }/static/images/bianji.png' style='width: 16px;margin-right:5px;' >编辑</a></p>";
							str+="  <p>规格选项 :";
							
	// 						alert(mapList);
// 							alert(mapList[listSpe[i].SPECIFICATIONS_ID]);
//<input onchange='findBiaoGe()' name='gg' type='checkbox' value='"+mapList[listSpe[i].SPECIFICATIONS_ID][j].SPECIFICATIONPROPERTIES_ID+"' />
		       				 for(var j=0;j<mapList[listSpe[i].SPECIFICATIONS_ID].length;j++){
		       					str+="<label style='margin-left:10px;'>"+mapList[listSpe[i].SPECIFICATIONS_ID][j].NAME+"</label>"; 
		       					
		       				 }
		       				str+=" <button type='button' class='btn btn-default' style='float: right;border:1px solid #5ba2cf; ' onclick='del1(\""+listSpe[i].SPECIFICATIONS_ID+"\")'><img style='width: 16px;margin-right:5px;' src='${pageContext.request.contextPath }/static/images/shanchu.png'>删除</button></p>";
		       				str+="  </li>";
// 		       				 alert(str);
							$("#guige1").append(str);
							
						}
						
						//alert();
					}
				}
				
			});
		
		}
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
        				$("#CLASSIFICATION_ID").empty(); 
        				var str = '<option value="">二级分类</option>';
    					$("#CLASSIFICATION_ID").append(str);
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
        					$("#CLASSIFICATION_ID").append(str);
        				}else{
        					str+="<option value='暂无'>暂无</option>";
        					$("#CLASSIFICATION_ID").append(str);
        				}
    					
    					
        			}
        		});
    		}
    	}
	function findBiaoGe(){
		var id='${pd.COMMODITY_ID}';
		var pausedCause = '';
		$(":checkbox[name='gg'][checked]").each(function(){
			pausedCause += this.value +',';
		});
		if(pausedCause.length>0){
			pausedCause = pausedCause.substring(0,pausedCause.length-1);
			 $.ajax({
					type : "get",
					dataType:"json",
					url : '${pageContext.request.contextPath}/commodity/findBiaoGe.do?pausedCause='+pausedCause+'&id='+id,
					success : function(data){
						$("#biaoge").html("");
							var flag=data.flag;
							$("#num").val(data.num);
							var listSpe =data.listSpe;
							var list=data.list;
// 							alert(listSpe);
							var str='';
							$("#biaoge").html("");
							if(listSpe.length>0){
								str+="<thead><tr>";
								for (var i = 0; i<listSpe.length;i++){
// 									var str='';
									if(listSpe[i].count>0){
										str+="<th>"+listSpe[i].NAME+"</th>";
// 										$("#biaoge").append(str);
									}
								}
								str+="<th>库存</th><th>销售价格</th>";
								str+="</tr></thead><tbody>";
								for(var j=0;j<list.length;j++){
									str+="<tr>";
// 									var str='';
									var listg=list[j];
									var pausedCause = '';
								    for(var g=0;g<listg.length;g++){
								    	pausedCause += listg[g].SPECIFICATIONPROPERTIES_ID +';';								    	
										str+="<td>"+listg[g].NAME+"</td>";
								    }
								    if(pausedCause.length>0){
										 pausedCause = pausedCause.substring(0,pausedCause.length-1);
								    }
								    str+="<input id='proper"+j+"' name='proper"+j+"' type='hidden' value='"+pausedCause+"'  >";
								    str+="<td><input id='kucun"+j+"' name='kucun"+j+"' type='text' value='' min='1' max='999999' style='position:relative;top:5px;' ></td>";
								    str+="<td><input id='jg"+j+"' name='jg"+j+"' type='text' value='' style='position:relative;top:5px;' ></td>";
								    str+="</tr>";
								}
								str+="</tbody>";
										$("#biaoge").html(str);
							}
						}
				});
		}
	}
		
		
		
		
		
		</script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
		<script>
		 //图片上传预览    IE是用了滤镜。
        		function previewImage(obj){
        			var img_div = $("#dimg");
        			var filepath = $("input[name='detailimgFile']").val();
        			for(var i = 0; i < obj.files.length; i++) {
        				var extStart = filepath.lastIndexOf(".");
        				var ext = filepath.substring(extStart, filepath.length).toUpperCase();
        				/*
        				描述：鉴定每个图片上传尾椎限制
        				* */
        				if(ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
        					$(".shade").fadeIn(500);
        					alert("图片限于bmp,png,gif,jpeg,jpg格式");
        					this.value = "";
        					//$(".img_div").html("");
        					return false;
        				} else {
        					$("#dimg").html("");
       				        	$.ajaxFileUpload({
       				            	url:"${pageContext.servletContext.contextPath}/commodityDetailImg/savedetailimg.do",
       				            	secureuri:false,                       //是否启用安全提交,默认为false
       				            	dataType:"json", 
       				            	fileElementId:"previewImg",//文件选择框的id属性
       				            	success:function(data){   
       				            	var msg=data.msg;
       				            	//alert(data.msg);
       				            		var varOList=data.varOList;
       				            		if(data.flag == 0){ 
       				                		alert("上传失败");
       				                	}else{
       				                		for(var i = 0; i < varOList.length; i++) {
       				                			if((varOList[i].IMGURL).substring(0,3)=='gzh'){
       				                				img_html = "<div class='isImg' id='preview'><a href='<%=OSSUrl %>"+varOList[i].IMGURL + "'><img id='imghead' border='0' src='<%=OSSUrl1 %>"+varOList[i].IMGURL + "' width='100%' height='100%'/></a>f<a class='removeBtn' onclick='removedelImg(this,\""+varOList[i].ORDERCOMMODITYIMG_ID+"\",\""+varOList[i].IMGURL+"\")'>x</a></div>";
       				                			}else{
       				                				img_html = "<div class='isImg' id='preview'><a href='<%=OSSUrl %>"+varOList[i].IMGURL + "'><img id='imghead' border='0' src='<%=OSSUrl %>"+varOList[i].IMGURL + "' width='100%' height='100%'/></a>f<a class='removeBtn' onclick='removedelImg(this,\""+varOList[i].ORDERCOMMODITYIMG_ID+"\",\""+varOList[i].IMGURL+"\")'>x</a></div>";
       				                			}
       				                			
       						   					img_div.append(img_html);
       				                		}
       				                	 $(".isImg img").click(function(){
       				                      $(".tanchuang").show();
       				                      $("body").css("overflow","hidden");
       				                    });
       				                    $(".xianshi .guanbi").click(function(){
       				                      $(".tanchuang").hide();
       				                       $("body").css("overflow","scroll");
       				                    });
       				             
       				            
       				                	}
       				            	}
       				        	});
       				       }
        			}
        }
		 
      //图片上传预览    IE是用了滤镜。
        function previewImage1(file){
        	
		        	$.ajaxFileUpload({
		            	url:"${pageContext.servletContext.contextPath}/businessuser/uploadBPhoto2.do",
		            	secureuri:false,                       //是否启用安全提交,默认为false
		            	dataType:"json", 
		            	fileElementId:"previewImg",//文件选择框的id属性
		            	success:function(data){   
		            	var msg=data.msg;
		            	//alert(data.msg);
		            		
		            		if(data.flag == 0){ 
		                		alert("上传失败");
		                	}else if(data.flag == 1){ 
		                		alert("请选择文件后上传");
		                	}else if(data.flag == 2){ 
		                		alert("上传文件格式不正确");
		                	}else{
	                			$("#DETAIL").val(data.msg);
	                			if(msg.substring(0,3)=='gzh'){
	                				$("#imghead").attr("src", "<%=OSSUrl1 %>"+msg+"?r="+Date.parse(new Date()));
	                			}else{
	                				$("#imghead").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
	                			}
	                			
		                	}
		            	}
		        	});
		       
        	  
        	  
    	  
         <%-- var MAXWIDTH  = 90; 
          var MAXHEIGHT = 90;
          var div = document.getElementById('preview');
          
          if (file.files && file.files[0]){
              div.innerHTML ='<img id=imghead onclick=$("#previewImg").click()>';
              var img = document.getElementById('imghead');
              img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){img.src = evt.target.result;}
              reader.readAsDataURL(file.files[0]);
          }else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }--%>
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight ){
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                
                if( rateWidth > rateHeight ){
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else{
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
    </script>
<script type="text/javascript">

	$(function() {
		var objUrl;
		var img_html;
		//$("#myFile").change(function() {
		//});
		/*
		描述：鉴定每个浏览器上传图片url 目前没有合并到Ie
		 * */
		function getObjectURL(file) {
			var url = null;
			if(window.createObjectURL != undefined) { // basic
				url = window.createObjectURL(file);
			} else if(window.URL != undefined) { // mozilla(firefox)
				url = window.URL.createObjectURL(file);
			} else if(window.webkitURL != undefined) { // webkit or chrome
				url = window.webkitURL.createObjectURL(file);
			}
			//console.log(url);
			return url;
		}
	});
	function uploadFile(obj){
		var img_div = $(".img_div");
		var filepath = $("input[name='myFile']").val();
		for(var i = 0; i < obj.files.length; i++) {
			//objUrl = getObjectURL(obj.files[i]);
			var extStart = filepath.lastIndexOf(".");
			var ext = filepath.substring(extStart, filepath.length).toUpperCase();
			/*
			描述：鉴定每个图片上传尾椎限制
			* */
			if(ext != ".BMP" && ext != ".PNG" && ext != ".GIF" && ext != ".JPG" && ext != ".JPEG") {
				$(".shade").fadeIn(500);
				$(".text_span").text("图片限于bmp,png,gif,jpeg,jpg格式");
				this.value = "";
				$(".img_div").html("");
				return false;
			} else {
				$(".img_div").html("");
			        	$.ajaxFileUpload({
			            	url:"${pageContext.servletContext.contextPath}/pictures/save.do",
			            	secureuri:false,                       //是否启用安全提交,默认为false
			            	dataType:"json", 
			            	fileElementId:"myFile",//文件选择框的id属性
			            	success:function(data){   
			            	var msg=data.msg;
			            	//alert(data.msg);
			            		var varOList=data.varOList;
			            		if(data.flag == 0){ 
			                		alert("上传失败");
			                	}else{
			                		for(var i = 0; i < varOList.length; i++) {
			                			if((varOList[i].IMGURL).substring(0,3)=='gzh'){
			                				img_html = "<div class='isImg'><img src='<%=OSSUrl1 %>"+varOList[i].IMGURL + "' onclick='javascript:lookBigImg(this)' style='height: 100%; width: 100%;' /><a class='removeBtn' onclick='removeImg(this,\""+varOList[i].COMMODITYIMG_ID+"\",\""+varOList[i].IMGURL+"\")'>x</a></div>";
			                			}else{
			                				img_html = "<div class='isImg'><img src='<%=OSSUrl %>"+varOList[i].IMGURL + "' onclick='javascript:lookBigImg(this)' style='height: 100%; width: 100%;' /><a class='removeBtn' onclick='removeImg(this,\""+varOList[i].COMMODITYIMG_ID+"\",\""+varOList[i].IMGURL+"\")'>x</a></div>";
			                			}
			                			
					   					img_div.append(img_html);
			                		}
			                		 $(".isImg img").click(function(){
      				                      $(".tanchuang").show();
      				                      $("body").css("overflow","hidden");
      				                    });
      				                    $(".xianshi .guanbi").click(function(){
      				                      $(".tanchuang").hide();
      				                       $("body").css("overflow","scroll");
      				                    });
      				               
			                		/*
			   					 若规则全部通过则在此提交url到后台数据库
			   					 * */
			   					 
			   					
			                			//$("#DETAIL").val(data.msg);
			                			//$("#shareImg").attr("src", "<%=OSSUrl %>"+msg+"?r="+Date.parse(new Date()));
			                		
			                	}
			            		
			            	}
			        	});
			       }
				
			
		}
		/*
		描述：鉴定每个图片大小总和
		* */
		var file_size = 0;
		var all_size = 0;
		for(j = 0; j < obj.files.length; j++) {
			file_size = obj.files[j].size;
			all_size = all_size + obj.files[j].size;
			var size = all_size / 1024;
			if(size > 150000) {
				$(".shade").fadeIn(1000);
				$(".text_span").text("上传的图片太大！");
				this.value = "";
				$(".img_div").html("");
				return false;
			}
		}
		/*
		描述：鉴定每个图片宽高 以后会做优化 多个图片的宽高 暂时隐藏掉 想看效果可以取消注释就行
		* */
		//					var img = new Image();
		//					img.src = objUrl;
		//					img.onload = function() {
		//						if (img.width > 100 && img.height > 100) {
		//							alert("图片宽高不能大于一百");
		//							$("#myFile").val("");
		//							$(".img_div").html("");
		//							return false;
		//						}
		//					}
		return true;
		}
	/*
	  描述：上传图片附带删除 再次地方可以加上一个ajax进行提交到后台进行删除
	 * */
	function removeImg(r,id,path){
 			$.ajax({
					type : "post",
					dataType:"json",
					data:{
						"COMMODITYIMG_ID":id,
						"path":path
					},
					url : '${pageContext.request.contextPath}/commodityimg/delete.do',
					success : function(data){
						$(r).parent().remove();
					}
				});
	
		
	}
	function removedelImg(r,id,path){
			$.ajax({
				type : "post",
				dataType:"json",
				data:{
					"ORDERCOMMODITYIMG_ID":id,
					"path":path
				},
				url : '${pageContext.request.contextPath}/commodityDetailImg/delete.do',
				success : function(data){
					$(r).parent().remove();
				}
			});

	
}
	/*
	  描述：上传图片附带放大查看处理
	 * */
	function lookBigImg(b){
		$(".shadeImg").fadeIn(500);
		$(".showImg").attr("src",$(b).attr("src"))
	}
	/*
	  描述：关闭弹出层
	 * */
	function closeShade(){
		$(".shade").fadeOut(500);
	}
	/*
	  描述：关闭弹出层
	 * */
	function closeShadeImg(){
		$(".shadeImg").fadeOut(500);
	}
</script>
    <script type="text/javascript">

var lablId = -1;



$(function() {

    $("#kk").blur(function() {

        if (isNan(this.value) != false) {

            this.value = '用空格分隔多个属性';

            this.style.color = '#999';

        }

    });

});

$(document).ready(function() {

    $("#kk").keydown(function(event) {

        if (event.keyCode == 32) {

            var str = $("#kk").val();

            if (isNan(str) != true) {

                var li_id = $(".label li:last-child").attr('id');

                if (li_id != undefined) {

                    li_id = li_id.split('_');

                    li_id = parseInt(li_id[1]) + 1;

                } else {

                    li_id = 0;

                }

                $(".label_box").css("display", "block");

                var text = "<li id='li_" + li_id + "'><a href='javascript:;' onclick='deletes(" + li_id + ");' >" + str + "<img src='images/label_03.png' class='label-pic'></a><input type='hidden' name='label[" + li_id + "].name' value='" + str + "'></li>";

                $(".label").append(text);

            }

            $("#kk").val("");

        }

    })

});

function isNan(obj) {

    try {

        return obj == 0 ? true: !obj

    } catch(e) {

        return true;

    }

}





function deletes(id) {

    $("#li_" + id).remove();

    var li_id = $(".label li:last-child").attr('id');

    if (li_id == undefined) {

        $(".label_box").css("display", "none");

    }

}



function addlabl(id) {

    if (lablId == id) {

        return;

    }

    lablId = id;

    var str = $("#add_" + id).text();

    var li_id = $(".label li:last-child").attr('id');

    if (li_id != undefined) {

        li_id = li_id.split('_');

        li_id = parseInt(li_id[1]) + 1;

    } else {

        li_id = 0;

    }

    $(".label_box").css("display", "block");

    var text = "<li id='li_" + li_id + "'><a href='javascript:;' onclick='deletes(" + li_id + ");' >" + str + "<img src='images/label_03.png' class='label-pic'></a><input type='hidden' name='label[" + li_id + "].name' value='" + str + "'></li>";

    $(".label").append(text);

}

</script>
<script>

</script>
</body>
</html>