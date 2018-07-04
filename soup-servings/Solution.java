import java.util.HashMap;
import java.util.Map;

public class Solution {
	static Map<State, Double> cache;

	public double soupServings(int N) {
		if (N >= 6000) {
			return 1;
		}

		int m = N / 25 + (N % 25 == 0 ? 0 : 1);

		cache = new HashMap<State, Double>();
		return search(m, m);
	}

	double search(int remainA, int remainB) {
		if (remainA <= 0) {
			if (remainB <= 0) {
				return 0.5;
			} else {
				return 1;
			}
		} else if (remainB <= 0) {
			return 0;
		}

		State state = new State(remainA, remainB);
		if (cache.containsKey(state)) {
			return cache.get(state);
		}

		double result = 0.25 * (search(remainA - 4, remainB) + search(remainA - 3, remainB - 1)
				+ search(remainA - 2, remainB - 2) + search(remainA - 1, remainB - 3));
		cache.put(state, result);
		return result;
	}
}

class State {
	int remainA;
	int remainB;

	State(int remainA, int remainB) {
		this.remainA = remainA;
		this.remainB = remainB;
	}

	@Override
	public int hashCode() {
		return remainA * remainB;
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return remainA == other.remainA && remainB == other.remainB;
	}
}