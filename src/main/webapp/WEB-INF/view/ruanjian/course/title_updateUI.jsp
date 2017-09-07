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
						<h5>选题表添加</h5>
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
										<td class="width-35"><input type="text" name="title_name" id="title_name" class="form-control"></td>
										<td class="active width-15">
											<label class="pull-right">
												课题类型
											</label>
										</td>
										<td class="width-35">
											<select name="title_type" id="title_type" class="form-control">
												<option value="1">理论研究</option>
												<option value="2">应用研究</option>
												<option value="3">开发研究</option>
												<option value="4">工艺设计</option>
												<option value="5">工程设计</option>
												<option value="6">设备设计</option>
												<option value="7">软件设计</option>
												<option value="8">实践研究</option>
												<option value="9">指定问题研究</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="active width-15">
											<label class="pull-right">
												课题来源
											</label>
										</td>
										<td class="width-35">
											<select name="title_source" id="title_source" class="form-control">
												<option value="1">国家资助</option>
												<option value="2">省资助项目</option>
												<option value="3">企业资助项目</option>
												<option value="4">学校资助项目</option>
												<option value="5">自选项目</option>
												<option value="6">储备项目</option>
											</select>
										</td>
										<td class="active width-15">
											<label class="pull-right">
												适用专业
											</label>
										</td>
										<td class="width-35">
											<select name="suitMajor" id="suitMajor" class="form-control">
												<option value="1">通信工程</option>
												<option value="2">计算机科学与技术</option>
												<option value="3">软件工程专业</option>
												<option value="4">物联网工程</option>
												<option value="5">网络工程专业</option>
												<option value="6">数字媒体专业</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="active width-15">
											<label class="pull-right">
												适用实训所在地
											</label>
										</td>
										<td class="width-35">
											<select name="suitScope" id="suitScope" class="form-control">
												<option value="1">校内实训</option>
												<option value="2">校外实训</option>
											</select>
										</td>
										<td class="active width-15">
											<label class="pull-right">
												上限人数
											</label>
										</td>
										<td class="width-35"><input type="text" name="limit_person" id="limit_person" class="form-control"></td>
									</tr>
									<tr>
										<td class="active width-15">
											<label class="pull-right">
												课程意义与目标
											</label>
										</td>
										<td colspan="3">
											<textarea rows="7" type="text" name="meaning_target" id="meaning_target" class="form-control"></textarea>
										</td>
									</tr>
									<tr>
										<td class="active width-15">
											<label class="pull-right">
												学生基本条件和前期工作
											</label>
										</td>
										<td colspan="3">
											<textarea rows="7" type="text" name="condition_work" id="condition_work" class="form-control"></textarea>
										</td>
									</tr>
								</table>
								<button type="submit" class="btn btn-primary" style="position: relative;left: 50%;width: 100px;right: 50px">保存</button>
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

    var validateForm;

    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        $.ajax({
            type: "POST",
            url:"<%=basePath%>ruanjian/course/title_update",
            data:$('#krtForm').serialize(),// 要提交的表单
            beforeSend:function(){
                return  validateForm.form() && loading();
            },
            success: function(msg) {
                closeloading();
                if(msg.state=='success'){
                    top.layer.msg("操作成功");
                    var index = top.layer.getFrameIndex(window.name); //获取窗口索引
                    refreshTable();
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
        validateForm= $("#krtForm").validate();
        //立即验证
        validateForm.form();
    });

</script>
</body>
</html>
