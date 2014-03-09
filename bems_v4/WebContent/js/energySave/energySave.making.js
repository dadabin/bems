$(document).ready(function()
{
//	$( ".date_picker" ).datepicker({
//			changeMonth: true,
//			changeYear: true,
//			dateFormat:'yy-mm-dd'
//		});
	
	$(".report_a").click(function(){
		 $("#report_tr").remove();
		 $(this).parent().parent().after("<tr id=\"report_tr\"><td colspan=\"3\" ><div id=\"container\"></div ><div id=\"reportText_div\"></div>" +
		 		"<input type=\"button\" value=\"     收    起       \" id=\"close_btn\">" +
		 		"" +
		 		"" +
		 		"</td></tr>");
		// $(this).parent().parent().append($("#plan_material_tr"));
		 $("#close_btn").click(function(){
			 
			 $("#report_tr").remove();
			 
		 });
		 $("#reportText_div").append("<span width=\"100%\">本系统拟通过监控并采集整个楼宇的各种能耗信息（包括楼层信息、水表、电表、电话等信息），根据读取到的基础数据做出各种科学分析，然后将系	统分析结果以图表、报表等形式呈现给总务人员，以便总务人员制作数据分析报告，最后由中层管理者在数据分析报告以及数据图表等基础上做出环保节能决策方案、计划等，并可由系统保存决策。当发生紧急事件时，系统能通过短信或者邮件进行周知到相关人员。 </span>");
		 
		 
		 var chart;
		    
         chart = new Highcharts.Chart({
             chart: {
                 renderTo: 'container',
                 plotBackgroundColor: null,
                 plotBorderWidth: null,
                 plotShadow: false
             },
             title: {
                 text: ' 2010~2011（年）'
             },
             tooltip: {
                 formatter: function() {
                     return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                 }
             },
             plotOptions: {
                 pie: {
                     allowPointSelect: true,
                     cursor: 'pointer',
                     dataLabels: {
                         enabled: false
                     },
                     showInLegend: true
                 }
             },
             series: [{
                 type: 'pie',
                 name: 'Browser share',
                 data: [
                     ['1F',10.0],
                     ['2F',26.8],
                     {
                         name: '3F',
                         y: 12.8,
                         sliced: true,
                         selected: true
                     },
                     ['4F',8.5],
                     ['5F',6.2],
                     ['6F',9.0],
                     ['地下室',4.0]
                 ]
             }]
         });
         
		// $("#container").append($("plan_material_div").text());
		 //$("#reprot_row_div").append($("#plan_material_div"));
		// $("#plan_material_div").slideDown(1000);
	});
		
//	$("#plan_select").change(function()
//	{
//		 $("#plan_material_div").slideDown(1000);
//	});
	
	$("#collepse_btn").click(function()
	{
		 $("#plan_material_div").slideUp(500);
	});
	
	$("#plan_select_option_a").hover(function(event)
	{
		$(this).css("background-color","#646ff4");
	},
	function()
	{
		$(this).css("background-color","#ffffff");
	})
});
