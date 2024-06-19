<!--項目詳細-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>項目詳細 | F&amp;M</title>
<%--<link rel="stylesheet" href="css/main.css">--%>
</head>
<body>
<header>
    <div class="nav">
        <div class="left_icons">
            <div class="green_box">父</div>
            <div class="green_box">ポイント</div>
        </div>

        <div class="home_logo">
            <a href="home.html">F&amp;M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management"><a href="/A3/AccountServlet">アカウント管理</a></button>
            <button class="logout"><a href="/A3/LogoutServlet">ログアウト</a></button>
        </div>

    </div>
</header>
<main>
<h1>項目詳細</h1>
<c:forEach var="e" items="${todolist}">
	<c:out value="${e.category}" />
	<c:out value="${e.givePoint}" />
	<c:out value="${e.memo}" />
</c:forEach>
</main>
</body>
</html>