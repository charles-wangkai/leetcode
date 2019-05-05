import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {
	Map<State, Integer> cache;

	public int minScoreTriangulation(int[] A) {
		cache = new HashMap<>();
		return search(A, 0, A.length - 2);
	}

	int search(int[] A, int beginIndex, int endIndex) {
		if (beginIndex == endIndex) {
			return 0;
		}

		State state = new State(beginIndex, endIndex);
		if (cache.containsKey(state)) {
			return cache.get(state);
		}

		int result = Integer.MAX_VALUE;
		for (int i = beginIndex; i < endIndex; i++) {
			result = Math.min(result,
					search(A, beginIndex, i) + A[beginIndex] * A[i + 1] * A[endIndex + 1] + search(A, i + 1, endIndex));
		}

		cache.put(state, result);
		return result;
	}
}

class State {
	int beginIndex;
	int endIndex;

	State(int beginIndex, int endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	@Override
	public int hashCode() {
		return Objects.hash(beginIndex, endIndex);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return beginIndex == other.beginIndex && endIndex == other.endIndex;
	}
}