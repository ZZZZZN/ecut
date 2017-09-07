<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <jsp:include page="/static/common/head.jsp" flush="true"/>
    <link rel="stylesheet" href="<%=basePath%>static/skin/css/base.css">
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
      	<div class="form-box">
      		<form action="#" id="krtForm" class="form-horizontal">
				<table class="table table-bordered table-krt">
									<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							题名
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="title_name" id="title_name" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							选题开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="start_time" id="start_time" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							选题结束时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="end_time" id="end_time" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							选题上限数量
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="title_limit" id="title_limit" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							出题人
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="author" id="author" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							1 : 校内选题  2 ： 校外选题
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="title_type" id="title_type" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							逻辑删除
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="dr" id="dr" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">

      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="ts" id="ts" class="form-control"></td>
						<td></td>
						<td></td>
  					</tr>
      			</table>
      		</form>
      	 </div>
    </div><!-- ./wrapper -->
    
    <script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
    <script src="<%=basePath%>static/plugins/layer/layer.js"></script>
    <script src="<%=basePath%>static/plugins/JQueryValidate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>static/plugins/JQueryValidate/localization/messages_zh.js"></script>
    <script src="<%=basePath%>static/skin/js/common.js"></script>
    <script type="text/javascript">
	
	    var validateForm;
	    
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
			  $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>ruanjian/course/title_insert",
			         data:$('#krtForm').serialize(),// 要提交的表单
			         beforeSend:function(){
			         	return  validateForm.form() && loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		var index = top.layer.getFrameIndex(window.name); //获取窗口索引
			         		refreshTable();
			         		top.layer.close(index);
			         	}else{
			         		top.layer.msg("操作失败");
			         	}
			         },
			         error: function(){
			        	 closeloading();
			         }
			      });
		}
		
    	$(function(){
    		validateForm= $("#krtForm").validate();
    		//立即验证
    		validateForm.form();
    	});

    </script>
  </body>
</html>
