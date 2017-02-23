<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="renderer" content="webkit">
<title>经开区征迁数据管理平台</title>
<!--[if lte IE 8]><script>window.location.href='<%=basePath%>static/common/browser.html';</script><![endif]-->
<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
<!-- Bootstrap 3.3.5 -->
<link rel="stylesheet" href="<%=basePath%>static/bootstrap/css/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet" href="<%=basePath%>static/font-awesome-4.4.0/css/font-awesome.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="<%=basePath%>static/AdminLTE/css/AdminLTE.min.css">


