import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {
	Map<State, Integer> cache = new HashMap<>();

	public int mergeStones(int[] stones, int K) {
		int[] prefixSums = new int[stones.length];
		int prefixSum = 0;
		for (int i = 0; i < prefixSums.length; i++) {
			prefixSum += stones[i];
			prefixSums[i] = prefixSum;
		}

		cache.clear();
		return search(prefixSums, K, new State(0, stones.length - 1, 1));
	}

	int search(int[] prefixSums, int K, State state) {
		if ((state.end - state.begin + 1 - state.pileNum) % (K - 1) != 0) {
			return -1;
		}

		if (cache.containsKey(state)) {
			return cache.get(state);
		}

		int result;
		if (state.begin == state.end) {
			result = (state.pileNum == 1) ? 0 : -1;
		} else if (state.pileNum == 1) {
			result = search(prefixSums, K, new State(state.begin, state.end, K))
					+ (prefixSums[state.end] - (state.begin == 0 ? 0 : prefixSums[state.begin - 1]));
		} else {
			result = -1;
			for (int i = state.begin; i < state.end; i++) {
				int subLeftResult = search(prefixSums, K, new State(state.begin, i, 1));

				if (subLeftResult != -1) {
					int subRightResult = search(prefixSums, K, new State(i + 1, state.end, state.pileNum - 1));

					if (subRightResult != -1) {
						int subResult = subLeftResult + subRightResult;

						if (result == -1 || subResult < result) {
							result = subResult;
						}
					}
				}
			}
		}

		if (result != -1) {
			cache.put(state, result);
		}
		return result;
	}
}

class State {
	int begin;
	int end;
	int pileNum;

	State(int begin, int end, int pileNum) {
		this.begin = begin;
		this.end = end;
		this.pileNum = pileNum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(begin, end, pileNum);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return begin == other.begin && end == other.end && pileNum == other.pileNum;
	}
}
