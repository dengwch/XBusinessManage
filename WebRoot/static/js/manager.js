function callAPI(purl,param,callback,async){
	if(undefined == async)
		async = false;
	var _url =  purl; 
	$.ajax({
		type: "post",
		url: _url,
		async : async,
		dataType: 'json',
		timeout : 3000 ,
		data:param,
		beforeSend: function(XMLHttpRequest){},
		success: function(data, textStatus){
			if(callback){
				callback(data);
			}
		},	
		complete: function(XMLHttpRequest, textStatus){
		},
		error: function(){
		}
	});
	
	
}
function StrNull(str){
	if(str==undefined){
		return "";
	}else{
		return str;
	}
}