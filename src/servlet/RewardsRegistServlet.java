package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RewardsRegistServlet
 */
@WebServlet("/RewardsRegistServlet")
public class RewardsRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsRegist.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String reward = request.getParameter("reward");
		String point = request.getParameter("point");
		String name = request.getParameter("name");

        request.setAttribute("reward", reward);
        request.setAttribute("point", point);
        request.setAttribute("name", name);


	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsRegist.jsp");
	dispatcher.forward(request, response);

	}

}
