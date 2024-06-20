<!--引継ぎノート履歴-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>履歴</title>
</head>
<body>
<h2>履歴</h2>
<form id="create_form" method="post"
		action="/A3/NoteServlet" enctype="multipart/form-data">
		<table>
		<tr>
			<td><label><input type="text" name="noteDate" value="${noteDate}"><br>
				</label></td>
			</tr>
			<tr>
			<td><label><input type="text" name="title" value="${title}"><br>
				</label></td>
			</tr>
			<tr>
				<td><label><input type="text" name="memo" value="${memo}"><br>
				</label></td>

			</tr>
			<tr>
				<td><a href="/A3/NoteEditServlet"><input type="submit" name="submit" value="更新"></a>

					</td>
				<td><a href="/A3/NoteEditServlet"><input type="submit" name="submit" value="削除"> </a>

					</td>
			</tr>

		</table>
	</form>


</body>
</html>