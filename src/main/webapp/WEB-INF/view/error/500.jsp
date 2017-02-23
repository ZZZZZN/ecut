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
    <style type="text/css">
		.content-wrapper, .right-side{
			background: #fff
		}
    </style>
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
        
          <div class="error-page">
            <h2 class="headline text-red">500</h2>
            <div class="error-content">
              <h3><i class="fa fa-warning text-red"></i>程序出现异常</h3>
              <p>抱歉！程序出现异常！请及时联系管理员！</p>
            </div>
          </div><!-- /.error-page -->
        </section><!-- /.content -->
      </div><!-- /.content-wrapper -->
    </div><!-- ./wrapper -->
    
    <script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
     <script type="text/javascript" src="<%=basePath%>static/skin/js/base.js"></script>
  </body>
</html>
