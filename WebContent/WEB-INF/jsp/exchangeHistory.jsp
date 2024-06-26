<!--交換履歴-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ご褒美一覧</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/rewards.css">



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
<div class="body">
<br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
</div>

	<br>
	<br>
<div class = "rewards_title">交換履歴</div>
	<c:forEach var="e" items="${exchangeList}">
		<div>
			<div>
				<c:out value="${e.exchangeDate}" />
			</div>
			<div>
				交換内容：
				<c:out value="${e.reward}" />
			</div>
			<div>
				交換した人：
				<c:out value="${e.name}" />
			</div>
		</div>
	</c:forEach>

	<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
</body>
</html>