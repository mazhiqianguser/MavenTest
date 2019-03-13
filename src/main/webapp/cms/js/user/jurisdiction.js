layui.config({
		    base: '../js/',
		    version: new Date().getTime()
}).use(['table','jquery',"form"], function(){
//	'navbar','tab'
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
	})
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
    //菜单管理编辑
    $('body').delegate('.add_uer', 'click', function(e){
		e.stopPropagation();
		layer.open({
		  	type: 1,
		  	title:"新增菜单",
		  	area: ['600px', '360px'],
		  	btn: ['确定', '取消'],
		 	content: $("#form_bnt"),
		 	yes:function(){
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
			layer.closeAll();
			},
			btn2:function(){
				
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
		alert( inputName );
		layer.confirm('是否要删除？', {
			btn: ['确定','取消'] //按钮
		}, function(){
	//		$.ajax({
	//			url:"xxx",
	//			type:"post",
	//			dataType:"json",
	//			data:{
	//				id : ID
	//			},
	//			success:function(data){
	//				console.log(6666)
	//			}
	//		})
			layer.msg('删除成功');
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
  	})
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
	var theID = $(the).val();
	alert( theID )
}
//列表icon点击添加
function IconAdd(ID,the){
	alert(ID)
	layer.open({
		type: 1,
		title:"新增菜单",
		area: ['600px', '360px'],
		btn: ['确定', '取消'],
		 	content: $("#form_bnt"),
		 	yes:function(){
				layer.closeAll();
			},
			btn2:function(){
				
			}
	})
}
//列表icon点击修改
function IconCompile(ID,the){
	alert(ID)
	layer.open({
		type: 1,
		title:"编辑菜单",
		area: ['600px', '360px'],
		btn: ['确定', '取消'],
		 	content: $("#form_bnt"),
		 	yes:function(){
				layer.closeAll();
			},
			btn2:function(){
				
			}
	})
}
//列表icon点击删除
function IconRemove(ID,the){
	alert(ID)
	layer.confirm('是否要删除？', {
		btn: ['确定','取消'] //按钮
	}, function(){
//		$.ajax({
//			url:"xxx",
//			type:"post",
//			dataType:"json",
//			data:{
//				id : ID
//			},
//			success:function(data){
//				console.log(6666)
//			}
//		})
		layer.msg('删除成功');
		$(".layui-laypage-btn").click();
	}, function(){
		layer.msg('删除失败');
	});
}
            

