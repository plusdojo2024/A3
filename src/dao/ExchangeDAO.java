package dao;

import java.sql.Connection;
import java.sql.DriverManager;

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
		}
	}

}
