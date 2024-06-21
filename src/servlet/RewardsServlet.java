package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RewardsDAO;
import model.Rewards;
import model.Users;

/**
 * Servlet implementation class RewardsServlet
 */
@WebServlet("/RewardsServlet")
public class RewardsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//sessionを使いますよという宣言
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("dbUser");
		int familyId = user.getFamilyId();
		int role = user.getRole();
		int userId = user.getUid();


		RewardsDAO dao = new RewardsDAO();
		ArrayList<Rewards> rewardsList = dao.allSelect(familyId, role, userId);

		request.setAttribute("rewardsList", rewardsList);




		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsAdult.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		request.setCharacterEncoding("UTF-8");







		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsAdult.jsp");
		dispatcher.forward(request, response);




	}



























}
