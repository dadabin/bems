$(document).ready(function(){
	$("table tbody tr:odd").css("background-color","#dcdcde");
	
    $.ajax({
        type: "GET",
        url: $.getRootPath() + "/rest/meterService/getMeters",
        success: function(xml){
            var $xml = $(xml);
            var index = 1;
            
            var meters = $xml.find("meter");
            meters.each(function(){
                var $this = $(this);
                var meterId = $this.find("meterId").text();
                var type = $this.find("type").text();
                var floorId = $this.find("floorId").text();
                
                var id_meterId = "#water_number" + index;
                var id_type = "#water_type" + index;
                var id_floorId = "#water_location" + index;
                
                var $form_meterId = $("#water_basic_form " + id_meterId);
                var $form_type = $("#water_basic_form " + id_type);
                var $form_floorId = $("#water_basic_form " + id_floorId);
                
                $form_meterId.attr("value", meterId);
                if (type === "总表") {
                    $form_type.attr("value", "zong");
                }
                else {
                    $form_type.attr("value", "fen");
                };
                $form_floorId.attr("value", floorId);
                
                index++;
            });
            
        }
    });
});
