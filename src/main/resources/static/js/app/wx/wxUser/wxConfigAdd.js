var $addConfigForm = $("#wxConfig-add-form");
$(function () {
	validateRule();
	$("#wxConfig-add .btn-save").click(function () {
		var name = $(this).attr("name");
		var validator = $addConfigForm.validate();
		var flag = validator.form();
		if (flag) {
			if (name === "save") {
				$.post(ctx + "wxUser/config", $addConfigForm.serialize(), function (r) {
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

