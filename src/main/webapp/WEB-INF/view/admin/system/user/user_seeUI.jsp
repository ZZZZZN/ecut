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
      							<font color="red">*</font>用户账号
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="username" id="username" value="${user.username}" class="form-control" readonly="readonly" rangelength="2,20" required></td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>用户姓名
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="name" id="name" value="${user.name}" class="form-control" rangelength="1,10" required></td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>密码
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="password" name="password" id="password" class="form-control">
      						<span class="help-inline">密码不填写，则保持原密码不变</span>
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>重复密码
      						</label>
      					</td>
      					<td class="width-35"><input type="password" name="password2" id="password2" class="form-control" equalTo="#password"></td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">所属角色</label>
      					</td>
      					<td class="width-35">
      						<select name="roleCode" class="form-control" required>
      							<option value="">==请选择==</option>
								<c:forEach items="${roleList}" var="role">
									<option value="${role.roleCode}" ${role.roleCode==user.roleCode?'selected':''}>${role.roleName}</option>
								</c:forEach>
      						</select>
      					</td>
      					
      					<td class="active width-15"><c:if test="${user.roleCode==dradmin}"><label class="pull-right"}>餐厅</label></c:if></td>
      					<td class="width-35">
      						<c:if test="${user.roleCode==dradmin}">
								<c:forEach items="${diningroomlist}" var="dining">
									${user.dId==dining.id?dining.name:''}
								</c:forEach>
	      					</c:if>
      					</td>
      				</tr>
      			</table>
      			<!-- 参数 -->
      			<input type="hidden" name="id" id="id" value="${user.id}">
      		</form>
      	 </div>
    </div><!-- ./wrapper -->
    
    <script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
    <script src="<%=basePath%>static/plugins/layer/layer.js"></script>
    <script src="<%=basePath%>static/plugins/JQueryValidate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>static/plugins/JQueryValidate/localization/messages_zh.js"></script>
    <script src="<%=basePath%>static/skin/js/common.js"></script>
  </body>
</html>
