<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset='utf-8' />
<link rel="stylesheet" href="css/main.css">

<link rel="stylesheet" href="css/calendar.css">
<title>テストcalendar</title>
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
					start : "${e.date}",
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

				//var url = "/TestWeb01/ShareUpdateDeleteServlet?event="+info.event.title+"&date="+info.event.start;
				const form = document.createElement('form');
				form.action = '/A3/ShareEditServlet';
				form.method = 'post';
				const data1 = document.createElement('input');
				data1.value = info.event.title;
				data1.name = "event";
				data1.type = "hidden";
				form.appendChild(data1);

				const data2 = document.createElement('input');
				data2.value = info.event.start;
				data2.name = "date";
				data2.type = "hidden";
				form.appendChild(data2);

				document.body.appendChild(form);

				form.submit();
				//window.location.href = url; // 通常の遷移

			}
		});

		calendar.render();
	});
</script>
</head>
<body>


	<div id='calendar'></div>
</body>
</html>
