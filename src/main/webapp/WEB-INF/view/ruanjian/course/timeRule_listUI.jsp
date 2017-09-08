<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/static/common/head.jsp" flush="true" />
	<link rel="stylesheet" href="<%=basePath%>static/skin/css/base.css">
	<link rel="stylesheet" href="<%=basePath%>static/plugins/datatables/dataTables.bootstrap.css">
	<script src="<%=basePath%>static/plugins/My97DatePicker/WdatePicker.js"></script>
</head>
<body class="hold-transition sidebar-mini body-bg">
	<div class="wrapper">
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-xs-12">
						<div class="box">
							<div class="box-header">
								<h5>时间管理</h5>
							</div>
							<div class="box-body">
								<div class="row">
								</div>
								<div class="form-box">
									<form id="krtForm" class="form-horizontal">
										<input type="hidden" name="id" value="${timeRule.id}">
										<table class="table table-bordered table-krt" style="width: 90%;margin-left: 50px">
											<tr>
												<td class="active width-15">
													<label class="pull-right">
														教师出题开始时间
													</label>
												</td>
												<td class="width-35">
													<input type="text" name="teacher_time_begin" id="teacher_time_begin" value="${timeRule.teacher_time_begin}" onclick="WdatePicker()" class="form-control">
												</td>
												<td class="active width-15">
													<label class="pull-right">
														教师出题结束时间
													</label>
												</td>
												<td class="width-35">
													<input type="text" name="teacher_time_end" id="teacher_time_end" value="${timeRule.teacher_time_end}" onclick="WdatePicker()" class="form-control">
												</td>
											</tr>
											<tr>
												<td class="active width-15">
													<label class="pull-right">
														学生选题开始时间
													</label>
												</td>
												<td class="width-35">
													<input type="text" name="student_time_begin" id="student_time_begin" value="${timeRule.student_time_begin}" onclick="WdatePicker()" class="form-control">
												</td>
												<td class="active width-15">
													<label class="pull-right">
														学生选题结束时间
													</label>
												</td>
												<td class="width-35">
													<input type="text" name="student_time_end" id="student_time_end" value="${timeRule.student_time_end}" onclick="WdatePicker()" class="form-control">
												</td>
											</tr>
											<tr>
												<td class="active width-15">
													<label class="pull-right">
														毕业设计任务书下达开始时间
													</label>
												</td>
												<td class="width-35">
													<input type="text" name="task_time_begin" id="task_time_begin" value="${timeRule.task_time_begin}" onclick="WdatePicker()" class="form-control">
												</td>
												<td class="active width-15">
													<label class="pull-right">
														毕业设计任务书下达结束时间
													</label>
												</td>
												<td class="width-35">
													<input type="text" name="task_time_end" id="task_time_end" value="${timeRule.task_time_end}" onclick="WdatePicker()" class="form-control">
												</td>
											</tr>
											<tr>
												<td class="active width-15">
													<label class="pull-right">
														过程前期开始时间
													</label>
												</td>
												<td>
													<input type="text" name="early_stage_begin" id="early_stage_begin" value="${timeRule.early_stage_begin}" onclick="WdatePicker()" class="form-control">
												</td>
												<td class="active width-15">
													<label class="pull-right">
														过程前期结束时间
													</label>
												</td>
												<td>
													<input type="text" name="early_stage_end" id="early_stage_end" value="${timeRule.early_stage_end}" onclick="WdatePicker()" class="form-control">
												</td>
											</tr>
											<tr>
												<td class="active width-15">
													<label class="pull-right">
														过程中期开始时间
													</label>
												</td>
												<td>
													<input type="text" name="mid_stage_begin" id="mid_stage_begin" value="${timeRule.mid_stage_begin}" onclick="WdatePicker()" class="form-control">
												</td>
												<td class="active width-15">
													<label class="pull-right">
														过程中期结束时间
													</label>
												</td>
												<td>
													<input type="text" name="mid_stage_end" id="mid_stage_end" value="${timeRule.mid_stage_end}" onclick="WdatePicker()" class="form-control">
												</td>
											</tr>
											<tr>
												<td class="active width-15">
													<label class="pull-right">
														过程后期开始时间
													</label>
												</td>
												<td>
													<input type="text" name="later_stage_begin" id="later_stage_begin" value="${timeRule.later_stage_begin}" onclick="WdatePicker()" class="form-control">
												</td>
												<td class="active width-15">
													<label class="pull-right">
														过程后期结束时间
													</label>
												</td>
												<td>
													<input type="text" name="later_stage_end" id="later_stage_end" value="${timeRule.later_stage_end}" onclick="WdatePicker()" class="form-control">
												</td>
											</tr>
											<tr>
												<td class="active width-15">
													<label class="pull-right">
														答辩开始时间
													</label>
												</td>
												<td>
													<input type="text" name="defence_time_begin" id="defence_time_begin" value="${timeRule.defence_time_begin}" onclick="WdatePicker()" class="form-control">
												</td>
												<td class="active width-15">
													<label class="pull-right">
														答辩结束时间
													</label>
												</td>
												<td>
													<input type="text" name="defence_time_end" id="defence_time_end" value="${timeRule.defence_time_end}" onclick="WdatePicker()" class="form-control">
												</td>
											</tr>
										</table>
									</form>
									<div style="text-align: center;">
										<button onclick="doEdit(event)" class="btn btn-warning" style="width: 100px;margin-top: 20px">编辑</button>
									</div>
									<div style="text-align: center;" id="operator">
										<button onclick="doSubmit()" class="btn btn-primary" style="width: 100px;margin-top: 20px">保存</button>
										<button onclick="doCancel()" class="btn btn-danger" style="width: 100px;margin-top: 20px; margin-left: 20px">取消</button>
									</div>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<!-- /.col -->
				</div>
				<!-- /.row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- ./wrapper -->

	<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	<script src="<%=basePath%>static/plugins/pace/pace.min.js"></script>
	<script src="<%=basePath%>static/plugins/layer/layer.js"></script>
	<script src="<%=basePath%>static/plugins/JQueryValidate/jquery.validate.min.js"></script>
	<script src="<%=basePath%>static/plugins/JQueryValidate/localization/messages_zh.js"></script>
	<script src="<%=basePath%>static/skin/js/common.js"></script>
	<script type="text/javascript">

        var validateForm;

        var operator = document.getElementById('operator');

        $(function(){
            $('input').each(function(index, item){
                item.disabled = 'disabled'
			})
            operator.style.display = 'none';
		})

		function doEdit(event){
            $('input').each(function(index, item){
                item.disabled = false;
            })
            operator.style.display = 'block' ;
            event.target.style.display = 'none'
		}

		function doCancel(){
            window.location.href = "<%=basePath%>ruanjian/course/timeRule_listUI";
		}

        function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
            $.ajax({
                type: "POST",
                url:"<%=basePath%>ruanjian/course/timeRule_update",
                data:$('#krtForm').serialize(),// 要提交的表单
                beforeSend:function(){
                    return  validateForm.form() && loading();
                },
                success: function(msg) {
                    closeloading();
                    if(msg.state=='success'){
                        top.layer.msg("操作成功");
                        window.location.href = "<%=basePath%>ruanjian/course/timeRule_listUI";
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
