/**
 * TreeGrid
 * @param  {Object} _config 定义TreeGrid配置项的对象
 * 
 */
TreeGrid = function(_config){
	var _config = _config || {};//配置对象
	
	var s = "";
	var rownum = 0;
	var __root;//根容器
	
	var __selectedData = null;
	var __selectedId = null;
	var __selectedIndex = null;

	var folderOpenIcon = (_config.folderOpenIcon || TreeGrid.FOLDER_OPEN_ICON);//展开图标
	var folderCloseIcon = (_config.folderCloseIcon || TreeGrid.FOLDER_CLOSE_ICON);//关闭图标

	// var defaultLeafIcon = (_config.defaultLeafIcon || TreeGrid.DEFAULT_LEAF_ICON);

	/**
	 * drawHeader 生成表头那一行
	 */
	var drawHeader = function(){
		s += "<tr class='header' height='" + (_config.headerHeight || "30") + "'>";
		var cols = _config.columns;
		for(i=0;i<cols.length;i++){
			var col = cols[i];
			s += "<td align='" + (col.headerAlign || _config.headerAlign || "center") + "' width='" + (col.width || "") + "'>" + (col.headerText || "") + "</td>";
		}
		s += "</tr>";
	}
	
	/**
	 * drawData 用于递归生成数据行
	 */
	var drawData = function(){
		var rows = _config.data;
		var cols = _config.columns;
		drawRowData(rows, cols, 1, "");
	}
	
	/**
	 * drawRowData 绘制每一行数据。局部变量i、j必须要用 var 来声明，否则，后续的数据无法正常显示
	 * @param  {Object} _rows 配置项中的data,后台请求数据
	 * @param  {Object} _cols 配置项中的列
	 * @param  {String} _level 层级级别
	 * @param  {String} _pid 父行的id
	*/
	var drawRowData = function(_rows, _cols, _level, _pid){
		var folderColumnIndex = (_config.folderColumnIndex || 0);

		for(var i=0;i<_rows.length;i++){
			var id = _pid + "_" + i; //行id
			var row = _rows[i];
			
			s += "<tr id='TR" + id + "' data-pid='" + ((_pid=="")?"":("TR"+_pid)) + "' data-open='Y' data=\"" + TreeGrid.jsonToStr(row) + "\" data-rowIndex='" + rownum++ + "'>";
			for(var j=0;j<_cols.length;j++){
				var col = _cols[j];
				s += "<td align='" + (col.dataAlign || _config.dataAlign || "left") + "'";

				//层次缩进 
				if(j==folderColumnIndex){
					s += " style='text-indent:" + (parseInt((_config.indentation || "20"))*(_level-1)) + "px;'> ";
				}else{
					s += ">";
				}

				//节点图标
				if(j == folderColumnIndex){
					if( row.children != undefined || row.children != null){
						if( row.children.length != 0){ //有下级数据
							s += "<img data-folder='Y' data-trid='TR" + id + "' src='" + folderOpenIcon + "' class='image_hand'>";
						}
                    }
				}

				//单元格内容
				if(col.handler){
					s += (eval(col.handler + ".call(new Object(), row, col)") || "") + "</td>";
				}else{
					s += (row[col.dataField] || "") + "</td>";
				}
			}
			s += "</tr>";

			//递归显示下级数据
			if(row.children){
				drawRowData(row.children, _cols, _level+1, id);
			}
		}
	}
	
	//主函数
	this.show = function(){
		this.id = _config.id || ("TreeGrid" + TreeGrid.COUNT++);

		s += "<table id='" + this.id + "' cellspacing=0 cellpadding=0 width='" + (_config.width || "100%") + "' class='TreeGrid'>";
		drawHeader();
		drawData();
		s += "</table>";
		
		__root = $("#"+_config.renderTo);
		__root.append(s);
		
		//初始化动作
		init();
	}

	/**
	 * init 初始化
	 */
	var init = function(){
		//鼠标经过改变背景色
		if((_config.hoverRowBackground || "false") == "true"){
			__root.find("tr").hover(
				function(){
					if($(this).attr("class") && $(this).attr("class") == "header") return;
					$(this).addClass("row_hover");
				},
				function(){
					$(this).removeClass("row_hover");
				}
			);
		}
		//将单击事件绑定到tr标签
		__root.find("tr").bind("click", function(){
            var LID;
            var Ltitle;
            var LPid;
            var LPtitle;
			__root.find("tr").removeClass("row_active");
			$(this).addClass("row_active");
			//获取当前行的数据
			__selectedData = this.data || this.getAttribute("data");
			__selectedId = this.id || this.getAttribute("id");
			__selectedIndex = this.rownum || this.getAttribute("data-rowIndex");
			//行记录单击后触发的事件
				eval(_config.itemClick + "(__selectedId, __selectedIndex, TreeGrid.strTojson(__selectedData))");
                //console.log( ID +"----" + title +"----"+Pid +"----"+Ptitle );
                // LID = ID;
                // Ltitle = title;
                // LPid = Pid;
                // LPtitle = Ptitle;
            //列表icon点击添加
            // $(".IconAdd").on("click",function () {
            //     alert( LID +"----" + Ltitle +"----"+LPid +"----"+LPtitle );
            // })
			//this.getSelectedItem()//当前行获取
		});
		//展开、关闭下级节点
		__root.find("img[data-folder='Y']").bind("click", function(e){
			e.stopPropagation();
			var trid = this.trid || this.getAttribute("data-trid");
			var isOpen = __root.find("#" + trid).attr("data-open");
			isOpen = (isOpen == "Y") ? "N" : "Y";
			__root.find("#" + trid).attr("data-open", isOpen);
			showHiddenNode(trid, isOpen);
		});
	}

	//显示或隐藏子节点数据
	/**
	 * showHiddenNode
	 * @param  {String} _trid 行id
	 * @param  {Boolean} _open 是否展开
	 */
	var showHiddenNode = function(_trid, _open){
		if(_open == "N"){ //隐藏子节点
			__root.find("#"+_trid).find("img[data-folder='Y']").attr("src", folderCloseIcon);
			__root.find("tr[id^=" + _trid + "_]").css("display", "none");
		}else{ //显示子节点
			__root.find("#"+_trid).find("img[data-folder='Y']").attr("src", folderOpenIcon);
			showSubs(_trid);
		}
	}

	/**
	 * showSubs 递归检查下一级节点是否需要显示
	 * @param  {String} _trid 所属的行id
	 */
	var showSubs = function(_trid){
		var isOpen = __root.find("#" + _trid).attr("data-open");
		if(isOpen == "Y"){
			var trs = __root.find("tr[data-pid=" + _trid + "]");
			trs.css("display", "");
			for(var i=0;i<trs.length;i++){
				showSubs(trs[i].id);
			}
		}
	}

	/**
	 * expandAll 展开或收起所有节点
	 * @param  {Boolean} isOpen 是否展开节点
	 */
	this.expandAll = function(isOpen){
		var trs = __root.find("tr[data-pid='']");
		for(var i=0;i<trs.length;i++){
			var trid = trs[i].id || trs[i].getAttribute("id");
			showHiddenNode(trid, isOpen);
		}
	}
	
	//取得当前选中的行记录
	this.getSelectedItem = function(){
		return new TreeGridItem(__root, __selectedId, __selectedIndex, TreeGrid.strTojson(__selectedData));
	}

};

