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
<h1>アルバム</h1>
<%-- <c:forEach> --%>
	<div class="accordion" onclick="toggleAccordion('accordion1')">xxxx年xx月xx日</div>
	<div class="panel" id="accordion1">
    	<table>
    		<thead>
                <tr>
                    <th>日付</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>写真</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="checkbox" id="ch"></td>
                </tr>
            </tbody>
    	</table>
	</div>
<%-- </c:forEach> --%>

<button id="deleteButton" onclick="deleteChecked()">削除</button>
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