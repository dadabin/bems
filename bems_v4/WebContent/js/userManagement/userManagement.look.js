$(document).ready(function(){
 	userManagermentbind();
	$("table tr:odd").css("background-color","#dcdcde");
	function userManagermentbind(){
		//sun 2012-7-19
		//加载表单 （加载成员列表）
	
	    $.ajax({
	        type: "GET",
	        url: $.getRootPath() + "/rest/staffservice/getstaffs",
	        success: function(xml){
	        	var $xml=null;
	             $xml= $(xml);
	            var meters = $xml.find("staff");
	            meters.each(function(i){
	                var $this = $(this);
	                var staffId=$this.find("staffId").text();
	                var name = $this.find("name").text();
	                var phoneNumber = $this.find("phoneNumber").text();
	                var email = $this.find("email").text();
	                var role=$this.find("role").text();
	                var photo=$this.find("photo").text();
	                var roleText="";
	                if(role=="1"){
	                	roleText="中层管理者";
	                }else{
	                	roleText="总务人员";
	                }
	                $("<tr><td>"+(i+1)+"<input type=\"hidden\" value=\""+staffId+"\"/></td><td>"+name+"</td><td>"+roleText+"</td><td>"+phoneNumber+"</td><td>"+email+"</td><td><img src=\"upload/"+photo+"\"/></td><td><a href=\"#\" class=\"userManagermetEdi\">修改</a></td><td><a href=\"#\" class=\"userManagermentDel\">删除</a></td></tr>").appendTo("#user_manage_look_table");
	            });
	            
	        }
	    });	
	}
	
	
    $(".userManagermentDel").live('click',function(){
    	var true_or_false = window.confirm("您确定要删除该用户吗？");
       	var tr=$(this).parent().parent();
       	var userId=tr.children("td:first").children("input:first").attr("value");
          if(true_or_false){
       	   $.ajax({
       		   type:"GET",
          		url:$.getRootPath()+"/rest/staffservice/del/"+userId,
          		success:function(data){
                   var $xml=$(data);
                      //取出返回的结果
          			var update_user_meg=$xml.find("message").text();         			
                     $("#user_manage_look_table").find("tr").each(function(i){
                    	 if(i>0)
                    	 $(this).remove();
                     });
          			userManagermentbind();
          		}
       	   });
          }
    });
    //编辑用户
    $(".userManagermetEdi").live('click',function(){
    	$("#user_manage_look_all_div").hide();
    	$("#user_manage_update_div").slideDown(500);
    	var tr=$(this).parent().parent();
    	var userId=tr.children("td:first").children("input:first").attr("value");
    	//使用ajax根据id查询人员详情
    	$.ajax({
    		type:"GET",
    		url:$.getRootPath()+"/rest/staffservice/"+userId,
    		success:function(data){
                var $xml=$(data);
                
                //取出返回的结果
    			var update_user_id=$xml.find("staffId").text();
    			var update_user_name=$xml.find("name").text();
    			var update_user_password=$xml.find("password").text();
    			var update_user_phoneNumber=$xml.find("phoneNumber").text();
    			var update_user_email=$xml.find("email").text();
    			var update_user_role=$xml.find("role").text();
    			var update_user_photo=$xml.find("photo").text();
    			//设置更改的项
    			$("#user_update_id").attr("value",update_user_id);
    			$("#user_update_name").attr("value", update_user_name);
    			$("#user_update_password").attr("value",update_user_password);
    			$("#user_update_tel").attr("value",update_user_phoneNumber);
    			$("#user_update_email").attr("value",update_user_email);
    	       // $("#user_update_pic").attr("src","upload/"+update_user_photo);
    		    $("#user_update_role").attr("value",update_user_role);
    		  
    		    $("#user_update_old_pic").attr("value",update_user_photo);
    	
    		}
    	});
        
    	
    });
    
    
	
	
	//////////////
//    $("#delet_user1").click(function(){
//        var true_or_false = window.confirm("您确定要删除该用户吗？");
//    	var tr=$(this).parent().parent();
//    	var userId=tr.children("td:first").children("input:first").attr("value");
//       if(true_or_false){
//    	   $.ajax({
//    		   type:"GET",
//       		url:$.getRootPath()+"/rest/staffservice/del/"+userId,
//       		success:function(data){
//                   var $xml=$(data);
//                   //取出返回的结果
//       			var update_user_meg=$xml.find("message").text();
//       			alert(update_user_meg);
//       		}
//    		   
//    	   });
//       } 
//    });
	/*==========================*/
//	$("#update_user1").click(function(){
//       //得到tr中的userID
//    	var tr=$(this).parent().parent();
//    	var userId=tr.children("td:first").children("input:first").attr("value");
//    	//使用ajax根据id查询人员详情
//    	$.ajax({
//    		type:"GET",
//    		url:$.getRootPath()+"/rest/staffservice/"+userId,
//    		success:function(data){
//                var $xml=$(data);
//                
//                //取出返回的结果
//    			var update_user_id=$xml.find("staffId").text();
//    			var update_user_name=$xml.find("name").text();
//    			var update_user_password=$xml.find("password").text();
//    			var update_user_phoneNumber=$xml.find("phoneNumber").text();
//    			var update_user_email=$xml.find("email").text();
//    			var update_user_role=$xml.find("role").text();
//    			var update_user_photo=$xml.find("photo").text();
//    			//设置更改的项
//    			$("#user_update_id").attr("value",update_user_id);
//    			$("#user_update_name").attr("value", update_user_name);
//    			$("#user_update_password").attr("value",update_user_password);
//    			$("#user_update_tel").attr("value",update_user_phoneNumber);
//    			$("#user_update_email").attr("value",update_user_email);
//    	       // $("#user_update_pic").attr("src","upload/"+update_user_photo);
//    		    $("#user_update_role").attr("value",update_user_role);
//    		    $("#user_update_old_pic").attr("value",update_user_photo);
//    		    //变换显示的div
//				
//    			$(".contentbox div").hide();//把右边页面其他div给隐藏了
//    		    $("#user_manage_update_div").show(500);
//    		}
//    	});
//    	
//      
//    });
    
    
    $("#user_manage_update_submitBtn").click(function(){
    	$.ajax({
    		type:"POST",
    		url:$.getRootPath()+"/rest/staffservice/updatestaff",
    		data:$('#user_manage_update_form').serialize(),
    		success:function(msg){
    			$xml=$(msg);
    			alert($xml.find("message").text());
    			$("#user_manage_look_all_div").slideDown(500);
    	    	$("#user_manage_update_div").hide();
    			
    		}
    	});
    });
    $("#user_manage_change_pic_btn").click(function(){
        //$("#contentDiv div").hide();//把右边页面其他div给隐藏了
        $("#user_manage_change_pic_td").show(500);
    });
    //==============sun 2012-7-15
    $("#user_manage_add_btn").click(function(){
    	$('#user_manage_add_form').submit();
    	
    });
    
	
});
