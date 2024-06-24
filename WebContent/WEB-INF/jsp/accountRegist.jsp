<!--家族登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/accountRegist.css">

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
<title>家族登録画面</title>

</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h1 class="box">家族登録</h1>
	<form id="form" method="post" action="/A3//AccountRegistServlet"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><label>個人名<br> <input type="text"
						name="user_name" maxlength="20">
				</label></td>
			<tr>
			<tr>
				<td><label>個人パスワード<br> <input type="password"
						name="user_pass" id="u_pass" maxlength="20"><span class="pass_warn">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください<br></span>
				</label></td>
			<tr>
			<tr>
				<td colspan="2">
					<button type="button" id="parent" class="role_button">
						<img src="images/parent.png" class="role_image" id="parent_image">
					</button>
					<button type="button" id="child" class="role_button">
						<img src="images/children.png" class="role_image" id="child_image">
					</button>
				</td>
				<td><input type="hidden" name="role" value="0" id="role"></td>
			<tr>
			<tr>
				<td><label>アイコン画像<br> <input type="file" accept="image/*"
						name="icon">
				</label></td>

			</tr>
			<tr>
				<td><label>個人カラー<br> <input type="color"
						name="color">
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" value="新規登録" onClick="return Check()"> <span
					id="error_message" class="custom-submit"> </span>

					<p class="error">
						<c:out value="${message.title}" />
						<c:out value="${message.message}" />
					</p></td>
			</tr>
		</table>
	</form>
	<script src="js/accountRegist.js"></script>
	<script src="js/submitConfirm.js"></script>
</body>
</html>