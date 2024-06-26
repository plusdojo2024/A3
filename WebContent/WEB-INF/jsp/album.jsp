<!-- アルバム-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>アルバム</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/album.css">

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
		<a href="/A3/HomeServlet"><img src="images/FM1.png" width="90%"
			height="90%"></a>
	</div>

	<div class="right_buttons">
		<button class="account-management"
			onclick="window.location.href = '/A3/AccountServlet';">
			アカウント管理<br> <img src="images/ic007.png" width="65px"
				height="65px">
		</button>
		<button class="logout"
			onclick="window.location.href = '/A3/LogoutServlet';">
			ログアウト<br> <img src="images/935.png" width="65px" height="65px">
		</button>

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
	<div class="sidebar">
		<a href="/A3/HomeServlet">ホーム</a> <a href="/A3/TodoServlet">やることリスト</a>
		<a href="/A3/NoteServlet">引継ぎノート</a> <a href="/A3/AlbumServlet">アルバム</a>
		<a href="/A3/RewardsServlet">ご褒美</a>
	</div>
	<h1 class="box">アルバム</h1>
	<form method="post" action="/A3/AlbumServlet">
	<c:forEach var="category" items="${yearMonthList}">
		<div class="accordion" onclick="toggleAccordion('accordion1')">
			<br><c:out value="${category.yearMonth}" />
		</div>

			<div class="panel" id="accordion1">
				<c:forEach var="e" items="${albumList}">
					<c:if test="${category.yearMonth==e.yearMonth}">

						<c:set var="imageOne" value="${e.imageOne}" />
						<c:set var="imageTwo" value="${e.imageTwo}" />
						<c:set var="noteId" value="${e.noteId}" />
						<c:if test="${imageOne!=null}">
							<table>
								<tr>
									<th><c:out value="${e.noteDate}"></c:out></th>
								</tr>
								<tr>
									<td><img src="${imageOne}" style="width: 100%" class="zoomable-image" onclick="zoomImage(this)"></td>
									<c:if test="${imageTwo!=null}">
										<td><img src="${imageTwo}" style="width: 100%"></td>
									</c:if>
								</tr>
								<tr>
									<td><input type="checkbox" name="check_one[]" id="ch"
										value="${noteId}"></td>
									<c:if test="${imageTwo!=null}">
										<td><input type="checkbox" name="check_two[]" id="ch"
											value="${noteId}"></td>
									</c:if>
								</tr>
							</table>
						</c:if>
					</c:if>
				</c:forEach>
			</div>
	</c:forEach>

	<input type="submit" id="deleteButton" onclick="deleteChecked()"
		class="custom-submit" value="削除">
	</form>

	<img class="back-button" src="images/603_1.png"
		onClick="history.back();return false;">
	<script src="js/delete.js"></script>
</body>
<script>
	function toggleAccordion(panelId) {
		var panel = document.getElementById(panelId);
		if (panel.style.display === 'block') {
			panel.style.display = 'none';
		} else {
			panel.style.display = 'block';
		}
	}
</script>
</html>