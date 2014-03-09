// Side Navigation Menu Slide

$(document).ready(function(){

    (function($){
        $.getRootPath = function(){
            var strFullPath = window.document.location.href;
            var strPath = window.document.location.pathname;
            var pos = strFullPath.indexOf(strPath);
            var prePath = strFullPath.substring(0, pos);
            var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
            //alert(prePath+postPath);
            return (prePath + postPath);
        };
    })(jQuery);
    
    /*=======================================*/
    $("#nav > li > a.collapsed + ul").slideToggle("medium");
    
    $("#nav > li > a").click(function(){
        var _this = this;
        $("#nav > li > a").each(function(){
            if (this != _this) {
                $(this).removeClass("expanded").addClass("collapsed").find("+ ul").slideUp("medium");
                //alert(11);
            }
        });
        
        $(this).toggleClass("expanded").toggleClass("collapsed").find("+ ul").slideToggle("medium");
        
        var first_nav = $(this).html();
        $("#first_nav").html(first_nav);
    });
    
    $("ul.navigation li").click(function(){
        $("ul.navigation li").removeClass("selected");
        $(this).addClass("selected");
        
        var seconde_nav = $(this).text();
        $("#seconde_nav").html(seconde_nav);
		$(".conthead h2").html(seconde_nav);
    });
    
});


// Toolbox Pulldown

$(document).ready(function(){
    $(".toolboxdrop").click(function(){
        if ($("#openCloseIdentifier").is(":hidden")) {
            $("#slider").animate({
                marginTop: "-150px"
            }, 500);
            $("#openCloseIdentifier").show();
			var src = $.getRootPath() + "/images/icon_expand_grey.png";
			$("#toolbox img").attr("src",src);
        }
        else {
            $("#slider").animate({
                marginTop: "0px"
            }, 500);
            $("#openCloseIdentifier").hide();
			var src = $.getRootPath() + "/images/icon_expand_grey1.png";
			$("#toolbox img").attr("src",src);
        }
    });
});

// Select all checkboxes

$(document).ready(function(){
    $("#checkboxall").click(function(){
        var checked_status = this.checked;
        $("input[name=checkall]").each(function(){
            this.checked = checked_status;
        });
    });
});

