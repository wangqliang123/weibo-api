package weibo4j.wang.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import weibo4j.wang.FileHandler;

public class Operations {
	
	public boolean exeInsert(String sql) {
		Connection conn = LocalDbConnection.getConn();
		try {
			Statement s = conn.createStatement();
			int count = s.executeUpdate(sql);
			return (count > 0);
		} catch (SQLException e) {
			FileHandler.appText2File(weibo4j.wang.Constants.TXTFILEPATH + "failed-insertion-sql.txt", sql + "\n", false);
			e.printStackTrace();
			return false;
		}
	}

	public ResultSet exeQuery(String sql) {
		Connection conn = LocalDbConnection.getConn();
		Statement s;
		try {
			s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
