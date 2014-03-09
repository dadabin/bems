$("table tbody tr:odd").css("background-color","#dcdcde");

$(function(){
	var url = $.getRootPath()+"/rest/floorService";
	$.ajax({
	        type: "get",
	        dataType: "xml",
	        url: url,
			timeout: 1000,
	        success: function(msg){
	          buildSelect(msg);
	        },
			error:function(){
				alert("获取数据失败！");
			}
	    });
	function buildSelect(msg){
		$(msg).find("floor").each(function(i){
			var id_value=$(this).children("floorNum").text(); //取文本
			$("#floorNo").append("<option value="+id_value+">"+id_value+"</option>");
			
		});
	}
	//=================表单提交===================//
	 $('#floor_basic_form').ajaxForm(function() {
         // 提交表单
	   	 	$(this).ajaxSubmit();
			
	    	// 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
	    	return false;	 
         });
	 
	 $("#floor_basic_data_submitBtn").click(function(){
		 
		 
		 //提示上传成功！
		 alert("上传成功啦！");
		//重置表单
		 document.forms[ "floor_basic_form "].reset();
	 });
	 
});