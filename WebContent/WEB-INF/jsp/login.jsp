<!--家族登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
</head>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/login.css">
<body>
	<div>
		<img src="images/login_logo.png" class="left" >
	</div>
	<div class="right">
		<div class="form">
			<form id="create_form" method="post" action="/A3//LoginServlet"
				>
				<table>
					<tr>

						<td class="mail"><label>メールアドレス <br><input type="text" placeholder="aaa@example.com" style="width:300px; height:30px"
								name="mail"  id="mail" required>
						</label></td>
						<tr>

					<tr>
				<td class="family_pass"><label>家族パスワード<br><input type="password" placeholder="password" style="width:300px; height:30px"
								name="family_pass" id="family_pass" required>
				</label></td>

					<tr>

					<tr>
				<td class="user_name"><label>ユーザー名 <br><input type="text" placeholder="山田太郎" style="width:300px; height:30px"
								name="user_name" id="user_name" required>
				</label></td>

					<tr>

					<tr>
				<td class="pass"><label>個人パスワード<br> <input type="password" placeholder="password" style="width:300px; height:30px"
								name="user_pass" required>
				</label></td>

					<tr>

					<tr>
				<td><input type="submit" name="submit" value="ログイン" class="custom-submit"> <span
							id="error_message"></span>
					<p class="error">
						<c:out value="${message.title}" />
						<c:out value="${message.message}" />
					</p></td>
			</tr>

		</table>
	</form>
	</div>
	<button class="new_account"
            onclick="window.location.href = '/A3/FamilyRegistServlet';">新規アカウント作成<br></button>

</div>
</body>
</html>