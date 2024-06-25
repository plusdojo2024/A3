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
<h1 class="box">アルバム</h1>
<%-- <c:forEach var="category" items="${yearMonthList}"> --%>
	<div class="accordion" onclick="toggleAccordion('accordion1')">
	<c:out value="${category.yearMonth}" /></div>
	<div class="panel" id="accordion1">
    	<table>
                    <tr>
                        <th>日付</th>
                    </tr>
                    <tr>
                        <td>写真</td>
                    </tr>
                    <tr>
                        <td><input type="checkbox" name="checkbox[]" id="ch"></td>
                    </tr>
                </table>
	</div>
<%-- </c:forEach> --%>

<button id="deleteButton" onclick="deleteChecked()" class="custom-submit">削除</button>
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