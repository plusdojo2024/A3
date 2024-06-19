package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TodoList;

public class TodoListDAO {
	public boolean regist(String date, int familyID, TodoList tl) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "INSERT INTO TODO_LIST VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, familyID);
			pStmt.setString(2, tl.getTask());
			pStmt.setString(3, tl.getCategory());
			pStmt.setInt(4, tl.getGivePoint());
			pStmt.setString(5, date);
			pStmt.setString(6, tl.getMemo());
			pStmt.setInt(7, tl.getTodoDelete());

			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	public List<TodoList> select(int familyId, String task) {
		Connection conn = null;
		ArrayList<TodoList> todolist = new ArrayList<TodoList>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");


			// SQL文を準備する
			String sql = "SELECT * FROM TODO_LIST WHERE FAMILY_ID=? AND TASK=? ORDER BY LIST_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト
			pStmt.setInt(1, familyId);
			pStmt.setString(2, task);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				TodoList record = new TodoList();
				record.setFamilyId(rs.getInt("family_id"));
				record.setListId(rs.getInt("list_id"));
				record.setGivePoint(rs.getInt("give_point"));
				record.setMemo(rs.getString("memo"));
				record.setListDate(rs.getString("list_date"));
				record.setTodoDelete(rs.getInt("todo_delete"));
				record.setCategory(rs.getString("category"));
				record.setTask(rs.getString("task"));

				todolist.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			todolist = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			todolist = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					todolist = null;
				}
			}
		}
		return todolist;
	}
}
