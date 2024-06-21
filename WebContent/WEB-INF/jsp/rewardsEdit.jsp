<!--ご褒美一覧-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ご褒美　編集・削除</title>
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
</head>
<body>
<h1>ご褒美　編集・削除</h1>
<form method="post" action="/A3/RewardsEditServlet">
	<input type="text" name="reward" value="${param.reward}"><br>
	<input type="text" name="point" value="${param.point}"><br>
	<input type="text" name="name" value="${param.name}"><br>
	<input type="hidden" name="reward_id" value="${param.rewardId}"><br>
	<input type="hidden" name="reward_date" value="${param.rewardDate}"><br>
	<input type="hidden" name="request" value="${param.re}"><br>
	<input type="hidden" name="u_id" value="${param.uId}"><br>
	<br>
<input type="submit" name="regist" value="更新完了">　

</form>
</body>
</html>