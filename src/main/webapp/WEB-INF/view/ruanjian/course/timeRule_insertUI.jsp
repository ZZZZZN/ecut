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
      							教师出题开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="teacher_time_begin" id="teacher_time_begin" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							教师出题结束时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="teacher_time_end" id="teacher_time_end" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							学生选题开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="student_time_begin" id="student_time_begin" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							学生选题结束时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="student_time_end" id="student_time_end" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							毕业设计任务书下达开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="task_time_begin" id="task_time_begin" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							毕业设计任务书下达结束时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="task_time_end" id="task_time_end" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							过程前期开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="early_stage_begin" id="early_stage_begin" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							过程前期开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="early_stage_end" id="early_stage_end" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							过程中期开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="mid_stage_begin" id="mid_stage_begin" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							过程中期结束时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="mid_stage_end" id="mid_stage_end" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							过程后期开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="later_stage_begin" id="later_stage_begin" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							过程后期结束时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="later_stage_end" id="later_stage_end" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							答辩开始时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="defence_time_begin" id="defence_time_begin" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							答辩结束时间
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="defence_time_end" id="defence_time_end" class="form-control"></td>
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
			         url:"<%=basePath%>ruanjian/course/timeRule_insert",
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
