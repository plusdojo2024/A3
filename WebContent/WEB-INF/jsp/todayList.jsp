<!--当日のリスト-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>当日のやることリスト</title>
    <link rel="stylesheet" href="css/main.css">

    <div class="nav">
        <div class="left_icons">
        <c:set var="icon" value="${myUser.icon}" />
            <div class="green_box"><img src="${icon}" id="user_icon"></div>
            <div class="green_box"><c:out value="${myUser.havePoint}" /></div>
        </div>

        <div class="home_logo">
            <a href="/A3/HomeServlet">F&M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management"><a href="/A3/AccountServlet">アカウント管理</a></button>
            <button class="logout"><a href="/A3/LogoutServlet">ログアウト</a></button>
        </div>

    </div>
</head>
<body>

<br><br><br><br>
<h1>当日のリスト</h1>
<br>
<div class="table-container">
	<h2>やることリスト</h2>
	<table>
		<%-- <c:forEach> --%>
		<tr>
			<td>${numberToday}</td>
			<td>${todayList}</td>
			<td><input type="checkbox" name="checkbox" value="${numberToday}"></td>
		</tr>
		<%-- </c:forEach> --%>
	</table>

	<h2>前日忘れたこと</h2>
	<table>
		<%-- <c:forEach> --%>
		<tr>
			<td>${numberYesterday}</td>
			<td>${yesterdayList}</td>
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

<h2>ご褒美リクエスト</h2>
<%-- <c:choose> --%>
<%--	<c:when test="${rewards}=null"> --%>
		<p>リクエストされているご褒美はありません</p>
<%--	</c:when>  --%>

<%--	<c:when test="${rewards}!=null"> --%>
		<button name="rewards">${rewards}aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</button>
<%--	</c:when>  --%>
<%-- </c:choose> --%>

</body>
</html>