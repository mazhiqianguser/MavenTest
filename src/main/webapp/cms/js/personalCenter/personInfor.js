/**
 * Created by Administrator on 2018/6/15.
 */
layui.config({
    base: '../js/',
    version: new Date().getTime()
}).use(['table', 'jquery', "form"], function () {
    var table = layui.table, $ = layui.jquery;
    var h = $(window).height();
    var w = $(window).width();
    var form = layui.form;
    var userId = 3; //从session中获取
    $.ajax({
        url: getRootPath_web() + "/user/select/" + userId,
        type: "GET",
        dataType: "json",
        contentType: 'application/json;charset=UTF-8',
        data: {},
        success: function (data) {
            if (data.code == 200) {
                var roleId = data.roleId;
                if (roleId != null) {
                    role(roleId);
                } else {
                    role(null);
                }
                var user = data.data.user;
                $("#user_name").val(user.user_name);
                $("#password").val(user.password);
                $("#name").val(user.name);
                $("#phone").val(user.phone);
                $("#email").val(user.email);
            } else {
                layer.msg('加载数据失败');
            }

        }
    });

    function role(roleId) {
        var str = "";
        $("#role").html("");
        $.ajax({
            url: getRootPath_web() + "/user/roles/",
            type: "GET",
            dataType: "json",
            data: {},
            success: function (data) {
                var selectObj = $("#role");
                if (roleId == null) {
                    for (var i = 0; i < data.data.length; i++) {
                        var id = data.data[i].role_id;
                        str += "<option value=" + id + ">" + data.data[i].role_name + "</option>";
                    }
                } else {
                    for (var j = 0; j < data.data.length; j++) {
                        var id1 = data.data[j].role_id;
                        if (roleId == id1) {
                            str += "<option selected='selected' value=" + id1 + ">" + data.data[j].role_name + "</option>";
                        } else {
                            str += "<option value=" + id1 + ">" + data.data[j].role_name + "</option>";
                        }
                    }
                }
                selectObj.append(str);
                form.render('select');
            }
        });
    }

    //监听提交
    form.on('submit(demo1)', function(data){
        var roleId = $("#role").select().val();
        var userId = 3; //从session中获取
        // 获取表单数据
        var data = {
            user_name: $("#user_name").val(),
            password: $("#password").val(),
            name: $("#name").val(),
            phone: $("#phone").val(),
            email: $("#email").val()
        };
        $.ajax({
            url:getRootPath_web() + "/user/update/" + userId + "/" + roleId,
            data:JSON.stringify(data),
            type:'PUT',
            cache:false,
            dataType:'json',
            contentType: 'application/json;charset=UTF-8',
            success:function(result) {
                if(result.code == "200"){
                    layer.msg(result.desc, {icon: 1},{
                        time: 2000 //20s后自动关闭
                    })
                    setTimeout(function(){
                        location.reload();
                    },1000);
                }else{
                    layer.msg(result.desc, {icon: 2},{
                        time: 2000 //20s后自动关闭
                    })
                }
            },
            error : function() {
                layer.msg("服务器异常");
            }
        });
    });
});


