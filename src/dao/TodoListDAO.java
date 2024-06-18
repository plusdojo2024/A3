package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.TodoList;

public class TodoListDAO {
	public boolean regist(TodoList tl) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "INSERT INTO TODOLIST VALUES(NULL, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, tl.getFamily_id());
			pStmt.setString(2, tl.getTask());
			pStmt.setString(3, tl.getCategory());
			pStmt.setInt(4, tl.getGive_point());
			pStmt.setString(5, tl.getList_date());
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
}
