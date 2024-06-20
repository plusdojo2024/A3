package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TodoDAO;
import logic.TimeLogic;
/**
 * Servlet implementation class ShareRegistServlet
 */
@WebServlet("/ShareRegistServlet")
public class ShareRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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





		TodoDAO tdDao = new TodoDAO();
		TimeLogic time = new TimeLogic();

		//日付の取得
		String date = time.nowNomalDay();
/*
		Object todoDate;
		if(tdDao.regist(date, listId, new Todo(0,todoDate,uid,loop,startDate,endDate,monday,tuesday,wednesday,thursday,friday,saturday,sunday)))
		{
			request.setAttribute("msg", "登録しました");
		}
		else
		{
			request.setAttribute("msg", "登録に失敗しました");
		}
		*/

		// 結果ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/shareRegist.jsp");
		dispatcher.forward(request, response);



	}

}
