package weibo4j.wang;

import java.io.IOException;
import java.util.List;

import weibo4j.Comments;
import weibo4j.Weibo;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.WeiboException;
import weibo4j.wang.db.DataWrapper;

public class CommentExtractor {

	public static void main(String[] args) throws IOException {
		CommentExtractor ec = new CommentExtractor();
		String access_token = ec.getAccessCode().trim();
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);

		DataWrapper dw = new DataWrapper();
		Comments cm = new Comments();
		String raw = FileHandler.readTXT(Constants.TXTFILEPATH
				+ "weiboid_2000.txt");

		String ids[] = raw.split("\r\n");
		int start = 0;
		int count = 0;

		for (String weiboId : ids) {
			try {
				P.p(count + " - Getting comments of " + weiboId + "\n");
				if (count++ < start)
					continue;
				ec.readCommentsOfWeibo(weiboId, cm, dw);
				Thread.sleep(15 * 1000);
			} catch (Exception e1) {
				FileHandler.appText2File(Constants.TXTFILEPATH
						+ "weiboid_2000_error.txt", weiboId + "\r\n");
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Load comments some Weibo with id `weiboId`.
	 * 
	 * @param weiboId
	 * @param cm
	 * @param dw
	 * @throws WeiboException
	 */
	public void readCommentsOfWeibo(String weiboId, Comments cm, DataWrapper dw)
			throws WeiboException {
		CommentWapper cw = cm.getCommentById(weiboId);
		List<Comment> comments = cw.getComments();
		for (Comment c : comments) {
			dw.saveComment(c);
		}
	}

	/**
	 * Read access code from the file `access-code.txt`.
	 * 
	 * @return
	 */
	public String getAccessCode() {
		try {
			return FileHandler.readTXT(Constants.TXTFILEPATH
					+ "access-code.txt");
		} catch (IOException e) {
			P.pr("Cannot read access token.");
			e.printStackTrace();
			return "";
		}
	}
}