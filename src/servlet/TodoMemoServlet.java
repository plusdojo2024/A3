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

import dao.TodoDAO;
import dao.TodoListDAO;
import model.Todo;
import model.TodoList;
import model.Users;

/**
 * Servlet implementation class TodoMemoServlet
 */
@WebServlet("/TodoMemoServlet")
public class TodoMemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}


		//request.setAttribute("ArrayList<TodoList>", atask);
		request.setCharacterEncoding("UTF-8");
		String task = request.getParameter("name");
		System.out.println(task);//デバッグ用
		//Family f = new Family();
		//f.setFamilyId(1);
		//session.setAttribute("familyId", f);
		Users user = (Users)session.getAttribute("user");
		Users dbUser = (Users) session.getAttribute("dbUser");
		request.setAttribute("myUser", dbUser);
		int familyId = user.getFamilyId();

		TodoListDAO tlDao = new TodoListDAO();
		List<TodoList> todolist = tlDao.select(familyId, task);
		request.setAttribute("todolist", todolist);

		TodoDAO tDao = new TodoDAO();
		List<Todo> todoHistory = tDao.getTaskHistory(familyId, task);

		request.setAttribute("todoHistory", todoHistory);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoMemo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
