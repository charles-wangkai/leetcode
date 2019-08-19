import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int maxDistance(int[][] grid) {
		int N = grid.length;

		int[][] distances = new int[N][N];
		Queue<Point> queue = new LinkedList<>();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (grid[r][c] == 1) {
					queue.offer(new Point(r, c));
				}
			}
		}

		int result = -1;
		while (!queue.isEmpty()) {
			Point head = queue.poll();

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int adjR = head.r + R_OFFSETS[i];
				int adjC = head.c + C_OFFSETS[i];

				if (adjR >= 0 && adjR < N && adjC >= 0 && adjC < N && grid[adjR][adjC] == 0
						&& distances[adjR][adjC] == 0) {
					distances[adjR][adjC] = distances[head.r][head.c] + 1;
					result = distances[adjR][adjC];

					queue.offer(new Point(adjR, adjC));
				}
			}
		}

		return result;
	}
}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}