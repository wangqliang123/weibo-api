package weibo4j.wang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import weibo4j.Comments;
import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.CommentWapper;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.wang.db.DataWrapper;
import weibo4j.wang.db.ModelFormatter;

public class ExtractInfo {

	public static void main(String[] args) throws IOException, WeiboException,
			JSONException {
		String access_token = "2.00oCIiNDN5IxDE78814a2afeBDSCHC";
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		DataWrapper dw = new DataWrapper();
		Comments cm = new Comments();

		String raw = FileHandler.readTXT(Constants.TXTFILEPATH
				+ "weiboid_2000.txt");
		String ids[] = raw.split("\r\n");
		int start = 199;
		int count = 0;

		for (String weiboId : ids) {
			try { // String weiboId = "3500903749534872";
				if (count++ < start)
					continue;
				P.p(count + " - Getting comments of " + weiboId);
				CommentWapper cw = cm.getCommentById(weiboId);
				List<Comment> comments = cw.getComments();
				for (Comment c : comments) {
					dw.saveComment(c);
				}
				Thread.sleep(15 * 1000);
			} catch (Exception e) {
				e.printStackTrace();
				FileHandler.appText2File(Constants.TXTFILEPATH
						+ "weiboid_2000_error.txt", weiboId + "\r\n");
			}
		}
	}

	public void user() throws InterruptedException {
		String access_token = "2.00DjkOMBBYA83E6dcbbb297c8go2fE";
		String uid = "1096965073";
		ExtractInfo ei = new ExtractInfo();
		int start = 0;

		int try_time = 0;
		int start_index = 9913;
		while (try_time < 5) {
			Weibo weibo = new Weibo();
			weibo.setToken(access_token);
			User user = ei.getUserById(uid);
			// DataWrapper dw = new DataWrapper();
			// dw.saveUser(user);
			try {
				// start_index = ei.saveStatus2Db(start_index);
				// ei.sample();
				// ei.getStartingPoints();
				// ei.saveFollowers(user);
				// Thread.sleep(600000);
				// ei.getStatusBetDates(user, "2012-4-15 00:00:00",
				// "2012-4-29 23:59:59");
				// ei.getCircles();
				start = ei.getExtraUsers(start);
				if (start < 8242) {
					P.pr("Exception. Waiting for 2 minutes...");
					Thread.sleep(2 * 60 * 1000);
					try_time++;
					continue;
				}
				// User u = ei.getUserById("1885272344");
				// List<User> followers = ei.getAllFollowers(u);
				break;
			} catch (Exception e) {
				P.pr("Weibo error! Pause for a 2 minutes");
			}
		}
		P.pl("Done");
	}

	public void sample() throws InterruptedException {
		String uid = "1096965073"; // -鎬濇棤娑�
		ExtractInfo ei = new ExtractInfo();
		User user = ei.getUserById(uid);
		List<User> followers = ei.getAllFollowers(user);
		String ids = "";
		int counter = 0;
		for (User f : followers) {
			if (f.getLocation().equals("海外 新加坡")) {
				ids += f.getId() + "\t" + f.getScreenName() + "\n";
				counter++;
			}
		}
		P.pl("Done. Followers in Singapore: " + counter);
		FileHandler.appText2File(Constants.TXTFILEPATH + "starting_user.txt",
				ids, true);
	}

	public int getExtraUsers(int start) throws IOException, WeiboException,
			InterruptedException {
		String raw = FileHandler.readTXT(Constants.TXTFILEPATH
				+ "luanma.sql.id");
		String ids[] = raw.split("\r\n");
		List<String> ids_visited = new ArrayList<String>();
		DataWrapper dw = new DataWrapper();
		int i = start;
		try {
			for (i = start; i < ids.length; i++) {
				String uid = ids[i];
				P.pl(uid);
				uid = uid.split("\t")[0];
				uid = uid.replaceAll("\n", "");
				uid = uid.replaceAll("\r", "");
				if (ids_visited.contains(uid) || uid.length() < 3)
					continue;
				ids_visited.add(uid);
				P.pl("------ " + i + ": Geting user " + uid + " -------");
				User user = getUserById(uid);
				dw.saveUser(user);
				if (i % 100 == 0) {
					Thread.sleep(60 * 1000);
				}
				Thread.sleep(5 * 1000);
			}
		} catch (Exception e) {
			return i;
		}
		return i;
	}

