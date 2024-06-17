package servlet;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import logic.FileLogic;

/**
 * Servlet implementation class FamilyRegistServlet
 */
@WebServlet("/FamilyRegistServlet")
public class FamilyRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 新規家族作成ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/familyRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String familyId = request.getParameter("family_id");
		String familyPass = request.getParameter("family_pass");
		String familyName = request.getParameter("family_name");
		String userName = request.getParameter("user_name");
		String mail = request.getParameter("mail");
		String userPass = request.getParameter("user_pass");
		String color = request.getParameter("color");

		FileLogic fC = new FileLogic();

		Part part = request.getPart("icon");

		String name = fC.getFileName(part);
		LocalDateTime date = LocalDateTime.now();
		//フォーマットを指定
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println(date.format(f));

		String now = date.format(f);
		System.out.println(now);

		System.out.println(name);

		//System.out.println("/uploaded/".exists());
		String absoluteBase = "C:/pleiades/workspace/TestWeb01/WebContent/upload" + "/";
		String relativeBase = "upload/";
		String fileName = now + name;
		String url = absoluteBase + fileName;
		part.write(url);

		String relativePath = relativeBase + fileName;
		request.setAttribute("url", relativePath);//URLセット
		//request.setAttribute("test", "uploaded/2024-06-14free-electron.jpg");//testセット

		try {//eclipseのファイル同期が遅いので少し待機しないとアップロードした画像を表示できない
			Thread.sleep(4000); // 4秒(4000ミリ秒)間だけ処理を止める
		} catch (InterruptedException e) {
		}
		System.out.println(familyId);
		System.out.println(familyPass);
		System.out.println(familyName);
		System.out.println(userName);
		System.out.println(mail);
		System.out.println(userPass);
		System.out.println(color);
		System.out.println("");
	}

}
