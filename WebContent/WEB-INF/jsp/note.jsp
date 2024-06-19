<!--引継ぎノート-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>引継ぎノート</title>
</head>
<body>
<h2>引継ぎノート</h2><br>
	<form id="create_form" method="post"
		action="/A3/NoteServlet" enctype="multipart/form-data">
		<table>

			<tr>
			<td><label>タイトル<br> <input type="text"
						name="memotittle">
				</label></td>
			</tr>
			<tr>
				<td><label>メモ<br> <input type="text"
						name="memo">
				</label></td>

			</tr>
			<tr>
				<td><label>写真<br> <input type="file"
						name="photo" >
				</label></td>
			</tr>

			<tr>
				<td><a href="/A3/NoteEditServlet"><input type="submit" name="submit" value="登録"> <span
					id="error_message"></span></a>
					<p class="error"><c:out value="${message.title}" />  <c:out value="${message.message}" /></p>
					</td>

			</tr>

		</table>
	</form>

</body>
</html>