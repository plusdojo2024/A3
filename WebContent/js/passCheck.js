
/* HTML要素をオブジェクトとして取得する */
let formObj = document.getElementById('regist_form');
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
	const family_pass_value_checked_result = password_regex.test(formObj.family_pass.value);
	const user_pass_value_checked_result = password_regex.test(formObj.user_pass.value);
	if (!formObj.family_name.value || !formObj.family_pass.value || !formObj.user_name.value || !formObj.user_pass.value) {
		errorMessageObj.textContent = '※全ての項目を入力してください。';
		return false;
	} else if(!family_pass_value_checked_result || !user_pass_value_checked_result){
		errorMessageObj.textContent = '※パスワードが要件を満たしていません。';
		return false;
	}else if(!formObj.mail.value.match(/[a-zA-Z0-9]+[a-zA-Z0-9\._-]*@[a-zA-Z0-9_-]+[a-zA-Z0-9\._-]+/)){
		errorMessageObj.textContent = 'メールアドレスの形式が不正です。';
		return false;
	}
	if (!window.confirm('この情報で登録します。よろしいですか？')) {
		return false;
	}
    errorMessageObj.textContent = null;
};

//画像チェック icon
let errorMessageObj = document.getElementById('error_icon');
let errorMessageObj = document.getElementById('icon');













