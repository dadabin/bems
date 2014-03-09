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
    
});

$(document).ready(function(){
    var mainDiv, //所有组态元素所在的div
 btnCreateFunctionDiv, // 创建功能区的按钮
 divs, // 所有创建的功能区div的数组
 cloned_obj, // 克隆的功能区div元素，内存中永远只存在一个
 id_index; // 用于让功能区的div的id属性唯一的下标值
    /*=============================*/
    (function init(){
        fn_dbclick_to_let_input_focus(); // 处理功能区div内部的input元素双击可编辑的函数
        fn_handle_input_blur(); // 处理功能区div内部的input元素失去焦点时候的善后事务的函数
        fn_contextMenu_of_functionDiv(); // 处理功能区div右击菜单的函数
        fn_contextMenu_of_mainDiv(); // 处理整个功能区容器主体div右击菜单的函数
        fn_contextMenu_of_instrumentDiv();
        fn_show_instrument_detail_div();
        change_background_image();
        fn_chang_tabs();
        $("#basicData_zutai_meunu_bar_div").tabs();
        divs = new Array();
    })(); //初始化函数，并且马上让其执行以初始化全局事务
    function fn_dbclick_to_let_input_focus(){
        $(".defaultFunctionDivClass").live("dblclick", function(event){
            var input = $(this).children()[0];
            $(input).attr("readonly", false);
            input.focus();
        });
    }
    function fn_chang_tabs(){
        $(".tabs_a").click(function(){
			$(".floor_number_select")[0].value = 0;
			$(".floor_number_select")[1].value = 0;
            $("#mainDiv").empty();
            $("#mainDiv").css("background-image", "none");
            
        });
    }
    
    function fn_show_instrument_detail_div(){
        $(".defaultInstrumentDivClass").live("mouseover", function(event){
        
            detail_div = $("#show_instrument_detail_div")[0];
            detail_div.style.left = (event.clientX - 170) + "px";
            detail_div.style.top = (event.clientY + 100) + "px";
//			detail_div.style.left = ($(this).left +50)+ "px";
//			detail_div.style.top = $(this).top + "px";
            $(detail_div).show(300);
        });
        
        $(".defaultInstrumentDivClass").live("mouseout", function(event){
            $(detail_div).hide(100);
        })
    }
    
    function show_title(){
        $(".defaultFunctionDivClass").live("mouseover", function(event){
            var title = "  " + $(this).children()[0].value + "  ";
            $(this).attr("title", title);
        })
    }
    
    function change_background_image(){
        $(".floor_number_select").change(function(){
            {
            
                if (this.value == 1) {
                    $("#mainDiv")[0].style.backgroundImage = "url(" + $.getRootPath() + "/images/floor_background.jpg)";
                }
                else 
                    if (this.value == 2) {
                        $("#mainDiv")[0].style.backgroundImage = "url(" + $.getRootPath() + "/images/floor_background1.jpg)";
                    }
            }
        });
    }
    /*=============================*/
    function fn_handle_input_blur(){
        $(".description").live("blur", function(event){
            $(this).attr("readonly", true);
            var length = $(this).val().length;
            $(this).attr("size", length + 1);
            show_title();
        });
    }
    
    /*=============================*/
    function fn_delete($this){
        var id = $this.attr("id");
        var index_array = id.match(/\d+/g);
        divs[index_array] = null;
        //alert(index_array);
        $this.remove();
    }
    
    function fn_edite($this){
        var input = $this.children()[0];
        $(input).attr("readonly", false);
        input.focus();
    }
    
    function fn_copy($this){
        cloned_obj = $this.clone();
    }
    
    function fn_cut($this){
        cloned_obj = $this.clone();
        $this.remove();
    }
    
    function fn_paste(){
    
        var input_length = cloned_obj.children("input").length;
        var img_length = cloned_obj.children("img").length;
        
        cloned_obj.attr("id", "div" + id_index);
        
        if (input_length == 1) {
            fn_handle_created_functionDiv(cloned_obj);
        }
        
        if (img_length == 1) {
            fn_handle_created_instrumentDiv(cloned_obj);
        }
        
        cloned_obj.appendTo(mainDiv);
        divs[id_index] = cloned_obj;
        
        id_index++;
    }
    
    function fn_clear(){
    
        for (var i = 0; i < divs.length; i++) {
            if (typeof(divs[i]) != 'undefined') {
                divs[i].remove();
            }
            id_index = 0;
            window.localStorage.clear();
            divs.length = 0;
        }
    }
    
    function fn_recover(){
        for (var i = 0; i < divs.length; i++) {
            if (typeof(divs[i]) != 'undefined') {
            
                var input_length = divs[i].children("input").length;
                var img_length = divs[i].children("img").length;
                
                if (input_length == 1) {
                    fn_handle_created_functionDiv(divs[i]);
                }
                else 
                    if (img_length == 1) {
                        fn_handle_created_instrumentDiv(divs[i]);
                    }
                
                divs[i].appendTo(mainDiv);
                id_index++;
            }
            else {
                //to do :no item here 
            }
            
            
        }
    }
    /*=============================*/
    function fn_contextMenu_of_functionDiv(){
        $.contextMenu({
            selector: '.defaultFunctionDivClass',
            callback: function(key, options){
                if (key === "delete") {
                    fn_delete($(this));
                }
                else 
                    if (key === "edit") {
                        fn_edite($(this));
                    }
                    else 
                        if (key === "copy") {
                            fn_copy($(this));
                        }
                        else 
                            if (key === "cut") {
                                fn_cut($(this));
                            }
            },
            items: {
                "edit": {
                    name: "编辑",
                    icon: "edit"
                },
                "cut": {
                    name: "剪切",
                    icon: "cut"
                },
                "copy": {
                    name: "复制",
                    icon: "copy"
                },
                "delete": {
                    name: "删除",
                    icon: "delete"
                },
                "sep1": "---------",
                "quit": {
                    name: "退出",
                    icon: "quit"
                }
            }
        });
    }
    
    /*=============================*/
    function fn_contextMenu_of_instrumentDiv(){
        $.contextMenu({
            selector: '.defaultInstrumentDivClass',
			trigger:'right',
            callback: function(key, options){
                if (key === "delete") {
                    fn_delete($(this));
                }
                else 
                    if (key === "copy") {
                        fn_copy($(this));
                    }
                    else 
                        if (key === "cut") {
                            fn_cut($(this));
                        }
            },
            items: {
				"regist":{
					name:"仪表注册",
					icon: "edit",
					items:{
						number: {
		                name: "仪表编号", 
		                type: 'text', 
                		},
						sep1: "---------",
						location:{
							  name: "仪表地理位置",
							 type: 'select', 
			                 options: {1: '一楼', 2: '二楼', 3: '三楼',4:'四楼',5:'五楼',6:'六楼',7:'地下室',8:'楼外',9:'==请选择仪表地理位置=='}, 
			                 selected: 9,
							
						},
						sep2: "---------",
						type:{
							 name: "仪表地类别",
							 type: 'select', 
			                 options: {1: '设备电路', 2: '空调电路', 3: '照明电路',4:'水表总表',5:'水表分表',6:'==请选择仪表类别=='}, 
			                 selected: 6,
							
						},
						sep3: "---------",
						area2: {
			                name:"备注", 
			                type: 'textarea',
							height: 50
            				},
						sep4: "---------",
						registe:{
							name: "注册"
		               		
						}
						
					}
					
				},
                "cut": {
                    name: "剪切",
                    icon: "cut"
                },
                "copy": {
                    name: "复制",
                    icon: "copy"
                },
                "delete": {
                    name: "删除",
                    icon: "delete"
                },
                "sep1": "---------",
                "quit": {
                    name: "退出",
                    icon: "quit"
                }
            }
        });
    }
    
    /*=============================*/
    function fn_contextMenu_of_mainDiv(){
        $.contextMenu({
            selector: '#mainDiv',
            callback: function(key, options){
                if (key === "clear") {
                    fn_clear();
                }
                else 
                    if (key === "paste") {
                        fn_paste($(this));
                    }
                    else 
                        if (key === "recover") {
                            fn_recover();
                        }
                
                
            },
            items: {
                "paste": {
                    name: "粘贴",
                    icon: "paste"
                },
                "clear": {
                    name: "清空",
                    icon: "delete"
                },
                "recover": {
                    name: "恢复",
                    icon: "recover"
                },
                "sep1": "---------",
                "quit": {
                    name: "退出",
                    icon: "quit"
                }
            }
        });
    }
    
    /*=============================*/
   
    
    /*=============================*/
    
    btnCreateFunctionDiv = $("#createFunctionDiv");
    var i_arr_color = 0;
    btnCreateFunctionDiv.click(function(){
        var arr_color = ["#808040", "#0080ff", "#26341b", "#ca9680", "#dbd239"];
        
        var str = "<div><input class='description' type='text' placeholder='请输入功能区名称' readonly maxlength='7' size='10'/></div>";
        var functionDiv = fn_create_div(str);
        functionDiv.addClass("defaultFunctionDivClass");
        var index = i_arr_color % 5;
        console.log("index=" + index);
        functionDiv.css("background-color", arr_color[index]);
        i_arr_color++;
        fn_handle_created_functionDiv(functionDiv);
    });
    
    $("#instument_type_div_shebei_a").click(function(){
        var str = "<div><img src='images/dian3.png'/></div>";
        var ameter_Div = fn_create_div(str);
        ameter_Div.addClass("defaultInstrumentDivClass");
        ameter_Div.addClass("yibiao");
        fn_handle_created_instrumentDiv(ameter_Div);
    });
    
    $("#instument_type_div_kongtiao_a").click(function(){
        var str = "<div><img src='images/dian2.png'/></div>";
        var ameter_Div = fn_create_div(str);
        ameter_Div.addClass("defaultInstrumentDivClass");
        ameter_Div.addClass("yibiao");
        fn_handle_created_instrumentDiv(ameter_Div);
    });
    
    $("#instument_type_div_zhaoming_a").click(function(){
        var str = "<div><img src='images/dian1.png'/></div>";
        var ameter_Div = fn_create_div(str);
        ameter_Div.addClass("defaultInstrumentDivClass");
        ameter_Div.addClass("yibiao");
        fn_handle_created_instrumentDiv(ameter_Div);
    });
    
    $("#instument_type_div_shuibiao_a").click(function(){
        var str = "<div><img src='images/shui1.png'/></div>";
        var ameter_Div = fn_create_div(str);
        ameter_Div.addClass("defaultInstrumentDivClass");
        ameter_Div.addClass("yibiao");
        fn_handle_created_instrumentDiv(ameter_Div);
    });
    /*=============================*/
    id_index = 0;
    mainDiv = $("#mainDiv");
    function fn_create_div(str){
        var div = $(str);
        div.appendTo(mainDiv);
        divs[id_index] = div;
        return div;
    }
    /*=============================*/
    function fn_handle_created_functionDiv($functionDiv){
    
    
        $functionDiv.attr("id", "div" + id_index);
        id_index++;
        
        $functionDiv.draggable({
            cursor: "move",
            cursorAt: {
                top: 56,
                left: 56
            },
            containment: mainDiv,
            scroll: false
        });
        
        $functionDiv.resizable({
            maxHeight: 500,
            maxWidth: 700,
            minHeight: 20,
            minWidth: 87
        });
    }
    
    function fn_handle_created_instrumentDiv($functionDiv){
    
        $functionDiv.attr("id", "div" + id_index);
        id_index++;
        
        $functionDiv.draggable({
            cursor: "move",
            cursorAt: {
                top: 10,
                left: 10
            },
            containment: mainDiv,
            scroll: false
        });
    }
    /*=============================*/
    
    $("#save_functionDivs_btn").click(function(){
        var styles = new Array();
        var texts = new Array();
        var img_srcs = new Array();
        var obj_detaile;
        var str_detaile;
        
        for (var i = 0; i < divs.length; i++) {
            styles[i] = divs[i].get(0).style;
            if (divs[i].children("input").length == 1) {
                texts[i] = divs[i].children("input")[0].value;
            };
            if (divs[i].children("img").length == 1) {
                img_srcs[i] = divs[i].children("img")[0].src;
            };
            //console.log(i);
        }
        
        obj_detaile = {
            "styles": styles,
            "texts": texts,
            "img_srcs": img_srcs
        };
        
        str_detaile = JSON.stringify(obj_detaile);
        
        //        $.ajax({
        //            type: "POST",
        //            url: $.getRootPath() + "/rest/TestFormSubmitService/save_functionDivs",
        //            data: {
        //                "str_detaile": str_detaile
        //            },
        //            success: function(msg){
        //                alert("布局图已成功保存！");
        //            }
        //        });
        if (window.localStorage) {
            window.localStorage.clear();
			//divs.length = 0;
            window.localStorage.setItem("str_detaile", str_detaile);
            alert("布局图已成功保存！");
        }
        
        
    });
    
    $("#look_functionDivs_btn").click(function(){
    
    
    
        //        $.ajax({
        //            type: "POST",
        //            url: $.getRootPath() + "/rest/TestFormSubmitService/get_functionDivs",
        //            dataType: "json",
        //            success: function(msg){
        //                look_zutai(msg);
        //            }
        //        });
        var style_str = null;
        if (window.localStorage) {
            style_str = window.localStorage.getItem("str_detaile");
        }
        look_zutai(JSON.parse(style_str));
        
    });
    
    function look_zutai(msg){
        var texts = msg.texts;
        var styles = msg.styles;
        var img_srcs = msg.img_srcs;
        
        for (var i = 0; i < styles.length; i++) {
            if (typeof(texts[i]) != "undefined") {
                look_div_handle_function_div(texts[i], styles[i]);
            }
            else 
                if (typeof(img_srcs[i]) != "undefined") {
                    look_div_handle_instrument_div(img_srcs[i], styles[i]);
                }
            
        };
            }
    
    function look_div_handle_style($div, styles_i){
        var div_style = $div.get(0).style;
        
        div_style.position = styles_i.position;
        div_style.left = styles_i.left;
        div_style.top = styles_i.top;
        div_style.width = styles_i.width;
        div_style.height = styles_i.height;
        div_style.backgroundColor = styles_i.backgroundColor;
        
    }
    
    function look_div_handle_function_div(texts_i, styles_i){
        var str = "<div><input class='description' type='text' placeholder='请输入功能区名称' readonly maxlength='7' size='10'/></div>";
        var functionDiv = fn_create_div(str);
        var input = $(functionDiv.children()[0]);
        var length = input.val().length;
        functionDiv.addClass("defaultFunctionDivClass");
        
        input.val(texts_i);
        input.attr("size", length + 1);
        
        look_div_handle_style(functionDiv, styles_i);
        //fn_handle_created_functionDiv(functionDiv);
        
        id_index++;
    }
    
    function look_div_handle_instrument_div(img_srcs_i, styles_i){
        var str = "<div><img src='images/shui1.png'/></div>";
        var instrumentDiv = fn_create_div(str);
        var img = $(instrumentDiv.children()[0]);
        instrumentDiv.addClass("defaultInstrumentDivClass");
        img.attr("src", img_srcs_i);
        
        look_div_handle_style(instrumentDiv, styles_i);
        //fn_handle_created_instrumentDiv(instrumentDiv);
        
        id_index++;
    }
    
    
    
    
    
    
    
});
