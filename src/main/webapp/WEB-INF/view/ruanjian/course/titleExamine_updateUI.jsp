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
      							主键
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="title_examine_id" id="title_examine_id" value="${titleExamine.title_examine_id}" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							题目id
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="title_id" id="title_id" value="${titleExamine.title_id}" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							申请人
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="applicant" id="applicant" value="${titleExamine.applicant}" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							审核人
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="auditor" id="auditor" value="${titleExamine.auditor}" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							1 : 未审核  2 ： 审核通过  3 ： 审核未通过
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="status" id="status" value="${titleExamine.status}" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="ts" id="ts" value="${titleExamine.ts}" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="dr" id="dr" value="${titleExamine.dr}" class="form-control"></td>
						<td></td>
						<td></td>
  					</tr>
      			</table>
      			<!-- 参数 -->
      			<input type="hidden" name="id" value="${titleExamine.id}">
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
			         url:"<%=basePath%>ruanjian/course/titleExamine_update",
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
