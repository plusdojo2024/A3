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
</head>
<body>
<%--<p><a href="/A3/TodoMemoServlet?name=掃除機をかける">掃除機</a>--%>
<%--<p><a href="/A3/TodoMemoServlet?name=皿洗い">皿洗い</a>--%>
<%--<p><a href="/A3/TodoMemoServlet?name=${task}">掃除機</a>--%>
<c:forEach var="e" items="${todoview}">
	<a href="/A3/TodoMemoServlet?name=${e.task}"><c:out value="${e.task}" /></a>
	<a href="/A3/TodoEditServlet?name=${e.task}">編集・削除</a><br>
</c:forEach>

<p><a href="/A3/TodoRegistServlet">登録</a>
</body>
</html>