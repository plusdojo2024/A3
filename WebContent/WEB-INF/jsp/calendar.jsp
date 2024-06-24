<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset='UTF-8' />
<link rel="stylesheet" href="css/main.css">
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
		<a href="home.png"></a> <a href="/A3/HomeServlet">F&M</a>
	</div>

	<div class="right_buttons">
		<button class="account-management"
			onclick="window.location.href = '/A3/AccountServlet';">アカウント管理</button>
		<button class="logout"
			onclick="window.location.href = '/A3/LogoutServlet';">ログアウト</button>
	</div>

</div>

<link rel="stylesheet" href="css/calendar.css">
<title>カレンダー</title>
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.14/index.global.min.js'></script>

<script>
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');



		var calendar = new FullCalendar.Calendar(calendarEl, {
			events : [
<c:forEach var="e" items="${eventList}">
<c:if test="${e!=null}">


				{
					title : '${e.task}：${e.name}',
					start : "${e.todoDate}",
					color : '${e.color}',
				},
</c:if>
</c:forEach>
				 ],
			locale : 'ja',
			selectable : true,
			headerToolbar : {
				left : 'prev,next today',
				center : 'title',
				right : 'dayGridMonth,timeGridWeek'
			},
			dateClick : function(info) {
				alert('clicked ' + info.dateStr);
				var url = "/A3/ShareRegistServlet?date="
						+ info.dateStr;
				window.location.href = url; // 通常の遷移
			},
			eventClick : function(info) {


				const form = document.createElement('form');
				form.action = '/A3/CalendarServlet';
				form.method = 'post';
				const data1 = document.createElement('input');
				data1.value = info.event.title;
				data1.name = "event";
				data1.type = "hidden";
				form.appendChild(data1);

				const data2 = document.createElement('input');
				data2.value = info.event.start;
				data2.name = "select_date";
				data2.type = "hidden";
				form.appendChild(data2);

				document.body.appendChild(form);

				form.submit();

			}
		});

		calendar.render();
	});
</script>
</head>
<body>
<br><br><br><br>

	<div id='calendar'></div>
</body>
</html>
