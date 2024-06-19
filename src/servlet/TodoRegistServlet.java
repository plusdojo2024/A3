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

		//リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		Users user = (Users)session.getAttribute("user");
		int familyID = user.getFamilyId();
		int familyId = Integer.parseInt(request.getParameter("familyId"));
		String task = request.getParameter("task");
		String category = request.getParameter("category");
		int givePoint = Integer.parseInt(request.getParameter("givePoint"));
		String listDate = request.getParameter("listDate");
		String memo = request.getParameter("memo");
		int todoDelete = 0;

		TodoListDAO tlDao = new TodoListDAO();
		TimeLogic time = new TimeLogic();
		String date = time.nowNomalDay();
		if(tlDao.regist(date, familyID, new TodoList(0, familyId, task, category, givePoint, listDate, memo, todoDelete))) {
			request.setAttribute("message", "家事を登録しました");
		}else {
			request.setAttribute("message", "登録に失敗しました");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todoRegist.jsp");
		dispatcher.forward(request, response);
	}

}
