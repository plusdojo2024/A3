<!--家族情報変更-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家族情報変更</title>
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
<h1>家族情報変更</h1>
<form id="create_form" method="post"
		action="/A3//FamilyEditServlet">
		<table>
		<c:set var="family_name" value="${userNoHash.familyName}" />
		<c:set var="mail" value="${userNoHash.mail}" />

			<tr>
				<td><label>家族名<br> <input type="text"
						name="family_name" value="${family_name}">
				</label></td>

			</tr>
			<tr>
				<td><label>メールアドレス<br> <input type="email"
						name="mail"  value="${mail}">
				</label></td>
			<tr>
			<tr>
				<td><label>家族パスワード<br> <input type="password"
						name="family_pass">
				</label></td>
			<tr>
			<tr>
				<td><label>家族パスワード確認<br> <input type="password"
						id = "pass_check">
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" value="変更"> <span
					id="error_message"></span>
					<p class="error"><c:out value="${message.title}" />  <c:out value="${message.message}" /></p>
					</td>
			</tr>

		</table>
	</form>
</body>
</html>