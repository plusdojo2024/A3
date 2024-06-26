<!--ホーム-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ホーム</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/home.css">

<div class="nav">
	<div class="left_icons">
		<c:set var="icon" value="${dbUser.icon}"></c:set>
		<div class="green_box">
			<img src="${icon}" id="user_icon">
		</div>
		<div class="green_box">
			<p style="font-size:30px;"><c:out value="${dbUser.havePoint}" /> P</p>
		</div>
	</div>

	<div class="home_logo">
		<a href="/A3/HomeServlet"><img src="images/FM1.png" width="90%"
			height="90%"></a>
	</div>

	<div class="right_buttons">
		<button class="account-management"
			onclick="window.location.href = '/A3/AccountServlet';">
			アカウント管理<br>
			<img src="images/ic007.png" width="65px" height="65px">
		</button>
		<button class="logout"
			onclick="window.location.href = '/A3/LogoutServlet';">
			ログアウト<br>
			<img src="images/935.png" width="65px" height="65px">
		</button>

	</div>

</div>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
       	<div class="sidebar">
		<a href="/A3/HomeServlet">ホーム</a> <a href="/A3/TodoServlet">やることリスト</a>
		<a href="/A3/NoteServlet">引継ぎノート</a> <a href="/A3/AlbumServlet">アルバム</a>
		<a href="/A3/RewardsServlet">ご褒美</a>
	</div>

	<br>
	<br>
	<br>
	<br>
	<h1 class="box">当日リスト</h1>
	<br>

<h2 class="box2">今日やること</h2>
<div class="scrollable ">
<table class="content">
    <c:forEach var="todo" items="${todayList}">
        <tr>
            <c:choose>
                <c:when test="${todo.complete == 0}">
                    <td>
                        <form id="form-${todo.todoId}" method="post" action="/A3/HomeServlet">
                            <input type="hidden" name="todo_id" value="${todo.todoId}">
                            <input type="submit" value="${todo.task}" id="task" onclick="return confirmDialog()">
                        </form>
                    </td>
                </c:when>
                <c:when test="${todo.complete == 1}">
                    <td id="line" class="box_task">${todo.task}</td>
                </c:when>
            </c:choose>
        </tr>
    </c:forEach>
</table>
</div>

	<h2 class="box3">前日忘れたこと</h2>
 	<table class="content1">
		<c:forEach var="task" items="${yesterdayList}">
			<tr>
				<td class="yes_box"><c:out value="${task.task}" /></td>
			</tr>
		</c:forEach>
	</table>

<!-- 	<p class="y_box">キッチン掃除</p>
	<p class="y_box1">ゴミ出し</p> -->

 	<h2 class="box4">前日の引継ぎノート</h2>
	<div class="notes">

	<c:if test="${note == null}">
			<p>前日の引継ぎノートのデータはありません。</p>
		</c:if>
		<c:if test="${note != null }">
			<p><c:out value="${note.note}" /></p>
		</c:if>
<!-- 	<p>　　昨日の朝、ごみ捨て忘れてたから娘が代わりに捨ててくれてたよ(^▽^)/</p> -->

	</div>
</body>

<script>
function confirmDialog() {
    if (confirm('家事が完了しましたか？')) {
        // OKボタンがクリックされた場合の処理
        alert('家事が完了したため、ポイントが付与されました！');
    } else {
        // キャンセルボタンがクリックされた場合の処理
        alert('キャンセルされました。');
    }
}
</script>


</html>