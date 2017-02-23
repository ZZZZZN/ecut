<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>

    <jsp:include page="/static/common/head.jsp" flush="true"/>
    <link rel="stylesheet" href="<%=basePath%>static/skin/css/tabcss.css">
    <link rel="stylesheet" href="<%=basePath%>static/AdminLTE/css/skins/skin-yellow-ys.css">
  </head>
  <body class="hold-transition skin-blue-light  sidebar-mini">
    <div class="wrapper">

      <!-- Main Header -->
      <header class="main-header">
 
        <!-- Logo --> 
        <a href="#" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini">征迁</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>经开区征迁数据管理平台</b></span>
        </a>
 
        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <!-- Navbar Right Menu -->
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <!-- User Account Menu -->
              <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" id="userLogo" class="dropdown-toggle" data-toggle="dropdown">
                  <!-- The user image in the navbar-->
                  <img src="<%=basePath%>static/AdminLTE/img/user2-160x160.jpg" class="user-image" alt="User Image">
                  <!-- hidden-xs hides the username on small devices so only the image appears. -->
                  <span class="hidden-xs">当前用户：${currentUser.username}</span>
                </a>
                <ul class="dropdown-menu">
                  <!-- The user image in the menu -->
                  <li class="user-footer">
                   	 <a href="#" id="updatePswBtn"><i class="fa fa-edit fa-btn"></i>修改密码</a>
                  </li>
                  <!-- Menu Footer-->
                  <li class="user-footer">
                      <a href="<%=basePath%>admin/logout"><i class="fa fa fa-sign-out"></i>退出系统</a>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <!-- Left side column. contains the logo and sidebar -->
      <aside class="main-sidebar" >
		<!--
        	作者：279467439@qq.com
        	时间：2016-07-13
        	描述：菜单
        -->
        <section class="sidebar" id="sidebar" style="height: auto;">
          <!-- Sidebar Menu -->
          <ul class="sidebar-menu" id="menu">
          </ul>
        </section>
        <!-- /.sidebar -->
      </aside>

      <!-- Content Wrapper. Contains page content -->
      <div class="content-wrapper">
      	<!--
          	作者：279467439@qq.com
          	时间：2016-07-13
          	描述：Tab菜单
          -->
        <div class="content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="<%=basePath%>admin/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="J_mainContent" id="content-main" style="width: 100%">
            <iframe class="J_iframe" src="<%=basePath%>druid" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="index_v1.html" seamless></iframe>
        </div>
      </div><!-- /.content-wrapper -->

      <!-- Main Footer -->
      <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
         	版本号: V1.0
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2016 <a href="http://www.cnkrt.com/" target="_blank">科睿特软件股份有限公司</a>.</strong> All rights reserved.
      </footer>
    </div><!-- ./wrapper -->

    <!-- REQUIRED JS SCRIPTS -->
	<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
    <script src="<%=basePath%>static/plugins/layer/layer.js"></script>
    <script src="<%=basePath%>static/skin/js/common.js"></script> 
    <script src="<%=basePath%>static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="<%=basePath%>static/bootstrap/js/bootstrap.min.js"></script>
    <!-- AdminLTE App -->
    <script src="<%=basePath%>static/AdminLTE/js/app.min.js"></script>
    <script src="<%=basePath%>static/skin/js/contabs.min.js"></script>
    
	<script>
		$("#content-main").css("height",$(window).height()-141);
		$(function(){
			$(window).resize(function() {
				$("#content-main").css("height",$(window).height()-141);
			});
			
			//获取菜单
			$.ajax({   
		         type: "POST",
		         url:"<%=basePath%>admin/selectRoleRes",
		         data:"",// 要提交的表单
		         beforeSend:function(){
		         	return  loading();
		         },
		         success: function(resList) {
		        	 closeloading();
		         	 //渲染菜单
		         	 var menuHtml='';
		         	 for(var i=0; i<resList.length;i++){ 
		         		var res =  resList[i];
		         		var icon = res.icon;
		         		if(icon==null || icon==''){
		         			icon="fa-link";
		         		}
	         			//获取第一级菜单
	         			var menu ='<li class="treeview"><a href="#"><i class="fa '+icon+'"></i> <span>'+res.name+'</span> <i class="fa fa-angle-left pull-right"></i></a>';
	         			var menu2 = '';
	         			var child1 = res.child;
	         			for(var j=0; j<child1.length;j++){ 
	         				var cres = child1[j];
	         				var icon2 = cres.icon;
			         		if(icon2==null || icon2==''){
			         			icon2="fa-circle-o";
			         		}
			         		var child2 = cres.child;
         					//第二级
         					if(child2.length>0){
         						var iconc = '<i class="fa fa-angle-left pull-right"></i>';
         						menu2 = menu2+'<li><a href="#"><i class="fa '+icon2+'"></i>'+cres.name+iconc+'</a>';
         					}else{
         						menu2 = menu2+'<li><a href="<%=basePath%>'+cres.url+'" class="J_menuItem"><i class="fa '+icon2+'"></i>'+cres.name+'</a>';
         					}
         					//第三级
			         		var menu3 = '';
         					for(var k=0; k<child2.length;k++){ 
         						var ccres = child2[k];
		         				var icon3 = ccres.icon;
				         		if(icon3==null || icon3==''){
				         			icon3="fa-circle-o";
				         		}
				         		menu3 = menu3+'<li><a href="<%=basePath%>'+ccres.url+'" class="J_menuItem"><i class="fa '+icon3+'"></i>'+ccres.name+'</a>';
				         		
         					}
         					if(menu3!=''){
         						menu3 = '<ul class="treeview-menu" style="display: none;">'+ menu3 +'</ul>';
         					}
         					menu2 = menu2+menu3+'</li>';
	         			}
	         		    menu = menu + '<ul class="treeview-menu">'+menu2 +'</ul></li>';
	         		    menuHtml = menuHtml + menu;
		         	}
	         		$("#menu").html(menuHtml);
	         		$('.J_menuItem').on('click', menuItem);
		         },
		         error: function(){
		        	 closeloading();
		         }
		      });
			
			//修改密码
			$("#updatePswBtn").click(function(){
				openDialog("修改密码","<%=basePath%>admin/system/user/user_updatePswUI","800px", "330px","");
			});
		});
	</script>
  </body>
</html>
