var validator;
var $addForm = $("#wxUser-add-form");

$(function () {
    validateRule();
    
    $("#wxUser-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $addForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "wxUser/add", $addForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("wxUserTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
            	$.post(ctx + "wxUser/update", $addForm.serialize(), function (r) {
            		if (r.code === 0) {
            			closeModal();
            			$MB.n_success(r.msg);
            			$MB.refreshTable("wxUserTable");
            		} else $MB.n_danger(r.msg);
            	});
            }
        }
    });

    $("#wxUser-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#wxUser-add-button").attr("name", "save");
    validator.resetForm();
    $("#wxUser-add-modal-title").html('新增客户');
    $MB.closeAndRestModal("wxUser-add");
}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $addForm.validate({
        rules: {
        	title: {
                required: true,
                minlength: 1,
                maxlength: 10,
            },
            wxName: {
                required: true,
                minlength: 1,
                maxlength: 10,
            },
            contactName:{
            	required: true,
            },
            contactPhone:{
            	checkPhone: true
            },
            kfPhone: {
            	checkPhone: true
            },
            kfWx: {
                required: true
            },
            kfWxEwm: {
            	required: true
            },
            billName: {
                required: true
            },
            billKey: {
            	required: true
            },
            channelPerson: {
            	required: true
            }
        },
        errorPlacement: function (error, element) {
            if (element.is(":checkbox") || element.is(":radio")) {
                error.appendTo(element.parent().parent());
            } else {
                error.insertAfter(element);
            }
        },
        messages: {
        }
    });
}

