package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Rewards;

//import model.Rewards.java;

public class RewardsDAO {
	//ご褒美に検索機能なんて無いよなと思いつつよく理解していないまま一旦書きしました。
	//勉強してやり直します。
	public ArrayList<Rewards> select(int rewardId, String reward, int reqPoint, String rewardDate, int uid, int request) {
		Connection conn = null;
		ArrayList<Rewards> list = new ArrayList<Rewards>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// １．データベースに接続する準備
			//どのデータベースにつなぐか、つなぐためのidとpwは何かをconに入れる
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// ２．SQL文を準備する（rewardsとusersを繋げる）
			String sql = "SELECT "
					+ "R.REWARD_ID, "
					+ "R.REWARD, "
					+ "R.REQ_POINT, "
					+ "R.REWARD_DATE, "
					+ "R.UID, "
					+ "R.REQUEST"
					+ "FROM REWARDS as R "
					+ "JOIN USERS as U ON U.UID = R.UID "
					+ "WHERE R.REWARD_ID like ? "
					+ "and R.REWARD = ? "
					+ "and R.REQ_POINT = ? "
					+ "and R.REWARD_DATE = ? "
					+ "and R.UID = ? "
					+ "and R.REQUEST = ? ";

			//１と２の情報をpStmtに入れる
			PreparedStatement pStmt = conn.prepareStatement(sql);


			// ?の部分が中途半端なのでSQL文を完成させる
			pStmt.setInt(1, rewardId);
			pStmt.setString(2, reward);
			pStmt.setInt(3, reqPoint);
			pStmt.setString(4, rewardDate);
			pStmt.setInt(5, uid);
			pStmt.setInt(6, request);


			// SQL文を実行する
			ResultSet rs = pStmt.executeQuery();

			//rsの中身をArrayListに移し替える
			while (rs.next()) {
				//空のBeans
				Rewards u = new Rewards();

				//セッターを使って、中にデータベースから取ってきた値を入れていく
				u.setRewardId(rs.getInt("REWARD_ID"));
				u.setReward(rs.getString("REWARD"));
				u.setReqPoint(rs.getInt("REQ_POINT"));
				u.setRewardDate(rs.getString("REWARD_DATE"));
				u.setUid(rs.getInt("UID"));
				u.setRequest(rs.getInt("REQUEST"));

				//全部の値が入ったらArrayListに入れる
				list.add(u);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			list = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					list = null;
				}
			}
		}

		//ArrayListをServletへ返す
		return list;
	}


	//入力された人のデータをrewardsに追加するメソッド-----------------------
	public int insert(int rewardId, String reward, int reqPoint, String rewardDate, int uid, int request) {

		Connection conn = null;
		int result = 0;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// １．データベースに接続する準備
			//どのデータベースにつなぐか、つなぐためのidとpwは何かをconに入れる
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// ２．SQL文を準備する
			String sql = "INSERT INTO REWARDS (REWARD, REQ_POINT, REWARD_DATE, REQUEST) VALUES (?, ?, ?, ?);";

			//１と２の情報をpStmtに入れる
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SQL文を完成させる
			pStmt.setString(1, reward);
			pStmt.setInt(2, reqPoint);
			pStmt.setString(3, rewardDate);
			pStmt.setInt(4, request);

			// SQL文を実行する（insertは登録した件数が返ってくる）
			result = pStmt.executeUpdate();
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

		// Servletに結果を返す（登録された件数をreturn）
		return result;
	}

}
