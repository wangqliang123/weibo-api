package weibo4j.wang.db;

import java.sql.*;

public class LocalDbConnection {
	public static Connection conn;
	public static Connection dump_conn;

	public static void main(String[] args) {
		Connection conn = null;
		try {
			String userName = "root";
			String password = "wang41311147";
			String url = "jdbc:mysql://localhost/sina_weibo";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("Database connection established");
			Statement s = conn.createStatement();
			int count = s
					.executeUpdate("INSERT INTO Relationship (followerId, followeeId)"
							+ " VALUES"
							+ "(1, 2),"
							+ "(3, 4),"
							+ "(5, 6),"
							+ "(1234567890123456, 9876543212345)");
			System.out.println(count + " records inserted.");
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Database connection terminated");
				} catch (Exception e) { /* ignore close errors */
				}
			}
		}
	}

	public static Connection getConn() {
		try {
			if (conn != null && !conn.isClosed())
				return conn;
			else {
				String userName = "root";
				String password = "root";
				String url = "jdbc:mysql://localhost/sina_weibo?useUnicode=true&characterEncoding=gbk";
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, userName, password);
				return conn;
			}
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			System.exit(1);
			return null;
		}
	}
	
	public static Connection getDumpConn() {
		try {
			if (dump_conn != null && !dump_conn.isClosed())
				return dump_conn;
			else {
				String userName = "root";
				String password = "wang41311147";
				String url = "jdbc:mysql://localhost/weibo_qingliang?useUnicode=true&characterEncoding=gbk";
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				dump_conn = DriverManager.getConnection(url, userName, password);
				return dump_conn;
			}
		} catch (Exception e) {
			System.err.println("Cannot connect to database server");
			System.exit(1);
			return null;
		}
	}	
	
	public static void setConn(Connection theConn) {
		conn = theConn;
	}
}