import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int orangesRotting(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int[][] distances = new int[row][col];
		for (int r = 0; r < row; ++r) {
			Arrays.fill(distances[r], Integer.MAX_VALUE);
		}

		Queue<Element> queue = new LinkedList<>();
		for (int r = 0; r < row; ++r) {
			for (int c = 0; c < col; ++c) {
				if (grid[r][c] == 2) {
					distances[r][c] = 0;
					queue.offer(new Element(r, c));
				}
			}
		}

		while (!queue.isEmpty()) {
			Element head = queue.poll();

			for (int i = 0; i < R_OFFSETS.length; ++i) {
				int adjR = head.r + R_OFFSETS[i];
				int adjC = head.c + C_OFFSETS[i];

				if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && grid[adjR][adjC] == 1
						&& distances[adjR][adjC] == Integer.MAX_VALUE) {
					distances[adjR][adjC] = distances[head.r][head.c] + 1;
					queue.offer(new Element(adjR, adjC));
				}
			}
		}

		int result = 0;
		for (int r = 0; r < row; ++r) {
			for (int c = 0; c < col; ++c) {
				if (grid[r][c] == 1) {
					if (distances[r][c] == Integer.MAX_VALUE) {
						return -1;
					}

					result = Math.max(result, distances[r][c]);
				}
			}
		}

		return result;
	}
}

class Element {
	int r;
	int c;

	Element(int r, int c) {
		this.r = r;
		this.c = c;
	}
}