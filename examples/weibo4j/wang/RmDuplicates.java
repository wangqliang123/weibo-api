package weibo4j.wang;

import java.io.IOException;
import java.util.ArrayList;

public class RmDuplicates {
	public static void main(String[] args) throws IOException {
		String raw = FileHandler.readTXT(Constants.TXTFILEPATH + "starting_base.txt");
	}
	
	public void rmDuplicateUsers() throws IOException {
		String raw = FileHandler.readTXT(Constants.TXTFILEPATH + "starting_base.txt");
		String lines[] = raw.split("\n");
		ArrayList<String> users = new ArrayList<String>();
		String clean_users = "";
		for (int i = 0; i < lines.length; i++) {
			if (!users.contains(lines[i]))
				users.add(lines[i]);
		}
		int i = 0;
		for (String u: users) {
			clean_users += u + "\n";
			P.pl(i++ + ": " + u);
		}
		FileHandler.appText2File(Constants.TXTFILEPATH + "clean_base.txt", clean_users, true);
	}
}
