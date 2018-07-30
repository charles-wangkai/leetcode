import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Solution {
	static final int MODULUS = 1000000007;

	public int profitableSchemes(int G, int P, int[] group, int[] profit) {
		Map<State, Integer> stateToSchemeNum = new HashMap<>();
		stateToSchemeNum.put(new State(0, 0), 1);
		for (int i = 0; i < group.length; i++) {
			Map<State, Integer> nextStateToSchemeNum = new HashMap<>();

			for (State state : stateToSchemeNum.keySet()) {
				nextStateToSchemeNum.put(state, stateToSchemeNum.get(state));
			}

			for (State state : stateToSchemeNum.keySet()) {
				State nextState = new State(state.group + group[i], Math.min(P, state.profit + profit[i]));
				if (nextState.group <= G) {
					nextStateToSchemeNum.put(nextState,
							addMod(nextStateToSchemeNum.getOrDefault(nextState, 0), stateToSchemeNum.get(state)));
				}
			}

			stateToSchemeNum = nextStateToSchemeNum;
		}

		int result = 0;
		for (State state : stateToSchemeNum.keySet()) {
			if (state.profit >= P) {
				result = addMod(result, stateToSchemeNum.get(state));
			}
		}
		return result;
	}

	static int addMod(int x, int y) {
		return (x + y) % MODULUS;
	}
}

class State {
	int group;
	int profit;

	State(int group, int profit) {
		this.group = group;
		this.profit = profit;
	}

	@Override
	public int hashCode() {
		return Objects.hash(group, profit);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return group == other.group && profit == other.profit;
	}
}