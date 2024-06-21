<!--ご褒美履歴-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ご褒美登録</title>
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
<h1>ご褒美登録</h1>
<form method="post" action="/A3/RewardsRegistServlet">
	※<input type="text" name="reward" placeholder="ご褒美"><br>
	※<input type="text" name="point" placeholder="付与ポイント"><br>
	<select name="name">
		<c:forEach var="e" items="${ud}" >
			<option value="${e.uid}">${e.name}</option>
		</c:forEach>
	</select><br>
	<input type="submit" name="regist" value="登録"><br>
</form>
</body>

</html>