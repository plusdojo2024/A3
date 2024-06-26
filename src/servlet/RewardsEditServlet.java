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

import dao.ExchangeDAO;
import dao.RewardsDAO;
import dao.UsersDAO;
import model.Exchange;
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

		RewardsDAO rdao = new RewardsDAO();


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsEdit.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		//文字化け防止
		request.setCharacterEncoding("UTF-8");


			//更新ボタンが押されたら-----------------------------------------------------------------------------------------

			if(request.getParameter("edit")!= null && request.getParameter("edit").equals("更新")) {
				//データを持ってくる
				String reward = request.getParameter("reward");
				String stPoint = request.getParameter("req_point");
				int reqPoint = Integer.parseInt(stPoint);    //pointはStringで受け取るからDBに合わせて型変換
				String stUId = request.getParameter("u_id");
				int uId = Integer.parseInt(stUId);    //nameはStringで受け取るからDBに合わせて型変換
				String name = request.getParameter("name");
				String stReId = request.getParameter("reward_id");
				int rewardId = Integer.parseInt(stReId);
				String rewardDate = request.getParameter("reward_date");
		        String stReq = request.getParameter("re");
		        int re = Integer.parseInt(stReq);
//		        String stUid = request.getParameter("u_id");
//		        int uid = Integer.parseInt(stUid);
		        //sessionのデータを使う準備
				HttpSession session = request.getSession();
				//sessionの中のuserデータを持ってくる
				Users user = (Users)session.getAttribute("user");


				//対象者選択のプルダウンで必要な登録ユーザー情報を、画面遷移時に取得する
				UsersDAO dao = new UsersDAO();
				List<Users> ud = dao.selectFamily(user.getFamilyId());
				request.setAttribute("ud", ud);

		        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsEdit.jsp");
		    	dispatcher.forward(request, response);

		    //-------------------------------------------------------------------------------------------------------------



			//削除ボタンが押されたら----------------------------------------------------------------------------------------

			}else if (request.getParameter("delete")!= null && request.getParameter("delete").equals("削除")){
				System.out.println("削除入ったやで");
				String stReId = request.getParameter("reward_id");
				int rewardId = Integer.parseInt(stReId);

				//ココでdaoをnewして、rewardIdをメソッドに渡して削除する
				RewardsDAO dao = new RewardsDAO();
				int result = dao.delete(rewardId);

				if(result==1) {
					request.setAttribute("msg", "削除が成功しました！");
				}

				//sessionにアクセス
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

			//----------------------------------------------------------------------------------------------------------------------



			//リクエストボタンが押されたら------------------------------------------------------------------------------------------
			 } else if(request.getParameter("req_button")!= null && request.getParameter("req_button").equals("リクエスト")) {

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
						request.setAttribute("msg", "リクエストを送りました！");
					}

					if(req==1) {
						request.setAttribute("reqmess", "リクエスト中");
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

			//--------------------------------------------------------------------------------------------------------------------



			//交換完了ボタンが押されたら---------------------------------------------------------------------------------------

			} else if(request.getParameter("complete")!= null && request.getParameter("complete").equals("交換完了")) {

				String reward = request.getParameter("reward");
				String stRewardId = request.getParameter("reward_id");
				int rewardId = Integer.parseInt(stRewardId);
				String stU = request.getParameter("u_id");
				int uId = Integer.parseInt(stU);

				String stPoint = request.getParameter("req_point");
				int reqPoint = Integer.parseInt(stPoint);

				String stReq = request.getParameter("re");
				int req = Integer.parseInt(stReq);

				Exchange ex = new Exchange();
				ex.setName(reward);
				ex.setUid(uId);
				HttpSession session = request.getSession();
				Users u = (Users)session.getAttribute("dbUser");
				int point = u.getHavePoint();
				int re =0;

				if(reqPoint <= point) {
					//交換処理
					ExchangeDAO edao = new ExchangeDAO();
					boolean result = edao.insert(ex);

					if(result == true) {
						//リクエストのフラグを元に戻す
						RewardsDAO exdao =new RewardsDAO();
						re = exdao.exchange(rewardId,0);

						if(re==1) {

						}
					}


				}












				//sessionを使いますよという宣言

				Users user = (Users)session.getAttribute("dbUser");
				int familyId = user.getFamilyId();
				int role = user.getRole();
				int userId = user.getUid();


				if(result==1) {
					request.setAttribute("msg", "交換を完了しました！");
			    }

				RewardsDAO dao1 = new RewardsDAO();
				ArrayList<Rewards> rewardsList = dao1.allSelect(familyId, role, userId);
				request.setAttribute("rewardsList", rewardsList);


			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsAdult.jsp");
			dispatcher.forward(request, response);

		//----------------------------------------------------------------------------------------------------------------



		//更新完了ボタンが押された時------------------------------------------------------------------------------------------

		} else if (request.getParameter("edit2")!=null&&request.getParameter("edit2").equals("更新")) {

			//jspから入力されたデータを持ってくる
			String reward = request.getParameter("reward");
			String point = request.getParameter("req_point");
			int p = Integer.parseInt(point);    //pointはStringで受け取るからDBに合わせて型変換
			String stUId = request.getParameter("u_id");
			int uId = Integer.parseInt(stUId);    //nameはStringで受け取るからDBに合わせて型変換
			String name = request.getParameter("name");
			String stReId = request.getParameter("reward_id");
			int rewardId = Integer.parseInt(stReId);
			String rewardDate = request.getParameter("reward_date");
	        String stReq = request.getParameter("re");
	        int re = Integer.parseInt(stReq);
	        //String stUid = request.getParameter("u_id");
            //int uid = Integer.parseInt(stUid);


			//受け取ったデータをdaoに渡して更新処理
			RewardsDAO dao =new RewardsDAO();
			int result = dao.update(rewardId, reward, p, rewardDate, uId, re);


		    if(result==1) {

		    	request.setAttribute("msg", "変更が成功しました！");
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

		//------------------------------------------------------------------------------------------------------------------

	}

}

