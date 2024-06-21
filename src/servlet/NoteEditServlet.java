package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NotesDAO;
import model.Notes;
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
		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();
				Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		request.setAttribute("myUser", dbUser);
		// 履歴ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/noteEdit.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("UTF-8");
		int noteID = Integer.parseInt(request.getParameter("noteID"));

		NotesDAO nDao = new NotesDAO();
        	if (request.getParameter("submit").equals("更新")) {
			// ここを改造する
        		if (nDao.update(new Notes( ))) {	// 更新成功
			// ここまで
				System.out.println("更新完了");
				request.setAttribute("message", "更新しました。");
			}
			else {												// 更新失敗
				System.out.println("失敗");
				request.setAttribute("message", "更新に失敗しました。");
			}
		}
		else {
			if (nDao.delete(noteID)){
				request.setAttribute("message", "削除しました");
			} else {
				request.setAttribute("message", "削除に失敗しました");
			}
		}

		// 結果ページにフォワードする

	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/noteEdit.jsp");
	dispatcher.forward(request, response);
	}

}
