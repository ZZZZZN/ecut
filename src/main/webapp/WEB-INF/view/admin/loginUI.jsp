<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="<%=basePath%>static/skin/css/login.css">
    <jsp:include page="/static/common/head.jsp" flush="true"/>
    <script type="text/javascript">
        if(self.location!=top.location){
            top.location.href = self.location.href;
        }
        (function (doc, win) {
            var docEl = doc.documentElement
            var resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize'
            var recalc = function () {
                var clientWidth = docEl.clientWidth
                if (!clientWidth) return
                docEl.style.fontSize = 20 * (clientWidth / 1440) + 'px'
            }
            if (!doc.addEventListener) return
            win.addEventListener(resizeEvt, recalc, false)
            doc.addEventListener('DOMContentLoaded', recalc, false)
        })(document, window)
    </script>
</head>
<body class="hold-transition login-bg">

<div class="login-left">
    <img src="<%=basePath%>static/skin/images/ecut-logo.png" class="ecut-logo">
    <div class="photo"></div>
</div>
<div class="ecut-title">毕业设计选题系统</div>
<div class="login-right">
    <div class="login-ecut-body">
        <p class="login-box-msg">欢迎登录系统</p>
        <form action="#" method="post" id="loginForm">
            <div class="form-group has-feedback">
                <input type="text" id="username" name="username" class="form-control" placeholder="账号">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" id="password" name="password" class="form-control" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="text" id="code" name="code" class="form-control" placeholder="验证码" style="width:5rem;display: inline;">
                <img alt="验证码" id="imgCode" src="<%=basePath%>admin/tools/imgCode/imgCode_get" style="float: right" onclick="changeImg();">
            </div>
            <div class="row">
                <div class="col-xs-4">
                    <button type="button" id="loginBtn" class="btn btn-primary btn-block btn-flat">登录</button>
                </div><!-- /.col -->
            </div>
        </form>
    </div><!-- /.login-box-body -->
</div><!-- /.login-box -->
<!-- jQuery 2.1.4 -->
<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
<script src="<%=basePath%>static/skin/js/common.js"></script>
<script type="text/javascript">
    $(function(){
        $("#loginBtn").click(function(){
            var username = $("#username").val();
            var password = $("#password").val();
            var code = $("#code").val();
            if(username==null || username==''){
                layer.msg("用户账号不可为空");
                return false;
            }
            if(password==null || password==''){
                layer.msg("密码不可为空");
                return false;
            }
            if(code==null || code==''){
                layer.msg("验证码不可为空");
                return false;
            }
            $.ajax({
                type: "POST",
                url:"<%=basePath%>admin/login",
                data:$('#loginForm').serialize(),// 要提交的表单
                beforeSend:function(){
                    return  loading();
                },
                success: function(msg) {
                    closeloading();
                    if(msg.state=='success'){
                        location.href="<%=basePath%>admin/index";
                    }else if(msg.state=='code_error'){
                        layer.msg('验证码错误');
                        changeImg();
                    }else{
                        layer.msg('登录失败');
                    }
                },
                error: function(){
                    layer.msg('服务器连接失败');
                }
            });
        });
    });
    //刷新验证码
    function changeImg() {
        var imgSrc = $("#imgCode");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    }
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        var urls = url.split("?");
        url = urls[0] + "?timestamp=" + timestamp;
        return url;
    }
</script>
</body>
</html>
