package logic;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import logic.FileController;

/**
 * Servlet implementation class SharingRegistServlet
 */
@WebServlet("/SharingRegistServlet")
@MultipartConfig(location = "C:/pleiades/workspace/TestWeb01/WebContent/tmp", maxFileSize = 1024 * 1024 * 10)
public class SharingRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログインページにフォワードする

		String date = request.getParameter("date");//日付データ取得

		request.setAttribute("date", date);//日付セット

		//登録画面表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/sharingRegist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		FileController fC = new FileController();

		Part part = request.getPart("file");

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
		part.delete();
		//結果画面表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
		dispatcher.forward(request, response);

	}

}
