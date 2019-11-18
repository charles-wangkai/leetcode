import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int minPushBox(char[][] grid) {
		Point player = find(grid, 'S');
		Point box = find(grid, 'B');

		State beginState = new State(player, box);

		Map<State, Integer> stateToPushNum = new HashMap<>();
		stateToPushNum.put(beginState, 0);

		Queue<State> queue = new LinkedList<>();
		queue.offer(beginState);

		int result = -1;
		while (!queue.isEmpty()) {
			State head = queue.poll();
			int pushNum = stateToPushNum.get(head);

			if (grid[head.box.r][head.box.c] == 'T' && (result == -1 || pushNum < result)) {
				result = pushNum;
			}

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int nextPlayerR = head.player.r + R_OFFSETS[i];
				int nextPlayerC = head.player.c + C_OFFSETS[i];

				int nextBoxR = head.box.r;
				int nextBoxC = head.box.c;
				int nextPushNum = pushNum;
				if (nextPlayerR == nextBoxR && nextPlayerC == nextBoxC) {
					nextBoxR += R_OFFSETS[i];
					nextBoxC += C_OFFSETS[i];
					nextPushNum++;
				}

				State nextState = new State(new Point(nextPlayerR, nextPlayerC), new Point(nextBoxR, nextBoxC));
				if (isFreeCell(grid, nextPlayerR, nextPlayerC) && isFreeCell(grid, nextBoxR, nextBoxC)
						&& (!stateToPushNum.containsKey(nextState) || nextPushNum < stateToPushNum.get(nextState))) {
					stateToPushNum.put(nextState, nextPushNum);
					queue.offer(nextState);
				}
			}
		}

		return result;
	}

	boolean isFreeCell(char[][] grid, int r, int c) {
		return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] != '#';
	}

	Point find(char[][] grid, char target) {
		for (int r = 0;; r++) {
			for (int c = 0; c < grid[0].length; c++) {
				if (grid[r][c] == target) {
					return new Point(r, c);
				}
			}
		}
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
	Point player;
	Point box;

	State(Point player, Point box) {
		this.player = player;
		this.box = box;
	}

	@Override
	public int hashCode() {
		return Objects.hash(player, box);
	}

	@Override
	public boolean equals(Object obj) {
		State other = (State) obj;
		return Objects.equals(player, other.player) && Objects.equals(box, other.box);
	}
}