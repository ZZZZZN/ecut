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
    <link rel="stylesheet" href="<%=basePath%>static/plugins/datatables/dataTables.bootstrap.css">
</head>
<body class="hold-transition sidebar-mini body-bg">
<div class="wrapper">
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header">
                            <h5>信工学院教师信息导出</h5>
                        </div>
                        <div class="box-body">
                            <div class="row">
                                <div class="form-group pull-left" style="width: 200px;height: 30px;margin-left: 15px">
                                    <label>请选择专业</label>
                                    <select class="form-control" id="major_name" name="major_name"
                                            onchange="handleChange(event)">
                                        <c:forEach items="${map}" var="major">
                                            <option value="${major.major_name}">${major.major_name}</option>
                                        </c:forEach>
                                    </select>
                                    <%--<button id="searchBtn" class="btn btn-primary btn-sm"
                                            style="position: relative;left: 220px;top: -32px">
                                        <i class="fa fa-search fa-btn"></i>搜索
                                    </button>--%>
                                </div>
                            </div>
                            <form action="#" id="stuForm" class="form-horizontal">
                                <table id="datatable" class="table table-striped table-bordered table-hover table-krt">
                                    <thead>
                                    <tr>
                                        <th>序号</th>
                                        <th>姓名</th>
                                        <th>工号</th>
                                        <th>学院</th>
                                        <th>职称</th>
                                        <th>所在系</th>
                                        <th>学历</th>
                                        <th>备注</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                    <input type="hidden" id="major_name1" name="major_name1">
                                    <input class="btn btn-primary" type="submit" value="导出excel" onclick="doSubmit()"
                                           style="position: relative; left: 250px;top: -20px">
                                </table>
                            </form>
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

<!-- jQuery 2.1.4 -->
<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<!-- DataTables -->
<script src="<%=basePath%>static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="<%=basePath%>static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="<%=basePath%>static/bootstrap/js/bootstrap.js"></script>
<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
<script src="<%=basePath%>static/skin/js/common.js"></script>
<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
<!-- page script -->
<script type="text/javascript">
    var datatable;
    var flag = 0;

    function check() {
        console.log($("#major_name").val());
    }

    function initDatatable() {
        flag++;
        datatable = $('#datatable').DataTable({
            "lengthChange": false,//选择lenth
            "autoWidth": false,//自动宽度
            "searching": false,//搜索
            "processing": false,//loding
            "serverSide": true,//服务器模式
            "ordering": false,//排序
            "pageLength": 10,//初始化lenth
            "language": {
                "url": "<%=basePath%>static/plugins/datatables/language/cn.json"
            },
            "ajax": {
                "url": "<%=basePath%>ruanjian/formExport/teachers_xg_export",
                "type": "post",
                "data": {
//	                    major_name: $("#major_name").val()
                }
            },
            "columns": [
                {"data": "id", "width": "7%"},
                {"data": "NAME", "width": "18%"},
                {"data": "username", "width": "10%"},
                {"data": "institute", "width": "13%"},
                {"data": "title_level", "width": "13%"},
                {"data": "department", "width": "13%"},
                {"data": "education", "width": "13%"},
                {"data": "note", "width": "18%"},
            ],
            "columnDefs": [

            ],
            "fnDrawCallback": function () {
                var api = this.api();
                var startIndex = api.context[0]._iDisplayStart;//获取到本页开始的条数
                api.column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = startIndex + i + 1;
                });
            }
        });
    }

    $(function () {

        //pace监听ajax
        $(document).ajaxStart(function () {
            Pace.restart();
        });

        //初始化datatable
        initDatatable();

        <%--$("#searchBtn").on('click', function () {--%>
            <%--var url = '<%=basePath%>ruanjian/formExport/teachers_xg_export?major_name=' + $("#major_name").val();--%>
            <%--url = encodeURI(url);--%>
            <%--console.log(url);--%>
            <%--datatable.ajax.url(url).load();--%>
        <%--});--%>



        //新增
        $("#insertBtn").click(function () {
            location.href = "<%=basePath%>ruanjian/course/title_insertUI";
        });

        //查看
        $(document).on("click", ".seeBtn", function () {
            var id = $(this).attr("rid");
            location.href = "<%=basePath%>ruanjian/course/title_seeUI?id=" + id;
            <%--openDialogView("查看表单","<%=basePath%>ruanjian/course/title_seeUI?id="+id,"800px", "380px","");--%>
        });

        //修改
        $(document).on("click", ".updateBtn", function () {
            var id = $(this).attr("rid");
            location.href = "<%=basePath%>ruanjian/course/title_updateUI?id=" + id;
            <%--openDialog("修改表单","<%=basePath%>ruanjian/course/title_updateUI?id="+id,"800px", "380px","");--%>
        });

        //删除
        $(document).on("click", ".deleteBtn", function () {
            var id = $(this).attr("rid");
            var fun = function () {
                $.ajax({
                    type: "POST",
                    url: "<%=basePath%>ruanjian/course/title_delete?id=" + id,
                    beforeSend: function () {
                        return loading();
                    },
                    success: function (msg) {
                        closeloading();
                        if (msg.state == 'success') {
                            top.layer.msg("操作成功");
                            refreshTable(datatable);
                        } else {
                            top.layer.msg("操作失败");
                        }
                    },
                    error: function () {
                        closeloading();
                    }
                });
            };
            confirmx("你确定删除吗？", fun);
        });
    });

    function doSubmit() {
        $("#stuForm").attr("action", "<%=basePath%>formExport/teachers_xg?major_name1=" + $("#major_name1").val());
        $("#stuForm").submit();
    }

    function handleChange(e) {
        console.log($("#major_name").find("option:selected").text());
        $("#major_name1").val($("#major_name").find("option:selected").text());
        console.log($("#major_name1").val());
        var url = '<%=basePath%>ruanjian/formExport/teachers_xg_export?major_name=' + $("#major_name").val();
        url = encodeURI(url);
        console.log(url);
        datatable.ajax.url(url).load();
    }
</script>
</body>
</html>
