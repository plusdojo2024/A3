package servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.NotesDAO;
import logic.FileLogic;
import logic.TimeLogic;
import model.Message;
import model.Notes;
import model.Users;

/**
 * Servlet implementation class NoteServlet
 */
@WebServlet("/NoteServlet")
@MultipartConfig(location = "C:/pleiades/workspace/A3/WebContent/tmp", maxFileSize = 1024 * 1024 * 10)
public class NoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		TimeLogic tLogic = new TimeLogic();
		request.setAttribute("date", tLogic.nowJpDay());

		// 引継ぎノートページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/note.jsp");
		dispatcher.forward(request, response);
	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		NotesDAO nDao = new NotesDAO();
		TimeLogic time = new TimeLogic();
		String date = time.nowNomalDay();
		FileLogic fL = new FileLogic();

		HttpSession session = request.getSession();

		Users user = (Users) session.getAttribute("user");

		Part part = request.getPart("photo");//写真画像取得
		String relativePath_one=null;
		String relativePath_two=null;

		if (part.getSize() != 0) {//1個目の画像が入ってたら
			String name = fL.getFileName(part);//ファイル名取得

			String absolutePass = fL.setAbsolutePass(name, user.getFamilyId());//絶対パス

			//フォルダのパスだけ作成
			File target = new File("C:/pleiades/workspace/A3/WebContent/upload/family_" + user.getFamilyId() + "/");

			if (!target.exists()) {//フォルダが存在しなければ作成
				target.mkdirs();
				//ファイル保存
				part.write(absolutePass);
			} else {
				part.write(absolutePass);
			}
			//画像の相対パス作成
			relativePath_one = fL.setRelativePath(name, user.getFamilyId());

		} else {
			//入ってなかったら　合ってるかわからない
			relativePath_one = fL.setRelativePath(relativePath_one, 0);;

		}


		Notes note = new Notes();
		Users dbUser = (Users) session.getAttribute("dbUser");//ハッシュ化後ユーザー

		note.setFamilyID(user.getFamilyId());
		note.setImageOne(relativePath_one);
		//どんどんセットしていく

		Message msg = new Message();
		if(nDao.insert(note))
		{
			System.out.println("成功");
			msg.setMessage("引継ぎノートを登録しました。");
			session.setAttribute("message", msg);
			// 引継ぎノートページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/note.jsp");
			dispatcher.forward(request, response);
		}else {
			System.out.println("失敗");
			msg.setMessage("引継ぎノートの登録に失敗しました。");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/note.jsp");
			dispatcher.forward(request, response);

		}
	}

}
