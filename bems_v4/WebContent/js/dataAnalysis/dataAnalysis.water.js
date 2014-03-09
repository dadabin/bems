/***
 * @author LPM
 * 7.20
 */
//===================时间选择控件
$(document).ready(function() {
		$("#dataAnalysis_water_meunu_bar_div").tabs();
		
		$( ".date_picker" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat:'yymmdd'
		});
		
		$("#dataAnalysis_water_meunu_bar_div ul li a").click(function(){
			$("#container").empty();
			$("#wataer_table").empty();
		});
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

//==================点击事件

//==================仪表历史数据查询
$("#dataAnalysis_water_basic_data_look_btn").click(function(){
	var deviceNo = $("#dataAnalysis_water_basic_data_look_number_select").find("option:selected").val();
	var url = $.getRootPath()+"/rest/deviceDataAnalysis/queryall?deviceTYpe=meter&did="+deviceNo;
	$("#container").empty();
	//=============table刷新
	$("#wataer_table").empty();
	freshChart1(url,deviceNo);
});
//==================楼层间用水量对比
$("#dataAnalysis_water_constrast_data_look_btn").click(function(){
	var floors = [];
	//获取复选按钮的值
	$('input[name="floors"]:checked').each(function() {
		floors.push($(this).val());
	});
	var begin = $("#water_constrast_begin_time").attr("value");
	var end = $("#water_constrast_end_time").attr("value");
	var url = $.getRootPath()+"/rest/deviceDataAnalysis/query?deviceTYpe=meter";
	//验证
	if(end.substring(0,4) != begin.substring(0,4)){
		alert("只能查询同一年份的数据对比！");
		return;
	}
	if(floors.length<2){
		alert("请选择两个以上的楼层进行对比！");
		return;
	}
	
	for(var i=0;i<floors.length;i++){
		url += "&id="+floors[i];
	}
	url += "&begin="+begin+"000000&end="+end+"000000";
	$("#container").empty();
	//=============table刷新
	$("#wataer_table").empty();
	
	freshChart2(url);
});

//============月份查询
$("#dataAnalysis_water_month_data_look_btn").click(function(){
	var deviceNo = $("#dataAnalysis_water_month_data_look_number_select").find("option:selected").val();
	var year = $("#dataAnalysis_water_month_data_look_number_year_select").find("option:selected").val();
	var url = $.getRootPath()+"/rest/deviceDataAnalysis/querymonth?deviceTYpe=meter&id="+deviceNo+"&year="+year;
	//=============图表刷新
	$("#container").empty();
	//=============table刷新
	$("#wataer_table").empty();
	
	freshChart3(url,deviceNo,year);
	
	
	
});
//============楼层比例
$("#dataAnalysis_water_season_data_look_btn").click(function(){
	
	var year = $("#dataAnalysis_water_season_data_look_number_year_select").find("option:selected").val();
	var month = $("#dataAnalysis_water_season_data_look_number_month_select").find("option:selected").val();

	url = $.getRootPath()+"/rest/deviceDataAnalysis/querybuilding?deviceTYpe=meter&year="+year+"&month="+month;
	$("#container").empty();
	//=============table刷新
	$("#wataer_table").empty();
	freshChart4(url,year,month);
});
//============年份对比
$("#dataAnalysis_water_year_data_look_btn").click(function(){
	var deviceNo = $("#dataAnalysis_water_year_data_look_number_select").find("option:selected").val();
	var year = $("#dataAnalysis_water_year_data_look_number_year_select").find("option:selected").val();
	var myDate = new Date();
	if(year > myDate.getFullYear()){
		alert("还未到"+year+"年！选择的年份必须在"+myDate.getFullYear()+"年之前！");
		return;
	}
	url = $.getRootPath()+"/rest/deviceDataAnalysis/queryyear?deviceTYpe=meter&id="+deviceNo+"&year="+year;
	$("#container").empty();
	//=============table刷新
	$("#wataer_table").empty();
	freshChart5(url,year,deviceNo);
});


//====================数据及图像处理
function freshChart1(url,id){
	 var chart, datas;
	    function handle_msg(msg){
	        var myobj = eval(msg);
	        datas = myobj.data;
	    };
	    
	    function create_chart(){
	        chart = new Highcharts.Chart({
	            chart: {
	                renderTo: 'container',
	                zoomType: 'x',
	                spacingRight: 20
	            },
	            title: {
	                text: '用水情況明細'
	            },
	            subtitle: {
	                text: document.ontouchstart === undefined ? '點擊查看個時段詳情' : '可隨意放大'
	            },
	            xAxis: {
	                type: 'datetime',
	                maxZoom: 14 * 24 * 3600000, // fourteen days
	                title: {
	                    text: null
	                }
	            },
	            yAxis: {
	                title: {
	                    text: '用量（噸）'
	                },
	                min: 0.6,
	                startOnTick: false,
	                showFirstLabel: false
	            },
	            tooltip: {
	                shared: true
	            },
	            legend: {
	                enabled: false
	            },
	            plotOptions: {
	                area: {
	                    fillColor: {
	                        linearGradient: {
	                            x1: 0,
	                            y1: 0,
	                            x2: 0,
	                            y2: 1
	                        },
	                        stops: [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(2,0,0,0)']]
	                    },
	                    lineWidth: 1,
	                    marker: {
	                        enabled: false,
	                        states: {
	                            hover: {
	                                enabled: true,
	                                radius: 5
	                            }
	                        }
	                    },
	                    shadow: false,
	                    states: {
	                        hover: {
	                            lineWidth: 1
	                        }
	                    }
	                }
	            },
	            
	            series: [{
	                type: 'area',
	                name: 'USD to EUR',
	                pointInterval: 24 * 3600 * 1000,
	                pointStart: Date.UTC(2006, 0, 01),
	                data: datas
	            }]
	        });
	    };
	    
	    
	    $.ajax({
	        type: "GET",
	        dataType: "json",
	        url: url,
	        success: function(msg){
	          //  alert(11);
	            handle_msg(msg);
	            create_chart();
	            
	            //=============表格数据填充
	            var myobj = eval(msg);
		        datas = myobj.data;
				//表格数据处理
				//===表格数据处理
				var mdata = new Array();
				for(var i=0;i<datas.length;i++){
					if(i < 10){
						var a = "{id:"+id+",data:"+datas[i]+"}";
						mdata.push(a);
					}
				}
				showTT1(eval("["+mdata+"]"));
	        }
	    });
	    function showTT1(mdata){
			var data={
				    name: '仪表明细',
		           list_id: '***',
		           table: mdata
			}
			
			//设置debug模式
		  $.jTemplatesDebugMode(true);
		  // 附上模板
		  $("#wataer_table").setTemplateURL("template/meter.table1.template");
		  // 给模板加载数据
		  $("#wataer_table").processTemplate(data);
		} 
}
function freshChart2(url){

    var chart, times, serise1 = [1, 1];
    
    function handle_msg(msg){
        var ajaxOb = msg.serize;
        
        for (var i = 0; i < ajaxOb.length; i++) {
            times = ajaxOb[i].times;
            var data1 = new Array();
            for (var j = 0; j < ajaxOb[i].data.length; j++) {
                data1[j] = ajaxOb[i].data[j] * 1;
            }
            
            serise1[i] = {
                name: ajaxOb[i].name,
                data: data1
            };
        }
    };
    
    function create_chart(){
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'spline',
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: '2010年一月到三月兩個月三水錶的用水情況',
                x: -20 //center
            },
            subtitle: {
                text: '	TQC統計 ',
                x: -20
            },
            xAxis: {
                categories: times
            },
            yAxis: {
                title: {
                    text: '用量（吨）'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                formatter: function(){
                    return '<b>' + this.series.name + '</b><br/>' +
                    this.x +
                    ': ' +
                    this.y +
                    '°C';
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
            series: serise1
        });
        
    };
    
    
    $.ajax({
        type: "GET",
        dataType: "json",
        url:url,
        success: function(msg){
            handle_msg(msg);
            create_chart();
            
            //==========表格数据填充
            var ajaxOb = eval(msg);
            showTT2(ajaxOb);
        }
    });
    
    function showTT2(mdata){
	
		//设置debug模式
	  $.jTemplatesDebugMode(true);
	  // 附上模板
	  $("#wataer_table").setTemplateURL("template/meter.table2.template");
	  // 给模板加载数据
	  $("#wataer_table").processTemplate(mdata);
	} 
}
function freshChart3(url,deviceNo,year){
	var chart, datas;
	function handle_msg(msg) {
		var myobj = eval(msg);
		datas = myobj.month;
	}

	function create_chart() {
		chart = new Highcharts.Chart({
			chart : {
				renderTo : 'container',
				type : 'column',
				margin : [ 50, 50, 100, 80 ]
			},
			title : {
				text : deviceNo+'号水表在'+year+'年的用水情況'
			},
			xAxis : {
				categories : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
						'九月', '十月', '十一月', '十二月' ],
				labels : {
					rotation : -45,
					align : 'right',
					style : {
						fontSize : '13px',
						fontFamily : 'Verdana, sans-serif'
					}
				}
			},
			yAxis : {
				min : 0,
				title : {
					text : '用水量 (/吨)'
				}
			},
			legend : {
				enabled : false
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.x + '</b><br/>' + '水量: '
							+ Highcharts.numberFormat(this.y, 1) + ' 噸';
				}
			},
			series : [ {
				name : 'Population',
				data : datas,
				dataLabels : {
					enabled : true,
					rotation : -90,
					color : '#FFFFFF',
					align : 'right',
					x : -3,
					y : 10,
					formatter : function() {
						return this.y;
					},
					style : {
						fontSize : '13px',
						fontFamily : 'Verdana, sans-serif'
					}
				}
			} ]
		});
	}
	$.ajax({
				type : "GET",
				dataType : "json",
				url : url,
				success : function(msg) {
					handle_msg(msg);
					create_chart();
					
					var myobj = eval(msg);
					datas = myobj.month;
					//表格数据处理
					//===表格数据处理
					var mdata = new Array();
					for(var i=0;i<datas.length;i++){
						var a = "{id:"+deviceNo+",year:"+year+",month:"+(i+1)+",data:"+datas[i]+"}";
						mdata.push(a);
					}
					
					showTT3(eval("["+mdata+"]"));
				}
			});
	//=====================================填充表格
	function showTT3(mdata){
		var data={
				 name: '月份费用对比',
	           list_id: '***',
	           table: mdata
		}
		
		//设置debug模式
	  $.jTemplatesDebugMode(true);
	  // 附上模板
	  $("#wataer_table").setTemplateURL("template/meter.table.template");
	  // 给模板加载数据
	  $("#wataer_table").processTemplate(data);
	} 

}

