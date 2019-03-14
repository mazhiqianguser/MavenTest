layui.config({
    base: '../js/',
    version: new Date().getTime()
}).use(['table', 'jquery', "form"], function () {
    var table = layui.table, $ = layui.jquery;
    var h = $(window).height();
    var w = $(window).width();
    var form = layui.form;
    var userName;
    //取出用户信息列入用户名
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
        var temp = arrStr[i].split("=");
        if( temp[0] == "username"){
            userName = temp[1]
        }
    };

 //监听提交
 form.on('submit(demo1)', function(data){


});
    $(".yes_btn").on("click",function () {
        var oldPwd = $("#oldPwd").val();
        var password = $("#password").val();
        var confirmPwd = $("#confirmPwd").val();

        if(password != confirmPwd){
            layer.msg("两次输入的密码不一致,请重新输入！");
            return false;
        }
        if( password.length <= 6 &&  password.length >= 18 ){
            layer.msg("密码请控制在六位到十八位之间！");
            return false;
        }

        var data = {
            username:userName,
            oldPwd : oldPwd,
            password : password,
            confirmPwd : confirmPwd,
        };

        $.ajax({
            url:getRootPath_web() + "/user/updatePwd/",
            data:data,
            type:'POST',
            cache:false,
            dataType:'json',
            success:function(result) {
                if(result.code == "200"){
                    layer.msg(result.desc, {icon: 1},{
                        time: 2000 //2s后自动关闭
                    })
                    setTimeout(function(){
                        window.parent.location.href = '../login.html';
                    },2000);
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
    })

});