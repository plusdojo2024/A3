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
	public boolean regist(String date, TodoList tl) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "INSERT INTO TODO_LIST VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, tl.getFamily_id());
			pStmt.setString(2, tl.getTask());
			pStmt.setString(3, tl.getCategory());
			pStmt.setInt(4, tl.getGive_point());
			pStmt.setString(5, date);
			pStmt.setString(6, tl.getMemo());
			pStmt.setInt(7, tl.getTodo_delete());

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

	public List<TodoList> select(TodoList tl) {
		Connection conn = null;
		ArrayList<TodoList> todolist = new ArrayList<TodoList>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", " ");


			// SQL文を準備する
			String sql = "SELECT * FROM TODO_LIST WHERE family_id=? AND task=? ORDER BY list_id";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト
			pStmt.setInt(0, tl.getFamily_id());
			pStmt.setString(1, tl.getTask());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				TodoList record = new TodoList(
				rs.getInt("list_id"),
				rs.getInt("family_id"),
				rs.getString("task"),
				rs.getString("category"),
				rs.getInt("give_point"),
				rs.getString("list_date"),
				rs.getString("memo"),
				rs.getInt("todo_delete"));

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
