<!--ご褒美履歴-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ご褒美登録</title>
</head>

<body>
<h1>ご褒美登録</h1>
<form method="post" action="/A3/RewardsRegistServlet">
	※<input type="text" name="reward" placeholder="ご褒美"><br>
	※<input type="text" name="point" placeholder="付与ポイント"><br>
	<select name="name">
		<c:forEach var="e" items="${ud}" >
			<option value="${u.uId}">${u.name}</option>
		</c:forEach>
	</select><br>
	<input type="submit" name="regist" placeholder="登録"><br>
</form>
</body>

</html>