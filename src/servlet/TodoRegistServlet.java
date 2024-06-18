package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TodoListDAO;
import logic.TimeLogic;
import model.Family;
import model.TodoList;

/**
 * Servlet implementation class TodoRegistServlet
 */
@WebServlet("/TodoRegistServlet")
public class TodoRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		//HttpSession session = request.getSession();
		//if (session.getAttribute("uid") == null) {
		//	response.sendRedirect("/A3/LoginServlet");
		//	return;
		//}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//if (session.getAttribute("uid") == null) {
		//	response.sendRedirect("/A3/LoginServlet");
		//	return;
		//}

		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		Family f = new Family();
		f.setFamilyId(1);
		session.setAttribute("family_id", f);
		Family family = (Family)session.getAttribute("family_id");
		int family_id = family.getFamilyId();
		String task = request.getParameter("task");
		String category = request.getParameter("category");
		int give_point = Integer.parseInt(request.getParameter("give_point"));
		String list_date = request.getParameter("list_date");
		String memo = request.getParameter("memo");
		int todo_delete = 0;

		TodoListDAO tlDao = new TodoListDAO();
		TimeLogic time = new TimeLogic();
		String date = time.nowNomalDay();
		if(tlDao.regist(date, new TodoList(0, family_id, task, category, give_point, list_date, memo, todo_delete))) {
			request.setAttribute("message", "家事を登録しました");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoRegist.jsp");
		dispatcher.forward(request, response);
	}

}
