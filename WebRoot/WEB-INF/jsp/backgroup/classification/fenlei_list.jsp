<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%
String TEL=(String)request.getSession().getAttribute("TEL");
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
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/static/css/bootstrap.min.css">
	<link href="${pageContext.request.contextPath }/static/css/jquery.treemenu.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/static/css/index.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/static/css/basicStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/static/css/NavigationStyle.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath }/static/css/ListStyle.css" rel="stylesheet" type="text/css">
	<style>
*{list-style:none;border:none;}
.tree {  color:#5ba2cf ;width:800px;}
.tree li,
.tree li > a,
.tree li > span {
    padding: 4pt;
    border-radius: 4px;
}
p{
	margin-bottom: 0;
}
.tree li a {
    color:#5ba2cf;
    text-decoration: none;
    line-height: 20pt;
    border-radius: 4px;
}

.tree li a:hover {
    background-color: #fff;
    color: #5ba2cf;
    text-decoration: none;
}

.active {
    background-color: #fff;
    color: #5ba2cf;
}

.active a {
    color: #5ba2cf;
}
.tree li ul{
	margin-left:20px;
}
.tree li a.active:hover {
    background-color: #fff;
}
</style>
<style type="text/css">

* {
 padding:0;
 margin:0;
}
#upcontent {
 list-style-position: outside;
 list-style-image: none;
 list-style-type: none;
}
#upcontent li {
 font-size:12px;
 color:#333;
 line-height:150%;
}
#bodyL {
 float:left;
 width:84px;
 margin-right:2px;
}
#tittleup {
 font-size:14px;
 font-weight:bold;
 color:#000066;
 padding-left:25px;
 border-bottom-width: 1px;
 border-bottom-style: solid;
 border-bottom-color: #d0daec;
 margin-bottom: 10px;
 padding-bottom: 10px;
}
a.od {
 float:right;
 font-size:14px;
 color: #CC0000;
 text-decoration: none;
}
a.od:hover {
 color:#FF0000;
}
.tree-opened{
font-size:16px!important;
font-weight:600;}
.tree-closed{
font-size:16px!important;
font-weight:600;}
.tree-closed .treemenu{
font-size:14px!important;
font-weight:100;}
.tree-opened .treemenu{
font-size:14px!important;
font-weight:100;}
#fd {
 background:#EDF1F8;
 border: 2px solid #849BCA;
 margin-top:2px;
 margin-left:2px;
 float:left;
 overflow:hidden;
 position:absolute;
 left:432px;
 top:212px;
 cursor:move;
 float:left;/*filter:alpha(opacity=50);*/
 z-index: 10;
}
.contentup {
 padding:20px;
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

   <div class="container" style="background: #eee;">
        <h3 style="font-size: 20px;color: #5ba2cf; ">分类管理</h3>
		<div class="row clearfix">
			<div class="col-md-12 column" style="padding: 20px 20%;min-height: 710px;">
			<form action="classification/save.do" name="Form" id="Form" method="post">
			<a id="modal-467791" href="#modal-container-467792" role="button" class="btn btn-primary" data-toggle="modal" style="float:right;margin-right:-30px;">新增分类</a>
			
				<div class="modal fade" id="modal-container-467792" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
								<h4 class="modal-title" id="myModalLabel">
									新增分类
								</h4>
							</div>
							<div class="modal-body">
							    <input style="width: 100px;text-align: center;" type="radio"  name="optionsRadios" id="optionsRadios1" value="option1" checked>一级分类
							    <input style="width: 100px;text-align: center;margin-bottom: 30px;"  type="radio" name="optionsRadios" id="optionsRadios2" value="option2">二级分类
							    <p class="one" style="padding-left:40px; ">一级分类：
								    <select name="SUPERIOR" id="SUPERIOR" class="form-control "  style="width: 173px;">
								     <c:forEach items="${list }" var="classfication">
											<option value="${classfication.CLASSIFICATION_ID }" <c:if test="${classfication.CLASSIFICATION_ID==pd.CLASSIFICATION_ID }">selected</c:if>>
												${classfication.NAME}
											</option>
									</c:forEach>
								    </select>
							    </p>
								<p style="padding-left:40px; ">分类名称：<input type="text" name="NAME" id="NAME"></p>
							</div>
							<div class="modal-footer">
								 <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button type="button" class="btn btn-primary" onclick="save();">保存</button>
							</div>
						</div>				
					</div>
				</div>
				
				</form>
				
			<ul class="tree" style="margin-top:30px;">
			<c:forEach items="${jsonList}" var="var" varStatus="vs">
			  <li><a>${var.yijiname}</a><a style="cursor: pointer; color: red;" onclick="delfenlei('${var.CLASSIFICATION_IDYI }');">删除</a><a onclick = "show('fd',event,'${var.CLASSIFICATION_IDYI }');return false;">修改</a>
			    <ul>
			    <c:forEach items="${var.erjinamelist}" var="var1" varStatus="vs">
			      <li><a>${var1.name}</a><a style="cursor: pointer; color: red;" onclick="delfenlei('${var1.id}');">删除</a><a onclick = "show('fd',event,'${var1.id}');return false;">修改</a></li>
			    </c:forEach>
			    </ul>
			  </li>
			 </c:forEach>
			</ul>
			<c:if test="${jsonList==null || fn:length(jsonList)<=0}"><p style="width:100%;text-align:center;">暂无分类</p></c:if>
			
			</div>

		</div>
    </div>
    <div class="foot" style="margin-top: 50px;width: 100%;height: 80px;background: #fff;text-align: center;line-height: 60px;border-top: 5px solid rgb(90, 163, 208);color: #ccc;">
    Copyright2015|xxxx Cc.Allright erserved 津ICP备13005847号-2
</div>

<div id="bodyL"></div>
<div id="fd" style="display:none;filter:alpha(opacity=100);opacity:1;">
  <div class="contentup"> <a href="#" class="od" onclick = "closeed('fd');return false;"> 关 闭 </a>
    <div id="tittleup">修改分类</div>
    <input id="FLID" type="hidden"/>
    <ul id="upcontent">
      <li><p style="padding-left:40px; ">分类名称：<input type="text" name="FGNAME" id="FGNAME"></p></li>
    </ul>
    <div style="margin-top:100px;" align="center">
								 <button type="button" style="margin-right:10px;" class="btn btn-default" onclick = "closeed('fd');return false;">关闭</button> <button type="button" class="btn btn-primary" onclick="saveFL();">保存</button>
							</div>
  </div>
</div>


</body>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/static/js/jquery.treemenu.js"></script>
<script type="text/javascript">
    var prox;
    var proy;
    var proxc;
    var proyc;
 var isIe=(document.all)?true:false;
 function setSelectState(state)
{
var objl=document.getElementsByTagName('select');
for(var i=0;i<objl.length;i++)
{
objl[i].style.visibility=state;
}
}
function mousePosition(ev)
{
if(ev.pageX || ev.pageY)
{
return {x:ev.pageX, y:ev.pageY};
}
return {
x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,y:ev.clientY + document.body.scrollTop - document.body.clientTop
};
}
//关闭窗口
function closeWindow()
{
if(document.getElementById('back')!=null)
{
document.getElementById('back').parentNode.removeChild(document.getElementById('back'));
}
if(document.getElementById('mesWindow')!=null)
{
document.getElementById('mesWindow').parentNode.removeChild(document.getElementById('mesWindow'));
}
if(isIe){
setSelectState('');}
}
    function show(id,ev,flid){/*--打开--*/
    	
    	
    	$.ajax({
    		type : "post",
    		dataType: "json",
    		data : {
    			"CLASSIFICATION_ID":flid
    		},
    		url : "${pageContext.request.contextPath }/classification/findFenLei.do",
    		success : function(data) {
    			$("#FGNAME").val(data.listClassT.NAME);
    			$("#FLID").val(flid);
    			 closeWindow();
    			 var bWidth=parseInt(document.documentElement.scrollWidth);
    			 var bHeight=parseInt(document.documentElement.scrollHeight)<592?592:parseInt(document.documentElement.scrollHeight);
    			 if(isIe){
    			 setSelectState('hidden');}
    			 var back=document.createElement("div");
    			 back.id="back";
    			 var styleStr="top:0px;left:0px;position:absolute;background:#666;width:"+bWidth+"px;height:"+bHeight+"px;";
    			 styleStr+=(isIe)?"filter:alpha(opacity=40);":"opacity:0.40;";
    			 back.style.cssText=styleStr;
    			 document.body.appendChild(back);
    			 
    			        clearInterval(prox);
    			        clearInterval(proy);
    			        clearInterval(proxc);
    			        clearInterval(proyc);
    			        var o = document.getElementById(id);
    			        o.style.display = "block";
    			        o.style.width = "1px";
    			        o.style.height = "1px"; 
    			        prox = setInterval(function(){openx(o,500)},10);
    		}
    	});
    	

  
}    
    function openx(o,x){/*--打开x--*/
        var cx = parseInt(o.style.width);
        if(cx < x)
        {
            o.style.width = (cx + Math.ceil((x-cx)/5)) +"px";
        }
        else
        {
            clearInterval(prox);
            proy = setInterval(function(){openy(o,300)},10);
        }
    }    
    function openy(o,y){/*--打开y--*/    
        var cy = parseInt(o.style.height);
        if(cy < y)
        {
            o.style.height = (cy + Math.ceil((y-cy)/5)) +"px";
        }
        else
        {
            clearInterval(proy);            
        }
    }    
    function closeed(id){/*--关闭--*/
 closeWindow();
        clearInterval(prox);
        clearInterval(proy);
        clearInterval(proxc);
        clearInterval(proyc);        
        var o = document.getElementById(id);
        if(o.style.display == "block")
        {
            proyc = setInterval(function(){closey(o)},10);            
        }        
    }    
    function closey(o){/*--打开y--*/    
        var cy = parseInt(o.style.height);
        if(cy > 0)
        {
            o.style.height = (cy - Math.ceil(cy/5)) +"px";
        }
        else
        {
            clearInterval(proyc);                
            proxc = setInterval(function(){closex(o)},10);
        }
    }    
    function closex(o){/*--打开x--*/
        var cx = parseInt(o.style.width);
        if(cx > 0)
        {
            o.style.width = (cx - Math.ceil(cx/5)) +"px";
        }
        else
        {
            clearInterval(proxc);
            o.style.display = "none";
        }
    }    
    
    
    /*-------------------------鼠标拖动---------------------*/    
    var od = document.getElementById("fd");    
    var dx,dy,mx,my,mouseD;
    var odrag;
    var isIE = document.all ? true : false;
    document.onmousedown = function(e){
        var e = e ? e : event;
        if(e.button == (document.all ? 1 : 0))
        {
            mouseD = true;            
        }
    }
    document.onmouseup = function(){
        mouseD = false;
        odrag = "";
        if(isIE)
        {
            od.releaseCapture();
            od.filters.alpha.opacity = 100;
        }
        else
        {
            window.releaseEvents(od.MOUSEMOVE);
            od.style.opacity = 1;
        }        
    }
    
    
    //function readyMove(e){    
    od.onmousedown = function(e){
        odrag = this;
        var e = e ? e : event;
        if(e.button == (document.all ? 1 : 0))
        {
            mx = e.clientX;
            my = e.clientY;
            od.style.left = od.offsetLeft + "px";
            od.style.top = od.offsetTop + "px";
            if(isIE)
            {
                od.setCapture();                
                od.filters.alpha.opacity = 50;
            }
            else
            {
                window.captureEvents(Event.MOUSEMOVE);
                od.style.opacity = 0.5;
            }
            
            //alert(mx);
            //alert(my);
            
        } 
    }
    document.onmousemove = function(e){
        var e = e ? e : event;
        
        //alert(mrx);
        //alert(e.button);        
        if(mouseD==true && odrag)
        {        
            var mrx = e.clientX - mx;
            var mry = e.clientY - my;    
            od.style.left = parseInt(od.style.left) +mrx + "px";
            od.style.top = parseInt(od.style.top) + mry + "px";            
            mx = e.clientX;
            my = e.clientY;
            
        }
    }
function showBackground(obj,endInt)
{
obj.filters.alpha.opacity+=1;
if(obj.filters.alpha.opacity<endInt)
{
setTimeout(function(){showBackground(obj,endInt)},8);
}
}
</script>

<script>
$(function(){
        $(".tree").treemenu({delay:300}).openActive();
    });
$(document).ready(function(){
  $(".one").hide();
  $("#optionsRadios2").click(function(){
    $(".one").slideDown("slow");
  });
  $("#optionsRadios1").click(function(){
    $(".one").slideUp("slow");
  });
});

function xg(id){
	$.ajax({
		type : "post",
		dataType: "json",
		data : {
			"CLASSIFICATION_ID":id
		},
		url : "${pageContext.request.contextPath }/classification/findFenLei.do",
		success : function(data) {
			$("#FGNAME").val(data.listClassT.NAME);
			$("#FLID").val(id);
			
		}
	});
	
}
function xg(id){
	$.ajax({
		type : "post",
		dataType: "json",
		data : {
			"CLASSIFICATION_ID":id
		},
		url : "${pageContext.request.contextPath }/classification/findFenLei.do",
		success : function(data) {
			$("#FGNAME").val(data.listClassT.NAME);
			$("#FLID").val(id);
			$("#FLDIV").show();
			
			
		}
	});
	
}

function saveFL(){
	var FGNAME= $("#FGNAME").val();
	var CLASSIFICATION_ID= $("#FLID").val();
		window.location.href= "${pageContext.request.contextPath }/classification/edit.do?CLASSIFICATION_ID="+CLASSIFICATION_ID+"&FGNAME="+FGNAME;
}


//保存
function save(){
	if($("#SUPERIOR").val()==""){
	      alert("请输入上级ID");
	}else
	if($("#NAME").val()==""){
		alert("请输入分类名称");
	}else{
		$("#Form").submit();
	}
	
}
</script>
<script>
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
		
		function delfenlei(Id,CLASSIFICATION_IDYI){
			if(confirm("是否确认删除,删除之后所属分类商品也会全部删除")==true){
				window.location.href= "${pageContext.request.contextPath }/commodity/deletesu.do?CLASSIFICATION_ID="+Id+"&CLASSIFICATION_IDYI="+CLASSIFICATION_IDYI;
			}
		}
		
		//修改
		function edit(Id){
			 //top.jzts();
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

