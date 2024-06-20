package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Todo;

public class TodoDAO
{
	public boolean regist(String date, int listId, Todo todo)
	{
		Connection conn = null;
		boolean result = false;

		try
		{
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "INSERT INTO TODO (list_id, todo_date, uid, loop, start_date, end_date, "
					+ "monday, tuesday, wednesday, thursday, friday, saturday, sunday) VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, listId);
			pStmt.setString(2, date);
			pStmt.setInt(3, todo.getUid());
			pStmt.setInt(4, todo.getLoop());
			pStmt.setString(5, todo.getStartDate());
			pStmt.setString(6, todo.getEndDate());
			pStmt.setInt(7, todo.getMonday());
			pStmt.setInt(8, todo.getTuesday());
			pStmt.setInt(9, todo.getWednesday());
			pStmt.setInt(10, todo.getThursday());
			pStmt.setInt(11, todo.getFriday());
			pStmt.setInt(12, todo.getSaturday());
			pStmt.setInt(13, todo.getSunday());

			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		}catch (SQLException e) {
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
	public ArrayList<Todo> select(int uid)
	{
		Connection conn = null;
		ArrayList<Todo> list = new ArrayList<Todo>();
		try
		{
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM TODO WHERE uid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト

			pStmt.setInt(1, uid);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			while(rs.next())
			{
				Todo t = new Todo();

				t.setTodoId(rs.getInt("todo_id"));
				t.setListId(rs.getInt("list_id"));
				t.setTodoDate(rs.getString("todo_date"));
				t.setUid(rs.getInt("uid"));
				t.setLoop(rs.getInt("loop"));
				t.setStartDate(rs.getString("start_date"));
				t.setEndDate(rs.getString("end_date"));
				t.setMonday(rs.getInt("monday"));
				t.setTuesday(rs.getInt("tuesday"));
				t.setWednesday(rs.getInt("wednesday"));
				t.setThursday(rs.getInt("thursday"));
				t.setFriday(rs.getInt("friday"));
				t.setSaturday(rs.getInt("saturday"));
				t.setSunday(rs.getInt("sunday"));

				list.add(t);
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
		return list;
	}
}
