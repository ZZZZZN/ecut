<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<jsp:include page="/static/common/head.jsp" flush="true" />
<link rel="stylesheet" href="<%=basePath%>static/skin/css/base.css">
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
								<h5>区域管理</h5>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-sm-12">
										<div class="pull-left">
											<shiro:hasPermission name="region:insert">
												<button title="添加" id="insertBtn" data-placement="left"
													data-toggle="tooltip" class="btn btn-white btn-sm">
													<i class="fa fa-plus"></i> 添加
												</button>
											</shiro:hasPermission>
											<button title="刷新" data-placement="left" onclick="window.location.href = window.location.href" class="btn btn-white btn-sm">
												<i class="fa fa-refresh"></i> 刷新
											</button>
										</div>
									</div>
								</div>
								<table id="tree_table" class="table table-striped table-bordered table-hover table-krt">
										<tr>
											<th width="40%">区域名称</th>
											<th width="20%">区域编码</th>
											<th width="20%">类型</th>
											<th width="20%">操作</th>
										</tr>
										<c:forEach items="${list}" var="row" varStatus="status">
											<tr id="${row.id}" hasChild="${row.hasChild}">
												<td style="text-align:left;padding-left:20px">${row.name}</td>
												<td>${row.code}</td>
												<td>${row.typeName}</td>
												<td>
													<shiro:hasPermission name="region:update">
														<button class="btn btn-xs btn-warning updateBtn" rid="${row.id}">
															<i class="fa fa-edit fa-btn"></i>修改
														</button>
													</shiro:hasPermission>
													<shiro:hasPermission name="region:delete">
														<button class="btn btn-xs btn-danger deleteBtn" rid="${row.id}">
															<i class="fa fa-trash fa-btn"></i>删除
														</button>
													</shiro:hasPermission>
													<shiro:hasPermission name="region:insert">
														<button class="btn btn-xs btn-success addBtn" rid="${row.id}">
															<i class="fa fa-plus fa-btn"></i>添加下级区域
														</button>
													</shiro:hasPermission>
												</td>
											</tr>
										</c:forEach>
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
	<script src="<%=basePath%>static/plugins/treeTable/jquery.js"></script>
	<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
	<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
	<script src="<%=basePath%>static/skin/js/common.js"></script>
	<script src="<%=basePath%>static/plugins/treeTable/jquery.treeTable.js"></script>
	<!-- page script -->
	<script language="javascript" type="text/javascript">   
  		function isNull(str){
  			if(str==null || str=='null'){
  				return "";
  			}else{
  				return str;
  			}
  		}
	
   	   //jequery
   	   $(function(){
            var option = {
                theme:'vsStyle',
                expandLevel : 1,
                beforeExpand : function($treeTable, id) {
                    //判断id是否已经有了孩子节点，如果有了就不再加载，这样就可以起到缓存的作用
                    if ($('.' + id, $treeTable).length) { return; }
                    //ajax 加载子节点
                    $.ajax({   
   			         type: "POST",
   			         url:"<%=basePath%>admin/system/region/region_child?pid="+id,
   			         beforeSend:function(){
   			         	return loading();
   			         },
   			         success: function(data) {
   			        	 var html = ""; 
   			        	 for(var index in data){ 
   			        		var row = data[index];  
   			        		var hasChild = "";
   			        		if(row.hasChild=='true'){
   			        			hasChild = "hasChild=\""+row.hasChild+"\"";
   			        		}
   			        		var tr = "<tr id=\""+row.id+"\" pid=\""+row.pid+"\""+hasChild+">"
   			        				 +"<td>"+isNull(row.name)+"</td>"
   			        				 +"<td>"+isNull(row.code)+"</td>"
   			        				 +"<td>"+isNull(row.typeName)+"</td>" 
   			        				 +"<td>"
   			        				 +' <shiro:hasPermission name="region:see"><button class="btn btn-xs btn-info seeBtn" rid="'+row.id+'"><i class="fa fa-eye fa-btn"></i>查看</button></shiro:hasPermission>'
   			        				 +' <shiro:hasPermission name="region:update"><button class="btn btn-xs btn-warning updateBtn" rid="'+row.id+'"><i class="fa fa-edit fa-btn"></i>修改</button></shiro:hasPermission>'
   			        				 +' <shiro:hasPermission name="region:delete"><button class="btn btn-xs btn-danger deleteBtn" rid="'+row.id+'"><i class="fa fa-trash fa-btn"></i>删除</button></shiro:hasPermission>'
   			        				 +' <shiro:hasPermission name="region:insert"><button class="btn btn-xs btn-success addBtn" rid="'+row.id+'"><i class="fa fa-plus fa-btn"></i>添加下级区域</button></shiro:hasPermission>'
   			        				 +"</td>"
   			        				 +"</tr>";
   			        	    html = html+tr;
   			        	 }
   			        	closeloading();
   			         	$treeTable.addChilds(html);
   			         
   			         },
   			         error: function(){
   			        	 closeloading();
   			         }
   			      });
                   
                },
                onSelect : function($treeTable, id) {
                    window.console && console.log('onSelect:' + id);
                }

            }; 
            $("#tree_table").treeTable(option);
           
          //pace监听ajax
   	    	$(document).ajaxStart(function() {
				Pace.restart();
			});
   		  
	   	   //新增
	   	   $("#insertBtn").click(function(){
	   			openDialog("新增表单","<%=basePath%>admin/system/region/region_insertUI","800px", "300px","");
	   	   });
	   	   //修改
	   	   $(".updateBtn").live("click",function(){
	   		    var id = $(this).attr("rid");
	   			openDialog("修改表单","<%=basePath%>admin/system/region/region_updateUI?id="+id,"800px", "300px","");
	   	   });
	   	  //添加下级区域
	   	  $(".addBtn").live("click",function(){
	   			var id = $(this).attr("rid");
	   			openDialog("新增表单","<%=basePath%>admin/system/region/region_insertUI?id="+id,"800px", "300px","");
	   	   });
	   	   //删除
	   	    $(".deleteBtn").live("click",function(){
	   	    	var id = $(this).attr("rid"); 
	   	    	var fun = function(){
	   	    	 $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/region/region_delete?id="+id,
			         data:$('#krtForm').serialize(),// 要提交的表单
			         beforeSend:function(){
			         	return loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		window.location.href = window.location.href;
			         	}else{
			         		top.layer.msg("操作成功");
			         	}
			         },
			         error: function(){
			        	 closeloading();
			         }
			      });
	   	    	}
	   	    	confirmx("你确定删除该区域及它的下级区域吗？",fun);
	   	    });
   	   });   
   	
	</script>
</body>
</html>
