var checkedArr=new Array();
var ctrl = false;  
var shift = false;  
var beforClassName="";
//处理按ctrl和shift键  
document.onkeydown = function () {  
	if (event.keyCode == 17) {  
		ctrl = true;  
	} else {  
		if (event.keyCode == 16) {  
			shift = true;  
		}  
	}  
};  
document.onkeyup = function () {  
	ctrl = false;  
	shift = false;  
};  
  
//鼠标经过时  
function hilite(theTD){  
	beforClassName=theTD.className;
    if(theTD.className != "selected"){  
        theTD.className = "mouseover";  
    } 
}  
//鼠标离开时  
function restore(theTD){  
    if(theTD.className != "selected"&&theTD.className != "bgRed")  {
        theTD.className = ""; 
    }
}  
//选择日期
function clickTd(tdObj){
   	if (ctrl && shift) {//同时按下ctrl和shift则不操作
    	return;  
   	}  
   	beforClassName=tdObj.className;
  	if (!ctrl && !shift) {//没有按ctrl或shift时 
		var rows= infoTable.rows.length;
		var b=true;
		if(tdObj.className=="selected"||tdObj.className==""){
			b=false;
			beforClassName="";
		}
		if(tdObj.className=="bgRed"){
			beforClassName="";
		}
		for (var i = 1; i <rows; i++) { 
			for(var j=0;j<infoTable.rows[i].cells.length;j++){
				var tempObj=infoTable.rows[i].cells[j];
				if(tempObj.tagName=="TD"&&tempObj.className!="bgRed"){
					tempObj.className="";
				}
			}
		}
		checkedArr.length=0;
		if(b){
			tdObj.className="selected";
			checkedArr[checkedArr.length]=tdObj.id;
		}
		fisterClick=0;
	}
	if(ctrl){//按ctrl键时
		if(tdObj.className=="selected"){//已经选中了TD，第二次点击
			tdObj.className="";
			//去除数据中的元素.
			for(var i=0;i<checkedArr.length;i++){
				if(checkedArr[i]==tdObj.id){
					checkedArr=removeElement(i,checkedArr);
				}
			}
		}else{
			tdObj.className="selected";
			checkedArr[checkedArr.length]=tdObj.id;
		}
	}
	if(shift){//按shift键时
		return ; 
	}
	findHolidayName(tdObj);//显示节日名
}

//AJAX得到当前月份是否设定节假日
function isExistMonth(){
var month = $("#yearMonth").val();
	$.ajax({
		type:"post",
		url:"admin/holidays/isExistMonth?month="+month, 
		dataType:'json', 
		success:function(data){
				$("#spanyearMonth").html(data);  
		}
	});
}

//得到最近的节假日信息
function getHolidayRecently(){
	var month = $("#yearMonth").val();
	$.ajax({
		type:"post",
		url:"admin/holidays/findHolidayRecently?month="+month, 
		dataType:'json', 
		success:function(data){
		$("#holiday-detail").html("");
			$.each(data.holidayList,function(i,value){   
				$("#holiday-detail").append("<h2>"+value.year_month1+" 法定节假日及周休：</h2><span> "+value.holiday_date +"</span>");  
		    })
		}
	});
}

//文档框显示节日
var fisterClick=0;
function findHolidayName(date){
	$.ajax({
		type:"post",
		contentType:"application/json;charset=utf-8",
		url: "admin/holiday/findHolidayName?date="+date.id, 
		dataType:'json', 
		async:false,  
		success:function(data){
			if(data.holidayName!=""){
				$("#holidayName").val(data.holidayName);
				var dataStr=data.dateStr;
				var dataStrArr=dataStr.split(",");
				if(ctrl){//按CTRL
					
				}else{
					checkedArr.length=0;
				}
				for(var d=0;d<dataStrArr.length;d++){
					if(fisterClick==0){
						for(var f=0;f<checkedArr.length;f++){
							if(checkedArr[f]==dataStrArr[d]){
								checkedArr=removeElement(f,checkedArr);
							}
						}
						checkedArr[checkedArr.length]=dataStrArr[d];
						$("#"+dataStrArr[d]+"").attr("class","selected");
					}
				}
				fisterClick=1;
			}
		}
	});
}

