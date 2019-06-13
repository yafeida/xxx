$(function () {
	var $userTableForm = $(".user-table-form");
	var settings = {
	        url: ctx + "order/list",
	        pageSize: 10,
	        queryParams: function (params) {
	            return {
	                pageSize: params.limit,
	                pageNum: params.offset / params.limit + 1,
	                orderId: $userTableForm.find("input[name='orderNumber']").val(),
	                phone: $userTableForm.find("input[name='phone']").val(),
	                agent: $userTableForm.find("input[name='agent']").val(),
	                createTime: $userTableForm.find("input[name='createTime']").val(),
	                endTime: $userTableForm.find("input[name='endTime']").val(),
	                czStatus: $userTableForm.find("select[name='status']").val()
	            };
	        },
	        columns: [{
	            checkbox: true
	        }, {
	            field: 'id',
	            visible: false
	        }, {
	            field: 'orderId',
	            title: '平台订单号'
	        }, {
	            field: 'upOrderId',
	            title: '微信流水号'
	        }, {
	            field: 'phone',
	            title: '手机号'
	        }, {
	            field: 'fee',
	            title: '充值金额'
	        }, {
	            field: 'disprice',
	            title: '折扣'
	        }, {
	            field: 'wxName',
	            title: '代理商名称',
	        },{
	            field: 'agent',
	            title: '代理商标识',
	        },{
	            field: 'zfTime',
	            title: '充值时间'
	        },{
	            field: 'zfStatus',
	            title: '是否支付',
	            formatter: function (value, row, index) {
	                if (value == '3') return '是';
	                else  return '否';
	            }
	        }, {
	            field: 'zfMsg',
	            title: '支付状态'
	        },{
	            field: 'czStatus',
	            title: '充值状态',
	            formatter: function (value, row, index) {
	                if (value == '0') return '充值中';
	                else if (value == '1') return '提交成功';
	                else if (value == '2') return '提交失败';
	                else if (value == '3') return '充值成功';
	                else if (value == '4') return '充值失败';
	            }
	        }]
	    };
	$MB.initTable('orderTable', settings);
    $MB.calenders('input[name="createTime"]', false, true);
    $MB.calenders('input[name="endTime"]', false, true);
});



//设置时间的显示格式
function dateFtt(fmt,date)   
{ //author: meizz   
  var o = {   
    "M+" : date.getMonth()+1,                 //月份   
    "d+" : date.getDate(),                    //日   
    "h+" : date.getHours(),                   //小时   
    "m+" : date.getMinutes(),                 //分   
    "s+" : date.getSeconds(),                 //秒   
    "q+" : Math.floor((date.getMonth()+3)/3), //季度   
    "S"  : date.getMilliseconds()             //毫秒   
  };   
  if(/(y+)/.test(fmt))   
    fmt=fmt.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));   
  for(var k in o)   
    if(new RegExp("("+ k +")").test(fmt))   
  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  return fmt;   
}

//根据条件查询
function search() {
    $MB.refreshTable('orderTable');
}

//刷新搜索框中内容
function refresh() {
    $(".user-table-form")[0].reset();
    $MB.refreshTable('orderTable');
}

//导出excel表格
function exportOrderExcel() {
	var createTime = $("#createTime").val();
    var endTime = $("#endTime").val();
    $.post(ctx + "order/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

//导出Csv表格
function exportOrderCsv() {
    $.post(ctx + "order/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}