package weibo4j.wang;

import java.io.IOException;

public class Tmp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String headContent;
		try {
			headContent = FileHandler.readHeadTXT("D:\\Projects\\weibo4j-oauth2\\file\\sql\\qingliang\\sina_weibo_user.sql", 50);
			P.pl(headContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
