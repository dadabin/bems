$(document).ready(function(){
	
	$("#url_table_div").tabs();
	
	
	$("#role_url_div").hide();
	
	//初始化页面加载函数，初始化checkbox中的数据
	
	function myonload(){
		if($("#class1_check").is(":checked")){
			$(".class1").each(function(){
				 $(this).removeAttr("disabled"); 
				
				});
			
		}else{
			$(".class1").each(function(){
				$(this).attr("disabled","disabled");
				 $(this).removeAttr("checked");
				});
		}
		
		if($("#class2_check").is(":checked")){
			$(".class2").each(function(){
				 $(this).removeAttr("disabled"); 
				 
				});
		}else{
			$(".class2").each(function(){
				$(this).attr("disabled","disabled");
				$(this).removeAttr("checked");
				});
		}
		
		if($("#class3_check").is(":checked")){
			$(".class3").each(function(){
				 $(this).removeAttr("disabled"); 
				});
		}else{
			$(".class3").each(function(){
				$(this).attr("disabled","disabled");
				$(this).removeAttr("checked");
				});
		}
		if($("#class4_check").is(":checked")){
			$(".class4").each(function(){
				 $(this).removeAttr("disabled"); 
				});
		}else{
			$(".class4").each(function(){
				$(this).attr("disabled","disabled");
				$(this).removeAttr("checked");
				});
		}
		if($("#class5_check").is(":checked")){
			$(".class5").each(function(){
				 $(this).removeAttr("disabled"); 
				
				});
		}else{
			$(".class5").each(function(){
				$(this).attr("disabled","disabled");
				 $(this).removeAttr("checked");
				
				});
		}
	}
	
	
	//绑定角色权限管理事件
	$("#system_administrator_a").click(function(){
		$("#role_url_hidden").attr("value","1");//设置角色
		$.ajax({
    		type:"GET",
    		url:$.getRootPath()+"/rest/urlService/geturlbyroleid/"+1,
    		success:function(msg){
    			$xml=$(msg);
    			var urls=$xml.find("urls").text().split(",");;
    			//alert(urls[0]);
    		 for(var i=0;i<urls.length;i++){
    				$("input[type=checkbox]").each(function(){
        				if($(this).attr("value")==urls[i]){
        					$(this).attr("checked",true);
        					
        				}
        			});
    		 }
    		 myonload();
    		}
    	});
		$("#url_role_management_div").hide();
		$("#role_url_div").slideDown(500);
		

	});
	
	$("#middle_level_management_system_a").click(function(){
		$("#role_url_hidden").attr("value","2");//设置角色
		$.ajax({
    		type:"GET",
    		url:$.getRootPath()+"/rest/urlService/geturlbyroleid/"+2,
    		success:function(msg){
    			$xml=$(msg);
    			var urls=$xml.find("urls").text().split(",");;
    			//alert(urls[0]);
    		 for(var i=0;i<urls.length;i++){
    				$("input[type=checkbox]").each(function(){
        				if($(this).attr("value")==urls[i]){
        					$(this).attr("checked",true);
        					
        				}
        			});
    		 }
    		 myonload();
    		}
    	});
		$("#url_role_management_div").hide();
		$("#role_url_div").slideDown(500);
	});
	
	$("#general_staff_a").click(function(){
		$("#role_url_hidden").attr("value","3");//设置角色
	
		
		$.ajax({
    		type:"GET",
    		url:$.getRootPath()+"/rest/urlService/geturlbyroleid/"+3,
    		success:function(msg){
    			$xml=$(msg);
    			var urls=$xml.find("urls").text().split(",");;
    			//alert(urls[0]);
    		 for(var i=0;i<urls.length;i++){
    				$("input[type=checkbox]").each(function(){
        				if($(this).attr("value")==urls[i]){
        					$(this).attr("checked",true);
        					
        				}
        			});
    		 }
    		 myonload();
    		}
    	});
		
		
		$("#url_role_management_div").hide();
		$("#role_url_div").slideDown(500);
		
	});
	

	//绑定主菜单的选择事件

	$("#class1_check").click(function(){
		if($(this).attr("checked")=="checked"){
			$(".class1").each(function(){
				 $(this).removeAttr("disabled"); 
				  $(this).attr("checked", true);
				});	
		}else{
			
			$(".class1").each(function(){
				$(this).attr("disabled","disabled");
				  $(this).attr("checked", false);
				});	
		}
		myonload();
	});
	
	$("#class2_check").click(function(){
		if($(this).attr("checked")=="checked"){
			$(".class2").each(function(){
				 $(this).removeAttr("disabled"); 
				  $(this).attr("checked", true);
				});	
		}else{
			
			$(".class2").each(function(){
				$(this).attr("disabled","disabled");
				  $(this).attr("checked", false);
				});	
		}
		myonload();
	});
	
	$("#class3_check").click(function(){
		if($(this).attr("checked")=="checked"){
			$(".class3").each(function(){
				 $(this).removeAttr("disabled"); 
				  $(this).attr("checked", true);
				});	
		}else{
			
			$(".class3").each(function(){
				$(this).attr("disabled","disabled");
				  $(this).attr("checked", false);
				});	
		}
		myonload();
	});
	
	$("#class4_check").click(function(){
		if($(this).attr("checked")=="checked"){
			$(".class4").each(function(){
				 $(this).removeAttr("disabled"); 
				  $(this).attr("checked", true);
				});	
		}else{
			
			$(".class4").each(function(){
				$(this).attr("disabled","disabled");
				  $(this).attr("checked", false);
				});	
		}
		myonload();
	});
	
	$("#class5_check").click(function(){
		if($(this).attr("checked")=="checked"){
			$(".class5").each(function(){
				 $(this).removeAttr("disabled"); 
				  $(this).attr("checked", true);
				});	
		}else{
			
			$(".class5").each(function(){
				$(this).attr("disabled","disabled");
				  $(this).attr("checked", false);
				});	
		}
		myonload();
	});
	//执行数据加载函数
	myonload();
	//取出选中的checkbox的value
	$("#role_url_button").click(function(){
		var result=new Array();
		$("input[type=checkbox]").each(function(){
			if($(this).is(":checked")){
				result.push($(this).attr("value"));
			}
		});
		//alert($.getRootPath());
		
		var d="urls="+ result.toString()+"&"+"roleid="+$("#role_url_hidden").attr("value");
		
		//ajax请求
		$.ajax({
    		type:"POST",
    		url:$.getRootPath()+"/rest/urlService/addurlrole",
    		data:d,
    		success:function(msg){
    			$xml=$(msg);
    			alert($xml.find("message").text());
    		}
    	});
	});
});








