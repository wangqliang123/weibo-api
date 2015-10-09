package weibo4j.wang;

import java.io.IOException;

public class ResetLogFiles {

	public static void main(String[] args) throws IOException {
		FileHandler.saveText2Txt("", Constants.TXTFILEPATH + "failed-insertion-sql.txt", true);
		FileHandler.saveText2Txt("", Constants.TXTFILEPATH + "summary.txt", true);
		FileHandler.saveText2Txt("", Constants.TXTFILEPATH + "weiboid_2000_error.txt", true);
		FileHandler.saveText2Txt("", Constants.TXTFILEPATH + "weibo-ids-with-more-than-2000-comments.txt", true);
		FileHandler.saveText2Txt("", "./weibo.log", true);
		FileHandler.saveText2Txt("", "./weibo.log.1", true);
		P.pl("All done.");
	}

}
