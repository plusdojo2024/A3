<!--引継ぎノート履歴-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>履歴</title>
<link rel="stylesheet" href="css/main.css">

<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${myUser.icon}" />
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${myUser.havePoint}" />
		</div>
	</div>

	<div class="home_logo">
		<a href="/A3/HomeServlet">F&M</a>
	</div>

	<div class="right_buttons">
		<button class="account-management">
			<a href="/A3/AccountServlet">アカウント管理</a>
		</button>
		<button class="logout">
			<a href="/A3/LogoutServlet">ログアウト</a>
		</button>
	</div>

</div>
</head>
<body>
	<h2>履歴</h2>
	<form id="create_form" method="post" action="/A3/NoteEditServlet"
		enctype="multipart/form-data">
		<table>
		<c:set var="note_date" value="${note.noteDate}" />
		<c:set var="title" value="${note.title}" />
		<c:set var="memo" value="${note.memo}" />
			<tr>

				<td><label><input type="text" name="noteDate"
						value="${note_date}"><br> </label></td>
			</tr>
			<tr>
				<td><label><input type="text" name="title"
						value="${title}"><br> </label></td>
			</tr>
			<tr>
				<td><label><input type="text" name="memo"
						value="${memo}"><br> </label></td>

			</tr>
			<tr>
				<td><input type="submit" name="submit" value="更新"></td>
				<td><input type="submit" name="submit" value="削除"></td>
			</tr>

		</table>
	</form>


</body>
</html>