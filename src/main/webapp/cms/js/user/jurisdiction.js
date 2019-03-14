var form;
layui.config({
    base: '../js/',
    version: new Date().getTime()
}).use(['table','jquery',"form"], function(){
//	'navbar','tab'
    var table = layui.table;
    var $     = layui.jquery;
    form     = layui.form;
    var h = $(window).height();
    var w = $(window).width();
    //监听开关
    form.on('switch(filter)', function(data){
        // console.log(data.elem); //得到checkbox原始DOM对象
        // console.log(data.elem.checked); //开关是否开启，true或者false
        // console.log(data.value); //开关value值，也可以通过data.elem.value得到
        // console.log(data.othis); //得到美化后的DOM对象
        // alert(data.elem.checked )
        var permState ;
        var ID = data.elem.value;
        if( data.elem.checked ==  true ){
            permState = 1;
        }else{
            permState = 2;
        }
        $.ajax({
            type: "put",
            url:getRootPath_web()+"/perm/update",
            dataType: "json",
            //data: object,
            data:JSON.stringify({
                perm_id:ID,
                perm_state:permState
            }),
            contentType: 'application/json;charset=UTF-8',
            cache: false,
            success:function (data) {
                console.log(data)
                if(data.code=="200"){
                    layer.msg('操作成功', {icon: 1});
                    //location.reload();
                }else{
                    layer.msg(data.desc, {icon: 2});
                }
            },
            error:function(arg1) {
                alert("系统异常,请联系管理员");
                console.log( arg1 )
            }
         });
    });
    //单选框监听
    form.on('radio(filter)', function(data){
        console.log(data.elem); //得到radio原始DOM对象
        console.log(data.value); //被点击的radio的value值
    });
//  var navbar = layui.navbar();
    $("#left_ul").css({
        "height":h-60+"px",
    });
    $("#dataTables-organization_wrapper").css({
        // "width":w-10+"px",
        "height":h-10+"px"
    });
    $("#dataTables").css({
        "max-height":h-58+"px",
        "overflow-y": "scroll"
    });
    //菜单管理编辑
    $('body').delegate('.compile_form', 'click', function(e){
        e.stopPropagation();
    });
    //菜单管理编辑
    $('body').delegate('.add_uer', 'click', function(e){
        form.render();
        //上级菜单的值
        $("#pervNav").val("");
        //上级菜单的id
        $("#pervNav").attr("dataid","");
        //菜单的值
        $("#namePo").val("");
        //菜单的id
        $("#namePo").attr("dataid","");
        //菜单的url
        $("#url").val("");
        //菜单的排序(升序)
        $("#sort").val("");
        //菜单的权限代码
        $("#sign").val("");
        //菜单的图标icon
        $("#icon").val("");
        e.stopPropagation();
        layer.open({
            type: 1,
            title:"新增菜单",
            area: ['600px', '360px'],
            btn: ['确定', '取消'],
            content: $("#form_bnt"),
            yes:function(){
                // console.log( $("#pervNav").attr("dataid"));//上级菜单的id
                // console.log( $("#namePo").val());//菜单的内容
                // console.log( $("#url").val());//url
                // console.log( $("#sort").val());//排序升序
                // console.log( $("#sign").val());//权限代码
                // console.log( $("#icon").val());//图标icon
                // var data = $("form").serialize();
                var parentId,perm_name,perm_url,perm_icon,perm_type,perm_state,perm_code,sort;
                var pervId = $("#pervNav").attr("dataid");
                var datas = $("form").serialize().split("&");
                for (var i = 0; i < datas.length; i++) {
                    perm_name = decodeURIComponent(datas[1].split("=")[1]);
                    perm_url = decodeURIComponent(datas[2].split("=")[1]);
                    sort = decodeURIComponent(datas[3].split("=")[1]);
                    perm_code = decodeURIComponent(datas[4].split("=")[1]);
                    perm_icon = decodeURIComponent(datas[5].split("=")[1]);
                    // = decodeURIComponent(datas[6].split("=")[1]);
                    perm_state = decodeURIComponent(datas[6].split("=")[1]);
                }
                if( $("#pervNav").attr("dataid") == "" || $("#pervNav").attr("dataid") == undefined||$("#pervNav").attr("dataid") == null ){
                    parentId = 0;
                }else{
                    parentId = $("#pervNav").attr("dataid");
                }
                if( perm_name == "" || perm_name == undefined || perm_name == null ){
                  layer.msg("请输入菜单名称");
                   return false
                }
                // if( perm_url == "" || perm_url == undefined || perm_url == null ){
                //     layer.msg("请输入");
                //     return false
                // }
                if( sort == "" || sort == undefined || sort == null ){
                    layer.msg("请输入排序号");
                    return false
                }
                // if( perm_icon == "" || perm_icon == undefined || perm_icon == null ){
                //     layer.msg("请输入图标");
                //     return false
                // }
                var object = new Object();
                if( perm_icon != "" ){
                    if( perm_icon.indexOf("&") == -1 ){
                        perm_icon = "&"+perm_icon
                    }
                }
                // var object = new Object();
                // object["parent_id"] = parseInt(parentId);
                // object["perm_name"] = perm_name;
                // object["perm_url"] = perm_url;
                // object["perm_icon"] = perm_icon;
                // object["perm_type"] = parseInt(perm_type);
                // object["perm_state"] = parseInt(perm_state);
                // object["perm_code"] = perm_code;
                // object["sort"] = parseInt(sort);
                // console.log( object );
                $.ajax({
                    type: "POST",
                    contentType: 'application/json;charset=UTF-8',
                    url:getRootPath_web()+"/perm/insert",
                    dataType: "json",
                    //data: object,
                    data:JSON.stringify({
                        parent_id:parentId,perm_name:perm_name,perm_url:perm_url,sort:sort,perm_code:perm_code,perm_icon:perm_icon,perm_type:perm_type,perm_state:perm_state
                    }),
                    cache: false,
                    success:function (data) {
                        if( data.code == 200 ){
                            layer.msg(data.desc, {icon: 1});
                            setTimeout( function(){
                                $(".layui-laypage-btn").click();
                                layer.closeAll();
                                location.reload();
                            },1000 )
                        }else{
                            layer.msg(data.desc, {icon: 2});
                        }
                    }
                });
            },
            btn2:function(){
                layer.closeAll();
            }
        })
    });
    //菜单展开操作
    $('body').delegate('.unfold', 'click', function(e){
        e.stopPropagation();
        if( $(this).children(".unzk").text() == "展开" ){
            expandAll("Y");
            $(this).children(".unzk").text("折叠");
            $(this).children(".layui-icon").attr("class","layui-icon layui-icon-up");
        }else{
            expandAll("N");
            $(this).children(".unzk").text("展开");
            $(this).children(".layui-icon").attr("class","layui-icon layui-icon-down");
        }
    });
    //菜单收起操作
    $('body').delegate('.fold', 'click', function(e){
        e.stopPropagation();
        expandAll("N")
    });
    //菜单删除操作
    $('body').delegate('.Remove', 'click', function(e){
        e.stopPropagation();
        var inputName = [];
        $("input[name='orgIds']").each(function(){
            if( $(this).prop('checked') == true ){
                inputName.push( $(this).val() )
            }
        });
        if( inputName.length == 0 || inputName == undefined || inputName == null ){
            layer.msg('你目前还没勾选');
            return;
        }
        layer.confirm('是否要删除？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                url:getRootPath_web() +"/perm/delete/"+inputName.join(',') ,
                type: "DELETE",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    if (data.code == 200) {
                        layer.msg('删除成功');
                        setTimeout( function(){
                            location.reload();
                        },1000 )
                        // location.reload();
                    } else {
                        layer.msg('当前菜单下是否含有子菜单，有子菜单不能删除，需先删除子菜单！');
                    }
                }
            });
        }, function(){
            layer.msg('删除失败');
        });
    });
    $(".Icon").hover(function(){
        $(this).css({
            "font-size":"18px"
        })
    },function(){
        $(this).css({
            "font-size":"16px"
        })
    });
