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
<br><br><br><br>
<%--<p><a href="/A3/TodoMemoServlet?name=掃除機をかける">掃除機</a>--%>
<%--<p><a href="/A3/TodoMemoServlet?name=皿洗い">皿洗い</a>--%>
<%--<p><a href="/A3/TodoMemoServlet?name=${task}">掃除機</a>--%>
<c:forEach var="e" items="${todoview}">
<c:set var="task" value="${e.task}"></c:set>
	<a href="/A3/TodoMemoServlet?name=${task}"><c:out value="${task}" /></a>
	<button id="todo_edit" name="${task}">編集・削除</button><br>
</c:forEach>

<p><a href="/A3/TodoRegistServlet">登録</a></p>
<script src="js/todoedit.js"></script>
</body>
</html>