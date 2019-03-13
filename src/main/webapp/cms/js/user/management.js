layui.config({
    base: '../js/',
    version: new Date().getTime()
}).use(['table', 'jquery', 'navbar', 'tab'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var h = $(window).height();
    var w = $(window).width();
    var navbar = layui.navbar();

    table.on('tool(demo)', function (obj) {
        var data = obj.data;
        if (obj.event === 'jurisdiction') {
            var roleId = data.role_id;
            // 展示权限树
            perms(roleId);
            layer.open({
                type: 1,
                area: ['490px', '450px'],
                title: "用户权限",
                btn: ['确定', '取消'],
                content: $("#dataTables-organization_wrapper"),
                yes: function () {
                    var chk_value = [];
                    $('input[name="orgIds"]:checked').each(function () {
                        chk_value.push($(this).val());
                    });
                    if (chk_value.length == 0) {
                        alert('你还没有选择任何内容！')
                    } else {
                        $.ajax({
                            url: getRootPath_web() + "/role/perm",
                            type: "POST",
                            dataType: "json",
                            data: {roleId: roleId, perms: chk_value.join(',')},
                            success: function (data) {
                                if (data.code === "200") {
                                    layer.msg(data.desc);
                                    $(".layui-laypage-btn").click();
                                    layer.closeAll();
                                } else {
                                    layer.msg(data.desc);
                                    layer.closeAll();
                                }
                                $("#dataTables-organization_wrapper").empty();
                            }
                        });
                    }
                },
                btn2: function () {
                    $("#dataTables-organization_wrapper").empty()
                }
            })
        } else if (obj.event === 'delete') {
            layer.confirm('确定要删除吗？', function (index) {
                $.ajax({
                    url: getRootPath_web() + "/role/delete/" + data.role_id,
                    type: "DELETE",
                    dataType: "json",
                    contentType: "application/json",
                    data: {},
                    success: function (data) {
                        if (data.code === "200") {
                            layer.msg(data.desc);
                            $(".layui-laypage-btn").click();
                            layer.close(index);
                        } else {
                            layer.msg(data.desc);
                            layer.close(index);
                        }

                    }
                });

            });
        } else if (obj.event === 'compile') {
            layer.prompt({title: '请重新编辑角色名', formType: 2}, function (text, index) {
                $.ajax({
                    url: getRootPath_web() + "/role/update/" + data.role_id,
                    type: "PUT",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify({role_name: text}),
                    success: function (data) {
                        if (data.code === "200") {
                            layer.msg(data.desc);
                            $(".layui-laypage-btn").click();
                            layer.close(index);
                        } else {
                            layer.msg(data.desc);
                            layer.close(index);
                        }
                    }
                });
            });
            var add_the = $(this).parent().parent().prev().children().text();
            $(".layui-layer-input").val(add_the)
        }
    });
    //第一个实例
    table.render({
        elem: '#idTest'
        , url: getRootPath_web() + '/role/select' //数据接口
        , width: w - 12
        , height: h - 50
        , skin: "nob"
        , cols: [[ //表头
            {title: '序号', sort: true, toolbar: '#indexTpl', fixed: 'left', width: 70}
            , {field: 'role_name', title: '角色'}
            , {title: '操作', toolbar: '#barDemo', align: "center"}
        ]]
        , page: true //开启分页
    });
});

/**
 * 加载权限树
 * @param roleId 角色id
 */
