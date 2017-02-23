<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/static/common/head.jsp" flush="true"/>
    <link rel="stylesheet" href="<%=basePath%>static/skin/css/base.css">
    <link rel="stylesheet" href="<%=basePath%>static/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>static/plugins/jquery-treeTable/css/jquery.treeTable.css"/>
    <link rel="stylesheet" href="<%=basePath%>static/plugins/jquery-treeTable/css/jquery.treetable.theme.default.css"/>
</head>
<body class="hold-transition sidebar-mini body-bg">
<div class="wrapper">
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-2">
                    <div class="box-header">
                        <h5>项目资源树</h5>
                        <div class="box-tools">
                            <button class="btn btn-box-tool" data-widget="collapse" id="refBtn">
                                <i class="fa fa-refresh"></i>
                            </button>
                        </div>
                    </div>
                    <div class="box-body">
                        <div id="resTree" class="ztree"></div>
                        <!-- 参数 -->
                        <input type="hidden" id="pid" value="">
                    </div>
                </div>
                <div class="col-xs-10">
                    <div class="box">
                        <div class="box-header">
                            <h5>资源管理</h5>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body"  id="boxbody">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="pull-left">
                                        <shiro:hasPermission name="res:insert">
                                            <button title="添加" id="insertBtn" data-placement="left"
                                                    data-toggle="tooltip" class="btn btn-white btn-sm">
                                                <i class="fa fa-plus"></i> 添加
                                            </button>
                                        </shiro:hasPermission>
                                    </div>
                                </div>
                            </div>
                            <table id="tree_table" class="table table-striped table-bordered table-hover table-krt">
                                <thead>
                                <tr>
                                    <th>名称</th>
                                    <th>连接</th>
                                    <th>类型</th>
                                    <th>权限标志</th>
                                    <th>排序</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="row" varStatus="status">
                                    <tr id="${row.id}" ${row.hasChild=='true'?'hasChild="true"':''}>
                                        <td width="20%">${row.name}</td>
                                        <td width="25%">${row.url}</td>
                                        <td width="10%">
                                            <c:if test="${row.type=='url'}">
                                                <span class="badge bg-red">菜单</span>
                                            </c:if>
                                            <c:if test="${row.type=='button'}">
                                                <span class="badge bg-light-blue">按钮</span>
                                            </c:if>
                                        </td>
                                        <td width="15%">${row.permission}</td>
                                        <td width="10%">${row.sortNo}</td>
                                        <td width="20%">
                                            <shiro:hasPermission name="res:see">
                                                <button class="btn btn-xs btn-info seeBtn" rid="${row.id}">
                                                    <i class="fa fa-eye fa-btn"></i>查看
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="res:update">
                                                <button class="btn btn-xs btn-warning updateBtn" rid="${row.id}">
                                                    <i class="fa fa-edit fa-btn"></i>修改
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="res:delete">
                                                <button class="btn btn-xs btn-danger deleteBtn" rid="${row.id}">
                                                    <i class="fa fa-trash fa-btn"></i>删除
                                                </button>
                                            </shiro:hasPermission>
                                            <shiro:hasPermission name="res:insert">
                                                <button class="btn btn-xs btn-success addBtn" rid="${row.id}">
                                                    <i class="fa fa-plus fa-btn"></i>添加子资源
                                                </button>
                                            </shiro:hasPermission>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->

                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

</div>
<!-- ./wrapper -->

