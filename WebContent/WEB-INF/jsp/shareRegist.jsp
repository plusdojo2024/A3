<!--家事分担登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!--これを追記-->
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>家事分担登録</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/shareRegist.css">
<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${dbUser.icon}" />
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${dbUser.havePoint}" />
		</div>
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
	<form action="/A3/ShareRegistServlet" method="post">
		<br> <br> <br> <br> <br> <br> <br> <br>

		<div class="day">
			<c:out value="${today}" />
		</div>
		<br>
		<div class="title_box">分担登録</div>

		<div class="main_box">
			<label for="task">項目</label> <select id="task" name="task" required>
				<c:forEach var="task" items="${taskList}">
					<option value="${task.listId}">${task.task}</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<label for="uid">担当者</label> <select id="uid" name="uid" required>
				<c:forEach var="user" items="${userList}">
					<option value="${user.uid}">${user.name}</option>
				</c:forEach>
			</select>
		</div>

		<div class="main_box">
			<select name="loop"
				 onchange="Checkbox(this.value)">
				<option value="0">繰り返さない</option>
				<option value="1">繰り返す</option>
			</select>
		</div>

		<div class="main_box">曜日</div>

		<label for="monday">月曜日</label> <input type="checkbox" id="monday"
			name="week[]" value="2" style="pointer-events: none" ><br>
			<label for="tuesday">火曜日</label>
		<input type="checkbox" id="tuesday" name="week[]" value="3"style="pointer-events: none" ><br>

		<label for="wednesday">水曜日</label> <input type="checkbox"
			id="wednesday" name="week[]" value="4"style="pointer-events: none" ><br>
			<label for="thuresday">木曜日</label> <input type="checkbox" id="thuresday"
			name="week[]" value="5"style="pointer-events: none" ><br>
			<label for="friday">金曜日</label>
		<input type="checkbox" id="friday" name="week[]" value="6"style="pointer-events: none" ><br>

		<label for="saturday">土曜日</label> <input type="checkbox" id="saturday"
			name="week[]" value="7"style="pointer-events: none" ><br>
			 <label for="sunday">日曜日</label>
		<input type="checkbox" id="sunday" name="week[]" value="1"style="pointer-events: none" ><br>

		<div class="main_box">
		<c:set var="set_today" value = "${today}" />
		<input type ="hidden" name="select_date" value="${set_today}">
		<c:set var="set_end" value = "${endDay}" />
			いつまで<input type="date"  name=end_date min="${set_today}" max="${set_end}" style="pointer-events: none">
		</div>

		<div class="submit_box">
			<input type="submit" value="登録" name="regist">
		</div>

		<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">

		<script>
		//繰り返すを選択時の曜日選択処理
		function Checkbox(value) {
			var checkboxes = document.querySelectorAll('input[type="checkbox"]');
			checkboxes.forEach(function(checkbox) {
				checkbox.disabled = value === '0';
				checkbox.style.pointerEvents = value === '0' ? 'none' : 'auto';
			});

			var dates = document.querySelectorAll('input[type="date"]');
			dates.forEach(function(date) {
				date.disabled = value === '0';
				date.style.pointerEvents = value === '0' ? 'none' : 'auto';
			});
		}
		/*
		function enableNextDropdown(nextDropdownId) {
				var nextDropdown = document.getElementById(nextDropdownId);
				nextDropdown.disabled = false;
			}
			*/
		</script>
		<c:out value="${msg}" />
	</form>
</body>
</html>