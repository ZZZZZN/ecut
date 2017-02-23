<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
								<h5>角色管理</h5>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<div class="pull-left">
											<shiro:hasPermission name="role:insert">
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
											<th>角色名称</th>
											<th>角色编码</th>
											<th>是否启用</th>
											<th>备注</th>
											<th>排序</th>
											<th>操作</th>
										</tr>
									</thead>
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
	<!-- DataTables -->
	<script src="<%=basePath%>static/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="<%=basePath%>static/plugins/datatables/dataTables.bootstrap.min.js"></script>
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
	                "url": "<%=basePath%>admin/system/role/role_list",
	                "type": "post"
	            },
	            "columns": [
	                {"data": "id", "width": "5%"},
	                {"data": "roleName", "width": "20%"},
	                {"data": "roleCode", "width": "20%"},
	                {"data": "status", "width": "10%",
	                	render: function ( data, type, row ) {
	                		if(data=='0'){
	                			return '<span class="badge bg-green">正常</span>';
	                		}else{
	                			return '<span class="badge bg-red">禁用</span>';
	                		}
	                	}
	                },
	                {"data": "remark", "width": "15%"},
	                {"data": "sortNo", "width": "10%"}
	            ], 
	            "columnDefs": [
	                {
	                    "targets":6,
	                    "data": "id",
	                    "width": "20%",
	                    "render": function(data, type, full) {
	                        return  ' <shiro:hasPermission name="role:see">'
			                        +'<button class="btn btn-xs btn-info seeBtn" rid="'+data+'">'
			                        +'<i class="fa fa-eye fa-btn"></i>查看'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                        +' <shiro:hasPermission name="role:update">'
			                        +'<button class="btn btn-xs btn-warning updateBtn" rid="'+data+'">'
			                        +'<i class="fa fa-edit fa-btn"></i>修改'
			                        +'</button>'
			                        +' </shiro:hasPermission>'
			                        +' <shiro:hasPermission name="role:delete">'
			                        +'<button class="btn btn-xs btn-danger deleteBtn" rid="'+data+'" roleCode="'+full.roleCode+'">'
			                        +'<i class="fa fa-trash fa-btn"></i>删除'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                        +' <shiro:hasPermission name="role:res">'
			                        +'<button class="btn btn-xs btn-success permBtn" roleCode="'+full.roleCode+'">'
			                        +'<i class="fa fa-edit fa-btn"></i>权限设置'
			                        +'</button>'
			                        +'</shiro:hasPermission>';
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
	   		
	   	   $("#searchBtn").on('click', function () {
	            datatable.ajax.reload();
	       });
	   	   //新增
	   	   $("#insertBtn").click(function(){
	   			openDialog("新增表单","<%=basePath%>admin/system/role/role_insertUI","800px", "380px","");
	   	   });
	   	  //查看
	   	   $(document).on("click",".seeBtn",function(){
	   			var id = $(this).attr("rid");
	   			openDialogView("查看表单","<%=basePath%>admin/system/role/role_seeUI?id="+id,"800px", "380px","");
	   	   });
	   	   //修改
	   	   $(document).on("click",".updateBtn",function(){	
	   		    var id = $(this).attr("rid");
	   			openDialog("修改表单","<%=basePath%>admin/system/role/role_updateUI?id="+id,"800px", "380px","");
	   	   });
	   	   //删除
	   	    $(document).on("click",".deleteBtn",function(){
	   	    	var id = $(this).attr("rid");
	   	    	var roleCode = $(this).attr("roleCode");
	   	    	var fun = function(){
		   	    	 $.ajax({   
				         type: "POST",
				         url:"<%=basePath%>admin/system/role/role_delete?id="+id+"&roleCode="+roleCode,
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
	   	    	};
	   	    	confirmx("你确定删除吗？",fun);
	   	    });
	   	   //权限设置
	   	 $(document).on("click",".permBtn",function(){
	   		    var roleCode = $(this).attr("roleCode");
		  		top.layer.open({
					type : 2,
					area : [ '300px', '600px' ],
					title : "选择菜单",
					maxmin : true, //开启最大化最小化按钮
					content : "<%=basePath%>admin/system/role/role_resTreeUI?roleCode="+roleCode,
					btn : [ '确定', '关闭' ],
					yes : function(index, layero) {
							var tree = layero.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
							var nodes = new Array();
							var nodesIds = new Array();
							nodes = tree.getCheckedNodes(true);
							for(var i = 0; i < nodes.length; i++) {  
					             nodesIds[i]=nodes[i].id;   
					         } 
							var res_ids = nodesIds.join(",");
							//提交
							$.ajax({   
						        type: "POST",
						        url:"<%=basePath%>admin/system/role/role_res",
								data : {
									"roleCode" : roleCode,
									"res_ids" : res_ids
								},//参数
								beforeSend : function() {
									return loading();
								},
								success : function(msg) {
									closeloading();
									if (msg.state == 'success') {
										top.layer.msg("操作成功");
										top.layer.close(index);
									} else {
										top.layer.msg("操作失败");
									}
								},
								error : function() {
									closeloading();
								}
							});
							},
							cancel : function(index) {
								top.layer.close(index);
							}
						});
			});
		});
	</script>
</body>
</html>
