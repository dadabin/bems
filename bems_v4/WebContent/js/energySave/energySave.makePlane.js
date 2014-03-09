
$(function(){
	
	$("#deviceType_select_div").change(function(){
		//1、选择仪表类型
		var deviceType = $("#deviceType_select_div").find("option:selected").val();
		
		if(deviceType == "meter"){
			//选择了水表，那么就需要填充水表的编号
			builtMeterSelect();
			//隐藏
			document.getElementById("plane_ammeter_deviceNo").style.display="none";//隐藏
			//显示出来
			document.getElementById("plane_meter_deviceNo").style.display="";//显示
		}else{
			builtAmmeterSelect();
			//隐藏
			document.getElementById("plane_meter_deviceNo").style.display="none";//隐藏
			//显示出来
			document.getElementById("plane_ammeter_deviceNo").style.display="";//显示
		}
	});   
	
	//选择电表编号
	$("#ammeter_select_div").change(function(){
		document.getElementById("plane_device_times").style.display="none";//隐藏年份
		document.getElementById("plane_device_times").style.display="";//显示年份
	});
	//选择水表编号
	$("#meter_select_div").change(function(){
		document.getElementById("plane_device_times").style.display="none";//隐藏年份
		document.getElementById("plane_device_times").style.display="";//显示年份
	});

	
	$("#plane_device_times_select").change(function(){
		
		var zhouqi = $("#plane_device_times_select").find("option:selected").val();
		if(zhouqi == "season"){
			//显示季节
			document.getElementById("choose_time_end_month").style.display="none";//显示年份
			document.getElementById("plane_device_year").style.display="";//显示年份
			document.getElementById("choose_time_end_season").style.display="";//显示年份
			

		}else if(zhouqi == "year"){
			document.getElementById("choose_time_end_season").style.display="none";//显示年份
			document.getElementById("choose_time_end_month").style.display="none";//显示年份
			document.getElementById("plane_device_year").style.display="";//显示年份

		}else{
			document.getElementById("choose_time_end_season").style.display="none";//显示年份
			document.getElementById("plane_device_year").style.display="";//显示年份
			//显示月份
			document.getElementById("choose_time_end_month").style.display="";//显示

		}
	});

	
	//============点击查询按钮===============//
	
	$("#make_plane_button").click(function(){
		$("#make_plane_charts_container").empty();
		var url = builtURL();
		var zhouqi = $("#plane_device_times_select").find("option:selected").val();
		
		//===============ajax请求数据================//
		$.ajax({
	        type: "get",
	        dataType: "json",
	        url: url,
			timeout: 5000,
	        success: function(msg){
	        	var myobj = eval(msg);
	        	
	    		if(zhouqi == "year"){
	    			var datas = myobj.result;
		    		var avg = myobj.avg;
		    		create_year_chart(datas,avg) ;
	    		}
	    		if(zhouqi == "season"){
	    			var datas = myobj.result;
	    			show_season_chart(datas);
//	    			alert(myobj.result);
	    		}
	    		if(zhouqi == "month"){
	    			show_month_chart();
	    		}
	    		
	        },
			error:function(){
				alert("获取数据失败！");
			}
	    });
	});
	
	
	
});

//============显示图表============//

	function create_year_chart(datas,avg) {
		var  chart = new Highcharts.Chart({
            chart: {
                renderTo: 'make_plane_charts_container',
                zoomType: 'xy'
            },
            title: {
                text: '预测能耗趋势图'
            },
            subtitle: {
                text: '（ps:直线代表平均值）'
            },
            xAxis: [{
                categories: [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
      						'九月', '十月', '十一月', '十二月' ],
            }],
            yAxis: [{ // Primary yAxis
                labels: {
                    formatter: function() {
                        return this.value +'单位';
                    },
                    style: {
                        color: '#89A54E'
                    }
                },
                title: {
                    text: '总能耗',
                    style: {
                        color: '#89A54E'
                    }
                }
            }, { // Secondary yAxis
                title: {
                    text: '人均能耗',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +'单位';
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
                        (this.series.name == '总能耗' ? ' 单位' : '单位');
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
                name: '人均能耗',
                color: '#4572A7',
                type: 'column',
                yAxis: 1,
                data: avg
    
            }, {
                name: '总能耗',
                color: '#89A54E',
                type: 'spline',
                data: datas
            }]
        });
	}

	
function show_season_chart(datas){
	var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'make_plane_charts_container',
            type: 'spline'
        },
        title: {
            text: '季度能耗预测'
        },
        subtitle: {
            text: '（ps:其中一条代表预测数据）'
        },
        xAxis: {
            categories: ['一季度', '二季度', '三季度', '四季度'],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: '能耗（单位）',
                align: 'high'
            }
        },
        tooltip: {
            formatter: function() {
                return ''+
                    this.series.name +': '+ this.y +' 单位';
            }
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -100,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: '#FFFFFF',
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: datas
	});
}	

