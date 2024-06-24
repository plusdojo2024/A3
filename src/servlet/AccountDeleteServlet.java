package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.FamilyDAO;
import dao.UsersDAO;
import model.Users;

/**
 * Servlet implementation class AccountDeleteServlet
 */
@WebServlet("/AccountDeleteServlet")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー

		UsersDAO uDao = new UsersDAO();

		//同じ家族の全ユーザー取得
		List<Users> familyList = uDao.selectFamily(dbUser.getFamilyId());

		//リクエストスコープにセット
		request.setAttribute("familyList", familyList);

		// アカウント削除ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー

		String strUid = request.getParameter("uid");
		String strAdmin = request.getParameter("admin");

		int admin = Integer.parseInt(strAdmin);//intに変換
		int uid = Integer.parseInt(strUid);//intに変換

		if (admin == 1) {
			FamilyDAO fDao = new FamilyDAO();
			if(fDao.familyDelete(dbUser.getFamilyId())) {
				System.out.println("家族削除成功！");
				// ログインページにリダイレクトする
				response.sendRedirect("/A3/LoginServlet");
			}else {
				System.out.println("家族削除失敗！");
				// アカウント削除ページにリダイレクトする
				response.sendRedirect("/A3/AccountDeleteServlet");
			}
		} else {
			UsersDAO uDao = new UsersDAO();
			if (uDao.delete(uid)) {
				System.out.println("成功しました");

				// アカウント削除ページにリダイレクトする
				response.sendRedirect("/A3/AccountDeleteServlet");
			} else {
				System.out.println("失敗しました");

				// アカウント削除ページにリダイレクトする
				response.sendRedirect("/A3/AccountDeleteServlet");
			}
		}

	}

}
