window.onload = function() {
	let parent_button = document.getElementById('parent');
	let parent_image = document.getElementById('parent_image');
	let child_button = document.getElementById("child");
	let child_image = document.getElementById('child_image');
	let role = document.getElementById("role");

	parent_button.addEventListener("click", () => {
		role.value = "1";
		alert("ロール："+role.value);//デバッグ用
		child_button.style.backgroundColor = '#ffffff';
		child_image.style.backgroundColor = '#ffffff';
		parent_button.style.backgroundColor = '#008000';
		parent_image.style.backgroundColor = '#008000';
	});
	child_button.addEventListener("click", () => {
		role.value = "0";
		alert("ロール："+role.value);//デバッグ用
		parent_button.style.backgroundColor = '#ffffff';
		parent_image.style.backgroundColor = '#ffffff';
		child_button.style.backgroundColor = '#008000';
		child_image.style.backgroundColor = '#008000';
	});
}