function Check() {
	var checked = confirm("本当に削除しますか？");
	if (checked == true) {
		return true;
	} else {
		return false;
	}
}

function Rcheck() {
	var checked = confirm("登録しますか？");
	if (checked == true) {
		return true;
	} else {
		return false;
	}
}

function Echeck() {
	var checked = confirm("更新しますか？");
	if (checked == true) {
		return true;
	} else {
		return false;
	}
}