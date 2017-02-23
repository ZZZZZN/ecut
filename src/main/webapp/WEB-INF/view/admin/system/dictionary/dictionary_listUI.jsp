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
					<h5>字典管理</h5>
				</div>
                <div class="box-body">
                <div class="row">
                  	<div class="col-sm-12">
						<span>字典编码:</span><input type="text" name="code" id="code" class="form-control input-150">
						<span>字典名称:</span> <input type="text" name="name" id="name"  class="form-control input-150">
						<!-- 参数 -->
						<input type="hidden" name="pid" id="pid" value="${param.pid}">
						<input type="hidden" name="type" id="type" value="${param.type}">
	                    <button type="button" id="searchBtn" class="btn btn-primary btn-sm"><i class="fa fa-search fa-btn"></i>搜索</button>
	                    <c:if test="${fid!='-1'}">
	                    	<button type="button" class="btn btn-primary btn-sm" onclick="location.href='<%=basePath%>admin/system/dictionary/dictionary_listUI?pid=${fid}&type=${param.type}'"><i class="fa fa-mail-reply fa-btn"></i>返回上一级</button>
	                    </c:if>
	                    <c:if test="${fid=='-1'}"> 
	                    	<button type="button" class="btn btn-primary btn-sm" onclick="location.href='<%=basePath%>admin/system/dictionaryType/dictionaryType_listUI'"><i class="fa fa-mail-reply fa-btn"></i>返回上一级</button>
	                    </c:if>
					</div>
                </div>
				<table id="datatable" class="table table-striped table-bordered table-hover table-krt">
                    <thead>
                      <tr>
                        <th>序号</th>
                        <th>字典编码</th>
                        <th>字典名称</th>
                        <th>字典类型</th>
                        <th>备注</th>
                        <th>排序</th>
                        <th>操作</th>
                      </tr>
                    </thead>
                    <tbody>

                    </tbody>  
	                  	<tr>
	                   		<td></td>
	                   		<td>
	                   			<input type="text" name="code" id="code0" class="form-control input-150">
	                   		</td>
	                   		<td>
	                   			<input type="text" name="name" id="name0" class="form-control input-150">
	                   		</td>
	                   		<td>
	                   			<input type="text" name="type" id="type0" value="${param.type}" readonly="readonly" class="form-control input-150">
	                   		</td>
	                   		<td>
	                   			<input type="text" name="remark" id="remark0" class="form-control input-150">
	                   		</td>
	                   		<td>
	                   			<input type="text" name="sortNo" id="sortNo0" class="form-control input-150">
	                   		</td>
	                   		<td>
	                   			<!-- 参数 -->
	                   			<input type="hidden" name="pid" id="pid0" value="${param.pid}">
								<shiro:hasPermission name="dictionary:insert">
									<button class="btn btn-xs btn-white" id="insertBtn">
										<i class="fa fa-plus fa-btn"></i>新增
									</button>
								</shiro:hasPermission>
							</td>
	                   	</tr>
                  </table>  
                   <div class="page" style="text-align: center;"></div>
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
	<script src="<%=basePath%>static/skin/js/common.js"></script>
	<!-- DataTables -->
	<script src="<%=basePath%>static/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="<%=basePath%>static/plugins/datatables/dataTables.bootstrap.min.js"></script>
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
	               "url": "<%=basePath%>admin/system/dictionary/dictionary_list",
	               "type": "post",
	               "data": function (d) {
	                   d.pid = $('#pid').val();
	                   d.type = $('#type').val();
	                   d.name = $('#name').val();
	                   d.code = $('#code').val();
	               }
	           },
	           "columns": [
	               {"data": "id", "width": "10%"},
	               {"data": "code", "width": "15%",
	            	   render: function ( data, type, row ) {
	            		   return '<input type="text" name="code" value="'+row.code+'" id="code'+row.id+'" class="form-control input-150">';
	                	}
	               },
	               {"data": "name", "width": "15%",
	            	   render: function ( data, type, row ) {
	                		return '<input type="text" name="name" value="'+row.name+'" readonly="readonly" id="name'+row.id+'"  class="form-control input-150">';
	                	}
	               },
	               {"data": "type", "width": "15%",
	            	   render: function ( data, type, row ) {
	            		   return '<input type="text" name="type" value="'+row.type+'" readonly="readonly" id="type'+row.id+'"  class="form-control input-150">';
	                	}
	               },
	               {"data": "remark", "width": "15%",
	            	   render: function ( data, type, row ) {
	            		   return '<input type="text" name="remark" value="'+row.remark+'" id="remark'+row.id+'"  class="form-control input-150">';
	                	}
	               },
	               {"data": "sortNo", "width": "15%",
	            	   render: function ( data, type, row ) {
	            		   return '<input type="text" name="sortNo" value="'+row.sortNo+'" id="sortNo'+row.id+'"  class="form-control input-150">';
	                	}
	               }
	           ],
	           "columnDefs": [
	               {
	                   "targets": 6,
	                   "data": "id",
	                   "width": "15%",
	                   "render": function(data, type, row) {
	                       return   '<input type="hidden" name="id" value="${row.id}">'
			                       +' <shiro:hasPermission name="dictionary:list">'
			                       +'<button class="btn btn-xs btn-success dicBtn" rid="'+row.id+'" type="'+row.type+'">'
			                       +'<i class="fa  fa-list fa-btn"></i>键值管理'
			                       +'</button>'
			                       +'</shiro:hasPermission>'
			                       +' <shiro:hasPermission name="dictionary:update">'
			                       +'<button class="btn btn-xs btn-warning updateBtn" rid="'+row.id+'">'
			                       +'<i class="fa fa-edit fa-btn"></i>修改'
			                       +'</button>'
			                       +'</shiro:hasPermission>'
			                       +' <shiro:hasPermission name="dictionary:delete">'
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
  	   
	    $(document).ready(function () {
		    //初始化datatable
		    initDatatable();   
		   	
		   	$("#searchBtn").on('click', function () {
	            datatable.ajax.reload();
	        });
		  //pace监听ajax
  	    	$(document).ajaxStart(function() {
				Pace.restart();
			});
		  
		    //新增
	   	    $("#insertBtn").click(function(){
	   	    	 var code = $("#code0").val();
	   	    	 if(code==null||code==''){
	   	    		top.layer.msg("字典编码不可为空！");
	   	    		return false;
	   	    	 }
	   	    	var name = $("#name0").val();
	   	    	if(name==null||name==''){
	   	    		top.layer.msg("字典名称不可为空！");
	   	    		return false;
	   	    	 }
	   	    	var sortNo = $("#sortNo0").val();
	   	    	if(sortNo==null||sortNo==''){
	   	    		top.layer.msg("排序不可为空！");
	   	    		return false;
	   	    	 }
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/dictionary/dictionary_insert",
			         data:"json",
			         data:{"pid":$("#pid0").val(),"code":$("#code0").val(),"name":$("#name0").val(),"type":$("#type0").val(),"remark":$("#remark0").val(),"sortNo":$("#sortNo0").val()},
			         beforeSend:function(){
			         	return loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		$("#pid0").val("");
			         		$("#code0").val("");
			         		$("#name0").val("");
			         		$("#remark0").val("");
			         		$("#sortNo").val("");
			         		refreshTable(datatable);
			         	}else if(msg.state=='error_code'){
			         		top.layer.msg("操作失败,字典编码重复！");
			         	}else{
			         		top.layer.msg("操作失败");
			         	} 
			         },
			         error: function(){
			        	 closeloading();
			         }
			      });
	   	    });
		    
	   		//修改
	   		$(document).on("click",".updateBtn",function(){
	   	    	 var id = $(this).attr("rid");
	   	    	 var code = $("#code"+id).val();
	   	    	 if(code==null||code==''){
	   	    		top.layer.msg("字典编码不可为空！");
	   	    		return false;
	   	    	 }
	   	    	var name = $("#name"+id).val();
	   	    	if(name==null||name==''){
	   	    		top.layer.msg("字典名称不可为空！");
	   	    		return false;
	   	    	 }
	   	    	var sortNo = $("#sortNo"+id).val();
	   	    	if(sortNo==null||sortNo==''){
	   	    		top.layer.msg("排序不可为空！");
	   	    		return false;
	   	    	 }
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/dictionary/dictionary_update",
			         data:"json",
			         data:{"id":id,"code":$("#code"+id).val(),"name":$("#name"+id).val(),"remark":$("#remark"+id).val(),"sortNo":$("#sortNo"+id).val()},
			         beforeSend:function(){
			         	return loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		refreshTable(datatable);
			         	}else if(msg.state=='error_code'){
			         		top.layer.msg("操作失败,字典编码重复！");
			         	}else{
			         		top.layer.msg("操作失败");
			         	} 
			         },
			         error: function(){
			        	 closeloading();
			         }
			      });
	   	    });

	   	   //删除
	   	    $(document).on("click",".deleteBtn",function(){
	   	    	var id = $(this).attr("rid");
	   	    	var fun = function(){
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/dictionary/dictionary_delete?id="+id,
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
	   	  //键值管理
	   	  $(document).on("click",".dicBtn",function(){
	   		  	var type = $(this).attr("type"); 
	   		  	var pid = $(this).attr("rid");
	   		  	window.location.href = "<%=basePath%>admin/system/dictionary/dictionary_listUI?pid="+pid+"&type="+type;
	   	   });
   	   });   
	</script>
  </body>
</html>
