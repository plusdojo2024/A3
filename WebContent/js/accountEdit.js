let formObj = document.getElementById('form');
let errorMessageObj = document.getElementById('error_message');


const icon = document.getElementById("icon");


icon.onchange = (e) => {
	if (icon.files.length > 0) {
		// ファイルサイズ取得
		var fileSize = icon.files[0].size;

		var fileMib = fileSize / (1024 ** 2);
		if (fileMib >= 5) {
			errorMessageObj.textContent = 'ファイルサイズは5MB以内にしてください。';
			image_flag = 1;
		} else {
			errorMessageObj.textContent = null;
			image_flag = 0;
		}
	};
}

formObj.onsubmit = function () {
	/*
  正規表現条件
  ・半角数字、半角英字のみ
  ・半角数字、半角英字がそれぞれ一文字以上使用されている
  ・文字数が8~20字以内
  */
	const password_regex = /^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[A-Z])[0-9a-zA-Z]{8,20}$/;
	const user_pass_value_checked_result = password_regex.test(formObj.user_pass.value);
	if (!formObj.user_name.value.trim()) {
		errorMessageObj.textContent = '※全ての項目を入力してください。';
		return false;
	} else if (formObj.user_pass.value && !user_pass_value_checked_result) {
		errorMessageObj.textContent = '※パスワードが要件を満たしていません。';
		return false;
	} else if (formObj.user_pass.value && formObj.user_pass.value != formObj.pass_check.value) {
		errorMessageObj.textContent = '※確認用パスワードが一致しません。';
		return false;
	} else if (!formObj.user_name.value.match(/^[A-Za-z0-9]*$/)) {
		errorMessageObj.textContent = '名前は半角英数字にしてください。';
		return false;
	}
	else {
		if (!window.confirm('この情報で登録します。よろしいですか？')) {
			return false;
		}
		errorMessageObj.textContent = null;
	}

};
