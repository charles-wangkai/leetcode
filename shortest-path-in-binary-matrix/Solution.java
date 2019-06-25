import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static final int[] R_OFFSETS = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] C_OFFSETS = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public int shortestPathBinaryMatrix(int[][] grid) {
		if (grid[0][0] == 1) {
			return -1;
		}

		int N = grid.length;

		int[][] minLengths = new int[N][N];
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0));
		minLengths[0][0] = 1;

		while (!queue.isEmpty()) {
			Point head = queue.poll();

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int nextR = head.r + R_OFFSETS[i];
				int nextC = head.c + C_OFFSETS[i];

				if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < N && grid[nextR][nextC] == 0
						&& minLengths[nextR][nextC] == 0) {
					minLengths[nextR][nextC] = minLengths[head.r][head.c] + 1;
					if (nextR == N - 1 && nextC == N - 1) {
						return minLengths[nextR][nextC];
					}

					queue.offer(new Point(nextR, nextC));
				}
			}
		}

		return -1;
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