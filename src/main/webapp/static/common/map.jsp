<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String lat=request.getParameter("lat")==null?"":request.getParameter("lat");
String lng=request.getParameter("lng")==null?"":request.getParameter("lng");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta id="viewport" name="viewport"
	content="width = device-width; initial-scale=1.0;
maximum-scale=1.0; user-scalable=0;" />
<meta content="yes" name="apple-mobile-web-app-capable" />
<meta content="telephone=no" name="format-detection" />
<title>地图标注</title>
<script type="text/javascript">
window.addEventListener("load",function(){setTimeout(function(){window.scrollTo(0, 1);},0);});
</script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?ak=nfPeVUXFF4FUsex4gVfrIG5b&v=2.0"></script>
<script src="<%=basePath%>static/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<style type="text/css">
* {
	padding: 0;
	margin: 0;
}

html,body {
	
}

#shMap {
	width: 100%;
	height: 100%;
	position: absolute;
	left: 0;
	top: 0;
}

#submit {
	width: 200px;
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	border: 1px solid #d2d2d2;
	background: #FFF;
	position: absolute;
	left: 0;
	bottom: 20px;
}

#bgzz {
	DISPLAY: block;
	Z-index: 9999;
	background: #222222;
	left: 0px;
	width: 100%;
	height: 30px;
	position: fixed;
	top: 0px;
	opacity: 0.8;
	text-align: center;
	padding-top: 5px;
	color: #ffffff;
}

#btn {
	background: #2e8ded;
	border-color: #2e8ded;
	color: #fff;
	border: 1px solid;
	border-radius: 2px;
	cursor: pointer;
	font-family: "Microsoft yahei", Arial;
	font-size: 12px;
	height: 24px;
	line-height: 24px;
	margin-bottom: 10px;
	padding: 0 12px;
}
</style>
</head>
<body>
	<div id="bgzz">
		输入地址：<input type="text" id="addr" value="" size="30" /> 
		<input type="button" id="btn" value="确定" onclick="getXy()" /> 
		<spanstyle="color: #f00">注意：这里输入详细地址</span>
	</div>
	<div id="shMap"></div>
	<input type="hidden" name="lng" id="lng" value="${param.lng}" />
	<input type="hidden" name="lat" id="lat" value="${param.lat}" />
	<script type="text/javascript">
var lcp_marker;
function myResize(){
	var lcp_w = document.documentElement.clientWidth;
	var lcp_h = document.documentElement.clientHeight;
	var P = document.getElementById("shMap");
	if(lcp_w == 0 && lcp_h == 0){lcp_w = document.body.clientWidth;lcp_h = document.body.clientHeight;}
	P.style.width = lcp_w+"px";P.style.height = lcp_h+"px";
}
myResize();
window.onresize = function(){ myResize(); }
var map = new BMap.Map("shMap",{enableMapClick:false});
map.addControl(new BMap.NavigationControl());
map.addControl(new BMap.MapTypeControl({mapTypes:[BMAP_NORMAL_MAP,BMAP_SATELLITE_MAP,BMAP_HYBRID_MAP]}));
map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT}));
var lng="${param.lng}";
var lat="${param.lat}";
if(lat=="" || lng==""){
	map.centerAndZoom(new BMap.Point(115.000128,27.119638), 11);
}else{
	map.centerAndZoom(new BMap.Point(lng, lat), 16);
}

map.enableScrollWheelZoom(true);
map.enableDragging();
(function(){
	var a = document.getElementById("lat").value,b = document.getElementById("lng").value;
	if(a!=""&&b!=""){
		lcp_marker = new BMap.Marker(new BMap.Point(b, a));			
		lcp_marker.setTitle("当前标注点");
		lcp_marker.enableDragging();
		map.addOverlay(lcp_marker);
	}
})()
map.addEventListener("click", function(e){
	var point = new BMap.Point(e.point.lng, e.point.lat);
	if(!lcp_marker){
		lcp_marker = new BMap.Marker(point);			
		lcp_marker.setTitle("当前标注点");
		lcp_marker.enableDragging();
		map.addOverlay(lcp_marker);
		document.getElementById("lat").value = e.point.lat;
		document.getElementById("lng").value = e.point.lng;			
	}
	else{
		lcp_marker.setPosition(point);
		document.getElementById("lat").value = e.point.lat;
		document.getElementById("lng").value = e.point.lng;		
	}
});

function getXy(){
	// 创建地址解析器实例
	var myGeo = new BMap.Geocoder();
		var addr=document.getElementById("addr").value;
		if(addr!=""){
			// 将地址解析结果显示在地图上,并调整地图视野
			myGeo.getPoint(addr, function(point){
				if (point) {
					map.centerAndZoom(point, 16);
					map.addOverlay(new BMap.Marker(point));
					//alert(point.lat+","+point.lng)
					document.getElementById("lat").value = point.lat;
					document.getElementById("lng").value = point.lng;					
				}else{
					alert("您选择地址没有解析到结果!");
				}
			}, "吉安市");
		}
}
</script>
</body>
</html>

