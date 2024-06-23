let form = document.getElementById("form");
let submit_error = document.getElementById('submit_error');
form.onsubmit = function() {
    if(!form.memo_title){
        submit_error.textContent = 'タイトルは必須項目です。';
        return false;
    }
    submit_error.textContent = null;
}