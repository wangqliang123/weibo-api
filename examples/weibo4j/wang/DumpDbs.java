package weibo4j.wang;

import java.sql.*;
import java.util.ArrayList;

import weibo4j.model.User;
import weibo4j.wang.db.DataWrapper;
import weibo4j.wang.db.LocalDbConnection;

public class DumpDbs {
	public static void main(String[] args) {
		Connection dumpDb = LocalDbConnection.getDumpConn();
		LocalDbConnection.setConn(dumpDb);
		DataWrapper dw = new DataWrapper();
		P.pl("Getting all users from the dump database...");
		ArrayList<User> allUsers = dw.getAllUsers();
		P.pl("All users are obtained: " + allUsers.size());
		
		Connection conn = LocalDbConnection.getConn();
		LocalDbConnection.setConn(conn);
		int counter = 0;
		for (int i = 0; i < allUsers.size(); i++) {
			if ((i + 1) % 10000 == 0) {
				P.pl("Saving to the weibo db in progress: " + counter + "/" + allUsers.size() + "...");
			}
			User u = allUsers.get(i);
			dw.saveUser(u);
			counter++;
		}
	}
}
