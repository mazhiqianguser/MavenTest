layui.use(['form','element','layer','jquery','table'],function(){
	var form = layui.form, $ = layui.jquery, table = layui.table,layer = layui.layer;
	setInterval (function(){
		$("#today").html(Math.round(Math.random()*5000));
	},1000);
	var style = localStorage.getItem("css_style");
			if( style ==  "1" ){
				document.getElementById('index_css').href = 'style/index_one.css';
				document.getElementById('reo_css').href = 'style/reo_one.css';
			}else{
				document.getElementById('index_css').href = 'style/index.css';
				$("body").css({
					"background":"url(images/bg.jpg) no-repeat",
					"background-size": "100%"
				})
			}
			var myChart1 = echarts.init(document.getElementById('top-map'));
			var	option = {
				    title: {
				        text: '省份TOP10',
				        textStyle: {  
				        	//文字颜色
			                color: '#5bc1fe',
			                 //字体风格,'normal','italic','oblique'
					        fontStyle:'normal',
					        //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
					        fontWeight:'bold',
					        //字体系列
					        fontFamily:'sans-serif',
					        //字体大小
					　　　　 fontSize:16
			            }, 
				    },
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'shadow'
				        }
				    },
				  
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis: {
				        type: 'value',
				         //设置坐标轴字体颜色和宽度  
		                axisLabel: {
                            textStyle: {
                                color: '#5bc1fe',
                            }
                        }, 
				        boundaryGap: [0, 0.01]
				        
				    },
				    yAxis: {
				        type: 'category',
				        //设置坐标轴字体颜色和宽度  
		                axisLabel: {
                            textStyle: {
                                color: '#5bc1fe',
                            }
                       },
				        data: ['山东','上海','北京','广东','四川','云南','河南','山西','大连','黑龙江'],
				         
				    },
				    series: [
				        {
				            name: '2018年5月28日',
				            type: 'bar',
				            itemStyle: {
                    				normal: {
			　　　　　　　　　　　　　　//好，这里就是重头戏了，定义一个list，然后根据所以取得不同的值，这样就实现了，
			                        color: function(params) {
			                            // build a color map as your need.
			                            var colorList = [
			                              '#b3e3f9','#b3e3f9','#87c5f1','#87c5f1','#58a9eb',
			                               '#58a9eb','#2c8be3','#2c8be3','#2074cb','#2074cb',
			                            ];
			                            return colorList[params.dataIndex]
			                        },
			                    }
			                },
				            data: [19325, 23438, 31000, 121594, 134141, 134148,141412,174141,184141,681807,]
				        }
				    ]
				};

			myChart1.setOption(option);
			//echart图自适应容器大小
		    window.addEventListener('resize', function () {
		        myChart1.resize();
		    });
		    var myChart = echarts.init(document.getElementById('china-map'));
		    var option = {
		//      title : {
		//          text: '订单量',
		//          subtext: '纯属虚构',
		//          x:'center'
		//      },
		        dataRange: {//颜色的变化设置
		            x: 'left',
		            y: 'bottom',
		            splitList: [
		                {start: 5000},
		                {start: 3000, end: 5000},
		                {start: 1000, end: 3000},
		                {start: 500, end: 1000},
		                {start: 200, end: 500},
//		                {start: 10, end: 200, label: '10 到 200（自定义label）'},
		//              {start: 5, end: 5, label: '5（自定义特殊颜色）', color: 'black'},
		                {end: 200}
		            ],
		//            calculable : true,//颜色呈条状
		//            text:['高','低'],// 文本，默认为数值文本
		        },
		        tooltip : {//提示框组件。
		            trigger: 'item'//数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用。
		        },
		//      legend: {
		//          orient: 'horizontal',//图例的排列方向
		//          x:'left',//图例的位置
		//          data:['订单量']
		//      },
		//      toolbox: {//工具栏
		//          show: true,
		//          orient : 'vertical',//工具栏 icon 的布局朝向
		//          x: 'right',
		//          y: 'center',
		//          feature : {//各工具配置项。
		//              mark : {show: true},
		//              dataView : {show: true, readOnly: false},//数据视图工具，可以展现当前图表所用的数据，编辑后可以动态更新。
		//              restore : {show: true},//配置项还原。
		//              saveAsImage : {show: true}//保存为图片。
		//          }
		//      },
		//      roamController: {//控制地图的上下左右放大缩小 图上没有显示
		//          show: true,
		//          x: 'right',
		//          mapTypeControl: {
		//              'china': true
		//          }
		//      },
		        series : [
		            {
		                name: '手机丢失数',
		                type: 'map',
		                mapType: 'china',
		                roam: false,//是否开启鼠标缩放和平移漫游
		                itemStyle:{//地图区域的多边形 图形样式
		                    normal:{//是图形在默认状态下的样式
		                        label:{
		                            show:true,//是否显示标签
		                            textStyle: {
		                                color: "rgb(249, 249, 249)"
		                            }
		                        },
		                    },
		                    emphasis:{//是图形在高亮状态下的样式,比如在鼠标悬浮或者图例联动高亮时
		                        label:{show:true}
		                    }
		                },
		                top:"3%",//组件距离容器的距离
		                data:[
		                    {name: '北京',value: Math.round(Math.random()*5000)},
		                    {name: '天津',value: Math.round(Math.random()*5000)},
		                    {name: '上海',value: Math.round(Math.random()*5000)},
		                    {name: '重庆',value: Math.round(Math.random()*5000)},
		                    {name: '河北',value: 0},
		                    {name: '河南',value: Math.round(Math.random()*5000)},
		                    {name: '云南',value: 5},
		                    {name: '辽宁',value: 305},
		                    {name: '黑龙江',value: Math.round(Math.random()*5000)},
		                    {name: '湖南',value: 200},
		                    {name: '安徽',value: Math.round(Math.random()*5000)},
		                    {name: '山东',value: Math.round(Math.random()*5000)},
		                    {name: '新疆',value: Math.round(Math.random()*5000)},
		                    {name: '江苏',value: Math.round(Math.random()*5000)},
		                    {name: '浙江',value: Math.round(Math.random()*5000)},
		                    {name: '江西',value: Math.round(Math.random()*5000)},
		                    {name: '湖北',value: Math.round(Math.random()*5000)},
		                    {name: '广西',value: Math.round(Math.random()*5000)},
		                    {name: '甘肃',value: Math.round(Math.random()*5000)},
		                    {name: '山西',value: Math.round(Math.random()*5000)},
		                    {name: '内蒙古',value: Math.round(Math.random()*5000)},
		                    {name: '陕西',value: Math.round(Math.random()*5000)},
		                    {name: '吉林',value: Math.round(Math.random()*5000)},
		                    {name: '福建',value: Math.round(Math.random()*5000)},
		                    {name: '贵州',value: Math.round(Math.random()*5000)},
		                    {name: '广东',value: Math.round(Math.random()*5000)},
		                    {name: '青海',value: Math.round(Math.random()*5000)},
		                    {name: '西藏',value: Math.round(Math.random()*5000)},
		                    {name: '四川',value: Math.round(Math.random()*5000)},
		                    {name: '宁夏',value: Math.round(Math.random()*5000)},
		                    {name: '海南',value: Math.round(Math.random()*5000)},
		                    {name: '台湾',value: Math.round(Math.random()*5000)},
		                    {name: '香港',value: Math.round(Math.random()*5000)},
		                    {name: '澳门',value: Math.round(Math.random()*5000)}
		                ]
		            }
		        ]
		    };
		    myChart.setOption(option);
		    myChart.on('mouseover', function (params) {
		        var dataIndex = params.dataIndex;
		        console.log(params);
		    });
			//echart图自适应容器大小
		    window.addEventListener('resize', function () {
		        myChart.resize();
		    });
})