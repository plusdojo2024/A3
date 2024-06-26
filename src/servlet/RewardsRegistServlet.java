package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RewardsDAO;
import dao.UsersDAO;
import model.Rewards;
import model.Users;

/**
 * Servlet implementation class RewardsRegistServlet
 */
@WebServlet("/RewardsRegistServlet")
public class RewardsRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("user");

		UsersDAO usersdao = new UsersDAO();
		List<Users> ud = usersdao.selectFamily(user.getFamilyId());
		request.setAttribute("ud", ud);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsRegist.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String reward = request.getParameter("reward");
		String point = request.getParameter("reqPoint");
		int p = Integer.parseInt(point);
		String name = request.getParameter("name");
		int n = Integer.parseInt(name);

		RewardsDAO dao = new RewardsDAO();
        int result = dao.insert(reward, p, n);


       //デバッグ用
       if(result==1) {
    	   System.out.println("成功だよ");
    	   request.setAttribute("msg", "ご褒美の登録が完了しました！");
       }else {
    	   System.out.println("失敗だよ");
    	   request.setAttribute("msg", "ご褒美の登録が出来ませんでした");
       }

       //sessionを使いますよという宣言
		HttpSession session = request.getSession();
		Users user = (Users)session.getAttribute("dbUser");
		int familyId = user.getFamilyId();
		int role = user.getRole();
		int userId = user.getUid();

		RewardsDAO dao1 = new RewardsDAO();
		ArrayList<Rewards> rewardsList = dao1.allSelect(familyId, role, userId);
		request.setAttribute("rewardsList", rewardsList);


	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsAdult.jsp");
	dispatcher.forward(request, response);

	}

}
