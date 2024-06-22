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
import model.Todo;
import model.TodoList;
import model.Users;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		request.setAttribute("myUser", dbUser);

		TodoDAO tDao = new TodoDAO();

		List<Todo> todoCalendar = tDao.getCalendarData(dbUser.getFamilyId());

		request.setAttribute("eventList", todoCalendar);
		// アカウント管理ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		request.setAttribute("myUser", dbUser);

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String date = request.getParameter("select_date");//日付データ取得
		String event = request.getParameter("event");//イベント情報取得

		System.out.println(date);

		String[] dateList = date.split(" ");
		int count=0;
		for(String test:dateList) {


			System.out.println(count+"："+test);
			count++;
		}
		String[] eventList = event.split("：");

		//フォーマット調整
		switch (dateList[1]) {
		case "Jan":
			dateList[1] = "01";
			break;
		case "Feb":
			dateList[1] = "02";
			break;
		case "Mar":
			dateList[1] = "03";
			break;
		case "Apr":
			dateList[1] = "04";
			break;
		case "May":
			dateList[1] = "05";
			break;
		case "Jun":
			dateList[1] = "06";
			break;
		case "Jul":
			dateList[1] = "07";
			break;
		case "Aug":
			dateList[1] = "08";
			break;
		case "Sep":
			dateList[1] = "09";
			break;
		case "Oct":
			dateList[1] = "10";
			break;
		case "Nov":
			dateList[1] = "11";
			break;
		case "Dec":
			dateList[1] = "12";
			break;
		}

		String name = eventList[1];
		event = eventList[0];
		date = dateList[3] + "-" + dateList[1] + "-" + dateList[2];
		//日付文字列をLocalDate型に変換
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");//フォーマット指定
		LocalDate startDay = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);

		//開始日の翌月の月末まで
		LocalDate endDay = startDay.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());

		UsersDAO uDao = new UsersDAO();
		TodoListDAO tLDao = new TodoListDAO();

		List<Users> userList = uDao.selectFamily(dbUser.getFamilyId());

		List<TodoList> taskList = tLDao.view(dbUser.getFamilyId());

		request.setAttribute("taskList", taskList);
		request.setAttribute("userList", userList);
		request.setAttribute("today", startDay);
		request.setAttribute("endDay", endDay);
		request.setAttribute("date", date);
		request.setAttribute("name", name);
		request.setAttribute("event", event);
		// アカウント管理ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareEdit.jsp");
		dispatcher.forward(request, response);
	}

}
