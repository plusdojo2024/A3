package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Family;

public class FamilyDAO {
	public Family search(Family family) {
		Connection conn = null;
		Family myIdpw = new Family();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT * FROM Idpw WHERE id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, family.getFamilyId());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
/*
			// ユーザーIDとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			myIdpw.setPw(rs.getString("pw"));
			myIdpw.setStrSalt(rs.getString("salt"));
*/
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
		return myIdpw;
	}

	public int searchId(String mail) {
		Connection conn = null;
		int id=0;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/simpleBC", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT family_id FROM FAMILY WHERE mail = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mail);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			rs.next();
			id = rs.getInt("family_id");

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
		return id;
	}

	public boolean familyCheck(String mail) {
		Connection conn = null;
		boolean idResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) FROM family WHERE mail = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, mail);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// メールアドレスが一致する家族がいたかどうかをチェックする
			rs.next();
			if (rs.getInt("COUNT(*)") == 1) {
				idResult = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			idResult = false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			idResult = false;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					idResult = false;
				}
			}
		}

		// 結果を返す
		return idResult;
	}

	public boolean insert(Family family) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO FAMILY(mail,family_name,family_pass,family_salt,family_date) values(?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, family.getMail());
			pStmt.setString(2, family.getFamilyName());
			pStmt.setString(3, family.getFamilyPass());
			pStmt.setString(4, family.getFamilySalt());
			pStmt.setString(5, family.getFamilyDate());



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
