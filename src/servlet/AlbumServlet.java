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

import dao.NotesDAO;
import model.Notes;
import model.Users;

/**
 * Servlet implementation class AlbumServlet
 */
@WebServlet("/AlbumServlet")
public class AlbumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Users user = (Users) session.getAttribute("user");

		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー
		request.setAttribute("myUser", dbUser);//ヘッダー用情報セット

		//単体テスト用ユーザー作成　使う時は上のdbUserとかをコメントアウトして重複回避
		//Users dbUser = new Users();
		//単体テスト用ファミリーIDをセット
		//dbUser.setFamilyId(22);//各自のfamilyテーブルにある家族情報を確認して入れる

		NotesDAO nDao = new NotesDAO();

		List<Notes> albumList = nDao.getAlbumAllByFamilyId(dbUser.getFamilyId());

		List<Notes> yearMonthList = albumList;
		int tempYear=0;
		int tempMonth=0;

		int index=0;
		for(int i=0;i<albumList.size();i++) {
			if(i==0) {
				tempYear = yearMonthList.get(i).getYear();
				tempMonth = yearMonthList.get(i).getMonth();
			}



			index++;
		}



		request.setAttribute("albumList", albumList);//アルバムセット

		// アルバムページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/album.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//文字化け禁止
		request.setCharacterEncoding("UTF-8");


		// アルバムページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/album.jsp");
		dispatcher.forward(request, response);
	}

}
