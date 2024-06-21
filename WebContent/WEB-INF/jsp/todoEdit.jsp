<!--項目編集-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>編集・削除 | F&amps;M</title>
    <link rel="stylesheet" href="css/main.css">

<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${myUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${myUser.havePoint}" />
		</div>
	</div>

	<div class="home_logo">
		<a href="home.png"></a> <a href="/A3/HomeServlet">F&M</a>
	</div>

	<div class="right_buttons">
		<button class="account-management"
			onclick="window.location.href = '/A3/AccountServlet';">アカウント管理</button>
		<button class="logout"
			onclick="window.location.href = '/A3/LogoutServlet';">ログアウト</button>
	</div>

</div>
</head>
<body>
<main>
<p>${message}</p>
<p><sup style="color: red;">*</sup>は入力必須項目です。</p>

<c:forEach var="e" items="${todolist}" >
<c:set var="list_id" value="${e.listId}"></c:set>
<c:set var="family_id" value="${e.familyId}"></c:set>
<c:set var="list_date" value="${e.listDate}"></c:set>
<form method="post" action="/A3/TodoEditServlet">
<table>
	<tr>
		<td><input type="hidden" value="${list_id}" name="list_id">
		<input type="hidden" value="${family_id}" name="family_id">
		<input type="hidden" value="${list_date}" name="list_date">
		</td>
		<td><sup style="color: red;">*</sup><input type="text" name="task" value="${e.task}"></td>
		<td><sup style="color: red;">*</sup>
		<select name="category" required>
		<option value="${e.category}">${e.category}</option>
		<option value="洗濯">洗濯</option>
		<option value="ごみ捨て">ごみ捨て</option>
		<option value="掃除">掃除</option>
		<option value="料理">料理</option>
		<option value="買い物">買い物</option>
		<option value="日用品の補充">日用品の補充</option>
		<option value="子育て">子育て</option>
		<option value="その他">その他</option>
		</select></td>
		<td>
		<select name="give_point">
		<script>
		var i;
		for(i=1; i<11; i+=1) {
			document.write('<option value="'+i+'">'+i+'pt</option>');
		}
		</script>
		</select></td>
	<td><p>設定中のポイント：${e.givePoint}</p></td>
	<td><textarea name="memo">${e.memo}</textarea></td>
	<td><input type="submit" name="submit" value="更新"></td>
	</tr>
	</table>
	</form>
</c:forEach>
</main>
<script src="js/main.js"></script>
</body>
</html>