/** navbar.js By Beginner Emain:zheng_jinfan@126.com HomePage:http://www.zhengjinfan.cn */
layui.define(['element', 'common'], function (exports) {
	var data;
    var $ = layui.jquery,
        layer = parent.layer === undefined ? layui.layer : parent.layer,
        element = layui.element,
        common = layui.common,
        cacheName = 'tb_navbar';

    var Navbar = function () {
		/**
		 *  默认配置
		 */
        this.config = {
            elem: undefined, //容器
            data: data, //数据源
            url: "undefined", //数据源地址
            type: 'GET', //读取方式
            cached: true, //是否使用缓存
            spreadOne: true //设置是否只展开一个二级菜单
        };
    };
    //渲染
    Navbar.prototype.render = function () {
        var _that = this;
        var _config = _that.config;
        if (typeof ( _config.elem) !== 'string' && typeof (_config.elem) !== 'object') {
            common.throwError('Navbar error: elem参数未定义或设置出错，具体设置格式请参考文档API.');
        }
        var $container;
        if (typeof (_config.elem) === 'string') {
            $container = $('' + _config.elem + '');
        }
        if (typeof (_config.elem) === 'object') {
            $container = _config.elem;
        }
        if ($container.length === 0) {
            common.throwError('Navbar error:找不到elem参数配置的容器，请检查.');
        }
        if (_config.data === undefined && _config.url === undefined) {
            common.throwError('Navbar error:请为Navbar配置数据源.')
        }
        if (_config.data !== undefined && typeof (_config.data) === 'object') {
            var html = getHtml(_config.data);
            $container.html(html);
            element.init();
            _that.config.elem = $container;
        } else {
            if (_config.cached) {
                var cacheNavbar = layui.data(cacheName);
                if (cacheNavbar.navbar === undefined) {
                    $.ajax({
                        type: _config.type,
                        url: _config.url,
                        async: false, //_config.async,
                        dataType: 'json',
                        success: function (result, status, xhr) {
                            //添加缓存
                            layui.data(cacheName, {
                                key: 'navbar',
                                value: result
                            });

                            var html = getHtml(result);
                            $container.html(html);
                            element.init();
                        },
                        error: function (xhr, status, error) {
                            common.msgError('Navbar error:' + error);
                        },
                        complete: function (xhr, status) {
                            _that.config.elem = $container;
                        }
                    });
                } else {
                    var html = getHtml(cacheNavbar.navbar);
                    $container.html(html);
                    element.init();
                    _that.config.elem = $container;
                }
            } else {
                //清空缓存
                layui.data(cacheName, null);
                $.ajax({
                    type: _config.type,
                    url: _config.url,
                    async: false, //_config.async,
                    dataType: 'json',
                    success: function (result, status, xhr) {
                        var html = getHtml(result);
                        $container.html(html);
                        element.init();
                    },
                    error: function (xhr, status, error) {
                        common.msgError('Navbar error:' + error);
                    },
                    complete: function (xhr, status) {
                        _that.config.elem = $container;
                    }
                });
            }
        }

        //只展开一个二级菜单
        if (_config.spreadOne) {
            var $ul = $container.children('ul');
            $ul.find('li.layui-nav-item').each(function () {
                $(this).on('click', function () {
                    $(this).siblings().removeClass('layui-nav-itemed');
                });
            });
        }
        return _that;
    };
	/**
	 * 配置Navbar
	 * @param {Object} options
	 */
    Navbar.prototype.set = function (options) {
        var that = this;
        that.config.data = undefined;
        $.extend(true, that.config, options);
        return that;
    };
	/**
	 * 绑定事件
	 * @param {String} events
	 * @param {Function} callback
	 */
    Navbar.prototype.on = function (events, callback) {
        var that = this;
        var _con = that.config.elem;

        if (typeof (events) !== 'string') {
            common.throwError('Navbar error:事件名配置出错，请参考API文档.');
        }
        var lIndex = events.indexOf('(');
        var eventName = events.substr(0, lIndex);
        var filter = events.substring(lIndex + 1, events.indexOf(')'));
        if (eventName === 'click') {
            if (_con.attr('lay-filter') !== undefined) {
                _con.children('ul').find('li').each(function () {
                    var $this = $(this);
                    if ($this.find('ul').length > 0) {
                        var $dd = $this.find('li').each(function () {
                            $(this).on('click', function () {
                                var $a = $(this).children('a');
                                var href = $a.data('url');
                                var icon = $a.children('i:first').data('icon');
                                var title = $a.children('cite').text();
                                var data = {
                                    elem: $a,
                                    field: {
                                        href: href,
                                        icon: icon,
                                        title: title
                                    }
                                }
                                callback(data);
                            });
                        });
                    } else {
                        $this.on('click', function () {
                            var $a = $this.children('a');
                            var href = $a.data('url');
                            var icon = $a.children('i:first').data('icon');
                            var title = $a.children('cite').text();
                            var data = {
                                elem: $a,
                                field: {
                                    href: href,
                                    icon: icon,
                                    title: title
                                }
                            }
                            callback(data);
                        });
                    }
                });
            }
        }
    };
	/**
	 * 清除缓存
	 */
    Navbar.prototype.cleanCached = function () {
        layui.data(cacheName, null);
    };
	/**
	 * 获取html字符串
	 * @param {Object} data
	 */
    function getHtml(data) {
    	data = data
        console.log(data)
        //debugger;
        var ulHtml = '<ul class="layui-nav layui-nav-tree back_blue_a beg-navbar ul_top" id="one_ul">';
        var ulhtml1 = '';
        var ulhtml2 = '';
         var left_ul = '<ul class="layui-nav layui-nav-tree back_blue_a beg-navbar">';
        for (var i = 0; i < data.length; i++) {
            if ( data[i].children !== undefined && data[i].children !== null && data[i].children.length > 0 ) {
                ulHtml += '<li class="layui-nav-item " data-url="' + data[i].href + '" data-title="' + data[i].title + '" data-id="'+ data[i].id +'" id='+ data[i].id +'  parentId='+ data[i].parentId +'>'; //第一个div可以默认修改
                left_ul += '<li class="layui-nav-item site_active_two" data-url="' + data[i].href + '" data-title="' + data[i].title + '" data-id="'+ data[i].id +'" id='+ data[i].id +'  parentId='+ data[i].parentId +'>'; //第一个div可以默认修改
            } else {
                ulHtml += '<li class="layui-nav-item site_active" id='+ data[i].id +'  data-url="' + data[i].href + '" data-title="' + data[i].title + '" data-id="'+ data[i].id +'" parentId='+ data[i].parentId +'>';
                left_ul += '<li class="layui-nav-item site_active_two" id='+ data[i].id +'  data-url="' + data[i].href + '" data-title="' + data[i].title + '" data-id="'+ data[i].id +'" parentId='+ data[i].parentId +'>';
            }
            if ( data[i].children !== undefined && data[i].children !== null && data[i].children.length > 0 ) {
                ulHtml += '<a href="javascript:;" data-url="' + data[i].href + '" data-title="' + data[i].title + '" data-id="'+ data[i].id +'">';
                left_ul += '<a href="javascript:;" data-url="' + data[i].href + '" data-title="' + data[i].title + '" data-id="'+ data[i].id +'">';
                if (data[i].icon !== undefined && data[i].icon !== '') {
                    if (data[i].icon.indexOf('fa-') !== -1) {
                        ulHtml += '<i class="fa ' + data[i].icon + '" aria-hidden="true" data-icon="' + data[i].icon + '"></i>';
                        left_ul += '<i class="fa ' + data[i].icon + '" aria-hidden="true" data-icon="' + data[i].icon + '"></i>';
                    } else {
                        ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
                        left_ul += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
                    }
                }
                if (data[i].children != undefined && data[i].children !='' &&  data[i].children.length != 0) {
                    ulHtml += '<span style="float: right;">></span>';
               	}
                ulHtml += '<cite>' + data[i].title + '</cite>'
                left_ul += '<cite>' + data[i].title + '</cite>'

                ulHtml += '</a>';
                left_ul += '</a>';

                ulhtml1 += '<ul class="layui-nav layui-nav-tree back_blue_a beg-navbar ul_top ul_top_p">'
                left_ul += '<ul class="layui-nav-child back_blue_a  " style="padding-left: 15px;">'
                for (var j = 0; j < data[i].children.length; j++) {
                	if( data[i].children[j].children !== undefined && data[i].children[j].children !== null && data[i].children[j].children.length > 0 ){
                    	ulhtml1 += '<li class="layui-nav-item "  data-url="' + data[i].children[j].href + '" data-title="' + data[i].children[j].title + '" data-id="'+ data[i].children[j].id +'" title="' + data[i].children[j].title + '" id='+ data[i].children[j].id +'  parentId='+ data[i].children[j].parentId +'>';
                    	left_ul += '<li class="layui-nav-item site_active_two"  data-url="' + data[i].children[j].href + '" data-title="' + data[i].children[j].title + '" data-id="'+ data[i].children[j].id +'" title="' + data[i].children[j].title + '" id='+ data[i].children[j].id +'  parentId='+ data[i].children[j].parentId +'>';
                	}else{
                		ulhtml1 += '<li class="layui-nav-item site_active"  data-url="' + data[i].children[j].href + '" data-title="' + data[i].children[j].title + '" data-id="'+ data[i].children[j].id +'" title="' + data[i].children[j].title + '" id='+ data[i].children[j].id +'  parentId='+ data[i].children[j].parentId +'>';
                		left_ul += '<li class="layui-nav-item site_active_two"  data-url="' + data[i].children[j].href + '" data-title="' + data[i].children[j].title + '" data-id="'+ data[i].children[j].id +'" title="' + data[i].children[j].title + '" id='+ data[i].children[j].id +'  parentId='+ data[i].children[j].parentId +'>';
                	}
                    ulhtml1 += '<a href="javascript:;" data-url="' + data[i].children[j].href + '" data-title="' + data[i].children[j].title + '" data-id="'+ data[i].children[j].id +'">';
                    left_ul += '<a href="javascript:;" data-url="' + data[i].children[j].href + '" data-title="' + data[i].children[j].title + '" data-id="'+ data[i].children[j].id +'">';
                    if (data[i].children[j].icon !== undefined && data[i].children[j].icon !== '') {
                        if (data[i].children[j].icon.indexOf('fa-') !== -1) {
                            ulhtml1 += '<i class="fa ' + data[i].children[j].icon + '" data-icon="' + data[i].children[j].icon + '" aria-hidden="true"></i>';
                            left_ul += '<i class="fa ' + data[i].children[j].icon + '" data-icon="' + data[i].children[j].icon + '" aria-hidden="true"></i>';
                        } else {
                            ulhtml1 += '<i class="layui-icon" data-icon="' + data[i].children[j].icon + '">' + data[i].children[j].icon + '</i>';
                            left_ul += '<i class="layui-icon" data-icon="' + data[i].children[j].icon + '">' + data[i].children[j].icon + '</i>';
                        }
                    }
                    if (data[i].children[j].children != undefined && data[i].children[j].children !='' &&  data[i].children[j].children.length != 0) {
                    	ulhtml1 += '<span style="float: right;">></span>';
                    }

                    ulhtml1 += '<cite>' + data[i].children[j].title + '</cite>';
                    left_ul += '<cite>' + data[i].children[j].title + '</cite>';

                    ulhtml1 += '</a>';
                    left_ul += '</a>';

                    ulhtml1 += '</li>';

                    var data_l = data[i].children[j].children;
                	if( data_l !== undefined && data_l !== null && data_l.length > 0   ){

                		ulhtml2 += '<ul class="layui-nav layui-nav-tree back_blue_a beg-navbar ul_top ul_top_p">'
                		left_ul += '<ul class="layui-nav-child back_blue_a " style="padding-left: 15px;">'
                		for( var l = 0 ; l <  data_l.length; l ++){
                			if ( data_l[l].children !== undefined && data_l[l].children !== null && data_l[l].length > 0 ) {

					            ulhtml2 += '<li class="layui-nav-item " data-url="' + data_l[l].href + '" data-title="' + data_l[l].title + '" data-id="'+ data_l[l].id +'" title="' + data_l[l].title + '" id='+ data_l[l].id +'  parentId='+ data_l[l].parentId +'>';
					            left_ul += '<li class="layui-nav-item site_active_two" data-url="' + data_l[l].href + '" data-title="' + data_l[l].title + '" data-id="'+ data_l[l].id +'" title="' + data_l[l].title + '" id='+ data_l[l].id +'  parentId='+ data_l[l].parentId +'>';
				            }
				            else {

				                ulhtml2 += '<li class="layui-nav-item site_active" data-url="' + data_l[l].href + '" data-title="' + data_l[l].title + '" data-id="'+ data_l[l].id +'" title="' + data_l[l].title + '" id='+ data_l[l].id +'  parentId='+ data_l[l].parentId +'>';

				                left_ul += '<li class="layui-nav-item site_active_two" data-url="' + data_l[l].href + '" data-title="' + data_l[l].title + '" data-id="'+ data_l[l].id +'" title="' + data_l[l].title + '" id='+ data_l[l].id +'  parentId='+ data_l[l].parentId +'>';
				            }
		                    	left_ul += '<a href="javascript:;" data-url="' + data_l[l].href + '" data-title="' + data_l[l].title + '" data-id="'+ data_l[l].id +'">' ;
		                    	
		                    	ulhtml2 += '<a href="javascript:;" data-url="' + data_l[l].href + '" data-title="' + data_l[l].title + '" data-id="'+ data_l[l].id +'">' ;


		                    if (data_l[l].icon !== undefined && data_l[l].icon !== '') {
		                        if (data_l[l].icon.indexOf('fa-') !== -1) {
		                            
		                            ulhtml2 += '<i class="fa ' + data_l[l].icon + '" data-icon="' + data_l[l].icon + '" aria-hidden="true"></i>';
		                            left_ul += '<i class="fa ' + data_l[l].icon + '" data-icon="' + data_l[l].icon + '" aria-hidden="true"></i>';
		                        } else {
		                            
		                            ulhtml2 += '<i class="layui-icon" data-icon="' + data_l[l].icon + '">' + data_l[l].icon + '</i>';
		                            left_ul += '<i class="layui-icon" data-icon="' + data_l[l].icon + '">' + data_l[l].icon + '</i>';
									
		                        }
		                    }
		                    
		                    ulhtml2 += '<cite>' + data_l[l].title + '</cite>';
		                    left_ul += '<cite>' + data_l[l].title + '</cite>';
		                    
		                    ulhtml2 += '</a>';
		                    left_ul += '</a>';
		                    
		                    ulhtml2 += '</li>';
		                    left_ul += '</li>';
                		}
                		
                		ulhtml2 += '</ul>';
                		left_ul += '</ul>';
                	}
                }
                
                    left_ul += '</li>';

                ulhtml1 += '</ul>';
                left_ul += '</ul>';
            } else {
                var dataUrl = (data[i].href !== undefined && data[i].href !== '') ? 'data-url="' + data[i].href + '"' : '';
                
                ulHtml += '<a href="javascript:;" ' + dataUrl + '>';
                left_ul += '<a href="javascript:;" ' + dataUrl + '>';
                if (data[i].icon !== undefined && data[i].icon !== '') {
                    if (data[i].icon.indexOf('fa-') !== -1) {
                        
                        ulHtml += '<i class="fa ' + data[i].icon + '" aria-hidden="true" data-icon="' + data[i].icon + '"></i>';
                        left_ul += '<i class="fa ' + data[i].icon + '" aria-hidden="true" data-icon="' + data[i].icon + '"></i>';
                    } else {
                        
                        ulHtml += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
                        left_ul += '<i class="layui-icon" data-icon="' + data[i].icon + '">' + data[i].icon + '</i>';
                    }
                }
                
                ulHtml += '<cite>' + data[i].title + '</cite>'
                left_ul += '<cite>' + data[i].title + '</cite>'
                
                ulHtml += '</a>';
                left_ul += '</a>';
            }
            
            ulHtml += '</li>';
            left_ul += '</li>';
        }
        ulHtml += '</ul>';
        console.log(left_ul)
        $("#admin-navbar-side1").append( ulhtml1 )
        $("#admin-navbar-side2").append( ulhtml2 )
         $("#left_ul").append( left_ul )
        return ulHtml;


    }
    var navbar = new Navbar();
    exports('navbar', function (options) {
        return navbar.set(options);
    });
});