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
	<link rel="stylesheet" href="<%=basePath%>static/skin/css/checkbox.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<section class="content">
		<div class="row">
			<div class="col-xs-12">
				<div class="box">
					<div class="box-header">
						<h5>选题表修改</h5>
						<span type="button" id="insertBtn" data-placement="left" data-toggle="tooltip" onclick="history.go(-1);" style="float: right; cursor: pointer;">
                            <i class="fa fa-mail-reply"></i> 返回
                        </span>
					</div>
					<div class="box-body">
						<div class="form-box">
							<form id="krtForm" class="form-horizontal">
								<table class="table table-bordered table-krt" style="width: 80%;margin-left: 100px">
									<tr>
										<td class="active width-15">
											<label class="pull-right">
												课题名称
											</label>
										</td>
										<td class="width-35"><input type="text" name="title_name" id="title_name" class="form-control" value="${title.title_name}" required></td>
										<td class="active width-15">
											<label class="pull-right">
												课题类型
											</label>
										</td>
										<td class="width-35">
											<select name="title_type" id="title_type" class="form-control">
												<option value="理论研究">理论研究</option>
												<option value="应用研究">应用研究</option>
												<option value="开发研究">开发研究</option>
												<option value="工艺设计">工艺设计</option>
												<option value="工程设计">工程设计</option>
												<option value="设备设计">设备设计</option>
												<option value="软件设计">软件设计</option>
												<option value="实践研究">实践研究</option>
												<option value="指定问题研究">指定问题研究</option>
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
												<option value="国家资助">国家资助</option>
												<option value="省资助项目">省资助项目</option>
												<option value="企业资助项目">企业资助项目</option>
												<option value="学校资助项目">学校资助项目</option>
												<option value="自选项目">自选项目</option>
												<option value="储备项目">储备项目</option>
											</select>
										</td>
										<td class="active width-15">
											<label class="pull-right">
												适用实训所在地
											</label>
										</td>
										<td class="width-35">
											<select name="suitScope" id="suitScope" class="form-control">
												<option value="校内实训">校内实训</option>
												<option value="校外实训">校外实训</option>
											</select>
										</td>
									</tr>
									<tr>
										<td class="active width-15">
											<label class="pull-right">
												上限人数
											</label>
										</td>
										<td class="width-35"><input type="text" name="limit_person" id="limit_person" class="form-control" AUTOCOMPLETE="off" value="${title.limit_person}" required></td>
									</tr>
									<tr>
										<td class="active width-15">
											<label class="pull-right">
												适用专业
											</label>
										</td>
										<td colspan="3" id="major_td">
											<div><span>${ title.suitMajorName }</span><a onclick="doEdit()" class="btn btn-warning" style="display: block;font-size: 12px;padding: 2px 8px;margin-top: 5px;width: 70px;">重新选择</a></div>
										</td>
									</tr>
									<tr>
										<td class="active width-15">
											<label class="pull-right">
												课程意义与目标
											</label>
										</td>
										<td colspan="3">
											<textarea rows="7" type="text" name="meaning_target" id="meaning_target" class="form-control"  required>${title.meaning_target}</textarea>
										</td>
									</tr>
									<tr>
										<td class="active width-15" style="text-align: center;">
											<label class="pull-right">
												学生基本条件和前期工作
											</label>
										</td>
										<td colspan="3">
											<textarea rows="7" type="text" name="condition_work" id="condition_work" class="form-control" required>${title.condition_work}</textarea>
										</td>
									</tr>
								</table>
								<input type="hidden" name="id" id="id" value="${ title.id }">
							</form>
                            <button onclick="doSubmit()" class="btn btn-primary" style="position: relative;left: 50%;width: 100px;right: 50px">保存</button>
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

    function checkNum(elm){
        return /\d+/.test(elm.val())
    }

    function doEdit() {
        var node = document.getElementById("major_td");
        var oldDiv = node.getElementsByTagName('div')[0];
        var newDiv = document.createElement('div');
        newDiv.innerHTML = '<input type="text" name="suitMajorName" style="display: none;"><c:forEach items="${map}" var="title">\n' +
            '\t\t\t\t\t\t\t\t\t\t\t\t<label style="font-weight: normal;"><input type="checkbox" name="suitMajor" value="${title.major_code}">${title.major_name}</label>\n' +
            '\t\t\t\t\t\t\t\t\t\t\t</c:forEach><a onclick="doCancel()" class="btn btn-danger" style="display: block;font-size: 12px;padding: 2px 8px;margin-left: 15px;margin-top: 5px;width: 45px;">取消</a>';
        node.replaceChild(newDiv,oldDiv);
    }

    function doCancel() {
        var node = document.getElementById("major_td");
        var oldDiv = node.getElementsByTagName('div')[0];
        var newDiv = document.createElement('div');
        newDiv.innerHTML = '<span>${ title.suitMajorName }</span><a onclick="doEdit()" class="btn btn-warning" style="display: block;font-size: 12px;padding: 2px 8px;margin-top: 5px;width: 70px;">重新选择</a>';
        node.replaceChild(newDiv,oldDiv);
    }

    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
		var majorInput = $("input[name='suitMajorName']");
		console.log(majorInput);
		if(majorInput.length){
            var name = [];
            var majorScope = $("input[name='suitMajor']:checked").each(function (index,val) {
                name.push(val.parentNode.innerText);
            });
            var suitMajorName = name.join(',')
            $("input[name='suitMajorName']").val(suitMajorName);
		}
        if(checkNum($('#limit_person'))) {
            $.ajax({
                type: "POST",
                url: "<%=basePath%>ruanjian/course/title_update",
                data: $('#krtForm').serialize(),// 要提交的表单
                beforeSend: function () {
                    return validateForm.form() && loading();
                },
                success: function (msg) {
                    closeloading();
                    if (msg.state == 'success') {
                        top.layer.msg("操作成功");
                        location.href = "<%=basePath%>ruanjian/course/title_listUI";
                        var index = top.layer.getFrameIndex(window.name); //获取窗口索引
                        refreshTable();
                        top.layer.close(index);
                    } else {
                        top.layer.msg("操作失败");
                    }
                },
                error: function () {
                    closeloading();
                }
            });
        }else{
            top.layer.msg("人数上限必须为数字！");
		}
    }

    $(function(){
        validateForm= $("#krtForm").validate();
        //立即验证
        validateForm.form();
    });

</script>
</body>
</html>
