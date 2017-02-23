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
								<h5>用户管理</h5>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<span>用户账号: </span><input type="text" name="username" id="username" value="${para.username}" class="form-control input-150"> 
										<span>用户姓名: </span> <input type="text" name="name" id="name" value="${para.name}" class="form-control input-150"> 
										<span>角色名: </span><input type="text" name="roleName" id="roleName" value="${para.roleName}" class="form-control input-150">
										<button type="button" id="searchBtn" class="btn btn-primary btn-sm">
											<i class="fa fa-search fa-btn"></i>搜索
										</button>
										<shiro:hasPermission name="user:insert">
											<button title="添加" type="button" id="insertBtn" data-placement="left" data-toggle="tooltip" class="btn btn-white btn-sm">
												<i class="fa fa-plus"></i> 添加
											</button>
										</shiro:hasPermission>
									</div>
								</div>
								<table id="datatable" class="table table-striped table-bordered table-hover table-krt">
									<thead>
										<tr>
											<th>序号</th>
											<th>用户账号</th>
											<th>用户姓名</th>
											<th>账号状态</th>
											<th>角色名</th>
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
	                "url": "<%=basePath%>admin/system/user/user_list",
	                "type": "post",
	                "data": function (d) {
	                    d.username = $('#username').val();
	                    d.name = $('#name').val();
	                    d.roleName = $('#roleName').val();
	                }
	            },
	            "columns": [
	                {"data": "id", "width": "10%"},
	                {"data": "username", "width": "20%"},
	                {"data": "name", "width": "20%"},
	                {"data": "status", "width": "15%",
	                	render: function ( data, type, row ) {
	                		if(data=='0'){
	                			return '<span class="badge bg-green">正常</span>';
	                		}else{
	                			return '<span class="badge bg-red">禁用</span>';
	                		}
	                	}
	                },
	                {"data": "roleName", "width": "15%"}
	            ],
	            "columnDefs": [
	                {
	                    "targets": 5,
	                    "data": "id",
	                    "width": "15%",
	                    "render": function(data, type, row) {
	                    	var button = "";
	                    	if(row.status == '0'){
	                    		button =   ' <shiro:hasPermission name="user:cancel">'
					                        +'<button class="btn btn-xs btn-danger cancelBtn" rid="'+row.id+'" status="1">'
					                        +'<i class="fa fa-user-times fa-btn"></i>禁用'
					                        +'</button>'
					                        +'</shiro:hasPermission>';
	                    	}else{
	                    		button =  ' <shiro:hasPermission name="user:cancel">'
					                        +'<button class="btn btn-xs btn-success cancelBtn" rid="'+row.id+'" status="0">'
					                        +'<i class="fa fa-user-times fa-btn"></i>启用'
					                        +'</button>'
				                    		+'</shiro:hasPermission>';
	                    	} 
	                    	if(row.username != 'admin'){
		                        return  ' <shiro:hasPermission name="user:see">'
				                        +'<button class="btn btn-xs btn-info seeBtn" rid="'+row.id+'">'
				                        +'<i class="fa fa-eye fa-btn"></i>查看'
				                        +'</button>'
				                        +'</shiro:hasPermission>'
				                        +' <shiro:hasPermission name="user:update">'
				                        +'<button class="btn btn-xs btn-warning updateBtn" rid="'+row.id+'">'
				                        +'<i class="fa fa-edit fa-btn"></i>修改'
				                        +'</button>'
				                        +'</shiro:hasPermission>'
				                        +' <shiro:hasPermission name="user:delete">'
				                        +'<button class="btn btn-xs btn-danger deleteBtn" rid="'+row.id+'">'
				                        +'<i class="fa fa-trash fa-btn"></i>删除'
				                        +'</button>'
				                        +'</shiro:hasPermission>'+button;
	                    	}else{
	                    	   return  ' <shiro:hasPermission name="user:see">'
				                        +'<button class="btn btn-xs  btn-info seeBtn" rid="'+row.id+'">'
				                        +'<i class="fa fa-user-times fa-btn"></i>查看'
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
	   			openDialog("新增表单","<%=basePath%>admin/system/user/user_insertUI","800px", "380px","");
	   	   });
	   	  //查看
	   	  $(document).on("click",".seeBtn",function(){
	   			var id = $(this).attr("rid");
	   			openDialogView("查看表单","<%=basePath%>admin/system/user/user_seeUI?id="+id,"800px", "380px","");
	   	   });
	   	   //修改
	   	   $(document).on("click",".updateBtn",function(){
	   		    var id = $(this).attr("rid");
	   			openDialog("修改表单","<%=basePath%>admin/system/user/user_updateUI?id="+id,"800px", "380px","");
	   	   });
	   	   //删除
	   	    $(document).on("click",".deleteBtn",function(){
	   	    	var id = $(this).attr("rid");
	   	    	var fun = function(){
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/user/user_delete?id="+id,
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
	   	   
	   	    //禁用
	   	    $(document).on("click",".cancelBtn",function(){
	   	    	var id = $(this).attr("rid");
	   	    	var status = $(this).attr("status");
	   	    	var tip = "启用";
	   	    	if(status=='1'){
	   	    		tip = "禁用";
	   	    	}
	   	    	var fun = function(){
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/user/user_cancel?id="+id+"&status="+status,
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
	   	    	confirmx("你确定"+tip+"该用户吗？",fun);
	   	    });
	    }); 
 	</script>
 
</body>
</html>