//公共静态变量
TreeGrid.FOLDER_OPEN_ICON = "img/collapse.png";
TreeGrid.FOLDER_CLOSE_ICON = "img/expand.png";
// TreeGrid.DEFAULT_LEAF_ICON = "img/branch_icon.png";
TreeGrid.COUNT = 1;

/**
 * jsonToStr 将json对象转换成字符串
 * @param  {[Object]} obj 需要进行转为字符串的json对象
 */
TreeGrid.jsonToStr = function(obj){
	var arr = [];

	var fmt = function(s){
		if(typeof s == 'object' && s != null){
			if(s.length){
				var _substr = "";
				for(var x=0;x<s.length;x++){
					if(x>0) _substr += ", ";
					_substr += TreeGrid.jsonTostr(s[x]);
				}
				return "[" + _substr + "]";
			}else{
				return TreeGrid.jsonTostr(s);
			}
		}
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}

	for(var i in obj){
		if(typeof obj[i] != 'object'){ //暂时不包括子数据
			arr.push(i + ":" + fmt(obj[i]));
		}
	}

	return '{' + arr.join(', ') + '}';
}

/**
 * strTojson 将字符串转为json对象
 * @param  {String} s 需要转换为json格式的字符串
 * jQuery判断ie浏览器，用$.support替换$.browser判断
 */
