<!--家族登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/accountRegist.css">
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
<title>家族登録画面</title>

</head>
<body>
	<br><br><br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>

	<h1 class="box">家族登録</h1>
	<form id="form" method="post" action="/A3//AccountRegistServlet"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td><label><div class="user_name">個人名：</div> <input type="text" style="width:300px; height:30px"
						name="user_name" maxlength="20" id="user_name" placeholder="山田花子" required >
				</label></td>
			<tr>
			<tr>
				<td><label><div class="u_pass">個人パスワード： </div><input type="password"
						name="user_pass" id="u_pass" maxlength="20" placeholder="password" style="width:300px; height:30px" required >
						<br><span class="pass_warn"><div class="text1">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください</div><br></span>
				</label></td>
			<tr>
			<tr>
				<td colspan="2" class="role">
					<button type="button" id="parent" class="role_button">
						<img src="images/parent.png" class="role_image" id="parent_image">
					</button>
					<button type="button" id="child" class="role_button">
						<img src="images/children.png" class="role_image" id="child_image">
					</button>
				</td>
				<td><input type="hidden" name="role" value="0" id="role"></td>
			<tr>
			<tr>
				<td><label><div class="icon">アイコン画像：</div> <input type="file" accept="image/*"
						name="icon" id="icon" >
				</label></td>

			</tr>
			<tr>
				<td><label><div class="color">個人カラー：</div><input type="color"
						name="color" id="color" style="height:30px">
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" value="新規登録" class="custom-submit"> <span
					id="error_message"  style="right:27%"> </span>

					<p class="error">
						<c:out value="${message.title}" />
						<c:out value="${message.message}" />
					</p></td>
			</tr>
		</table>
	</form>
	<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
	<script src="js/accountRegist.js"></script>
	<script src="js/submitConfirm.js"></script>
</body>
</html>