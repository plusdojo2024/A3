<!--引継ぎノート-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>引継ぎノート</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/note.css">

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
<br><br><br>
<div id="main">
	<h2>引継ぎノート</h2>
	<br>
<br>
<br>
<br>
	<form method="post" action="/A3/NoteServlet" enctype="multipart/form-data" id="form">
		<table>
		<tr>
		<c:out value="${date}" />
		</tr>

			<tr>
				<td><label>タイトル<br> <input type="text"
						name="note_title" maxlength="20" placeholder="タイトルを記入">
				</label></td>
			</tr>
			<tr>
				<td><textarea name="note" maxlength="200" placeholder="引継ぎ事項"></textarea></td>
			</tr>
			<tr>
				<td ><label>写真１<br> <input type="file" name="photo" id="photo">
				</label><br><span
					id="error_message"></span></td>
				<td ><label>写真２<br> <input type="file" name="photo_two" id="photo_two">
				</label><br><span
					id="error_message_two"></span></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" name="submit" value="登録" onclick="return Check();">
					<a href="/A3/NoteEditServlet">履歴・編集</a><span id="submit_error"></span>
				</td>
				<c:out value="${message.message}" />

			</tr>

		</table>
	</form>
</div>
<script src="js/fileSizeCheck.js"></script>
<script src="js/submitConfirm.js"></script>
<script src="js/note.js"></script>
</body>
</html>