TreeGrid.strTojson = function(s){
	var json = null;
	if($.support){
		json = eval("(" + s + ")");
	}else{
		json = new Function("return " + s)();
	}
	return json;
}

/**
 * TreeGridItem 数据行对象
 * @param {Object} _root     根容器对象
 * @param {String} _rowId    行id
 * @param {String} _rowIndex 行索引
 * @param {String} _rowData  行数据
 */
function TreeGridItem (_root, _rowId, _rowIndex, _rowData){
	var __root = _root;
	
	this.id = _rowId;
	this.index = _rowIndex;
	this.data = _rowData;
	
	// 获取父对象
	this.getParent = function(){
		var pid = $("#" + this.id).attr("data-pid");
		if(pid!=""){
			var rowIndex = $("#" + pid).attr("data-rowIndex");
			var data = $("#" + pid).attr("data");
			return new TreeGridItem(_root, pid, rowIndex, TreeGrid.strTojson(data));
		}
		return null;
	}
	
	// 获取子对象
	this.getChildren = function(){
		var arr = [];
		var trs = $(__root).find("tr[data-pid='" + this.id + "']");
		for(var i=0;i<trs.length;i++){
			var tr = trs[i];
			arr.push(new TreeGridItem(__root, tr.id, tr.rowIndex, TreeGrid.strTojson(tr.data)));
		}
		return arr;
	}
};
    $("input[name='orgIds']").unbind("click").click(function () {
        var thew = $(this);
        var id = $(this).parent().parent().attr("id");
        var data_pid = $(this).parent().parent().attr("data-pid");
        var i = 0;
        var y = 0 ;
        thew.parent().parent().parent().children("tr").each(function () {
            var the = $(this);
            if( the.attr("id") != undefined || the.attr("id") != null ){
                if( the.attr("id").indexOf( id ) != -1 ){
                    if( thew.prop('checked') == true ){
                        thew.prop('checked',true);
                        the.children("td:nth-child(1)").children( $("input[name='orgIds']") ).prop('checked',true);
                    }else{
                        thew.prop('checked',false);
                        the.children("td:nth-child(1)").children( $("input[name='orgIds']") ).prop('checked',false);
                    }
                }
            }
            if( the.attr("data-pid") != undefined || the.attr("data-pid") != null || the.attr("data-pid") != ""){
                if( the.attr("data-pid") == data_pid){
                    i ++;
                    if( the.children("td:nth-child(1)").children( $("input[name='orgIds']") ).prop('checked') == true ){
                        y++
                    }
                }
            }
            if( i == y ){
                $("#"+data_pid).children("td:nth-child(1)").children( $("input[name='orgIds']") ).prop('checked',true);
            }else{
                $("#"+data_pid).children("td:nth-child(1)").children( $("input[name='orgIds']") ).prop('checked',false);
            }
        });
    });
