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
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h5>选题表查看</h5>
                        <span type="button" id="insertBtn" data-placement="left" data-toggle="tooltip" onclick="history.go(-1);" style="float: right; cursor: pointer;">
                            <i class="fa fa-mail-reply"></i> 返回
                        </span>
                    </div>
                    <div class="box-body">
                        <div class="form-box">
                            <form action="#" id="krtForm" class="form-horizontal">
                                <table class="table table-bordered table-krt" style="width: 80%;margin-left: 100px">
                                    <tr>
                                        <td class="active" style="width: 25%;">
                                            <label class="pull-right">
                                                课题名称
                                            </label>
                                        </td>
                                        <td class="">${title.title_name}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">
                                            <label class="pull-right">
                                                课题类型
                                            </label>
                                        </td>
                                        <td class="">
                                            ${title.title_type}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="active">
                                            <label class="pull-right">
                                                课题来源
                                            </label>
                                        </td>
                                        <td class="">
                                            ${title.title_source}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="active">
                                            <label class="pull-right">
                                                适用专业
                                            </label>
                                        </td>
                                        <td class="">
                                            ${title.suitMajorName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="active">
                                            <label class="pull-right">
                                                适用实训所在地
                                            </label>
                                        </td>
                                        <td class="">
                                            ${title.suitScope}
                                        </td>
                                    <tr>
                                        <td class="active">
                                            <label class="pull-right">
                                                上限人数
                                            </label>
                                        </td>
                                        <td class="">${title.limit_person}</td>
                                    </tr>
                                    <tr>
                                        <td class="active">
                                            <label class="pull-right">
                                                课程意义与目标
                                            </label>
                                        </td>
                                        <td>
                                            ${title.meaning_target}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="active" style="text-align: center;">
                                            <label class="pull-right">
                                                学生基本条件和前期工作
                                            </label>
                                        </td>
                                        <td>
                                            ${title.condition_work}
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

</div><!-- ./wrapper -->

<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
<script src="<%=basePath%>static/plugins/JQueryValidate/jquery.validate.min.js"></script>
<script src="<%=basePath%>static/plugins/JQueryValidate/localization/messages_zh.js"></script>
<script src="<%=basePath%>static/skin/js/common.js"></script>
<script type="text/javascript">


</script>
</body>
</html>
