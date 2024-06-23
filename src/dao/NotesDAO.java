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
	public List<Notes> select(int noteID, String familyID, String title, String note, String noteDate, String imageOne, String imageTwo, String noteUpdate) {
		Connection conn = null;
		List<Notes> memo = new ArrayList<Notes>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SQL文を準備する
			String sql = "SELECT * FROM Notes WHERE "
					+ "noteID LIKE ? AND "
					+ "familyID LIKE ? AND "
					+ "title LIKE ? AND "
					+ "note LIKE ? AND "
					+ "noteDate LIKE ? AND "
					+ "imageOne LIKE ? AND "
					+ "imageTwo LIKE ? AND "
					+ "noteUpdate LIKE ? AND "

					+ " ORDER BY noteDate";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setInt(1, noteID);
			pStmt.setString(2, familyID);
			pStmt.setString(3, title);
			pStmt.setString(4, note);
			pStmt.setString(5, noteDate);
			pStmt.setString(6, imageOne);
			pStmt.setString(7, imageTwo);
			pStmt.setString(8, noteUpdate);


			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Notes record = new Notes(
				rs.getInt("noteID"),
				rs.getInt("familyID"),
				rs.getString("title"),
				rs.getString("note"),
				rs.getString("noteDate"),
				rs.getString("imageOne"),
				rs.getString("imageTwo"),
				rs.getString("noteUpdate")
				);
				memo.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			memo = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			memo = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
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
			pStmt.setInt(1,familyId);

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Notes record = new Notes();

				record.setFamilyID(familyId);
				record.setNoteID(rs.getInt("note_id"));
				record.setTitle(rs.getString("title"));
				record.setNote(rs.getString("note"));
				record.setNoteDate(rs.getString("note_date"));
				record.setImageOne(rs.getString("image_one"));
				record.setImageTwo(rs.getString("image_two"));

				TimeLogic time = new TimeLogic();
				//noteDateを年月日で分割してint型に格納
				record.setYear(time.splitDate(record.getNoteDate())[0]);
				record.setMonth(time.splitDate(record.getNoteDate())[1]);
				record.setDay(time.splitDate(record.getNoteDate())[2]);

				album.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			album = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			album = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
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
				pStmt.setInt(1,familyId);

				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					Notes record = new Notes();

					record.setFamilyID(familyId);
					record.setNoteID(rs.getInt("note_id"));
					record.setNoteDate(rs.getString("note_date"));
					record.setYearMonth(rs.getString("note_date_yearmonth"));

					album.add(record);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				album = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				album = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
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

				String sql = "INSERT INTO NOTES ( TITLE , NOTE, NOTE_DATE, IMAGE_ONE, IMAGE_TWO,NOTE_UPDATE,NOTE_DATE_YEARMONTH) VALUES(? , ?, ?, ?, ?,?,?)";
				if(memo.getImageOne()==null && memo.getImageTwo()==null) {
					sql = "INSERT INTO NOTES ( TITLE , NOTE, NOTE_DATE,NOTE_UPDATE,NOTE_DATE_YEARMONTH) VALUES(? , ?, ?, ?,?)";
				}else if(memo.getImageOne()==null || memo.getImageTwo()==null) {
					sql = "INSERT INTO NOTES ( TITLE , NOTE, NOTE_DATE,IMAGE_ONE,NOTE_UPDATE,NOTE_DATE_YEARMONTH) VALUES(? , ?, ?, ?, ?,?)";
				}
				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）

				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if(memo.getImageOne()==null && memo.getImageTwo()==null) {
					pStmt.setString(1, memo.getTitle());
					pStmt.setString(2, memo.getNote());
					pStmt.setString(3, memo.getNoteDate());
					pStmt.setString(4, memo.getNoteUpdate());
				}else if(memo.getImageOne()==null) {
					pStmt.setString(1, memo.getTitle());
					pStmt.setString(2, memo.getNote());
					pStmt.setString(3, memo.getNoteDate());
					pStmt.setString(4, memo.getImageTwo());
					pStmt.setString(5, memo.getNoteUpdate());
				}else if(memo.getImageTwo()==null){
					pStmt.setString(1, memo.getTitle());
					pStmt.setString(2, memo.getNote());
					pStmt.setString(3, memo.getNoteDate());
					pStmt.setString(4, memo.getImageOne());
					pStmt.setString(5, memo.getNoteUpdate());
				}else {
					pStmt.setString(1, memo.getTitle());
					pStmt.setString(2, memo.getNote());
					pStmt.setString(3, memo.getNoteDate());
					pStmt.setString(4, memo.getImageOne());
					pStmt.setString(5, memo.getImageTwo());
					pStmt.setString(6, memo.getNoteUpdate());
				}
				// SQL文を実行する
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
				String sql = "UPDATE Notes SET title=?, note=?, note_date=?, image_one=?, image_two=? , note_update=? WHERE note?id=?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				if (memo.getTitle() != null && !memo.getTitle().equals("")) {
					pStmt.setString(1, memo.getTitle());
				}
				else {
					pStmt.setString(1, null);
				}
				if (memo.getNote() != null && !memo.getNote().equals("")) {
					pStmt.setString(2, memo.getNote());
				}
				else {
					pStmt.setString(2, null);
				}
				if (memo.getNoteDate() != null && !memo.getNoteDate().equals("")) {
					pStmt.setString(3, memo.getNoteDate());
				}
				else {
					pStmt.setString(3, null);
				}
				if (memo.getImageOne() != null && !memo.getImageOne().equals("")) {
					pStmt.setString(4, memo.getImageOne());
				}
				else {
					pStmt.setString(4, null);
				}
				if (memo.getImageTwo() != null && !memo.getImageTwo().equals("")) {
					pStmt.setString(5, memo.getImageTwo());
				}
				else {
					pStmt.setString(5, null);
				}
				if (memo.getNoteUpdate() != null && !memo.getNoteUpdate().equals("")) {
					pStmt.setString(6, memo.getNoteUpdate());
				}
				else {
					pStmt.setString(6, null);
				}
				// SQL文を実行する
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
					String sql = "DELETE FROM A3 WHERE note_id=?";
					PreparedStatement pStmt = conn.prepareStatement(sql);

					// SQL文を完成させる
					pStmt.setInt(1, note_id);

					// SQL文を実行する
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

				// 結果を返す
				return result;
			}
		}


