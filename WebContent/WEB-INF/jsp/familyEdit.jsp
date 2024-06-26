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
<link rel="stylesheet" href="css/familyEdit.css">
<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${dbUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${dbUser.havePoint}pt" />
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
	<br><br><br><br><br><br>
 	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
<h1 class="box">家族情報変更</h1>
<form id="form" method="post"
		action="/A3//FamilyEditServlet">
		<table class="tb">
		<c:set var="family_name" value="${user.familyName}" />
		<c:set var="mail" value="${user.mail}" />

			<tr>
				<td><label>家族名<br> <input type="text"
						name="family_name" value="${family_name}" maxlength="20" style="width:300px; height:30px">
				</label></td>

			</tr>
			<tr>
				<td><label>メールアドレス<br> <input type="email"
						name="mail"  value="${mail}" maxlength="64" style="width:300px; height:30px">
				</label></td>
			<tr>
			<tr>
				<td><label>家族パスワード<br> <input type="password"
						name="family_pass" maxlength="20" style="width:300px; height:30px"><span class="pass_warn">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください<br></span>
				</label></td>
			<tr>
			<tr>
				<td><label>家族パスワード確認<br> <input type="password"
						id = "pass_check" maxlength="20" name="pass_check" style="width:300px; height:30px"><span class="pass_warn">8文字以上20文字以内の大文字を一文字以上含む半角英数字を入力してください<br></span>
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" class="custom-submit" name="submit" value="変更"> <span
					id="error_message"  style="right:24%"></span>
					<p class="error"><c:out value="${message.title}" />  <c:out value="${message.message}" /></p>
					</td>
			</tr>
		</table>
	</form>
	<script src="js/familyEdit.js"></script>

	<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">

</body>
</html>