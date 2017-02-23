<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
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
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
      	<div class="form-box">
      		<form action="#" id="krtForm" class="form-horizontal">
      			<table class="table table-bordered table-krt">
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>任务名
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="jobName" id="jobName" value="${quartzJob.jobName}" class="form-control" required></td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>组名 
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="jobGroup" id="jobGroup" value="${quartzJob.jobGroup}" class="form-control"  required></td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>cron表达式
      						</label>
      					</td>
      					<td class="width-35">
      						<div class="input-group">
								<input type="text" name="cronExpression" id="cronExpression" value="${quartzJob.cronExpression}" class="form-control" required>
								<span class="input-group-btn">
									<button class="btn btn-primary btn-flat" id="cronBtn" type="button"><i class="fa fa-cog"></i></button>
								</span>
							</div>
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>类路径 
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="text" name="beanClass" id="beanClass" class="form-control" value="${quartzJob.beanClass}" required>
      						<span class="help-inline">与springId选填一个</span>
      					</td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>springId
      						</label>
      					</td>
      					<td class="width-35">
							<input type="text" name="springId" id="springId" class="form-control" value="${quartzJob.springId}" required>
							<span class="help-inline">spring管理的bean名称</span>
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>方法名
      						</label>
      					</td>
      					<td class="width-35">
      							<input type="text" name="methodName" id="methodName" class="form-control"  value="${quartzJob.methodName}" required>
      							<span class="help-inline">执行任务的方法名</span>
						</td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">描述</label>
      					</td>
      					<td colspan="3">
      						<textarea rows="2" name="description" class="form-control">${quartzJob.description}</textarea>
      					</td>
      				</tr>
      			</table>
      			<!-- 参数 -->
      			<input type="hidden" name="id" id="id" class="form-control" value="${quartzJob.id}" required>
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
		$("#cronBtn").click(function(){
			top.layer.open({
				type: 2, 
				title:"生成con表达式",
				area: ['820px',  '600px'],
			    content: '<%=basePath%>admin/system/quartzJob/quartzCron',
			    btn: ['确定', '关闭'],
			    yes: function(index, layero){ //或者使用btn1
			    	var cron = layero.find("iframe")[0].contentWindow.$("#cron").val();
	            	$("#cronExpression").val(cron);
	            	validateForm.form();
	                top.layer.close(index);
			    },cancel: function(index){ //或者使用btn2
			    	 top.layer.close(index);
			    }
			});
		});
	</script>
    <script type="text/javascript">
	    var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
			  $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/quartzJob/quartzJob_update",
			         data:$('#krtForm').serialize(),// 要提交的表单
			         beforeSend:function(){
			         	return  validateForm.form() && loading('');
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		var index = top.layer.getFrameIndex(window.name); //获取窗口索引
			         		refreshIframe();
			         		top.layer.close(index);
			         	}else if(msg.state=='error_cron'){
			         		top.layer.msg("操作失败,cron表达式有误，不能被解析！");
			         	}else if(msg.state=='error_class'){
			         		top.layer.msg("操作失败,执行任务类不存在！");
			         	}else if(msg.state=='error_method'){
			         		top.layer.msg("操作失败,未找到目标方法！");
			         	}else if(msg.state=='error_nameAndGroup'){
			         		top.layer.msg("操作失败,组名和任务名组合重复！");
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