//   //设置navbar
//		    navbar.set({
//		        spreadOne: true,
//		        elem: '#admin-navbar-side',
//		        cached: true,
//		        url: 'data/root.json'
//		    });
//		    //渲染navbar
//		    navbar.render();
//		    //监听点击事件
//		    $('body').delegate('.site_active_two', 'click', function(e){
//					e.stopPropagation();
//			 			$(this).parents("#left_ul").find(".site_active_two").children().removeClass("click_blue");
//		    		$(this).children().addClass("click_blue");
//		    });
    //第一个实例
    var app =  table.render({
        elem: '#idTest'
        ,url: '../data/data.json' //数据接口
        ,page: true //开启分页
        ,skin:"nob"
        ,cols: [[ //表头
            {title: '序号', sort: true, toolbar: '#indexTpl',fixed: 'left'}
            ,{field: 'username', title: '名称', }
            ,{field: 'wealth', title: '资源路径', }
            ,{field: 'sex', title: '状态', }
            ,{title: '菜单序号',toolbar: '#input_all'}
            ,{title: '操作',toolbar: '#barDemo',align:"center"}
        ]]
    });

});
//失去焦点事件
function Inputblur(the){
    var theID = $(the).attr("id");
    var theVal = $(the).val();
    // alert( theID )
    $.ajax({
        type: "put",
        url:getRootPath_web()+"/perm/update",
        dataType: "json",
        //data: object,
        data:JSON.stringify({
            perm_id:theID,
            sort:theVal
        }),
        contentType: 'application/json;charset=UTF-8',
        cache: false,
        success:function (data) {
            if(data.code == 200){
                layer.msg('操作成功', {icon: 1});
                setTimeout( function(){
                    $(".layui-laypage-btn").click();
                    layer.closeAll();
                    location.reload();
                },1000 )
            }else{
                layer.msg('序号不能为负数，请填写正确的格式', {icon: 2});
                setTimeout( function(){
                    location.reload();
                },2000 )
            }
        }
    });
}
//列表icon点击删除
function IconRemove(ID,the){
    layer.confirm('是否要删除？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            url: getRootPath_web()+"/perm/delete/"+ID ,
            type: "DELETE",
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                if ( data.code == 200) {
                    layer.msg('删除成功');
                    setTimeout( function(){
                        // $(".layui-laypage-btn").click();
                        // layer.closeAll();
                        location.reload();
                    },1000 )
                } else {
                    layer.msg('当前菜单下是否含有子菜单，有子菜单不能删除，需先删除子菜单！');
                }
            }
        });
    }, function(){
        layer.msg('删除失败');
    });
}


            

