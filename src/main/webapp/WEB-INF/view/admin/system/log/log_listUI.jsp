<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <jsp:include page="/static/common/head.jsp" flush="true"/>
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
					<h5>日志管理</h5>
				</div>
                <div class="box-body">
                <div class="row">
                  	<div class="col-sm-12">
						<span>用户账号:</span><input type="text" id="username" class="form-control input-150">
						<span>操作内容:</span> <input type="text" id="name" class="form-control input-150">
						<span>日志类别:</span>
						<select class="form-control input-150" id="type">
							<option value="">==请选择==</option>
							<option value="0" ${para.type=='0'?'selected':''}>登录日志</option>
							<option value="1" ${para.type=='1'?'selected':''}>操作日志</option>
						</select>
	                    <button type="button" id="searchBtn" class="btn btn-primary btn-sm"><i class="fa fa-search fa-btn"></i>搜索</button>
	                    <shiro:hasPermission name="log:deleteAll">
			                <button class="btn btn-sm btn-danger" id="deleteBtn">
			                    <i class="fa fa-trash fa-btn"></i>清空所有日志
			                </button>
			            </shiro:hasPermission>
					</div>
                </div>
				<table id="datatable" class="table table-striped table-bordered table-hover table-krt">
                    <thead>
                      <tr>
                        <th>序号</th>
                        <th>用户账号</th>
                        <th>操作内容</th>
                        <th>操作类别</th>
                        <th>请求方法</th>
                        <th>请求ip</th>
                        <th>发生时间</th>
                      </tr>
                    </thead>
                    <tbody>
                    </tbody>  
                  </table>  
                </div><!-- /.box-body -->         
              </div><!-- /.box -->
            </div><!-- /.col -->
          </div><!-- /.row -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->

    </div><!-- ./wrapper -->

    <!-- jQuery 2.1.4 -->
    <script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
	<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
	<!-- DataTables -->
	<script src="<%=basePath%>static/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="<%=basePath%>static/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="<%=basePath%>static/skin/js/common.js"></script>
    <!-- page script -->
   <script type="text/javascript" >
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
	                "url": "<%=basePath%>admin/system/log/log_list",
	                "type": "post",
	                "data": function (d) {
	                    d.username = $('#username').val();
	                    d.name = $('#name').val();
	                    d.type = $('#type').val();
	                }
	            },
	            "columns": [
	                {"data": "id", "width": "5%"},
	                {"data": "username", "width": "10%"},
	                {"data": "description", "width": "20%"},
	                {"data": "type", "width": "10%",
	                	render: function ( data, type, row ) {
	                		if(data=='0'){
	                			return '<span class="badge bg-red">登录日志</span>';
	                		}else if(data=='1'){
	                			return '<span class="badge bg-yellow">操作日志</span>';
	                		}
	                	}
	                },
	                {"data": "method", "width": "25%"},
	                {"data": "requestIp", "width": "15%"},
	                {"data": "insertTime", "width": "15%"}
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
   			initDatatable();
   			$("#searchBtn").on('click', function () {
	            datatable.ajax.reload();
	        });
   			
   		   //删除
	   	   $("#deleteBtn").click(function(){
	   	    	var fun = function(){
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/log/log_deleteAll",
			         beforeSend:function(){
			         	return loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		refreshIframe();
			         	}else{
			         		top.layer.msg("操作失败");
			         	} 
			         },
			         error: function(){
			        	 closeloading();
			         }
			      });
	   	    	}
	   	    	confirmx("你确定清空系统日志吗？",fun);
	   	    });
       
   	   });   
	</script>
  </body>
</html>
