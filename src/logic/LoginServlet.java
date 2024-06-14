/*
package logic;

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

import dao.IdpwDAO;
import logic.HashLogic;
import logic.IpCheckLogic;
import model.Idpw;
import model.LoginUser;
import model.Result;
import model.UserIp;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
/*	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ログインページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");

		//------------最初にipアドレスチェック-------------------//

		//ipアドレス取得
		String ipAddress = request.getRemoteAddr();

		//ipアドレス表示(デバッグ用)
		System.out.println("ipアドレス：" + ipAddress);
		//ipアドレスのデータ型表示(デバッグ用)
		//System.out.println("ipアドレスデータ型：" + ((Object) ipAddress).getClass().getSimpleName());

		// ログイン処理を行う
		IdpwDAO iDao = new IdpwDAO();
		Idpw myIdpw = new Idpw();
		myIdpw.setId(id);
		myIdpw.setPw(pw);

		IdpwDAO myDao = new IdpwDAO();
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

				//現在時刻の24時間前を格納
				LocalDateTime yesterday = user.getDate().minusHours(24);

				//最終アクセスから24時間以上経過していたら
				if (listUser.getDate().isEqual(yesterday) || listUser.getDate().isBefore(yesterday)) {
					//リストから削除
					ipLogic.removeList(user, ipList);

					//リストに再登録
					ipList.add(user);
					application.setAttribute("ipList", ipList);
				} else {//24時間経過していなければ
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

		System.out.println("カウント："+ipLogic.userGetCount(user, ipList));

		if(ipLogic.userGetCount(user, ipList)>=20) {
			// アカウントロック

			// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
			request.setAttribute("result",
					new Result("不正アクセスを検知しました。", "24時間ログインをロックします。", "/simpleBC/LoginServlet"));

			// ログインページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
			return;
		}

		//入力されたidがデータベースに存在するかチェック
		if (myDao.idCheck(myIdpw)) {
			Idpw searchUser = iDao.search(myIdpw);
			String salt = searchUser.getStrSalt();
			String dbPass = searchUser.getPw();

			HashLogic hashPw = new HashLogic();

			if (hashPw.checkHash(pw, dbPass, salt)) {
				//ログイン成功

				myIdpw.setPw(pw);
				// セッションスコープにIDを格納する
				HttpSession session = request.getSession();
				session.setAttribute("id", new LoginUser(id));

				// メニューサーブレットにリダイレクトする
				response.sendRedirect("/simpleBC/HomeServlet");
			} else {
				// ログイン失敗

				// リクエストスコープに、タイトル、メッセージ、戻り先を格納する
				request.setAttribute("result",
						new Result("ログイン失敗！", "IDまたはPWに間違いがあります。", "/simpleBC/LoginServlet"));

				// ログインページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
				dispatcher.forward(request, response);
			}

		} else {//一致するIDがいない
			Result result = new Result();
			result.setTitle("ログイン失敗！");
			result.setMessage("IDが一致しませんでした。");
			request.setAttribute("result", result);
			// ログインページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}
}*/
