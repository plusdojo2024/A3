<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>マイページ | 名刺管理</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/mypage.css">

</head>

<body>

	<div class="wrapper">
		<!-- ヘッダー（ここから） -->
		<h1 id="logo">
			<a href="/simpleBC/HomeServlet"><img src="images/logo_Blue.png"
				width="260" height="60" alt="名刺管理"></a>
		</h1>
		<ul id="nav">
			<li><a href="/simpleBC/HomeServlet">ホーム</a></li>
			<li><a href="/simpleBC/SearchServlet">検索</a></li>
			<li><a href="/simpleBC/RegistServlet">登録</a></li>
			<li><a href="/simpleBC/TweetServlet">ツイート</a></li>
			<li><a href="/simpleBC//DmServlet">DM</a></li>
			<li><a href="/simpleBC/MypageServlet">マイページ</a></li>
			<li><a href="/simpleBC/LoginServlet" id="logout">ログアウト</a></li>

		</ul>
		<!-- ヘッダー（ここまで） -->
		<!-- メイン（ここから） -->
		<h2>マイページ</h2>
		<form id="regist_form" method="post" action="/simpleBC/MypageServlet">

			<table>
				<div class="myIcon">
					<!-- モーダル本体 -->
					<div class="modal js-modal">
						<div class="modal-container">
							<!-- モーダルを閉じるボタン -->
							<div class="modal-close js-modal-close">×</div>
							<!-- モーダル内部のコンテンツ -->
							<div class="modal-content" id="select_icons">
								<p>アイコンを選択してください</p>
								<div>
									<ul id="icon_list">
										<li class="icon_items" title="アイコンナンバー：0"><img
											src="images/icons/icon_0.png"></li>
										<li class="icon_items" title="アイコンナンバー：1"><img
											src="images/icons/icon_1.png"></li>
										<li class="icon_items" title="アイコンナンバー：2"><img
											src="images/icons/icon_2.png"></li>
										<li class="icon_items" title="アイコンナンバー：3"><img
											src="images/icons/icon_3.png"></li>
										<li class="icon_items" title="アイコンナンバー：4"><img
											src="images/icons/icon_4.png"></li>
										<li class="icon_items" title="アイコンナンバー：5"><img
											src="images/icons/icon_5.png"></li>
										<li class="icon_items" title="アイコンナンバー：6"><img
											src="images/icons/icon_6.png"></li>
										<li class="icon_items" title="アイコンナンバー：7"><img
											src="images/icons/icon_7.png"></li>
										<li class="icon_items" title="アイコンナンバー：8"><img
											src="images/icons/icon_8.png"></li>
										<li class="icon_items" title="アイコンナンバー：9"><img
											src="images/icons/icon_9.png"></li>
										<li class="icon_items" title="アイコンナンバー：10"><img
											src="images/icons/icon_10.png"></li>
										<li class="icon_items" title="アイコンナンバー：11"><img
											src="images/icons/icon_11.png"></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					<button type="button" class="imageButton" id="imageChange"
						title="アイコンを変更する">
						<img src="${myProfile.iconSrc}" id="myimage" name=myIcon>
					</button>
					<input type="hidden" name="iconURL" value="${myProfile.iconSrc}"
						id="icon_URL">
				</div>
				<tr>
					<td><label>ユーザーID<br> <input type="text"
							name="number" value="${userID}" readonly="readonly"
							style="background-color: lightgray">
					</label></td>
					<td><label>郵便番号<br> <input type="text"
							name="zipcode" value="${myProfile.zipcode}">
					</label></td>
				</tr>
				<tr>
					<td><label>会社名<br> <input type="text"
							name="company" value="${myProfile.company}">
					</label></td>
					<td><label>住所<br> <input type="text"
							name="address" value="${myProfile.address}">
					</label></td>
				</tr>
				<tr>
					<td><label>部署名<br> <input type="text"
							name="department" value="${myProfile.department}">
					</label></td>
					<td><label>電話番号<br> <input type="text"
							name="phone" value="${myProfile.phone}">
					</label></td>
				</tr>
				<tr>
					<td><label>役職名<br> <input type="text"
							name="position" value="${myProfile.position}">
					</label></td>
					<td><label>FAX番号<br> <input type="text"
							name="fax" value="${myProfile.fax}">
					</label></td>
				</tr>
				<tr>
					<td><label>氏名（※必須）<br> <input type="text"
							name="name" value="${myProfile.name}">
					</label></td>
					<td><label>メールアドレス<br> <input type="text"
							name="email" value="${myProfile.email}">
					</label></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" id="register"
						name="submit" value="登録"> <input type="reset" name="reset"
						value="リセット"> <span id="error_message"></span></td>
				</tr>
			</table>
		</form>
		<!-- メイン（ここまで） -->
		<!-- フッター（ここから） -->
		<div id="footer">
			<p>&copy;Copyright plusDOJO(SE plus). All rights reserved.</p>
		</div>
		<!-- フッター（ここまで） -->
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="js/mypage.js"></script>
	<script src="js/logout.js"></script>
</body>

</html>