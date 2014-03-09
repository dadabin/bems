/***
 * @author  LPM
 * 7.19
 */
//=========选项卡
$(document).ready(function() {
		$("#dataAnalysis_telephone_meunu_bar_div").tabs();
		$("#dataAnalysis_telephone_show_data_btn").click(function()
		{	
			$("#anylis_table_and_content_warper_div").slideDown(500);
		});
		
		$("#dataAnalysis_telephone_meunu_bar_div ul li a").click(function(){
			$("#container").empty();
			$("#anylis_table_div").empty();
		});
		
});

//=================点击事件====================//
//=======1、获取不同权限话费最高的前十门数据
$("#dataAnalysis_telephone_basic_data_look_btn").click(function(){
	//获取更新的参数$("#ddlregtype").find("option:selected").text();
	var power = $("#dataAnalysis_telephone_basic_data_look_power_select").find("option:selected").val();
	var year = $("#dataAnalysis_telephone_basic_data_look_year_select").find("option:selected").val();
	var month = $("#dataAnalysis_telephone_basic_data_look_month_select").find("option:selected").val();
	
	var url = $.getRootPath()+"/rest/phoneAnalysis/query?type="+power+"&year="+year+"&month="+month;
	//图表更新
	$("#container").empty();
	//表格为空
	$("#anylis_table_div").empty();
	showChart1(url,power,year,month);
	
	//=============table处理
	showTable1(url);
	//显示
	$("#anylis_div").slideDown(500);
	
	
	
});
//=======2、大楼各楼层话费所占比例情况
$("#dataAnalysis_telephone_constrast_data_look_btn").click(function(){
	//获取更新的参数$("#ddlregtype").find("option:selected").text();
	var year = $("#dataAnalysis_telephone_constrast_data_look_year_select").find("option:selected").val();
	var month = $("#dataAnalysis_telephone_constrast_data_look_month_select").find("option:selected").val();
	var url_all = $.getRootPath()+"/rest/phoneAnalysis/floors?year="+year+"&month="+month;
	var url_avg = $.getRootPath()+"/rest/phoneAnalysis/avg?year="+year+"&month="+month;
	//图表更新
	$("#container").empty();
	//表格为空
	$("#anylis_table_div").empty();
	showChart2(url_all,url_avg);
	//显示
	$("#anylis_div").slideDown(500);
});

//=======3、大楼各楼层话费所占比例情况
$("#dataAnalysis_telephone_all_data_look_btn").click(function(){
	//获取更新的参数$("#ddlregtype").find("option:selected").text();
	var year = $("#dataAnalysis_telephone_all_data_look_year_select").find("option:selected").val();
	var month = $("#dataAnalysis_telephone_all_data_look_month_select").find("option:selected").val();
	var url = $.getRootPath()+"/rest/phoneAnalysis/floors?year="+year+"&month="+month;
	//图表更新
	$("#container").empty();
	//表格为空
	$("#anylis_table_div").empty();
	showChart3(url);	
	//显示
	$("#anylis_div").slideDown(500);
	
});

//===========================表单提交
$("#anylis_content_form_submit_btn").click(function(){
	var content = $("#anylis_content_text_area").val();
	var url = $.getRootPath()+"/rest/analysisReportService/add?content="+content;
	$.ajax({
	        type: "post",
	        dataType: "json",
	        url: url,
	        success: function(msg){
	          alert("分析添加成功！");
	          $("#anylis_content_text_area").value = "";
	        },
			error:function(){
				alert("分析添加失败咯！");
			}
	
	    });
});

//=================绘制图表==================//
//==================获取不同权限话费最高的前十门电话数据
function showChart1(url_top,power,year,month){
	var title = year+"年"+month+"月，";
	if(power=="word"){
		title += "国际长途权限";
	}else if (power=="city") {
		title += "市话权限";
	}else if (power=="contry") {
		title += "国内长途权限";
	}else if (power=="inner") {
		title += "内线权限";
	}
	title += "的前十门电话费用情况";
	 var chart;
	 var datas=[] ,phoneNO=[];
	 $.ajax({
		 type: "GET",
		 dataType: "json",
		 url:url_top,
		 success: function(msg){
			 var myobj = eval(msg);
		     var all = myobj.serize;
		        for(var i=0;i<all.length;i++){
		        	datas[i] = all[i].fee * 1;
		        	phoneNO[i] = "号码："+all[i].phone + "\n责任人："+all[i].responsor;
		        }
				create_chart();//加载图像
				showTable1(all);//填充表格
		 }
	 });
	 
	   function create_chart(){
	   	 chart = new Highcharts.Chart({
	   	 chart: {
        renderTo: 'container',
        type: 'column',
        margin: [ 50, 50, 100, 80]
    	},
    	title: {
       		text: title
    	},
    xAxis: {
        categories: phoneNO,
        labels: {
            rotation: -45,
            align: 'right',
            style: {
                fontSize: '10px',
                fontFamily: 'Verdana, sans-serif'
            }
        }
    },
    yAxis: {
        min: 0,
        title: {
            text: '电话费用（￥）'
        }
    },
    legend: {
        enabled: false
    },
    tooltip: {
        formatter: function() {
            return '<b>'+ this.x +'</b><br/>'+
                '话费: '+ Highcharts.numberFormat(this.y, 1) +
                '元';
        }
    },
        series: [{
        name: '话费',
        data: datas,
        dataLabels: {
            enabled: true,
            rotation: -90,
            color: '#FFFFFF',
            align: 'right',
            x: -3,
            y: 10,
            formatter: function() {
                return this.y;
	             },
	             style: {
	                 fontSize: '10px',
	                 fontFamily: 'Verdana, sans-serif'
	             }
        	}
        }]
	   	 });
	   }
}

