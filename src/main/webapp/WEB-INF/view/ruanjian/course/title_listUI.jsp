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
								<h5>选题表管理</h5>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<shiro:hasPermission name="title:insert">
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
											<th>题名</th>
											<th>选题开始时间</th>
											<th>选题结束时间</th>
											<th>选题上限数量</th>
											<th>出题人</th>
											<th>1 : 校内选题  2 ： 校外选题</th>
											<th>逻辑删除</th>
											<th></th>
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
	                "url": "<%=basePath%>ruanjian/course/title_list",
	                "type": "post",
	                "data": function (d) {
	                
	                }
	            },
	            "columns": [
	                {"data": "id", "width": "5%"},
					{"data": "title_name", "width": "20%"},
					{"data": "start_time", "width": "20%"},
					{"data": "end_time", "width": "20%"},
					{"data": "title_limit", "width": "20%"},
					{"data": "author", "width": "20%"},
					{"data": "title_type", "width": "20%"},
					{"data": "dr", "width": "20%"},
					{"data": "ts", "width": "20%"},
	            ],
	            "columnDefs": [
	                {
	                    "targets": 10,
	                    "data": "id",
	                    "width": "20%",
	                    "render": function(data, type, row) {
	                        return  ' <shiro:hasPermission name="title:see">'
			                        +'<button class="btn btn-xs btn-info seeBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-eye fa-btn"></i>查看'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                        +' <shiro:hasPermission name="title:update">'
			                        +'<button class="btn btn-xs btn-warning updateBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-edit fa-btn"></i>修改'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                        +' <shiro:hasPermission name="title:delete">'
			                        +'<button class="btn btn-xs btn-danger deleteBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-trash fa-btn"></i>删除'
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
	   			openDialog("新增表单","<%=basePath%>ruanjian/course/title_insertUI","800px", "380px","");
	   	    });
	   	    
	   	    //查看
	   	    $(document).on("click",".seeBtn",function(){
	   			var id = $(this).attr("rid");
	   			openDialogView("查看表单","<%=basePath%>ruanjian/course/title_seeUI?id="+id,"800px", "380px","");
	   	    });
	   	   
	   	    //修改
	   	    $(document).on("click",".updateBtn",function(){
	   		    var id = $(this).attr("rid");
	   			openDialog("修改表单","<%=basePath%>ruanjian/course/title_updateUI?id="+id,"800px", "380px","");
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
       
	    }); 
 	</script>
</body>
</html>
