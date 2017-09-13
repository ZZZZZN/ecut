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
								<h5>学生申请记录表</h5>
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
											<th>选题名称</th>
											<th>申请人</th>
											<th>审核人</th>
											<th>审核状态</th>
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
	                {"data": "id", "width": "12%"},
					{"data": "titleName", "width": "34%"},
					{"data": "applyer", "width": "13%"},
					{"data": "author", "width": "13%"},
                    {"data": "flag", "width": "13%"},
					{"data": "operate", "width": "15%"},
	            ],
	            "columnDefs": [
	                {
	                    "targets": 5,
	                    "data": "id",
	                    "width": "20%",
	                    "render": function(data, type, row) {
	                        return  ' <shiro:hasPermission name="titleExamine:passOrFail">'
			                        +'<button class="btn btn-xs btn-success passBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-check fa-btn"></i>通过'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                        +' <shiro:hasPermission name="titleExamine:passOrFail">'
			                        +'<button class="btn btn-xs btn-danger failBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-remove fa-btn"></i>不通过'
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
                                top.layer.msg("审核失败");
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
