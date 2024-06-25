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

		List<Users> userList = uDao.selectFamily(user.getFamilyId());

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

		HttpSession session = request.getSession();
		/*
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}
		*/
		Users user = (Users) session.getAttribute("user");

		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		request.setAttribute("myUser", dbUser);
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		int uid = Integer.parseInt(request.getParameter("uid"));
		String strListId = request.getParameter("task");
		String selectDate = request.getParameter("select_date");
		String loop = request.getParameter("loop");

		int listId = Integer.parseInt(strListId);

		TodoDAO tdDao = new TodoDAO();
		Todo todo = new Todo();
		if (loop.equals("0")) {
			todo.setLoop(0);
			todo.setUid(uid);
			todo.setTodoDate(selectDate);
			todo.setListId(listId);
			if (!tdDao.registCheck(todo)) {
				if (tdDao.registOneDay(todo)) {
					System.out.println("成功しました。");
					// Calendarにフォワードする
					response.sendRedirect("/A3/CalendarServlet");
				} else {
					System.out.println("失敗しました。");
					// Calendarにフォワードする
					response.sendRedirect("/A3/CalendarServlet");
				}
			} else {
				System.out.println("既にあります。");
				request.setAttribute("msg", "既に同じタスクが登録されています。");

				//日付文字列をLocalDate型に変換
				LocalDate startDay = LocalDate.parse(selectDate, DateTimeFormatter.ISO_LOCAL_DATE);

				//開始日の翌月の月末まで
				LocalDate endDay = startDay.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

				UsersDAO uDao = new UsersDAO();
				TodoListDAO tLDao = new TodoListDAO();

				List<Users> userList = uDao.selectFamily(user.getFamilyId());

				List<TodoList> taskList = tLDao.view(user.getFamilyId());

				request.setAttribute("taskList", taskList);
				request.setAttribute("userList", userList);
				request.setAttribute("today", selectDate);
				request.setAttribute("endDay", endDay);

				// 登録ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareRegist.jsp");
				dispatcher.forward(request, response);
				return;
			}

		} else {
			String endDate = request.getParameter("end_date");
			String strWeek[] = request.getParameterValues("week[]");

			if (strWeek == null) {
				request.setAttribute("msg", "ループ選択時は曜日を1つ以上選択してください");

				//日付文字列をLocalDate型に変換
				LocalDate startDay = LocalDate.parse(selectDate, DateTimeFormatter.ISO_LOCAL_DATE);

				//開始日の翌月の月末まで
				LocalDate endDay = startDay.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

				UsersDAO uDao = new UsersDAO();
				TodoListDAO tLDao = new TodoListDAO();

				List<Users> userList = uDao.selectFamily(user.getFamilyId());

				List<TodoList> taskList = tLDao.view(user.getFamilyId());

				request.setAttribute("taskList", taskList);
				request.setAttribute("userList", userList);
				request.setAttribute("today", selectDate);
				request.setAttribute("endDay", endDay);
				// 登録ページにフォワードする
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareRegist.jsp");
				dispatcher.forward(request, response);
			} else {
				if (endDate == "") {
					request.setAttribute("msg", "ループ終了日を選択してください");
					//日付文字列をLocalDate型に変換
					LocalDate startDay = LocalDate.parse(selectDate, DateTimeFormatter.ISO_LOCAL_DATE);

					//開始日の翌月の月末まで
					LocalDate endDay = startDay.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

					UsersDAO uDao = new UsersDAO();
					TodoListDAO tLDao = new TodoListDAO();

					List<Users> userList = uDao.selectFamily(user.getFamilyId());

					List<TodoList> taskList = tLDao.view(user.getFamilyId());

					request.setAttribute("taskList", taskList);
					request.setAttribute("userList", userList);
					request.setAttribute("today", selectDate);
					request.setAttribute("endDay", endDay);
					// 登録ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareRegist.jsp");
					dispatcher.forward(request, response);

				} else {
					//strWeekと同じ要素数のint型配列宣言
					int[] week = new int[strWeek.length];

					//Stringの配列をintに変換
					for (int i = 0; i < strWeek.length; i++) {
						week[i] = Integer.parseInt(strWeek[i]);
					}

					TimeLogic tLogic = new TimeLogic();

					//startからendまでの指定した曜日の日をリストにして取得
					List<Todo> todoList = tLogic.createTodo(selectDate, endDate, week, uid, listId);

					for (Todo loopTodo : todoList) {
						if (!tdDao.registCheck(loopTodo)) {
							if (tdDao.registLoop(loopTodo)) {
								System.out.println("成功しました。");
							} else {
								System.out.println("失敗しました。");
							}
						} else {
							System.out.println("既に登録してあります。");
						}

					}

					// Calendarにリダイレクトする
					response.sendRedirect("/A3/CalendarServlet");

				}

			}

		}

	}

}
