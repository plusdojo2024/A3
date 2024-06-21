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
<title>アカウント管理画面</title>


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
	<h1>アカウント管理画面</h1>
	<c:if test="${myUser.role==1}">
		<button id="family_regist">家族登録</button>
	</c:if>
	<button id="user_edit">個人情報変更</button>
	<br>
	<br>
	<c:if test="${myUser.role==1}">
		<button id="family_edit">家族情報変更</button>
	</c:if>
	<c:if test="${myUser.role==1}">
		<button id="user_delete">アカウント削除</button>
	</c:if>
	<c:out value="${message.message}" />
	<script src="js/account.js"></script>
</body>
</html>