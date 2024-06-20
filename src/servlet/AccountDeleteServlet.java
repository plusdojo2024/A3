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

import dao.UsersDAO;
import model.Message;
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

		request.setAttribute("myUser", dbUser);
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

		int uid = Integer.parseInt(strUid);//intに変換


		UsersDAO uDao = new UsersDAO();
		if(uDao.delete(uid)) {
			System.out.println("成功しました");
			Message msg = new Message();
			msg.setMessage("アカウントを削除しました。");
			request.setAttribute("message",msg);
			request.setAttribute("myUser", dbUser);
			// アカウント削除ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountDelete.jsp");
			dispatcher.forward(request, response);
		}else {
			System.out.println("失敗しました");
			Message msg = new Message();
			msg.setMessage("失敗しました。");
			request.setAttribute("message",msg);
			request.setAttribute("myUser", dbUser);
			// アカウント削除ページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accountDelete.jsp");
			dispatcher.forward(request, response);
		}

	}

}
