import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class Solution {
	public int minimumMoves(int[][] grid) {
		int n = grid.length;

		Map<State, Integer> stateToDistance = new HashMap<>();
		Queue<State> queue = new LinkedList<>();
		State initialState = new State(0, 0, true);
		stateToDistance.put(initialState, 0);
		queue.offer(initialState);

		while (!queue.isEmpty()) {
			State head = queue.poll();
			if (isEnd(n, head)) {
				return stateToDistance.get(head);
			}

			for (State nextState : new State[] { moveRight(grid, head), moveDown(grid, head),
					rotateClockwise(grid, head), rotateCounterclockwise(grid, head) }) {
				if (nextState != null && !stateToDistance.containsKey(nextState)) {
					stateToDistance.put(nextState, stateToDistance.get(head) + 1);
					queue.offer(nextState);
				}
			}
		}

		return -1;
	}

	State moveRight(int[][] grid, State state) {
		int n = grid.length;

		if (state.horizontalOrVertical) {
			if (!(state.c + 2 < n && grid[state.r][state.c + 2] == 0)) {
				return null;
			}
		} else {
			if (!(state.c + 1 < n && grid[state.r][state.c + 1] == 0 && grid[state.r + 1][state.c + 1] == 0)) {
				return null;
			}
		}

		return new State(state.r, state.c + 1, state.horizontalOrVertical);
	}

	State moveDown(int[][] grid, State state) {
		int n = grid.length;

		if (state.horizontalOrVertical) {
			if (!(state.r + 1 < n && grid[state.r + 1][state.c] == 0 && grid[state.r + 1][state.c + 1] == 0)) {
				return null;
			}
		} else {
			if (!(state.r + 2 < n && grid[state.r + 2][state.c] == 0)) {
				return null;
			}
		}

		return new State(state.r + 1, state.c, state.horizontalOrVertical);
	}

	State rotateClockwise(int[][] grid, State state) {
		int n = grid.length;

		if (state.horizontalOrVertical && state.r + 1 < n && grid[state.r + 1][state.c] == 0
				&& grid[state.r + 1][state.c + 1] == 0) {
			return new State(state.r, state.c, false);
		} else {
			return null;
		}
	}

	State rotateCounterclockwise(int[][] grid, State state) {
		int n = grid.length;

		if (!state.horizontalOrVertical && state.c + 1 < n && grid[state.r][state.c + 1] == 0
				&& grid[state.r + 1][state.c + 1] == 0) {
			return new State(state.r, state.c, true);
		} else {
			return null;
		}
	}

	boolean isEnd(int n, State state) {
		return state.r == n - 1 && state.c == n - 2 && state.horizontalOrVertical;
	}
}

class State {
	int r;
	int c;
	boolean horizontalOrVertical;

	State(int r, int c, boolean horizontalOrVertical) {
		this.r = r;
		this.c = c;
		this.horizontalOrVertical = horizontalOrVertical;
	}

	@Override
	public int hashCode() {
		return Objects.hash(r, c, horizontalOrVertical);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return r == other.r && c == other.c && horizontalOrVertical == other.horizontalOrVertical;
	}
}