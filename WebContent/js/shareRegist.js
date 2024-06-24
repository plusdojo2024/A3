let formObj = document.getElementById("form");
let errorMessageObj = document.getElementById('error_message');
formObj.onsubmit = function() {

    if(formObj.loop=="1"){
if(!formObj.week[0] && !formObj.week[1] && !formObj.week[2] && !formObj.week[3]
        && !formObj.week[4] && !formObj.week[5] && !formObj.week[6]){
            errorMessageObj.textContent = 'ループ選択時は曜日を選択してください';
		    return false;
        }else if(!formObj.end_date){
            errorMessageObj.textContent = 'ループ選択時は終了日を選択してください。';
		    return false;
        }
    } 
}