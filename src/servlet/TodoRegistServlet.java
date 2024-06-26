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
import logic.TimeLogic;
import model.TodoList;
import model.Users;

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
		if (session.getAttribute("user") == null) {
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
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}

		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		Users user = (Users)session.getAttribute("user");
		int familyID = user.getFamilyId();
		//int familyId = Integer.parseInt(request.getParameter("familyId"));
		String task = request.getParameter("task");
		String category = request.getParameter("category");
		int givePoint = Integer.parseInt(request.getParameter("give_point"));
		String listDate = request.getParameter("listDate");
		String memo = request.getParameter("memo");
		int todoDelete = 0;

		Users dbUser = (Users) session.getAttribute("dbUser");
		request.setAttribute("myUser", dbUser);

		TodoListDAO tlDao = new TodoListDAO();
		TimeLogic time = new TimeLogic();
		String date = time.nowNomalDay();
		if(tlDao.isTaskOK(familyID, task)) {
			request.setAttribute("message", "登録済みのタスクです");
		} else {
			if(tlDao.regist(date, familyID, new TodoList(0, familyID, task, category, givePoint, listDate, memo, todoDelete))) {
				request.setAttribute("message", "家事を登録しました");
			}else {
				request.setAttribute("message", "登録に失敗しました");
			}
		}

		List<TodoList> todoview = tlDao.view(familyID);
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

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo.jsp");
		dispatcher.forward(request, response);
	}

}
