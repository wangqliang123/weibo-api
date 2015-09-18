package weibo4j.wang;

import java.io.IOException;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;
import weibo4j.wang.db.DataWrapper;

public class GetStatus {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws WeiboException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, WeiboException, InterruptedException {
		String access_token = "2.00DjkOMBBYA83E150231e547tqNT7E";
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		
		String status_ids = FileHandler.readTXT(Constants.TXTFILEPATH + "status_ids.txt");
		String ids[] = status_ids.split("\n");
		Timeline tm = new Timeline();
		DataWrapper dw = new DataWrapper();
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			P.pl("Status id: " + id);
			int try_time = 1;
			while (try_time++ <= 10) {
				try {
					Status s = tm.showStatus(id);
					dw.saveStatus(s);
					break;
				} catch (Exception e) {
					e.printStackTrace();
					P.pr("Wait for " + try_time * 10 + " minutes...");
					Thread.sleep(try_time * 1000);
					break;
				}
			}
			
		}
	}
}
