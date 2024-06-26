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
			<c:out value="${dbUser.havePoint} P" />
		</div>
	</div>

	<div class="home_logo">
		<a href="/A3/HomeServlet"><img src="images/FM1.png" width="90%"
			height="90%"></a>
	</div>

	<div class="right_buttons">
		<button class="account-management"
			onclick="window.location.href = '/A3/AccountServlet';">
			アカウント管理<br> <img src="images/ic007.png" width="65px"
				height="65px">
		</button>
		<button class="logout"
			onclick="window.location.href = '/A3/LogoutServlet';">
			ログアウト<br> <img src="images/935.png" width="65px" height="65px">
		</button>

	</div>

</div>
</head>
<body>
<div class="body">
	<br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
	<div id="main">
		<h2>
			<div id="title" class="box">引継ぎノート</div>
		</h2>
		<c:set var="day" value="${date}" />

		<form method="post" action="/A3/NoteServlet"
			enctype="multipart/form-data" id="form">
			<table class="note">
				<tr>
					<td><label><p>${day}</p> <input type="text"
							name="note_title" maxlength="20" placeholder="タイトルを記入" required>
					</label></td>
				</tr>
				<tr>
					<td><textarea name="note" maxlength="200" placeholder="引継ぎ事項"
							required></textarea></td>
				</tr>
				<div id="a">

					<tr>
					<tr>
						<td><label>写真１<br> <input type="file"
								name="photo" id="photo">
						</label><br> <span id="error_message"><br></span></td>
						<td id="preview"><span id="preview_msg"></span></td>
					</tr>
					<tr>
						<td><label>写真２<br> <input type="file"
								name="photo_two" id="photo_two">
						</label></td>
						<br>
						<span id="error_message_two"><br></span>
						<td id="preview_b"><span id="preview_msg_b"></span></td>
					</tr>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" name="submit" value="登録"
							onclick="return Check();"> <a href="/A3/NoteEditServlet">履歴・編集</a><span
							id="submit_error"></span></td>
						<c:out value="${message.message}" />

					</tr>
				</div>
			</table>
		</form>
	</div>
	<img class="back-button" src="images/603_1.png"
		onClick="history.back();return false;">

	<script src="js/fileSizeCheck.js"></script>
	<script src="js/submitConfirm.js"></script>
	<script src="js/note.js"></script>
</body>
</html>