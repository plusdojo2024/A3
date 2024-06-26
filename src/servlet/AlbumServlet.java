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

		//単体テスト用ユーザー作成　使う時は上のdbUserとかをコメントアウトして重複回避
		//Users dbUser = new Users();
		//単体テスト用ファミリーIDをセット
		//dbUser.setFamilyId(22);//各自のfamilyテーブルにある家族情報を確認して入れる

		NotesDAO nDao = new NotesDAO();

		List<Notes> albumList = nDao.getAlbumAllByFamilyId(dbUser.getFamilyId());


		List<Notes> yearMonthList = nDao.getAlbumCategory(dbUser.getFamilyId());
		//System.out.println("アルバム："+albumList.size());
		//System.out.println("年月："+yearMonthList.size());


		request.setAttribute("yearMonthList", yearMonthList);//アルバムセット

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

		HttpSession session = request.getSession();

		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー

		//文字化け禁止
		request.setCharacterEncoding("UTF-8");

		//1枚目の画像のnoteID
		String[] strImageOne = request.getParameterValues("check_one[]");

		//2枚目の画像のnoteID
		String[] strImageTwo = request.getParameterValues("check_two[]");

		//何も選択していなかったら
		if(strImageOne==null && strImageTwo==null) {
			//何もせずAlbumにリダイレクトする
			response.sendRedirect("/A3/AlbumServlet");
			return;
		}else {
			if(strImageOne!=null) {
				//strImageOneと同じ要素数のint型配列宣言
				int[] imageOne = new int[strImageOne.length];

				//Stringの配列をintに変換
				for (int i = 0; i < strImageOne.length; i++) {
					imageOne[i] = Integer.parseInt(strImageOne[i]);
				}
				NotesDAO nDao = new NotesDAO();
				for(int noteId:imageOne) {
					if(nDao.deleteImageOne(noteId)) {
						System.out.println("画像1の削除が成功しました。");
					}else {
						System.out.println("画像1の削除が失敗しました。");
					}
				}
			}

			if(strImageTwo!=null) {
				//strImageOneと同じ要素数のint型配列宣言
				int[] imageTwo = new int[strImageTwo.length];

				//Stringの配列をintに変換
				for (int i = 0; i < strImageTwo.length; i++) {
					imageTwo[i] = Integer.parseInt(strImageTwo[i]);
				}
				NotesDAO nDao = new NotesDAO();
				for(int noteId:imageTwo) {
					if(nDao.deleteImageTwo(noteId)) {
						System.out.println("画像2の削除が成功しました。");
					}else {
						System.out.println("画像2の削除が失敗しました。");
					}
				}
			}

			//Albumにリダイレクトする
			response.sendRedirect("/A3/AlbumServlet");
		}
	}

}