	public void getStartingPoints() throws IOException, WeiboException,
			InterruptedException {
		String raw = FileHandler.readTXT(Constants.TXTFILEPATH
				+ "starting_user.txt");
		String ids[] = raw.split("\n");
		List<String> ids_visited = new ArrayList<String>();
		String startingUids = "";
		int friends_count = 0;
		int followers_count = 0;
		int friends_part_count = 0;
		int followers_part_count = 0;
		ExtractInfo ei = new ExtractInfo();
		for (int i = 0; i < ids.length; i++) {
			String uid = ids[i];
			uid = uid.split("\t")[0];
			if (ids_visited.contains(uid))
				continue;
			ids_visited.add(uid);
			P.pl("------ Geting friends and followers id of user " + uid
					+ " -------");
			User user = ei.getUserById(uid);
			List<User> followers = ei.getAllFollowers(user);
			for (User u : followers) {
				startingUids += u.getId() + "\t" + u.getScreenName() + "\n";
				followers_part_count++;
			}
			List<User> friends = ei.getAllFriends(user);
			for (User u : friends) {
				startingUids += u.getId() + "\t" + u.getScreenName() + "\n";
				friends_part_count++;
			}
			P.pl("Users to start with: " + followers_count + " + "
					+ followers_part_count + ", " + friends_count + " + "
					+ friends_part_count);
			FileHandler.appText2File(Constants.TXTFILEPATH
					+ "starting_base.txt", startingUids, false);
			startingUids = "";
			friends_count += friends_part_count;
			followers_count += followers_part_count;
			friends_part_count = 0;
			followers_part_count = 0;
			P.pl("Pause for 2 minutes...");
			Thread.sleep(2 * 60 * 1000);
		}
		P.pl("Done. Check file " + Constants.TXTFILEPATH + "starting_base.txt");
	}

	public void getCircles() throws IOException, InterruptedException {
		String raw = FileHandler.readTXT(Constants.TXTFILEPATH
				+ "clean_base.txt");
		String ids[] = raw.split("\n");
		int friends_count = 0;
		int followers_count = 0;
		int friends_part_count = 0;
		int followers_part_count = 0;
		ExtractInfo ei = new ExtractInfo();
		DataWrapper dw = new DataWrapper();
		for (int i = 2490; i < 2501; i++) {
			String uid = ids[i];
			if (uid.indexOf("#") >= 0)
				continue;
			uid = uid.split("\t")[0];
			User user = ei.getUserById(uid);
			if (user == null) {
				P.pr("User with id " + uid + " does not exist.");
				continue;
			}
			String msg = "------ " + i + "/" + ids.length
					+ " Saving friends and followers of user: " + uid + " "
					+ user.getScreenName() + "-------";
			P.pl(msg);
			Log.logInfo(msg);
			List<User> followers = ei.getAllFollowers(user);
			for (User u : followers) {
				dw.saveUser(u);
				dw.setRelationship(u, user);
				followers_part_count++;
			}
			P.pl("+" + followers_part_count + " Followers to "
					+ followers_count);
			List<User> friends = ei.getAllFriends(user);
			for (User u : friends) {
				dw.saveUser(u);
				dw.setRelationship(user, u);
				friends_part_count++;
			}
			P.pl("+" + friends_part_count + " Followers to " + friends_count);
			friends_count += friends_part_count;
			followers_count += followers_count;
			P.pl("Pause for 1 minutes...");
			Thread.sleep(1 * 60 * 1000);
		}
	}

