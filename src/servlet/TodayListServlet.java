package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Users;

/**
 * Servlet implementation class TodayListServlet
 */
@WebServlet("/TodayListServlet")
public class TodayListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//値の取得
		//文字化け禁止
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		//ユーザー名・メアド・パスワードのハッシュ化前の値が格納されてる
		//ついでに無いと不便なのでファミリーIDとユーザーIDも格納されてる
		Users user = (Users) session.getAttribute("user");//ハッシュ化前ユーザー

		//データベースに入ってる個人情報は全部入ってる。
		//ユーザー名・メアド・パスワードはハッシュ化されてる
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー

		//DAOを複数インスタンス化
		//当日やることリストのdaoをインスタンス化
//		TodoListDAO tDAO = new TodoListDAO();
//		tDAO.select(user.getFamilyId());

		//上でインスタンス化したdaoに値を取得するようにさせる（メソッド使用）


		//取得してきた値をリクエストに保存



		// 今日やることリストページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todayList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//文字化け禁止
		request.setCharacterEncoding("UTF-8");

		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todayList.jsp");
		dispatcher.forward(request, response);
	}

}
