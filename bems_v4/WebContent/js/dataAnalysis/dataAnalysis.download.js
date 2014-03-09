$("#download_phone_lookss_btn").click(function(){
	var year = $("#download_phone_year_select").find("option:selected").val();
	var month = $("#download_phonea_look_month_select").find("option:selected").val();
	var url = $.getRootPath()+"/rest/templates/phone?year="+year+"&month="+month;
	a  = event.srcElement; 
    a.href=   url;
	
});

$("#download_meter_look_btn").click(function(){
	
	var num = $("#download_meter_look_number_select").find("option:selected").val();
	var url = $.getRootPath()+"/rest/templates/meter?id="+num;
	 a  =   event.srcElement; 
     a.href=   url;
	
});

$("#download_ammeter_look_btn").click(function(){
	var floor = $("#download_ammeter_look_number_select").find("option:selected").val();
	var url = $.getRootPath()+"/rest/templates/ammeter?type=floor&id="+floor;
	a  =   event.srcElement; 
    a.href=   url;
	
});