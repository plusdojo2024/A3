<!-- アカウント削除-->
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
		<a href="home.png"></a> <a href="/A3/HomeServlet"><img src="images/FM1.png" width="90%" height="90%"></a>
	</div>

	<div class="right_buttons">
		<button class="account-management"
			onclick="window.location.href = '/A3/AccountServlet';">アカウント管理<br><img src="images/ic007.png" width="65px" height="65px"></button>
		<button class="logout"
			onclick="window.location.href = '/A3/LogoutServlet';">ログアウト<br><img src="images/935.png" width="65px" height="65px"></button>
	</div>
</div>
<title>家族削除画面</title>
</head>
<body>
<br><br><br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
	<h1 class="box">家族削除</h1>
	<div class="family_list">
		<c:forEach var="e" items="${familyList}">
			<form method="post" action="/A3/AccountDeleteServlet" id="form">
				<c:set var="name" value="${e.name}"></c:set>
				<c:set var="uid" value="${e.uid}"></c:set>
				<c:set var="admin" value="${e.admin}"></c:set>
				<input type="submit" class="family_name" name="user_name" value="${name}" onClick="return Check()"> <input
					type="hidden" name="uid" value="${uid}"> <input
					type="hidden" name="admin" value="${admin}">
			</form>
		</c:forEach>
	</div>
	<script src="js/delete.js"></script>

	<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
</body>
</html>