layui.use(['element','layer','form','laydate'], function(){
    var element = layui.element,
        layer = layui.layer,
        laydate = layui.laydate,
        form = layui.form;
    // 操作对象
    var form = layui.form;
    var $ = layui.jquery;
    $.ajax({
        url: getRootPath_web() + "/config/select",
        type: 'get',
        dataType: 'json',
        success: function (data) {
            if ( data.code == 200) {
                var str = '';
                var str1 = '';
                $("#title").text( data.data.project_name);
                document.title = data.data.project_name;
            }
        }
    });
    $(".img_tie").attr("src",getRootPath_web()+"/getGifCode?random=" + Math.random());
    $(".img_tie").on('click',function () {
        $(".img_tie").attr("src",getRootPath_web()+"/getGifCode?random=" + Math.random());
    });
    if(window.top.location.href!=location.href)
    {
        window.top.location.href=location.href;
    }
    //表单验证
    $(".layui-form").Validform({
        tiptype:3,
        ajaxPost:true,
        callback:function(data){
            //账号
            var username = $("#username").val();
            //密码
            var password = $("#password").val();
            //验证码
            var vcode = $("#verification").val();
            //alert("账号"+ username +"密码" + password +"验证码"+ verification);
            $.ajax({
                url:getRootPath_web()+"/login",
                type: "post",
                dataType: "json",
                data:{
                    username:username,
                    password:password,
                    vcode:vcode
                },
                success:function (data) {
                    layer.msg(  data.desc );
                    if ( data.code == 200) {


                        // var date = new Date();
                        // date.setTime(date.getTime() - 1);
                        // var delValue = getCookie("alert");
                        // if (!!delValue) {
                        //     document.cookie = 'alert='+delValue+';expires='+date.toGMTString();
                        // }
                        // function getCookie(key) {
                        //     var arr,reg = RegExp('(^| )'+key+'=([^;]+)(;|$)');
                        //     if (arr = document.cookie.match(reg))    //["username=liuwei;", "", "liuwei", ";"]
                        //         return decodeURIComponent(arr[2]);
                        //     else
                        //         return null;
                        // }

                        document.cookie = "username="+ username +";password="+ password +";";
                        setTimeout(function () {
                            window.location.href = './index.html';
                        },2000)
                    }else{
                        setTimeout(function () {
                            $(".img_tie").attr("src",getRootPath_web()+"/getGifCode?random=" + Math.random() );
                        })
                    }

                }
            })
        }
    });
    form.on('checkbox(allChoose)', function(data){
        var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
        child.each(function(index, item){
            item.checked = data.elem.checked;
        });
        form.render('checkbox');
    });
});
