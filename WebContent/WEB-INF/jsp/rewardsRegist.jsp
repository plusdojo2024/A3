<!--ご褒美履歴-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ご褒美登録</title>
</head>

<body>
<h1>ご褒美登録</h1>
<form method="post" action="/A3/rewardsRegistServlet">
※<input type="text" name="reward" value="ご褒美"><br>
※<input type="text" name="point" value="付与ポイント"><br>
※<input type="text" name="name" value="担当者"><br>
<input type="submit" name="regist" value="登録"><br>
</form>
</body>

</html>