package weibo4j.wang;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.Weibo;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Paging;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;
import weibo4j.wang.db.DataWrapper;
import weibo4j.wang.db.ModelFormatter;

public class ExtractStatus {

	public static void main(String[] args) throws WeiboException, IOException,
			InterruptedException {
		String access_token = "2.00DjkOMBT5DwLE16fd11733cGPotpD";
		String uid = "1096965073";
		ExtractStatus es = new ExtractStatus();

		int try_time = 0;
		int start_index = 5941;
		Weibo weibo = new Weibo();
		weibo.setToken(access_token);
		while (try_time < 5) {
			try {
				es.saveStatus2Db(start_index);
				break;
			} catch (Exception e) {
				P.pr("Weibo error! Pause for a 10 mins");
				P.pr("Exception Msg: " + e.getMessage());
				Thread.sleep(600000);
				try_time++;
			}
		}

		P.pl("Done");
	}

	public int saveStatus2Db(int start) throws 	IOException {
		String base_ids = FileHandler.readTXT(Constants.TXTFILEPATH
				+ "clean_base.txt");
		String ids[] = base_ids.split("\n");
		ExtractInfo ei = new ExtractInfo();
		User u;
		int i = start;
		for (i = start; i < ids.length; i++) {
			String id = ids[i];
			id = id.split("\t")[0].trim();
			try {
				u = ei.getUserById(id);
				P.pl("-------------------- [" + start + ", " + i + ", "
						+ ids.length + "]: Trying to get statuses of user: "
						+ u.getScreenName() + "(" + u.getId()
						+ ")--------------------");
				getStatusBetDates(u, "2012-10-14 00:00:00", "2012-11-12 23:59:59");
				P.pl("Waiting for 20 sec...");
				Thread.sleep(1000 * 20);
			} catch (Exception e) {
				P.pr("Error when retrieving user " + id);
				P.pr(e.getMessage());
				if (e.getMessage() == null) {
					Log.logInfo("User " + id + " may have been removed from weibo.com.");
					continue;
				}
				return i;
			}
		}
		return i;
	}

	public List<Status> getStatusBetDates(User user, String startDate,
			String endDate) throws WeiboException, InterruptedException {
		List<Status> allStatus = new ArrayList<Status>();
		Paging page = new Paging();
		Timeline tm = new Timeline();
		DataWrapper dw = new DataWrapper();
		ModelFormatter mf = new ModelFormatter();
		P.pr("================================================================");
		P.pr("User " + user.getScreenName() + "(" + user.getId() + "): "
				+ " # of status: " + user.getStatusesCount());
		int page_no = 0;
		int try_time = 0;
		boolean to_stop = false;
		StatusWapper statusWrapper;
		do {
			try_time = 0;
			page_no++;
			while (try_time++ <= 5) {
				try {
					page.setPage(page_no);
					page.setCount(Constants.N_RECORD_ON_PAGE);
					statusWrapper = tm.getUserTimelineByUid(user.getId(), page,
							0, 0);
					P.pr("User " + user.getId() + ": # of status on page " + page_no + ": "
							+ statusWrapper.getStatuses().size());
					if (statusWrapper.getStatuses().size() == 0) {
						to_stop = true;
					} else {
						for (Status s : statusWrapper.getStatuses()) {
							//P.pl(s.getCreatedAt() + "\t"
							//		+ mf.parseDate(startDate));
							if (mf.cmpDate(s.getCreatedAt(),
									mf.parseDate(endDate)) >= 0) {
								continue;
							}
							//P.pl(mf.cmpDate(s.getCreatedAt(),
							//		mf.parseDate(startDate)));
							if (mf.cmpDate(s.getCreatedAt(),
									mf.parseDate(startDate)) < 0) {
								to_stop = true;
								break;
							}
							if (s.getRetweetedStatus() != null)
								dw.saveStatus(s.getRetweetedStatus());
							//P.pl(s.toString());
							dw.saveStatus(s);
						}
						allStatus.addAll(statusWrapper.getStatuses());
					}
					P.pl("Wait for 10 sec to get next page ...");
					Thread.sleep(10000);
					break;
				} catch (Exception e) {
					try_time++;
					e.printStackTrace();
					P.pr("Error when retrieving user " + user.getId());
					P.pr("Wait for " + try_time * 10 + " minutes...");
					Thread.sleep(try_time * 1000 * 60 * 10);
				}
			}
		} while (!to_stop && allStatus.size() < user.getStatusesCount());
		P.pl("Total status obtained: " + allStatus.size());
		return allStatus;
	}
}