/***
 * @author LPM
 * 7.25
 */
$(document).ready(function() {
	
	(function($){
        $.getRootPath = function(){
            var strFullPath = window.document.location.href;
            var strPath = window.document.location.pathname;
            var pos = strFullPath.indexOf(strPath);
            var prePath = strFullPath.substring(0, pos);
            var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
            //alert(prePath+postPath);
            return (prePath + postPath);
        };
    })(jQuery);
	
	var url = $.getRootPath()+"/rest/staffservice/currentUser";
	$.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        success: function(msg){
        	var myobj = eval(msg);
			//插入数据到页面的表格
			$("#user_update_id").val(myobj.staffId);
			$("#user_update_name").val(myobj.name);
			$("#user_update_password").val(myobj.password);
			$("#user_update_tel").val(myobj.phoneNumber);
			$("#user_update_email").val(myobj.email);
			$("#photo").val(myobj.photo);
			$("#user_update_role").val(myobj.role);
			
        },
		Error:function(msg){
			alert("数据获取失败！");
		}
		
    });
	
	
});