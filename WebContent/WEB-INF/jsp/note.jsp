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
<br><br><br>
<div id="main">
	<h2>引継ぎノート</h2>
	<br>
<br>
<br>
<br>
	<form method="post" action="/A3/NoteServlet">
		<table>
		<tr>
		<c:out value="${date}" />
		</tr>

			<tr>
				<td><label>タイトル<br> <input type="text"
						name="memotittle">
				</label></td>
			</tr>
			<tr>
				<td><textarea name="memo">メモ</textarea></td>

			</tr>
			<tr>
				<td><label>写真<br> <input type="file" name="photo">
				</label></td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" name="submit" value="登録">
					<a href="/A3/NoteEditServlet">履歴・編集</a><span id="error_message"></span>
				</td>

			</tr>

		</table>
	</form>
</div>
</body>
</html>