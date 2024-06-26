package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.UsersDAO;
import logic.FileLogic;
import logic.HashLogic;
import logic.TimeLogic;
import model.Message;
import model.Users;

/**
 * Servlet implementation class AccountRegistServlet
 */
@WebServlet("/AccountRegistServlet")
@MultipartConfig(location = "C:/pleiades/workspace/A3/WebContent/tmp", maxFileSize = 1024 * 1024 * 10)
public class AccountRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
			// アカウント登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String strRole = request.getParameter("role");
		String userName = request.getParameter("user_name");
		String userPass = request.getParameter("user_pass");
		String color = request.getParameter("color");

		//System.out.println(strRole);
		//System.out.println(userName);
		//ロールが文字列で送られてくるのでintに修正
		int role = 0;
		if (strRole.equals("0")) {
			role = 0;
		} else {
			role = 1;
		}

		HashLogic hLogic = new HashLogic();
		hLogic.randHash(userPass);

		TimeLogic time = new TimeLogic();

		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();

		//ユーザー名・メアド・パスワードのハッシュ化前の値が格納されてる
		//ついでに無いと不便なのでファミリーIDとユーザーIDも格納されてる
		Users user = (Users) session.getAttribute("user");//ハッシュ化前ユーザー

		//データベースに入ってる個人情報は全部入ってる。
		//ユーザー名・メアド・パスワードはハッシュ化されてる
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		UsersDAO uDao = new UsersDAO();

		if (uDao.userCheck(dbUser.getFamilyId(), userName)) {
			System.out.println("既にいる");
			request.setAttribute("myUser", dbUser);
			Message msg = new Message();
			msg.setTitle("作成失敗！");
			msg.setMessage("既に同じ名前のアカウントが存在します。");
			session.setAttribute("message", msg);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountRegist.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Users addUser = new Users();

		addUser.setAdmin(0);
		addUser.setColor(color);
		addUser.setRole(role);
		addUser.setFamilyId(user.getFamilyId());
		addUser.setHavePoint(0);
		addUser.setName(userName);
		addUser.setPw(hLogic.getPw());
		addUser.setUserSalt(hLogic.getSalt());
		addUser.setDelete(0);
		addUser.setUserDate(time.nowJp());

		FileLogic fL = new FileLogic();

		Part part = request.getPart("icon");//アイコン画像取得

		if (part.getSize() != 0) {
			String name = fL.getFileName(part);//ファイル名取得

			int familyId = user.getFamilyId();

			String absolutePass = fL.setAbsolutePass(name, familyId);//絶対パス

			//フォルダのパスだけ作成
			File target = new File("C:/pleiades/workspace/A3/WebContent/upload/family_" + familyId + "/");

			if (!target.exists()) {//フォルダが存在しなければ作成
				target.mkdirs();
				//ファイル保存
				part.write(absolutePass);
			} else {
				part.write(absolutePass);
			}
			try {//eclipseのファイル同期が遅いので少し待機しないとアップロードした画像を表示できない
				Thread.sleep(2000); // 2秒(2000ミリ秒)間だけ処理を止める
			} catch (InterruptedException e) {
			}
			part.delete();

			//アイコン画像の相対パス作成
			String relativePath = fL.setRelativePath(name, familyId);

			addUser.setIcon(relativePath);
		}else {
			if(role==1) {
				addUser.setIcon("images/icon/parent.png");
			}else {
				addUser.setIcon("images/icon/children.png");
			}
		}

		Message msg = new Message();
		if (uDao.insert(addUser)) {
			System.out.println("成功しました。");

			msg.setMessage("アカウントを作成しました。");

			request.setAttribute("message", msg);
			session.setAttribute("myUser", dbUser);
			// アカウント登録ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("失敗しました。");
			msg.setMessage("アカウントの作成に失敗しました。");
			request.setAttribute("message", msg);
			session.setAttribute("user", user);
			// アカウント登録ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
			dispatcher.forward(request, response);
		}
	}
}
