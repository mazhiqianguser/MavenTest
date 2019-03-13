// layui.config({
// 		    base: '../js/',
// 		    version: new Date().getTime()
// }).use(['table','jquery','navbar','tab'], function(){
//   var table = layui.table;
//   var $     = layui.jquery;
//   var h = $(window).height();
//   var w = $(window).width();
//     var navbar = layui.navbar();
//     $("#left_ul").css({
//     	"height":h-60+"px",
//     })
//      //菜单管理编辑
//     $('body').delegate('.compile_form', 'click', function(e){
// 			e.stopPropagation();
// 			layer.open({
// 			  	type: 1,
// 			  	title:"编辑部门",
// 			  	area: ['490px', '240px'],
// 			  	btn: ['确定', '取消'],
// 			 	content: $("#form_bnt"),
// 			 	yes:function(){
// 					layer.closeAll();
// 				},
// 				btn2:function(){
//
// 				}
// 			})
//     });
//      //部门新增
//     $('body').delegate('.add', 'click', function(e){
// 			e.stopPropagation();
// 			var cite_text;
// 			var li_id;
// 			$("#left_ul ul li a").each(function(){
// 				if( $(this).hasClass("click_blue") ){
// 					 cite_text = $(this).children("cite").text();
// 					 li_id = $(this).parent().attr("id");
// 				}
// 			})
// 			layer.open({
// 			  	type: 1,
// 			  	title:"新增部门",
// 			  	area: ['490px', '240px'],
// 			 	btn: ['确定', '取消'],
// 				content: $("#form_name"),
// 			 	yes:function(){
// 					layer.closeAll();
// 				},
// 				btn2:function(){
//
// 				}
// 			})
// 			$('body').delegate('#yes_btn', 'click', function(e){
// 				alert( $("#department_name").val() )
// 				alert(li_id)
// 			});
//     });
//      //部门新增
//     $('body').delegate('.compile', 'click', function(e){
// 			e.stopPropagation();
// 			var cite_text;
// 			var li_id;
// 			$("#left_ul ul li a").each(function(){
// 				if( $(this).hasClass("click_blue") ){
// 					 cite_text = $(this).children("cite").text();
// 					 li_id = $(this).parent().attr("id");
// 				}
// 			})
// 			if( cite_text == "" || cite_text == undefined ||  cite_text == null ){
// 				layer.msg('你当前还未选择需要修改的部门，请选择！！！');
// 				return false
// 			}
// 			$("#department_name").val( cite_text )
// 			layer.open({
// 			  	type: 1,
// 			  	title:"修改部门",
// 			  	area: ['490px', '200px'],
// 			 	btn: ['确定', '取消'],
// 				content: $("#form_name"),
// 			 	yes:function(){
// 					layer.closeAll();
// 				},
// 				btn2:function(){
//
// 				}
// 			})
// 			$('body').delegate('#yes_btn', 'click', function(e){
// 				alert( $("#department_name").val() )
// 				alert(li_id)
// 			});
//     });
//      //部门新增
//     $('body').delegate('.remove', 'click', function(e){
// 			e.stopPropagation();
// 			var cite_text;
// 			var li_id;
// 			$("#left_ul ul li a").each(function(){
// 				if( $(this).hasClass("click_blue") ){
// 					 cite_text = $(this).children("cite").text();
// 					 li_id = $(this).parent().attr("id");
// 				}
// 			})
// 			if( cite_text == "" || cite_text == undefined ||  cite_text == null ){
// 				layer.msg('你当前还未选择需要删除的部门，请选择！！！');
// 				return false
// 			}
// 			layer.confirm('是否要删除？', {
// 				btn: ['确定','取消'] //按钮
// 			}, function(){
// 		//		$.ajax({
// 		//			url:"xxx",
// 		//			type:"post",
// 		//			dataType:"json",
// 		//			data:{
// 		//				id : ID
// 		//			},
// 		//			success:function(data){
// 		//				console.log(6666)
// 		//			}
// 		//		})
// 				layer.msg('删除成功');
// 				alert(li_id)
// 				$(".layui-laypage-btn").click();
// 			}, function(){
// 				layer.msg('删除失败');
// 			});
//     });
//     //菜单新增
//     $('body').delegate('.add_uer', 'click', function(e){
// 		e.stopPropagation();
// 		layer.open({
// 		  	type: 1,
// 		  	title:"新增部门",
// 		  	area: ['490px', '240px'],
// 		 	btn: ['确定', '取消'],
// 			content: $("#form_bnt"),
// 		 	yes:function(){
// 				layer.closeAll();
// 			},
// 			btn2:function(){
//
// 			}
//
// 		})
//     });
//      //设置navbar
// 		    navbar.set({
// 		        spreadOne: true,
// 		        elem: '#admin-navbar-side',
// 		        cached: true,
// 		        url: 'data/root.json'
// 		    });
// 		    //渲染navbar
// 		    navbar.render();
// 		    //监听点击事件
// 		    $('body').delegate('.site_active_two', 'click', function(e){
// 					e.stopPropagation();
// 			 			$(this).parents("#left_ul").find(".site_active_two").children().removeClass("click_blue");
// 		    		$(this).children().addClass("click_blue");
// 		    });
//   //第一个实例
//     var app = table.render({
//     elem: '#idTest'
//     ,url: '../data/data.json' //数据接口
//     ,page: true //开启分页
//       ,skin:"nob"
//     ,cols: [[ //表头
//       {title: '序号', sort: true, toolbar: '#indexTpl',fixed: 'left'}
//       ,{field: 'username', title: '用户名', }
//       ,{field: 'city', title: '姓名', }
//       ,{field: 'classify', title: '角色', }
//       ,{field: 'wealth', title: '邮箱', }
//       ,{field: 'logins', title: '最近登陆', }
//       ,{field: 'wealth', title: '上次登录', }
//       ,{title: '操作',toolbar: '#barDemo',align:"center"}
//     ]]
//   });
// });
//
//
//
//
layui.config({
    base: '../js/',
    version: new Date().getTime()
}).use(['table','jquery',"form"], function(){
    var table = layui.table;
    var $     = layui.jquery;
    var form     = layui.form;
    var h = $(window).height();
    var w = $(window).width();
    //监听开关
    form.on('switch(filter)', function(data){
        console.log(data.elem); //得到checkbox原始DOM对象
        console.log(data.elem.checked); //开关是否开启，true或者false
        console.log(data.value); //开关value值，也可以通过data.elem.value得到
        console.log(data.othis); //得到美化后的DOM对象
        alert(data.elem.checked )
    });
    //单选框监听
    form.on('radio(filter)', function(data){
        console.log(data.elem); //得到radio原始DOM对象
        console.log(data.value); //被点击的radio的value值
    });

//  var navbar = layui.navbar();
    $("#left_ul").css({
        "height":h-60+"px",
    })
    $("#dataTables-organization_wrapper").css({
        "width":w-10+"px",
        "height":h-10+"px"
    })
    $("#dataTables").css({
        "max-height":h-50+"px",
        "overflow-x": "scroll"
    })
    //菜单管理编辑
    $('body').delegate('.compile_form', 'click', function(e){
        e.stopPropagation();
    });
//     //菜单管理新增
    $('body').delegate('.add_uer', 'click', function(e){
        e.stopPropagation();
        $("#pervNav").attr("disabled",false);
        layer.open({
            type: 1,
            title:"新增部门",
            area: ['300px', '360px'],
            btn: ['确定', '取消'],
            content: $("#form_bnt"),
            yes:function(){
                var namePo = $("#namePo").val();
                var pervNav = $("#pervNav").val();
                var pervNavId = $("#pervNav").attr("dataId");
                if( namePo == '' || namePo == null||  namePo == undefined){
                    layer.msg("你当前还没有输入部门名称！请输入！");
                    return false
                }
                //alert("上级部门名称："+ pervNav + " 上级部门ID："+pervNavId+" 部门名称："+namePo);

                //layer.closeAll();
                $.ajax({
                    url:getRootPath_web()+'/dept/insert',
                    type:'post',
                    data:JSON.stringify({
                        parent_id:pervNavId,
                        dept_name:namePo
                    }),
                    cache:false,
                    dataType:'json',
                    contentType: 'application/json;charset=UTF-8',
                    success:function(result) {
                        $("#namePo").val("");
                        $("#pervNav").val("");
                        $("#pervNav").attr("dataId","");
                        if(result.code == "200"){
                            layer.msg('操作成功', {icon: 1});
                            location.reload();
                        }else{
                            layer.msg('操作失败', {icon: 2});
                        }
                    },
                    error : function() {
                        layer.msg("服务器异常");
                    }
                });
//		 		var chk_value =[];
//				$('input[name="orgIds"]:checked').each(function(){
//					chk_value.push( $(this).val() );
//				});
//				if( chk_value.length == 0  ){
//					alert( '你还没有选择任何内容！')
//				}else{
//					alert(chk_value);
//					layer.closeAll();
//				}

            },
            btn2:function(){
                $("#namePo").val("");
                $("#pervNav").val("");
                $("#pervNav").attr("dataId","");
                layer.closeAll();
            }
        })
    });
    //菜单展开操作
    $('body').delegate('.unfold', 'click', function(e){
        e.stopPropagation();
        expandAll("Y")
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
        })
        if( inputName.length == 0 || inputName == undefined || inputName == null ){
            layer.msg('你目前还没勾选');
            return;
        }
        layer.confirm('是否要删除？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            $.ajax({
                url:getRootPath_web() +"/dept/delete/"+inputName.join(',') ,
                type: "DELETE",
                dataType: "json",
                contentType: "application/json",
                success: function (data) {
                    if (data.code == "200") {
                        layer.msg('删除成功');
                        $(".layui-laypage-btn").click();
                        location.reload();;
                    } else {
                        layer.msg('删除失败，请重试');
                        layer.closeAll(); //关闭所有层
                    }
                }
            });
            layer.closeAll(); //关闭所有层
            $(".layui-laypage-btn").click();
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
    //第一个实例
    // var app =  table.render({
    //     elem: '#idTest'
    //     ,url: '../data/data.json' //数据接口
    //     ,page: true //开启分页
    //     ,skin:"nob"
    //     ,cols: [[ //表头
    //         {title: '序号', sort: true, toolbar: '#indexTpl',fixed: 'left'}
    //         ,{field: 'username', title: '名称', }
    //         ,{field: 'wealth', title: '资源路径', }
    //         ,{field: 'sex', title: '状态', }
    //         ,{title: '菜单序号',toolbar: '#input_all'}
    //         ,{title: '操作',toolbar: '#barDemo',align:"center"}
    //     ]]
    // });
});
//失去焦点事件
function Inputblur(the){
    var theID = $(the).val();
    alert( theID )
}
// var ID;
// var title;
// var Pid;
// var Ptitle;
// //监听配置菜单数据
// function AddcoRmove( ID,title,Pid,Ptitle ){
//     ID = ID;
//     title = title;
//     Pid = Pid;
//     Ptitle = Ptitle;
// }
//列表icon点击删除
function IconRemove(ID,the){
      layer.confirm('是否要删除？', {
        btn: ['确定','取消'] //按钮
    }, function(){
        $.ajax({
            url: getRootPath_web()+"/dept/delete/"+ID ,
            type: "DELETE",
            dataType: "json",
            contentType: "application/json",
            success: function (data) {
                if (data.data == "200") {
                    layer.msg('删除成功');
                    $(".layui-laypage-btn").click();
                    location.reload();
                } else {
                    layer.msg('删除失败，请重试');
                    layer.closeAll(); //关闭所有层
                }
            }
        });
        layer.closeAll(); //关闭所有层
        $(".layui-laypage-btn").click();
    }, function(){
        layer.msg('删除失败');
    });
}