<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
<script src="<%=basePath%>static/skin/js/common.js"></script>
<script src="<%=basePath%>static/skin/js/browser.js"></script>
<script src="<%=basePath%>static/plugins/jquery-treeTable/javascripts/jquery.treeTable.js"></script>
<script type="text/javascript" src="<%=basePath%>static/plugins/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=basePath%>static/plugins/ztree/jquery.ztree.excheck-3.5.js"></script>
<!-- page script -->
<script language="javascript" type="text/javascript">

    //pace监听ajax
    $(document).ajaxStart(function () {
        Pace.restart();
    });

    $("#refBtn").click(function(){
        setCheck();
    });

    //新增
    $("#insertBtn").click(function () {
        var pid = $("#pid").val();
        openDialog("新增表单", "<%=basePath%>admin/system/res/res_insertUI?&id="+pid, "800px", "500px", "");
    });
    //查看
    $(document).on("click",".seeBtn",function(){
        var id = $(this).attr("rid");
        openDialogView("查看表单", "<%=basePath%>admin/system/res/res_seeUI?id=" + id, "800px", "500px", "");
    });
    //修改
    $(document).on("click",".updateBtn",function(){
        var id = $(this).attr("rid");
        openDialog("修改表单", "<%=basePath%>admin/system/res/res_updateUI?id=" + id, "800px", "500px", "");
    });
    //添加子资源
    $(document).on("click",".addBtn",function(){
            var id = $(this).attr("rid");
            openDialog("新增表单", "<%=basePath%>admin/system/res/res_insertUI?id=" + id, "800px", "500px", "");
    });
    //删除
    $(document).on("click",".deleteBtn",function(){
        var id = $(this).attr("rid");
        var fun = function () {
            $.ajax({
                type: "POST",
                url: "<%=basePath%>admin/system/res/res_delete?id=" + id,
                data: $('#krtForm').serialize(),// 要提交的表单
                beforeSend: function () {
                    return loading();
                },
                success: function (msg) {
                    closeloading();
                    if (msg.state == 'success') {
                        top.layer.msg("操作成功");
                        var pid = $("#pid").val();
                        initTable(pid);
                    } else {
                        top.layer.msg("操作成功");
                    }
                },
                error: function () {
                    closeloading();
                }
            });
        }
        confirmx("你确定删除此模块及下级模块吗？", fun);
    });