//去除元素
function removeElement(index,array){
	if(index>=0 && index<array.length) {
		for(var i=index; i<array.length; i++)  {
		array[i] = array[i+1];
		}
		array.length = array.length-1;
	}
	return array;
}

//选择节假日
function selectHoliday(){
	var selMonth=document.getElementById("holidayMonth");
	var index=selMonth.selectedIndex;
	var yMonth=selMonth.options[index].getAttribute("monthValue");
	var id=selMonth.value;
	if(id=="-"){
		/*var now = new Date(); 
		var year=now.getFullYear();
		var month=now.getMonth()+1;
		if(parseInt(month)<10){
			month="0"+month;
		}
		now =year+ "-"+month;
		yMonth=now;*/
	}else{
		$("#yearMonth").val(yMonth);
		$("#hideHolidayID").val(id);
		chkCalendar('yearMonth');
	}
	//isExistMonth();
	//getHolidayRecently();
}

//得到某月的最后一天
function getLastDay(yearmonth) {
	var year = yearmonth.substring(0, 4);
	var month = yearmonth.substring(5, 7);
	var new_year = year;    //取当前的年份         
	var new_month = month++;//取下一个月的第一天，方便计算（最后一天不固定）         
	if (month > 12) {            //如果当前大于12月，则年份转到下一年         
	}
	new_month -= 12;        //月份减         
	new_year++;            //年份增         
	var new_date = new Date(new_year, new_month, 1);                //取当年当月中的第一天         
	var date_count = (new Date(new_date.getTime() - 1000 * 60 * 60 * 24)).getDate();//获取当月的天数       
	var last_date = new Date(new_date.getTime() - 1000 * 60 * 60 * 24);//获得当月最后一天的日期
	return date_count;
}    

//根据日期得到星期
function getWeek(yearmonthday) {
	year = yearmonthday.substring(0, 4);
	month = yearmonthday.substring(5, 7) - 1;
	day = yearmonthday.substring(8, 10);
	var newdate = new Date(year, month, day);
	var week = newdate.getDay();
	return week;
} 

