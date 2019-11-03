import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Leaderboard {
	Map<Integer, Integer> playerIdToScore = new HashMap<>();

	public void addScore(int playerId, int score) {
		playerIdToScore.put(playerId, playerIdToScore.getOrDefault(playerId, 0) + score);
	}

	public int top(int K) {
		return playerIdToScore.values().stream().sorted(Collections.reverseOrder()).limit(K).mapToInt(x -> x).sum();
	}

	public void reset(int playerId) {
		playerIdToScore.put(playerId, 0);
	}
}

// Your Leaderboard object will be instantiated and called as such:
// Leaderboard obj = new Leaderboard();
// obj.addScore(playerId,score);
// int param_2 = obj.top(K);
// obj.reset(playerId);
