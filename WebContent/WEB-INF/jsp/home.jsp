<!--ホーム-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
            <button class="account-management"><a href="/A3/AccountServlet">アカウント管理</a></button>
            <button class="logout"><a href="/A3/LogoutServlet">ログアウト</a></button>
        </div>

    </div>
<title>ホーム画面</title>
</head>
<body>
<h1>ホーム画面</h1>
<a href="/A3/TodoServlet">やることリスト</a>
<a href="/A3/NoteServlet">引継ぎノート</a>
<a href="/A3/RewardsServlet">ご褒美</a>
<a href="/A3/AlbumServlet">アルバム</a>
</body>
</html>