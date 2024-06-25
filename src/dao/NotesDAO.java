package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.TimeLogic;
import model.Notes;

public class NotesDAO {
	// 引数で検索項目を指定し、検索結果のリストを返す
	public List<Notes> select(int familyId) {
		Connection conn = null;
		List<Notes> memo = new ArrayList<Notes>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM Notes WHERE FAMILY_ID = ?"
					+ " ORDER BY note_date desc";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる

			pStmt.setInt(1, familyId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Notes record = new Notes();
				record.setFamilyId(rs.getInt("family_id"));
				record.setNoteId(rs.getInt("note_id"));
				record.setTitle(rs.getString("title"));
				record.setNote(rs.getString("note"));
				record.setNoteDate(rs.getString("note_date"));
				record.setImageOne(rs.getString("image_one"));
				record.setImageTwo(rs.getString("image_two"));
				record.setYearMonth(rs.getString("note_date_yearmonth"));

				memo.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			memo = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			memo = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					memo = null;
				}
			}
		}

		// 結果を返す
		return memo;
	}

	//FamilyIdを引数にしてアルバム取得
	public List<Notes> getAlbumAllByFamilyId(int familyId) {
		Connection conn = null;
		List<Notes> album = new ArrayList<Notes>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM Notes WHERE family_id = ? ORDER BY note_date DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, familyId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Notes record = new Notes();

				record.setFamilyId(familyId);
				record.setNoteId(rs.getInt("note_id"));
				record.setTitle(rs.getString("title"));
				record.setNote(rs.getString("note"));
				record.setNoteDate(rs.getString("note_date"));
				record.setImageOne(rs.getString("image_one"));
				record.setImageTwo(rs.getString("image_two"));
				record.setYearMonth(rs.getString("note_date_yearmonth"));

				TimeLogic time = new TimeLogic();
				//noteDateを年月日で分割してint型に格納
				record.setYear(time.splitDate(record.getNoteDate())[0]);
				record.setMonth(time.splitDate(record.getNoteDate())[1]);
				record.setDay(time.splitDate(record.getNoteDate())[2]);

				album.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			album = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			album = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					album = null;
				}
			}
		}

		// 結果を返す
		return album;
	}

	//FamilyIdを引数にしてアルバムの年月重複なしリストを取得
	public List<Notes> getAlbumCategory(int familyId) {
		Connection conn = null;
		List<Notes> album = new ArrayList<Notes>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM Notes WHERE family_id = ? GROUP BY note_date_yearmonth ORDER BY note_date_yearmonth DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, familyId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Notes record = new Notes();

				record.setFamilyId(familyId);
				record.setNoteId(rs.getInt("note_id"));
				record.setNoteDate(rs.getString("note_date"));
				record.setYearMonth(rs.getString("note_date_yearmonth"));

				album.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			album = null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			album = null;
		} finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					album = null;
				}
			}
		}

		// 結果を返す
		return album;
	}

	// 引数memoで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Notes memo) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			String sql = "INSERT INTO NOTES ( TITLE , NOTE, NOTE_DATE, IMAGE_ONE, IMAGE_TWO,NOTE_UPDATE,NOTE_DATE_YEARMONTH ,FAMILY_ID) VALUES(?,? , ?, ?, ?, ?,?,?)";
			if (memo.getImageOne() == null && memo.getImageTwo() == null) {
				sql = "INSERT INTO NOTES ( TITLE , NOTE, NOTE_DATE,NOTE_UPDATE,NOTE_DATE_YEARMONTH ,FAMILY_ID) VALUES(? , ?, ?, ?,? ,?)";
			} else if (memo.getImageOne() == null || memo.getImageTwo() == null) {
				sql = "INSERT INTO NOTES ( TITLE , NOTE, NOTE_DATE,IMAGE_ONE,NOTE_UPDATE,NOTE_DATE_YEARMONTH ,FAMILY_ID) VALUES(? , ?, ?, ?, ?,? ,?)";
			}
			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (memo.getImageOne() == null && memo.getImageTwo() == null) {
				pStmt.setString(1, memo.getTitle());
				pStmt.setString(2, memo.getNote());
				pStmt.setString(3, memo.getNoteDate());
				pStmt.setString(4, memo.getNoteUpdate());
				pStmt.setString(5, memo.getYearMonth());
				pStmt.setInt(6, memo.getFamilyId());

			} else if (memo.getImageOne() == null) {
				pStmt.setString(1, memo.getTitle());
				pStmt.setString(2, memo.getNote());
				pStmt.setString(3, memo.getNoteDate());
				pStmt.setString(4, memo.getImageTwo());
				pStmt.setString(5, memo.getNoteUpdate());
				pStmt.setString(6, memo.getYearMonth());
				pStmt.setInt(7, memo.getFamilyId());

			} else if (memo.getImageTwo() == null) {
				pStmt.setString(1, memo.getTitle());
				pStmt.setString(2, memo.getNote());
				pStmt.setString(3, memo.getNoteDate());
				pStmt.setString(4, memo.getImageOne());
				pStmt.setString(5, memo.getNoteUpdate());
				pStmt.setString(6, memo.getYearMonth());
				pStmt.setInt(7, memo.getFamilyId());

			} else {
				pStmt.setString(1, memo.getTitle());
				pStmt.setString(2, memo.getNote());
				pStmt.setString(3, memo.getNoteDate());
				pStmt.setString(4, memo.getImageOne());
				pStmt.setString(5, memo.getImageTwo());
				pStmt.setString(6, memo.getNoteUpdate());
				pStmt.setString(7, memo.getYearMonth());
				pStmt.setInt(8, memo.getFamilyId());

			}
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

	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Notes memo) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "UPDATE Notes SET title=?, note=?, note_date=?, note_update=? WHERE note_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, memo.getTitle());
			pStmt.setString(2, memo.getNote());
			pStmt.setString(3, memo.getNoteDate());
			pStmt.setString(4, memo.getNoteUpdate());
			pStmt.setInt(5, memo.getNoteId());

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

	// 引数note_idで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(int note_id) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "DELETE FROM Notes WHERE note_id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, note_id);

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

	public Notes getNoteByDay(int familyId, String date) {
		Connection conn = null;

		Notes note = new Notes();
		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM Notes WHERE family_id = ? AND note_date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, familyId);
			pStmt.setString(2, date);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする

			note.setFamilyId(rs.getInt("family_id"));
			note.setNoteId(rs.getInt("note_id"));
			note.setTitle(rs.getString("title"));
			note.setNote(rs.getString("note"));
			note.setNoteDate(rs.getString("note_date"));
			note.setImageOne(rs.getString("image_one"));
			note.setImageTwo(rs.getString("image_two"));
			note.setYearMonth(rs.getString("note_date_yearmonth"));

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
		return note;
	}

	public boolean checkNote(int familyId, String date) {
		Connection conn = null;
		boolean result=false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT COUNT(*) FROM Notes WHERE family_id = ? AND note_date = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, familyId);
			pStmt.setString(2, date);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();


			// 結果表をコレクションにコピーする
			if (rs.getInt("COUNT(*)") == 1) {
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