function perms(roleId) {
    /**
     * [config treegrid配置项对象]
     * @type {Object}
     */
    var datas = null;
    $.ajax({
        url: getRootPath_web() + "/role/select/" + roleId,
        async: false,
        data: {},
        success: function (data) {
            if (data.code === "200") {
                //将一位数组改变成树状结构图
                function treeObj(originObj) {
                    //对象深拷贝
                    var obj = {};
                    for (key in originObj) {
                        var val = originObj[key];
                        obj[key] = typeof val === 'object' ? arguments.callee(val) : val;
                    }
                    //对象新增children键值，用于存放子树
                    obj['children'] = [];
                    return obj;
                }
                //data：带转换成树形结构的对象数组
                //attributes：对象属性
                function toTreeData(data, attributes) {
                    var resData = data;
                    var tree = [];
                    //找寻根节点 默认返回的parentId为空后期可以更改判断
                    for (var i = 0; i < resData.length; i++) {
                        if (resData[i][attributes.parentId] === '' || resData[i][attributes.parentId] === null || resData[i][attributes.parentId] == 0) {
                            tree.push(treeObj(resData[i]));
                            resData.splice(i, 1);
                            i--;
                        }
                    }
                    run(tree);
                    //找寻子树
                    function run(chiArr) {
                        if (resData.length !== 0) {
                            for (var i = 0; i < chiArr.length; i++) {
                                for (var j = 0; j < resData.length; j++) {
                                    if (chiArr[i][attributes.id] === resData[j][attributes.parentId]) {
                                        var obj = treeObj(resData[j]);
                                        chiArr[i].children.push(obj);
                                        resData.splice(j, 1);
                                        j--;
                                    }
                                }
                                run(chiArr[i].children);
                            }
                        }
                    }
                    return tree;
                }
                data = data.data;
                // 属性配置信息
                var attributes = {
                    id: 'id',
                    parentId: 'parentId'
                };
                var treeData = toTreeData(data, attributes);
                datas = treeData
            } else {
                layer.msg(data.desc);
            }
        }
    });
    var config = {
        id: "datatables-organization",//table的id
        width: "100%",
        renderTo: "dataTables-organization_wrapper",//渲染table的容器对象id
        headerAlign: "left",
        // headerHeight: "38",
        dataAlign: "left",
        indentation: "20",//缩进
        folderOpenIcon: "../js/TreeGrid/img/collapse.png",//展开的图标
        folderCloseIcon: "../js/TreeGrid/img/expand.png",//关闭的图标
        // defaultLeafIcon: "img/branch_icon.png",//叶子节点的图标
        hoverRowBackground: "true",//鼠标悬停是否显示背景色
        folderColumnIndex: "1",//层次缩进
        itemClick: "itemClickEvent",//点击一行的事件处理函数
        columns: [//列名,表头和数据的对齐方式，宽度（可以是数值，也可以是百分比），自定义处理函数等
            {
                headerText: "<input type='checkbox' id='checkAll'/ >",
                headerAlign: "left",
                dataAlign: "left",
                width: "10",
                handler: "customCheckBox"
            },
            {headerText: "全选", dataField: "title", headerAlign: "left", handler: "customOrgName", width: ""}
        ],
        data: datas
    };
    //创建一个组件对象
    var treeGrid = new TreeGrid(config);
    treeGrid.show();

    /**
     * [itemClickEvent 单击数据行后触发该事件]
     * @param  {[String]} id    [行的id]
     * @param  {[String]} index [行的索引]
     * @param  {[Object]} data  [json格式的行数据对象]
     */
    // 单选，并将单选绑定到外面的td标签上。当选中的是父节点时，相应子节点执行同样的操作
    var chkParent = $("input[name='orgIds']").parent();
    chkParent.click(function (e) {
        var ch = $(this).find('input').prop('checked');
        if (ch == false) {
            $(this).find('input').prop('checked', true);
            if ($("input[name='orgIds']").length == $("input[name='orgIds']:checked").length) {
                $("#checkAll").prop('checked', true);
            } else {
                $("#checkAll").prop('checked', false);
            }
        } else {
            $(this).find('input').prop('checked', false);
            if ($("input[name='orgIds']").length == $("input[name='orgIds']:checked").length) {
                $("#checkAllt").prop('checked', true);
            } else {
                $("#checkAll").prop('checked', false);
            }
        }
    });
    $("input[name='orgIds']").click(function (e) {
        e.stopPropagation();
        if ($("input[name='orgIds']").length == $("input[name='orgIds']:checked").length) {
            $("#checkAll").prop("checked", true);
        } else {
            $("#checkAll").prop("checked", false);
        }
    });

    // 全选，并将全选绑定到外面的td标签上
    var chkAllParent = $("#checkAll").parent();
    chkAllParent.click(function (e) {
        var ch = $(this).find('input').prop('checked');
        if (ch == false) {
            $(this).find('input').prop('checked', true);
            $("input[name='orgIds']").prop("checked", true);
        } else {
            $(this).find('input').prop('checked', false);
            $("input[name='orgIds']").prop("checked", false);
        }
    });
    $("#checkAll").click(function (e) {
        e.stopPropagation();
        $("input[name='orgIds']").prop("checked", this.checked);
    });
}

function itemClickEvent(id, index, data) {
//			 jQuery("#currentRow").val(id + ", " + index + ", " + TreeGrid.jsonToStr(data));
    console.log(id + ", " + index + ", " + TreeGrid.jsonToStr(data));
}

/**
 * [customCheckBox 通过指定的方法来自定义栏数据]
 * @param  {[Object]} row
 * @param  {[Object]} col
 */
function customCheckBox(row, col) {
    if (row.checked) {
        return "<input type='checkbox' name='orgIds' checked='checked' value=" + row.id + " >";
    } else {
        return "<input type='checkbox' name='orgIds' value=" + row.id + ">";
    }
}

/**
 * [customOrgName 获取列名]
 * @param  {[Object]} row
 * @param  {[Object]} col
 */
function customOrgName(row, col) {
    var name = row[col.dataField] || "";
    return name;
}

/**
 * [customLook 根据权限来显示操作控件的个数]
 * @param  {[Object]} row [
 * @param  {[Object]} col
 */
function customLook(row, col) {
    return "<input type='button' class='btn btn-info' value='查看'/>&nbsp;<input type='button' class='btn btn-warning' value='修改'/>&nbsp;<input type='button' class='btn btn-primary' value='添加子组织'/>&nbsp;<input type='button' class='btn btn-danger'value='删除'/>";
}

/**
 * [expandAll 展开、关闭所有节点]
 * @param  {Boolean} isOpen [isOpen=Y表示展开，isOpen=N表示关闭]
 */
function expandAll(isOpen) {
    treeGrid.expandAll(isOpen);

}

/**
 * [selectedItem 取得当前选中的行，方法返回TreeGridItem对象]
 */
function selectedItem() {
    var treeGridItem = treeGrid.getSelectedItem();
    if (treeGridItem != null) {
        //获取数据行属性值
        //console(treeGridItem.id + ", " + treeGridItem.index + ", " + treeGridItem.data.name);

        //获取父数据行
        var parent = treeGridItem.getParent();
        if (parent != null) {
            console.log(parent.data.name);
        }

        //获取子数据行集
        var children = treeGridItem.getChildren();
        if (children != null && children.length > 0) {
            console.log(children[0].data.name);
        }
    }
}

//新增用户
function add() {
    layer.prompt({title: '请输入用户名', formType: 2}, function (text, index) {
        if( text == '' || text == undefined || text == null){
            layer.msg('用户名不可以为空，请输入用户名！');
            return false
        }
        $.ajax({
            url: getRootPath_web() + "/role/insert",
            type: "POST",
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({
                role_name: text
            }),
            success: function (data) {
                if (data === "200") {
                    layer.msg(data.desc);
                    $(".layui-laypage-btn").click();
                    location.reload();
                } else {
                    layer.msg(data.desc);
                    layer.closeAll(); //关闭所有层
                }

            }
        });
    });
}


