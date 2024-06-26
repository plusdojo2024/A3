<!-- アカウント管理-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/account.css">

<title>アカウント管理画面</title>


<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${dbUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${dbUser.havePoint}pt" />
		</div>
	</div>

	<div class="home_logo">
		<a href="/A3/HomeServlet"><img src="images/FM1.png" width="90%" height="90%"></a>
	</div>

    <div class="right_buttons">
        <button class="account-management" P
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

    <div class="container">
	<h1 class="box">アカウント管理画面</h1>
	<div class="button-container">
	<c:if test="${dbUser.admin==1}">
		<button id="family_regist" class="button1">家族登録</button>
	</c:if>
	<button id="user_edit" class="button1">個人情報変更</button>
	<br>
	<br>
	<c:if test="${dbUser.admin==1}">
		<button id="family_edit" class="button1">家族情報変更</button>
	</c:if>
	<c:if test="${dbUser.admin==1}">
		<button id="user_delete" class="button1">アカウント削除</button>
	</c:if>
	<c:out value="${message.message}" />
	</div>
	</div>
	<script src="js/account.js"></script>
</div>
<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
</body>
</html>