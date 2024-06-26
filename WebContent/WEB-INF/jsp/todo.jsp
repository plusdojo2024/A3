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
		<c:set var="icon" value="${dbUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${dbUser.havePoint}" />
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
<!--
<div class="body">
<br><br><br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div> -->
</div>
<main>
	<br><br><br><br><br><br><br>
	<h1 class="title_box">やることリスト</h1>
	<p class="message">${message}</p>
	<!-- <div>
	<h3 class="category1">洗濯</h3>
	<h3 class="category2">ごみ捨て</h3>
	<h3 class="category3">掃除</h3>
	<h3 class="category4">料理</h3>
	<h3 class="category5">買い物</h3>
	<h3 class="category6">日用品の補充</h3>
	<h3 class="category7">子育て</h3>
	<h3 class="category8">その他</h3>
	</div>-->
	<ul class="filter">
		<li data-filter="all" class="is-active">全て表示</li>
		<li data-filter="sentaku">洗濯</li>
		<li data-filter="gomisute">ごみ捨て</li>
		<li data-filter="souji">掃除</li>
		<li data-filter="ryouri">料理</li>
		<li data-filter="kaimono">買い物</li>
		<li data-filter="hojuu">日用品の補充</li>
		<li data-filter="kosodate">子育て</li>
		<li data-filter="sonota">その他</li>
	</ul>
	<!-- <div class="btn">
	<c:forEach var="e" items="${todoview}">
	<c:set var="task" value="${e.task}"></c:set>
	<c:set var="category" value="${e.category}"></c:set>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button><br>
	</c:forEach>
	</div> -->
	<ul class="category">
	<c:forEach var="e" items="${todoview}">
	<c:set var="task" value="${e.task}"></c:set>
	<c:set var="category" value="${e.category}"></c:set>
	<c:if test="${category == '洗濯'}">
	<p>洗濯</p>
		<li class="is-show" data-category="${category}"><button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button></li>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button><br>
	</c:if>
	<c:if test="${category == '料理'}">
	<p>料理</p>
		<li class="is-show" data-category="${category}"><button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button></li>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button><br>
	</c:if>
	<!-- <li class="is-show" data-category="${category}"><button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button></li>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button><br>-->
	</c:forEach>
	</ul>

	<button onclick="window.location.href= '/A3/TodoRegistServlet'"  class="r_btn">家事登録</button><br>
	<button onclick="window.location.href= '/A3/CalendarServlet'"  class="c_btn">カレンダー</button><br>
</main>
<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
<script src="js/main.js"></script>
</body>
</html>