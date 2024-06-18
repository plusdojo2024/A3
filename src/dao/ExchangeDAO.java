package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

			String sql = "SELECT * FROM exchange WHERE exchange_id = ?";
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

	public Exchange insert(Exchange exchange)
	{
		Connection conn = null;
		boolean result = false;

		try
		{
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/A3", "sa", " ");

			// SELECT文を準備する
			String sql = "insert into family(reward_id,uid,exchange_date)values(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, exchange.getReward());
			pStmt.setInt(2, exchange.getUid());
			pStmt.setString(3, exchange.getExchangeDate());

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
		return exchange;
	}

}
