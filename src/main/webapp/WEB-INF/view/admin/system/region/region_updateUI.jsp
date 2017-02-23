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
      							上级区域
      						</label>
      					</td>
      					<td class="width-35">
      						<div class="input-group">
								<input type="text" name="pname" value="${region.pname}" id="pname" class="form-control" readonly="readonly">
								<span class="input-group-btn">
									<button class="btn btn-primary btn-flat" id="regionTreeBtn" type="button"><i class="fa fa-search"></i></button>
								</span>
								<!-- 参数 -->
								<input type="hidden" name="pid" id="pid" value="${region.pid}" class="form-control">
							</div>
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>区域类型
      						</label>
      					</td>
      					<td class="width-35">
      						<select class="form-control" name="type" required>
      							<option value="">==请选择==</option>
      							<c:forEach items="${typeList}" var="type">
      								<option value="${type.code}" ${region.type==type.code?'selected':''}>${type.name}</option>
      							</c:forEach>
      						</select>  
      					</td>
      				</tr>
      				<tr>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>区域名称
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="text" name="name" id="name" value="${region.name}" class="form-control"  required>
      					</td>
      					<td class="active width-15">
      						<label class="pull-right">
      							<font color="red">*</font>区域编码
      						</label>
      					</td>
      					<td class="width-35">
      						<input type="text" name="code" id="code" value="${region.code}" class="form-control" rangelength="2,20" required>
      					</td>
      				</tr>
      			</table>
      			<!-- 参数 -->
      			<input type="hidden" name="id" id="id" value="${region.id}"/>
      		</form>
      	 </div>
    </div><!-- ./wrapper -->
    
    <script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
    <script src="<%=basePath%>static/plugins/layer/layer.js"></script>
    <script src="<%=basePath%>static/plugins/iCheck/icheck.min.js"></script>
    <script src="<%=basePath%>static/plugins/JQueryValidate/jquery.validate.min.js"></script>
    <script src="<%=basePath%>static/plugins/JQueryValidate/localization/messages_zh.js"></script>
    <script src="<%=basePath%>static/skin/js/common.js"></script>
    <script type="text/javascript">
	    var validateForm;
		function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
			  $.ajax({   
			         type: "POST",
			         url:"<%=basePath%>admin/system/region/region_update",
			         data:$('#krtForm').serialize(),// 要提交的表单
			         beforeSend:function(){
			         	return  validateForm.form() && loading();
			         },
			         success: function(msg) {
			        	 closeloading();
			         	if(msg.state=='success'){
			         		top.layer.msg("操作成功");
			         		var index = top.layer.getFrameIndex(window.name); //获取窗口索引
			         		refreshIframe(); 
			         		top.layer.close(index);
			         	}else{
			         		top.layer.msg("操作失败");
			         	}
			         },
			         error: function(){
			        	 closeloading();
			         }
			      });
		}
    	$(function(){
    		validateForm= $("#krtForm").validate({
    			rules: {
    				code: {
						remote: {
						    url: "<%=basePath%>admin/system/region/checkCode",     //后台处理程序
						    type: "post",               //数据发送方式
						    dataType: "json",           //接受数据格式   
						    data: {                     //要传递的数据
						    	username: function() {
						            return $("#code").val();
						        },
						    	id: function() {
					            return $("#id").val();
					        	}
						    }
					    }
    		        }
				},
				messages: {
					code: {remote: "区域编码已存在"}
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-group")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
    		//立即验证
    		validateForm.form();
    		//icheck
    		$('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
    	          checkboxClass: 'icheckbox_minimal-blue',
    	          radioClass: 'iradio_minimal-blue'
    	    });
    		
    		$("#regionTreeBtn").click(function(){
    			top.layer.open({
    				type : 2,
    				area : [ '300px', '450px' ],
    				title : "选择区域",
    				maxmin : true, //开启最大化最小化按钮
    				content : "<%=basePath%>admin/system/region/region_treeUI?id="+$("#pid").val(),
    				btn : [ '确定', '关闭' ],
    				yes : function(index, layero) {
    					var tree = layero.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
    					var nodes = tree.getSelectedNodes();
    					if(nodes==null){
    						top.layer.msg("请选择资源");
    					}else{
    						$("#pname").val(nodes[0].name);
    						$("#pid").val(nodes[0].id);
    						top.layer.close(index);
    					}
    				},
    				cancel : function(index) {
    					top.layer.close(layer_window);
    				}
    			});
    		});
    	});
    </script>
  </body>
</html>
