package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Users;

public class UsersDAO {

	public Users loginSearch(int familyId,String name) {
		Connection conn = null;

		Users user = new Users();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SELECT文を準備する
			String sql = "SELECT * FROM users WHERE family_id = ? AND name = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, familyId);
			pStmt.setString(2, name);

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

		// パスワードとソルトを入れたfamilyを返す
		return user;
	}

	public boolean userCheck(int familyId,String name) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) FROM users WHERE family_id = ? AND name = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, familyId);
			pStmt.setString(2, name);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// メールアドレスが一致する家族がいたかどうかをチェックする
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
}
