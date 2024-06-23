<!--家族登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家族・代表者登録</title>
<link rel="stylesheet" href="css/main.css">
<body>
	<h2>新規登録</h2>
	<form id="form" method="post"
		action="/A3//FamilyRegistServlet" enctype="multipart/form-data">
		<table>

			<tr>
				<td><label>アイコン画像<br> <input type="file"
						name="icon" id="icon">
				</label>
				<p id="error_icon" style="display: none">入力された値が正しくありません</p>
				</td>
				<td><label>家族名<br> <input type="text"
						name="family_name" maxlength="20">
				</label></td>

			</tr>
			<tr>
				<td><label>個人カラー<br> <input type="color"
						name="color">
				</label></td>
				<td><label>メールアドレス<br> <input type="email"
						name="mail" maxlength="20">
				</label></td>
			<tr>
			<tr>
				<td><label>家族パスワード<br> <input type="password"
						name="family_pass" id="f_pass" maxlength="20">
						<span class="pass_warn">8文字以上20文字以内の大文字を1文字以上含む半角英数字を入力してください<br></span>
				</label></td>
			<tr>
			<tr>
				<td><label>代表者ユーザー名<br> <input type="text"
						name="user_name" maxlength="20">
				</label></td>
			<tr>
			<tr>
				<td><label>代表者個人パスワード<br> <input type="password"
						name="user_pass" id="u_pass" maxlength="20">
						<span class="pass_warn">8文字以上20文字以内の大文字を1文字以上含む半角英数字を入力してください<br></span>
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" value="新規作成" onClick="return Check()"> <span
					id="error_message"></span>
					<p class="error"><c:out value="${message.title}" />  <c:out value="${message.message}" /></p>
					</td>

			</tr>

		</table>
	</form>
	<script src="js/passCheck.js"></script>
	<script src="js/fileSizeCheck.js"></script>
	<script src="js/submitConfirm.js"></script>
</body>
</html>