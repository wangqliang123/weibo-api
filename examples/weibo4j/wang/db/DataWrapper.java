package weibo4j.wang.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import weibo4j.model.Comment;
import weibo4j.model.Status;
import weibo4j.model.User;

public class DataWrapper {
	private Operations ope;
	private ModelFormatter formatter;

	public DataWrapper() {
		ope = new Operations();
		formatter = new ModelFormatter();
	}

	public boolean saveUser(User user) {
		String sql = formatter.getUserQueryById(user);
		try {
			if (!ope.exeQuery(sql).next()) {
				sql = formatter.getUserInsSql(user);
				return ope.exeInsert(sql);
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean saveComment(Comment c) {
		String sql = formatter.getCommentInsSql(c);
		return ope.exeInsert(sql);
	}

	public boolean saveStatus(Status s) {
		if (s.getUser() == null)
			return false;
		String sql = formatter.getStatusQuery(s);
		try {
			if (!ope.exeQuery(sql).next()) {
				sql = formatter.getStatusInsSql(s);
				return ope.exeInsert(sql);
			} else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean hasRelationship(User follower, User followee) {
		String sql = formatter.checkRelationship(follower, followee);
		try {
			if (ope.exeQuery(sql).next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Relationship setRelationship(User follower, User followee) {
		if (hasRelationship(follower, followee))
			return null;
		String sql = formatter.getRelationInsSql(follower, followee);
		if (ope.exeInsert(sql))
			return new Relationship(follower, followee);
		else
			return null;
	}

	public User wrapUser(ResultSet rs) {
		User user = new User();
		try {
			user.setId(String.valueOf(rs.getLong("id")));
			user.setScreenName(rs.getString("screenName"));
			user.setName(rs.getString("name"));
			user.setProvince(rs.getInt("province"));
			user.setCity(rs.getInt("city"));
			user.setLocation(rs.getString("location"));
			user.setDescription(rs.getString("description"));
			user.setUrl(rs.getString("url"));
			user.setProfileImageUrl(rs.getString("profileImageUrl"));
			user.setUserDomain(rs.getString("userDomain"));
			user.setGender(rs.getString("gender"));
			user.setFollowersCount(rs.getInt("followersCount"));
			user.setFriendsCount(rs.getInt("friendsCount"));
			user.setStatusesCount(rs.getInt("statusesCount"));
			user.setFavouritesCount(rs.getInt("favouritesCount"));
			user.setCreatedAt(rs.getDate("createdAt"));
			user.setFollowing(Boolean.valueOf(rs.getString("following")));
			user.setVerified(Boolean.valueOf(rs.getString("verified")));
			user.setVerifiedType(rs.getInt("verifiedType"));
			user.setAllowAllActMsg(Boolean.valueOf(rs
					.getString("allowAllActMsg")));
			user.setAllowAllComment(Boolean.valueOf(rs
					.getString("allowAllComment")));
			user.setFollowMe(Boolean.valueOf(rs.getString("followMe")));
			user.setAvatarLarge(rs.getString("avatarLarge"));
			user.setOnlineStatus(rs.getInt("onlineStatus"));
			user.setStatusId(String.valueOf(rs.getLong("statusId")));
			user.setBiFollowersCount(rs.getInt("biFollowersCount"));
			user.setRemark(rs.getString("remark"));
			user.setLang(rs.getString("lang"));
			user.setVerifiedReason(rs.getString("verifiedReason"));
			user.setWeihao(rs.getString("weihao"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	public ArrayList<User> getRangeUsers(int start, int end) {
		ArrayList<User> usersWithinRange = new ArrayList<User>();
		Connection conn = LocalDbConnection.getConn();
		Statement s;
		try {
			s = conn.createStatement();
			ModelFormatter mf = new ModelFormatter();
			String sql = mf.getRangeUserSql(start, end);
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				User user = wrapUser(rs);
				usersWithinRange.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersWithinRange;
	}

	public ArrayList<User> getAllUsers() {
		ArrayList<User> allUsers = new ArrayList<User>();
		Connection conn = LocalDbConnection.getConn();
		Statement s;
		try {
			s = conn.createStatement();
			ModelFormatter mf = new ModelFormatter();
			String sql = mf.getAllUserSql();
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				User user = wrapUser(rs);
				allUsers.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUsers;
	}
}
