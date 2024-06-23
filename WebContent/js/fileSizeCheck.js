const img_one = document.getElementById("photo");
const img_two = document.getElementById("photo_two");
const icon = document.getElementById("icon");
const formObj = document.getElementById('form');
let errorMessageObj = document.getElementById('error_message');
let errorMessageObj_two = document.getElementById('error_message_two');

let image_flag=0;
let image_flagB=0;
let image_flagC=0;

if (img_one != null) {
	img_one.onchange = (e) => {
		if (img_one.files.length > 0) {
			/// ファイルサイズ取得
			var fileSize = img_one.files[0].size;

			var fileMib = fileSize / (1024 ** 2);
			if (fileMib >= 5) {
				errorMessageObj.textContent = 'ファイルサイズは5MB以内にしてください。';
				image_flagA=1;
			}else{
				errorMessageObj.textContent = null;
				image_flagA=0;
			}
		};
	}

}


if (img_two != null) {
	img_two.onchange = (e) => {
		if (img_two.files.length > 0) {
			/// ファイルサイズ取得
			var fileSize = img_two.files[0].size;

			var fileMib = fileSize / 1024 ** 2;
			if (fileMib >= 5) {
				errorMessageObj_two.textContent = 'ファイルサイズは5MB以内にしてください。';
				image_flagB=1;
			}else{
				errorMessageObj.textContent = null;
				image_flagB=0;
			}
		};
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
				image_flagC=1;
			}else{
				errorMessageObj.textContent = null;
				image_flagC=0;
			}
		};
	};

};
let submit_error = document.getElementById('submit_error');
/* [ログイン]ボタンをクリックしたときの処理 */
formObj.onsubmit = function () {
    if (image_flagA==1 || image_flagB==1 || image_flagC==1) {
        submit_error.textContent = 'ファイルサイズは5MB以内にしてください。';
        return false;
    }
    submit_error.textContent = null;
};

