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
								<h5>审核表（记录学生申请的题目）管理</h5>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<shiro:hasPermission name="titleExamine:insert">
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
											<th>主键</th>
											<th>题目id</th>
											<th>申请人</th>
											<th>审核人</th>
											<th>1 : 未审核  2 ： 审核通过  3 ： 审核未通过</th>
											<th></th>
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
	                "url": "<%=basePath%>ruanjian/course/titleExamine_list",
	                "type": "post",
	                "data": function (d) {
	                
	                }
	            },
	            "columns": [
	                {"data": "id", "width": "5%"},
					{"data": "title_examine_id", "width": "20%"},
					{"data": "title_id", "width": "20%"},
					{"data": "applicant", "width": "20%"},
					{"data": "auditor", "width": "20%"},
					{"data": "status", "width": "20%"},
					{"data": "ts", "width": "20%"},
					{"data": "dr", "width": "20%"},
	            ],
	            "columnDefs": [
	                {
	                    "targets": 8,
	                    "data": "id",
	                    "width": "20%",
	                    "render": function(data, type, row) {
	                        return  ' <shiro:hasPermission name="titleExamine:see">'
			                        +'<button class="btn btn-xs btn-info seeBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-eye fa-btn"></i>查看'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                        +' <shiro:hasPermission name="titleExamine:update">'
			                        +'<button class="btn btn-xs btn-warning updateBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-edit fa-btn"></i>修改'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                        +' <shiro:hasPermission name="titleExamine:delete">'
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
       
	    }); 
 	</script>
</body>
</html>
