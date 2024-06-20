<!--項目詳細-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>タイトル各自変更してください</title>
    <link rel="stylesheet" href="css/main.css">

    <div class="nav">
        <div class="left_icons">
        <c:set var="icon" value="${myUser.icon}"></c:set>
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
<main>
<h1>項目詳細</h1>
<c:forEach var="e" items="${todolist}">
	<c:out value="${e.category}" /><br>
	<c:out value="${e.givePoint}" /><br>
	<c:out value="${e.memo}" />

	<input type="text" name="task" value="${e.task}">
	<select name="category" required>
	<option value="">カテゴリーを選択してください</option>
	<option value="洗濯">洗濯</option>
	<option value="ごみ捨て">ごみ捨て</option>
	<option value="掃除">掃除</option>
	<option value="料理">料理</option>
	<option value="買い物">買い物</option>
	<option value="日用品の補充">日用品の補充</option>
	<option value="子育て">子育て</option>
	<option value="その他">その他</option>
	</select><br>
</c:forEach>
</main>
</body>
</html>