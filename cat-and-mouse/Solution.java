import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Solution {
	public int catMouseGame(int[][] graph) {
		int nodeNum = graph.length;

		Map<State, Integer> stateToResult = new HashMap<>();
		Set<State> pendingStates = new HashSet<>();

		for (int node = 1; node < nodeNum; node++) {
			setResult(graph, stateToResult, pendingStates, new State(node, node, true), 2);
			setResult(graph, stateToResult, pendingStates, new State(node, node, false), 2);
		}

		for (int catNode = 1; catNode < nodeNum; catNode++) {
			setResult(graph, stateToResult, pendingStates, new State(0, catNode, false), 1);
		}

		while (!pendingStates.isEmpty()) {
			State pendingState = pendingStates.iterator().next();
			pendingStates.remove(pendingState);

			List<State> nextStates = findAllNextStates(graph, pendingState);
			if (isMouseWin(stateToResult, pendingState.turnByMouseOrCat, nextStates)) {
				setResult(graph, stateToResult, pendingStates, pendingState, 1);
			} else if (isCatWin(stateToResult, pendingState.turnByMouseOrCat, nextStates)) {
				setResult(graph, stateToResult, pendingStates, pendingState, 2);
			}
		}

		return stateToResult.getOrDefault(new State(1, 2, true), 0);
	}

	void setResult(int[][] graph, Map<State, Integer> stateToResult, Set<State> pendingStates, State state,
			int result) {
		stateToResult.put(state, result);

		for (State prevState : findAllPrevStates(graph, state)) {
			if (!stateToResult.containsKey(prevState)) {
				pendingStates.add(prevState);
			}
		}
	}

	List<State> findAllPrevStates(int[][] graph, State state) {
		List<State> result = new ArrayList<>();
		if (state.turnByMouseOrCat) {
			for (int prevCatNode : graph[state.catNode]) {
				if (prevCatNode != 0) {
					result.add(new State(state.mouseNode, prevCatNode, false));
				}
			}

		} else {
			for (int prevMouseNode : graph[state.mouseNode]) {
				result.add(new State(prevMouseNode, state.catNode, true));
			}
		}
		return result;
	}

	List<State> findAllNextStates(int[][] graph, State state) {
		List<State> result = new ArrayList<>();
		if (state.turnByMouseOrCat) {
			for (int nextMouseNode : graph[state.mouseNode]) {
				result.add(new State(nextMouseNode, state.catNode, false));
			}
		} else {
			for (int nextCatNode : graph[state.catNode]) {
				if (nextCatNode != 0) {
					result.add(new State(state.mouseNode, nextCatNode, true));
				}
			}
		}
		return result;
	}

	boolean isMouseWin(Map<State, Integer> stateToResult, boolean currentTurnByMouseOrCat, List<State> nextStates) {
		if (currentTurnByMouseOrCat) {
			return nextStates.stream().anyMatch(nextState -> stateToResult.getOrDefault(nextState, -1) == 1);
		} else {
			return nextStates.stream().allMatch(nextState -> stateToResult.getOrDefault(nextState, -1) == 1);
		}
	}

	boolean isCatWin(Map<State, Integer> stateToResult, boolean currentTurnByMouseOrCat, List<State> nextStates) {
		if (currentTurnByMouseOrCat) {
			return nextStates.stream().allMatch(nextState -> stateToResult.getOrDefault(nextState, -1) == 2);
		} else {
			return nextStates.stream().anyMatch(nextState -> stateToResult.getOrDefault(nextState, -1) == 2);
		}
	}
}

class State {
	int mouseNode;
	int catNode;
	boolean turnByMouseOrCat;

	State(int mouseNode, int catNode, boolean turnByMouseOrCat) {
		this.mouseNode = mouseNode;
		this.catNode = catNode;
		this.turnByMouseOrCat = turnByMouseOrCat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mouseNode, catNode, turnByMouseOrCat);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return mouseNode == other.mouseNode && catNode == other.catNode && turnByMouseOrCat == other.turnByMouseOrCat;
	}
}