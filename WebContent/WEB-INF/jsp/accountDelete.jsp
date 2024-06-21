<!-- アカウント削除-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main.css">

    <div class="nav">
        <div class="left_icons">
        <c:set var="icon" value="${myUser.icon}" />
            <div class="green_box"><img src="${icon}" id="user_icon"></div>
            <div class="green_box"><c:out value="${myUser.havePoint}" /></div>
        </div>

        <div class="home_logo">
            <a href="/A3/HomeServlet">F&M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management" id="account_manage">アカウント管理</button>
            <button class="logout" id="logout">ログアウト</button>
        </div>

    </div>
<title>家族削除画面</title>
</head>
<body>
	<h1>家族削除</h1>
	<c:forEach var="e" items="${familyList}">
		<form method="post" action="/A3/AccountDeleteServlet">
			<c:set var="name" value="${e.name}"></c:set>
			<c:set var="uid" value="${e.uid}"></c:set>
			<c:set var="admin" value="${e.admin}"></c:set>
			<input type="submit" name="user_name" value="${name}"> <input
				type="hidden" name="uid" value="${uid}"> <input
				type="hidden" name="admin" value="${admin}">
		</form>
	</c:forEach>
<script src="js/main.js"></script>
</body>
</html>