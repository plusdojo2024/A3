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

import dao.RewardsDAO;
import dao.UsersDAO;
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

		UsersDAO dao = new UsersDAO();
		List<Users> ud = dao.selectFamily(user.getFamilyId());
		request.setAttribute("ud", ud);


		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsEdit.jsp");
		dispatcher.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

		//文字化け防止
		request.setCharacterEncoding("UTF-8");

		//jspから入力されたデータを持ってくる
		String reward = request.getParameter("reward");
		String point = request.getParameter("point");
		int p = Integer.parseInt(point);    //pointはStringで受け取るからDBに合わせて型変換
		String name = request.getParameter("name");
		int n = Integer.parseInt(name);    //nameはStringで受け取るからDBに合わせて型変換

		//受け取ったデータをdaoに渡して登録処理
		RewardsDAO dao =new RewardsDAO();
		int result = dao.insert(reward, p, n);


	    //デバッグ用
	    if(result==1) {
	      System.out.println("成功だよ");
	    }else {
          System.out.println("失敗だよ");
	    }

	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/rewardsEdit.jsp");
	dispatcher.forward(request, response);
	}

}
