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
</head>
<body>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<h1>家族情報変更</h1>
<form id="create_form" method="post"
		action="/A3//FamilyEditServlet">
		<table>
		<c:set var="family_name" value="${userNoHash.familyName}" />
		<c:set var="mail" value="${userNoHash.mail}" />

			<tr>
				<td><label>家族名<br> <input type="text"
						name="family_name" value="${family_name}" maxlength="20">
				</label></td>

			</tr>
			<tr>
				<td><label>メールアドレス<br> <input type="email"
						name="mail"  value="${mail}" maxlength="64">
				</label></td>
			<tr>
			<tr>
				<td><label>家族パスワード<br> <input type="password"
						name="family_pass" maxlength="20"><span class="pass_warn">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください<br></span>
				</label></td>
			<tr>
			<tr>
				<td><label>家族パスワード確認<br> <input type="password"
						id = "pass_check" maxlength="20"><span class="pass_warn">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください<br></span>
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