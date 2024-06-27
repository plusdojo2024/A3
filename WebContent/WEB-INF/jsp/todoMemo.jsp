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
    <link rel="stylesheet" href="css/todoMemo.css">

    <div class="nav">
        <div class="left_icons">
        <c:set var="icon" value="${dbUser.icon}"></c:set>
            <div class="green_box"><img src="${icon}" id="user_icon"></div>
            <div class="green_box"><c:out value="${dbUser.havePoint}pt" /></div>
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
<div class="body">
<br><br><br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
</div>
<main>
<br><br><br><br><br><br><br>
	<h1 class="title_box">項目詳細</h1>
	<div class="t_main">
	<table>
	<c:forEach var="e" items="${todolist}">
	<c:set var="category" value="${e.category}" />
	<c:set var="point" value="${e.givePoint}" />
	<c:set var="memo" value="${e.memo}" />
	<tr>
	<td>
	<p>カテゴリー：</p>
	</td>
	<td class="view_box">
		<c:out value="${category}" /><br>
	</td>
	</tr>
	<tr>
	<td>
	<p>ポイント：</p>
	</td>
	<td class="view_box">
		<c:out value="${point}" /><br>
	</td>
	</tr>
	<tr>
	<td>
	<p>メモ：</p>
	</td>
	<td class="view_box">
		<c:out value="${memo}" />
	</td>
	</tr>
	<tr>
	<td>
	<p>履歴：</p>
	</td>
	<td class="view_box">
	<c:forEach var="e" items="${todoHistory}">
	<c:set var="date" value="${e.todoDate}"></c:set>
	<c:set var="name" value="${e.name}"></c:set>
		<c:out value="${date}"></c:out><br>
		<c:out value="${name}"></c:out><br>
	</td>
	</tr>
	</c:forEach>
	</c:forEach>
	</table>
	</div>
</main>
<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
<script src="js/main.js"></script>
</body>
</html>