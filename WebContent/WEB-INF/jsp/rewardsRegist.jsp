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
			<c:out value="${dbUser.havePoint}pt" />
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
<div class = "rewards_title">ご褒美登録</div>
<p style="font-size:14px;"><sup style="color: red;" >　　　　　　　　　　*</sup>すべて入力必須です。</p>
	<form method="post" action="/A3/RewardsRegistServlet">


	<div class="ex_form">
		　　ご褒美名：　<input type="text" name="reward" placeholder="ご褒美" required><br>
	</div>

	<div class="ex_form">
		付与ポイント：　<input type="number" name="reqPoint" placeholder="付与ポイント" required min="1" max="1000"><br>
	</div>

	<div class="ex_form">
		　 　対象者：　<select name="name" required class="name">
			<option value="">対象者を選択してください</option>
			<c:forEach var="e" items="${ud}" >
				<option value="${e.uid}">${e.name}</option>
			</c:forEach>
		</select>
	</div>
	<!-- <input type="submit" name="regist" value="登録" id="regist"><br> -->
	<div class="ex_form">
	<input type="submit" class = "reregi-button" name="regist" value="登録" onclick="return registBt()"><br>
	</div>
	</form>


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
		 let ans = confirm("ご褒美を登録しますか？");

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