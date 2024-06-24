<!--個人情報変更-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>個人情報変更画面</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/accountEdit.css">
<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${dbUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${dbUser.havePoint}" />
		</div>
	</div>
	<div class="home_logo">
		<a href="/A3/HomeServlet"><img src="images/FM1.png" width="90%" height="90%"></a>
	</div>
    <div class="right_buttons">
        <button class="account-management"
            onclick="window.location.href = '/A3/AccountServlet';">アカウント管理<br><img src="images/ic007.png" width="65px" height="65px"></button>
        <button class="logout"
            onclick="window.location.href = '/A3/LogoutServlet';">ログアウト<br><img src="images/935.png" width="65px" height="65px"></button>
    </div>
</div>
</head>
<body>
<div class="body">
	<br><br><br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
	<h2 class="box">個人情報変更</h2>
	<form id="form" method="post" action="/A3/AccountEditServlet"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><c:set var="name" value="${dbUser.name}">
					</c:set> <label><div class="userName">個人名：</div> <input type="text" name="user_name"
						value="${name}" maxlength=20 id="user_name" style="width:300px; height:30px">
				</label></td>
			<tr>
			<tr>
				<td><label><div class="pass">個人パスワード：</div> <input type="password" placeholder="password"
						name="user_pass" id="pass" maxlength=20 style="width:300px; height:30px"><span class="pass_warn"><br><div class="text1">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください</div><br></span>
				</label></td>
			<tr>
			<tr>
				<td><label><div class="pass_check">個人パスワード確認：</div><input type="password" placeholder="password"
						id="pass_check" name="pass_check" maxlength="20" style="width:300px; height:30px"><span class="pass_warn"><br><div class="text2">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください</div><br></span>
				</label></td>
			<tr>
			<tr>
				<td><label><div  class="icon">アイコン画像：</div> <input type="file" accept="image/*"
						name="icon" style="width:300px; height:30px" id="icon">
				</label></td>
			</tr>
			<tr>
				<c:set var="color" value="${dbUser.color}"></c:set>
				<td><label><div class="color">個人カラー：</div> <input type="color"
						name="color" value="${color}" style="height:30px" id="color">
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" id = "submit" value="変更"  class="custom-submit"> <span
					id="error_message" style="right:24%;"> </span>
					<p class="error">
						<c:out value="${message.title}" />
						<c:out value="${message.message}" />
					</p></td>
			</tr>
		</table>
	</form>
	<script src="js/submitConfirm.js"></script>
	<script src="js/accountEdit.js"></script>
	</div>
	<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
</body>
</html>