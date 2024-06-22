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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
			todolist = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			todolist = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					todolist = null;
				}
			}
		}
		return todolist;
	}

	public List<TodoList> view(int familyId) {
		Connection conn = null;
		ArrayList<TodoList> todoview = new ArrayList<TodoList>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM TODO_LIST WHERE FAMILY_ID=? ORDER BY LIST_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト
			pStmt.setInt(1, familyId);

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

				todoview.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			todoview = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			todoview = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					todoview = null;
				}
			}
		}
		return todoview;
	}

	public boolean update(int familyId, TodoList tl) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "UPDATE TODO_LIST SET task=?, category=?, give_point=?, memo=? WHERE list_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			System.out.println(sql);
			System.out.println(tl.getTask());
			// SQL文を完成させる
			pStmt.setString(1, tl.getTask());

			pStmt.setString(2, tl.getCategory());
			pStmt.setInt(3, tl.getGivePoint());
			pStmt.setString(4, tl.getMemo());
			pStmt.setInt(5, tl.getListId());

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	//日付とflgをもらうことで、その日のやることを取得できる。
	public List<TodoList> selectNow(int uId, String date, int flg) {
		Connection conn = null;
		ArrayList<TodoList> todolist = new ArrayList<TodoList>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM TODO_LIST as L "
					+ "JOIN TODO as T ON L.LIST_ID = T.LIST_ID "
					+ "WHERE T.uId=? AND T.TODO_DATE=? AND T.TODO_COMPLETE=? ORDER BY LIST_ID";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト
			pStmt.setInt(1, uId);
			pStmt.setString(2, date);
			pStmt.setInt(3, flg);

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
		} catch (SQLException e) {
			e.printStackTrace();
			todolist = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			todolist = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					todolist = null;
				}
			}
		}
		return todolist;
	}

	//listIdを引数にしてtaskだけを返す
	public String getTaskByListId(int listId) {
		Connection conn = null;
		String task = "";

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT task FROM TODO_LIST WHERE list_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト
			pStmt.setInt(1, listId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			task = rs.getString("task");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return task;
	}

	public boolean delete(int listId) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "DELETE FROM TODO_LIST WHERE list_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, listId);

			// SQL文を実行する
			if (pStmt.executeUpdate() == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		// 結果を返す
		return result;
	}

	public boolean isTaskOK(int familyId, String task) {
		Connection conn = null;
		boolean taskResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " "); //接続するための文字列、ユーザー名、パスワード

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) FROM TODO_LIST WHERE family_id=? AND task=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, familyId);
			pStmt.setString(2, task);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			rs.next();
			if (rs.getInt("COUNT(*)") == 1) {
				taskResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			taskResult = false;
		} catch (ClassNotFoundException e) { //ドライバの読み込み失敗
			e.printStackTrace(); //エラー内容はコンソールに表示
			taskResult = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					taskResult = false;
				}
			}
		}

		// 結果を返す
		return taskResult;
	}

	public TodoList selectByFamilyIdAndTask(int familyId, String task) {
		Connection conn = null;
		TodoList record = new TodoList();

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
			rs.next();

			record.setFamilyId(rs.getInt("family_id"));
			record.setListId(rs.getInt("list_id"));
			record.setGivePoint(rs.getInt("give_point"));
			record.setMemo(rs.getString("memo"));
			record.setListDate(rs.getString("list_date"));
			record.setTodoDelete(rs.getInt("todo_delete"));
			record.setCategory(rs.getString("category"));
			record.setTask(rs.getString("task"));

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return record;
	}
}
