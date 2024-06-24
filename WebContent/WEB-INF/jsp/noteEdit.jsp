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
<br>
<br>
<br>
<br>
<br>

<br>
	<h2>履歴</h2>
							<c:out value="${message.message}" />

		<c:forEach var="a" items="${noteList }" >
			<form id="form" method="post" action="/A3/NoteEditServlet">
			<table>
			<tr>

				<td><label><input type="hidden" name="note_id"
						value="${a.noteId}"><br> </label></td>
			</tr>
			<tr>

				<td><c:out value="${a.noteDate}" />
				<label><input type="hidden" name="note_date"
						value="${a.noteDate}"><br> </label></td>
			</tr>
			<tr>
				<td><label><input type="text" name="title"
						value="${a.title}" maxlength="20" placeholder="タイトルを記入"><br> </label></td>
			</tr>
			<tr>
				<td><br><textarea name="note"
						 maxlength="200" placeholder="引継ぎ事項">${a.note}</textarea><br></td>

			</tr>
			<tr>
				<td><input type="submit" name="submit" value="更新">
				<input type="submit" name="submit" value="削除"></td>


			</tr>

		</table>

			</form>
		</c:forEach>





</body>
</html>