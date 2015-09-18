package weibo4j.wang.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import weibo4j.model.Comment;
import weibo4j.model.Status;
import weibo4j.model.User;
import weibo4j.wang.P;

public class ModelFormatter {
	public String formatDate(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	public Date parseDate(String dateStr) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			Date date = format.parse(dateStr);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int cmpDate(Date date_0, Date date_1) {
		if (date_0.getTime() > date_1.getTime()) {
			return 1;
		} else if (date_0.getTime() < date_1.getTime()) {
			return -1;
		} else {
			return 0;
		}
	}

	public String rmQuote(String str) {
		if (str == null || str.equals(""))
			return "";
		return str.replaceAll("'", "''");
	}

	public String getUserInsSql(User user) {
		String sql = "INSERT INTO User VALUES(";
		sql += user.getId() + ", ";
		sql += "'" + rmQuote(user.getScreenName()) + "', ";
		sql += "'" + rmQuote(user.getName()) + "', ";
		sql += user.getProvince() + ", ";
		sql += user.getCity() + ", ";
		sql += "'" + rmQuote(user.getLocation()) + "', ";
		sql += "'" + rmQuote(user.getDescription()) + "', ";
		sql += "'" + rmQuote(user.getUrl()) + "', ";
		sql += "'" + rmQuote(user.getProfileImageUrl()) + "', ";
		sql += "'" + rmQuote(user.getUserDomain()) + "', ";
		sql += "'" + rmQuote(user.getGender()) + "', ";
		sql += user.getFollowersCount() + ", ";
		sql += user.getFriendsCount() + ", ";
		sql += user.getStatusesCount() + ", ";
		sql += user.getFavouritesCount() + ", ";
		sql += "'" + rmQuote(formatDate(user.getCreatedAt())) + "', ";
		sql += "'" + user.isFollowing() + "', ";
		sql += "'" + user.isVerified() + "', ";
		sql += user.getVerifiedType() + ", ";
		sql += "'" + user.isAllowAllActMsg() + "', ";
		sql += "'" + user.isAllowAllComment() + "', ";
		sql += "'" + user.isFollowMe() + "', ";
		sql += "'" + rmQuote(user.getAvatarLarge()) + "', ";
		sql += user.getOnlineStatus() + ", ";
		sql += (user.getStatusId().equals("")) ? (-1 + ", ") : (user
				.getStatusId() + ", ");
		sql += user.getBiFollowersCount() + ", ";
		sql += "'" + rmQuote(user.getRemark()) + "', ";
		sql += "'" + rmQuote(user.getLang()) + "', ";
		sql += "'" + rmQuote(user.getVerifiedReason()) + "', ";
		sql += "'" + rmQuote(user.getWeihao()) + "')";
		//P.pl("Insert user sql: " + sql);
		return sql;
	}

	public String getCommentInsSql(Comment com) {
		Comment replyComment = com.getReplycomment();
		String sql = "INSERT INTO Comment VALUES(";
		sql += com.getId() + ", ";
		sql += "'" + formatDate(com.getCreatedAt()) + "', ";
		sql += "'" + rmQuote(com.getMid()) + "', ";
		sql += "'" + rmQuote(com.getIdstr()) + "', ";
		sql += "'" + rmQuote(com.getText()) + "', ";
		sql += "'" + rmQuote(com.getSource()) + "', ";
		sql += replyComment == null ? "-1, " : (replyComment.getId() + ", ");
		sql += com.getUser().getId() + ", ";
		sql += com.getStatus().getId() + ")";
		P.pl("Insert comment sql: " + sql);
		return sql;
	}
	
	public String getStatusInsSql(Status s) {
		String sql = "INSERT INTO Status VALUES(";
		sql += s.getId() + ", ";
		sql += "'" + formatDate(s.getCreatedAt()) + "', ";
		sql += "'" + s.getIdstr() + "', ";
		sql += s.getUser().getId() + ", ";
		sql += "'" + rmQuote(s.getText()) + "', ";
		sql += "'" + rmQuote(s.getSource().toString()) + "', ";
		sql += "'" + s.isFavorited() + "', ";
		sql += "'" + s.isTruncated() + "', ";
		sql += s.getInReplyToStatusId() + ", ";
		sql += s.getInReplyToUserId() + ", ";
		sql += "'" + rmQuote(s.getInReplyToScreenName()) + "', ";
		sql += "'" + rmQuote(s.getThumbnailPic()) + "', ";
		sql += "'" + rmQuote(s.getBmiddlePic()) + "', ";
		sql += "'" + rmQuote(s.getOriginalPic()) + "', ";
		sql += s.getRetweetedStatus() == null ? "0, " : s.getRetweetedStatus()
				.getId()
				+ ", ";
		sql += "'" + rmQuote(s.getGeo()) + "', ";
		sql += s.getLatitude() + ", ";
		sql += s.getLongitude() + ", ";
		sql += s.getRepostsCount() + ", ";
		sql += s.getCommentsCount() + ", ";
		sql += "'" + rmQuote(s.getMid()) + "', ";
		sql += "'" + rmQuote(s.getAnnotations()) + "', ";
		sql += s.getMlevel() + ", ";
		sql += "'" + rmQuote(s.getVisible().toString()) + "')";
		P.pl("Insert status sql: " + sql);
		return sql;
	}

	public String getRelationInsSql(Relationship r) {
		String sql = "INSERT INTO Relationship (followerId, followeeId) VALUES(";
		sql += r.getFollower().getId() + ", ";
		sql += r.getFollowee().getId() + ")";
		P.pl("Insert relationship sql: " + sql);
		return sql;
	}

	public String getRelationInsSql(User follower, User followee) {
		String sql = "INSERT INTO Relationship (followerId, followeeId) VALUES(";
		sql += follower.getId() + ", ";
		sql += followee.getId() + ")";
		//P.pl("Insert relationship sql: " + sql);
		return sql;
	}

	public String getUserQueryById(User user) {
		return "SELECT * from User where id = " + user.getId();
	}

	public String getStatusQuery(Status s) {
		return "SELECT * from Status where id = " + s.getId();
	}

	public String checkRelationship(User follower, User followee) {
		return "SELECT * from Relationship where followerId = "
				+ follower.getId() + " and followeeId = " + followee.getId();
	}

	public String getAllUserSql() {
		return "SELECT * from User";
	}
	
	public String getRangeUserSql(int start, int end) {
		return "SELECT * from User limit " + start + ", " + end;
	}
}
