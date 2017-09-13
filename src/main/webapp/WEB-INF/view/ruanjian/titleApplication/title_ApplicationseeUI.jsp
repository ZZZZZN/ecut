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
                        <h5>课题详情</h5>
                    </div>
                    <div class="box-body">
                        <div class="form-box">
                            <form action="#" id="krtForm" class="form-horizontal">
                                <table class="table table-bordered table-krt" style="width: 80%;margin-left: 100px">
                                    <tr>
                                        <td class="active width-15">
                                            <label class="pull-right">
                                                课题名称
                                            </label>
                                        </td>
                                        <td class="width-35">${title.title_name}</td>
                                        <td class="active width-15">
                                            <label class="pull-right">
                                                课题类型
                                            </label>
                                        </td>
                                        <td class="width-35">
                                            ${title.title_type}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="active width-15">
                                            <label class="pull-right">
                                                课题来源
                                            </label>
                                        </td>
                                        <td class="width-35">
                                            ${title.title_source}
                                        </td>
                                        <td class="active width-15">
                                            <label class="pull-right">
                                                适用专业
                                            </label>
                                        </td>
                                        <td class="width-35">
                                            ${title.suitMajorName}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="active width-15">
                                            <label class="pull-right">
                                                适用实训所在地
                                            </label>
                                        </td>
                                        <td class="width-35">
                                            ${title.suitScope}
                                        </td>
                                        <td class="active width-15">
                                            <label class="pull-right">
                                                上限人数
                                            </label>
                                        </td>
                                        <td class="width-35">${title.limit_person}</td>
                                    </tr>
                                    <tr>
                                        <td class="active width-15">
                                            <label class="pull-right">
                                                课程意义与目标
                                            </label>
                                        </td>
                                        <td colspan="3">
                                            ${title.meaning_target}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="active width-15" style="text-align: center;">
                                            <label class="pull-right">
                                                学生基本条件和前期工作
                                            </label>
                                        </td>
                                        <td colspan="3">
                                            ${title.condition_work}
                                        </td>
                                    </tr>
                                    <input type="hidden" name="id" id="id" value="${ title.id }">
                                </table>
                            </form>
                            <shiro:hasPermission name="title:application">
                                <div style="text-align: center;">
                                    <button class="btn btn-md btn-warning updateBtn" style="width: 100px;margin-top: 20px">
                                        <i class="fa fa-edit fa-btn"></i>申请
                                    </button>
                                </div>
                            </shiro:hasPermission>
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

    //申请
    $(document).on("click",".updateBtn",function(){
        var id = document.getElementsByName('id')[0].value;
        console.log(id)
        $.ajax({
            type: "POST",
            url:"<%=basePath%>ruanjian/course/title_application?id="+id,
            beforeSend:function(){
                return loading();
            },
            success: function(msg) {
                closeloading();
                if(msg.state=='success'){
                top.layer.msg("操作成功");
                location.href = "<%=basePath%>ruanjian/course/title_Application_listUI";
//                refreshTable(datatable);
                }
                if (msg.state=='overstep'){
                top.layer.msg("超出选题人数上限");
//                refreshTable(datatable);
                }
                else{
                top.layer.msg("操作失败");
                }
            },
            error: function(){
                closeloading();
            }
        });
    });

</script>
</body>
</html>
