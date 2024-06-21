<!--やることリスト-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>やることリスト | F&amp;M</title>
<link rel="stylesheet" href="css/main.css">

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
<header>

</header>
<br><br><br><br>
<h1>やることリスト</h1>
<c:forEach var="e" items="${todoview}">
<c:set var="task" value="${e.task}"></c:set>
	<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" ><c:out value="${task}" /></button><br>
	<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" >編集・削除</button><br>
</c:forEach>

<button onclick="window.location.href= '/A3/TodoRegistServlet'" >登録</button><br>
<button onclick="window.location.href= '/A3/CalendarServlet'" >カレンダー</button><br>
<button onclick="window.location.href= '/A3/TodayListServlet'" >当日リスト</button><br>

<p>${message}</p>
<script src="js/main.js"></script>
</body>
</html>