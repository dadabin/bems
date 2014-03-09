

$(document).ready(function(){

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
                type: 'line',
                marginRight: 130,
                marginBottom: 25
            },
            title: {
                text: 'Monthly Average Temperature',
                x: -20 //center
            },
            subtitle: {
                text: 'Source: WorldClimate.com',
                x: -20
            },
            xAxis: {
                categories: times
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
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
        url: $.getRootPath()+"/rest/ammeterAnalysisService/a",
        success: function(msg){
            handle_msg(msg)
            create_chart();
        }
    });
    
    
});

























































































