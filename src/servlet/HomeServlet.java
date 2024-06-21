package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}

//		//値の取得
//		//文字化け禁止
//		request.setCharacterEncoding("UTF-8");
//
//
//
//		//ユーザー名・メアド・パスワードのハッシュ化前の値が格納されてる
//		//ついでに無いと不便なのでファミリーIDとユーザーIDも格納されてる
//		Users user = (Users) session.getAttribute("user");//ハッシュ化前ユーザー
//
//		//データベースに入ってる個人情報は全部入ってる。
//		//ユーザー名・メアド・パスワードはハッシュ化されてる
//		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
//
//		//DAOを複数インスタンス化
//		//当日やることリストのdaoをインスタンス化
//		TodoListDAO tDAO = new TodoListDAO();
//		TimeLogic time= new TimeLogic();//インスタンス化
//		String date = time.nowJp();//日本語の日付文字列格納
//		//今日のやることリストのデータを持ってくる----------------
//		List<TodoList> todoList = tDAO.selectNow(user.getUid(), date, 0);
//
//		//昨日の日付を取得
//		LocalDateTime now = LocalDateTime.now();
//		LocalDateTime yesterday = now.minusDays(1);
//		//日本語フォーマットを指定
//		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH時mm分ss秒");
//		String date2 = yesterday.format(f);
//		//昨日のやっていないやることリストのデータを持ってくる----------------
//		List<TodoList> todoListAfter = (List<TodoList>)tDAO.selectNow(user.getUid(), date2, 0);
//
//		//取得してきた値をリクエストに保存
//		request.setAttribute("todoList", todoList);
//		request.setAttribute("todoListAfter", todoListAfter);
//		System.out.println(todoList);
//		System.out.println(todoListAfter);
//		System.out.println(todoList.get(0).getCategory());
//		System.out.println(todoListAfter.get(0).getTask());

		// ホーム画面にフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);
	}


}
