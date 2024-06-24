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
    <link rel="stylesheet" href="css/todoEdit.css">

<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${dbUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${dbUser.havePoint}" />
		</div>
	</div>

	<div class="home_logo">
		<a href="/A3/HomeServlet"><img src="images/FM1.png" width="90%" height="90%"></a>
	</div>

    <div class="right_buttons">
        <button class="account-management"
            onclick="window.location.href = '/A3/AccountServlet';">アカウント管理<br><img src="images/ic007.png" width="65px" height="65px"></button>
        <button class="logout"
            onclick="window.location.href = '/A3/LogoutServlet';">ログアウト<br><img src="images/935.png" width="65px" height="65px"></button>

    </div>

</div>
</head>
<body>
<div class="body">
<br><br><br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
</div>
<main>
	<br><br><br><br><br><br><br>
	<h1 class="title_box">家事編集・削除</h1>
	<div class="t_main">
	<p>${message}</p>
	<p><sup style="color: red;">*</sup>は入力必須項目です。</p>
	<c:forEach var="e" items="${todolist}" >
	<c:set var="list_id" value="${e.listId}"></c:set>
	<c:set var="family_id" value="${e.familyId}"></c:set>
	<c:set var="list_date" value="${e.listDate}"></c:set>
	<c:set var="task" value="${e.task}"></c:set>
	<c:set var="category" value="${e.category}"></c:set>
	<c:set var="give_point" value="${e.givePoint}"></c:set>
	<c:set var="memo" value="${e.memo}"></c:set>
	<form method="post" action="/A3/TodoEditServlet">
	<table>
		<tr>
			<td><input type="hidden" value="${list_id}" name="list_id">
			<input type="hidden" value="${family_id}" name="family_id">
			<input type="hidden" value="${list_date}" name="list_date">
			</td>
		</tr>
		<tr>
		<td>
		<input type="text" name="task" value="${task}"><sup style="color: red;">*</sup>
		</td>
		</tr>
		<tr>
		<td>
		<select name="category" required class="select_box">
		<option value="${category}">${e.category}</option>
		<option value="洗濯">洗濯</option>
		<option value="ごみ捨て">ごみ捨て</option>
		<option value="掃除">掃除</option>
		<option value="料理">料理</option>
		<option value="買い物">買い物</option>
		<option value="日用品の補充">日用品の補充</option>
		<option value="子育て">子育て</option>
		<option value="その他">その他</option>
		</select><sup style="color: red;">*</sup>
		</td>
		</tr>
		<tr>
		<td>
		<select name="give_point" class="select_box">
		<script>
		var i;
		for(i=1; i<11; i+=1) {
			document.write('<option value="'+i+'">'+i+'pt</option>');
		}
		</script>
		</select><sup style="color: red;">*</sup></td>
		<td>
		<p>設定中のポイント：${give_point}</p>
		</td>
		</tr>
		<tr>
		<td>
		<textarea name="memo">${memo}</textarea>
		</td>
		</tr>
		<tr>
		<td>
		<input type="submit" name="submit" value="更新" class="custom-submit">
		</td>
		<td>
		<input type="submit" name="submit" value="削除" class="custom-submit_d">
		</td>
		</tr>
		</table>
		</form>
	</c:forEach>
	</div>
</main>
<script src="js/main.js"></script>
<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
</body>
</html>