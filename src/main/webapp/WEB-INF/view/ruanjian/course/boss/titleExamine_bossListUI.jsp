<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/static/common/head.jsp" flush="true" />
	<link rel="stylesheet" href="<%=basePath%>static/skin/css/base.css">
	<link rel="stylesheet" href="<%=basePath%>static/plugins/datatables/dataTables.bootstrap.css">
</head>
<style>
	.mybtn{
		margin-left: 5px;
		margin-right: 5px;
	}
	.batch,.exportBtn{
		float : right;
		height: 30px;
		margin-left: 50px;
	}
	.search-input{
		margin-right:10px;
		margin-left: 2px;
	}
	.exportBtn{
		height: 30px;
		margin-left: 50px;
	}
</style>
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
							<h5>选题表管理</h5>
							<a class="btn btn-primary exportBtn" id="export" onclick="doSubmit()">导出excel</a>
							<button class="btn btn-primary batch">批量审核</button>
						</div>
						<div class="box-body">
							<div class="row">
								<div class="col-sm-12">
									<span>课题名称: </span><input type="text" name="titlename" id="titlename" value="" class="form-control input-150 search-input">
									<span>出题老师: </span> <input type="text" name="author" id="author" value="" class="form-control input-150 search-input">
									<button type="button" id="searchBtn" class="btn btn-primary btn-sm">
										<i class="fa fa-search fa-btn"></i>搜索
									</button>
									<div class="col-sm-3 pull-right">
										<select name="flag" id="flag" class="form-control" onchange="handleSelect(event)">
											<option value="">全部</option>
											<option value="1">待审核</option>
											<option value="2">审核通过</option>
											<option value="3">审核未通过</option>
										</select>
									</div>
									<span class="pull-right" style="height: 34px;line-height: 34px;font-size: 15px;">筛选：</span>
								</div>
							</div>
							<%--<form action="#" id="stuForm" class="form-horizontal">--%>
								<table id="datatable" class="table table-striped table-bordered table-hover table-krt">
									<thead>
									<tr>
										<th > 全选
											<input type="checkbox" name="checkAll" id="checkAll"
												   class="group-checkable" data-set="#sample_1 .checkboxes" />
										</th>
										<th>序号</th>
										<th>课题名称</th>
										<th>出题教师</th>
										<th>课题类型</th>
										<th>适用专业</th>
										<th>适用实训所在地</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
									</thead>
									<tbody>
									</tbody>
								</table>
							<%--</form>--%>
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
<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
<script src="<%=basePath%>static/skin/js/common.js"></script>
<!-- page script -->
<script type="text/javascript">
    var datatable;
    var disflag = '待审核';
    var status = $('#flag').val();
    function initDatatable() {
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
                "url": "<%=basePath%>ruanjian/course/boss/titleExamine_bossList",
                "type": "post",
                "data": function (d) {
                    d.titlename = $("#titlename").val(),
                        d.author = $("#author").val();
                    d.status = status;
                }
            },
            "columns": [
                {
                    className: "td-checkbox",
                    orderable : false,
                    bSortable : false,
                    data : "id",
                    render : function(data, type, row) {
                        var content = '<label style="margin-left:32px;" class="mt-checkbox mt-checkbox-single mt-checkbox-outline">';
                        content += '    <input type="checkbox" name="subBox" id="subBox" value="' + row.titleId+ '" />';
                        content += '</label>';
                        return content;
                    }
                },
                {"data": "titleId", "width": "7%"},
                {"data": "titleName", "width": "12%"},
                {"data": "teacherName", "width": "10%"},
                {"data": "titleType", "width": "10%"},
                {"data": "suitMajorName", "width": "13%"},
                {"data": "suitScope", "width": "10%"},
                {"data": "flag", "width": "10%"},
                {"data": "operate", "width": "20%"},
            ],
            "columnDefs": [
                {
                    "targets": 8,
                    "data": "id",
                    "width": "20%",
                    "render": function(data, type, row) {
                        var optBtn = '';
                        if (row.flag == disflag ) {
                            optBtn = '<shiro:hasPermission name="titleExamine:anoPassOrFail">'
                            +'<button class="btn btn-xs btn-success passBtn" rid="'+row.titleId+'">'
                            +'<i class="fa fa-check fa-btn"></i>通过'
                            +'</button>'
                            +'</shiro:hasPermission>'
                            +' <shiro:hasPermission name="titleExamine:anoPassOrFail">'
                            +'<button class="btn btn-xs btn-danger failBtn" rid="'+row.titleId+'">'
                            +'<i class="fa fa-remove fa-btn"></i>不通过'
                            +'</button>'
                            +'</shiro:hasPermission>'
                        }
                        return ' <shiro:hasPermission name="titleExamine:see">'
                            +'<button class="btn mybtn btn-xs btn-info seeBtn" rid="'+row.titleId+'">'
                            +'<i class="fa fa-eye fa-btn"></i>查看'
                            +'</button>'
                            +'</shiro:hasPermission>'
                            +optBtn;
                    }
                }
            ],
            "fnDrawCallback": function(){
                var api = this.api();
                var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
                /*api.column(0).nodes().each(function(cell, i) {
                    cell.innerHTML = '<input type="checkbox"/>';
                });*/
                //控制序号的位置
                api.column(1).nodes().each(function(cell, i) {
                    cell.innerHTML = startIndex + i + 1;
                });
            }
        });
    }
    function handleSelect(e) {
        status = e.target.value;
        datatable.ajax.reload();
    }
	//导出excel
    function doSubmit() {
        $("#export").attr("href", "<%=basePath%>ruanjian/course/boss/exportExcelForDean");
    }

    $(function(){

        //pace监听ajax
        $(document).ajaxStart(function() {
            Pace.restart();
        });

        //初始化datatable
        initDatatable();

        $("#searchBtn").on('click', function () {
            datatable.ajax.reload();
        });


        //新增
        $("#insertBtn").click(function(){
            location.href = "<%=basePath%>ruanjian/course/title_insertUI";
        });

        //查看
        $(document).on("click",".seeBtn",function(){
            var id = $(this).attr("rid");
            openDialogView("查看表单","<%=basePath%>ruanjian/course/titleExamine_seeUI?id="+id,"800px", "380px","");
        });

        //修改
        $(document).on("click",".updateBtn",function(){
            var id = $(this).attr("rid");
            location.href = "<%=basePath%>ruanjian/course/title_updateUI?id=" + id;
            <%--openDialog("修改表单","<%=basePath%>ruanjian/course/title_updateUI?id="+id,"800px", "380px","");--%>
        });

        //删除
        $(document).on("click",".deleteBtn",function(){
            var id = $(this).attr("rid");
            var fun = function(){
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>ruanjian/course/title_delete?id="+id,
                    beforeSend:function(){
                        return loading();
                    },
                    success: function(msg) {
                        closeloading();
                        if(msg.state=='success'){
                            top.layer.msg("操作成功");
                            refreshTable(datatable);
                        }else{
                            top.layer.msg("操作失败");
                        }
                    },
                    error: function(){
                        closeloading();
                    }
                });
            };
            confirmx("你确定删除吗？",fun);
        });
        //通过
        $(document).on("click",".passBtn",function(){
            var id = $(this).attr("rid");
            var fun = function(){
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>ruanjian/course/boss/titleExamine_anoPassOrFail?id=" +id + "&flag=2",
                    beforeSend:function(){
                        return loading();
                    },
                    success: function(msg) {
                        closeloading();
                        if(msg.state=='success'){
                            top.layer.msg("审核成功");
                            refreshTable(datatable);
                        }else{
                            top.layer.msg("审核失败");
                        }
                    },
                    error: function(){
                        closeloading();
                    }
                });
            };
            confirmx("是否确定课题审核通过？",fun);
        });

        //未通过
        $(document).on("click",".failBtn",function(){
            var id = $(this).attr("rid");
            var fun = function(){
                $.ajax({
                    type: "POST",
                    url:"<%=basePath%>ruanjian/course/boss/titleExamine_anoPassOrFail?id=" +id + "&flag=3",
                    beforeSend:function(){
                        return loading();
                    },
                    success: function(msg) {
                        closeloading();
                        if(msg.state=='success'){
                            top.layer.msg("审核成功");
                            refreshTable(datatable);
                        }else{
                            top.layer.msg("审核失败");
                        }
                    },
                    error: function(){
                        closeloading();
                    }
                });
            };
            confirmx("不允许该学生选择该课题？",fun);
        });

        //全选
        $('input[name="checkAll"]').click(function(){
            if($(this).is(':checked')){
                $('input[name="subBox"]').each(function(){
                    $(this).prop("checked",true);
                });
            }else{
                $('input[name="subBox"]').each(function(){
                    $(this).removeAttr("checked",false);
                });
            }
        });

        //批量审核
        $(document).on("click",".batch",function(){
            var ids = "";
			$('input[name="subBox"]:checked').each(function() {
                ids = ids + $(this).val() + ",";
			});
			if (ids != "") {
                var fun = function(){
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>ruanjian/course/boss/batchUpdate",
                        beforeSend:function(){
                            return loading();
                        },
                        data : {
                            ids : ids
                        },
                        success: function(msg) {
                            closeloading();
                            if(msg.state=='success'){
                                top.layer.msg("审核成功");
                                refreshTable(datatable);
                            }else{
                                top.layer.msg("审核失败");
                            }
                        },
                        error: function(){
                            closeloading();
                        }
                    });
                };
                confirmx("确认所选课题全部通过",fun);
			} else {
                alert("请勾选审核题目!");
			}
        });
    });
</script>
</body>
</html>
