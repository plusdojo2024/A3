<!--ご褒美一覧-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
※<input type="text" name="name" value="${name}"><br>
<input type="submit" name="regist" value="登録"><br>
</form>
</body>
</html>