import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {
	Map<State, Integer> cache;

	public int stoneGameII(int[] piles) {
		cache = new HashMap<>();
		return computeMaxStoneNumForFirstPlayer(piles, 0, 1);
	}

	int computeMaxStoneNumForFirstPlayer(int[] piles, int beginIndex, int M) {
		State state = new State(beginIndex, M);
		if (cache.containsKey(state)) {
			return cache.get(state);
		}

		int result = 0;
		int taken = 0;
		int remain = 0;
		for (int i = beginIndex; i < piles.length; i++) {
			remain += piles[i];
		}
		for (int X = 1; X <= 2 * M && beginIndex + X <= piles.length; X++) {
			taken += piles[beginIndex + X - 1];
			remain -= piles[beginIndex + X - 1];

			int subResult = computeMaxStoneNumForFirstPlayer(piles, beginIndex + X, Math.max(M, X));

			result = Math.max(result, taken + (remain - subResult));
		}

		cache.put(state, result);
		return result;
	}

}

class State {
	int beginIndex;
	int M;

	State(int beginIndex, int M) {
		this.beginIndex = beginIndex;
		this.M = M;
	}

	@Override
	public int hashCode() {
		return Objects.hash(beginIndex, M);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return beginIndex == other.beginIndex && M == other.M;
	}
}