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

import dao.NotesDAO;
import dao.TodoDAO;
import dao.UsersDAO;
import logic.TimeLogic;
import model.Notes;
import model.Todo;
import model.Users;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		// もしもログインしていなかったらログインサーブレットにリダイレクトする

		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}

//		//値の取得
//		//文字化け禁止
//		request.setCharacterEncoding("UTF-8");

//		//ユーザー名・メアド・パスワードのハッシュ化前の値が格納されてる
//		//ついでに無いと不便なのでファミリーIDとユーザーIDも格納されてる
//		Users user = (Users) session.getAttribute("user");//ハッシュ化前ユーザー
//
//		//データベースに入ってる個人情報は全部入ってる。
//		//ユーザー名・メアド・パスワードはハッシュ化されてる
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー

		TodoDAO tDao = new TodoDAO();
		NotesDAO nDao = new NotesDAO();

		TimeLogic time = new TimeLogic();
		String today = time.nowCalendar();
		String yesterday = time.yesterday(today);

		//今日のやることリスト
		List<Todo> todayList = tDao.getTodayListByUidNow(dbUser.getUid(), today);

		//前日の完了がついてないやることリスト
		List<Todo> yesterdayList = tDao.getYesterdayListByUidNow(dbUser.getUid(), yesterday);

		//前日の引継ぎノートを取得
		Notes note = nDao.getNoteByDay(dbUser.getFamilyId(), time.changeFormat(yesterday));

		//前日の引継ぎノート
		request.setAttribute("note", note);

		//今日のやることリスト
		request.setAttribute("todayList", todayList);

		//前日のやることリスト
		request.setAttribute("yesterdayList", yesterdayList);



		// ホーム画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}

	//Post
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession();

		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		String strTodoId = request.getParameter("todo_id");

		System.out.println(strTodoId);

		int todoId = Integer.parseInt(strTodoId);
		TodoDAO tDao = new TodoDAO();
		if(tDao.complete(todoId)) {
			//更新成功
			System.out.println("完了しました");
			//完了したタスクのポイントを取得
			int point = tDao.getPointByTodoId(todoId);
			System.out.println("ポイント："+point);
			//ユーザーの保有ポイントと合わせる
			point += dbUser.getHavePoint();
			System.out.println("合計ポイント："+point);

			UsersDAO uDao = new UsersDAO();
			//ユーザーのポイントを更新
			uDao.pointUpdate(dbUser.getUid(), point);

			//セッションスコープのユーザー情報更新
			Users updateUser = uDao.getUser(dbUser.getUid());

			session.setAttribute("dbUser", updateUser);
		}else {
			System.out.println("更新失敗しました");
		}
		response.sendRedirect("/A3/HomeServlet");
	}
}
