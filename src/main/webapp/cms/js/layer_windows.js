layui.use(['form','element','layer','jquery','table'],function(){
	var form = layui.form, $ = layui.jquery, table = layui.table,layer = layui.layer;
	//关闭当前所有弹窗
    $('body').delegate('.close', 'click', function(e){
			e.stopPropagation();
	 		layer.closeAll()
    });
    var style = localStorage.getItem("css_style");
	if( style ==  "1" ){
		document.getElementById('index_css').href = '../style/index_one.css';
		document.getElementById('reo_css').href = '../style/reo_one.css';
	}else{
		document.getElementById('index_css').href = '../style/index.css';
		document.getElementById('reo_css').href = '../style/reo.css';
	}
})
//删除列表
function remove(ID){
	//询问框
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
//新增用户
function add(){
	layer.prompt({ title: '请输入用户名', formType: 2 }, function(text, index){
		//layer.msg('您最后写下了：'+text);
//		$.ajax({
//			url:"xxx",
//			type:"post",
//			dataType:"json",
//			data:{
//				text : text
//			},
//			success:function(data){
//				console.log(6666)
//			}
//		})
		layer.close(index);
	});

}
