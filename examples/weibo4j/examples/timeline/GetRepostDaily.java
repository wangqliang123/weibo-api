package weibo4j.examples.timeline;

import weibo4j.Timeline;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;

public class GetRepostDaily {
	public static void main(String[] args) {
		String access_token = "2.00DjkOMBBYA83E057b7f352eKfuiJD";
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		Timeline tm = new Timeline();
		try {
			JSONArray status = tm.getRepostDaily();
			System.out.println(status.toString());

		} catch (WeiboException e) {
			e.printStackTrace();
		}

	}

}
