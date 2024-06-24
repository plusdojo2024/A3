<!--ご褒美（大人）-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/rewards.css">


    <div class="nav">
        <div class="left_icons">
        <c:set var="icon" value="${myUser.icon}" ></c:set>
            <div class="green_box"><img src="${icon}" id="user_icon"></div>
            <div class="green_box"><c:out value="${myUser.havePoint}" /></div>
        </div>

        <div class="home_logo">
            <a href="/A3/HomeServlet">F&M</a>
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

<h1>ご褒美一覧</h1>
	<c:forEach var="e" items="${rewardsList}" >  <!--リストの内容を端から順に入れていく-->
		<form method="post" action="/A3/RewardsEditServlet">
			<input type = "text" name = "reward" value="${e.reward}"><br>
			<input type = "text" name = "reqPoint" value="${e.reqPoint}"><br>
			<input type = "hidden" name = "uId" value="${e.uid}">
			<input type = "text" name = "name" value="${e.name}"><br>
			<input type = "hidden" name = "rewardId" value="${e.rewardId}">
			<input type = "hidden" name = "rewardDate" value="${e.rewardDate}">
			<input type = "hidden" name = "re" value="${e.request}">
			<input type="submit" name="submit" value="更新">　
			<input type="submit" name="submit" value="削除" onclick="return deleteBt()"><br>
		</form>
</c:forEach>

	<div class="white_box">
		<button class="rewards_regist"
			onclick="window.location.href = '/A3/RewardsRegistServlet';">ご褒美登録</button>
	</div><br>
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
</script>

</html>