//得到带checkbox的日历
function chkCalendar(calendarID) {
	//清
	clearSelect();
	//设
	appendSelect();
	var selectYearMonth = $("#" + calendarID).val();
	if (selectYearMonth != "" && selectYearMonth != undefined) {
		$.ajax({
			type:"post",
			url: "admin/holiday/findMonthHolidays?month=" + selectYearMonth,
			dataType:"json",
			async : false,
			success:function (data) {
				var dateArr=new Array();
				var dateName=new Array();
				var objs = data.holidays;
				if(objs){
					for(var j=0;j<objs.length;j++){
						dateArr[dateArr.length]=objs[j].holiday_date;
						dateName[dateName.length]=objs[j].holiday_name;
					}
				}
				var yearMonth = data.YearMonth;
				var day = data.Day;
			//计算某个月有多少天
			var monthDay = getLastDay(selectYearMonth);
			//本月1号是星期几
			var week = getWeek(selectYearMonth + "-01");
			var calendarHtml = "<tr class=\"date-tab-tr\">";
			for (var i = 0; i < week; i++) {
				calendarHtml += "<td height=\"30\" align=\"left\">&nbsp;</td>";
			}
			for (var j = 0; j < monthDay; j++) {
				var t = 0;
				var dateNow1 = '';
				dateNow = j + 1;
				if(selectYearMonth==yearMonth&&dateNow==day){
					t = 1;
				}
				if (dateNow < 10) {//补零
					dateNow = "0" + dateNow;
				}
				if(t==1){
					dateNow1 = '<span class="date-tab-day">'+dateNow+'</span>';
				}else{
					dateNow1 = dateNow;
				}
				weekNow = getWeek(selectYearMonth + "-" + dateNow);//星期几
				if (weekNow == 0 && j > 0) {
					calendarHtml += "<tr class=\"date-tab-tr\">";
				}
				//if (data == "'\u65e0'") {
				if (dateArr.length == 0) {
					if (workDay.indexOf(weekNow) > -1) {//非假日
						//calendarHtml += "<td height=\"30\" align=\"left\"><input type=\"checkbox\" name=\"chkCalendar\" class=\"checkbox-rl\" value=\"" + selectYearMonth + "-" + dateNow + "\"/>" + dateNow + "</td>";
					} else {//固定假日
						//calendarHtml += "<td height=\"30\" align=\"left\"><input type=\"checkbox\" name=\"chkCalendar\" checked=\"checked\" class=\"checkbox-rl\" value=\"" + selectYearMonth + "-" + dateNow + "\"/><span class=\"red\">" + dateNow + "</span></td>";
					}
					calendarHtml+="<td height=\"30\" align=\"center\" onclick=\"clickTd(this)\" id=\"" + selectYearMonth + "-" + dateNow + "\" value=\"" + selectYearMonth + "-" + dateNow + "\" onMouseover=\"hilite(this)\" onMouseout=\"restore(this)\">"+dateNow1+"</td>"; 
				} else {
					var index=$.inArray(selectYearMonth + "-" + dateNow, dateArr);
					if (index > -1) {//已设置的假日
						//calendarHtml += "<td height=\"30\" align=\"left\"><input type=\"checkbox\" name=\"chkCalendar\" checked=\"checked\" class=\"checkbox-rl\" value=\"" + selectYearMonth + "-" + dateNow + "\"/><span class=\"red\" style=\"color: red\" >" + dateNow + "<br />建军节</span></td>";
						calendarHtml+="<td height=\"30\" align=\"center\" onclick=\"clickTd(this)\" id=\"" + selectYearMonth + "-" + dateNow + "\" value=\"" + selectYearMonth + "-" + dateNow + "\" class=\"bgRed\" onMouseover=\"hilite(this)\" onMouseout=\"restore(this)\"><span style=\"color:red;\">"+dateNow1+"</span><br />"+dateName[index]+"</td>";
					} else {
						//calendarHtml += "<td height=\"30\" align=\"left\"><input type=\"checkbox\" name=\"chkCalendar\" class=\"checkbox-rl\" value=\"" + selectYearMonth + "-" + dateNow + "\" />" + dateNow + "</td>";
						calendarHtml+="<td height=\"30\" align=\"center\" onclick=\"clickTd(this)\" id=\"" + selectYearMonth + "-" + dateNow + "\" value=\"" + selectYearMonth + "-" + dateNow + "\" onMouseover=\"hilite(this)\" onMouseout=\"restore(this)\">"+dateNow1+"</td>";
					}
				}
				if (weekNow == 6) {
					calendarHtml += "</tr>";
				}
			}
			weekLast = getWeek(selectYearMonth + "-" + monthDay);
			for (var i = 0; i < 6 - weekLast; i++) {
				calendarHtml += "<td height=\"30\" align=\"left\">&nbsp;</td>";
			}
			if (weekLast != 6) {
				calendarHtml += "</tr>";
			}
			$("#calendarBody").html(calendarHtml);
		}});
	}
}

//清除下拉框
function clearSelect(){
	var selObj=document.getElementById("holidayMonth");
	//clear
	selObj.length=0;
//	$("#hideHolidayID").val('');
}

//设置下拉框
function appendSelect(){
	var selObj=$("#holidayMonth");
	var month=$("#yearMonth").val();
	var hideHolidayID=$("#hideHolidayID").val();
	//set
	selObj.append( "<option value=\"-\">-请选择-</option>" );
	$.ajax({
		type:"post",
		contentType:"application/json;charset=utf-8",
		url: "admin/holiday/findYearHolidayName?month="+month,
		async : false,
		dataType:'json', 
		success:function(data){
			for(var i=0;i<data.length;i++){
				if(data[i].id==hideHolidayID){
					selObj.append( "<option value=\""+data[i].id+"\" monthValue=\""+data[i].year_month1+"\" selected=\"selected\" >"+data[i].holiday_name+"</option>" );
				}else{
					selObj.append( "<option value=\""+data[i].id+"\" monthValue=\""+data[i].year_month1+"\"  >"+data[i].holiday_name+"</option>" );
				}
			}
		}
	});
}

function removeHid(){
	if($("#hideHolidayID")){
		$("#hideHolidayID").val("");
	}
}
