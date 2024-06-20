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

import dao.TodoListDAO;
import model.TodoList;
import model.Users;

/**
 * Servlet implementation class TodoEditServlet
 */
@WebServlet("/TodoEditServlet")
public class TodoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}

		TodoListDAO tlDao = new TodoListDAO();
		Users user = (Users)session.getAttribute("user");
		int familyID = user.getFamilyId();
		String task = request.getParameter("task");
		List<TodoList> todolist = tlDao.select(familyID, task);
		request.setAttribute("todolist", todolist);

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoEdit.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}

//		int listId = Integer.parseInt(request.getParameter("list_id"));
//		int familyId = Integer.parseInt(request.getParameter("family_id"));
//		String task = request.getParameter("task");
//		String category = request.getParameter("category");
//		int givePoint = Integer.parseInt(request.getParameter("give_point"));
//		String listDate = request.getParameter("listDate");
//		String memo = request.getParameter("memo");
//		int todoDelete = 0;
//
//		TodoListDAO tlDao = new TodoListDAO();
//		Users user = (Users)session.getAttribute("user");
//		int familyID = user.getFamilyId();
//
//		if (request.getParameter("submit").equals("更新")) {
//			if (tlDao.update(familyID, new TodoList(listId, familyId, task, category, givePoint, listDate, memo, todoDelete))) {
//				request.setAttribute("message", "更新しました。");
//			} else {
//				request.setAttribute("message", "更新に失敗しました。");
//			}
//		}
//
//		// 結果ページにフォワードする
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoEdit.jsp");
//		dispatcher.forward(request, response);
	}

}
