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
import logic.TimeLogic;
import model.Message;
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

		NotesDAO ndao = new NotesDAO();
		List<Notes> noteList = ndao.select(dbUser.getFamilyId());
		request.setAttribute("noteList", noteList);


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
		int noteId = Integer.parseInt(request.getParameter("note_id"));
		String noteDate = request.getParameter("note_date");
		String title = request.getParameter("title");
		String note = request.getParameter("note");

TimeLogic time = new TimeLogic();



		NotesDAO nDao = new NotesDAO();
		Notes notes = new Notes();
		notes.setNoteId(noteId);
		notes.setNoteDate(noteDate);
		notes.setTitle(title);
		notes.setNote(note);
		notes.setNoteUpdate(time.nowJpDay());

		Message msg = new Message();
        	if (request.getParameter("submit").equals("更新")) {
			// ここを改造する
        		if (nDao.update(notes)) {	// 更新成功
			// ここまでs
				System.out.println("更新完了");
				msg.setMessage("履歴更新しました");
				request.setAttribute("message",msg);
			}
			else {												// 更新失敗
				System.out.println("失敗");
				msg.setMessage("失敗");
				request.setAttribute("message",msg);
			}

		}

		if (request.getParameter("submit").equals("削除")) {
			if (nDao.delete(noteId)){
				msg.setMessage("削除しました");
				request.setAttribute("message", msg);
			} else {
				msg.setMessage("削除に失敗しました");
				request.setAttribute("message", msg);

			}

		}

		// 結果ページにフォワードする

	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/noteEdit.jsp");
	dispatcher.forward(request, response);
	}

}
