layui.config({
    base: '../js/',
    version: new Date().getTime()
}).use(['table', 'jquery', 'navbar', 'tab', "form"], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var h = $(window).height();
    var w = $(window).width();
    var form = layui.form;
    var navbar = layui.navbar();
    var tab = layui.tab();

    $(".form_lay").css({
        "width": w - 220 + "px"
    });
    $.ajax({
        url: getRootPath_web() + "/dept/select",
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
                //设置navbar
                navbar.set({
                    spreadOne: true,
                    elem: '#admin-navbar-side',
                    cached: true,
                    data: treeData
                });
                //渲染navbar
                navbar.render();
                //监听点击事件
                navbar.on('click(side)', function (data) {
                    tab.tabAdd(data.field);
                });
            }
            else {
                layer.msg(data.desc);
            }
        }
    });

    function role(roleId) {
        var str = "";
        $("#role").html("");
        $.ajax({
            url: getRootPath_web() + "/user/roles/",
            type: "GET",
            dataType: "json",
            data: {},
            success: function (data) {
                if (data.code === "200") {
                    var roles = data.data;
                    var selectObj = $("#role");
                    if (roleId == null) {
                        for (var i = 0; i < roles.length; i++) {
                            var id = roles[i].role_id;
                            str += "<option value=" + id + ">" + roles[i].role_name + "</option>";
                        }
                    } else {
                        for (var j = 0; j < roles.length; j++) {
                            var id1 = roles[j].role_id;
                            if (roleId === id1) {
                                str += "<option selected='selected' value=" + id1 + ">" + roles[j].role_name + "</option>";
                            } else {
                                str += "<option value=" + id1 + ">" + roles[j].role_name + "</option>";
                            }
                        }
                    }
                } else {
                    str += "<option value=" + ">本系统无角色、请联系管理员" + "</option>";
                }
                selectObj.append(str);
                form.render('select');
            }
        });
    }

    // 进入用户管理 加载全部用户
    render(null);

    // 渲染点击部门后的用户表格
    function render(deptId) {
        var url = "";
        if (deptId == null) {
            url = getRootPath_web() + "/user/select";
        } else {
            url = getRootPath_web() + "/user/select?deptId=" + deptId;
        }
        //列表
        var app = table.render({
            elem: '#idTest'
            , url: url //数据接口
            , width: w - 220
            , page: true //开启分页
            , skin: "nob"
            , cols: [[ //表头
                {title: '序号', sort: true, toolbar: '#indexTpl', fixed: 'left', width: 70}
                , {field: 'user_name', title: '用户名'}
                , {field: 'name', title: '姓名'}
                , {field: 'phone', title: '电话'}
                , {field: 'email', title: '邮箱'}
                , {field: 'last_login', title: '上次登录'}
                , {field: 'status', title: '禁用', toolbar: '#checked', unresize: true, width: 70}
                , {title: '操作', toolbar: '#barDemo', align: "center"}
            ]]
        });
    }
    $("#left_ul").css({
    	"height":h-60+"px",
    })
    $(".layui-table-body").css({
        "max-height": h - 130 + "px"
    });
    form.on('switch(filter)', function (obj) {
        var status = 1;
        if (obj.elem.checked) {
            status = 0;
        }
        $.ajax({
            url:getRootPath_web() +  "/user/disabled/" + this.value,
            type: "POST",
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify({status: status}),
            success: function (data) {
                if (data === "200") {
                    if (status === 0) {
                        layer.msg('禁用成功');
                    }
                    else {
                        layer.msg('启用成功');
                    }
                } else {
                    layer.msg(data.desc);
                }

            }
        });
    });
    table.on('tool(demo)', function (obj) {
        var data = obj.data;
        var userId = data.user_id;
        $.ajax({
            url: getRootPath_web() + "/user/select/" + userId,
            type: "GET",
            dataType: "json",
            contentType: 'application/json;charset=UTF-8',
            data: {},
            success: function (data) {
                if (data.code === "200") {
                    var roleId = data.data.roleId;
                    if (roleId != null) {
                        role(roleId);
                    } else {
                        role(null);
                    }
                    var user = data.data.user;
                    $("#user_name").val(user.user_name);
                    $("#password").val(user.password);
                    $("#name").val(user.name);
                    $("#phone").val(user.phone);
                    $("#email").val(user.email);
                } else {
                    layer.msg('加载数据失败');
                }

            }
        });
        if (obj.event === 'compile') {
            layer.open({
                type: 1,
                title: "编辑用户",
                area: ['490px', '500px'],
                btn: ['确定', '取消'],
                content: $("#form_bnt"),
                yes: function () {
                    var roleId = $("#role").select().val();
                    if (roleId == null || roleId === "") {
                        layer.msg('请选择角色');
                    }
                    // 获取表单数据
                    var data = {
                        user_name: $("#user_name").val(),
                        password: $("#password").val(),
                        name: $("#name").val(),
                        phone: $("#phone").val(),
                        email: $("#email").val()
                    };
                    $.ajax({
                        url: getRootPath_web() + "/user/update/" + userId + "/" + roleId,
                        type: "PUT",
                        dataType: "json",
                        contentType: 'application/json;charset=UTF-8',
                        data: JSON.stringify(data),
                        success: function (data) {
                            if (data.code === "200") {
                                clear();// 清空表单数据
                                layer.closeAll();
                                layer.msg(data.desc);
                                $(".layui-laypage-btn").click();
                            } else {
                                layer.msg(data.desc);
                            }
                        }
                    });
                },
                btn2: function () {
                    clear();
                },
                end: function () {
                    clear();
                }
            })
        } else if (obj.event === 'delete') {
            layer.confirm('确定要删除吗？', function (index) {
                $.ajax({
                    url:getRootPath_web() +  "/user/delete/" + data.user_id,
                    type: "DELETE",
                    dataType: "json",
                    contentType: 'application/json;charset=UTF-8',
                    data: {},
                    success: function (data) {
                        if (data.code === "200") {
                            layer.msg(data.desc);
                            layer.close(index);
                            $(".layui-laypage-btn").click();
                        } else {
                            layer.msg(data.desc);
                            layer.close(index);
                        }

                    }
                });
            });
            //
        } else if (obj.event === "jurisdiction") {
            userId = data.user_id;
            // 展示权限树
            perms(userId);
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
                            url: getRootPath_web() + "/user/perm/" + userId,
                            type: "POST",
                            dataType: "json",
                            data: {perms: chk_value.join(',')},
                            success: function (data) {
                                if (data.code === "200") {
                                    layer.msg(data.desc);
                                    $("#dataTables-organization_wrapper").empty()
                                    $(".layui-laypage-btn").click();
                                    layer.closeAll();
                                } else {
                                    $("#dataTables-organization_wrapper").empty()
                                    layer.msg(data.desc);
                                    layer.closeAll();
                                }
                            }
                        });
                    }
                },
                btn2: function () {
                    $("#dataTables-organization_wrapper").empty()
                    layer.closeAll();
                },
                end: function () {
                    $("#dataTables-organization_wrapper").empty()
                    layer.closeAll();
                }
            })
        }
    });
    //用户管理新增
    $('body').delegate('.add_uer', 'click', function (e) {
        e.stopPropagation();
        role();
        layer.open({
            type: 1,
            title: "新增用户",
            area: ['490px', '500px'],
            btn: ['确定', '取消'],
            content: $("#form_bnt"),
            yes: function () {
                var select = $("#role").select();
                var roleId = select.val();
                if (roleId == null || roleId === "") {
                    layer.msg('请选择角色');
                }
                var data = {
                    user_name: $("#user_name").val(),
                    password: $("#password").val(),
                    name: $("#name").val(),
                    phone: $("#phone").val(),
                    email: $("#email").val()
                };
                $.ajax({
                    url: getRootPath_web() + "/user/insert/" + roleId,
                    type: "POST",
                    dataType: "json",
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify(data),
                    success: function (data) {
                        if (data.code === "200") {
                            clear();
                            layer.closeAll();
                            layer.msg(data.desc);
                            $(".layui-laypage-btn").click();
                        } else {
                            layer.msg(data.desc);
                        }

                    }
                });
            },
            btn2: function () {
                clear();
            }
        })
    });

    // 提交表单后 清空表单
    function clear() {
        $("#user_name").val("");
        $("#password").val("");
        $("#name").val("");
        $("#phone").val("");
        $("#email").val("");
    }

    //监听点击事件 左侧导航点击请求数据
    $('body').delegate('.site_active_two', 'click', function (e) {
        e.stopPropagation();
        render($(this).attr("id"));
        $(this).parents("#left_ul").find(".site_active_two").children().removeClass("click_blue");
        $(this).children().addClass("click_blue");
    });
});

// 加载用户拥有的权限
function perms(userId) {
    /**
     * [config treeGrid配置项对象]
     * @type {Object}
     */
    var datas = null;
    $.ajax({
        url: getRootPath_web() + "/user/perm/" + userId,
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
                datas = toTreeData(data, attributes);
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
    // console.log(id + ", " + index + ", " + TreeGrid.jsonToStr(data));
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
    return row[col.dataField] || "";
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
            


