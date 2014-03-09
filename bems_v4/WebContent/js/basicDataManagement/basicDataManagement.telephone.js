/*
 * @author:秦凡鹏
 * @date：7.13
 * @description:数据采集功能的右边页面对应的js文件
 */

$(document).ready(function(){
    $("#excel_type").click(function(){
        $("#manual_type_div").hide();
        $("#excel_type_div").slideDown(500);
    });
    $("#manual_type").click(function(){
        $("#excel_type_div").hide();
        $("#manual_type_div").slideDown(500);
    });
     
	 $("table tbody tr:odd").css("background-color","#dcdcde");
 
});
