package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
import dao.UsersDAO;
import logic.TimeLogic;
import model.Todo;
import model.TodoList;
import model.Users;

/**
 * Servlet implementation class ShareRegistServlet
 */
@WebServlet("/ShareRegistServlet")
public class ShareRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		/*
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}
		*/
		Users user = (Users) session.getAttribute("user");

		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String strDay = request.getParameter("date");
		System.out.println(strDay);
		//日付文字列をLocalDate型に変換
		LocalDate startDay = LocalDate.parse(strDay, DateTimeFormatter.ISO_LOCAL_DATE);

		//開始日の翌月の月末まで
		LocalDate endDay = startDay.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

		UsersDAO uDao = new UsersDAO();
		TodoListDAO tLDao = new TodoListDAO();

		List<Users>userList = uDao.selectFamily(user.getFamilyId());

		List<TodoList> taskList = tLDao.view(user.getFamilyId());


		request.setAttribute("taskList", taskList);
		request.setAttribute("userList", userList);
		request.setAttribute("today", strDay);
		request.setAttribute("endDay", endDay);
		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareRegist.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int uid = Integer.parseInt(request.getParameter("uid"));
		String name = request.getParameter(request.getParameter("name"));
		int listId = Integer.parseInt(request.getParameter("list_id"));
		String loop = request.getParameter(request.getParameter("loop"));
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		int monday = Integer.parseInt(request.getParameter("monday"));
		int tuesday = Integer.parseInt(request.getParameter("tuesday"));
		int wednesday = Integer.parseInt(request.getParameter("wednesday"));
		int thursday = Integer.parseInt(request.getParameter("thursday"));
		int friday = Integer.parseInt(request.getParameter("friday"));
		int saturday = Integer.parseInt(request.getParameter("saturday"));
		int sunday = Integer.parseInt(request.getParameter("sunday"));
		String selectDate = request.getParameter("select_date");

		TodoDAO tdDao = new TodoDAO();
		TimeLogic time = new TimeLogic();

		//日付の取得
		String date = time.nowNomalDay();

		Todo newTodo = new Todo(0, uid, loop, sunday, sunday, startDate, endDate, monday, tuesday, wednesday, thursday,
				friday, saturday, sunday);

		boolean isRegistered = tdDao.regist(date, 1, newTodo);

		if (isRegistered) {
			request.setAttribute("msg", "登録しました");
		} else {
			request.setAttribute("msg", "登録に失敗しました");
		}

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareRegist.jsp");
		dispatcher.forward(request, response);

	}

}
