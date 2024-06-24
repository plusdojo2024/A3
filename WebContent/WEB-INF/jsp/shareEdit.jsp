<!--家事分担編集-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>家事分担編集・削除</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/shareRegist.css">

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
	<form action="/A3/ShareEditServlet" method="post">
		<br> <br> <br> <br> <br> <br> <br> <br>

		<div class="title_box">分担編集・削除</div>
		選択した予定：<c:out value="${date}" />：<c:out value="${event}" />
		<c:set var="day" value="${date}" /><c:set var="select_task" value="${event}" />
		<input type="hidden" name="start_date" value="${day}">
		<input type="hidden" name="select_task" value="${select_task}">
		担当者：<c:out value="${name}" />
		<c:set var="select_name" value="${name}" />
		<input type="hidden" name="select_name" value="${select_name}">
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
			繰り返し<select name="loop"
				onchange="Checkbox(this.value)">
				<option value="0">繰り返さない</option>
				<option value="1">繰り返す</option>
			</select>
		</div>

		<div class="main_box">曜日</div>

		<label for="monday">月曜日</label> <input type="checkbox" id="monday"
			name="week[]" value="2" style="pointer-events: none" required><br>
			<label for="tuesday">火曜日</label>
		<input type="checkbox" id="tuesday" name="week[]" value="3" style="pointer-events: none" required><br>

		<label for="wednesday">水曜日</label> <input type="checkbox"
			id="wednesday" name="week[]" value="4" style="pointer-events: none" required><br>
			<label for="thuresday">木曜日</label> <input type="checkbox" id="thuresday"
			name="week[]" value="5" style="pointer-events: none" required><br>
			<label for="friday">金曜日</label>
		<input type="checkbox" id="friday" name="week[]" value="6" style="pointer-events: none" required><br>

		<label for="saturday">土曜日</label> <input type="checkbox" id="saturday"
			name="week[]" value="7" style="pointer-events: none" required><br>
			 <label for="sunday">日曜日</label>
		<input type="checkbox" id="sunday" name="week[]" value="1" style="pointer-events: none" required><br>

		<div class="main_box">
		<c:set var="set_today" value = "${today}" />
		<c:set var="set_end" value = "${endDay}" />
			いつまで<input type="date" id="end_date" name=end_date min="${set_today}" max="${set_end}"
			style="pointer-events: none" >
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
		</script>
		<c:out value="${msg}" />
	</form>
</body>
</html>