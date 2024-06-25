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
		//sessionからuser情報を持ってくる
		Users user = (Users)session.getAttribute("dbUser");
		int familyId = user.getFamilyId();
		int role = user.getRole();
		int userId = user.getUid();

        //allSelectメソッドを使って一覧表示のためにrewardsの情報を全て持ってくる
		RewardsDAO dao = new RewardsDAO();
		//ログインユーザーに合わせたご褒美情報を取ってくる
		ArrayList<Rewards> rewardsList = dao.allSelect(familyId, role, userId);

		//情報を入れたRewardをセット(jspでこの情報を全て表示)
		request.setAttribute("rewardsList", rewardsList);

        //準備出来たらjspに飛ばす
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsAdult.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		request.setCharacterEncoding("UTF-8");

				//リクエストボタンが押されたら-----------------------------------------------------------------------------
				if(request.getParameter("req_button")!= null && request.getParameter("req_button").equals("リクエスト")) {

					String stReId = request.getParameter("reward_id");
					int rewardId = Integer.parseInt(stReId);

					String stReq = request.getParameter("re");
					int req = Integer.parseInt(stReq);

					if(req == 0) {
						req = 1;
					}

					//受け取ったデータをdaoに渡してリクエスト処理
					RewardsDAO exdao =new RewardsDAO();
					int result = exdao.exchange(rewardId, req);

					if(result==1) {
						request.setAttribute("msg", "リクエストを送りました");


/*				    if(result==1) {

				    	request.setAttribute("msg", "リクエストを送りました！");
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
*/
				    }else {
			          System.out.println("失敗だよ");
				    }

					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsAdult.jsp");
					dispatcher.forward(request, response);


				//-----------------------------------------------------------------------------------------------------------



				//交換完了ボタンが押されたら---------------------------------------------------------------------------------
				} else if(request.getParameter("complete")!= null && request.getParameter("complete").equals("交換完了")) {

					String stReId = request.getParameter("reward_id");
					int rewardId = Integer.parseInt(stReId);

					String stReq = request.getParameter("re");
					int req = Integer.parseInt(stReq);

					if(req == 1) {
						req = 0;
					}

					//受け取ったデータをdaoに渡して交換完了処理
					RewardsDAO exdao =new RewardsDAO();
					int result = exdao.exchange(rewardId, req);


					if(result==1) {
						request.setAttribute("msg", "リクエストを送りました");
					}


/*				    if(result==1) {

				    	request.setAttribute("msg", "交換完了しました！");
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
*/

				    }else {
			          System.out.println("失敗だよ");
				    }

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsAdult.jsp");
				dispatcher.forward(request, response);

				}

				//-----------------------------------------------------------------------------------------------------------

	}



