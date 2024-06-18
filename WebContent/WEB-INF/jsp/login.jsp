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
<body>
	<form id="create_form" method="post"
		action="/A3//FamilyRegistServlet" enctype="multipart/form-data">
		<table>

			<tr>

				<td><label>メールアドレス<br> <input type="text"
						name="mail">
				</label></td>
			<tr>
			<tr>
				<td><label>家族パスワード<br> <input type="password"
						name="family_pass">
				</label></td>
			<tr>
			<tr>
				<td><label>ユーザー名<br> <input type="text"
						name="user_name">
				</label></td>
			<tr>
			<tr>
				<td><label>個人パスワード<br> <input type="password"
						name="user_pass">
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" value="ログイン"> <span
					id="error_message"></span>
					<p class="error"><c:out value="${message.title}" />  <c:out value="${message.message}" /></p>
					</td>

			</tr>

		</table>
	</form>
</body>
</html>