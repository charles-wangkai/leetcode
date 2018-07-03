import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Twitter {
	Deque<Tweet> tweets;
	Map<Integer, Set<Integer>> follower2followees;

	/** Initialize your data structure here. */
	public Twitter() {
		tweets = new LinkedList<Tweet>();
		follower2followees = new HashMap<Integer, Set<Integer>>();
	}

	/** Compose a new tweet. */
	public void postTweet(int userId, int tweetId) {
		tweets.addFirst(new Tweet(tweetId, userId));
	}

	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item
	 * in the news feed must be posted by users who the user followed or by the
	 * user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		return tweets.stream()
				.filter(tweet -> tweet.userId == userId || (follower2followees.containsKey(userId)
						&& follower2followees.get(userId).contains(tweet.userId)))
				.limit(10).map(tweet -> tweet.tweetId).collect(Collectors.toList());
	}

	/**
	 * Follower follows a followee. If the operation is invalid, it should be a
	 * no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if (!follower2followees.containsKey(followerId)) {
			follower2followees.put(followerId, new HashSet<Integer>());
		}
		follower2followees.get(followerId).add(followeeId);
	}

	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be
	 * a no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		if (!follower2followees.containsKey(followerId)) {
			return;
		}
		follower2followees.get(followerId).remove(followeeId);

		if (follower2followees.get(followerId).isEmpty()) {
			follower2followees.remove(followerId);
		}
	}
}

class Tweet {
	int tweetId;
	int userId;

	Tweet(int tweetId, int userId) {
		this.tweetId = tweetId;
		this.userId = userId;
	}
}

/**
 * Your Twitter object will be instantiated and called as such: Twitter obj =
 * new Twitter(); obj.postTweet(userId,tweetId); List<Integer> param_2 =
 * obj.getNewsFeed(userId); obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */