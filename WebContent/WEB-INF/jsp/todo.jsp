<!--やることリスト-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>やることリスト | F&amp;M</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/todo.css">

<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${dbUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<c:out value="${dbUser.havePoint} P" />
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
</div>
<main>
	<br><br><br><br><br><br><br>
	<h1 class="title_box">やることリスト</h1>
	<p class="message">${message}</p>

	<h3 class="c_h">カテゴリー</h3>
	<div class="ul">
	<ul>
		<a href="#wash" class="a"><li>洗濯</li></a>
		<a href="#garbage" class="a"><li>ごみ捨て</li></a>
		<a href="#clean" class="a"><li>掃除</li></a>
		<a href="#cooking" class="a"><li>料理</li></a>
		<a href="#shopping" class="a"><li>買い物</li></a>
		<a href="#plus" class="a"><li>日用品の補充</li></a>
		<a href="#child" class="a"><li>子育て</li></a>
		<a href="#other" class="a"><li>その他</li></a>
	</ul>
	</div>

	<div class="tl">
	<h3 id="wash">洗濯</h3>
	<c:forEach var="e" items="${todowash}">
	<c:set var="task" value="${e.task}"></c:set>
	<ul>
	<li>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button>
	</li>
	</ul>
	</c:forEach>

	<h3 id="garbage">ごみ捨て</h3>
	<c:forEach var="e" items="${todogarbage}">
	<c:set var="task" value="${e.task}"></c:set>
	<ul>
	<li>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button>
	</li>
	</ul>
	</c:forEach>

	<h3 id="clean">掃除</h3>
	<c:forEach var="e" items="${todoclean}">
	<c:set var="task" value="${e.task}"></c:set>
	<ul>
	<li>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button>
	</li>
	</ul>
	</c:forEach>

	<h3 id="cooking">料理</h3>
	<c:forEach var="e" items="${todocooking}">
	<c:set var="task" value="${e.task}"></c:set>
	<ul class="c_r">
	<li>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button>
	</li>
	</ul>
	</c:forEach>

	<h3 id="shopping">買い物</h3>
	<c:forEach var="e" items="${todoshopping}">
	<c:set var="task" value="${e.task}"></c:set>
	<ul class="c_s">
	<li>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button>
	</li>
	</ul>
	</c:forEach>

	<h3 id="plus">日用品の補充</h3>
	<c:forEach var="e" items="${todoplus}">
	<c:set var="task" value="${e.task}"></c:set>
	<ul>
	<li>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button>
	</li>
	</ul>
	</c:forEach>

	<h3 id="child">子育て</h3>
	<c:forEach var="e" items="${todochild}">
	<c:set var="task" value="${e.task}"></c:set>
	<ul>
	<li>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button>
	</li>
	</ul>
	</c:forEach>

	<h3 id="other">その他</h3>
	<c:forEach var="e" items="${todoother}">
	<c:set var="task" value="${e.task}"></c:set>
	<ul>
	<li>
		<button onclick="window.location.href= '/A3/TodoMemoServlet?name=${task}'" class="task_btn"><c:out value="${task}" /></button><br>
		<button onclick="window.location.href= '/A3/TodoEditServlet?name=${task}'" class="edit_btn">編集・削除</button>
	</li>
	</ul>
	</c:forEach>
	</div>

	<button onclick="window.location.href= '/A3/TodoRegistServlet'"  class="r_btn">家事登録</button><br>
	<button onclick="window.location.href= '/A3/CalendarServlet'"  class="c_btn">カレンダー</button><br>
</main>
<img class="back-button" src="images/603_1.png" onClick="history.back();return false;">
<a href="#" class="top"><img src="images/top.png" class="top_btn"></a>
<script src="js/main.js"></script>
</body>
</html>