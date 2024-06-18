<!--家族登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家族・代表者登録</title>
</head>
<body>
	<h2>新規登録</h2>
	<form id="create_form" method="post"
		action="/A3//FamilyRegistServlet" enctype="multipart/form-data">
		<table>

			<tr>
				<td><label>アイコン画像<br> <input type="file"
						name="icon" >
				</label></td>
				<td><label>家族名<br> <input type="text"
						name="family_name">
				</label></td>

			</tr>
			<tr>
				<td><label>個人カラー<br> <input type="color"
						name="color">
				</label></td>
				<td><label>メールアドレス<br> <input type="text"
						name="mail">
				</label></td>
			<tr>
			<tr>
				<td><label>家族PASS<br> <input type="password"
						name="family_pass">
				</label></td>
			<tr>
			<tr>
				<td><label>代表者ユーザー名<br> <input type="text"
						name="user_name">
				</label></td>
			<tr>
			<tr>
				<td><label>代表者個人PASS<br> <input type="password"
						name="user_pass">
				</label></td>
			<tr>
			<tr>
				<td><input type="submit" name="submit" value="新規作成"> <span
					id="error_message"></span>
					<p class="error"><c:out value="${message.title}" />  <c:out value="${message.message}" /></p>
					</td>

			</tr>

		</table>
	</form>
</body>
</html>