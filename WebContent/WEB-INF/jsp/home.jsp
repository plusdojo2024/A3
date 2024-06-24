<!--ホーム-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link rel="stylesheet" href="css/main.css">
<!-- <link rel="stylesheet" href="css/home.css"> -->

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
	<br><br><br><br><br><br>
<!-- 	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div> -->

    <br><br><br><br>
<h1>当日のリスト</h1>
<br>
<div class="table-container">
	<h2>やることリスト</h2>
	<table>
		<%-- <c:forEach> --%>
		<tr>
			<td>${todoList}</td>
			<td><input type="checkbox" name="checkbox"></td>
		</tr>
		<%-- </c:forEach> --%>
	</table>

	<h2>前日忘れたこと</h2>
	<table>
		<%-- <c:forEach> --%>
		<tr>
			<td>${todoListAfter}</td>
		</tr>
		<%-- </c:forEach> --%>
</table>
</div>

<h2>前日の引継ぎノート</h2>
<%-- <c:choose> --%>
<%--	<c:when test="${notes}=null"> --%>
		<p>前日の引継ぎノートのデータはありません。<p>
<%--	</c:when>  --%>
<%--	<c:when test="${notes}!=null"> --%>
		<button name="notes">${notes}aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</button>
<%--	</c:when>  --%>
<%-- </c:choose> --%>

</body>

<script>

</script>


</html>