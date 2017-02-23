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
    <link rel="stylesheet" href="<%=basePath%>static/plugins/iCheck/all.css">
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
      							上级菜单
      						</label>
      					</td>
      					<td class="width-35">
      						<div class="input-group">
								<input type="text" name="pname" id="pname" value="${res.pname}" class="form-control" readonly="readonly">
								<span class="input-group-btn">
									<button class="btn btn-primary btn-flat" id="resTreeBtn" type="button"><i class="fa fa-search"></i></button>
								</span>
								<!-- 参数 -->
								<input type="hidden" name="pid" id="pid" value="${res.pid}" class="form-control">
							</div>
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>名称
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="text" name="name" id="name" value="${res.name}" class="form-control" rangelength="1,50" required>
      					</td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">
      							链接
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="text" name="url" id="url" value="${res.url}" class="form-control">
      						<span class="help-inline">点击菜单跳转的页面,按钮为空</span>
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							权限标志
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="text" name="permission" id="permission" value="${res.permission}" class="form-control">
      						<span class="help-inline">控制器中定义的权限标识，如：@RequiresPermissions("权限标识")</span>
      					</td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">
      							图标
      						</label>
      					</td>
      					<td class="width-35">
      						<i id="iconIcon" class="fa ${res.icon==null?'icon-hide':res.icon}"></i>
							<span id="iconIconLabel">${res.icon==null?'无':res.icon}</span>
							<input id="icon" type="hidden" value="${res.icon}" name="icon">
							<a id="iconButton" class="btn btn-primary" href="javascript:">选择</a>
							<input id="iconclear" class="btn btn-default" type="button" onclick="clear()" value="清除">
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							类别
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="radio" name="type" value="url" class="minimal" ${res.type=='url'?'checked':''}> 菜单
      						<input type="radio" name="type" value="button" class="minimal" ${res.type=='button'?'checked':''}> 按钮 
      					</td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">
      							目标
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="text" name="target" id="target" class="form-control" value="${res.target}">
      						<span class="help-inline">链接打开的目标窗口，不填默认：mainFrame</span>
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>排序
      						</label>
      					</td>
      					<td class="width-35"><input type="text" name="sortNo" id="sortNo" value="${res.sortNo}" class="form-control" digits="true" required></td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">备注</label>
      					</td>
      					<td colspan="3">
      						<textarea rows="2" name="remark" class="form-control">${res.remark}</textarea>
      					</td>
      				</tr>
      			</table>
      			<!-- 参数 -->
      			<input type="hidden" name="id" value="${res.id}">
      		</form>
		</div>
    </div><!-- ./wrapper -->
    
    <script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
    <script src="<%=basePath%>static/plugins/layer/layer.js"></script>
    <script src="<%=basePath%>static/plugins/iCheck/icheck.min.js"></script>
  </body>
</html>
