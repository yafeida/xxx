//订单数据
var datas;
var startDate;
var endDate;
// 初始化监听事件
$(document).ready(function() {
	addDate();
	addListener();
});

$(function(){
	//初始化下一页
	$('#order-page').on('click', '#nextPage', function() {
		if (datas.pageNumber == datas.totalPage) {
			alert("当前已经是最后一页");
		} else {
			loadData(startDate, endDate, datas.pageNumber + 1, datas.pageSize);
		}
	});
	
	//初始化上一页
	$('#order-page').on('click', '#upPage', function() {
		if (datas.pageNumber == 1) {
			alert("当前已经是第一页");
		} else {
			loadData(startDate, endDate, datas.pageNumber - 1, datas.pageSize);
		}

	});
});

//根据openid查询指定时间段的订单信息
function addListener(){
	startDate = $("#startDate").val();
	endDate = $("#endDate").val();
	if (startDate == '') {
		alert("亲，开始日期不能为空~!");
		return;
	}

	if (endDate == '') {
		alert("亲，结束日期不能为空~!");
		return;
	}
	if (compareDate(startDate, endDate)) {
		loadData(startDate, endDate, 1,8);
	} else {
		alert("开始时间不能大于结束时间！");
		$('#order-table').html('');
		$('#order-page').html('');
	}
	
}

function loadData(startDate, endDate, pageNumber, pageSize) {
	var openId = "wxopenid";
	$.ajax({
		type:"post",
		url:'order/myOrderList',
		data:'openId='+openId+'&startDate=' + startDate + '&endDate=' + endDate + '&pageNumber=' + pageNumber + '&pageSize=' + pageSize,
		dataType:"json",
		success : function(data) {
			addFeeList(data);
			datas = data;
			console.log("ajax success:" + data + "\r\n");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("亲，遇到网络问题请重试~!");
			console.log("sorry error\r\n");
		}
	});
	
}

//加载订单列表
function addFeeList(data) {
	var html = '<table class="myOrderTable"  border="1" cellspacing="1" cellpadding="1"><thead><tr><th><b>手机号码</b></th><th><b>金额</b></th><th><b>优惠价</b></th><th><b>状态</b></th><th><b>日期</b></th></tr></thead><tbody>';
		data.myOrder.forEach(function(value) {
				html = html
					+ '<tr><td>' + value.phone + '</td><td>' + value.fee + '元</td><td>' + (value.fee*value.discount) + '元</td><td>' + value.status + '</td><td>' + value.createTime + '</td></tr>';
		});
	html = html + '</tbody></table>';
	$('#order-table').html(html);
	if (data.totalPage != 0 && data.totalPage != 1) {
		var pageHtml = '<table class=""><thead><tr><th><a id="upPage"  href="javascript:">上一页</a></th>	<th><a href="##">第' + data.pageNumber + '页/共' + data.totalPage + '页   总记录 '+data.totalRow+'条</a></th><th><a id="nextPage" href="javascript:">下一页</a></th></tr></thead></table>';
		$('#order-page').html(pageHtml);
	} else {
		$('#order-page').html("");
	}
}


//判断开始时间是否大于结束时间
function compareDate(beginDate, endDate) {
	var d1 = new Date(beginDate.replace(/\-/g, "\/"));
	var d2 = new Date(endDate.replace(/\-/g, "\/"));
	if (d1 == d2) {
		return true;
	}
	if (beginDate != "" && endDate != "" && d1 > d2) {
		return false;
	}
	return true;
}

//input标签显示当天时间
function addDate(){
	var now = new Date();
    //格式化日，如果小于9，前面补0
    var day = ("0" + now.getDate()).slice(-2);
    var endDay = ("0" + (now.getDate()+1)).slice(-2);
    //格式化月，如果小于9，前面补0
    var month = ("0" + (now.getMonth() + 1)).slice(-2);
    //拼装完整日期格式
    var startDate = now.getFullYear()+"-"+(month)+"-"+(day) ;
    var endDate = now.getFullYear()+"-"+(month)+"-"+(endDay) ;
    $('#startDate').val(startDate);
    $('#endDate').val(endDate);
    
    var date = new Date();
}
