$(function () {
    var $TableForm = $(".wxUser-table-form");
    var settings = {
        url: ctx + "wxUser/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                title: $TableForm.find("input[name='title']").val().trim(),
                wxName: $TableForm.find("input[name='wxName']").val(),
                channelPerson: $TableForm.find("input[name='channelPerson']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'id',
            visible: false
        }, {
            field: 'title',
            title: '页面标题'
        }, {
            field: 'wxName',
            title: '公众号名称'
        }, {
            field: 'contactName',
            title: '联系人名字'
        }, {
            field: 'contactPhone',
            title: '联系人手机号'
        }, {
            field: 'kfPhone',
            title: '客服手机号',
        }, {
            field: 'kfWx',
            title: '客服微信号',
        }, {
            field: 'kfWxEwm',
            title: '客服微信号二维码',
        }, {
            field: 'billName',
            title: '充值平台用户名',
        }, {
            field: 'createTime',
            title: '创建时间',
        }, {
            field: 'createUser',
            title: '创建人',
        }, {
            field: 'channelPerson',
            title: '归属渠道',
        }

        ]
    };

    $MB.initTable('wxUserTable', settings);
});

function search() {
    $MB.refreshTable('wxUserTable');
}

function refresh() {
    $(".wxUser-table-form")[0].reset();
    $MB.refreshTable('wxUserTable');
}

function deleteUsers() {
    var selected = $("#wxUserTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的用户！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].id;
        if (i !== (selected_length - 1)) ids += ",";
    }

    $MB.confirm({
        text: "确定删除选中数据？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'wxUser/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportUserExcel() {
    $.post(ctx + "user/excel", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".user-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}