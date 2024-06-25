<!--ご褒美一覧-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ご褒美　編集・削除</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/rewards.css">

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
<br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
</div>

<br>
<br>
<br>
<br>
<br>
<div class="rewards_title">ご褒美編集</div>
<form method="post" action="/A3/RewardsEditServlet">
	<input type="text" name="reward" value="${param.reward}"><br>
	<input type="text" name="reqPoint" value="${param.reqPoint}"><br>
	<select name="name">
		<c:forEach var="e" items="${ud}" >
			<option value="${e.uid}">${e.name}</option>
		</c:forEach>
	</select><br>
	<input type="hidden" name="reward_id" value="${param.rewardId}">
	<input type="hidden" name="reward_date" value="${param.rewardDate}">
	<input type="hidden" name="request" value="${param.re}">
	<input type="hidden" name="u_id" value="${param.uId}">
<input class="green_edit_button" type="submit" name="edit" value="更新" onclick="return editBt()">　


</form>

<script>
	function editBt(){
		let edit document.getElementById("edit");
		let ans = confirm("ご褒美内容を更新しますか？");

		if(ans == true){
			return true;
		}else{
			return false;
		}
	}


</script>
<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
</body>
</html>