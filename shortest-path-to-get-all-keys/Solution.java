import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int shortestPathAllKeys(String[] grid) {
		Point startPoint = findStartPoint(grid);
		int totalKeyNum = countKeys(grid);
		State startState = new State(startPoint, Collections.emptySet());
		Set<State> history = new HashSet<>();
		history.add(startState);
		Queue<Element> queue = new LinkedList<>();
		queue.offer(new Element(startState, 0));
		while (!queue.isEmpty()) {
			Element head = queue.poll();

			if (head.state.keys.size() == totalKeyNum) {
				return head.moveNum;
			}

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int nextR = head.state.point.r + R_OFFSETS[i];
				int nextC = head.state.point.c + C_OFFSETS[i];

				State nextState = buildNextState(head.state, grid, nextR, nextC);
				if (nextState != null && !history.contains(nextState)) {
					history.add(nextState);
					queue.offer(new Element(nextState, head.moveNum + 1));
				}
			}
		}

		return -1;
	}

	State buildNextState(State state, String[] grid, int nextR, int nextC) {
		int row = grid.length;
		int col = grid[0].length();

		if (!(nextR >= 0 && nextR < row && nextC >= 0 && nextC < col)) {
			return null;
		}

		Set<Character> nextKeys = new HashSet<>(state.keys);
		char cell = grid[nextR].charAt(nextC);
		if (cell == '#') {
			return null;
		} else if (Character.isUpperCase(cell)) {
			if (!state.keys.contains(Character.toLowerCase(cell))) {
				return null;
			}
		} else if (Character.isLowerCase(cell)) {
			nextKeys.add(cell);
		}

		return new State(new Point(nextR, nextC), nextKeys);
	}

	Point findStartPoint(String[] grid) {
		for (int r = 0;; r++) {
			for (int c = 0; c < grid[0].length(); c++) {
				if (grid[r].charAt(c) == '@') {
					return new Point(r, c);
				}
			}
		}
	}

	int countKeys(String[] grid) {
		int row = grid.length;
		int col = grid[0].length();

		int totalKeyNum = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (Character.isLowerCase(grid[r].charAt(c))) {
					totalKeyNum++;
				}
			}
		}
		return totalKeyNum;
	}
}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public int hashCode() {
		return Objects.hash(r, c);
	}

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return r == other.r && c == other.c;
	}
}

class State {
	Point point;
	Set<Character> keys;

	State(Point point, Set<Character> keys) {
		this.point = point;
		this.keys = keys;
	}

	@Override
	public int hashCode() {
		return Objects.hash(point, keys);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return point.equals(other.point) && keys.equals(other.keys);
	}
}

class Element {
	State state;
	int moveNum;

	Element(State state, int moveNum) {
		this.state = state;
		this.moveNum = moveNum;
	}
}