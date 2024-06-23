const img_one = document.getElementById("photo");
const img_two = document.getElementById("photo_two");
const icon = document.getElementById("icon");
const form = document.getElementById('form');
let errorMessageObj = document.getElementById('error_message');
let errorMessageObj_two = document.getElementById('error_message_two');

if (img_one != null) {
	img_one.onchange = (e) =>{
		if (img_one.files.length > 0){
		/// ファイルサイズ取得
			var fileSize = img_one.files[0].size;

			var fileMib = fileSize / 1024 ** 2;
			if (fileMib >= 5) {
				errorMessageObj.textContent = 'ファイルサイズは5MB以内にしてください。';
			}
		}
	}

formObj.onsubmit = function() {
	if (img_one.files.length > 0) {
		/// ファイルサイズ取得
		var fileSize = img_one.files[0].size;

		var fileMib = fileSize / 1024 ** 2;
		if (fileMib >= 5) {
			errorMessageObj.textContent = 'ファイルサイズは5MB以内にしてください。';
			return false;
		}
	}
	if (!window.confirm('この情報で登録します。よろしいですか？')) {
		return false;
	}
	errorMessageObj.textContent = null;
};
};


if (img_two !=  null){
	img_two.onchange = (e) => {
		if (img_two.files.length > 0) {
			/// ファイルサイズ取得
			var fileSize = img_two.files[0].size;

			var fileMib = fileSize / 1024 ** 2;
			if (fileMib >= 5) {
				errorMessageObj_two.textContent = 'ファイルサイズは5MB以内にしてください。';
			}
		}
	}
	formObj.onsubmit = function() {
		if (icon.files.length > 0) {
			/// ファイルサイズ取得
			var fileSize = icon.files[0].size;

			var fileMib = fileSize / 1024 ** 2;
			if (fileMib >= 5) {
				errorMessageObj.textContent = 'ファイルサイズは5MB以内にしてください。';
				return false;
			}
		}
		if (!window.confirm('この情報で登録します。よろしいですか？')) {
			return false;
		}
		errorMessageObj.textContent = null;
	};
};
if (icon != null) {
	icon.onchange = (e) => {
		if (icon.files.length > 0) {
			/// ファイルサイズ取得
			var fileSize = icon.files[0].size;

			var fileMib = fileSize / 1024 ** 2;
			if (fileMib >= 5) {
				errorMessageObj.textContent = 'ファイルサイズは5MB以内にしてください。';
			}
		}
	}
	formObj.onsubmit = function() {
		if (icon.files.length > 0) {
			/// ファイルサイズ取得
			var fileSize = icon.files[0].size;

			var fileMib = fileSize / 1024 ** 2;
			if (fileMib >= 5) {
				errorMessageObj.textContent = 'ファイルサイズは5MB以内にしてください。';
				return false;
			}
		}
		if (!window.confirm('この情報で登録します。よろしいですか？')) {
			return false;
		}
		errorMessageObj.textContent = null;
	};
};