	public int saveStatus2Db() throws InterruptedException {
		DataWrapper dw = new DataWrapper();
		int startIndex = 0;
		int endIndex = 1000;
		ArrayList<User> usersWithinRange = dw.getRangeUsers(startIndex,
				endIndex);
		for (int i = 0; i < usersWithinRange.size(); i++) {
			User u = usersWithinRange.get(i);
			try {
				P.pl("--------------------" + (startIndex + i) + "/"
						+ (startIndex + endIndex)
						+ ": Trying to get statuses of user: "
						+ u.getScreenName() + "(" + u.getId()
						+ ")--------------------");
				getStatusBetDates(u, "2012-4-15 00:00:00", "2012-4-29 23:59:59");
				P.pl("Waiting for 5 mins...");
				Thread.sleep(1000 * 60 * 5);
			} catch (WeiboException e) {
				P.pr(e.getMessage());
				return i + startIndex;
			}
		}
		return endIndex;
	}

	public void saveFollowers(User user) {
		Friendships fm = new Friendships();
		DataWrapper dw = new DataWrapper();
		long next_cursor = -1;
		long n_total_followers = user.getFollowersCount();
		int counter = 0;
		try {
			dw.saveUser(user);
			P.pl("Total # of followers of " + user.getScreenName() + ": "
					+ n_total_followers);
			Thread.sleep(1000);
			do {
				UserWapper followers = fm.getFollowersById(user.getId(),
						Constants.N_RECORD_ON_PAGE, (int) (next_cursor));
				P.pl("Next cursor: " + next_cursor);
				for (User f : followers.getUsers()) {
					if (counter++ < 0)
						continue;
					Thread.sleep(200);
					P.pl("===================================================");
					P.pl("# of followers: " + followers.getUsers().size());
					dw.saveUser(f);
					dw.setRelationship(f, user);
					saveFollowers2Db(f);
				}
				next_cursor = followers.getNextCursor();
			} while (next_cursor < n_total_followers && next_cursor > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFollowers2Db(User user) {
		Friendships fm = new Friendships();
		DataWrapper dw = new DataWrapper();
		long next_cursor = -1;
		long n_total_followers = user.getFollowersCount();
		try {
			dw.saveUser(user);
			P.pl("Total # of followers of " + user.getScreenName() + ": "
					+ n_total_followers);
			Thread.sleep(1000);
			do {
				UserWapper followers = fm.getFollowersById(user.getId(),
						Constants.N_RECORD_ON_PAGE, (int) (next_cursor));
				for (User f : followers.getUsers()) {
					Thread.sleep(200);
					P.pl("===================================================");
					dw.saveUser(f);
					dw.setRelationship(f, user);
				}
				next_cursor = followers.getNextCursor();
			} while (next_cursor < n_total_followers && next_cursor > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFriends2Db(User user) {
		Friendships fm = new Friendships();
		DataWrapper dw = new DataWrapper();
		ExtractInfo ei = new ExtractInfo();
		long next_cursor = -1;
		long n_total_followers = user.getFollowersCount();
		try {
			dw.saveUser(user);
			P.pl("Total # of followers of " + user.getScreenName() + ": "
					+ n_total_followers);
			Thread.sleep(1000);
			do {
				String[] followers = fm.getFriendsIdsByUid(user.getId(),
						Constants.N_RECORD_ON_PAGE, (int) (next_cursor));
				for (String uid : followers) {
					Thread.sleep(200);
					P.pl("===================================================");
					User f = ei.getUserById(uid);
					dw.saveUser(f);
					dw.setRelationship(f, user);
				}
				next_cursor += Constants.N_RECORD_ON_PAGE;
			} while (next_cursor < n_total_followers && next_cursor > 0
					&& next_cursor < Constants.MAX_USERS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get all followers of a user, maximum 5000 limited by Weibo API
	 * 
	 * @param user
	 * @return
	 * @throws InterruptedException
	 */
	public List<User> getAllFollowers(User user) throws InterruptedException {
		Friendships fm = new Friendships();
		List<User> allFollowers = new ArrayList<User>();
		long next_cursor = -1;
		long n_total_followers = user.getFollowersCount();

		String msg = "Total # of followers of " + user.getScreenName() + ": "
				+ n_total_followers;
		P.pl(msg);
		Log.logInfo(msg);
		long max_pages = n_total_followers / Constants.N_RECORD_ON_USER_PAGE
				+ 1;
		max_pages *= 200;
		long pages = 0;
		int invalid_times = 0;

		do {
			int try_time = 0;
			// Try 10 times to get one page
			while (try_time++ < 10) {
				try {
					UserWapper followers = fm.getFollowersById(user.getId(),
							Constants.N_RECORD_ON_PAGE, (int) (next_cursor));
					allFollowers.addAll(followers.getUsers());
					if (followers.getUsers().size() == 0) // Indicates empty
															// result from Weibo
						invalid_times++;
					else
						invalid_times = 0;
					// next_cursor = followers.getNextCursor();
					next_cursor = allFollowers.size();
					pages++;
					break;
				} catch (Exception e) {
					e.printStackTrace();
					P.pr("Wait for " + try_time * 10 + " minutes...");
					Thread.sleep(try_time * 1000 * 60 * 10);
				}
			}
			P.pl("next_cursor: " + next_cursor);
			msg = "pages/max_pages: " + pages + "/" + max_pages;
			P.pl(msg);
			Log.logInfo(msg);
			msg = allFollowers.size() + " followers of user " + user.getId()
					+ " are got. Wait for 5 sec...";
			P.pl(msg);
			Log.logInfo(msg);
			if (pages > 400) {
				msg = allFollowers.size() + " followers of user "
						+ user.getId() + " are got. Wait for another 10 sec...";
				P.pl(msg);
				Log.logInfo(msg);
				Thread.sleep(1000 * 10);
			}
			Thread.sleep(1000 * 5);
		} while (pages < max_pages && next_cursor > 0
				&& next_cursor < n_total_followers
				&& next_cursor < Constants.MAX_USERS && invalid_times < 5);
		// If there are 5 times we get empty from Weibo, stop
		return allFollowers;
	}

	public List<User> getAllFriends(User user) throws InterruptedException {
		Friendships fm = new Friendships();
		List<User> allFriends = new ArrayList<User>();
		long next_cursor = -1;
		long n_total_friends = user.getFriendsCount();
		long max_pages = n_total_friends / Constants.N_RECORD_ON_USER_PAGE + 1;
		max_pages *= 200;
		long pages = 0;
		int invalid_times = 0;

		String msg = "Total # of friends of " + user.getScreenName() + ": "
				+ n_total_friends;
		P.pl(msg);
		Log.logInfo(msg);
		do {
			int try_time = 0;
			while (try_time++ <= 10) {
				try {
					UserWapper friends = fm.getFriendsByUid(user.getId(),
							Constants.N_RECORD_ON_USER_PAGE,
							(int) (next_cursor));
					allFriends.addAll(friends.getUsers());
					if (friends.getUsers().size() == 0) // Indicates empty
														// result from Weibo
						invalid_times++;
					else
						invalid_times = 0;
					next_cursor = allFriends.size();
					pages++;
					break;
				} catch (Exception e) {
					e.printStackTrace();
					P.pr("Wait for " + try_time * 10 + " minutes...");
					Thread.sleep(try_time * 1000 * 60 * 10);
				}
			}
			P.pl("next_cursor: " + next_cursor);
			msg = "pages/max_pages: " + pages + "/" + max_pages;
			P.pl(msg);
			Log.logInfo(msg);
			msg = allFriends.size() + " friends of user " + user.getId()
					+ " are got. Wait for 5 sec...";
			P.pl(msg);
			Log.logInfo(msg);
			if (pages > 400) {
				msg = allFriends.size() + " friends of user " + user.getId()
						+ " are got. Wait for another 10 sec...";
				P.pl(msg);
				Log.logInfo(msg);
				Thread.sleep(1000 * 10);
			}
			Thread.sleep(1000 * 5);
		} while (pages < max_pages && next_cursor > 0
				&& next_cursor < n_total_friends
				&& next_cursor < Constants.MAX_FRIENDS && invalid_times < 5);
		// If there are 5 times we get empty from Weibo, stop

		return allFriends;
	}

	public List<String> getFullFriendsId(User user) {
		Friendships fm = new Friendships();
		List<String> allFriends = new ArrayList<String>();
		long next_cursor = -1;
		long n_total_friends = user.getFriendsCount();
		try {
			P.pl("Total # of friends of " + user.getScreenName() + ": "
					+ n_total_friends);
			Thread.sleep(1000);
			do {
				String[] followers = fm.getFriendsIdsByUid(user.getId(),
						Constants.N_RECORD_ON_USER_PAGE, (int) (next_cursor));
				for (String fid : followers) {
					allFriends.add(fid);
				}
				next_cursor += Constants.N_RECORD_ON_USER_PAGE;
			} while (next_cursor < n_total_friends && next_cursor > 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allFriends;
	}

	public List<Status> getStatusBetDates(User user, String startDate,
			String endDate) throws WeiboException, InterruptedException {
		List<Status> allStatus = new ArrayList<Status>();
		Paging page = new Paging();
		Timeline tm = new Timeline();
		DataWrapper dw = new DataWrapper();
		ModelFormatter mf = new ModelFormatter();
		P.pl("User " + user.getScreenName() + "(" + user.getId() + "): "
				+ " # of status: " + user.getStatusesCount());
		int page_no = 0;
		int try_time = 0;
		int invalid_times = 0;
		do {
			try_time = 0;
			while (try_time++ <= 5) {
				try {
					page.setPage(page_no);
					page.setCount(Constants.N_RECORD_ON_PAGE);
					StatusWapper status = tm.getUserTimelineByUid(user.getId(),
							page, 0, 0);
					P.pl("# of status on page " + page_no + ": "
							+ status.getStatuses().size());
					// Indicates empty result from Weibo
					if (status.getStatuses().size() == 0)
						invalid_times++;
					else
						invalid_times = 0;
					for (Status s : status.getStatuses()) {
						if (s.getRetweetedStatus() != null)
							dw.saveStatus(s.getRetweetedStatus());
						P.pl(s.toString());
						dw.saveStatus(s);
						P.pl(s.getCreatedAt() + "\t" + mf.parseDate(startDate));
						P.pl(mf.cmpDate(s.getCreatedAt(),
								mf.parseDate(startDate)));
						if (mf.cmpDate(s.getCreatedAt(),
								mf.parseDate(startDate)) < 0) {
							break;
						}
					}
					allStatus.addAll(status.getStatuses());
					P.pr("Wait for 5 sec to get next page ...");
					Thread.sleep(5000);
				} catch (Exception e) {
					e.printStackTrace();
					P.pr("Wait for " + try_time * 10 + " minutes...");
					Thread.sleep(try_time * 1000 * 60 * 10);
				}
			}
		} while (try_time < 10 && invalid_times < 5
				&& allStatus.size() < user.getStatusesCount());
		P.pl("Total status obtained: " + allStatus.size());
		return allStatus;
	}

	public User getUserById(String user_id) throws InterruptedException {
		Users um = new Users();
		int try_time = 1;
		User user = new User();
		while (try_time++ <= 5) {
			try {
				user = um.showUserById(user_id);
				Log.logInfo(user.toString());
				return user;
			} catch (Exception e) {
				e.printStackTrace();
				FileHandler.appText2File(weibo4j.wang.Constants.TXTFILEPATH
						+ "debug.txt", "[ERROR] " + user_id + "\n", false);
				FileHandler.appText2File(weibo4j.wang.Constants.TXTFILEPATH
						+ "debug.txt", "[ERROR] " + e.getMessage() + "\n",
						false);
				if (e.getMessage().indexOf("User does not exists") >= 0)
					break;
				P.pr("Wait for " + try_time * 5 + " minutes...");
				Thread.sleep(try_time * 1000 * 60 * 5);
			}
		}
		return null;
	}
}