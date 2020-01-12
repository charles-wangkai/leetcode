import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Solution {
	static final int COLUMN_SIZE = 6;

	public int minimumDistance(String word) {
		Map<State, Integer> stateToDistance = new HashMap<>();
		stateToDistance.put(new State((char) 0, (char) 0), 0);

		for (char current : word.toCharArray()) {
			List<State> currStates = new ArrayList<>();
			for (char ch = 'A'; ch <= 'Z'; ++ch) {
				currStates.add(new State(ch, current));
				currStates.add(new State((char) 0, current));

				currStates.add(new State(current, ch));
				currStates.add(new State(current, (char) 0));
			}

			Map<State, Integer> nextStateToDistance = new HashMap<>();
			for (State currState : currStates) {
				for (State prevState : stateToDistance.keySet()) {
					if (canSwitchState(prevState, currState)) {
						updateMap(nextStateToDistance, currState,
								stateToDistance.get(prevState) + computeStateDistance(prevState, currState));
					}
				}
			}

			stateToDistance = nextStateToDistance;
		}

		return stateToDistance.values().stream().mapToInt(x -> x).min().getAsInt();
	}

	boolean canSwitchState(State prevState, State currState) {
		return !(prevState.left == 0 && prevState.right == 0 && currState.left != 0 && currState.right != 0)
				&& canSwitchLetter(prevState.left, currState.left) && canSwitchLetter(prevState.right, currState.right);
	}

	boolean canSwitchLetter(char prevLetter, char currLetter) {
		return currLetter != 0 || prevLetter == 0;
	}

	int toIndex(char letter) {
		return letter - 'A';
	}

	int computeStateDistance(State prevState, State currState) {
		return computeLetterDistance(prevState.left, currState.left)
				+ computeLetterDistance(prevState.right, currState.right);
	}

	int computeLetterDistance(char prevLetter, char currLetter) {
		if (prevLetter == 0) {
			return 0;
		}

		return Math.abs(toIndex(prevLetter) / COLUMN_SIZE - toIndex(currLetter) / COLUMN_SIZE)
				+ Math.abs(toIndex(prevLetter) % COLUMN_SIZE - toIndex(currLetter) % COLUMN_SIZE);
	}

	void updateMap(Map<State, Integer> stateToDistance, State state, int distance) {
		stateToDistance.put(state, Math.min(stateToDistance.getOrDefault(state, Integer.MAX_VALUE), distance));
	}
}

class State {
	char left;
	char right;

	State(char left, char right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public int hashCode() {
		return Objects.hash(left, right);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return left == other.left && right == other.right;
	}
}