let formObj = document.getElementById('form');

formObj.onsubmit = function() {
	if (!window.confirm('この情報で登録します。よろしいですか？')) {
		return false;
	}
}