$(document).ready(function() {

	
		$("#dataAnalysis_ammeter_meunu_bar_div ul li a").click(function(){
			$("#container").empty();
			$("#ammeter_table").empty();
		});

		$("#dataAnalysis_ammeter_meunu_bar_div").tabs();
		$( ".date_picker" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat:'yy-mm-dd'
		});
		///============//
		$( "#dialog_anaysis" ).dialog({
    		height: 320,
    		width: 600,
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
		
		
		$("#tabs6-1").hide();
		$("#tabs6-2").hide();
		$("#tabs6radio1").click(function(){
	    	$("#tabs6-2").hide();
	    	$("#tabs6-1").show();
	    });
	    $("#tabs6radio2").click(function(){
	    	$("#tabs6-1").hide();
	    	$("#tabs6-2").show();
	    	
	    });
		
		
		//整栋楼用电量
		$("#dataAnalysis_ammeter_whole_bulding_fee_data_btn").click(function(){
			var chart, times, serise1 = [1, 1];
		    $("#anylis_div").slideDown(500);
			
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
		    //ajax请求
		    $.ajax({
		        type: "GET",
		        dataType: "json",
		        url: $.getRootPath()+"/rest/ammeterAnalysisService/analysisfloorall",
		        success: function(msg){
		            handle_msg(msg);
		            create_chart();
		        }
		    });
			
			
			
		});
		
		
		
		//整栋楼不同电路的比较
		$("#dataAnalysis_ammeter_whole_bulding_different_circuit_data_btn").click(function(){
			
			 var chart, times, serise1 = [1, 1];
			    $("#anylis_div").slideDown(500);
				
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
			                type: 'column',
			                marginRight: 130,
			                marginBottom: 25
			            },
			            title: {
			                text: '',
			                x: -20 //center
			            },
			            subtitle: {
			                text: '',
			                x: -20
			            },
			            xAxis: {
			                categories: times
			            },
			            yAxis: {
			                title: {
			                    text: '电量 (度)'
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
			                    '度';
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
			        type: "POST",
			        dataType: "json",
			        url: $.getRootPath()+"/rest/ammeterAnalysisService/analysisbuildingtype",
			        data:{"startTime":$("#ammeter_whole_bulding_different_circuit_data_constrast_begin_time").attr("value"),"endTime":$("#ammeter_whole_bulding_different_circuit_data_constrast_end_time").attr("value")},
			        success: function(msg){
			            handle_msg(msg);
			            
			            create_chart();
			        }
			    });
			    
		});
		//====================================================各楼层间相同电路的比较==============================
		$("#dataAnalysis_ammeter_diferent_floors_same_circuit_data_constrast_btn").click(function(){
			var chart, times, serise1 = [1, 1];
		    $("#anylis_div").slideDown(500);
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
		                type: 'line',
		                marginRight: 130,
		                marginBottom: 25
		            },
		            title: {
		                text: '',
		                x: -20 //center
		            },
		            subtitle: {
		                text: ' ',
		                x: -20
		            },
		            xAxis: {
		                categories: times
		            },
		            yAxis: {
		                title: {
		                    text: '电量 (度)'
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
		                    '度';
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
			var floors = [];
			//获取复选按钮的值
			$('input[name="floors1"]:checked').each(function() {
				floors.push($(this).val());
			});
			var furl="";
			for(var i=0;i<floors.length;i++){
				furl += floors[i];
			}
			
		    
		    $.ajax({
		        type: "POST",
		        dataType: "json",
		        url: $.getRootPath()+"/rest/ammeterAnalysisService/analysisfloorandtype",
		        data:{"furl":furl,"type":$("#dataAnalysis_ammeter_diferent_floors_same_circuit_data_constrast_select").find("option:selected").val(),"startTime":$("#ammeter_diferent_floors_same_circuit_data_constrast_begin_time").attr("value"),"endTime":$("#ammeter_diferent_floors_same_circuit_data_constrast_end_time").attr("value")},
		        success: function(msg){
		            handle_msg(msg);
		            create_chart();
		        }
		    });
		});
		
		
		//=====================================楼层间用电总量对比==========================================
		$("#dataAnalysis_ammeter_diferent_floors_data_constrast_btn").click(function(){
			 var chart, times, serise1 = [1, 1];
			    $("#anylis_div").slideDown(500);
			    
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
			    
			    function writeTable(msg){
			    	var tables;
			    	var ajaxOb = msg.serize;
			    	for (var i = 0; i < ajaxOb.length; i++) {
			            times = ajaxOb[i].times;
			            var data1 = new Array();
			            for (var j = 0; j < ajaxOb[i].data.length; j++) {
			                data1[j] = ajaxOb[i].data[j] * 1;
			            }
			            
			            tables[i] = {
			                name: ajaxOb[i].name,
			                data: data1
			            };
			            
			        }
			    };
			    
			    
			    
			    
			    
			    function create_chart(){
			        chart = new Highcharts.Chart({
			            chart: {
			                renderTo: 'container',
			                type: 'column',
			                marginRight: 130,
			                marginBottom: 25
			            },
			            title: {
			                text: ' ',
			                x: -20 //center
			            },
			            subtitle: {
			                text: '',
			                x: -20
			            },
			            xAxis: {
			                categories: times
			            },
			            yAxis: {
			                title: {
			                    text: '电量 (度)'
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
			                    '度';
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
			    
				var floors = [];
				//获取复选按钮的值
				$('input[name="floors"]:checked').each(function() {
					floors.push($(this).val());
				});
				var furl="";
				for(var i=0;i<floors.length;i++){
					furl += floors[i];
				}
			    $.ajax({
			        type: "POST",
			        dataType: "json",
			        url: $.getRootPath()+"/rest/ammeterAnalysisService/analysisfloorall",
			        data:{"furl":furl,"startTime":$("#ammeter_diferent_floors_data_constrast_begin_time").attr("value"),"endTime":$("#ammeter_diferent_floors_data_constrast_end_time").attr("value")},
			        success: function(msg){
			            handle_msg(msg);
			            create_chart();
			        }
			    });
			    
			
			
			
		});
		
		
		
		
		
	//楼层间不同电路分析
		$("#dataAnalysis_ammeter_floor_data_constrast_btn").click(function()
		{
			 var chart, times, serise1 = [1, 1];
			    $("#anylis_div").slideDown(500);
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
			                type: 'column',
			                marginRight: 130,
			                marginBottom: 25
			            },
			            title: {
			                text: ' ',
			                x: -20 //center
			            },
			            subtitle: {
			                text: ' ',
			                x: -20
			            },
			            xAxis: {
			                categories: times
			            },
			            yAxis: {
			                title: {
			                    text: '电量 (度)'
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
			                    '度';
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
			        type: "POST",
			        dataType: "json",
			        url: $.getRootPath()+"/rest/ammeterAnalysisService/analysistype",
			        data:{"startTime":$("#ammeter_floor_data_constrast_begin_time").attr("value"),"endTime":$("#ammeter_floor_data_constrast_end_time").attr("value"),"floorNumber":$("#location1").find("option:selected").val()},
			        success: function(msg){
			            handle_msg(msg);
			            create_chart();
			        }
			    });
			    
			//$("#container").load("html/dataAnalysis/ammeterAnalysisOne.html");
		});
		
		$("#dataAnalysis_ammeter_diferent_floors_data_constrast_btn_toexcel").click(function(){
		
			 $.ajax({
			        type: "GET",
			        url: $.getRootPath()+"/rest/ammeterAnalysisService/analysisBuildingTypeToExcel",
			        success: function(msg){
			          //  handle_msg(msg);
			        }
			    });
		});
		
		$("#dataAnalysis_ammeter_diferent_ammeter_number_btn").click(function(){
			
			var deviceNo = $("#dataAnalysis_ammeter_basic_data_look_number_select").find("option:selected").val();
			
			var url = $.getRootPath()+"/rest/deviceDataAnalysis/queryall?deviceTYpe=ammeter&did="+deviceNo;
			$("#container").empty();
			//=============table刷新
			$("#ammeter_table").empty();
			freshChart1(url,deviceNo);
			
			
			
		});
		
		
		$("#dataAnalysis_ammeter_diferent_ammeter_number_btn1").click(function(){
			var deviceNo = $("#dataAnalysis_ammeter_basic_data_look_number_select1").find("option:selected").val();
			var year = $("#dataAnalysis_ammeter_season_data_look_number_year_select1").find("option:selected").val();
			var url = $.getRootPath()+"/rest/deviceDataAnalysis/querymonth?deviceTYpe=ammeter&id="+deviceNo+"&year="+year;
			//=============图表刷新
			$("#container").empty();
			//=============table刷新
			$("#wataer_table").empty();
			freshChart3(url,deviceNo,year);
		});

});


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
				text : '用水情況'
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
					text : '用电量 (/度)'
				}
			},
			legend : {
				enabled : false
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.x + '</b><br/>' + '电量: '
							+ Highcharts.numberFormat(this.y, 1) + ' 度';
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
	  $("#ammeter_table").setTemplateURL("template/ammeter.table.template");
	  // 给模板加载数据
	  $("#ammeter_table").processTemplate(data);
	} 

}
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
	                text: '用电情況明细'
	            },
	            subtitle: {
	                text: document.ontouchstart === undefined ? '点击查看时段详情' : '可随意放大'
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
	                    text: '用量（度）'
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
	                pointStart: Date.UTC(2012, 0, 01),
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
		  $("#ammeter_table").setTemplateURL("template/ammeter.table1.template");
		  // 给模板加载数据
		  $("#ammeter_table").processTemplate(data);
		} 
	    
	    ////////////////////====================
	    

	    
	    
}