function Check() {
	var checked = confirm("削除します");
	if (checked == true) {
		return true;
	} else {
		return false;
	}
}