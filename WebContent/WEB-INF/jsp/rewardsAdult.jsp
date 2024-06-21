<!--ご褒美（大人）-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/rewards.css">

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
</head>
<body>
<h1>ご褒美一覧</h1>
	<div class="white_box">
	<c:forEach var="e" items="${rewardsList}" >  <!--リストの内容を端から順に入れていく-->
		<form method="post" action="/A3/RewardsEditServlet">
			${e.reward}
			<input type = "text" name = "reward" value="${e.reward}">
			<input type = "text" name = "point" value="${e.reqPoint}">
			<input type = "text" name = "uId" value="${e.uid}">
			<input type = "text" name = "name" value="${e.name}">
			<input type = "text" name = "rewardId" value="${e.rewardId}">
			<input type = "text" name = "rewardDate" value="${e.rewardDate}">
			<input type = "text" name = "re" value="${e.request}">
		<input type="submit" name="submit" value="更新">
		<input type="submit" name="submit" value="削除"><br>
		</form>
		<hr>
</c:forEach>
	<button class="rewards_regist"
			onclick="window.location.href = '/A3/RewardsRegistServlet';">ご褒美登録</button>
	</div><br>
</body>
</html>