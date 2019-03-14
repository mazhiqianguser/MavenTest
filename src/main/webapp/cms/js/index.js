layui.config({
    base: 'js/',
    version: new Date().getTime()
}).use(['element','layer','form','navbar','tab','element'],function(exports){
    var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块
    var $ = layui.jquery;        //调用jq方法
    var form = layui.form;
    var layer = layui.layer;
    var navbar = layui.navbar();
    var h = $(window).height();
    var w = $(window).width();
    var data;
    var userName;
    //实例化element
    $("body").css("height",h+"px");
    $(".title_ul").css("width",(w-86)+"px");
    $("iframe").css("height",h-103+"px");
    $("iframe").css("width",w+"px");
    //取出用户信息列入用户名
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if( temp[0] == "username"){
            userName = temp[1]
        }
    };
    $("#logOut").click(function () {
        document.cookie = "username="+ "" +";password="+ "" +";";
        window.location.href = './login.html';
    });
    //配置用户信息，并发起渲染
    $("#username").html(userName);
    if( userName == "" || userName == null || userName == undefined){
        window.location.href = './login.html';
    }
    $('#admin-navbar-side').slimScroll({
        width:"200px",
        height: "350px",//高度
        size: '3px',//大小
        position: 'right',//位置
        color: '#666',//颜色
    });
    $('#admin-navbar-side1').slimScroll({
        width:"200px",
        height: "350px",//高度
        size: '3px',//大小
        position: 'right',//位置
        color: '#666',//颜色
    });
    $('#admin-navbar-side2').slimScroll({
        width:"200px",
        height: "350px",//高度
        size: '3px',//大小
        position: 'right',//位置
        color: '#666',//颜色
    });
    $(".slimScrollDiv").eq(0).css({
        "width": "200px",
        "position": "absolute",
        "top": "61px",
        "left": "20px",
        "display":"none"
    });
    $(".slimScrollDiv").eq(1).css({
        "width": "200px",
        "position": "absolute",
        "top": "61px",
        "left": "220px",
        "display":"none"
    });
    $(".slimScrollDiv").eq(2).css({
        "width": "200px",
        "position": "absolute",
        "top": "61px",
        "left": "420px",
        "display":"none"
    });
    $.ajax({
        url:getRootPath_web()+"getUserPerm ",
        type: "POST",
        dataType: "json",
        data:{
            username:userName,
        },
        success:function (data) {
            //将一位数组改变成树状结构图
            function treeObj(originObj){
                //对象深拷贝
                var obj ={};
                for (key in originObj){
                    var val = originObj[key];
                    obj[key] = typeof val === 'object' ? arguments.callee(val):val;
                }
                //对象新增children键值，用于存放子树
                obj['children'] = [];
                return obj;
            }
            //data：带转换成树形结构的对象数组
            //attributes：对象属性
            function toTreeData (data, attributes) {
                var resData = data;
                var tree = [];
                //找寻根节点 默认返回的parentId为空后期可以更改判断
                for (var i = 0; i < resData.length; i++) {
                    if ( resData[i][attributes.parentId] === ''|| resData[i][attributes.parentId] === null ||resData[i][attributes.parentId] == 0) {
                        tree.push( treeObj(resData[i]) );
                        resData.splice(i, 1);
                        i--;
                    }
                }
                run(tree);
                //找寻子树
                function run(chiArr) {
                    if (resData.length !== 0) {
                        for (var i = 0; i < chiArr.length; i++) {
                            for (var j = 0; j < resData.length; j++) {
                                if (chiArr[i][attributes.id] === resData[j][attributes.parentId]){
                                    var obj = treeObj(resData[j]);
                                    chiArr[i].children.push(obj);
                                    resData.splice(j, 1);
                                    j--;
                                }
                            }
                            run(chiArr[i].children);
                        }
                    }
                }
                return tree;
            }
            //排序
            var arr = data.data;
            var compare = function (obj1, obj2) {
                var val1 = obj1.sort;
                var val2 = obj2.sort;
                if (val1 < val2) {
                    return -1;
                } else if (val1 > val2) {
                    return 1;
                } else {
                    return 0;
                }
            };
            data = arr.sort(compare);
            // 属性配置信息
            var attributes = {
                id: 'id',
                parentId: 'parentId',
            };
            var treeData = toTreeData(data, attributes);
            //侧面菜单传值并循环
            $.ajax({
                url:getRootPath_web() + "/config/select",
                type:'get',
                dataType:'json',
                success:function(data) {
                    if( data.code == 200){
                        var str = '';
                        var str1 = '';
                        $("#title").text(data.data.project_name);
                        $("#title").attr("dataid",data.data.id);
                        document.title = data.data.project_name;
                        //var showlist = $("<ul class='layui-nav-child'></ul>");
                        showall(treeData);
                        //循环递归显示数据数据
                        function showall(menu_list,parent) {
                            $.each(menu_list, function(index,val) {
                                if ( val.id == data.data.menu_bar1_id ) {
                                    srt= '';
                                    //循环人员列表
                                    if ( val.children.length > 0 ) {
                                        str += "<li class='layui-nav-item' >"
                                        str += "<i class='layui-icon' style='float: left;margin: 0 5px;'>" + val.icon + "</i>"
                                        str += "<a href='javascript:;'  style='float: left;padding: 0;'><sapn style='margin-right: 20px;'>" + val.title +"</a>"
                                        str += "<ul  class='layui-nav-child'>"
                                        for( var i = 0 ; i < val.children.length;i++){
                                            str += "<li class='site_active' data-title="+ val.children[i].title +"   data-url="+ val.children[i].href +" data-id="+ val.children[i].id +"  >"
                                            str += "<a  style='padding: 0 10px;overflow: hidden'>"
                                            str += "<i class='layui-icon' style='margin-right: 5px;'>" + val.children[i].icon + "</i>"
                                            str += "<sapn  style='padding-right: 5px;' >" + val.children[i].title +"</sapn>"
                                            str += "</a>"
                                            str += "</li>";
                                        }
                                        str +=  "</ul >"
                                        str += "</li>";
                                    } else {
                                        str += "<li class='layui-nav-item site_active' data-title="+ val.title +"  data-url="+ val.href +" data-id="+ val.id +"  >"
                                        str += "<i class='layui-icon' style='float: left;margin: 0 5px;'>" + val.icon + "</i>"
                                        str +=  "<a style='float: left;padding: 0;'><sapn>" + val.title+"</sapn></a>"
                                        str += "</li>";
                                    }
                                    $("#leftNav").append(str);
                                    str =''
                                }else if ( val.id == data.data.menu_bar2_id ) {
                                    str = '';
                                    //循环人员列表
                                    if ( val.children.length > 0 ) {
                                        str += "<li class='layui-nav-item' >"
                                        str += "<i class='layui-icon' style='float: left;margin: 0 5px;'>" + val.icon + "</i>"
                                        str += "<a href='javascript:;'  style='float: left;padding: 0;'><sapn style='margin-right: 20px;'>" + val.title +"</a>"
                                        str += "<ul class='layui-nav-child'>"
                                        for( var i = 0 ; i < val.children.length;i++){
                                            str += "<li class='site_active' data-title="+ val.children[i].title +"   data-url="+ val.children[i].href +" data-id="+ val.children[i].id +"  >"
                                            str += "<a  style='padding: 0 10px;overflow: hidden'>"
                                            str += "<i class='layui-icon' style='margin-right: 5px;'>" + val.children[i].icon + "</i>"
                                            str += "<sapn  style='padding-right: 5px;' >" + val.children[i].title +"</sapn>"
                                            str += "</a>"
                                            str += "</li>";
                                        }
                                        str +=  "</ul>"
                                        str += "</li>";
                                    } else {
                                        str += "<li class='layui-nav-item site_active' data-title="+ val.title +"  data-url="+ val.href +" data-id="+ val.id +"  >"
                                        str += "<i class='layui-icon' style='float: left;margin: 0 5px;'>" + val.icon + "</i>"
                                        str +=  "<a style='float: left;padding: 0;'><sapn>" + val.title+"</sapn></a>"
                                        str += "</li>";
                                    }
                                    $("#leftNav").append(str);
                                    str = '';
                                }else{
                                    showall(val.children);
                                }

                                //初始化动态元素，一些动态生成的元素如果不设置初始化，将不会有默认的动态效果
                                element.render();
                                $("#leftNav li").hover(function () {
                                    var chliLay = $(this).children("a").children(".layui-nav-more");
                                    chliLay.addClass("layui-nav-mored");
                                    $(this).children("ul").addClass("layui-show")
                                },function () {
                                    var chliLay = $(this).children("a").children(".layui-nav-more");
                                    chliLay.removeClass("layui-nav-mored");
                                    $(this).children("ul").removeClass("layui-show")
                                })
                                // if ( val.id == data.data.menu_bar2_id ) {
                                //     //循环人员列表
                                //     if (val.children.length > 0) {
                                //         var li = $("<li></li>");
                                //         var li_two = $("<li></li>");
                                //         li.append(
                                //             "<li class='layui-nav-item'>"
                                //             + "<i class='layui-icon' style='float: left;margin: 0 5px;'>" + val.icon + "</i>"
                                //             + "<a >" + val.title + "</a>"
                                //             + "</li>"
                                //         ).append("<ul></ul>").appendTo(parent);
                                //         //递归显示
                                //         showall(val.children, $(li).children().eq(1));
                                //     } else {
                                //         $("<li></li>").append("<li class='layui-nav-item'>"
                                //             + "<i class='layui-icon' style='float: left;margin: 0 5px;'>" + val.icon + "</i>"
                                //             + "<a >" + val.title + "</a>"
                                //             + "</li>").appendTo(parent);
                                //     }
                                // }
                            });
                        };
                    }else{
                        layer.msg("查询失败");
                    }

                    console.log( data )
                },
                error : function() {
                    layer.msg("服务器异常");
                }
            });
            //设置navbar
            navbar.set({
                spreadOne: true,
                elem: '#admin-navbar-side',
                cached: true,
                data:treeData
                // url: 'data/root.json'
            });
            //渲染navbar
            navbar.render();
            //监听点击事件
            navbar.on('click(side)', function (data) {
                tab.tabAdd(data.field);
            });
        }
    })
    //关闭当前所有弹窗
    $('body').delegate('.close', 'click', function(e){
        e.stopPropagation();
        layer.closeAll()
    });
    // $(".add").hover(function(){
    //     $(".admin-navbar-side").show();
    //     $(".slimScrollDiv").eq(0).show();
    // },function(){
    //      $(".admin-navbar-side").hide();
    //     $(".slimScrollDiv").eq(0).hide();
    // });
    $(".add").click(function(){
        $(".admin-navbar-side").show();
        $(".slimScrollDiv").eq(0).show();
    });
    $(".admin-navbar-side").hover(function(){
        $(this).show();
        $(".slimScrollDiv").eq(0).show();
        $("#admin-navbar-side > ul > li").mouseover(function(){
            $("#admin-navbar-side1").show();
            $(".slimScrollDiv").eq(1).show();
            $("#admin-navbar-side1").attr("wid",$(this).attr("id"));
            $("#admin-navbar-side1 > ul").hide();
            $("#admin-navbar-side2").hide();
            $(".slimScrollDiv").eq(2).hide();
            var side_id = $(this).attr("id");
            $("#admin-navbar-side1 > ul ").each(function(){
                var the = $(this);
                var side_parentid = the.attr("parentid");
                if( side_id == side_parentid ){
                    $("#admin-navbar-side1").show();
                    $(".slimScrollDiv").eq(1).show();
                    the.show().siblings().hide();
                    return false;
                }else{
                     $("#admin-navbar-side1").hide();
                    $(".slimScrollDiv").eq(1).hide();
                }
            })
        });
    },function(){
        $(this).hide();
        $("#admin-navbar-side1").hide();
        $("#admin-navbar-side2").hide();
        $(".slimScrollDiv").eq(0).hide();
        $(".slimScrollDiv").eq(2).hide();
        $(".slimScrollDiv").eq(1).hide();
    });
    $("#admin-navbar-side1 ul").hover(function(){
        $("#admin-navbar-side").show();
        $("#admin-navbar-side1").show();
        $(".slimScrollDiv").eq(0).show();
        $(".slimScrollDiv").eq(1).show();
    });
    $("#admin-navbar-side1").hover(function(){
        $("#admin-navbar-side").show();
        $("#admin-navbar-side1").show();
        $(".slimScrollDiv").eq(0).show();
        $(".slimScrollDiv").eq(1).show();
        $("#admin-navbar-side1 ul li").hover(function(){
            var side_id = $(this).attr("id");
            var sidePid = $(this).attr("parentid");
            $("#admin-navbar-side2").attr("wid",$(this).attr("id"));
            $("#admin-navbar-side2 > ul ").each(function(){
                var the = $(this);
                var side_parentid = the.attr("parentid");
                if( side_id == side_parentid ){
                    $("#admin-navbar-side2").show();
                    $(".slimScrollDiv").eq(2).show();
                    the.show().siblings().hide();
                    return false;
                }else{
                    $("#admin-navbar-side2").hide();
                    $(".slimScrollDiv").eq(2).hide();
                }
            })
            $("#admin-navbar-side > ul > li ").each(function(){
                var the = $(this);
                var sideId = the.attr("id");
                if( sidePid == sideId ){
                    the.addClass("pop").siblings().removeClass("pop")
                }
            })
        });
    },function(){
        $("#admin-navbar-side").hide();
        $("#admin-navbar-side1").hide();
        $("#admin-navbar-side2").hide();
        $(".slimScrollDiv").eq(0).hide();
        $(".slimScrollDiv").eq(1).hide();
        $(".slimScrollDiv").eq(2).hide();
        $("#admin-navbar-side > ul > li ").removeClass("pop")
    });
    $("#admin-navbar-side2").hover(function(){
        $("#admin-navbar-side").show();
        $("#admin-navbar-side1").show();
        $("#admin-navbar-side2").show();
        $(".slimScrollDiv").eq(0).show();
        $(".slimScrollDiv").eq(1).show();
        $(".slimScrollDiv").eq(2).show();
        $("#admin-navbar-side2 ul li").hover(function(){
            var sidePid = $(this).attr("parentid");
            $(this).parent().attr("parentid",$(this).attr("parentid"));
            $("#admin-navbar-side1 > ul > li ").each(function(){
                var the = $(this);
                var sideId = the.attr("id");
                if( sidePid == sideId ){
                    the.addClass("pop").siblings().removeClass("pop")
                }
            })
        });
    },function(){
        $("#admin-navbar-side").hide();
        $("#admin-navbar-side1").hide();
        $("#admin-navbar-side2").hide();
        $(".slimScrollDiv").eq(0).hide();
        $(".slimScrollDiv").eq(1).hide();
        $(".slimScrollDiv").eq(2).hide();
        $("#admin-navbar-side1 > ul > li ").removeClass("pop")
    });
    //主题颜色的下拉框被切换的事件
    $('.skin_yes').on('click', function(){
        var selectVal = $("input[name='skin']:checked").val();
        if( selectVal == '0'){
            // 存储
            localStorage.setItem("css_style", "2");
            document.getElementById('index_css').href = 'style/index.css';
            $(".left_title").attr("src","images/head_bg-bule_left.png");
            $(".right_title").attr("src","images/head_bg-bule_right.png");
            window.location.reload();

        }else{
            // 存储
            localStorage.setItem("css_style", "1");
            document.getElementById('index_css').href = 'style/index_one.css';
            $(".left_title").attr("src","images/head_bg-balck_left.png");
            $(".right_title").attr("src","images/head_bg-balck_right.png");
            window.location.reload();
        }
    });
//		    $(".layui-tab-title li").hover(function(){
//		    	$(this).children().css({
//		    		"color":"#222!important";
//		    	})
//		    },function(){
//		    	$(this).children().css({
//		    		"color":"#222!important";
//		    	})
//		    })
    var style = localStorage.getItem("css_style");
    if( style ==  "1" ){
        document.getElementById('index_css').href = 'style/index_one.css';
        $(".left_title").attr("src","images/head_bg-balck_left.png");
        $(".right_title").attr("src","images/head_bg-balck_right.png");
        $("#radio_skin input").eq(1).attr("checked",true);
        form.render();
    }else{
        document.getElementById('index_css').href = 'style/index.css';
        $(".left_title").attr("src","images/head_bg-bule_left.png");
        $(".right_title").attr("src","images/head_bg-bule_right.png");
        $("#radio_skin input").eq(1).attr("checked",false);
        form.render();
    }
    var active = {
        //在这里给active绑定几项事件，后面可通过active调用这些事件
        tabAdd : function (url, id, name) {
            //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
            //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
            element.tabAdd('demo', {
                title: name,
                content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '" style="width:100%;height:99%;"></iframe>',
                id: id //规定好的id
            })
            element.tabAdd('demoul', {
                title: name,
                content: '<li class="layui-this" layid="' + id + '"><cite></cite></li>',
                id: id //规定好的id
            })
            //CustomRightClick(id); //给tab绑定右击事件
            FrameWH();  //计算ifram层的大小
        },
        tabChange: function (id) {
            //切换到指定Tab项
            element.tabChange('demo', id); //根据传入的id传入到指定的tab项
        },
        tabDelete: function (id) {
            element.tabDelete("demo", id);//删除
        }
        , tabDeleteAll: function (ids) {//删除所有
            $.each(ids, function (i,item) {
                element.tabDelete("demo", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
            })
        }
    };
    function FrameWH() {
        var h = $(window).height();
        $("iframe").css("height",(h-103)+"px");
    }
    // 配置tab实践在下面无法获取到菜单元素
    $('body').delegate(".site_active",  "click",function () {
        var data_url = $(this).attr("data-url");
        var flag = CheckUrl1(data_url);
        if( flag ){
            data_url = getNameofConfig(data_url);
        }
        var dataid = $(this);
        //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
        if ($(".layui-tab-title li[lay-id]").length <= 0) {
            //如果比零小，则直接打开新的tab项
            active.tabAdd(data_url, dataid.attr("data-id"), dataid.attr("data-title"));
        } else {
            //否则判断该tab项是否以及存在
            var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
            $.each($(".layui-tab-title li[lay-id]"), function () {
                //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                    isData = true;
                }
            });
            if ( isData == false) {
                //标志为false 新增一个tab项
                active.tabAdd(data_url, dataid.attr("data-id"), dataid.attr("data-title"));
            }
        }
        //最后不管是否新增tab，最后都转到要打开的选项页面上
        active.tabChange(dataid.attr("data-id"));
        var pop = 0;
        var length;
        $(".title_ul li").each(function(){
            length = $(this).width()+6;
            pop += $(this).width()+6;
        });
        var nav = $(".title_ul").width();
        $("#num").val( pop/nav );
        if( (pop-length) > nav){
            $("#ring_bth").parent().show()
        }
    });
    $("#left_bth").on('click',function(){
//	      	var pop = 0;
//		    var nav = $(".title_ul").width();
//		    var val = $("#num").val();
//	        if( val > 1 ){
//	        	if( val == 1 ){
//		        	$(".title_ul").css("left","-"+( val - 1 )*nav+"px" );
//	        		$("#num").val( val );
//	        	}else{
//		        	$(".title_ul").css("left","-"+ ( val - 1 ) *nav+"px" );
//	        		$("#num").val( val - 1 );
//	        	}
//	        }else{
//	        	$(".title_ul").css("left","0px" );
//	        }
        var f = $(".title_ul");
        var i = $(".title_ul");
        var t = f[0].style.left, e = parseInt(t.substr(0, t.indexOf("px")));
        0 !== e && ( e = (e += i.width() ) > 0 ? 0 : e,
            f.animate({
                left: e + "px"
            }));
        if( e < 0 ){
            $("#ring_bth").parent().show();
        }
    });
    $("#ring_bth").on('click',function(){
//      	var pop = 0;
//      	var nav = $(".title_ul").width();
//      	var val = $("#num").val();
//	        if( val > 1 ){
//	        	$(".title_ul").css("left","-"+val*nav+"px" );
//	        }
//	        var pop = 0;
//	        $(".title_ul li").each(function(){
//	        	pop += $(this).width()+6;
//	        });
//	       	$("#num").val( pop/nav );
        var f = $(".title_ul");
        var i = $(".title_ul");
        var t = f[0].style.left
            , e = parseInt( t.substr(0, t.indexOf("px")))
            , n = i.width()
            , a = -18;
        if (f.children("li").each(function() {
            var t = $(this).width();
            a += t + 48
        }),
        n < a && e - n > -a) {
            parseInt(a / n);
            e = e - n >= 0 ? 0 : e - n,
                f.animate({
                    left: e + "px"
                })
        }
        if( e < 0 ){
            $("#left_bth").parent().show();
        }
    })
    //设置皮肤编辑
    $('body').delegate('.skin', 'click', function(e){
        e.stopPropagation();
        layer.open({
            type: 1,
            title:"更换皮肤",
            area: ['380px', '180px'],
            content: $("#form_bnt")
        })
    });
    $(".closeBox li").on('click',function () {
        //右键菜单中的选项被点击之后，判断type的类型，决定关闭所有还是关闭当前。
        if ($(this).attr("data-type") == "closethis") {
            //如果关闭当前，即根据显示右键菜单时所绑定的id，执行tabDelete
            active.tabDelete($(this).attr("data-id"))
        } else if ($(this).attr("data-type") == "closeall") {
            var tabtitle = $(".layui-tab-title li");
            var ids = new Array();
            $.each(tabtitle, function (i) {
                ids[i] = $(this).attr("lay-id");
            });
            //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
            active.tabDeleteAll(ids);
        }
        $('.closeBox').hide(); //最后再隐藏右键菜单
    });
    function CustomRightClick(id) {
        //取消右键  closeBox属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
        $('.layui-tab-title li').on('contextmenu', function () { return false; })
        $('.layui-tab-title,.layui-tab-title li').click(function () {
            $('.closeBox').hide();
        });
        //桌面点击右击
        $('.layui-tab-title li').on('contextmenu', function (e) {
            var popupmenu = $(".closeBox");
            popupmenu.find("li").attr("data-id",id); //在右键菜单中的标签绑定id属性

            //判断右侧菜单的位置
            l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
            t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
            popupmenu.css({ left: l, top: t }).show(); //进行绝对定位
            //alert("右键菜单")
            return false;
        });
    }
    setTimeout(function () {
        $("#admin-navbar-side ul > li").eq(0).click();
        $(".title_ul > li").eq(1).children("i").hide();
    },1000);
});
function getNameofConfig(url){

    var new_url = url;

    $.ajax({
        type: "Post",
        dataType:"json",
        async: false,
        data:{
            url: url
        },
        url: projectPath+"/PoliceController/getNameofConfig",
        success: function(data) {
            new_url = data.url;
            query(data,url)
        },
        error: function(data){
            alert("异常错误信息");
        }
    });
    return new_url;
}

function query(data,url){

    $.ajax({
        type: "get",
        url: data.afcp_loginUrl,
        async: false,
        data:{
            userName:data.afcp_loginName,
            password:data.afcp_loginPassWord,
            requestType:'1'
        },
        // url: projectPath+"/PoliceController/jumpPoliceAdd",
        dataType:"jsonp",
        jsonp : 'callback',
        jsonpCallback : "successCallback",
        async: false,
        success: function(res) {
            console.log(res.d);
            //window.open(url);
        },
        error: function(res){
            console.log(res);
            alert("异常错误信息");
        }
    });
}
var projectPath = getRootPath_web(); //项目路径
//获取主机
function getRootPath_web() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (localhostPaht + projectName + "/");
}
function CheckUrl1(str) {
    var RegUrl = new RegExp();
    RegUrl.compile("[a-zA-z]+://[^\\s]*");
    if (!RegUrl.test(str)) {
        return false;
    }
    return true;
}
		

 					