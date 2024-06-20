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

import dao.UsersDAO;
import logic.TimeLogic;
import model.Users;

/**
 * Servlet implementation class NoteEditServlet
 */
@WebServlet("/NoteEditServlet")
public class NoteEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");
		UsersDAO usersdao = new UsersDAO();
		List<Users> ud = usersdao.selectFamily(user.getFamilyId());
		System.out.println(ud.get(0).getName());
		request.setAttribute("ud", ud);
		TimeLogic time = new TimeLogic();
		String date = time.nowNomalDay();


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/noteEdit.jsp");
	    dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		String nd =request.getParameter("noteDate");
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");

        request.setAttribute("title", title);
        request.setAttribute("memo", memo);
        request.setAttribute("nd", nd);


	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/noteEdit.jsp");
	dispatcher.forward(request, response);
	}

}
