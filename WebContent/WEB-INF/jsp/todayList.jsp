<!--当日のリスト-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>当日やることリスト | F&amp;M</title>
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
            <a href="home.png"></a>
            <a href="/A3/HomeServlet">F&amp;M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management"><a href="/A3/AccountServlet">アカウント管理</a></button>
            <button class="logout"><a href="/A3/LogoutServlet">ログアウト</a></button>
        </div>

    </div>
</header>

<h2>当日やることリスト</h2>
	<table>
		<tr>
			<th>1.</th>
			<th>${todayList}</th>
			<th><input type="checkbox" name="checkbox" value="1"></th>
		</tr>
		<tr>
			<th>2.</th>
			<th>${todayList}</th>
			<th><input type="checkbox" name="checkbox" value="2"></th>
		</tr>
	</table>

	<table>
		<tr>
			<th>1.</th>
			<th>${yesterdayList}</th>
		</tr>
		<tr>
			<th>2,</th>
			<th>${yesterdayList}</th>
		</tr>
	</table>

<h2>前日の引継ぎノート</h2>
<p>${notesContent}</p>

<h2>ご褒美リクエスト</h2>
<p>${rewardsContent}</p>

<button type = "button"><a href="/A3/HomeServlet"><img src="/images/batu.png"></a></button>

</body>
</html>