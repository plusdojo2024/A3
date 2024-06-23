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
		<c:set var="icon" value="${myUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${myUser.havePoint}" />
		</div>
	</div>

	<div class="home_logo">
		<a href="home.png"></a> <a href="/A3/HomeServlet">F&M</a>
	</div>

	<div class="right_buttons">
		<button class="account-management"
			onclick="window.location.href = '/A3/AccountServlet';">アカウント管理</button>
		<button class="logout"
			onclick="window.location.href = '/A3/LogoutServlet';">ログアウト</button>
	</div>

</div>
</head>
<body>
	<br>
	<br>
	<br>
	<h2>個人情報変更</h2>

	<form id="form" method="post" action="/A3/AccountEditServlet"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><c:set var="name" value="${myUser.name}">
					</c:set> <label>個人名<br> <input type="text" name="user_name"
						value="${name}" maxlength=20>
				</label></td>
			<tr>
			<tr>
				<td><label>個人パスワード<br> <input type="password"
						name="user_pass" id="pass" maxlength=20><span class="pass_warn">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください<br></span>
				</label></td>
			<tr>
			<tr>
				<td><label>個人パスワード確認<br> <input type="password"
						id="pass_check" maxlength=20><span class="pass_warn">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください<br></span>
				</label></td>
			<tr>
			<tr>
				<td><label>アイコン画像<br> <input type="file"
						name="icon">
				</label></td>

			</tr>
			<tr>
				<c:set var="color" value="${myUser.color}"></c:set>
				<td><label>個人カラー<br> <input type="color"
						name="color" value="${color}">
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" id = "submit" value="変更"  onClick="return Check()"> <span
					id="error_message"> </span>

					<p class="error">
						<c:out value="${message.title}" />
						<c:out value="${message.message}" />
					</p></td>
			</tr>
		</table>
	</form>
	<script src="js/submitConfirm.js"></script>
</body>
</html>