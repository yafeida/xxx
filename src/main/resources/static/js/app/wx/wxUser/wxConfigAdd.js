var validator;
var $addForm = $("#wxConfig-add-form");

$(function () {
	validateRule();
	$("#wxConfig-add .btn-save").click(function () {
		var name = $(this).attr("name");
		var validator = $addForm.validate();
		var flag = validator.form();
		if (flag) {
			if (name === "save") {
				$.post(ctx + "wxUser/config", $addForm.serialize(), function (r) {
					if (r.code === 0) {
						closeModal();
						$MB.n_success(r.msg);
						$MB.refreshTable("wxUserTable");
					} else $MB.n_danger(r.msg);
				});
			}
		}
	});

	$("#wxConfig-add .btn-close").click(function () {
		closeModal();
	});

});

function configUsers(){
	var selected = $("#wxUserTable").bootstrapTable('getSelections');
	var selected_length = selected.length;
	if (!selected_length) {
		$MB.n_warning('请勾选需要配置的用户！');
		return;
	}
	if (selected_length > 1) {
		$MB.n_warning('一次只能配置一个用户！');
		return;
	}
	var id = selected[0].id;
	var $form = $('#wxConfig-add');
	$form.modal();
	$form.find("input[name='Id']").val(id).attr("readonly", true);
}

function closeModal() {
	$("#wxConfig-add-button").attr("name", "save");
	validator.resetForm();
	$("#wxConfig-add-modal-title").html('新增客户');
	$MB.closeAndRestModal("wxConfig-add");
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
