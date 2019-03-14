layui.config({
    base: '../js/',
    version: new Date().getTime()
}).use(['table','jquery','navbar','tab','laydate','form'], function(){
    var table = layui.table,$     = layui.jquery,laydate = layui.laydate;
    var h = $(window).height();
    var w = $(window).width();
    var form     = layui.form;
    var url = '';
    //监听开关
    form.on('switch(filter)', function(data){
        console.log(data.elem); //得到checkbox原始DOM对象
        console.log(data.elem.checked); //开关是否开启，true或者false
        console.log(data.value); //开关value值，也可以通过data.elem.value得到
        console.log(data.othis); //得到美化后的DOM对象
        alert(data.elem.checked )
    });
    var startDate = laydate.render({//渲染开始时间选择  
        elem: '#start_time',//通过id绑定html中插入的start
        done: function (value, dates) {
            endDate.config.min ={
                year:dates.year,
                month:dates.month-1, //关键  
                date: dates.date,
            };
        }
    });
    var endDate= laydate.render({//渲染结束时间选择  
        elem: '#end_time',//通过id绑定html中插入的end  
        done: function (value, dates) {
            startDate.config.max={
                year:dates.year,
                month:dates.month-1,//关键   
                date: dates.date,
            }
        }
    });
    $("#searchName").css({
        "width":w-10+"px"
    });
    $('body').delegate('#searchBtn', 'click',function(e){
        e.stopPropagation();
        var  inquire  = $("#searchName").serialize(); //获取form拼接值
        tableID(inquire )
    });
    tableID(null);
    function tableID( inquire ){
        if( inquire == null ){
            url = getRootPath_web()+"operateLogControl/operateLog?"
        }else{
            url = getRootPath_web()+"operateLogControl/selectOperateLogs?"+ inquire
        }
        //列表
        var app = table.render({
            elem: '#idTest'
            ,url: url
            ,page: true //开启分页
            ,skin:"nob"
            ,cols: [[ //表头
                {field: 'oplog_id',title: '编号', sort: true, fixed: 'left',width: 70}
                 ,{field: 'login_name', title: '用户名', }
                ,{field: 'username', title: '角色名', }
                ,{field: 'phone', title: '手机号', }
                ,{field: 'operate_time', title: '操作时间', templet: function(d){
                        var data =d.operate_time;
                        var datatime='';
                        if( data != null|| data!=undefined){
                            timestampToTime(data);
                            function timestampToTime(timestamp) {
                                var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
                                var Y = date.getFullYear() + '-';
                                var M = ( date.getMonth() + 1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
                                var D = date.getDate() + ' ';
                                var h = ( date.getHours() < 10 ? '0'+(date.getHours()) : date.getHours() ) + ':';
                                var m = ( date.getMinutes() < 10 ? '0'+(date.getMinutes() ) : date.getMinutes() ) + ':';
                                var s = ( date.getSeconds()  < 10 ? '0'+(date.getSeconds() ) : date.getSeconds() );
                                 datatime = Y+M+D+h+m+s;
                                return Y+M+D+h+m+s;
                            }
                            return datatime;
                        }
                    }}
                ,{field: 'op_ip', title: '登陆ip', }
                ,{field: 'function_name', title: '登录功能菜单', }
                ,{field: 'operate_type', title: '操作类型',toolbar: '#control' }
                ,{field: 'operate_content', title: '详细内容', }
            ]]
        });
        $(".layui-table-body").css({
            "max-height":h-180+"px"
        });
    }

    // //起止时间
    // laydate.render({
    //     elem: '#range-time'
    //     ,range: true //或 range: '~' 来自定义分割字符
    // });
    //菜单管理编辑
    $('body').delegate('.compile_form', 'click', function(e){
        e.stopPropagation();
        layer.open({
            type: 1,
            title:"编辑菜单",
            area: ['490px', '500px'],
            btn: ['确定', '取消'],
            content: $("#form_bnt"),
            yes:function(){
                layer.closeAll();
            },
            btn2:function(){

            }
        })
    });
    //用户管理新增
    $('body').delegate('.add_uer', 'click', function(e){
        e.stopPropagation();
        layer.open({
            type: 1,
            title:"新增菜单",
            area: ['490px', '500px'],
            btn: ['确定', '取消'],
            content: $("#form_bnt"),
            yes:function(){
                layer.closeAll();
            },
            btn2:function(){

            }

        })
    });

    //用户管理新增
    $('body').delegate('#SearchName', 'click',function(e){
        e.stopPropagation();
        var the = $(this).children("span");
        if( the.text() == "查询"){
            $("#searchName").show();
            $(".layui-table-body").css({
                "max-height":h-200+"px"
            });
            the.text("隐藏")
        }else{
            $("#searchName").hide();
            $(".layui-table-body").css({
                "max-height":h-130+"px"
            });
            the.text("查询")
        }

    });
    $(".layui-table-body").css({
        "max-height":h-130+"px"
    })
    //用户管理新增
//  $('body').click(function(){
//		$("#searchName").hide();
//  });

    //判断两次用户密码是否正确
//  $('body').delegate('.yes_btn', 'click', function(e){
//		e.stopPropagation();
//		var password = $("#password").val();
//		var Ypassword = $("#Ypassword").val();
//		if( password == Ypassword){
//			layer.closeAll()
//		}else{
//			layer.msg('两次密码不一致',{icon: 1});
//		}
//  });


    $(".an").click(function(){
        var account = $(".dl_c_zh1").val();
        if( account.length < 8 ){
            alert("账号不能小于8个字符");
            return;
        }else if(account.length > 20){
            alert("账号不能大于20个字符");
            return;
        }else{
            if( isNaN(account) ){
                alert("账号应为全数字");
                return;
            }
        }
        var password = $(".dl_c_zh2").val();
        if( password.length < 6 ){
            alert("密码不能小于6个字符");
            return;
        }else if( password.length > 18 ){
            alert("密码不能大于18个字符");
            return;
        }
        alert("用户输入账号为："+ account + "密码为："+ password );
    });
});