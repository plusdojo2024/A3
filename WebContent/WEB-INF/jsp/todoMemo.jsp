<!--項目詳細-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>項目詳細 | F&amp;M</title>
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/todoRegist.css">

    <div class="nav">
        <div class="left_icons">
        <c:set var="icon" value="${myUser.icon}"></c:set>
            <div class="green_box"><img src="${icon}" id="user_icon"></div>
            <div class="green_box"><c:out value="${myUser.havePoint}" /></div>
        </div>

        <div class="home_logo">
            <a href="/A3/HomeServlet">F&M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management"><a href="/A3/AccountServlet">アカウント管理</a></button>
            <button class="logout"><a href="/A3/LogoutServlet">ログアウト</a></button>
        </div>

    </div>
</head>
<body>
<main>
<br><br><br><br>
<h1 class="title_box">項目詳細</h1>
<c:forEach var="e" items="${todolist}">
	<c:out value="${e.category}" /><br>
	<c:out value="${e.givePoint}" /><br>
	<c:out value="${e.memo}" />
</c:forEach>
</main>
<script src="js/main.js"></script>
</body>
</html>