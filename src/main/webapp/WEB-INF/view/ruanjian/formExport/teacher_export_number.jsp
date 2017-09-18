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
	.search-input{
		margin-right:10px;
		margin-left: 2px;
	}
	.my-td {
		height: 37px;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
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
								<h5>选题表导出</h5>
							</div>
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<span>教师名: </span><input type="text" name="teacher" id="teacher" value="" class="form-control input-150 search-input">
											<button type="button" id="searchBtn" class="btn btn-primary btn-sm"><i class="fa fa-search fa-btn"></i>搜索
										</button>
										<a class="btn btn-primary" id="export" onclick="exportClick(event)"  style="position: relative; left: 1000px;top: -5px">导出excel</a>
									</div>
								</div>
								<table id="datatable" style="table-layout: fixed" class="table table-striped table-bordered table-hover table-krt">
									<thead>
										<tr>
											<th>序号</th>
											<th>指导老师</th>
											<th>职称</th>
											<th>指导老师学历</th>
											<%--<th>课题来源</th>--%>
											<th>审核通过题目数</th>
											<th>选题通过学生人数</th>
											<th>未选到学生题目数</th>

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
	                "url": "<%=basePath%>ruanjian/course/teacher_Exportlist",
	                "type": "post",
	                "data": function (d) {
                        d.teacher= $('#teacher').val();
	                }
	            },
	            "columns": [
	                {"data": "id", "width": "7%",},
                    {"data": "name", "width": "8%",},
					{"data": "title_level", "width": "8%",},
					{"data": "teacEducation", "width": "8%",},
					{"data": "number", "width": "13%",},
					{"data": "passnumber", "width": "8%",},
					{"data": "notselectednumber", "width": "8%",},
	            ],
	            "columnDefs": [
	                {
	                    "targets": 5,
	                    "data": "id",
	                    "width": "0%",
	                   /* "render": function(data, type, row) {
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

        function exportClick(e) {
            var flag= $('#flag').val();
            var titlename = $("#titlename").val();
                var teacher = $("#teacher").val();
            e.target.href = "<%=basePath%>ruanjian/course/teacherExport?teacher="+teacher
        }
        function handleSelect(e) {
            status = e.target.value;
            datatable.ajax.reload();
        }
   	    $(function(){
   	    
   	    	//pace监听ajax
   	    	$(document).ajaxStart(function() {
				Pace.restart();
			});
			
   	    	//初始化datatable
   	     	initDatatable();

            $('#datatable tbody td').each(function(index, item){
                var titleVal = item.innerText;
                if (typeof titleVal === "string" && titleVal !== '') {
                    item.setAttribute('title', titleVal);
                }
            });
   	    
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
                location.href = "<%=basePath%>ruanjian/course/title_seeUI?id=" +id;
                <%--openDialogView("查看表单","<%=basePath%>ruanjian/course/title_seeUI?id="+id,"800px", "380px","");--%>
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
       
	    });

   	    window.onload = function(){


		}
 	</script>
</body>
</html>
