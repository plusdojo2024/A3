<!--家事分担編集-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>Green Rounded Square</title>
    <link rel="stylesheet" href="/A3/WebContent/WEB-INF/jsp/css/main.css">
	<link rel="stylesheet" href="/A3/WebContent/WEB-INF/jsp/css/shareRegist.css">

    <div class="nav">
        <div class="left_icons">
            <div class="green_box">父</div>
            <div class="green_box">ポイント</div>
        </div>

        <div class="home_logo">
            <a href="home.html">F&M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management"><a href="">アカウント管理</a></button>
            <button class="logout"><a href="">ログアウト</a></button>
        </div>

    </div>
</head>
<body>
    <br>
    <br>
    <br>
    <br>

    <div class= "title_box">分担編集・削除</div>

    <div class= "main_box"><input type="text" name="task" size="20" maxlength="20" value="項目"></div>

    <div class= "icon_box">担当者</div>

    <div class= "main_box">繰り返し<select name="repeat" value="繰り返し">
        <option>繰り返さない</option>
        <option>繰り返す</option>
        </select></div>

    <div class= "main_box">曜日<select name="day" value="曜日">
        <option>今日だけ</option>
        <option>月曜日</option>
        <option>火曜日</option>
        <option>水曜日</option>
        <option>木曜日</option>
        <option>金曜日</option>
        <option>土曜日</option>
        <option>日曜日</option>
    </select></div>

    <div class= "main_box">いつまで<input type="date"></div>

    <div class= "submit_box"><input type="submit" value="更新"></div>
    <div class= "delete_box"><input type="submit" value="削除"></div>

</body>
</html>