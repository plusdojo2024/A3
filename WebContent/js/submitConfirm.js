let formObj = document.getElementById('form');

formObj.onsubmit = function() {
	if (!window.confirm('本当によろしいですか？')) {
		return false;
	}
}