package weibo4j.wang;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import weibo4j.Comments;
import weibo4j.Weibo;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Paging;
import weibo4j.model.WeiboException;
import weibo4j.wang.db.DataWrapper;

public class CommentExtractor {

	// Number of comments to be retrieved every page
	public static int PAGE_SIZE = 200;
	// If last run you stop at 100, you can set it to be 100 for next run.
	public static int START_INDEX = 14;
	// Seconds to pause between retrieving comments of two consecutive weibos
	public static int PAUSE_SECONDS_COMMENTS = 45;
	// Seconds to pause between retrieving two pages of comments
	public static int PAUSE_SECONDS_PAGING = 10;

	public int counter = 0;

	public static void main(String[] args) throws IOException,
			InterruptedException {
		int codeIndex = 0;

		CommentExtractor ce = new CommentExtractor();
		ArrayList<String> codes = ce.getAccessCode();
		String access_token = codes.get(codeIndex++);
		P.pl("access_token: " + access_token);
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);

		DataWrapper dw = new DataWrapper();
		Comments cm = new Comments();
		String raw = FileHandler.readTXT(Constants.TXTFILEPATH
				+ "weiboid_2000.txt");
		FileHandler
				.appText2File(
						Constants.TXTFILEPATH + "summary.txt",
						"---------------------------------------------------------------------------------\r\n"
								+ "["
								+ ce.getCurrentTime()
								+ "] Retrieving comments starting from index "
								+ START_INDEX + "\r\n");

		String ids[] = raw.split("\r\n");
		int count = 0;

		for (String weiboId : ids) {
			try {
				if (count++ < START_INDEX)
					continue;
				P.p(count + " - Getting comments of " + weiboId + "\n");
				FileHandler.appText2File(Constants.TXTFILEPATH + "summary.txt",
						"[" + ce.getCurrentTime() + "] [Weibo " + weiboId
								+ "] " + count + " - ");
				ce.readCommentsOfWeibo(weiboId, cm, dw);
				Thread.sleep(PAUSE_SECONDS_COMMENTS * 1000);
			} catch (Exception e) {
				FileHandler.appText2File(Constants.TXTFILEPATH
						+ "weiboid_2000_error.txt", weiboId + "\r\n");
				FileHandler.appText2File(Constants.TXTFILEPATH + "summary.txt",
						"\r\n");
				e.printStackTrace();
				P.pr(e.getMessage());
				
				if (e.getMessage().indexOf("User requests out of rate limit") >= 0
						&& codeIndex < codes.size()) {
					access_token = codes.get(codeIndex++);
					weibo.setToken(access_token);
					P.pr("\r\nRate out of limit: switching to a new access token "
							+ access_token + " ...");
					Thread.sleep(10 * 1000);
				} else if (e
						.getMessage()
						.equals("A JSONObject text must begin with '{' at character 1:[]")) {
					P.pr("More than 10 pages, skip");
					FileHandler.appText2File(Constants.TXTFILEPATH
							+ "summary.txt",
							"Cannot retrieve comments out of page 10. \r\n");
					FileHandler.appText2File(Constants.TXTFILEPATH
							+ "weibo-ids-with-more-than-2000-comments.txt",
							weiboId + "\r\n");
				} else
					break;
			}
		}
		P.pl("All Done.");
	}

	public String getCurrentTime() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}

	/**
	 * Load comments some Weibo with id `weiboId`.
	 * 
	 * @param weiboId
	 * @param cm
	 * @param dw
	 * @throws WeiboException
	 * @throws InterruptedException
	 */
	public void readCommentsOfWeibo(String weiboId, Comments cm, DataWrapper dw)
			throws WeiboException, InterruptedException {
		Paging pager = new Paging(1, PAGE_SIZE);
		counter = 0;
		CommentWapper cw = readPagingCommentsOfWeibo(weiboId, pager, cm, dw);
		long total = cw.getTotalNumber();

		if (cw.getTotalNumber() > PAGE_SIZE) {
			int totalPages = (int) Math.ceil((cw.getTotalNumber() / PAGE_SIZE)) + 1;
			P.pl("Total number: " + cw.getTotalNumber());
			P.pl("Total pages: " + totalPages);

			for (int i = 2; i <= totalPages; i++) {
				P.pl("Querying page " + i + "...");
				pager.setPage(i);
				readPagingCommentsOfWeibo(weiboId, pager, cm, dw);
				Thread.sleep(PAUSE_SECONDS_PAGING * 1000);
			}
		}
		FileHandler.appText2File(Constants.TXTFILEPATH + "summary.txt", counter
				+ "/" + total + " comments.\r\n");
	}

	/**
	 * Retrieve comments by page
	 * 
	 * @param weiboId
	 * @param pager
	 * @param cm
	 * @param dw
	 * @return
	 * @throws WeiboException
	 */
	public CommentWapper readPagingCommentsOfWeibo(String weiboId,
			Paging pager, Comments cm, DataWrapper dw) throws WeiboException {
		CommentWapper cw = cm.getCommentById(weiboId, pager, 0);
		List<Comment> comments = cw.getComments();
		for (Comment c : comments) {
			dw.saveComment(c);
			counter++;
		}
		return cw;
	}

	/**
	 * Read access code from the file `access-code.txt`.
	 * 
	 * @return
	 */
	public ArrayList<String> getAccessCode() {
		ArrayList<String> codes = new ArrayList<String>();
		try {
			String raw = FileHandler.readTXT(Constants.TXTFILEPATH
					+ "access-code.txt");
			for (String line : raw.split("\r\n")) {
				if (line.trim().length() > 5)
					codes.add(line.trim());
			}
		} catch (IOException e) {
			P.pr("Cannot read access token.");
			e.printStackTrace();
		}
		return codes;
	}
}