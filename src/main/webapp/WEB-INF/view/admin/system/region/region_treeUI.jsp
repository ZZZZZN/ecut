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
    <link rel="stylesheet" href="<%=basePath%>static/plugins/ztree/zTreeStyle/zTreeStyle.css" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath%>static/skin/css/base.css">
  </head>
  <body class="hold-transition skin-blue sidebar-mini">
    <div class="wrapper">
      	<div id="regionTree" class="ztree"></div>
    </div><!-- ./wrapper -->
    
    <script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
    <script src="<%=basePath%>static/plugins/layer/layer.js"></script>
    <script src="<%=basePath%>static/skin/js/common.js"></script>
    <script src="<%=basePath%>static/skin/js/browser.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/plugins/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath%>static/plugins/ztree/jquery.ztree.excheck-3.5.js"></script>
	<script language="javascript" type="text/javascript">
	 	var setting = {view:{selectedMulti:false,dblClickExpand:false},
					    data:{simpleData:{enable:true}}
					  };
		var zNodes = ${regionTree};
		function setCheck() {
			$.fn.zTree.init($("#regionTree"), setting, zNodes);
		}		
		// 默认选择节点
		function selectCheckNode(){
			var id = '${param.id}';
			if(id!=''){
				var node = tree.getNodeByParam("id",id);
				tree.selectNode(node);
			}
		}
		
		//初始化
		setCheck();
		var tree = $.fn.zTree.getZTreeObj("regionTree");
		selectCheckNode();
    </script>
  </body>
</html>
