package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Users;

public class UsersDAO {

	public Users loginSearch(int familyId, String name) {
		Connection conn = null;

		Users user = new Users();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SELECT文を準備する
			String sql = "SELECT * FROM users WHERE family_id = ? AND name = ? AND delete = 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, familyId);
			pStmt.setString(2, name);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// ファミリーIDと名前に一致するユーザー情報を持ってくる
			rs.next();
			user.setFamilyId(rs.getInt("family_id"));
			user.setPw(rs.getString("pw"));
			user.setUserSalt(rs.getString("user_salt"));
			user.setColor(rs.getString("color"));
			user.setAdmin(rs.getInt("admin"));
			user.setRole(rs.getInt("role"));
			user.setHavePoint(rs.getInt("have_point"));
			user.setIcon(rs.getString("icon"));
			user.setDelete(rs.getInt("delete"));
			user.setName(rs.getString("name"));
			user.setUid(rs.getInt("uid"));

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

		// パスワードとソルトを入れたuserを返す
		return user;
	}

	public boolean userCheck(int familyId, String name) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) FROM users WHERE family_id = ? AND name = ? AND delete = 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, familyId);
			pStmt.setString(2, name);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 家族の中に一致する名前があるかチェックする
			rs.next();
			if (rs.getInt("COUNT(*)") == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			result = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					result = false;
				}
			}
		}

		// 結果を返す
		return result;
	}

	//ユーザー登録
	public boolean insert(Users user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "INSERT INTO Users(family_id,name,pw,user_salt,color,have_point,icon,admin,role,user_date,user_update) values(?,?,?,?,?,?,?,?,?,?,now())";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, user.getFamilyId());
			pStmt.setString(2, user.getName());
			pStmt.setString(3, user.getPw());
			pStmt.setString(4, user.getUserSalt());
			pStmt.setString(5, user.getColor());
			pStmt.setInt(6, user.getHavePoint());
			pStmt.setString(7, user.getIcon());
			pStmt.setInt(8, user.getAdmin());
			pStmt.setInt(9, user.getRole());
			pStmt.setString(10, user.getUserDate());

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

	//ユーザー更新
	public boolean update(Users user) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "UPDATE users SET name = ?,color = ?,icon = ?, pw = ?,user_salt = ? , user_update = now() WHERE uid = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getColor());
			pStmt.setString(3, user.getIcon());
			pStmt.setString(4, user.getPw());
			pStmt.setString(5, user.getUserSalt());
			pStmt.setInt(6, user.getUid());

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

	//uidでユーザーを検索して日付以外を全て持ってくる
	public Users getUser(int uid) {
		Connection conn = null;

		Users user = new Users();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SELECT文を準備する
			String sql = "SELECT * FROM users WHERE uid = ? AND delete = 0";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,uid);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// メールアドレスに一致するユーザー情報を持ってくる
			rs.next();
			user.setFamilyId(rs.getInt("family_id"));
			user.setPw(rs.getString("pw"));
			user.setUserSalt(rs.getString("user_salt"));
			user.setColor(rs.getString("color"));
			user.setAdmin(rs.getInt("admin"));
			user.setRole(rs.getInt("role"));
			user.setHavePoint(rs.getInt("have_point"));
			user.setIcon(rs.getString("icon"));
			user.setDelete(rs.getInt("delete"));
			user.setName(rs.getString("name"));
			user.setUid(rs.getInt("uid"));

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

		return user;
	}

	//引数のユーザーIDに一致するユーザーを論理削除する
	public boolean delete(int uid) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "UPDATE users SET delete = 1, user_update = now() WHERE uid = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,uid);


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

	public List<Users> selectFamily(int familyId) {
		Connection conn = null;
		List<Users> familyList = new ArrayList<Users>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");
			// SQL文を準備する
			String sql;
			sql = "SELECT * FROM users WHERE family_id = ? AND delete=0";


			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる

			pStmt.setInt(1, familyId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Users record = new Users();
				record.setAdmin(rs.getInt("admin"));//管理者フラッグ
				record.setRole(rs.getInt("role"));//ロール
				record.setColor(rs.getString("color"));//個人カラー
				record.setIcon(rs.getString("icon"));//アイコン画像パス
				record.setUid(rs.getInt("uid"));//ユーザーID
				record.setName(rs.getString("name"));//名前
				record.setHavePoint(rs.getInt("have_point"));//保有ポイント


				familyList.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			familyList = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			familyList = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					familyList = null;
				}
			}
		}

		// 結果を返す
		return familyList;
	}
}