function show_month_chart(){
	var chart = new Highcharts.Chart({
        chart: {
            renderTo: 'make_plane_charts_container',
            type: 'spline',
            marginRight: 130,
            marginBottom: 25
        },
        title: {
            text: '能耗预测',
            x: -20 //center
        },
        subtitle: {
            text: '（ps:直线代表平均值）',
            x: -20
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: '单位'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                    this.x +': '+ this.y +'单位';
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -10,
            y: 100,
            borderWidth: 0
        },
        series: [{
            name: '2012年',
            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
        }]
    });
}
	
	
	
//===============构造url================//
function builtURL()
{
	//==============获取参数===================//
	var devicT = $("#deviceType_select_div").find("option:selected").val();
	var MeterNo = $("#meter_select_div").find("option:selected").val();
	var AmmeterNo = $("#ammeter_select_div").find("option:selected").val();

	
	var qYear = $("#plane_device_year_select").find("option:selected").val();
	
	var tType = $("#plane_device_times_select").find("option:selected").val();
	var qMonth = $("#choose_time_end_month_select").find("option:selected").val();
	var qSeason = $("#choose_time_end_season_select").find("option:selected").val();
	
	var url = $.getRootPath();
	
	if(tType == "year" || tType=="")
	{
		//按年
		url += "/rest/forecastService/forcastYear";
			if(devicT == "ammeter"){
				url += "?deviceType=ammeter&deviceID="+AmmeterNo+"&forcastYear="+qYear;
			}else{
				url += "?deviceType=meter&deviceID="+MeterNo+"&forcastYear="+qYear;
			}
	}
	if(tType == "season")
	{
		//按季度
		url += "/rest/forecastService/forcastSeason";
		if(devicT == "ammeter"){
			url += "?deviceType=ammeter&deviceID="+AmmeterNo+"&forcastYear="+qYear+"&forcastSeason="+qSeason;
		}else{
			url += "?deviceType=meter&deviceID="+MeterNo+"&forcastYear="+qYear+"&forcastSeason="+qSeason;
		}
	}
	if(tType == "month")
	{
		//按月
		url += "/rest/forecastService/forcastMonth";
		if(devicT == "ammeter"){
			url += "?deviceType=ammeter&deviceID="+AmmeterNo+"&forcastYear="+qYear+"&forcastMonth="+qMonth;
		}else{
			url += "?deviceType=meter&deviceID="+MeterNo+"&forcastYear="+qYear+"&forcastMonth="+qMonth;
		}
	}
	return url;
}




//=============填充水表的编号================//
function builtMeterSelect()
{
	 //ajax填充下拉列表框
	var url = $.getRootPath()+"/rest/meterService/getMeters";
	$.ajax({
	        type: "get",
	        dataType: "xml",
	        url: url,
			timeout: 1000,
	        success: function(msg){
	          buildSelectMeter(msg);
	        },
			error:function(){
				alert("获取数据失败！");
			}
	    });
		function buildSelectMeter(msg){
				$(msg).find("meter").each(function(i){
					var id_value=$(this).children("meterId").text(); //取文本
					$("#meter_select_div").append("<option value="+id_value+">"+id_value+"</option>");
					
				});
			}	
}

//=================填充电表的编号==================//
function builtAmmeterSelect()
{
	 //ajax填充下拉列表框
	var url = $.getRootPath()+"/rest/ammeterService";
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
		$(msg).find("ammeter").each(function(i){
			var id_value=$(this).children("ammeterId").text(); //取文本
			$("#ammeter_select_div").append("<option value="+id_value+">"+id_value+"</option>");
		});
	}
}
