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
import model.Message;
import model.Users;

/**
 * Servlet implementation class AccountEditServlet
 */
@WebServlet("/AccountEditServlet")
@MultipartConfig(location = "C:/pleiades/workspace/A3/WebContent/tmp", maxFileSize = 1024 * 1024 * 10)
public class AccountEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		request.setAttribute("myUser", dbUser);
		// アカウント編集ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountEdit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String userName = request.getParameter("user_name");
		String userPass = request.getParameter("user_pass");
		String color = request.getParameter("color");

		//System.out.println("name"+userName);
		//System.out.println("pass"+userPass);
		//System.out.println("color"+color);


		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();

		//ユーザー名・メアド・パスワードのハッシュ化前の値が格納されてる
		//ついでに無いと不便なのでファミリーIDとユーザーIDも格納されてる
		Users user = (Users) session.getAttribute("user");//ハッシュ化前ユーザー

		//データベースに入ってる個人情報は全部入ってる。
		//ユーザー名・メアド・パスワードはハッシュ化されてる
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー

		Users updateUser = new Users();


		if(userPass==null || userPass.equals("")) {//パスワードが送られて来なかったら
			System.out.println("ない");
			//元々ある値をそのままセット
			updateUser.setPw(dbUser.getPw());
			updateUser.setUserSalt(dbUser.getUserSalt());
		}else {//パスワードが送られてきていたら
			System.out.println("ある");
			//ハッシュ化してハッシュ値とソルトをセット
			HashLogic hLogic = new HashLogic();
			hLogic.randHash(userPass);
			updateUser.setPw(hLogic.getPw());
			updateUser.setUserSalt(hLogic.getSalt());
		}


		updateUser.setColor(color);
		updateUser.setName(userName);

		updateUser.setDelete(0);

		FileLogic fL = new FileLogic();

		Part part = request.getPart("icon");//アイコン画像取得
		//System.out.println("画像"+part.getSize());
		if(part.getSize()!=0) {
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

			//アイコン画像の相対パス作成
			String relativePath = fL.setRelativePath(name, familyId);

			updateUser.setIcon(relativePath);
		}else {
			updateUser.setIcon(dbUser.getIcon());
		}

		UsersDAO uDao = new UsersDAO();
		updateUser.setUid(dbUser.getUid());
		Message msg = new Message();
		if (uDao.update(updateUser)) {
			System.out.println("成功しました。");
			updateUser = uDao.getUser(updateUser.getUid());

			session.setAttribute("dbUser", updateUser);

			updateUser.setPw(userPass);
			session.setAttribute("user", updateUser);


			msg.setMessage("アカウントを更新しました。");

			request.setAttribute("message", msg);
			request.setAttribute("myUser", updateUser);

			// アカウント登録ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
			dispatcher.forward(request, response);
		} else {
			System.out.println("失敗しました。");
			msg.setMessage("アカウントの更新に失敗しました。");
			request.setAttribute("message", msg);
			// アカウント登録ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
			dispatcher.forward(request, response);
		}
	}

}
