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
import model.Family;
import model.TodoList;

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
		//if (session.getAttribute("uid") == null) {
		//	response.sendRedirect("/A3/LoginServlet");
		//	return;
		//}

		//aタグURLから項目名を取り出す
		String task = request.getParameter("name");
		//request.setAttribute("ArrayList<TodoList>", atask);
		request.setCharacterEncoding("UTF-8");
		Family f = new Family();
		f.setFamilyId(1);
		session.setAttribute("familyId", f);
		Family family = (Family)session.getAttribute("familyId");
		int familyId = family.getFamilyId();

		TodoListDAO tlDao = new TodoListDAO();
		List<TodoList> todolist = tlDao.select(familyId, task);
		request.setAttribute("todolist", todolist);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoMemo.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
