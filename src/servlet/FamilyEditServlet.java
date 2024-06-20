package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FamilyDAO;
import dao.UsersDAO;
import logic.HashLogic;
import model.Family;
import model.Message;
import model.Users;

/**
 * Servlet implementation class FamilyEditServlet
 */
@WebServlet("/FamilyEditServlet")
public class FamilyEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		Users myUser = (Users) session.getAttribute("user");//ハッシュ化前ユーザー
		request.setAttribute("myUser", dbUser);
		request.setAttribute("userNoHash", myUser);
		// アカウント管理ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/familyEdit.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String familyName = request.getParameter("family_name");
		String familyPass = request.getParameter("family_pass");
		String mail = request.getParameter("mail");
		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		Users myUser = (Users) session.getAttribute("user");//ハッシュ化前ユーザー

		Family updateFamily = new Family();
		HashLogic hLogic = new HashLogic();

		updateFamily.setFamilyId(myUser.getFamilyId());
		updateFamily.setFamilyName(familyName);
		String hashMail = hLogic.getHash(mail);
		updateFamily.setMail(hashMail);

		FamilyDAO fDao = new FamilyDAO();

		if (familyPass == null || familyPass.equals("")) {

			Family passFamily = fDao.getPassById(myUser.getFamilyId());
			updateFamily.setFamilyPass(passFamily.getFamilyPass());
			updateFamily.setFamilySalt(passFamily.getFamilySalt());
		} else {

			hLogic.randHash(familyPass);

			updateFamily.setFamilyPass(hLogic.getPw());
			updateFamily.setFamilySalt(hLogic.getSalt());
		}



		Message msg = new Message();
		if (fDao.familyUpdate(updateFamily)) {
			System.out.println("成功しました。");
			msg.setMessage("家族情報の更新に成功しました。");
			UsersDAO uDao = new UsersDAO();
			dbUser = uDao.getUser(dbUser.getUid());
			myUser.setFamilyName(familyName);
			myUser.setFamilyPass(familyPass);
			myUser.setMail(mail);
			session.setAttribute("myUser", dbUser);
			session.setAttribute("user", myUser);

			request.setAttribute("userNoHash", myUser);
			request.setAttribute("message", msg);
			// アカウント管理ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/familyEdit.jsp");
			dispatcher.forward(request, response);

		} else {
			System.out.println("失敗しました。");
			msg.setMessage("家族情報の更新に失敗しました。");
			request.setAttribute("message", msg);
			// アカウント管理ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/familyEdit.jsp");
			dispatcher.forward(request, response);
		}

	}

}
