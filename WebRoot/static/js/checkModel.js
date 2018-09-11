function checkTupian(fileId,fileName){
	if (fileId!="") {
		var uploadFileNames = [];
		uploadFileNames = fileId.split(".");
		if (uploadFileNames.length>1&&uploadFileNames[uploadFileNames.length-1]!="jpg"&&uploadFileNames[uploadFileNames.length-1]!="JPG"&&uploadFileNames[uploadFileNames.length-1]!="png"&&uploadFileNames[uploadFileNames.length-1]!="PNG"&&uploadFileNames[uploadFileNames.length-1]!="gif"&&uploadFileNames[uploadFileNames.length-1]!="GIF") {
			alert("友情提示,您"+fileName+"所选择的文件类型不是jpg,png,gif格式的！！！");
			return true;
		} 
	} 
			return false;
}

function checkSize(id){
	var img=document.getElementById(id).files[0];
	if(img!=null ){
		var imagSize =  img.size;
		if(imagSize>1024*1024*3){
			alert("图片不能大于3M");
			return true;
		}
	}
	return false;
}

//校验手机号
function isPhoneNo(phone){
	var myreg = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/; 
	if(!myreg.test(phone)) 
	{ 
	    return false; 
	}else{
		return true;
	}
}
// 验证身份证 
function isCardNo(card) { 
 var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
 return pattern.test(card); 
} 