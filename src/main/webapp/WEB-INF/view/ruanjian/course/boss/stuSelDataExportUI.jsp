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
	}
	.search-input{
		margin-right:10px;
		margin-left: 2px;
	}
	.exportBtn{
		float : right;
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
								<h5>学生选题数据导出</h5>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<span>学号: </span><input type="text" name="stuNo" id="stuNo" value="" class="form-control input-150 search-input">
										<span>指导老师姓名: </span> <input type="text" name="teacName" id="teacName" value="" class="form-control input-150 search-input">
										<button type="button" id="searchBtn" class="btn btn-primary btn-sm">
											<i class="fa fa-search fa-btn"></i>搜索
										</button>
										<button class="btn btn-primary exportBtn" onclick="doSubmit()">导出excel</button>
									</div>
								</div>
								<form action="#" id="stuForm" class="form-horizontal">
								<table id="datatable" class="table table-striped table-bordered table-hover table-krt">
									<thead>
										<tr>
											<th>序号</th>
											<th>学号</th>
											<th>学生姓名</th>
											<th>学生班级</th>
											<th>设计题目</th>
											<th>课题类型</th>
											<th>课题来源</th>
											<th>指导教师姓名</th>
											<th>职称</th>
										</tr>
									</thead>
									<tbody>
									</tbody>
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
	<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
	<script src="<%=basePath%>static/skin/js/common.js"></script>
	<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
	<!-- page script -->
	<script type="text/javascript">
   		var datatable;
   		var disflag = '未审核';
   	    function initDatatable() {
   	        datatable = $('#datatable').DataTable({
   	            "lengthChange": false,//选择lenth
   	        	"autoWidth": false,//自动宽度
	   			"searching": false,//搜索
	            "processing": false,//loding
	            "serverSide": true,//服务器模式
	            "ordering": false,//排序
	            "pageLength": 10,//初始化length
	            "language": {
	                "url": "<%=basePath%>static/plugins/datatables/language/cn.json"
	            },
	            "ajax": {
	                "url": "<%=basePath%>ruanjian/course/boss/stuSelDataExport",
	                "type": "post",
	                "data": function (d) {
                        d.stuNo = $("#stuNo").val(),
                            d.teacName = $("#teacName").val();

                    }
	            },
	            "columns": [
	                {"data": "id", "width": "7%"},
					{"data": "stuNo", "width": "10%"},
					{"data": "stuName", "width": "13%"},
					{"data": "stuClass", "width": "11%"},
                    {"data": "titleName", "width": "13%"},
                    {"data": "titleType", "width": "13%"},
                    {"data": "titleSource", "width": "13%"},
                    {"data": "teacName", "width": "13%"},
                    {"data": "titleLevel", "width": "16%"}
	            ],
	            "columnDefs": [
	                {
	                    "targets": 8,
	                    "data": "id",
	                    "width": "20%"
	                    /*"render": function(data, type, row) {
                            var optBtn = '';
                            if (row.flag == disflag ) {
                                optBtn = '<shiro:hasPermission name="titleExamine:passOrFail">'
                                    +'<button class="btn mybtn btn-xs btn-success passBtn" rid="'+row.id+'">'
                                    +'<i class="fa fa-check fa-btn"></i>通过'
                                    +'</button>'
                                    +'</shiro:hasPermission>'
                                    +'<shiro:hasPermission name="titleExamine:passOrFail">'
                                    +'<button class="btn mybtn btn-xs btn-danger failBtn" rid="'+row.id+'">'
                                    +'<i class="fa fa-remove fa-btn"></i>不通过'
                                    +'</button>'
                                    +'</shiro:hasPermission>'
                            }
                            return  ' <shiro:hasPermission name="title:see">'
                                +'<button class="btn mybtn btn-xs btn-info seeBtn" rid="'+row.id+'">'
                                +'<i class="fa fa-eye fa-btn"></i>查看'
                                +'</button>'
                                +'</shiro:hasPermission>'
                                +optBtn;
	                    }*/
	                }
	            ],
	            "fnDrawCallback": function(){
	            	var api = this.api();
	            	var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
	            	api.column(0).nodes().each(function(cell, i) {
	            		cell.innerHTML = startIndex + i + 1;
	            	}); 
	            }
	        });
   	    }

        function handleSelect(e) {
            status = e.target.value;
            datatable.ajax.reload();
            <%--$.ajax({--%>
                <%--type: "POST",--%>
                <%--url:"<%=basePath%>ruanjian/course/titleExamine_list",--%>
                <%--data: {--%>
                    <%--status: status--%>
                <%--},--%>
                <%--beforeSend:function(){--%>
                    <%--return loading();--%>
                <%--},--%>
                <%--success: function(msg) {--%>
                    <%--closeloading();--%>
                    <%--if(msg.state=='success'){--%>
<%--//                        top.layer.msg("审核成功");--%>
                        <%--refreshTable(datatable);--%>
                    <%--}else{--%>
                        <%--top.layer.msg("筛选失败");--%>
                    <%--}--%>
                <%--},--%>
                <%--error: function(){--%>
                    <%--closeloading();--%>
                <%--}--%>
            <%--});--%>
        }

        function doSubmit() {
            $("#stuForm").attr("action", "<%=basePath%>ruanjian/course/boss/exportExcelForAdmin");
            $("#stuForm").submit();
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
	   			openDialog("新增表单","<%=basePath%>ruanjian/course/titleExamine_insertUI","800px", "380px","");
	   	    });
	   	    
	   	    //查看
	   	    $(document).on("click",".seeBtn",function(){
	   			var id = $(this).attr("rid");
	   			openDialogView("查看表单","<%=basePath%>ruanjian/course/titleExamine_seeUI?id="+id,"800px", "380px","");
	   	    });
	   	   
	   	    //修改
	   	    $(document).on("click",".updateBtn",function(){
	   		    var id = $(this).attr("rid");
	   			openDialog("修改表单","<%=basePath%>ruanjian/course/titleExamine_updateUI?id="+id,"800px", "380px","");
	   	    });

            $("#searchBtn").on('click', function () {
                datatable.ajax.reload();
            });
	   	   
	   	    //删除
	   	    $(document).on("click",".deleteBtn",function(){
	   	    	var id = $(this).attr("rid");
	   	    	var fun = function(){
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>ruanjian/course/titleExamine_delete?id="+id,
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
                        url:"<%=basePath%>ruanjian/course/titleExamine_passOrFail?id=" +id + "&status=2&dr=0",
                        beforeSend:function(){
                            return loading();
                        },
                        success: function(msg) {
                            closeloading();
                            if(msg.state=='success'){
                                top.layer.msg("审核成功");
                                refreshTable(datatable);
                            }else{
                                top.layer.msg(msg.state);
                            }
                        },
                        error: function(){
                            closeloading();
                        }
                    });
                };
                confirmx("允许该学生选择该课题？",fun);
            });

            //未通过
            $(document).on("click",".failBtn",function(){
                var id = $(this).attr("rid");
                var fun = function(){
                    $.ajax({
                        type: "POST",
                        url:"<%=basePath%>ruanjian/course/titleExamine_passOrFail?id=" +id + "&status=3&dr=1",
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
       
	    }); 
 	</script>
</body>
</html>
