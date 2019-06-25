var amount = null;	//充值金额
var discount = null;	//折扣后金额
$(function() {
	/*禁用提交充值按钮*/
//	$("#submitProm").attr("disabled",true).css("pointer-events","none"); 
	/*设置顶部导航栏选中后的颜色*/
	$('#t3').tab({
		defaultIndex : 0,
		activeClass : 'tab-blue'
	});
	
	/*给选中的面值添加样式，清除上一个选中金额的样式*/
	$(".weui-payselect-ul").on('click','.weui-payselect-li',function() {
		$(this).children().addClass("weui-payselect-on");
		$(this).siblings().children().removeClass("weui-payselect-on");
		return false;
	});
})

/*
 * 手机号信息查询
 */
function checkPhoneNumber(phone){
	// 130~9  145~9  150~3&155~9  165&166    170~178   180~189   191&198&199 
	var reg = /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/;	
	if(reg.test(phone)){
		if(phone.length == 11){
			$.ajax({
				type:"post",
				url:'../../getPhoneInfo',
				data:'phone='+phone,
				dataType:"json",
				success: function(data){
					if (data.msg == "sucess") {
						$(".yunyingshang").text(data.province+" "+data.mobiletype);
						var bill = data.bill;
						if (bill.length != 0) {
							$("#package").hide();
							$("#package2").show();
							addFeeList(bill);
						}
					}else{
						$(".yunyingshang").text("暂不支持当前号段");
					}
				}
			});
		}
	}else if(phone.length == 0){
		$("#package").show();
		$("#package2").hide();
		$(".amount").hide();
		$(".yunyingshang").text("移动/联通/电信/虚商");
	}
}

//加载话费列表
function addFeeList(arr) {
	var flag = false;
	var v = null;
	var html = '';
	arr.forEach(function(value) {
		if(value.pkgName != '任意充'){		//先组合不等于任意充的，最后在判断是否有任意充，有则添加反之则不添加 ，保证将任意充放在最后的位置
			html = html
			+'<li class="weui-payselect-li"><a class="weui-payselect-a2" id="fee" onclick="changeBillPkgExt(this);" '
			+'data-price="'+value.price+'" data-pkgName="'+value.pkgName+'"  data-discount="'+value.discount+'" >'
			+'<span>'+value.pkgName+'</span><br/><span class="weui-pay-12">售价'+value.discount+'元</span></a></li>'
		}
		if(value.pkgName == '任意充'){flag = true;v = value}
	});
	if(flag){
		html = html
		+'<li class="weui-payselect-li"><a class="weui-payselect-a" id="fee" onclick="changeBillPkgExt(this);" '
		+'data-price="'+v.price+'" data-pkgName="'+v.pkgName+'"  data-discount="'+v.discount+'" ><span>其他金额</span></a></li>'
	}
	$('#package2-fee').html(html);
};

//显示任意充金额输入框
function changeBillPkgExt(obj) {
	/*隐藏任意金额输入框*/
	$(".amount").hide();
	/*清空任意金额输入框*/
	$("#anyAmount").val("");
	/*任意金额输入框添加提示语句*/
	$(".discount").html("请输入充值金额");	
	/*取消禁用提交充值按钮*/
	$("#submitProm").attr("disabled",true).css("pointer-events","auto"); 
	/*获取当前选中话费包的名称*/
	var price= $(obj).attr("data-pkgName");
	amount = $(obj).attr("data-price");
	discount = $(obj).attr("data-discount");
	if(price == "任意充"){
		amount = price;
		//当折扣不等于空，则代表手机号输入无误，此时显示出input标签
		$(".amount").show();
		$("#submitProm").attr("disabled",true).css("pointer-events","none"); 
	}
}

//对输入的金额进行处理
function changeFee(){
	var anyAmount = $("#anyAmount").val();
	if(anyAmount <=1000 && anyAmount>=0.01 ){	//任意充值金额范围为0.01~1000之间
		$("#submitProm").attr("disabled",true).css("pointer-events","auto"); 
		$(".discount").html("售价"+(anyAmount*discount).toFixed(2)+"元");
	}else if(anyAmount == ''){
		$(".discount").html("请输入充值金额");
		$("#submitProm").attr("disabled",true).css("pointer-events","none");
	}else{
		$(".discount").html("未查询到折扣");
		$("#submitProm").attr("disabled",true).css("pointer-events","none");
	}
};

//提交充值
//提交时需要添加一个open_id
function submitForm(){
	alert(1);
	//页面显示支付等待
	$.showLoading("正在发起支付请求");
	var phone = $("#phone").val();
	if (phone.length != 11) {
		$.hideLoading();
		alert("号码错误，请核对后重新输入");
		return false;
	}
	//amount定义为全局，每次点击充值金额后都会对他进行赋值
	if(amount == null){	
		$.hideLoading();
		alert("亲，您还没选择充值内容，选一个吧");
		return false;
	}
	//为任意充时，discount为充值折扣
	if(amount == '任意充'){
		amount = $("#anyAmount").val();
		discount = (amount*discount).toFixed(2);
	}
	console.log("折扣价："+discount+"    "+"金额:"+amount+"    "+"手机号："+phone);
	$.ajax({
		type:"post",
		url:'../../pay',
		data:{
			userid:$("#userid").val(),
			agent:$("#agent").val(),
			phone:phone,
			fee:amount,
			disprice:discount,
			openId:$("#openId").val()
		},
		dataType:"json",
		success: function(data){
			$.hideLoading();
			alert(data);
			if(data.code == 0){
				alert("xx");
			}else{
				$.hideLoading();
				alert("亲，支付失败，请关闭页面后重新打开。");
				return false;
			}
		}
	});
}