</script>
<script type="text/javascript">
    //ztree点击事件
    function zTreeOnClick(event, treeId, treeNode) {
        var pid = treeNode.id;
        $("#pid").val(pid);
        initTable(pid);
    };
    //ztree设置
    var setting = { view:{
        selectedMulti:false,
        dblClickExpand:false
    },
        data:{
            simpleData:{enable:true}
        },
        callback:{
            onClick: zTreeOnClick
        }
    };

    function setCheck() {
        $.ajax({
            type: "POST",
            url:"<%=basePath%>admin/system/res/res_tree",
            async:false,
            beforeSend:function(){
                loading();
            },
            success: function(data) {
                $.fn.zTree.init($("#resTree"), setting, data);
                closeloading();

            },
            error: function(){
                closeloading();
            }
        });
    }

    // 默认选择节点
    function selectCheckNode(){
        var id = 0;
        var tree = $.fn.zTree.getZTreeObj("resTree");
        var node = tree.getNodeByParam("id",id);
        tree.selectNode(node);
        //初始化权限系统
        $("#pid").val(id);
        initTable(id);
    }

    //初始化ztree
    setCheck();
    selectCheckNode();

    function isNull(str){
        if(str==null || str=='null'){
            return "";
        }else{
            return str;
        }
    }
    //加载父表
    function initTable(pid){
        $.ajax({
            type: "POST",
            url:"<%=basePath%>admin/system/res/res_list?pid="+pid,
            beforeSend:function(){
                loading();
            },
            success: function(resList) {
                closeloading();
                var tbody='';
                for(var i=0; i<resList.length;i++){
                    var row =  resList[i];
                    var hasChild = "";
                    if(row.hasChild=='true'){
                        hasChild = 'data-tt-branch="true"';
                    }
                    var type = row.type;
                    if(type=='url'){
                        type = '<span class="badge bg-red">菜单</span>';
                    }else{
                        type='<span class="badge bg-light-blue">按钮</span>';
                    }
                    var tr =  '<tr data-tt-id="'+row.id+'" '+hasChild+'>'
                            +"<td>"+isNull(row.name)+"</td>"
                            +"<td>"+isNull(row.url)+"</td>"
                            +"<td>"+type+"</td>"
                            +"<td>"+isNull(row.permission)+"</td>"
                            +"<td>"+isNull(row.sortNo)+"</td>"
                            +"<td>"
                            +' <shiro:hasPermission name="res:see"><button class="btn btn-xs btn-info seeBtn" rid="'+row.id+'" pid="'+row.pid+'"><i class="fa fa-eye fa-btn"></i>查看</button></shiro:hasPermission>'
                            +' <shiro:hasPermission name="res:update"><button class="btn btn-xs btn-warning updateBtn" rid="'+row.id+'" pid="'+row.pid+'"><i class="fa fa-edit fa-btn"></i>修改</button></shiro:hasPermission>'
                            +' <shiro:hasPermission name="res:delete"><button class="btn btn-xs btn-danger deleteBtn" rid="'+row.id+'"><i class="fa fa-trash fa-btn"></i>删除</button></shiro:hasPermission>'
                            +' <shiro:hasPermission name="res:insert"><button class="btn btn-xs btn-success addBtn" rid="'+row.id+'"><i class="fa fa-plus fa-btn"></i>添加子资源</button></shiro:hasPermission>'
                            +"</td>"
                            +"</tr>";
                    tbody = tbody + tr;
                }

                $("#tree_table").remove();

                var html = '<table id="tree_table" class="table table-striped table-bordered table-hover table-krt"><thead><tr><th>名称</th><th>连接</th><th>类型</th><th>权限标志</th><th>排序</th><th>操作</th></tr></thead><tbody id="tbody">'+tbody+'</tbody></table>';

                $("#boxbody").append(html);

                initTreeTable();
            },
            error: function(){
                closeloading();
            }
        });
    }

    //初始化treeTable
    function initTreeTable(){
        $("#tree_table").treetable({
            expandable:     true,
            onNodeExpand:   nodeExpand,
            onNodeCollapse: nodeCollapse
        });
    }

    //展开事件
    function nodeExpand () {
        getNodeViaAjax(this.id);
    }
    //收缩事件
    function nodeCollapse () {

    }
    //加载子节点
    function getNodeViaAjax(pid) {
        $.ajax({
            type: 'POST',
            url:"<%=basePath%>admin/system/res/res_list?ptag="+$("#ptag").val()+"&pid="+pid,
            beforeSend:function(){
                loading();
            },
            success: function(data) {
                closeloading();
                var childNodes = data;
                if(childNodes) {
                    var parentNode = $("#tree_table").treetable("node", pid);
                    for (var i = 0; i < childNodes.length; i++) {
                        var node = childNodes[i];
                        var nodeToAdd = $("#tree_table").treetable("node",node.id);
                        if(!nodeToAdd) {
                            var hasChild = "";
                            if(node.hasChild=='true'){
                                hasChild = 'data-tt-branch="true"';
                            }
                            var type = node.type;
                            if(type=='url'){
                                type = '<span class="badge bg-red">菜单</span>';
                            }else{
                                type='<span class="badge bg-light-blue">按钮</span>';
                            }
                            var row =  '<tr data-tt-id="'+node.id+'" '+hasChild+' data-tt-parent-id="'+pid +'">'
                                    +"<td>"+isNull(node.name)+"</td>"
                                    +"<td>"+isNull(node.url)+"</td>"
                                    +"<td>"+type+"</td>"
                                    +"<td>"+isNull(node.permission)+"</td>"
                                    +"<td>"+isNull(node.sortNo)+"</td>"
                                    +"<td>"
                                    +' <shiro:hasPermission name="res:see"><button class="btn btn-xs btn-info seeBtn" rid="'+node.id+'" pid="'+node.pid+'"><i class="fa fa-eye fa-btn"></i>查看</button></shiro:hasPermission>'
                                    +' <shiro:hasPermission name="res:update"><button class="btn btn-xs btn-warning updateBtn" rid="'+node.id+'" pid="'+node.pid+'"><i class="fa fa-edit fa-btn"></i>修改</button></shiro:hasPermission>'
                                    +' <shiro:hasPermission name="res:delete"><button class="btn btn-xs btn-danger deleteBtn" rid="'+node.id+'"><i class="fa fa-trash fa-btn"></i>删除</button></shiro:hasPermission>'
                                    +' <shiro:hasPermission name="res:insert"><button class="btn btn-xs btn-success addBtn" rid="'+node.id+'"><i class="fa fa-plus fa-btn"></i>添加子资源</button></shiro:hasPermission>'
                                    +"</td>"
                                    +"</tr>";
                            $("#tree_table").treetable("loadBranch", parentNode, row);
                        }

                    }

                }

            },
            error:function(error){
            },
            dataType: 'json'
        });
    }
</script>
</body>
</html>

