package weibo4j;

import weibo4j.http.HttpClient;
import weibo4j.model.PostParameter;
import weibo4j.model.User;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.util.WeiboConfig;
import weibo4j.wang.P;

public class Users {
	
	public static HttpClient client = new HttpClient();


	/*----------------------------鐢ㄦ埛鎺ュ彛----------------------------------------*/
	/**
	 * 鏍规嵁鐢ㄦ埛ID鑾峰彇鐢ㄦ埛淇℃伅
	 * 
	 * @param uid
	 *            闇�鏌ヨ鐨勭敤鎴稩D
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/users/show">users/show</a>
	 * @since JDK 1.5
	 */
	public User showUserById(String uid) throws WeiboException {
		return new User(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "users/show.json",
				new PostParameter[] { new PostParameter("uid", uid) })
				.asJSONObject());
	}

	/**
	 * 鏍规嵁鐢ㄦ埛ID鑾峰彇鐢ㄦ埛淇℃伅
	 * 
	 * @param screen_name
	 *            鐢ㄦ埛鏄电О
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/users/show">users/show</a>
	 * @since JDK 1.5
	 */
	public User showUserByScreenName(String screen_name) throws WeiboException {
		return new User(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "users/show.json",
				new PostParameter[] { new PostParameter("screen_name",
						screen_name) }).asJSONObject());
	}

	/**
	 * 閫氳繃涓�鍖栧煙鍚嶈幏鍙栫敤鎴疯祫鏂欎互鍙婄敤鎴锋渶鏂扮殑涓�潯寰崥
	 * 
	 * @param domain
	 *            闇�鏌ヨ鐨勪釜鎬у寲鍩熷悕銆�
	 * @return User
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/users/domain_show">users/domain_show</a>
	 * @since JDK 1.5
	 */
	public User showUserByDomain(String domain) throws WeiboException {
		return new User(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "users/domain_show.json",
				new PostParameter[] { new PostParameter("domain", domain) })
				.asJSONObject());
	}
	/**
	 * 鎵归噺鑾峰彇鐢ㄦ埛鐨勭矇涓濇暟銆佸叧娉ㄦ暟銆佸井鍗氭暟
	 * 
	 * @param uids
	 *            闇�鑾峰彇鏁版嵁鐨勭敤鎴稶ID锛屽涓箣闂寸敤閫楀彿鍒嗛殧锛屾渶澶氫笉瓒呰繃100涓�
	 * @return jsonobject
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/users/domain_show">users/domain_show</a>
	 * @since JDK 1.5
	 */
	public JSONArray getUserCount(String uids) throws WeiboException{
		return  Weibo.client.get(WeiboConfig.getValue("baseURL") + "users/counts.json",
				new PostParameter[] { new PostParameter("uids", uids)}).asJSONArray();
	}
}
