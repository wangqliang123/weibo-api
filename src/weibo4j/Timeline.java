package weibo4j;

import java.util.List;
import weibo4j.http.ImageItem;
import weibo4j.model.Emotion;
import weibo4j.model.Paging;
import weibo4j.model.PostParameter;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONArray;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class Timeline {

	/*----------------------------璇诲彇鎺ュ彛----------------------------------------*/

	/**
	 * 杩斿洖鏈�柊鐨勫叕鍏卞井鍗�
	 * 
	 * @return list of statuses of the Public Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/public_timeline">statuses/public_timeline
	 *      </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getPublicTimeline() throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/public_timeline.json"));
	}

	/**
	 * 杩斿洖鏈�柊鐨勫叕鍏卞井鍗�
	 * 
	 * @param count
	 *            鍗曢〉杩斿洖鐨勮褰曟潯鏁帮紝榛樿涓�0銆�
	 * @param baseApp
	 *            鏄惁浠呰幏鍙栧綋鍓嶅簲鐢ㄥ彂甯冪殑淇℃伅銆�涓烘墍鏈夛紝1涓轰粎鏈簲鐢ㄣ�
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/public_timeline">statuses/public_timeline
	 *      </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getPublicTimeline(int count, int baseApp) throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/public_timeline.json", new PostParameter[] {
						new PostParameter("count", count),
						new PostParameter("base_app", baseApp) }));

	}

	/**
	 * 鑾峰彇褰撳墠鐧诲綍鐢ㄦ埛鍙婂叾鎵�叧娉ㄧ敤鎴风殑鏈�柊20鏉″井鍗氭秷鎭�
	 * 鍜岀敤鎴风櫥褰�http://weibo.com 鍚庡湪鈥滄垜鐨勯椤碘�涓湅鍒扮殑鍐呭鐩稿悓銆�
	 * This method calls
	 * http://api.t.sina.com.cn/statuses/friends_timeline.format
	 * 
	 * @return list of the Friends Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline">
	 *      statuses/friends_timeline </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getFriendsTimeline() throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig.getValue("baseURL") + "statuses/friends_timeline.json"));

	}

	/**
	 * 鑾峰彇褰撳墠鐧诲綍鐢ㄦ埛鍙婂叾鎵�叧娉ㄧ敤鎴风殑鏈�柊寰崥娑堟伅銆�br/>
	 * 鍜岀敤鎴风櫥褰�http://weibo.com 鍚庡湪鈥滄垜鐨勯椤碘�涓湅鍒扮殑鍐呭鐩稿悓銆�
	 * 
	 * @param paging
	 *            鐩稿叧鍒嗛〉鍙傛暟
	 * @param 杩囨护绫诲瀷ID锛�锛氬叏閮ㄣ�1锛氬師鍒涖�2锛氬浘鐗囥�3锛氳棰戙�4锛氶煶涔愶紝榛樿涓�銆�
	 * @return list of the Friends Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline">
	 *      statuses/friends_timeline </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getFriendsTimeline(Integer baseAPP, Integer feature,
			Paging paging) throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/friends_timeline.json",
				new PostParameter[] {
						new PostParameter("base_app", baseAPP.toString()),
						new PostParameter("feature", feature.toString()) },
				paging));
		}
	/**
	 * 鑾峰彇褰撳墠鐧诲綍鐢ㄦ埛鍙婂叾鎵�叧娉ㄧ敤鎴风殑鏈�柊20鏉″井鍗氭秷鎭�
	 * 鍜岀敤鎴风櫥褰�http://weibo.com 鍚庡湪鈥滄垜鐨勯椤碘�涓湅鍒扮殑鍐呭鐩稿悓銆�
	 * This method calls
	 * http://api.t.sina.com.cn/statuses/friends_timeline.format
	 * 
	 * @return list of the Friends Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/statuses/friends_timeline/ids">
	 *      statuses/friends_timeline/ids </a>
	 * @since JDK 1.5
	 */
	public JSONObject getFriendsTimelineIds() throws WeiboException {
		return Weibo.client.get(WeiboConfig.getValue("baseURL") + "statuses/friends_timeline/ids.json").asJSONObject();

	}
	public JSONObject getFriendsTimelineIds(Integer baseAPP, Integer feature,
			Paging paging) throws WeiboException {
		return Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/friends_timeline/ids.json",
				new PostParameter[] {
						new PostParameter("base_app", baseAPP.toString()),
						new PostParameter("feature", feature.toString()) },
				paging).asJSONObject();
		}
	/**
	 * 鑾峰彇褰撳墠鐧诲綍鐢ㄦ埛鍙婂叾鎵�叧娉ㄧ敤鎴风殑鏈�柊寰崥娑堟伅銆�br/>
	 * 鍜岀敤鎴风櫥褰�http://weibo.com 鍚庡湪鈥滄垜鐨勯椤碘�涓湅鍒扮殑鍐呭鐩稿悓銆�
	 * 
	 * @return list of status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a href="http://open.weibo.com/wiki/2/statuses/home_timeline">
	 *      statuses/home_timeline </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getHomeTimeline() throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/home_timeline.json"));

	}

	/**
	 * 鑾峰彇褰撳墠鐧诲綍鐢ㄦ埛鍙婂叾鎵�叧娉ㄧ敤鎴风殑鏈�柊寰崥娑堟伅銆�br/>
	 * 鍜岀敤鎴风櫥褰�http://weibo.com 鍚庡湪鈥滄垜鐨勯椤碘�涓湅鍒扮殑鍐呭鐩稿悓銆�
	 * 
	 * @param paging
	 *            鐩稿叧鍒嗛〉鍙傛暟
	 * @param 杩囨护绫诲瀷ID
	 *            锛�锛氬叏閮ㄣ�1锛氬師鍒涖�2锛氬浘鐗囥�3锛氳棰戙�4锛氶煶涔愶紝榛樿涓�銆�
	 * @return list of the Friends Timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a href="http://open.weibo.com/wiki/2/Statuses/home_timeline">
	 *      statuses/home_timeline </a>
	 * @since JDK 1.5
	 */
	public StatusWapper getHomeTimeline(Integer baseAPP, Integer feature,
			Paging paging) throws WeiboException {
		return Status
				.constructWapperStatus(Weibo.client.get(
						WeiboConfig.getValue("baseURL") + "statuses/home_timeline.json",
						new PostParameter[] {
								new PostParameter("base_app", baseAPP.toString()),
								new PostParameter("feature", feature.toString()) },
						paging));
	}

	/**
	 * 鑾峰彇鏌愪釜鐢ㄦ埛鏈�柊鍙戣〃鐨勫井鍗氬垪琛�
	 * 
	 * @return list of the user_timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getUserTimeline() throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/user_timeline.json"));
	}
	public StatusWapper getUserTimelineByUid(String uid) throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/user_timeline.json",new PostParameter[]{
			new PostParameter("uid", uid)
		}));
	}
	public StatusWapper getUserTimelineByName(String screen_name) throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/user_timeline.json",new PostParameter[]{
			new PostParameter("screen_name", screen_name)
		}));
	}
	/**
	 * 鑾峰彇鏌愪釜鐢ㄦ埛鏈�柊鍙戣〃鐨勫井鍗氬垪琛�
	 * 
	 * @param uid
	 *            闇�鏌ヨ鐨勭敤鎴稩D銆�
	 * @param screen_name
	 *            闇�鏌ヨ鐨勭敤鎴锋樀绉般�
	 * @param count
	 *            鍗曢〉杩斿洖鐨勮褰曟潯鏁帮紝榛樿涓�0銆�
	 * @param page
	 *            杩斿洖缁撴灉鐨勯〉鐮侊紝榛樿涓�銆�
	 * @param base_app
	 *            鏄惁鍙幏鍙栧綋鍓嶅簲鐢ㄧ殑鏁版嵁銆�涓哄惁锛堟墍鏈夋暟鎹級锛�涓烘槸锛堜粎褰撳墠搴旂敤锛夛紝榛樿涓�銆�
	 * @param feature
	 *            杩囨护绫诲瀷ID锛�锛氬叏閮ㄣ�1锛氬師鍒涖�2锛氬浘鐗囥�3锛氳棰戙�4锛氶煶涔愶紝榛樿涓�銆�
	 * @return list of the user_timeline
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getUserTimelineByUid(String uid, Paging page,
			Integer base_app, Integer feature) throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(
						WeiboConfig.getValue("baseURL")	+ "statuses/user_timeline.json",
						new PostParameter[] {
								new PostParameter("uid", uid),
								new PostParameter("base_app", base_app.toString()),
								new PostParameter("feature", feature.toString()) },
						page));
	}
	public StatusWapper getUserTimelineByName(String screen_name, Paging page,Integer base_app, Integer feature) throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(
						WeiboConfig.getValue("baseURL")	+ "statuses/user_timeline.json",
						new PostParameter[] {
								new PostParameter("screen_name", screen_name),
								new PostParameter("base_app", base_app.toString()),
								new PostParameter("feature", feature.toString()) },
						page));
	}
	/**
	 * 鑾峰彇鏌愪釜鐢ㄦ埛鏈�柊鍙戣〃鐨勫井鍗氬垪琛↖D
	 * 
	 * @return user_timeline IDS 
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/user_timeline">statuses/user_timeline</a>
	 * @since JDK 1.5
	 */
	public JSONObject getUserTimelineIdsByUid(String uid) throws WeiboException{
		return Weibo.client.get(WeiboConfig.getValue("baseURL")+"statuses/user_timeline/ids.json",new PostParameter[]{
			new PostParameter("uid", uid)
		}).asJSONObject();
	}
	public JSONObject getUserTimelineIdsByName(String screen_name) throws WeiboException{
		return Weibo.client.get(WeiboConfig.getValue("baseURL")+"statuses/user_timeline/ids.json",new PostParameter[]{
			new PostParameter("screen_name", screen_name)
		}).asJSONObject();
	}
	/**
	 * 鑾峰彇鎸囧畾寰崥鐨勮浆鍙戝井鍗氬垪琛�
	 * 
	 * @param id
	 *            闇�鏌ヨ鐨勫井鍗欼D
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_timeline">statuses/repost_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getRepostTimeline(String id) throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "statuses/repost_timeline.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * 鑾峰彇鎸囧畾寰崥鐨勮浆鍙戝井鍗氬垪琛�
	 * 
	 * @param id
	 *            闇�鏌ヨ鐨勫井鍗欼D
	 * @param count
	 *            鍗曢〉杩斿洖鐨勮褰曟潯鏁帮紝榛樿涓�0
	 * @param page
	 *            杩斿洖缁撴灉鐨勯〉鐮侊紝榛樿涓�
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_timeline">statuses/repost_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getRepostTimeline(String id, Paging page)
			throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(
				WeiboConfig.getValue("baseURL")
						+ "statuses/repost_timeline.json",
				new PostParameter[] { new PostParameter("id", id) }, page));
	}
	/**
	 * 鑾峰彇鎸囧畾寰崥鐨勮浆鍙戝井鍗氬垪琛�
	 * 
	 * @param id
	 *            闇�鏌ヨ鐨勫井鍗欼D
	 * @return ids
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_timeline/ids">statuses/repost_timeline/ids</a>
	 * @since JDK 1.5
	 */
	public JSONObject getRepostTimelineIds(String id) throws WeiboException {
		return Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/repost_timeline/ids.json",
				new PostParameter[] { new PostParameter("id", id) }).asJSONObject();
	}
	/**
	 * 鑾峰彇褰撳墠鐢ㄦ埛鏈�柊杞彂鐨勫井鍗氬垪琛�
	 * 
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_by_me">statuses/repost_by_me</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getRepostByMe() throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/repost_by_me.json"));
	}

	/**
	 * 鑾峰彇褰撳墠鐢ㄦ埛鏈�柊杞彂鐨勫井鍗氬垪琛�
	 * 
	 * @param page
	 *            杩斿洖缁撴灉鐨勯〉鐮侊紝榛樿涓�
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost_by_me">statuses/repost_by_me</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getRepostByMe(Paging page) throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/repost_by_me.json",null, page));
	}

	/**
	 * 鑾峰彇鏈�柊鐨勬彁鍒扮櫥褰曠敤鎴风殑寰崥鍒楄〃锛屽嵆@鎴戠殑寰崥
	 * 
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/mentions">statuses/mentions</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getMentions() throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/mentions.json"));
	}

	/**
	 * 鑾峰彇鏈�柊鐨勬彁鍒扮櫥褰曠敤鎴风殑寰崥鍒楄〃锛屽嵆@鎴戠殑寰崥
	 * 
	 * @param count
	 *            鍗曢〉杩斿洖鐨勮褰曟潯鏁帮紝榛樿涓�0銆�
	 * @param page
	 *            杩斿洖缁撴灉鐨勯〉鐮侊紝榛樿涓�銆�
	 * @param filter_by_author
	 *            浣滆�绛涢�绫诲瀷锛�锛氬叏閮ㄣ�1锛氭垜鍏虫敞鐨勪汉銆�锛氶檶鐢熶汉锛岄粯璁や负0銆�
	 * @param filter_by_source
	 *            鏉ユ簮绛涢�绫诲瀷锛�锛氬叏閮ㄣ�1锛氭潵鑷井鍗氥�2锛氭潵鑷井缇わ紝榛樿涓�銆�
	 * @param filter_by_type
	 *            鍘熷垱绛涢�绫诲瀷锛�锛氬叏閮ㄥ井鍗氥�1锛氬師鍒涚殑寰崥锛岄粯璁や负0銆�
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/mentions">statuses/mentions</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getMentions(Paging page, Integer filter_by_author,
			Integer filter_by_source, Integer filter_by_type)
			throws WeiboException {
		return Status.constructWapperStatus(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/mentions.json",
				new PostParameter[] {
						new PostParameter("filter_by_author", filter_by_author.toString()),
						new PostParameter("filter_by_source", filter_by_source.toString()),
						new PostParameter("filter_by_type", filter_by_type.toString()) }, page));
	}
	/**
	 * 鑾峰彇鏈�柊鐨勬彁鍒扮櫥褰曠敤鎴风殑寰崥ID鍒楄〃锛屽嵆@鎴戠殑寰崥
	 * 
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/mentions/ids">statuses/mentions/ids</a>
	 * @since JDK 1.5
	 */
	public JSONObject getMentionsIds() throws WeiboException {
		return Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/mentions/ids.json").asJSONObject();
	}
	public JSONObject getMentionsIds(Paging page, Integer filter_by_author,
			Integer filter_by_source, Integer filter_by_type)
			throws WeiboException {
		return Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/mentions/ids.json",
				new PostParameter[] {
						new PostParameter("filter_by_author", filter_by_author.toString()),
						new PostParameter("filter_by_source", filter_by_source.toString()),
						new PostParameter("filter_by_type", filter_by_type.toString()) }, page).asJSONObject();
	}
	/**
	 * 鑾峰彇鍙屽悜鍏虫敞鐢ㄦ埛鐨勬渶鏂板井鍗�
	 * 
	 * @return list of Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/bilateral_timeline">statuses/bilateral_timeline</a>
	 * @since JDK 1.5
	 */
	public StatusWapper getBilateralTimeline() throws WeiboException{
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig.getValue("baseURL")+"statuses/bilateral_timeline.json"));
	}
	public StatusWapper getBilateralTimeline(Integer base_app,Integer feature) throws WeiboException{
		return Status.constructWapperStatus(Weibo.client.get(WeiboConfig.getValue("baseURL")+"statuses/bilateral_timeline.json",
				new PostParameter[]{
			new PostParameter("base_app", base_app),
			new PostParameter("feature",feature)
		}));
	}
	/**
	 * 鏍规嵁寰崥ID鑾峰彇鍗曟潯寰崥鍐呭
	 * 
	 * @param id
	 *            闇�鑾峰彇鐨勫井鍗欼D銆�
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.1
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/show">statuses/show</a>
	 * @since JDK 1.5
	 */
	public Status showStatus(String id) throws WeiboException {
		return new Status(Weibo.client.get(WeiboConfig.getValue("baseURL")
				+ "statuses/show.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * 閫氳繃寰崥ID鑾峰彇鍏禡ID
	 * 
	 * @param id
	 *            闇�鏌ヨ鐨勫井鍗欼D锛屾壒閲忔ā寮忎笅锛岀敤鍗婅閫楀彿鍒嗛殧锛屾渶澶氫笉瓒呰繃20涓�
	 * @param type
	 *            鑾峰彇绫诲瀷锛�锛氬井鍗氥�2锛氳瘎璁恒�3锛氱淇★紝榛樿涓�銆�
	 * @return Status's mid
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/querymid">statuses/querymid</a>
	 * @since JDK 1.5
	 */
	public JSONObject QueryMid(Integer type, String id) throws WeiboException {
		return Weibo.client.get(WeiboConfig.getValue("baseURL") + "statuses/querymid.json",
				new PostParameter[] { new PostParameter("id", id),
						new PostParameter("type", type.toString()) }).asJSONObject();
	}	
	/**
	 * 閫氳繃寰崥ID鑾峰彇鍏禡ID
	 * 
	 * @param id
	 *            闇�鏌ヨ鐨勫井鍗欼D锛屾壒閲忔ā寮忎笅锛岀敤鍗婅閫楀彿鍒嗛殧锛屾渶澶氫笉瓒呰繃20涓�
	 * @param type
	 *            鑾峰彇绫诲瀷锛�锛氬井鍗氥�2锛氳瘎璁恒�3锛氱淇★紝榛樿涓�銆�
	 * @param is_batch
	 *            鏄惁浣跨敤鎵归噺妯″紡锛�锛氬惁銆�锛氭槸锛岄粯璁や负0銆�
	 * @return Status's mid
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/querymid">statuses/querymid</a>
	 * @since JDK 1.5
	 */
	public JSONObject QueryMid(Integer type, String id,int is_batch) throws WeiboException {
		return Weibo.client.get(WeiboConfig.getValue("baseURL") + "statuses/querymid.json",
				new PostParameter[] { new PostParameter("id", id),
						new PostParameter("type", type.toString()),
						new PostParameter("is_batch", is_batch)}).asJSONObject();
	}
	/**
	 * 閫氳繃寰崥MID鑾峰彇鍏禝D
	 * 
	 * @param mid
	 *            true string 闇�鏌ヨ鐨勫井鍗歁ID锛屾壒閲忔ā寮忎笅锛岀敤鍗婅閫楀彿鍒嗛殧锛屾渶澶氫笉瓒呰繃20涓�
	 * @param type
	 *            鑾峰彇绫诲瀷锛�锛氬井鍗氥�2锛氳瘎璁恒�3锛氱淇★紝榛樿涓�銆�
	 * @return Status's id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/queryid">statuses/queryid</a>
	 * @since JDK 1.5
	 */
	public JSONObject QueryId(String mid, Integer type,int isBase62) throws WeiboException {
		return Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/queryid.json",
				new PostParameter[] { new PostParameter("mid", mid),
						new PostParameter("type", type.toString()),
						new PostParameter("isBase62", isBase62)}).asJSONObject();
	}

	/**
	 * 閫氳繃寰崥MID鑾峰彇鍏禝D
	 * 
	 * @param mid
	 *            true string 闇�鏌ヨ鐨勫井鍗歁ID锛屾壒閲忔ā寮忎笅锛岀敤鍗婅閫楀彿鍒嗛殧锛屾渶澶氫笉瓒呰繃20涓�
	 * @param type
	 *            鑾峰彇绫诲瀷锛�锛氬井鍗氥�2锛氳瘎璁恒�3锛氱淇★紝榛樿涓�銆�
	 * @param is_batch
	 *            鏄惁浣跨敤鎵归噺妯″紡锛�锛氬惁銆�锛氭槸锛岄粯璁や负0銆�
	 * @param inbox
	 *            浠呭绉佷俊鏈夋晥锛屽綋MID绫诲瀷涓虹淇℃椂鐢ㄦ鍙傛暟锛�锛氬彂浠剁銆�锛氭敹浠剁锛岄粯璁や负0 銆�
	 * @param isBase62
	 *            MID鏄惁鏄痓ase62缂栫爜锛�锛氬惁銆�锛氭槸锛岄粯璁や负0銆�
	 * @return Status's id
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/queryid">statuses/queryid</a>
	 * @since JDK 1.5
	 */
	public JSONObject QueryId(String mid, Integer type, Integer isBatch,Integer isBase62) throws WeiboException {
		return Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "statuses/queryid.json",
				new PostParameter[] { new PostParameter("mid", mid),
						new PostParameter("type", type.toString()),
						new PostParameter("is_batch", isBatch.toString()),
						new PostParameter("isBase62", isBase62.toString()) }).asJSONObject();
	}



	/**
	 * 鎸夊ぉ杩斿洖鐑棬寰崥杞彂姒滅殑寰崥鍒楄〃
	 * 
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
	 * @since JDK 1.5
	 */
	public JSONArray getRepostDaily() throws WeiboException {
		return Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/hot/repost_daily.json").asJSONArray();
	}
	/**
	 * 鎸夊懆杩斿洖鐑棬寰崥杞彂姒滅殑寰崥鍒楄〃
	 * 
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
	 * @since JDK 1.5
	 */
	public JSONArray getRepostWeekly() throws WeiboException {
		return Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/hot/repost_weekly.json").asJSONArray();
	}
	/**
	 * 鎸夊ぉ杩斿洖鐑棬寰崥璇勮姒滅殑寰崥鍒楄〃
	 * 
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
	 * @since JDK 1.5
	 */
	public JSONArray getCommentsDaily() throws WeiboException {
		return Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/hot/comments_daily.json").asJSONArray();
	}
	/**
	 * 鎸夊懆杩斿洖鐑棬寰崥璇勮姒滅殑寰崥鍒楄〃
	 * 
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/hot/repost_daily">statuses/hot/repost_daily</a>
	 * @since JDK 1.5
	 */
	public JSONArray getCommentsWeekly() throws WeiboException {
		return Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "statuses/hot/comments_weekly.json").asJSONArray();
	}

	/**
	 * 杞彂涓�潯鏂板井鍗�
	 * 
	 * @param id
	 *            瑕佽浆鍙戠殑寰崥ID
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost">statuses/repost</a>
	 * @since JDK 1.5
	 */
	public Status Repost(String id) throws WeiboException {
		return new Status(Weibo.client.post(WeiboConfig.getValue("baseURL")
				+ "statuses/repost.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * 杞彂涓�潯寰崥
	 * 
	 * @param id
	 *            瑕佽浆鍙戠殑寰崥ID
	 * @param status
	 *            娣诲姞鐨勮浆鍙戞枃鏈紝蹇呴』鍋歎RLencode锛屽唴瀹逛笉瓒呰繃140涓眽瀛楋紝涓嶅～鍒欓粯璁や负鈥滆浆鍙戝井鍗氣�
	 * @param is_comment
	 *            鏄惁鍦ㄨ浆鍙戠殑鍚屾椂鍙戣〃璇勮锛�锛氬惁銆�锛氳瘎璁虹粰褰撳墠寰崥銆�锛氳瘎璁虹粰鍘熷井鍗氥�3锛氶兘璇勮锛岄粯璁や负0
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/repost">statuses/repost</a>
	 * @since JDK 1.5
	 */
	public Status Repost(String id, String status, Integer is_comment)
			throws WeiboException {
		return new Status(Weibo.client.post(WeiboConfig.getValue("baseURL") + "statuses/repost.json", new PostParameter[] {
				new PostParameter("id", id),
				new PostParameter("status", status),
				new PostParameter("is_comment", is_comment.toString()) }));
	}

	/**
	 * 鏍规嵁寰崥ID鍒犻櫎鎸囧畾寰崥
	 * 
	 * @param id
	 *            闇�鍒犻櫎鐨勫井鍗欼D
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/destroy">statuses/destroy</a>
	 * @since JDK 1.5
	 */
	public Status Destroy(String id) throws WeiboException {
		return new Status(Weibo.client.post(WeiboConfig.getValue("baseURL")
				+ "statuses/destroy.json",
				new PostParameter[] { new PostParameter("id", id) }));
	}

	/**
	 * 鍙戝竷涓�潯鏂板井鍗�
	 * 
	 * @param status
	 *            瑕佸彂甯冪殑寰崥鏂囨湰鍐呭锛屽繀椤诲仛URLencode锛屽唴瀹逛笉瓒呰繃140涓眽瀛�
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/update">statuses/update</a>
	 * @since JDK 1.5
	 */
	public Status UpdateStatus(String status) throws WeiboException {
		return new Status(Weibo.client.post(WeiboConfig.getValue("baseURL")
				+ "statuses/update.json",
				new PostParameter[] { new PostParameter("status", status) }));
	}

	/**
	 * 鍙戝竷涓�潯鏂板井鍗�
	 * 
	 * @param status
	 *            瑕佸彂甯冪殑寰崥鏂囨湰鍐呭锛屽繀椤诲仛URLencode锛屽唴瀹逛笉瓒呰繃140涓眽瀛�
	 * @param lat
	 *            绾害锛屾湁鏁堣寖鍥达細-90.0鍒�90.0锛�琛ㄧず鍖楃含锛岄粯璁や负0.0銆�
	 * @param long 缁忓害锛屾湁鏁堣寖鍥达細-180.0鍒�180.0锛�琛ㄧず涓滅粡锛岄粯璁や负0.0銆�
	 * @param annotations
	 *            鍏冩暟鎹紝涓昏鏄负浜嗘柟渚跨涓夋柟搴旂敤璁板綍涓�簺閫傚悎浜庤嚜宸变娇鐢ㄧ殑淇℃伅锛屾瘡鏉″井鍗氬彲浠ュ寘鍚竴涓垨鑰呭涓厓鏁版嵁锛�
	 *            蹇呴』浠son瀛椾覆鐨勫舰寮忔彁浜わ紝瀛椾覆闀垮害涓嶈秴杩�12涓瓧绗︼紝鍏蜂綋鍐呭鍙互鑷畾
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/update">statuses/update</a>
	 * @since JDK 1.5
	 */
	public Status UpdateStatus(String status, Float lat, Float longs,
			String annotations) throws WeiboException {
		return new Status(Weibo.client.post(WeiboConfig.getValue("baseURL")
				+ "statuses/update.json", new PostParameter[] {
				new PostParameter("status", status),
				new PostParameter("lat", lat.toString()),
				new PostParameter("long", longs.toString()),
				new PostParameter("annotations", annotations) }));
	}

	/**
	 * 涓婁紶鍥剧墖骞跺彂甯冧竴鏉℃柊寰崥
	 * 
	 * @param status
	 *            瑕佸彂甯冪殑寰崥鏂囨湰鍐呭锛屽繀椤诲仛URLencode锛屽唴瀹逛笉瓒呰繃140涓眽瀛�
	 * @param pic
	 *            瑕佷笂浼犵殑鍥剧墖锛屼粎鏀寔JPEG銆丟IF銆丳NG鏍煎紡锛屽浘鐗囧ぇ灏忓皬浜�M銆�
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/upload">statuses/upload</a>
	 * @since JDK 1.5
	 */
	public Status UploadStatus(String status, ImageItem item)
			throws WeiboException {
		return new Status(Weibo.client.multPartURL(
				WeiboConfig.getValue("baseURL") + "statuses/upload.json",
				new PostParameter[] { new PostParameter("status", status)},
				item));
	}

	/**
	 * 涓婁紶鍥剧墖骞跺彂甯冧竴鏉℃柊寰崥
	 * 
	 * @param status
	 *            瑕佸彂甯冪殑寰崥鏂囨湰鍐呭锛屽繀椤诲仛URLencode锛屽唴瀹逛笉瓒呰繃140涓眽瀛�
	 * @param pic
	 *            瑕佷笂浼犵殑鍥剧墖锛屼粎鏀寔JPEG銆丟IF銆丳NG鏍煎紡锛屽浘鐗囧ぇ灏忓皬浜�M銆�
	 * @param lat
	 *            绾害锛屾湁鏁堣寖鍥达細-90.0鍒�90.0锛�琛ㄧず鍖楃含锛岄粯璁や负0.0銆�
	 * @param long 缁忓害锛屾湁鏁堣寖鍥达細-180.0鍒�180.0锛�琛ㄧず涓滅粡锛岄粯璁や负0.0銆�
	 * @return Status
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a
	 *      href="http://open.weibo.com/wiki/2/statuses/upload">statuses/upload</a>
	 * @since JDK 1.5
	 */
	public Status UploadStatus(String status, ImageItem item, Float lat,
			Float longs) throws WeiboException {
		return new Status(Weibo.client.multPartURL(
				WeiboConfig.getValue("baseURL") + "statuses/upload.json",
				new PostParameter[] { new PostParameter("status", status),
						new PostParameter("lat", lat.toString()),
						new PostParameter("long", longs.toString()) }, item));
	}

	/**
	 * 鑾峰彇寰崥瀹樻柟琛ㄦ儏鐨勮缁嗕俊鎭�
	 * 
	 * @return Emotion
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a href="http://open.weibo.com/wiki/2/emotions">emotions</a>
	 * @since JDK 1.5
	 */
	public List<Emotion> getEmotions() throws WeiboException {
		return Emotion.constructEmotions(Weibo.client.get(WeiboConfig
				.getValue("baseURL") + "emotions.json"));
	}

	/**
	 * 鑾峰彇寰崥瀹樻柟琛ㄦ儏鐨勮缁嗕俊鎭�
	 * 
	 * @param type
	 *            琛ㄦ儏绫诲埆锛宖ace锛氭櫘閫氳〃鎯呫�ani锛氶瓟娉曡〃鎯呫�cartoon锛氬姩婕〃鎯咃紝榛樿涓篺ace
	 * @param language
	 *            璇█绫诲埆锛宑nname锛氱畝浣撱�twname锛氱箒浣擄紝榛樿涓篶nname
	 * @return Emotion
	 * @throws WeiboException
	 *             when Weibo service or network is unavailable
	 * @version weibo4j-V2 1.0.0
	 * @see <a href="http://open.weibo.com/wiki/2/emotions">emotions</a>
	 * @since JDK 1.5
	 */
	public List<Emotion> getEmotions(String type, String language)
			throws WeiboException {
		return Emotion.constructEmotions(Weibo.client.get(
				WeiboConfig.getValue("baseURL") + "emotions.json",
				new PostParameter[] { 
					new PostParameter("type", type),
					new PostParameter("language", language) }));
	}

}
