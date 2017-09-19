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
      				<%--<tr>
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
      				</tr>--%>
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
						<td class="active width-15">
							<label class="pull-right">学院</label>
						</td>
						<td class="width-35">
							<select name="roleCode" class="form-control" required>
								<option value="">==请选择==</option>
								<c:forEach items="${institutes}" var="institute">
									<option value="${institute.institute}" ${institute.institute==user.institute?'selected':''}>${institute.institute}</option>
								</c:forEach>
							</select>
						</td>
      				</tr>
					<tr>
						<td class="active width-15">
							<label class="pull-right">专业</label>
						</td>
						<td class="width-35">
							<select name="roleCode" class="form-control" required>
								<option value="">==请选择==</option>
								<c:forEach items="${majorList}" var="major">
									<option value="${major.major_name}" ${major.major_name==user.major?'selected':''}>${major.major_name}</option>
								</c:forEach>
							</select>
						</td>
						<td class="active width-15">
							<label class="pull-right">职称</label>
						</td>
						<td class="width-35">
							<input type="text" name="title_level" id="title_level" value="${user.title_level}" class="form-control" rangelength="1,10" required>
						</td>
					</tr>
					<tr>
						<td class="active width-15">
							<label class="pull-right">可带学生人数</label>
						</td>
						<td class="width-35">
							<input type="text" name="title_level_num" id="title_level_num" value="${user.title_level_num}" class="form-control" rangelength="1,10" required>
						</td>
						<td class="active width-15">
							<label class="pull-right">所在系</label>
						</td>
						<td class="width-35">
							<input type="text" name="department" id="department" value="${user.department}" class="form-control" rangelength="1,10" required>
						</td>
					</tr>
					<tr>
						<td class="active width-15">
							<label class="pull-right">学历</label>
						</td>
						<td class="width-35">
							<input type="text" name="education" id="education" value="${user.education}" class="form-control" rangelength="1,10" required>
						</td>
						<td class="active width-15">
							<label class="pull-right">实训地点</label>
						</td>
						<td class="width-35">
							<input type="text" name="training_site" id="training_site" value="${user.training_site}" class="form-control" rangelength="1,10" required>
						</td>
					</tr>
					<tr>
						<td class="active width-15">
							<label class="pull-right">所在企业</label>
						</td>
						<td class="width-35">
							<input type="text" name="company" id="company" value="${user.company}" class="form-control" rangelength="1,10" required>
						</td>

						<td class="active width-15">
							<label class="pull-right">
								<font color="red"></font>备注
							</label>
						</td>
						<td class="width-35">
							<textarea class="form-control" id="note">${user.note}</textarea>
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
