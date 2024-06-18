package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Users;

public class UsersDAO {
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
