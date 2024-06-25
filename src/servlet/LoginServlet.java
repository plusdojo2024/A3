
package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FamilyDAO;
import dao.UsersDAO;
import logic.HashLogic;
import logic.IpCheckLogic;
import model.Family;
import model.Message;
import model.UserIp;
import model.Users;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String userName = request.getParameter("user_name");
		String familyPass = request.getParameter("family_pass");
		String pw = request.getParameter("user_pass");

		HashLogic hashLogic = new HashLogic();
		String hashMail = hashLogic.getHash(mail);

		//------------最初にipアドレスチェック-------------------//

		//ipアドレス取得
		String ipAddress = request.getRemoteAddr();

		//ipアドレス表示(デバッグ用)
		System.out.println("ipアドレス：" + ipAddress);
		//ipアドレスのデータ型表示(デバッグ用)
		//System.out.println("ipアドレスデータ型：" + ((Object) ipAddress).getClass().getSimpleName());

		// ログイン処理を行う
		FamilyDAO fDao = new FamilyDAO();
		Family family = new Family();

		UsersDAO uDao = new UsersDAO();
		IpCheckLogic ipLogic = new IpCheckLogic();

		UserIp user = new UserIp();//インスタンス化と同時に現在時刻がdateに入る
		user.setIpAddress(ipAddress);
		//user.countPlus();//ログイン回数+1

		// アプリケーションスコープの保存領域を確保
		ServletContext application = this.getServletContext();

		// アプリケーションスコープからインスタンスを取得
		@SuppressWarnings("unchecked")
		List<UserIp> ipList = (List<UserIp>) application.getAttribute("ipList");

		//リストが空なら
		if (ipList == null) {
			ipList = new ArrayList<UserIp>();
			user.countPlus();//ログイン回数+1

			ipList.add(user);
			application.setAttribute("ipList", ipList);

		} else {//一応リストには値が入ってる
			//リストにこのipアドレスが存在するかチェック
			if (ipLogic.listCheck(user, ipList)) {

				//ipアドレスが一致するユーザーを格納
				UserIp listUser = ipLogic.searchUser(user, ipList);

				//現在時刻の1時間前を格納
				LocalDateTime yesterday = user.getDate().minusHours(1);

				//最終アクセスから1時間以上経過していたら
				if (listUser.getDate().isEqual(yesterday) || listUser.getDate().isBefore(yesterday)) {
					//リストから削除
					ipLogic.removeList(user, ipList);

					//リストに再登録
					ipList.add(user);
					application.setAttribute("ipList", ipList);
				} else {//1時間経過していなければ
					//最終アクセス時間を更新
					user.setDate(LocalDateTime.now());
					user.setCount(ipLogic.userCountPlus(user, ipList));
					//再セット
					ipLogic.updateList(user, ipList);
					application.setAttribute("ipList", ipList);
				}
			} else {//一致するユーザーがいない
				//リストに追加
				user.countPlus();//ログイン回数+1
				ipList.add(user);
				application.setAttribute("ipList", ipList);
			}
		}

		System.out.println("カウント：" + ipLogic.userGetCount(user, ipList));

		if (ipLogic.userGetCount(user, ipList) >= 20) {
			// アカウントロック

			Message msg = new Message();
			msg.setTitle("不正アクセスを検知しました。");
			msg.setMessage("24時間ログインをロックします。");

			// リクエストスコープに、タイトル、メッセージを格納する
			request.setAttribute("message", msg);

			// ログインページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//メールアドレスの存在をチェック
		if (fDao.familyCheck(hashMail)) {//存在する

			//メールアドレスを元にソルト値とパスワードを持ってきて比較

			family = fDao.searchByMail(hashMail);//DB内のパスワードとソルトを格納

			//DBから持ってきたパスワードと今回入力されたパスワードを比較
			if (hashLogic.checkHash(familyPass, family.getFamilyPass(), family.getFamilySalt())) {
				//一致したら家族内の個人認証へ移行

				int familyId = fDao.searchId(hashMail);

				//一致する名前がいるかチェック
				if (uDao.userCheck(familyId, userName)) {
					//家族内に一致する名前がいる
					Users dbUser = new Users();
					Users myUser = new Users();


					dbUser = uDao.loginSearch(familyId, userName);
					String salt = dbUser.getUserSalt();
					String dbPass = dbUser.getPw();

					//パスワード・名前・メールアドレスのハッシュ化前の値を入れる
					myUser.setFamilyId(dbUser.getFamilyId());
					myUser.setName(userName);
					myUser.setUid(dbUser.getUid());
					myUser.setPw(pw);
					myUser.setMail(mail);
					myUser.setFamilyPass(familyPass);

					String familyName = fDao.getFamilyName(familyId);
					myUser.setFamilyName(familyName);

					//パスワードチェック
					if(hashLogic.checkHash(pw, dbPass, salt)) {
						//ログイン成功

						// セッションスコープにユーザー情報を保存する
						HttpSession session = request.getSession();
						//いろいろハッシュ化されたDB内と同じデータを保存
						session.setAttribute("dbUser", dbUser);

						//いろいろハッシュ化される前の値を保存
						session.setAttribute("user", myUser);

						response.sendRedirect("/A3//HomeServlet");
						return;
					}else {
						//パスワードが一致しない
						Message msg = new Message();
						msg.setTitle("ログイン失敗！");
						msg.setMessage("個人パスワードが一致しません");
						// リクエストスコープに、タイトル、メッセージを格納する
						request.setAttribute("message", msg);

						// ログインページにフォワードする
						RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
						dispatcher.forward(request, response);
						return;
					}

				} else {
					//一致する名前がいない

					Message msg = new Message();
					msg.setTitle("ログイン失敗！");
					msg.setMessage("家族内に一致する名前が見つかりませんでした。");
					// リクエストスコープに、タイトル、メッセージを格納する
					request.setAttribute("message", msg);

					// ログインページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					dispatcher.forward(request, response);
				}

			} else {
				//一致しなかったら

				Message msg = new Message();
				msg.setTitle("ログイン失敗　");
				msg.setMessage("パスワードが一致しません。");

				// リクエストスコープに、タイトル、メッセージを格納する
				request.setAttribute("message", msg);

				// ログインページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
				return;

			}

		} else {//メールアドレスが見つからない
			Message msg = new Message();
			msg.setTitle("入力されたメールアドレスが見つかりませんでした。");
			msg.setMessage("メールアドレスを確認してください。");

			// リクエストスコープに、タイトル、メッセージを格納する
			request.setAttribute("message", msg);

			// ログインページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

	}
}
