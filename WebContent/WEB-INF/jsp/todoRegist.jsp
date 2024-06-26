<!--項目登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>家事登録 | F&amp;M</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/todoRegist.css">
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
</head>
<body>
<div class="body">
<br><br><br><br><br><br>
	<div class="sidebar">
        <a href="/A3/HomeServlet">ホーム</a>
        <a href="/A3/TodoServlet">やることリスト</a>
        <a href="/A3/NoteServlet">引継ぎノート</a>
        <a href="/A3/AlbumServlet">アルバム</a>
        <a href="/A3/RewardsServlet">ご褒美</a>
    </div>
<main>
<br>
	<h1 class="title_box">家事登録</h1>
	<div class="t_main">
	<p>${message}</p>
	<p><sup style="color: red;">*</sup>は入力必須項目です。</p>
	<table>
	<form method="post" action="/A3/TodoRegistServlet" id="form">
	<tr>
	<td>
	<p>家事名：</p>
	</td>
	<td class="view_box">
	<input type="text" name="task" placeholder="例：洗濯物を干す" required id="task"><sup style="color: red;">*</sup><br><br>
	</td>
	</tr>
	<tr>
	<td>
	<p>カテゴリー：</p>
	</td>
	<td class="view_box">
	<select name="category" required class="select_box">
	<option value="">カテゴリーを選択してください</option>
	<option value="洗濯">洗濯</option>
	<option value="ごみ捨て">ごみ捨て</option>
	<option value="掃除">掃除</option>
	<option value="料理">料理</option>
	<option value="買い物">買い物</option>
	<option value="日用品の補充">日用品の補充</option>
	<option value="子育て">子育て</option>
	<option value="その他">その他</option>
	</select><sup style="color: red;">*</sup><br><br>
	</td>
	</tr>
	<tr>
	<td>
	<p>ポイント：</p>
	</td>
	<td class="view_box">
	<select name="give_point" class="select_box">
	<script>
	var i;
	for(i=1; i<11; i+=1) {
		document.write('<option value="'+i+'">'+i+'pt</option>');
	}
	</script>
	</select><sup style="color: red;">*</sup><br><br>
	</td>
	</tr>
	<tr>
	<td>
	<p>メモ：</p>
	</td>
	<td class="view_box">
	<textarea name="memo">${memo}</textarea><br><br>
	</td>
	</tr>
	<tr>
	<td><span id="error_message" style="position:absolute; right:65%;"> </span>
	<input type="submit" name="regist" value="登録" class="custom-submit" onClick="return Rcheck()">
	</td>
	</tr>
	</form>
	</table>
	</div>

</main>
<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
<script src="js/todo.js"></script>
</div>
</body>
</html>