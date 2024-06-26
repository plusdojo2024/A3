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

		request.setCharacterEncoding("UTF-8");
		Users user = (Users)session.getAttribute("user");
		Users dbUser = (Users) session.getAttribute("dbUser");

		TodoListDAO tlDao = new TodoListDAO();
//		Users user = (Users)session.getAttribute("user");
		int familyID = user.getFamilyId();
		String task = request.getParameter("name");
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
		request.setCharacterEncoding("UTF-8");
		int listId = Integer.parseInt(request.getParameter("list_id"));
		int familyId = Integer.parseInt(request.getParameter("family_id"));
		String task = request.getParameter("task");
		String category = request.getParameter("category");
		int givePoint = Integer.parseInt(request.getParameter("give_point"));
		String listDate = request.getParameter("list_date");
		String memo = request.getParameter("memo");
		int todoDelete = 0;
		System.out.println("ここまできたよ");

		TodoListDAO tlDao = new TodoListDAO();
		Users user = (Users)session.getAttribute("user");
		int familyID = user.getFamilyId();
//		List<TodoList> todolist = tlDao.select(familyID, task);
//		request.setAttribute("todolist", todolist);
		System.out.println("aaa"+request.getParameter("submit")+"aaa");
		if (request.getParameter("submit").equals("更新")) {
			if (tlDao.update(familyID, new TodoList(listId, familyId, task, category, givePoint, listDate, memo, todoDelete))) {
				System.out.println("更新完了");
				request.setAttribute("message", "更新しました。");

			} else {
				System.out.println("更新あかん");
				request.setAttribute("message", "更新に失敗しました。");
			}
		} else {
			if(tlDao.delete(listId)) {
				request.setAttribute("message", "削除しました");
			} else {
				request.setAttribute("message", "削除に失敗しました");
			}
		}

//		List<TodoList> todolist = tlDao.select(familyId, task);
//		request.setAttribute("todolist", todolist);

		List<TodoList> todoview = tlDao.view(familyId);
		request.setAttribute("todoview", todoview);

		List<TodoList> todowash = tlDao.viewWash(familyID, "洗濯");
		request.setAttribute("todowash", todowash);
		List<TodoList> todogarbage = tlDao.viewGarbage(familyID, "ごみ捨て");
		request.setAttribute("todogarbage", todogarbage);
		List<TodoList> todoclean = tlDao.viewClean(familyID, "掃除");
		request.setAttribute("todoclean", todoclean);
		List<TodoList> todocooking = tlDao.viewCooking(familyID, "料理");
		request.setAttribute("todocooking", todocooking);
		List<TodoList> todoshopping = tlDao.viewShopping(familyID, "買い物");
		request.setAttribute("todoshopping", todoshopping);
		List<TodoList> todoplus = tlDao.viewPlus(familyID, "日用品の補充");
		request.setAttribute("todoplus", todoplus);
		List<TodoList> todochild = tlDao.viewChild(familyID, "子育て");
		request.setAttribute("todochild", todochild);
		List<TodoList> todoother = tlDao.viewOther(familyID, "その他");
		request.setAttribute("todoother", todoother);
		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}

}
