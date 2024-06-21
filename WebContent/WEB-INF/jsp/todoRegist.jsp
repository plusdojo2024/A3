<!--項目登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>項目登録 | F&amp;M</title>
<link rel="stylesheet" href="css/main.css">
<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${myUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${myUser.havePoint}" />
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
<main>
	<h1>項目登録</h1>
	<p>${message}</p>
	<p><sup style="color: red;">*</sup>は入力必須項目です。</p>
	<form method="post" action="/A3/TodoRegistServlet">
	<sup style="color: red;">*</sup><input type="text" name="task" placeholder="例：洗濯"><br>
	<sup style="color: red;">*</sup>
	<select name="category" required>
	<option value="">カテゴリーを選択してください</option>
	<option value="洗濯">洗濯</option>
	<option value="ごみ捨て">ごみ捨て</option>
	<option value="掃除">掃除</option>
	<option value="料理">料理</option>
	<option value="買い物">買い物</option>
	<option value="日用品の補充">日用品の補充</option>
	<option value="子育て">子育て</option>
	<option value="その他">その他</option>
	</select><br>
	<sup style="color: red;">*</sup>
	<select name="give_point">
	<script>
	var i;
	for(i=1; i<11; i+=1) {
		document.write('<option value="'+i+'">'+i+'pt</option>');
	}
	</script>
	</select><br>
	<textarea name="memo">${memo}</textarea>
	<input type="submit" name="regist" value="登録">

	</form>
</main>
<script src="js/todoedit.js"></script>
</body>
</html>