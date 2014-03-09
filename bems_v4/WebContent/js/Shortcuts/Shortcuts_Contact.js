/***
 * @author LPM
 * 7.22
 */
//===================时间选择控件
$(document).ready(function() {
	(function($){
        $.getRootPath = function(){
            var strFullPath = window.document.location.href;
            var strPath = window.document.location.pathname;
            var pos = strFullPath.indexOf(strPath);
            var prePath = strFullPath.substring(0, pos);
            var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
            return (prePath + postPath);
        };
    })(jQuery);
	
	var url = $.getRootPath()+"/rest/staffservice/getStaffs";
	$.ajax({
        type: "GET",
        dataType: "json",
        url: url,
        success: function(msg){
        	var myobj = eval(msg);
			//插入数据到页面的表格
        	showTable(myobj);
        },
		Error:function(msg){
			alert("数据获取失败！");
		}
		
    });
	
	function showTable(mdata){
		//设置debug模式
		  $.jTemplatesDebugMode(true);
		  // 附上模板
		  $("#shortcuts_contact_table_div").setTemplateURL("template/Contacts.template");
		  // 给模板加载数据
		  $("#shortcuts_contact_table_div").processTemplate(mdata);
	}
		
});