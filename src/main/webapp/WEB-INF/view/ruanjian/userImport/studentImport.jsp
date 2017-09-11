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
    <section class="content">
        <div class="row">
            <div class="col-xs-12">
                <div class="box">
                    <div class="box-header">
                        <h5>学生批量导入</h5>
                    </div>
                    <div class="box-body">
                        <div class="form-box">
                            <form enctype="multipart/form-data" id="batchUpload" action="ExcelImport/StudentImport" method="post" class="form-horizontal">
                                <button class="btn btn-primary btn-sm" id="uploadEventBtn" type="button" style="margin-right: 5px; float: left">选择文件</button>
                                <input type="file" name="file" style="width:0px;height:0px;" id="uploadEventFile">
                                <input id="uploadEventPath" disabled="disabled" type="text" placeholder="请选择excel表"
                                       style="border: 1px solid #e6e6e6; height: 30px;width: 200px;float: left;text-indent: 5px">
                            </form>
                            <button type="button" class="btn btn-primary btn-sm" id="uploadBtn" style="margin-left: 20px">上传</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>

<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
<script src="<%=basePath%>static/plugins/JQueryValidate/jquery.validate.min.js"></script>
<script src="<%=basePath%>static/plugins/JQueryValidate/localization/messages_zh.js"></script>
<script src="<%=basePath%>static/skin/js/common.js"></script>
<script type="text/javascript">
    var User = function(){

        this.init = function(){

            //模拟上传excel
            $("#uploadEventBtn").unbind("click").bind("click",function(){
                $("#uploadEventFile").click();
            });
            $("#uploadEventFile").bind("change",function(){
                $("#uploadEventPath").attr("value",$("#uploadEventFile").val());
            });

        };

        this.sendAjaxRequest = function(url,type,data){
            $.ajax({
                url : url,
                type : type,
                data : data,
                success : function(result) {
                    alert( "excel上传成功！");
                },
                error : function() {
                    alert( "excel上传失败！");
                },
                cache : false,
                contentType : false,
                processData : false
            });
        };
    }

    $("#uploadBtn").click(function () {
        var uploadEventFile = $("#uploadEventFile").val();
        if(uploadEventFile == ''){
            alert("请选择excel,再上传");
        }else if(uploadEventFile.lastIndexOf(".xls")<0){//可判断以.xls和.xlsx结尾的excel
            alert("只能上传Excel文件");
        }else{
            var url =  '<%=basePath%>ExcelImport/StudentImport/';
            var formData = new FormData($('form')[0]);
            user.sendAjaxRequest(url,'POST',formData);
        }
    });

    var user;
    $(function(){
        user = new User();
        user.init();
    });

</script>
</body>
</html>
