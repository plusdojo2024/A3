<!-- アカウント管理-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>アカウント管理画面</title>
</head>
<body>
 <h1>アカウント管理画面</h1>
 <c:if test="${user.role==1}"> <button id="family_regist">家族登録</button></c:if>　<button id="user_edit">個人情報変更</button><br><br>
 <c:if test="${user.role==1}"><button id="family_edit">家族情報変更</button></c:if>　<c:if test="${user.role==1}"><button id="user_delete">アカウント削除</button></c:if>
<script src="js/account.js"></script>
</body>
</html>