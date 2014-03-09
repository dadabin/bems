 
//========================================饼状图封装函数====================================================


//================================
[{
	                type: 'pie',
	                name: 'Browser share',
	                data: [
	                    ['Firefox',   45.0],
	                    ['IE',       26.8],
	                    {
	                        name: 'Chrome',
	                        y: 12.8,
	                        sliced: true,
	                        selected: true
	                    },
	                    ['Safari',    8.5],
	                    ['Opera',     6.2],
	                    ['Others',   0.7]
	                ]
	}]
var textValue='Browser market shares at a specific website, 2010';
renderToDiv="container";
function pie_legend(textValue,renderToDiv,seriesValue){
	 var chart;
	    $(document).ready(function() {
	        chart = new Highcharts.Chart({
	            chart: {
	                renderTo: renderToDiv,
	                plotBackgroundColor: null,
	                plotBorderWidth: null,
	                plotShadow: false
	            },
	            title: {
	                text: textValue
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
	            series:seriesValue
	        });
	    });

}



//==========================================Dynamic charts封装图表==================================================

function dynamicCharts(){
	 $(document).ready(function() {
	        Highcharts.setOptions({
	            global: {
	                useUTC: false
	            }
	        });
	        
	        
	        var chart;
	        chart = new Highcharts.Chart({
	            chart: {
	                renderTo: 'container',
	                type: 'spline',
	                marginRight: 10,
	                events: {
	                    load: function() {
	    
	                        // set up the updating of the chart each second
	                        var series = this.series[0];
	                        setInterval(function() {
	                            var x = (new Date()).getTime(), // current time
	                                y = Math.random();
	                            series.addPoint([x, y], true, true);
	                        }, 1000);
	                    }
	                }
	            },
	            title: {
	                text: 'Live random data'
	            },
	            xAxis: {
	                type: 'datetime',
	                tickPixelInterval: 150
	            },
	            yAxis: {
	                title: {
	                    text: 'Value'
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
	                        Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
	                        Highcharts.numberFormat(this.y, 2);
	                }
	            },
	            legend: {
	                enabled: false
	            },
	            exporting: {
	                enabled: false
	            },
	            series: [{
	                name: 'Random data',
	                data: (function() {
	                    // generate an array of random data
	                    var data = [],
	                        time = (new Date()).getTime(),
	                        i;
	    
	                    for (i = -19; i <= 0; i++) {
	                        data.push({
	                            x: time + i * 1000,
	                            y: Math.random()
	                        });
	                    }
	                    return data;
	                })()
	            }]
	        });
	
    });
}

//============================================================================================





//============================================================================================


//=================Area Charts===========================================================================
/*
 * renderToDiv="container"
 * titleText="US and USSR nuclear stockpiles"
 * subtitleText="Source: <a href="http://thebulletin.metapress.com/content/c4120650912x74k7/fulltext.pdf">'+
	                    'thebulletin.metapress.com</a>"
	                 series=   [{
	                name: 'USA',
	                data: [null, null, null, null, null, 6 , 11, 32, 110, 235, 369, 640,
	                    1005, 1436, 2063, 3057, 4618, 6444, 9822, 15468, 20434, 24126,
	                    27387, 29459, 31056, 31982, 32040, 31233, 29224, 27342, 26662,
	                    26956, 27912, 28999, 28965, 27826, 25579, 25722, 24826, 24605,
	                    24304, 23464, 23708, 24099, 24357, 24237, 24401, 24344, 23586,
	                    22380, 21004, 17287, 14747, 13076, 12555, 12144, 11009, 10950,
	                    10871, 10824, 10577, 10527, 10475, 10421, 10358, 10295, 10104 ]
	            }, {
	                name: 'USSR/Russia',
	                data: [null, null, null, null, null, null, null , null , null ,null,
	                5, 25, 50, 120, 150, 200, 426, 660, 869, 1060, 1605, 2471, 3322,
	                4238, 5221, 6129, 7089, 8339, 9399, 10538, 11643, 13092, 14478,
	                15915, 17385, 19055, 21205, 23044, 25393, 27935, 30062, 32049,
	                33952, 35804, 37431, 39197, 45000, 43000, 41000, 39000, 37000,
	                35000, 33000, 31000, 29000, 27000, 25000, 24000, 23000, 22000,
	                21000, 20000, 19000, 18000, 18000, 17000, 16000]
	            }]
	                    
 */
function BasicArea(renderToDiv,titleText,subtitleText,ytitleText,seriesData ){
	
	 var chart;
	    $(document).ready(function() {
	        chart = new Highcharts.Chart({
	            chart: {
	                renderTo: renderToDiv,
	                type: 'area'
	            },
	            title: {
	                text: titleText
	            },
	            subtitle: {
	                text: subtitleText
	            },
	            xAxis: {
	                labels: {
	                    formatter: function() {
	                        return this.value; // clean, unformatted number for year
	                    }
	                }
	            },
	            yAxis: {
	                title: {
	                    text: ytitleText
	                },
	                labels: {
	                    formatter: function() {
	                        return this.value / 1000 +'k';
	                    }
	                }
	            },
	            tooltip: {
	                formatter: function() {
	                    return this.series.name +' produced <b>'+
	                        Highcharts.numberFormat(this.y, 0) +'</b><br/>warheads in '+ this.x;
	                }
	            },
	            plotOptions: {
	                area: {
	                    pointStart: 1940,
	                    marker: {
	                        enabled: false,
	                        symbol: 'circle',
	                        radius: 2,
	                        states: {
	                            hover: {
	                                enabled: true
	                            }
	                        }
	                    }
	                }
	            },
	            series: seriesData
	        });
	    });

	
	
}


//============================================================================================


//========================Column And bar charts====================================================================

function Datadefined(){
	 // On document ready, call visualize on the datatable.
    $(document).ready(function() {
        /**
         * Visualize an HTML table using Highcharts. The top (horizontal) header
         * is used for series names, and the left (vertical) header is used
         * for category names. This function is based on jQuery.
         * @param {Object} table The reference to the HTML table to visualize
         * @param {Object} options Highcharts options
         */
        Highcharts.visualize = function(table, options) {
            // the categories
            options.xAxis.categories = [];
            $('tbody th', table).each( function(i) {
                options.xAxis.categories.push(this.innerHTML);
            });
    
            // the data series
            options.series = [];
            $('tr', table).each( function(i) {
                var tr = this;
                $('th, td', tr).each( function(j) {
                    if (j > 0) { // skip first column
                        if (i == 0) { // get the name and init the series
                            options.series[j - 1] = {
                                name: this.innerHTML,
                                data: []
                            };
                        } else { // add values
                            options.series[j - 1].data.push(parseFloat(this.innerHTML));
                        }
                    }
                });
            });
    
            var chart = new Highcharts.Chart(options);
        }
    
        var table = document.getElementById('datatable'),
        options = {
            chart: {
                renderTo: 'container',
                type: 'column'
            },
            title: {
                text: 'Data extracted from a HTML table in the page'
            },
            xAxis: {
            },
            yAxis: {
                title: {
                    text: 'Units'
                }
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+
                        this.y +' '+ this.x.toLowerCase();
                }
            }
        };
    
        Highcharts.visualize(table, options);
    });

	
}

//============================================================================================




//============================================================================================


//============================================================================================

function linefunction(){
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
}


//============================================================================================





