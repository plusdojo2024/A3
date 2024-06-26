package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.TimeLogic;
import model.Exchange;

public class ExchangeDAO
{
	public Exchange search(Exchange exchange)
	{
		Connection conn = null;

		try
		{
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");
			//usersテーブルとエクスチェンジテーブルを結合
			//自分の家族IDを家族IDで検索
			String sql = "";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, exchange.getExchangeId());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// データベースを切断
			if (conn != null) {
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return exchange;
	}

	public boolean insert(Exchange exchange)
	{
		Connection conn = null;
		boolean result = false;

		try
		{
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");
			//現在の日付を取ってくる
			TimeLogic tl = new TimeLogic();
			String now = tl.nowJpDay();

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			//usersテーブルとエクスチェンジテーブルを結合し、自分の家族IDを家族IDで検索

			String sql = "insert into exchange (reward,uid,exchange_date)values(?,?,?)";//sql修正が必要
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, exchange.getReward());
			pStmt.setInt(2, exchange.getUid());
			pStmt.setString(3, now);

			//SQL文を実行する
			// SQL文を実行する
            if (pStmt.executeUpdate() == 1)
            {
                result = true;
            }
        }
		catch (SQLException e)
		{
            e.printStackTrace();
        }
		catch (ClassNotFoundException e)
		{
            e.printStackTrace();
        }
		finally
		{
            // データベースを切断
            if (conn != null) {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
		return result;
	}

	public List<Exchange> getExchangeHistoryByUid(int familyId)
	{
		Connection conn = null;
		List<Exchange> exchangeList = new ArrayList<Exchange>();

		try
		{
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");
			//usersテーブルとエクスチェンジテーブルを結合
			//自分の家族IDを家族IDで検索
			String sql = "SELECT * FROM EXCHANGE as E JOIN USERS as U ON U.UID = E.UID WHERE FAMILY_ID = ?";

			System.out.println(sql);
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, familyId);

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				Exchange e = new Exchange();

				e.setName(rs.getString("name"));
				e.setExchangeDate(rs.getString("exchange_date"));
				e.setReward(rs.getString("reward"));
				e.setUid(rs.getInt("uid"));

				exchangeList.add(e);
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			// データベースを切断
			if (conn != null) {
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return exchangeList;
	}

}
