package weibo4j.wang.db;

import weibo4j.model.User;

public class Relationship {
	private User follower;
	private User followee;

	public Relationship(User follower, User followee) {
		this.follower = follower;
		this.followee = followee;
	}
	
	public User getFollower() {
		return follower;
	}

	public void setFollower(User follower) {
		this.follower = follower;
	}

	public User getFollowee() {
		return followee;
	}

	public void setFollowee(User followee) {
		this.followee = followee;
	}

}
