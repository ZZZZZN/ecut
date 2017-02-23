/**
 * 通用公共方法
 * 
 */
var layer_loading;
var layer_window;
var layer_confirm;
// 显示加载框
function loading() {
	layer_loading = top.layer.load({
		shade : 0.5
	});
}
//关闭加载框
function closeloading() {
	top.layer.close(layer_loading);
}
//询问框
function confirmx(tips, fun) {
	layer_confirm = top.layer.confirm(tips, {
		btn : [ '确定', '取消' ]
	//按钮
	}, fun, function() {
		layer.close(layer_confirm);
	});
}

//刷新Iframe
function refreshIframe() {
	var target = top.$(".J_iframe:visible");
	if (target[0] == null || target[0] == 'undefined') {
		parent.location.href = parent.location.href;
	} else {
		var url = target[0].contentWindow.location.href;
		//显示loading提示
		var loading = layer.load();
		target.attr('src', url).load(function() {
			//关闭loading提示
			layer.close(loading);
		});
	}
}
//刷新dataTable
function refreshTable(datatableObj) {
	if (datatable == null || datatable == 'undefined') {//添加、修改
		var target = top.$(".J_iframe:visible");
		if (target[0] == null || target[0] == 'undefined') {
			var datatable = parent.window.datatable;
			datatable.ajax.reload(null, false);
		} else {
			var datatable = target[0].contentWindow.datatable;
			datatable.ajax.reload(null, false);
		}
	} else {//删除
		start = $("#datatable").dataTable().fnSettings()._iDisplayStart;
		total = $("#datatable").dataTable().fnSettings().fnRecordsDisplay();
		if ((total - start) == 1) {
			if (start > 0) {
				$("#datatable").dataTable().fnPageChange('previous', true);
			}
		}
		datatableObj.ajax.reload(null, false);
	}
}

//打开对话框(添加修改)
function openDialog(title, url, width, height, target) {

	if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，就使用自适应大小弹窗
		width = 'auto';
		height = 'auto';
	} else {//如果是PC端，根据用户设置的width和height显示。

	}

	layer_window = top.layer.open({
		type : 2,
		area : [ width, height ],
		title : title,
		maxmin : false, //开启最大化最小化按钮
		content : url,
		btn : [ '确定', '关闭' ],
		yes : function(index, layero) {
			var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			iframeWin.contentWindow.doSubmit();
		},
		cancel : function(index) {
			top.layer.close(layer_window);
		}
	});

}

//打开对话框(添加修改)(默认最大化)
function openMaxDialog(title, url, width, height, target) {

	if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，就使用自适应大小弹窗
		width = 'auto';
		height = 'auto';
	} else {//如果是PC端，根据用户设置的width和height显示。

	}

	layer_window = top.layer.open({
		type : 2,
		area : [ width, height ],
		title : title,
		maxmin : true, //开启最大化最小化按钮
		content : url,
		btn : [ '确定', '关闭' ],
		yes : function(index, layero) {
			var iframeWin = layero.find('iframe')[0]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
			iframeWin.contentWindow.doSubmit();
		},
		cancel : function(layer_window) {
			top.layer.close(layer_window);
		}
	});
	top.layer.full(layer_window);

}

//打开对话框(查看)
function openDialogView(title, url, width, height) {

	if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，就使用自适应大小弹窗
		width = 'auto';
		height = 'auto';
	} else {//如果是PC端，根据用户设置的width和height显示。

	}
	layer_window = top.layer.open({
		type : 2,
		area : [ width, height ],
		title : title,
		maxmin : false, //开启最大化最小化按钮
		content : url,
		btn : [ '关闭' ],
		cancel : function(layer_window) {
			top.layer.close(layer_window);
		}
	});
}

//打开对话框(查看)
function openMaxDialogView(title, url, width, height) {

	if (navigator.userAgent.match(/(iPhone|iPod|Android|ios)/i)) {//如果是移动端，就使用自适应大小弹窗
		width = 'auto';
		height = 'auto';
	} else {//如果是PC端，根据用户设置的width和height显示。

	}
	layer_window = top.layer.open({
		type : 2,
		area : [ width, height ],
		title : title,
		maxmin : true, //开启最大化最小化按钮
		content : url,
		btn : [ '关闭' ],
		cancel : function(layer_window) {
			top.layer.close(layer_window);
		}
	});
	top.layer.full(layer_window);
}

String.prototype.startWith=function(str){
	var reg=new RegExp("^"+str);
	return reg.test(this);
} 

function IsURL(str_url) {
	if(str_url.startWith("http")||str_url.startWith("wwww.")){
		return true;
	}else{
		return false;
	}
}

$.ajaxSetup({
    complete:function(XMLHttpRequest,textStatus) {
		if (XMLHttpRequest.getResponseHeader('Session-Status') == 'timeout') {
			top.layer.msg("登陆超时！请重新登陆！");
			setTimeout(function () {
				top.location.href = top.location.href;
			}, 1000);
		} else if (textStatus == "parsererror") {
			top.layer.msg("JSON解析错误！");
		}
	}
});
