let formObj = document.getElementById('form');

let errorMessageObj = document.getElementById('error_message');

formObj.onsubmit = function() {
	if (!formObj.task.value.trim() || formObj.category.value=="") {
		errorMessageObj.textContent = '※全ての項目を入力してください。';
		return false;
	}else if(!formObj.task.value.match(/&gt|&lt|<|>|;|:|"|'|/)){
		errorMessageObj.textContent = '※不正な値が入力されています。';
		return false;
	} else {
		var checked = confirm("登録しますか？");
		if (checked == true) {
			return true;
		} else {
			return false;
		}
	}
}