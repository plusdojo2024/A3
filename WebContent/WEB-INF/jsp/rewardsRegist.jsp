<!--ご褒美履歴-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ご褒美登録</title>
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
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div class = "rewardsRe">
<h1>ご褒美登録</h1>
	<div class = "rewardsRegist">
		<form method="post" action="/A3/RewardsRegistServlet">
			※<input type="text" name="reward" placeholder="ご褒美"><br>
			※<input type="text" name="reqPoint" placeholder="付与ポイント"><br>
			※<select name="name">
				<c:forEach var="e" items="${ud}" >
					<option value="${e.uid}">${e.name}</option>
				</c:forEach>
				</select>
	<!-- <input type="submit" name="regist" value="登録" id="regist"><br> -->
<br>
		<input type="submit" name="regist" value="登録" onclick="return registBt()"><br>
		</form>
	</div>
</div>
<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">


<script>
//登録ボタン押された時の処理
	/* let regist =document.getElementById("regist");

	regist.addEventListener('click',function(event){
		let ans = confirm("登録してもよろしいですか？");
		alert(ans);
		if(ans == true){
			return true;
		}else{
			event.preventDefault();
			return false;
		}
	}); */

	function registBt(){
		 let regist =document.getElementById("regist");
		 let ans = confirm("登録しますか？");

			if(ans == true){
				return true;
			}else{
				return false;
			}
	}

</script>

</body>

</html>