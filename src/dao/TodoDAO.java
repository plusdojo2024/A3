package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.TimeLogic;
import model.Todo;
import model.Users;

public class TodoDAO {
	public boolean registOneDay(Todo todo) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "INSERT INTO TODO (list_id, todo_date, uid) VALUES "
					+ "(?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, todo.getListId());
			pStmt.setString(2, todo.getTodoDate());
			pStmt.setInt(3, todo.getUid());

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

	public boolean registLoop(Todo todo) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "INSERT INTO TODO (list_id, todo_date, uid, loop) VALUES "
					+ "(?, ?, ?, ?)";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, todo.getListId());
			pStmt.setString(2, todo.getTodoDate());
			pStmt.setInt(3, todo.getUid());
			pStmt.setInt(4, todo.getLoop());
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

	//登録しようとしたタスクが既にあるかチェック
	public boolean registCheck(Todo todo) {
		Connection conn = null;
		boolean taskResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " "); //接続するための文字列、ユーザー名、パスワード

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) FROM TODO WHERE uid = ? AND list_id = ? AND todo_date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, todo.getUid());
			pStmt.setInt(2, todo.getListId());
			pStmt.setString(3, todo.getTodoDate());

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

	public ArrayList<Todo> select(int uid) {
		Connection conn = null;
		ArrayList<Todo> list = new ArrayList<Todo>();
		try {
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

			while (rs.next()) {
				Todo t = new Todo();

				t.setTodoId(rs.getInt("todo_id"));
				t.setListId(rs.getInt("list_id"));
				t.setTodoDate(rs.getString("todo_date"));
				t.setUid(rs.getInt("uid"));
				t.setLoop(rs.getInt("loop"));
				TodoListDAO tlDao = new TodoListDAO();
				t.setTask(tlDao.getTaskByListId(t.getListId()));

				list.add(t);
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
		return list;
	}

	//カレンダーに表示する用
	//テーブル3つ結合するの面倒いからゴリ押し
	public List<Todo> getCalendarData(int familyId) {
		Connection conn = null;
		ArrayList<Todo> calendarList = new ArrayList<Todo>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT "
					+ "*"
					+ "FROM TODO as T "
					+ "JOIN USERS as U ON U.UID = T.UID "
					+ "WHERE family_id = ? AND todo_complete=0";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト

			pStmt.setInt(1, familyId);
			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Todo t = new Todo();

				t.setTodoId(rs.getInt("todo_id"));
				t.setListId(rs.getInt("list_id"));
				t.setTodoDate(rs.getString("todo_date"));
				t.setUid(rs.getInt("uid"));
				UsersDAO uDao = new UsersDAO();

				Users user = new Users();
				user = uDao.getUser(t.getUid());
				t.setName(user.getName());
				t.setColor(user.getColor());
				TodoListDAO tlDao = new TodoListDAO();
				t.setTask(tlDao.getTaskByListId(t.getListId()));

				calendarList.add(t);
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
		return calendarList;
	}

	public boolean delete(int uid, int listId, String todoDate) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "DELETE FROM todo WHERE uid = ? AND list_id = ? AND todo_date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, uid);
			pStmt.setInt(2, listId);
			pStmt.setString(3, todoDate);

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

	public boolean update(int updateUid, int updateListId, int uid, int listId, String todoDate) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "UPDATE todo SET uid = ?,list_id = ? WHERE uid = ? AND list_id = ? AND todo_date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, updateUid);
			pStmt.setInt(2, updateListId);
			pStmt.setInt(3, uid);
			pStmt.setInt(4, listId);
			pStmt.setString(5, todoDate);

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

	//todayList用

	public List<Todo> getTodayListByUidNow(int uid, String date) {
		Connection conn = null;
		ArrayList<Todo> list = new ArrayList<Todo>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM TODO WHERE uid = ? AND todo_date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト

			pStmt.setInt(1, uid);
			pStmt.setString(2, date);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Todo t = new Todo();

				t.setTodoId(rs.getInt("todo_id"));
				t.setListId(rs.getInt("list_id"));
				t.setTodoDate(rs.getString("todo_date"));
				t.setUid(rs.getInt("uid"));
				TodoListDAO tlDao = new TodoListDAO();
				t.setTask(tlDao.getTaskByListId(t.getListId()));
				t.setComplete(rs.getInt("todo_complete"));

				list.add(t);
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
		return list;

	}

	public List<Todo> getYesterdayListByUidNow(int uid, String date) {
		Connection conn = null;
		ArrayList<Todo> list = new ArrayList<Todo>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM TODO WHERE uid = ? AND todo_date = ? AND todo_complete=0";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト

			pStmt.setInt(1, uid);
			pStmt.setString(2, date);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Todo t = new Todo();

				t.setTodoId(rs.getInt("todo_id"));
				t.setListId(rs.getInt("list_id"));
				t.setTodoDate(rs.getString("todo_date"));
				t.setUid(rs.getInt("uid"));
				TodoListDAO tlDao = new TodoListDAO();
				t.setTask(tlDao.getTaskByListId(t.getListId()));

				list.add(t);
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
		return list;

	}

	public List<Todo> getTaskHistory(int familyId ,String task){
		Connection conn = null;
		ArrayList<Todo> list = new ArrayList<Todo>();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver"); //運転手を雇っている

			// データベースに接続する　connにはどこのデータベースに繋ぐかの地図がいる。通行証であるidとpw(h2に接続するために必要な情報)も入っている
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT "
					+ "*"
					+ "FROM TODO as T "
					+ "JOIN USERS as U ON U.UID = T.UID "
					+ "WHERE family_id = ? AND task = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql); //データベースにアクセスするためにあるオブジェクト

			pStmt.setInt(1, familyId);
			pStmt.setString(2, task);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Todo t = new Todo();

				t.setTodoId(rs.getInt("todo_id"));
				t.setListId(rs.getInt("list_id"));
				t.setUid(rs.getInt("uid"));
				t.setLoop(rs.getInt("loop"));
				t.setTask(task);
				TimeLogic time = new TimeLogic();
				t.setTodoDate(time.changeFormat(rs.getString("todo_date")));
				t.setName(rs.getString("name"));

				list.add(t);
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
		return list;
	}

	public boolean complete(int todoId) {
		Connection conn = null;
		boolean result = false;

		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "UPDATE todo SET todo_complete=1 WHERE todo_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			pStmt.setInt(1, todoId);


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

}
