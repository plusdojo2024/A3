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

import dao.ExchangeDAO;
import model.Exchange;
import model.Users;

/**
 * Servlet implementation class ExchangeHistoryServlet
 */
@WebServlet("/ExchangeHistoryServlet")
public class ExchangeHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//セッションスコープからログイン中のユーザー情報を持ってくる
		HttpSession session = request.getSession();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー

		ExchangeDAO eDao = new ExchangeDAO();
		System.out.println(dbUser.getFamilyId()+"ふぁみあい");

		//単体で実行したらエラーになるのでやるならログインから
		List<Exchange> exchangeList =  eDao.getExchangeHistoryByUid(dbUser.getFamilyId());

		session.setAttribute("exchangeList", exchangeList);

		System.out.println(exchangeList.size());
		// 交換履歴ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/exchangeHistory.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