function freshChart4(url,year,month){
	var chart,mydatas;
	
	$.ajax({
    type: "GET",
    dataType: "json",
    url: url,
    success: function(msg){
        var myobj = eval(msg);
		mydatas = myobj.data;
		create_chart();

		//===表格数据处理
			var mdatas = new Array();
			for ( var i = 0; i < mydatas.length; i++) {
				var a = "{year:" + year + ",floor:" + (i + 1) + ",data:"
						+ mydatas[i] * 1 + "}";
				mdatas.push(a);
			}
			showTT4(eval("["+mdatas+"]"));
    	}
		
	});

function showTT4(mdata){
		var data={
				 name: '月份费用对比',
	           list_id: '***',
	           table: mdata
		}
		
		//设置debug模式
	  $.jTemplatesDebugMode(true);
	  // 附上模板
	  $("#wataer_table").setTemplateURL("template/meter.table4.template");
	  // 给模板加载数据
	  $("#wataer_table").processTemplate(data);
} 	
	
	
function create_chart(){
	  chart = new Highcharts.Chart({
		
        chart: {
            renderTo: 'container',
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: year+'年'+month+'月,各楼层用水量比例图'
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
					['6楼',  mydatas[5]*1],
                    ['绿化带',  mydatas[6]*1],
					['地下室',  mydatas[7]*1]
//					['1楼',  161],
//                    ['2楼',  221],
//                    ['3楼',  155],
//                    ['4楼',  121],
//                    ['5楼',  350],
//					['6楼',  232]
            	]
        }]
    });
}     
	
	
}
function freshChart5(url,year,deviceNo){
	var chart,mydatas;
	
	$.ajax({
    type: "GET",
    dataType: "json",
    url:url,
    success: function(msg){
        var myobj = eval(msg);
		mydatas = myobj.serize;
		
		var mTable = myobj;
		
		for(var i=0;i<mydatas.length;i++){
			var data1 = new Array();

			for (var j = 0; j < mydatas[i].data.length; j++) {
            	data1[j] = mydatas[i].data[j] * 1;
        	}
			mydatas[i].data = data1;
   		}
		create_chart();
		//创建图表
		showTT5(mTable);
    }
		
	});
	
	function showTT5(mdata){
		
		//设置debug模式
	  $.jTemplatesDebugMode(true);
	  // 附上模板
	  $("#wataer_table").setTemplateURL("template/meter.table5.template");
	  // 给模板加载数据
	  $("#wataer_table").processTemplate(mdata);
} 	

function create_chart(){
	 chart = new Highcharts.Chart({
        chart: {
            renderTo: 'container',
            type: 'bar'
        },
        title: {
            text: year+'年至'+new Date().getFullYear()+"年"+deviceNo+"号水表"
        },
        subtitle: {
            text: '季度对比情况'
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
                text: '用量（吨）',
                align: 'high'
            }
        },
        tooltip: {
            formatter: function() {
                return ''+
                    this.series.name +': '+ this.y +'吨';
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
		series:mydatas
	 });	
	}       
}
$(function() {
	$( "#dialog_anaysis" ).dialog({
		height: 300,
		width: 500,
		autoOpen: false,
		show: "blind",
		hide: "explode",
		
	});

	$( "#opener_1" ).click(function() {
		$( "#dialog_anaysis" ).dialog( "open" );
		return false;
	});
	$( "#opener_2" ).click(function() {
		$( "#dialog_anaysis" ).dialog( "open" );
		return false;
	});
	$( "#opener_3" ).click(function() {
		$( "#dialog_anaysis" ).dialog( "open" );
		return false;
	});
	$( "#opener_4" ).click(function() {
		$( "#dialog_anaysis" ).dialog( "open" );
		return false;
	});
	$( "#opener_5" ).click(function() {
		$( "#dialog_anaysis" ).dialog( "open" );
		return false;
	});
});
