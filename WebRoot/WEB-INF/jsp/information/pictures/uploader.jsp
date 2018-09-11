<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
String TEL=(String)request.getSession().getAttribute("TEL");
	String  IMGURL=(String) request.getSession().getAttribute("IMGURL");
	String path = request.getContextPath();
	String OSSUrl = application.getInitParameter("OSSUrl");
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/webuploader.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/webuploader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/upload.js"></script>
<style type="text/css">

#container{overflow-y:scroll;} 

</style>
</head>
<body>
     <div id="container">
     
     <input type="hidden" value="<%=OSSUrl %>" id="ossder" />
            <!--头部，相册选择和格式选择 -->
            <div id="uploader"  >
                <div class="queueList">
                    <div id="dndArea" class="placeholder">
                        <div id="filePicker"></div>
                        <p>或将照片拖到这里，单次最多可选300张</p>
                    </div>
                </div>
                <div class="statusBar" style="display:none;" >
                    <div class="progress">
                        <span class="text">0%</span>
                        <span class="percentage"></span>
                    </div><div class="info"></div>
                    <div class="btns">
                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
                    </div>
                </div>
            </div>
      </div>
</body>
</html>