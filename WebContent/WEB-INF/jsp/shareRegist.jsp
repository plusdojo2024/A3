<!--家事分担登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>家事分担登録</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/shareRegist.css">

    <div class="nav">
        <div class="left_icons">
        <c:set var="icon" value="${myUser.icon}" />
            <div class="green_box"><img src="${icon}" id="user_icon"></div>
            <div class="green_box"><c:out value="${myUser.havePoint}" /></div>
        </div>

        <div class="home_logo">
            <a href="/A3/HomeServlet">F&M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management"><a href="/A3/AccountServlet">アカウント管理</a></button>
            <button class="logout"><a href="/A3/LogoutServlet">ログアウト</a></button>
        </div>

    </div>
</head>
<body>
	<form action="ShareRegistServlet" method="post"></form>
    <br>
    <br>
    <br>
    <br>

    <div class= "title_box">分担登録</div>

    <div class= "main_box"><input type="text" name="task" size="20" maxlength="20" value="項目"></div>

    <div >
    	<label for="uid">担当者</label>
    	<input type="" id="uid" name="uid" required>
    </div>

    <div class= "main_box">繰り返し<select name="repeat" value="繰り返し"  onchange="enableNextDropdown('day')">
        <option>繰り返さない</option>
        <option>繰り返す</option>
        </select></div>

    <div class= "main_box" >曜日<select id="day" name="day" value="曜日" disabled>
    	<option value="1">選択してください</option>
        <option value="2">月曜日</option>
        <option value="3">火曜日</option>
        <option value="4">水曜日</option>
        <option value="5">木曜日</option>
        <option value="6">金曜日</option>
        <option value="7">土曜日</option>
        <option value="8">日曜日</option>
    </select></div>

    <div class= "main_box">いつまで<input type="date"></div>

    <div class= "submit_box"><input type="submit" value="登録" name="bu" id="ken" disabled></div>

	<script>
        function enableNextDropdown(nextDropdownId) {
            var nextDropdown = document.getElementById(nextDropdownId);
            nextDropdown.disabled = false;
        }
    </script>
</body>
</html>
