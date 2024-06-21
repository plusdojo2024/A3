<!--家事分担登録-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!--これを追記-->
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>家事分担登録</title>
    <link rel="stylesheet" href="css/main.css">
    <!-- <link rel="stylesheet" href="css/shareRegist.css"> -->

    <div class="nav">
        <div class="left_icons">
        <c:set var="icon" value="${myUser.icon}" />
            <div class="green_box"><img src="${icon}" id="user_icon"></div>
            <div class="green_box"><c:out value="${myUser.havePoint}" /></div>
        </div>

        <div class="home_logo">
            <a href="/A3/HomeServlet">F&M</a>
        </div>

        <div class="right_buttons">
            <button class="account-management"><a href="/A3/AccountServlet">アカウント管理</a></button>
            <button class="logout"><a href="/A3/LogoutServlet">ログアウト</a></button>
        </div>

    </div>
</head>
<body>
	<form action="/A3/ShareRegistServlet" method="post"></form>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>


    <div class= "title_box">分担登録</div>

    <div class= "main_box"><input type="text" name="task" size="20" maxlength="20" value="項目" required></div>

    <div >
    	<label for="uid">担当者</label>
    	<select id="uid" name="uid" required>
    		<c:forEach var="user" items="${userList}">
    			<option value="${user.uid}">${uset.name}</option>
    		</c:forEach>
    	</select>
    </div>

    <div class= "main_box">繰り返し<select name="repeat" value="繰り返し"  onchange="enableNextDropdown('day')">
        <option>繰り返さない</option>
        <option>繰り返す</option>
        </select></div>

    <div class= "main_box" >曜日</div>

    <lablel for="monday">月曜日</lablel>
    <input type="checkbox" id="monday" name="monday" value="1"><br>
    <lablel for="tuesday">火曜日</lablel>
    <input type="checkbox" id="tuesday" name="tuesday" value="1"><br>
    <lablel for="wednesday">水曜日</lablel>
    <input type="checkbox" id="wednesday" name="wednesday" value="1"><br>
    <lablel for="thuresday">木曜日</lablel>
    <input type="checkbox" id="thuresday" name="thuresday" value="1"><br>
    <lablel for="friday">金曜日</lablel>
    <input type="checkbox" id="friday" name="friday" value="1"><br>
    <lablel for="saturday">土曜日</lablel>
    <input type="checkbox" id="saturday" name="saturday" value="1"><br>
    <lablel for="sunday">日曜日</lablel>
    <input type="checkbox" id="sunday" name="sunday" value="1"><br>



    <div class= "main_box">いつまで<input type="date"></div>

    <div class= "submit_box"><input type="submit" value="登録" name="regist" ></div>

	<script>
        function enableNextDropdown(nextDropdownId) {
            var nextDropdown = document.getElementById(nextDropdownId);
            nextDropdown.disabled = false;
        }
    </script>
    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>
</body>
</html>