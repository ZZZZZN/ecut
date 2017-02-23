<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<div class="form-box">
			<form action="http://baidu.com" id="krtForm" class="form-horizontal">
				<table class="table table-bordered table-krt">
					<tr>
						<td class="active width-15"><label class="pull-right">
								<font color="red">*</font>角色名称
						</label></td>
						<td class="width-35"><input type="text" name="roleName"
							id="roleName" value="${role.roleName}" class="form-control"
							required></td>
						<td class="active width-15"><label class="pull-right">
								<font color="red">*</font>角色编码
						</label></td>
						<td class="width-35"><input type="text" name="roleCode"
							id="roleCode" value="${role.roleCode}" class="form-control"
							required></td>
					</tr>
					<tr>
						<td class="active width-15"><label class="pull-right">是否启用</label>
						</td>
						<td class="width-35"><select name="status"
							class="form-control">
								<option value="0" ${role.status=='0'?'selected':''}>正常</option>
								<option value="1" ${role.status=='1'?'selected':''}>禁用</option>
						</select> <span class="help-inline">正常代表角色可用，禁用表示该角色的用户都不可用</span></td>
						<td class="active width-15"><label class="pull-right">
								<font color="red">*</font>排序
						</label></td>
						<td class="width-35"><input type="text" name="sortNo"
							id="sortNo" class="form-control" digits="true"
							value="${role.sortNo}" required></td>
					</tr>
					<tr>
						<td class="active width-15"><label class="pull-right">备注</label>
						</td>
						<td colspan="3"><textarea name="remark" rows="2"
								class="form-control">${role.remark}</textarea></td>
					</tr>
				</table>
				<!-- 参数 -->
				<input type="hidden" name="id" value="${role.id}">
			</form>
		</div>
	</div>
	<!-- ./wrapper -->

	<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
	<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
	<script
		src="<%=basePath%>static/plugins/JQueryValidate/jquery.validate.min.js"></script>
	<script
		src="<%=basePath%>static/plugins/JQueryValidate/localization/messages_zh.js"></script>
	<script src="<%=basePath%>static/skin/js/common.js"></script>
	<script type="text/javascript">
	    var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
			  $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/role/role_update",
			         data:$('#krtForm').serialize(),// 要提交的表单
			         beforeSend:function(){
			         	return  validateForm.form() && loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		var index = top.layer.getFrameIndex(window.name); //获取窗口索引
			         		refreshIframe();
			         		top.layer.close(index);
			         	}else{
			         		top.layer.msg("操作成功");
			         	}
			         },
			         error: function(){
			        	 closeloading();
			         }
			      });
		}
    	$(function(){
    		validateForm= $("#krtForm").validate({
				rules: {
					//name: {remote: "/jeeplus/a/sys/role/checkName?oldName=" + encodeURIComponent("")},//设置了远程验证，在初始化时必须预先调用一次。
					//enname: {remote: "/jeeplus/a/sys/role/checkEnname?oldEnname=" + encodeURIComponent("")}
				},
				messages: {
					//name: {remote: "角色名已存在"},
					//enname: {remote: "英文名已存在"}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
    		$("#krtForm").validate();
    	});
    </script>
</body>
</html>
