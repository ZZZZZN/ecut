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
								<h5>可申请课题</h5>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<span>课题名称: </span><input type="text" name="titlename" id="titlename" value="" class="form-control input-150 search-input">
										<span>出题老师: </span> <input type="text" name="author" id="author" value="" class="form-control input-150 search-input">
										<button type="button" id="searchBtn" class="btn btn-primary btn-sm">
											<i class="fa fa-search fa-btn"></i>搜索
										</button>
									</div>
								</div>
								<table id="datatable" class="table table-striped table-bordered table-hover table-krt">
									<thead>
										<tr>
											<th>序号</th>
											<th>课题名称</th>
											<th>课题类型</th>
											<th>课题来源</th>
											<th>适用专业</th>
											<%--<th>适用实训所在地</th>--%>
											<th>指导老师</th>
											<th>上限人数</th>
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
	                "url": "<%=basePath%>ruanjian/course/title_application_list",
	                "type": "post",
	                "data": function (d) {
                        d.titlename = $("#titlename").val(),
							d.author = $("#author").val();
	                }
	            },
	            "columns": [
	                {"data": "id", "width": "7%"},
					{"data": "title_name", "width": "22%"},
					{"data": "title_type", "width": "10%"},
					{"data": "title_source", "width": "10%"},
					{"data": "suitMajorName", "width": "10%"},
					/*{"data": "suitScope", "width": "13%"},*/
					{"data": "name","width":"13"},
					{"data": "limit_person", "width": "10%"},
					{"data": "operate", "width": "12%"},
	            ],
	            "columnDefs": [
	                {
	                    "targets": 7,
	                    "data": "id",
	                    "width": "20%",
	                    "render": function(data, type, row) {
	                        return  ' <shiro:hasPermission name="title:applicationSee">'
			                        +'<button class="btn btn-xs btn-info seeBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-eye fa-btn"></i>查看'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                        +' <shiro:hasPermission name="title:application">'
			                        +'<button class="btn btn-xs btn-warning updateBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-edit fa-btn"></i>申请'
			                        +'</button>'
			                        +'</shiro:hasPermission>'
			                       /* +'<shiro:hasPermission name="title:delete">'
			                        +'<button class="btn btn-xs btn-danger deleteBtn" rid="'+row.id+'">'
			                        +'<i class="fa fa-trash fa-btn"></i>删除'
			                        +'</button>'
			                        +'</shiro:hasPermission>';*/
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
                location.href = "<%=basePath%>ruanjian/course/title_insertUI";
	   	    });
	   	    
	   	    //查看
	   	    $(document).on("click",".seeBtn",function(){
                var id = $(this).attr("rid");
                location.href = "<%=basePath%>ruanjian/course/title_application_seeUI?id=" +id;
                <%--openDialogView("查看表单","<%=basePath%>ruanjian/course/title_seeUI?id="+id,"800px", "380px","");--%>
	   	    });
	   	   
	   	/*    //申请
	   	    $(document).on("click",".updateBtn",function(){
                var id = $(this).attr("rid");
                location.href = "<%=basePath%>ruanjian/course/title_application?id=" + id;
                <%--openDialog("修改表单","<%=basePath%>ruanjian/course/title_updateUI?id="+id,"800px", "380px","");--%>
	   	    });*/
	   	   
	   	    //申请
	   	    $(document).on("click",".updateBtn",function(){
	   	    	var id = $(this).attr("rid");
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>ruanjian/course/title_application?id="+id,
			         beforeSend:function(){
			         	return loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		refreshTable(datatable);
			         	}
                         if(msg.state=='overnumber'){
                            top.layer.msg("预选题超过三个，无法继续预选");
                            refreshTable(datatable);
						}
			         	if (msg.state=='overstep'){
						 top.layer.msg("超出选题人数上限");
						 refreshTable(datatable);
						 }
                         if (msg.state=='alreadselected'){
                             top.layer.msg("已被选择，无法继续选题");
                             refreshTable(datatable);
                         }
                         if (msg.state=='overteacherlevel'){
                             top.layer.msg("所选课题教师超出指导人数上限");
                             refreshTable(datatable);
                         }
                         if (msg.state=='error'){
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