//==========楼层话费以及平均对比情况
function showChart2(url_All,url_Avg){
	var all,avg,myCharts;
	$.ajax({
        type: "GET",
        dataType: "json",
        url: url_All,
        success: function(msg){
        	var myobj = eval(msg);
			all = myobj.datas;
			//取得平均值
			getDataAvg();
			
        }
    });
	
	function getDataAvg(){
		$.ajax({
	        type: "GET",
	        dataType: "json",
	        url: url_Avg,
	        success: function(msg){
	        	var myobj = eval(msg);
				avg = myobj.datas;
				//显示图表
				show();
				
				//===表格数据处理
				var mdata = new Array();
				for(var i=0;i<avg.length;i++){
					var a = "{floor:"+(i+1)+",all:"+all[i]+",avg:"+avg[i]+"}";
					mdata.push(a);
				}
				showTable2(eval("["+mdata+"]"));
	        }
	    });
	}
	
	function show(){
		myCharts = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                zoomType: 'xy'
            },
            title: {
                text: '楼层间话费对比'
            },
            subtitle: {
                text: '（ps:直线代表平均值）'
            },
            xAxis: [{
                categories: ['一楼', '二楼', '三楼', '四楼', '五楼', '六楼']
            }],
            yAxis: [{ // Primary yAxis
                labels: {
                    formatter: function() {
                        return this.value +'￥';
                    },
                    style: {
                        color: '#89A54E'
                    }
                },
                title: {
                    text: '人均消费金额',
                    style: {
                        color: '#89A54E'
                    }
                }
            }, { // Secondary yAxis
                title: {
                    text: '总消费金额',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +' ￥';
                    },
                    style: {
                        color: '#4572A7'
                    }
                },
                opposite: true
            }],
            tooltip: {
                formatter: function() {
                    return ''+
                        this.x +': '+ this.y +
                        (this.series.name == '总消费金额' ? '￥' : '￥');
                }
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                x: 120,
                verticalAlign: 'top',
                y: 100,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [{
                name: '总消费金额',
                color: '#4572A7',
                type: 'column',
                yAxis: 1,
                data: all
    
            }, {
                name: '人均消费金额',
                color: '#89A54E',
                type: 'spline',
                data: avg
            }]
		 });		
	}
	
} 


//=============各楼层数据话费比例图
function showChart3(urls) {
	var chart,mydatas;	
	$.ajax({
    type: "GET",
    dataType: "json",
    url: urls,
    success: function(msg){
        var myobj = eval(msg);
		mydatas = myobj.datas;
		create_chart();
		
		//===表格数据处理
		var mdata = new Array();
		//球的总话费
		var totle ;
		for(var j=0;j<mydatas.length;j++){
			totle += Number(mydatas[i]) * 1;
		}
		//数据拼接
		for(var i=0;i<mydatas.length;i++){
			var d = (mydatas[i]*1) / totle;
			var a = "{floor:"+(i+1)+",all:"+mydatas[i]+",rate:"+d+"}";
			mdata.push(a);
		}
		//显示表格
		showTable3(eval("["+mdata+"]"));
    	}
		
	});

function create_chart(){
	  chart = new Highcharts.Chart({
		
        chart: {
            renderTo: 'container',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: '每月各楼层电话费用比例情况图'
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
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    formatter: function() {
                        return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                    }
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data:[
                    ['1楼',  mydatas[0]*1],
                    ['2楼',  mydatas[1]*1],
                    ['3楼',  mydatas[2]*1],
                    ['4楼',  mydatas[3]*1],
                    ['5楼',  mydatas[4]*1],
					['6楼',  mydatas[5]*1]
            	]
        }]
    });
}
}

//====================================
//==========填充表格
function showTable1(mdata){
		 var data={
				 name: '话费最高前十门',
	             list_id: '***',
	             table: mdata
		}
		//设置debug模式
	    $.jTemplatesDebugMode(true);
	    // 附上模板
	    $("#anylis_table_div").setTemplateURL("template/phone.table.template");
	    // 给模板加载数据
	    $("#anylis_table_div").processTemplate(data);

    
}

function showTable2(mdata){
	 var data={
			 name: '费用对比',
            list_id: '***',
            table: mdata
	}
	//设置debug模式
   $.jTemplatesDebugMode(true);
   // 附上模板
   $("#anylis_table_div").setTemplateURL("template/phone.table2.template");
   // 给模板加载数据
   $("#anylis_table_div").processTemplate(data);
}

function showTable3(mdata){
	 var data={
			 name: '楼层费话比例',
           list_id: '****',
           table: mdata
	}
	//设置debug模式
  $.jTemplatesDebugMode(true);
  // 附上模板
  $("#anylis_table_div").setTemplateURL("template/phone.table3.template");
  // 给模板加载数据
  $("#anylis_table_div").processTemplate(data);
}


$(function() {
	$( "#dialog_anaysis_phone" ).dialog({
		height: 300,
		width: 550,
		autoOpen: false,
		show: "blind",
		hide: "explode"
	});

	$( "#opener_phone_1" ).click(function() {
		$( "#dialog_anaysis_phone" ).dialog( "open" );
		return false;
	});
	$( "#opener_phone_2" ).click(function() {
		$( "#dialog_anaysis_phone" ).dialog( "open" );
		return false;
	});
	$( "#opener_phone_3" ).click(function() {
		$( "#dialog_anaysis_phone" ).dialog( "open" );
		return false;
	});
});