/*===================================*/
$(document).ready(function(){
    /*================数据分析===================*/
    $("ul.navigation #data_anaylis_water").click(function(){
        $("#container_of_index_page .contentbox").load("html/dataAnalysis/dataAnalysis.water.html");
    });
    
    $("ul.navigation #data_anaylis_ammeter").click(function(){
        $("#container_of_index_page .contentbox").load("html/dataAnalysis/dataAnalysis.ammeter.html");
    });
    
    $("ul.navigation #data_anaylis_telephone").click(function(){
        $("#container_of_index_page .contentbox").load("html/dataAnalysis/dataAnalysis.telephone.html");
    });
    
    $("ul.navigation #data_anaylis_download").click(function(){
        $("#container_of_index_page .contentbox").load("html/dataAnalysis/dataAnalysis.download.html");
    });
    
    /*===============数据收集====================*/
    $("ul.navigation #data_collection_water").click(function(){
        $("#container_of_index_page .contentbox").load("html/dataCollection/dataCollection.water.html");
    });
    
    $("ul.navigation #data_collection_ammeter").click(function(){
        $("#container_of_index_page .contentbox").load("html/dataCollection/dataCollection.ammeter.html");
    });
    
    $("ul.navigation #data_collection_telephone").click(function(){
        $("#container_of_index_page .contentbox").load("html/dataCollection/dataCollection.telephone.html");
    });
    
    
    /*================用户管理===================*/
    $("ul.navigation #user_management_emergency").click(function(){
        $("#container_of_index_page .contentbox").load("html/userManagement/userManagement.emergency.html");
    });
    
    $("ul.navigation #user_management_add").click(function(){
        $("#container_of_index_page .contentbox").load("html/userManagement/userManagement.add.html");
    });
    
    $("ul.navigation #user_management_look").click(function(){
        $("#container_of_index_page .contentbox").load("html/userManagement/userManagement.look.html");
    });
    
    $("ul.navigation #url_management").click(function(){
    	$("#container_of_index_page .contentbox").load("html/userManagement/urlManagement.html");
    });
    
    /*================节能减排===================*/
 
    $("ul.navigation #egergy_save_making").click(function(){
        $("#container_of_index_page .contentbox").load("html/energySave/energySave.making.html");
    });
    
    $("ul.navigation #egergy_save_look").click(function(){
        $("#container_of_index_page .contentbox").load("html/energySave/energySave.makePlane.html");
    });
    
    /*================基本信息维护===================*/
    $("ul.navigation #basic_data_water").click(function(){
        $("#container_of_index_page .contentbox").load("html/basicDataManagement/basicDataManagement.water.html");
    });
    
    $("ul.navigation #basic_data_ammeter").click(function(){
        $("#container_of_index_page .contentbox").load("html/basicDataManagement/basicDataManagement.ammeter.html");
    });
    
    $("ul.navigation #basic_data_telephone").click(function(){
        $("#container_of_index_page .contentbox").load("html/basicDataManagement/basicDataManagement.telephone.html");
    });
    
    $("ul.navigation #basic_data_floor").click(function(){
        $("#container_of_index_page .contentbox").load("html/basicDataManagement/basicDataManagement.floor.html");
    });
    
    $("ul.navigation #basic_data_zu_tai").click(function(){
        $("#container_of_index_page .contentbox").load("html/basicDataManagement/basicDataManagement.zu_tai.html");
    });
    
    //===========================ShortCuts快捷键=============================//
    $("#Shortcuts_About").click(function(){
        $("#container_of_index_page .contentbox").load("html/Shortcuts/Shortcuts_About.html");
    });
    
    $("#Shortcuts_Calendar").click(function(){
        $("#container_of_index_page .contentbox").load("html/Shortcuts/Shortcuts_Calendar.html");
    });
    
    $("#Shortcuts_Contacts").click(function(){
        $("#container_of_index_page .contentbox").load("html/Shortcuts/Shortcuts_Contacts.html");
    });
    
    $("#Shortcuts_Create").click(function(){
        $("#container_of_index_page .contentbox").load("html/energySave/energySave.making.html");
    });
    
    $("#Shortcuts_Notice").click(function(){
        $("#container_of_index_page .contentbox").load("html/Shortcuts/Shortcuts_Notice.html");
    });
    
    $("#Shortcuts_Users").click(function(){
        $("#container_of_index_page .contentbox").load("html/Shortcuts/Shortcuts_Users.html");
    });
	
	 $("#Shortcuts_About").click(function(){
        $("#container_of_index_page .contentbox").load("html/Shortcuts/Shortcuts_About.html");
    });
    
});

//=================================消息提示
$(document).ready(function(){
	getCurrentUserInfo();
	GetMsgCount();
     window.setInterval("GetMsgCount()", 1000*60*5); //1分钟刷新1次
});
//============================获取当前用户未处理消息数
function GetMsgCount() {
	var url = $.getRootPath()+"/rest/informService/noDeal";
    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (data) {
        	var myobj = eval(data);
        	var a = myobj.inform.length;
        	
            if (a > 0) {
            	
            	$.messager.lays(200, 200);
            	$.messager.anim('fade', 1000);
                $.messager.show('<font color=red>消息提醒</font>', '目前尚有<font color=red>' + a + '</font>消息未处理，请相关处理！<a href=# target=_blank>进入处理</a>', 2000);
                showMSG(a);
                
            }
        }
    });
}
//================获取登陆这信息
function getCurrentUserInfo(){
	var url = $.getRootPath()+"/rest/staffservice/currentUser";
	var myobj;
    $.ajax({
        type: "get",
        dataType: "json",
        url: url,
        success: function (data) {
        	var a = eval(data);
        	myobj = a.role;
        	var role;
        	if(myobj==1){
        		role = "中层管理者";
        	}else{
        		role = "总务人员";
        	}
        	$("#login_user_infor").text("");// 清空数据
        	$("#login_user_infor").append(role);
        }
    });
}

//=====退出，同时删除session
function logout(){
	if (confirm("您确定要退出系统吗？"))
		//删除session
		var url = $.getRootPath()+"/rest/staffservice/logout";
    	$.ajax({
	        type: "get",
	        dataType: "xml",
	        url: url,
	        success: function (data) {
	        	top.location = "login.html";
	        }
    	});
     return false;
}
//============

function showMSG(a){
	
//	window.open ('html/Shortcuts/msg.html', 'newwindow', 'height=100, width=400, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no'); 
}














