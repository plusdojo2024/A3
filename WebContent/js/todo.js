function Check() {
	var checked = confirm("本当に削除しますか？");
	if (checked == true) {
		return true;
	} else {
		return false;
	}
}
let errorObj = document.getElementById('error_message');
let taskObj = document.getElementById('task');
function Rcheck() {
	if(!taskObj.value.trim() || formObj.category.value==""){
		errorObj.textContent = '※全ての値を入力してください。';
		return false;}
		else{
		var checked = confirm("登録しますか？");
		if (checked == true) {
			return true;
		} else {
			return false;
		}
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