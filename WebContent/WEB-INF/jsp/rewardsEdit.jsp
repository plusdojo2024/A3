<!--ご褒美一覧-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ご褒美　編集・削除</title>
</head>
<body>
<h1>ご褒美　編集・削除</h1>
<form method="post" action="/A3/rewardsEditServlet">
	※<input type="text" name="reward" value="${rewards}"><br>
	※<input type="text" name="point" value="${point}"><br>
	<select name="name">
		<c:forEach var="e" items="${ud}" >
			<option value="${u.uId}">${u.name}</option>
		</c:forEach>
	</select><br>
<input type="submit" name="regist" value="更新">　
<input type="submit" name="regist" value="削除"><br>
</form>
</body>
</html>