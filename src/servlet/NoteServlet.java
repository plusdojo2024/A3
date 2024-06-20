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

import logic.FileLogic;
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

		FileLogic fL = new FileLogic();


		HttpSession session = request.getSession();

		Users user = (Users) session.getAttribute("user");

	Part part = request.getPart("photo");//写真画像取得

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
	String relativePath = fL.setRelativePath(name, user.getFamilyId());
	}


}
