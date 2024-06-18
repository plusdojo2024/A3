<!--項目登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>項目登録 | F&amp;M</title>
<link rel="stylesheet" href="css/main.css">
</head>
<body>
<header>
    <div class="nav">
        <div class="left_icons">
            <div class="green_box">父</div>
            <div class="green_box">ポイント</div>
        </div>

        <div class="home_logo">
            <a href="home.html">F&amp;M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management"><a href="/A3/AccountServlet">アカウント管理</a></button>
            <button class="logout"><a href="/A3/LogoutServlet">ログアウト</a></button>
        </div>

    </div>
</header>
<main>
	<h1>項目登録</h1>
	<p>${message}</p>
	<p><sup style="color: red;">*</sup>は入力必須項目です。</p>
	<form method="post" action="/A3/TodoRegistServlet">
	<sup style="color: red;">*</sup><input type="text" name="task" placeholder="例：洗濯"><br>
	<sup style="color: red;">*</sup>
	<select name="category" required>
	<option value="">カテゴリーを選択してください</option>
	<option value="laundry">洗濯</option>
	<option value="garbage">ごみ捨て</option>
	<option value="cleaning">掃除</option>
	<option value="cooking">料理</option>
	<option value="shopping">買い物</option>
	<option value="refill">日用品の補充</option>
	<option value="child_rearing">子育て</option>
	<option value="others">その他</option>
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
	<textarea>${memo}</textarea>
	<input type="submit" name="regist" value="登録">

	</form>
</main>
</body>
</html>