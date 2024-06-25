<!--ご褒美（大人）-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ご褒美一覧</title>
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

<br><br>
<div class = "rewards_title">ご褒美一覧　　　　　　　　　</div>
<button class="reregi-submit"onclick="window.location.href = '/A3/RewardsRegistServlet';">ご褒美登録</button>
<br>
<br>
<br>
<span style="color:red">${msg}</span>

<div class = "rewards">
	<!--リストの内容を端から順に入れていく-->
	<c:forEach var="e" items="${rewardsList}" >
		<form method="post" action="/A3/RewardsEditServlet">
			<input type = "hidden" name = "reward" value="${e.reward}">${e.reward}<br>
			<input type = "hidden" name = "req_point" value="${e.reqPoint}">${e.reqPoint}pt<br>
			<input type = "hidden" name = "u_id" value="${e.uid}">
			<input type = "hidden" name = "name" value="${e.name}">${e.name}<br>
			<input type = "hidden" name = "reward_id" value="${e.rewardId}">
			<input type = "hidden" name = "reward_date" value="${e.rewardDate}">
			<input type = "hidden" name = "re" value="${e.request}">

			<input class="req_button" type="submit" name="req_button" value="リクエスト" onclick="return reqBt()">
			<input class="comp_button" type="submit" name="complete" value="交換完了" onclick="return compBt()">
			<input class="green_edit" type="submit" name="edit" value="更新">
			<input class="red_button" type="submit" name="delete" value="削除" onclick="return deleteBt()">　
			<br><br>
		</form>
<!-- 		<button class="req_buttont"onclick="window.location.href = '/A3/RewardsServlet';">リクエスト</button>
		<button class="comp_button"onclick="window.location.href = '/A3/RewardsServlet';">交換完了</button> -->
    </c:forEach>
</div>

<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">

</body>

<script>
	function deleteBt(){
		 let regist =document.getElementById("regist");
		 let ans = confirm("ご褒美を削除しますか？");

			if(ans == true){
				return true;
			}else{
				return false;
			}
	}

	function reqBt(){
		 let regist =document.getElementById("req");
		 let ans = confirm("ご褒美をリクエストしますか？");

			if(ans == true){
				return true;
			}else{
				return false;
			}
	}

	function compBt(){
		 let regist =document.getElementById("comp");
		 let ans = confirm("ご褒美の交換を完了しますか？");

			if(ans == true){
				return true;
			}else{
				return false;
			}
	}
</script>

</html>