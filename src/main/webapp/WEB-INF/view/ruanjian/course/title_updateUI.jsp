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
      					<td class="width-35"><input type="text" name="title_name" id="title_name" value="${title.title_name}" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							课题类型
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="title_type" id="title_type" value="${title.title_type}" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							课题来源
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="title_source" id="title_source" value="${title.title_source}" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							适用专业
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="suitMajor" id="suitMajor" value="${title.suitMajor}" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							适用实训所在地
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="suitScope" id="suitScope" value="${title.suitScope}" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							上线人数
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="limit_person" id="limit_person" value="${title.limit_person}" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							课程意义与目标
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="meaning_target" id="meaning_target" value="${title.meaning_target}" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							学生基本条件和前期工作
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="condition_work" id="condition_work" value="${title.condition_work}" class="form-control"></td>
  					</tr>
					<tr>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							逻辑删除
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="dr" id="dr" value="${title.dr}" class="form-control"></td>
					 	 <td class="active width-15">
      						<label class="pull-right">
      							时间戳
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="ts" id="ts" value="${title.ts}" class="form-control"></td>
  					</tr>
      			</table>
      			<!-- 参数 -->
      			<input type="hidden" name="id" value="${title.id}">
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
			         url:"<%=basePath%>ruanjian/course/title_update",
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
