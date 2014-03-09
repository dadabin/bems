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
            //alert(prePath+postPath);
            return (prePath + postPath);
        };
    })(jQuery);
	
	
		
function showT(){
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
}
//============================绘制表格
	function showTable(mdata){
		//设置debug模式
		  $.jTemplatesDebugMode(true);
		  // 附上模板
		  $("#contact_dialog_table_div").setTemplateURL("template/choose_user.template");
		  // 给模板加载数据
		  $("#contact_dialog_table_div").processTemplate(mdata);
	}
	//===================================显示对话框
		var names = [];
		$( "#contact_dialog_div" ).dialog({
			height: 400,
			width: 550,
			autoOpen: false,
			show: "blind",
			hide: "explode",
			modal: true,
			buttons: {
				"确认":function(){
					//赋值给name
					//获取复选按钮的值
					$('input[name="user_name"]:checked').each(function() {
						names.push($(this).val());
					});
					
					$('#accept').val(names);
					
					$( this ).dialog( "close" );
				},
				Cancel: function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				//清空操作
			}
			
		});
		
		
		//主调函数
		$("#accept").focus(function(){
			$('#accept').val("");
			names.splice(0,names.length);
		 	showT();
		 	$( "#contact_dialog_div" ).dialog( "open" );
		 	//填充table
			return false;
		});	
		
});

