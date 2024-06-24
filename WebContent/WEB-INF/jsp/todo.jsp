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
<link rel="stylesheet" href="css/todo.css">

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
		<a href="/A3/HomeServlet"><img src="images/FM1.png" width="90%" height="90%"></a>
	</div>

    <div class="right_buttons">
        <button class="account-management"
            onclick="window.location.href = '/A3/AccountServlet';">アカウント管理<br><img src="images/ic007.png" width="65px" height="65px"></button>
        <button class="logout"
            onclick="window.location.href = '/A3/LogoutServlet';">ログアウト<br><img src="images/935.png" width="65px" height="65px"></button>

    </div>

</div>
</head>
<body>
<header>

</header>
<main>
	<br><br><br><br><br><br><br>
	<h1 class="title_box">やることリスト</h1>
	<c:forEach var="e" items="${todoview}">
	<c:set var="task" value="${e.task}"></c:set>
	<div class="btn">
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button><br>
	</div>
	</c:forEach>

	<button onclick="window.location.href= '/A3/TodoRegistServlet'" >登録</button><br>
	<button onclick="window.location.href= '/A3/CalendarServlet'" >カレンダー</button><br>
	<button onclick="window.location.href= '/A3/TodayListServlet'" >当日リスト</button><br>

	<p>${message}</p>
</main>
<script src="js/main.js"></script>
</body>
</html>