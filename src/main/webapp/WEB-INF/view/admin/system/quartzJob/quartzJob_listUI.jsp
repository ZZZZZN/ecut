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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<jsp:include page="/static/common/head.jsp" flush="true" />
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
								<h5>任务管理</h5>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<div class="pull-left">
											<shiro:hasPermission name="quartzJob:insert">
												<button title="添加" id="insertBtn" data-placement="left" data-toggle="tooltip" class="btn btn-white btn-sm">
													<i class="fa fa-plus"></i> 添加
												</button>
											</shiro:hasPermission>
										</div>
									</div>
								</div>
								<table id="datatable" class="table table-striped table-bordered table-hover table-krt">
									<thead>
										<tr>
											<th>序号</th>
											<th>任务名</th>
											<th>组名</th>
											<th>cron表达式</th>
											<th>类路径</th>
											<th>springId</th>
											<th>方法名</th>
											<th>状态</th>
											<th>描述</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody>
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
	<!-- DataTables -->
	<script src="<%=basePath%>static/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="<%=basePath%>static/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="<%=basePath%>static/skin/js/common.js"></script>
	<!-- page script -->
	<script type="text/javascript">
		var datatable;
	    function initDatatable() {
	        datatable = $('#datatable').DataTable({
				"dom": 'rt<"row"<"col-sm-6"il><"col-sm-6"p>>',
				"lengthChange": true,//选择lenth
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
	                "url": "<%=basePath%>admin/system/quartzJob/quartzJob_list",
	                "type": "post"
	            },
	            "columns": [
	                {"data": "id", "width": "5%"},
	                {"data": "jobName", "width": "10%"},
	                {"data": "jobGroup", "width": "10%"},
	                {"data": "cronExpression", "width": "10%"},
	                {"data": "beanClass", "width": "15%"},
	                {"data": "springId", "width": "5%"},
	                {"data": "methodName", "width": "10%"},
	                {"data": "jobStatus", "width": "5%",
	                	render: function ( data, type, row ) {
	                		if(data=='1'){
	                			return '<span class="badge bg-green">正在运行</span>';
	                		}else{
	                			return '<span class="badge bg-red">停止中</span>';
	                		}
	                	}
	                },
	                {"data": "description", "width": "15%"}
	            ], 
	            "columnDefs": [
	                {
	                    "targets":9,
	                    "data": "id",
	                    "width": "15%",
	                    "render": function(data, type, row) {
	                        if(row.jobStatus=='0'){
	                        	return  ' <shiro:hasPermission name="quartzJob:startOrstop">'
			                        	+'<button class="btn btn-xs btn-info startOrstopBtn" rid="'+row.id+'" jobStatus="'+row.jobStatus+'">'
			                        	+'<i class="fa fa-eye fa-btn"></i>启动任务'
			                        	+'</button>'
			                        	+'</shiro:hasPermission>'
			                        	+' <shiro:hasPermission name="quartzJob:update">'
			                        	+'<button class="btn btn-xs btn-warning updateBtn" rid="'+row.id+'">'
			                        	+'<i class="fa fa-edit fa-btn"></i>修改'
			                        	+'</button>'
			                        	+'</shiro:hasPermission>'
			                        	+' <shiro:hasPermission name="quartzJob:delete">'
			                        	+'<button class="btn btn-xs btn-danger deleteBtn" rid="'+row.id+'" quartzJobCode="'+row.quartzJobCode+'">'
			                        	+'<i class="fa fa-trash fa-btn"></i>删除'
			                        	+'</button>'
			                        	+'</shiro:hasPermission>';
	                        }else if(row.jobStatus=='1'){
	                        	return  ' <shiro:hasPermission name="quartzJob:startOrstop">'
			                        	+'<button class="btn btn-xs btn-info startOrstopBtn" rid="'+row.id+'" jobStatus="'+row.jobStatus+'">'
			                        	+'<i class="fa fa-eye fa-btn"></i>停止任务'
			                        	+'</button>'
			                        	+'</shiro:hasPermission>'
			                        	+' <shiro:hasPermission name="quartzJob:doOnce">'
			                        	+'<button class="btn btn-xs btn-success doOnceBtn" rid="'+row.id+'" jobStatus="'+row.jobStatus+'">'
			                        	+'<i class="fa fa-bol fa-btn"></i>立即执行一次'
			                        	+'</button>'
			                        	+'</shiro:hasPermission>';
	                        }
	                    }
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
   	   //jequery
   	   $(function(){
   		   //pace监听ajax
  	    	$(document).ajaxStart(function() {
				Pace.restart();
			});
   		   //初始化datatable
   		   initDatatable();
	   	   //新增
	   	   $("#insertBtn").click(function(){
	   			openDialog("新增表单","<%=basePath%>admin/system/quartzJob/quartzJob_insertUI","800px", "450px","");
	   	   });
	   	  //启动or停止任务
	   	   $(document).on("click",".startOrstopBtn",function(){
	   			var id = $(this).attr("rid");
	   			var jobStatus = $(this).attr("jobStatus");
	   			var tip="";
	   			if(jobStatus=='0'){
	   				tip="启动";
	   			}else{
	   				tip="停止";
	   			}
	   			var fun = function(){
		   	    	 $.ajax({   
				         type: "POST",
				         url:"<%=basePath%>admin/system/quartzJob/quartzJob_startOrStop?id="+id+"&jobStatus="+jobStatus,
				         data:$('#krtForm').serialize(),// 要提交的表单
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
		   	    	}
		   	   confirmx("你确定"+tip+"任务吗？",fun);
	   			
	   	   });
	   	   //修改
	   	   $(document).on("click",".updateBtn",function(){
	   		    var id = $(this).attr("rid");
	   			openDialog("修改表单","<%=basePath%>admin/system/quartzJob/quartzJob_updateUI?id="+id,"800px", "450px","");
	   	   });
	   	   //删除
	   	   $(document).on("click",".deleteBtn",function(){
	   	    	var id = $(this).attr("rid");
	   	    	var quartzJobCode = $(this).attr("quartzJobCode");
	   	    	var fun = function(){
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/quartzJob/quartzJob_delete?id="+id+"&quartzJobCode="+quartzJobCode,
			         data:$('#krtForm').serialize(),// 要提交的表单
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
	   	    	}
	   	    	confirmx("你确定删除吗？",fun);
	   	    });
	   	   //立即执行
	   	   $(document).on("click",".doOnceBtn",function(){
	   		    var id = $(this).attr("rid");
		   		 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/quartzJob/quartzJob_doOnce?id="+id,
			         beforeSend:function(){
			         	return loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         	}else{
			         		top.layer.msg("操作失败");
			         	} 
			         },
			         error: function(){
			        	 closeloading();
			         }
			     });
			});
   	   });   
	</script>
</body>
</html>
