window.onload = function() {
	let parent_button = document.getElementById('parent');
	let parent_image = document.getElementById('parent_image');
	let child_button = document.getElementById("child");
	let child_image = document.getElementById('child_image');
	let role = document.getElementById("role");
	let image_flag = 0;

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


	parent_button.addEventListener("click", () => {
		role.value = "1";
		//alert("ロール：" + role.value);//デバッグ用
		child_button.style.backgroundColor = '#ffffff';
		child_image.style.backgroundColor = '#ffffff';
		parent_button.style.backgroundColor = '#008000';
		parent_image.style.backgroundColor = '#008000';
	});
	child_button.addEventListener("click", () => {
		role.value = "0";
		//alert("ロール：" + role.value);//デバッグ用
		parent_button.style.backgroundColor = '#ffffff';
		parent_image.style.backgroundColor = '#ffffff';
		child_button.style.backgroundColor = '#008000';
		child_image.style.backgroundColor = '#008000';
	});
	/* HTML要素をオブジェクトとして取得する */
	let formObj = document.getElementById('form');
	let errorMessageObj = document.getElementById('error_message');

	/* [新規作成]ボタンをクリックしたときの処理 */
	formObj.onsubmit = function() {
		/*
	  正規表現条件
	  ・半角数字、半角英字のみ
	  ・半角数字、半角英字がそれぞれ一文字以上使用されている
	  ・文字数が8~20字以内
	  */
		const password_regex = /^(?=.*[0-9])(?=.*[a-zA-Z])[0-9a-zA-Z]{8,20}$/;
		const name_regex = /^[0-9a-zA-Z]{8,20}$/;
		const user_pass_value_checked_result = password_regex.test(formObj.user_pass.value);
		if (!formObj.user_name.value || !formObj.user_pass.value) {
			errorMessageObj.textContent = '※全ての項目を入力してください。';
			return false;
		} else if (!user_pass_value_checked_result) {
			errorMessageObj.textContent = '※パスワードが要件を満たしていません。';
			return false;
		} else if (image_flag == 1) {
			errorMessageObj.textContent = 'ファイルサイズは5MB以内にしてください。';
			return false;
		}
		else {
			if (!window.confirm('この情報で登録します。よろしいですか？')) {
				return false;
			}
			errorMessageObj.textContent = null;
		}

	};
}
