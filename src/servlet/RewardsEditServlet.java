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
 * Servlet implementation class RewardsEditServlet
 */
@WebServlet("/RewardsEditServlet")
public class RewardsEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

        //sessionのデータを使う準備
		HttpSession session = request.getSession();
		//sessionの中のuserデータを持ってくる
		Users user = (Users)session.getAttribute("user");


		//対象者選択のプルダウンで必要な登録ユーザー情報を、画面遷移時に取得する
		UsersDAO dao = new UsersDAO();
		List<Users> ud = dao.selectFamily(user.getFamilyId());
		request.setAttribute("ud", ud);

		//
		RewardsDAO redao = new RewardsDAO();






		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsEdit.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		if(request.getParameter("submit")!= null) {
			if(request.getParameter("submit").equals("更新")) {
				//jspから入力されたデータを持ってくる
				String reward = request.getParameter("reward");
				String point = request.getParameter("point");
				int p = Integer.parseInt(point);    //pointはStringで受け取るからDBに合わせて型変換
				String stUId = request.getParameter("uId");
				int uId = Integer.parseInt(stUId);    //nameはStringで受け取るからDBに合わせて型変換
				String name = request.getParameter("name");
				String stReId = request.getParameter("rewardId");
				int rewardId = Integer.parseInt(stReId);
				String rewardDate = request.getParameter("rewardDate");
		        String stReq = request.getParameter("re");
		        int re = Integer.parseInt(stReq);
//		        String stUid = request.getParameter("u_id");
//		        int uid = Integer.parseInt(stUid);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsEdit.jsp");
		    	dispatcher.forward(request, response);
			}
		}else {

			//jspから入力されたデータを持ってくる
			String reward = request.getParameter("reward");
			String point = request.getParameter("point");
			int p = Integer.parseInt(point);    //pointはStringで受け取るからDBに合わせて型変換
			String stUId = request.getParameter("u_id");
			int uId = Integer.parseInt(stUId);    //nameはStringで受け取るからDBに合わせて型変換
			String name = request.getParameter("name");
			String stReId = request.getParameter("reward_id");
			int rewardId = Integer.parseInt(stReId);
			String rewardDate = request.getParameter("reward_date");
	        String stReq = request.getParameter("request");
	        int re = Integer.parseInt(stReq);
	//        String stUid = request.getParameter("u_id");
	//        int uid = Integer.parseInt(stUid);



			//受け取ったデータをdaoに渡して登録処理
			RewardsDAO dao =new RewardsDAO();
			int result = dao.update(rewardId, reward, p, rewardDate, uId, re);



		    if(result==1) {
		        System.out.println("成功だよ");
			    //sessionを使いますよという宣言
				HttpSession session = request.getSession();
				Users user = (Users)session.getAttribute("dbUser");
				int familyId = user.getFamilyId();
				int role = user.getRole();
				int userId = user.getUid();


				RewardsDAO dao1 = new RewardsDAO();
				ArrayList<Rewards> rewardsList = dao1.allSelect(familyId, role, userId);

				request.setAttribute("rewardsList", rewardsList);
		    }else {
	          System.out.println("失敗だよ");
		    }

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsAdult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
