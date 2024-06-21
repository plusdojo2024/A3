<!--ホーム-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/home.css">

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
<br><br><br><br><br>
<h1>ホーム画面</h1>
    <div class="button-container">
	<button class="button1"
			onclick="window.location.href = '/A3/TodoServlet';">やることリスト</button>
	<button class="button1"
			onclick="window.location.href = '/A3/NoteServlet';">引継ぎノート</button>
	<button class="button1"
			onclick="window.location.href = '/A3/RewardsServlet';">ご褒美</button>
	<button class="button1"
			onclick="window.location.href = '/A3/AlbumServlet';">アルバム</button>
    </div>
</body>

<script>

</script>


</html>