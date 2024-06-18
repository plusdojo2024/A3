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
import javax.servlet.http.Part;

import dao.FamilyDAO;
import dao.UsersDAO;
import logic.FileLogic;
import logic.HashLogic;
import logic.TimeLogic;
import model.Family;
import model.Message;
import model.Users;

/**
 * Servlet implementation class FamilyRegistServlet
 */

@WebServlet("/FamilyRegistServlet")
@MultipartConfig(location = "C:/pleiades/workspace/A3/WebContent/tmp", maxFileSize = 1024 * 1024 * 10)
public class FamilyRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 新規家族作成ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/familyRegist.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String familyPass = request.getParameter("family_pass");
		String familyName = request.getParameter("family_name");
		String userName = request.getParameter("user_name");
		String mail = request.getParameter("mail");
		String userPass = request.getParameter("user_pass");
		String color = request.getParameter("color");

		HashLogic hashLogic = new HashLogic();
		String hashMail = hashLogic.getHash(mail);//固定のソルト値でハッシュ化

		FamilyDAO fDao = new FamilyDAO();
		//家族(メールアドレス)が既に登録されているかチェック
		if (fDao.familyCheck(hashMail)) {//既に登録されている
			Message msg = new Message();
			msg.setTitle("アカウント登録失敗！");
			msg.setMessage("このメールアドレスは既に使用されています");

			request.setAttribute("message", msg);

			// 新規家族作成ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/familyRegist.jsp");
			dispatcher.forward(request, response);
			return;
		} else {//メールアドレスが未登録
				//家族情報をBeanに格納
			Family family = new Family();
			//メアドは既にハッシュ化してあるのでそのまま格納
			family.setMail(hashMail);

			//パスワードをランダムなソルト値でハッシュ化して
			//hashLogicオブジェクトのsalt、pwにソルト値とハッシュ化したパスワードを格納
			hashLogic.randHash(familyPass);

			String salt = hashLogic.getSalt();
			family.setFamilySalt(salt);//ソルト値格納

			family.setFamilyPass(hashLogic.getPw());//ハッシュ化したパスワード格納

			family.setFamilyName(familyName);//家族名格納

			TimeLogic time = new TimeLogic();
			family.setFamilyDate(time.nowJp());//家族アカウント作成日時
			if (fDao.insert(family)) {//アカウント作成成功
				FileLogic fL = new FileLogic();

				Part part = request.getPart("icon");//アイコン画像取得

				String name = fL.getFileName(part);//ファイル名取得

				int familyId = fDao.searchId(hashMail);//familyIDを取得

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

				//管理用ユーザー作成
				Users user = new Users();
				user.setAdmin(1);
				user.setColor(color);
				user.setFamilyId(familyId);
				user.setIcon(relativePath);
				user.setHavePoint(0);
				user.setRole(1);
				user.setUserDate(time.nowJp());

				user.setColor(color);

				//パスワードのハッシュ化
				hashLogic.randHash(userPass);
				user.setPw(hashLogic.getPw());//パスワードセット
				user.setUserSalt(hashLogic.getSalt());//ソルトセット

				user.setName(hashLogic.getHash(userName));

				UsersDAO uDao = new UsersDAO();

				if (uDao.insert(user)) {
					System.out.println("成功！");
					try {//eclipseのファイル同期が遅いので少し待機しないとアップロードした画像を表示できない
						Thread.sleep(2000); // 4秒(4000ミリ秒)間だけ処理を止める
					} catch (InterruptedException e) {
					}

					Message msg = new Message();
					msg.setTitle("アカウント作成に成功しました。");
					msg.setMessage("ログインページからログインしてください。");

					request.setAttribute("message", msg);
					// ログインページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
					dispatcher.forward(request, response);
				} else {
					Message msg = new Message();
					msg.setTitle("アカウント作成失敗！");
					msg.setMessage("代表者アカウントの作成に失敗しました");

					request.setAttribute("message", msg);

					// 新規家族作成ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/familyRegist.jsp");
					dispatcher.forward(request, response);
					return;
				}

			} else {
				Message msg = new Message();
				msg.setTitle("アカウント作成失敗！");
				msg.setTitle("予期せぬ問題が発生し、アカウント作成に失敗しました。");

				request.setAttribute("message", msg);

				// 新規家族作成ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/familyRegist.jsp");
				dispatcher.forward(request, response);
			}

		}

	}

}
