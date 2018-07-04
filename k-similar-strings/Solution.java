import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class Solution {
	public int kSimilarity(String A, String B) {
		State state = new State(A, B);
		Map<State, Integer> stateToStep = new HashMap<>();
		stateToStep.put(state, 0);
		Queue<State> states = new LinkedList<>();
		states.offer(state);

		while (true) {
			State head = states.poll();
			int step = stateToStep.get(head);
			if (head.source.isEmpty()) {
				return step;
			}

			String nextSource = findPerfectSwap(head);
			if (nextSource != null) {
				checkAndAddNext(stateToStep, states, step, new State(nextSource, head.target));
			} else {
				for (int i = 0; i < head.source.length(); i++) {
					if (head.source.charAt(i) != head.target.charAt(i)) {
						for (int j = 0; j < head.source.length(); j++) {
							if (head.source.charAt(j) == head.target.charAt(i)) {
								checkAndAddNext(stateToStep, states, step,
										new State(swap(head.source, i, j), head.target));
							}
						}
					}
				}
			}
		}
	}

	String findPerfectSwap(State state) {
		for (int i = 0; i < state.target.length(); i++) {
			if (state.source.charAt(i) != state.target.charAt(i)) {
				for (int j = 0; j < state.target.length(); j++) {
					if (state.source.charAt(j) == state.target.charAt(i)
							&& state.source.charAt(i) == state.target.charAt(j)) {
						return swap(state.source, i, j);
					}
				}
			}
		}
		return null;
	}

	String swap(String s, int index1, int index2) {
		StringBuilder sb = new StringBuilder(s);
		char temp = sb.charAt(index1);
		sb.setCharAt(index1, sb.charAt(index2));
		sb.setCharAt(index2, temp);
		return sb.toString();
	}

	void checkAndAddNext(Map<State, Integer> stateToStep, Queue<State> states, int currentStep, State nextState) {
		if (!stateToStep.containsKey(nextState)) {
			stateToStep.put(nextState, currentStep + 1);
			states.offer(nextState);
		}
	}
}

class State {
	String source;
	String target;

	State(String source, String target) {
		StringBuilder s = new StringBuilder();
		StringBuilder t = new StringBuilder();
		for (int i = 0; i < source.length(); i++) {
			if (source.charAt(i) != target.charAt(i)) {
				s.append(source.charAt(i));
				t.append(target.charAt(i));
			}
		}

		this.source = s.toString();
		this.target = t.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(source, target);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return source.equals(other.source) && target.equals(other.target);
	}
}