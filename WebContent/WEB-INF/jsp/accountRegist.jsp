<!--新規登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/accountRegist.css">
<link rel="stylesheet" href="css/main.css">
<!--
<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${myUser.icon}" />
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${myUser.havePoint}" />
		</div>
	</div>

	<div class="home_logo">
		<a href="/A3/HomeServlet">F&M</a>
	</div>

	<div class="right_buttons">
		<button class="account-management">
			<a href="/A3/AccountServlet">アカウント管理</a>
		</button>
		<button class="logout">
			<a href="/A3/LogoutServlet">ログアウト</a>
		</button>
	</div>
</div>
-->
<title>家族登録画面</title>

</head>
<body>
	<h1>家族登録</h1>
	<form id="create_form" method="post" action="/A3//AccountRegistServlet"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><label>個人名<br> <input type="text"
						name="user_name">
				</label></td>
			<tr>
			<tr>
				<td><label>個人パスワード<br> <input type="password"
						name="user_pass">
				</label></td>
			<tr>
			<tr>
				<td colspan="2">
					<button type="button" id="parent">
						<img src="images/parent.png" class="role_image" id="parent_image">
					</button>
					<button type="button" id="child">
						<img src="images/children.png" class="role_image" id="child_image">
					</button>
				</td>
				<td><input type="hidden" name="role" value="0" id="role"></td>
			<tr>
			<tr>
				<td><label>アイコン画像<br> <input type="file"
						name="icon">
				</label></td>

			</tr>
			<tr>
				<td><label>個人カラー<br> <input type="color"
						name="color">
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" value="新規登録"> <span
					id="error_message"> </span>

					<p class="error">
						<c:out value="${message.title}" />
						<c:out value="${message.message}" />
					</p></td>
			</tr>
		</table>
	</form>
	<script src="js/accountRegist.js"></script>
</body>
</html>