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
		HttpSession session = request.getSession();
		if (session.getAttribute("uid") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("uid") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}

		request.setCharacterEncoding("UTF-8");
		int family_id = Integer.parseInt(request.getParameter("family_id"));
		String task = request.getParameter("task");
		String category = request.getParameter("category");
		int give_point = Integer.parseInt(request.getParameter("give_point"));
		String list_date = request.getParameter("list_date");
		String memo = request.getParameter("memo");
		int todo_delete = Integer.parseInt(request.getParameter("todo_delete"));

		TodoListDAO tlDao = new TodoListDAO();
		tlDao.regist(new TodoList(0, family_id, task, category, give_point, list_date, memo, todo_delete));

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoRegist.jsp");
		dispatcher.forward(request, response);
	}

}
