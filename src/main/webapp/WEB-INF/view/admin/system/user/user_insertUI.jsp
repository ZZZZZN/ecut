<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
                        <label class="pull-right"><font color="red">*</font>所属角色</label>
                    </td>
                    <td class="width-35">
                        <select name="roleCode" id="roleCode" class="form-control" <%--onchange="roleset(this)"--%> required>
                            <option value="">==请选择==</option>
                            <c:forEach items="${roleList}" var="role">
                                <c:if test="${role.roleCode!='admin'}">
                                    <option value="${role.roleCode}">${role.roleName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red">请首先选择用户所属角色！</font>
                        </label>
                    </td>
                </tr>

                <tr>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red">*</font>用户账号
                        </label>
                    </td>
                    <td class="width-35"><input type="text" name="username" id="username" class="form-control"
                                                rangelength="2,20" required></td>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red">*</font>用户姓名
                        </label>
                    </td>
                    <td class="width-35"><input type="text" name="name" id="name" class="form-control"
                                                rangelength="1,10" required></td>
                </tr>
                <tr>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red">*</font>密码
                        </label>
                    </td>
                    <td class="width-35"><input type="password" name="password" id="password" class="form-control"
                                                rangelength="6,20" required></td>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red">*</font>重复密码
                        </label>
                    </td>
                    <td class="width-35"><input type="password" name="password2" id="password2" class="form-control"
                                                equalTo="#password" rangelength="6,20" required></td>
                </tr>

                <tr>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red">*</font>学院
                        </label>
                    </td>
                    <td class="width-35">
                        <select class="form-control" id="institute" name="institute">
                            <option value="">==请选择==</option>
                            <c:forEach items="${list1}" var="inst">
                                <option value="${inst.institute}">${inst.institute}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red">*</font>专业
                        </label>
                    </td>
                    <td class="width-35">
                        <select class="form-control" id="major" name="major">
                            <option value="">==请选择==</option>
                            <c:forEach items="${map}" var="major">
                                <option value="${major.major_name}">${major.major_name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red"></font>实训地点
                        </label>
                    </td>
                    <td class="width-35">
                        <select name="training_site" id="training_site" class="form-control" disabled="disabled">
                            <option value="">==请选择==</option>
                            <option value="校内">校内</option>
                            <option value="校外">校外</option>
                            <option value="其他">其他</option>
                        </select>
                    </td>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red"></font>所在企业
                        </label>
                    </td>
                    <td class="width-35"><input type="text" name="company" id="company" class="form-control" disabled="disabled"></td>
                </tr>

                <tr>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red"></font>职称
                        </label>
                    </td>
                    <td class="width-35"><input type="text" name="title_level" id="title_level" class="form-control"
                                                 disabled="disabled"></td>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red"></font>所在系
                        </label>
                    </td>
                    <td class="width-35">
                        <select class="form-control" id="department" name="department">
                            <option value="">==请选择==</option>
                            <c:forEach items="${departs}" var="departs">
                                <option value="${departs.major_name}">${departs.major_name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="active width-15">
                        <label class="pull-right">
                            <font color="red"></font>备注
                        </label>
                    </td>
                    <td class="width-35" colspan="4">
                        <textarea class="form-control" id="note" rows="4"></textarea>
                    </td>
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

    function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        $.ajax({
            type: "POST",
            url: "<%=basePath%>admin/system/user/user_insert",
            data: $('#krtForm').serialize(),// 要提交的表单
            beforeSend: function () {
                return validateForm.form() && loading();
            },
            success: function (msg) {
                closeloading();
                if (msg.state == 'success') {
                    top.layer.msg("操作成功");
                    var index = top.layer.getFrameIndex(window.name); //获取窗口索引
                    refreshTable();
                    top.layer.close(index);
                } else {
                    top.layer.msg("操作失败");
                }
            },
            error: function () {
                closeloading();
            }
        });
    }

    $(function () {
        validateForm = $("#krtForm").validate({
            rules: {
                username: {
                    remote: {
                        url: "<%=basePath%>admin/system/user/checkUsername",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            username: function () {
                                return $("#username").val();
                            }
                        }
                    }
                }
            },
            messages: {
                username: {remote: "用户名已存在"}
            }
        });
        //立即验证
        validateForm.form();
    });

    $("#roleCode").change(function () {

        $("#training_site").attr("disabled", "disabled");
        $("#company").attr("disabled", "disabled");
        $("#title_level").attr("disabled", "disabled");
        var role = $("#roleCode").val();
        var t = role.indexOf('stu');
        if (t>=0) {
            $("#training_site").removeAttr("disabled");
            $("#company").removeAttr("disabled");
        }else {
            $("#title_level").removeAttr("disabled");
        }
    })
</script>
</body>
</html>
