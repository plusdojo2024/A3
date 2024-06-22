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
import dao.UsersDAO;
import logic.TimeLogic;
import model.Todo;
import model.TodoList;
import model.Users;

/**
 * Servlet implementation class ShareEditServlet
 */
@WebServlet("/ShareEditServlet")
public class ShareEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareEdit.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		/*
		if (session.getAttribute("user") == null) {
			response.sendRedirect("/A3/LoginServlet");
			return;
		}
		*/
		Users user = (Users) session.getAttribute("user");

		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		request.setAttribute("myUser", dbUser);//ヘッダー用情報
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		//選択したイベント
		String selectName = request.getParameter("select_name");
		String selectTask = request.getParameter("select_task");
		String startDate = request.getParameter("start_date");

		UsersDAO uDao = new UsersDAO();
		//担当者情報
		Users manager = uDao.loginSearch(dbUser.getFamilyId(), selectName);

		TodoListDAO tLDao = new TodoListDAO();
		TodoList todoList = tLDao.selectByFamilyIdAndTask(manager.getFamilyId(), selectTask);
		TodoDAO tDao = new TodoDAO();


		//編集後の項目
		String strUid = request.getParameter("uid");
		int uid = Integer.parseInt(strUid);

		String strListId = request.getParameter("task");
		int listId = Integer.parseInt(strListId);

		String loop = request.getParameter("loop");

		Users updateUser = uDao.getUser(uid);


		String submit = request.getParameter("submit");

		TimeLogic tLogic = new TimeLogic();





		if(loop.equals("0")) {
			if(submit.equals("編集")) {
				tDao.update(updateUser.getUid(), listId,manager.getUid(),todoList.getListId(),startDate);
				System.out.println("更新成功しました。");
			}else if(submit.equals("削除")) {
				tDao.delete(manager.getUid(), listId,startDate);
				System.out.println("削除成功しました。");
			}
		}else {
			String endDate = request.getParameter("end_date");
			String strWeek[] = request.getParameterValues("week[]");

			//strWeekと同じ要素数のint型配列宣言
			int[] week = new int[strWeek.length];

			//Stringの配列をintに変換
			for (int i = 0; i < strWeek.length; i++) {
				week[i] = Integer.parseInt(strWeek[i]);
			}


			List<Todo> list = tLogic.createTodo(startDate, endDate, week,manager.getUid(), todoList.getListId());
			if(submit.equals("編集")) {
				for(Todo todo:list) {
					if(tDao.update(updateUser.getUid(),listId,todo.getUid(),todo.getListId(),todo.getTodoDate())) {
						System.out.println("更新成功しました。");
					}else {
						System.out.println("更新失敗しました。");
					}
				}
			}else if(submit.equals("削除")) {
				for(Todo todo:list) {
					if(tDao.delete(manager.getUid(), todoList.getListId(),todo.getTodoDate())) {
						System.out.println("削除成功しました。");
					}else {
						System.out.println("削除失敗しました。");
					}
				}
			}
		}
		// Calendarにフォワードする
		response.sendRedirect("/A3/CalendarServlet");

	}

}
