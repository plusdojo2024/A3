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
 * Servlet implementation class TodoServlet
 */
@WebServlet("/TodoServlet")
public class TodoServlet extends HttpServlet {
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

		Users user = (Users)session.getAttribute("user");
		int familyId = user.getFamilyId();

		Users dbUser = (Users) session.getAttribute("dbUser");
		request.setAttribute("myUser", dbUser);

		TodoListDAO tlDao = new TodoListDAO();
		List<TodoList> todoview = tlDao.view(familyId);
		request.setAttribute("todoview", todoview);

		List<TodoList> todowash = tlDao.viewWash(familyId, "洗濯");
		request.setAttribute("todowash", todowash);
		List<TodoList> todogarbage = tlDao.viewGarbage(familyId, "ごみ捨て");
		request.setAttribute("todogarbage", todogarbage);
		List<TodoList> todoclean = tlDao.viewClean(familyId, "掃除");
		request.setAttribute("todoclean", todoclean);
		List<TodoList> todocooking = tlDao.viewCooking(familyId, "料理");
		request.setAttribute("todocooking", todocooking);
		List<TodoList> todoshopping = tlDao.viewShopping(familyId, "買い物");
		request.setAttribute("todoshopping", todoshopping);
		List<TodoList> todoplus = tlDao.viewPlus(familyId, "日用品の補充");
		request.setAttribute("todoplus", todoplus);
		List<TodoList> todochild = tlDao.viewChild(familyId, "子育て");
		request.setAttribute("todochild", todochild);
		List<TodoList> todoother = tlDao.viewOther(familyId, "その他");
		request.setAttribute("todoother", todoother);

		// メニューページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
