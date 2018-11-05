import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int shortestBridge(int[][] A) {
		int row = A.length;
		int col = A[0].length;

		int[][] distances = new int[row][col];
		for (int r = 0; r < row; r++) {
			Arrays.fill(distances[r], -1);
		}

		Point pointOne = findPointOne(A);
		Queue<Point> queue = new LinkedList<>();
		dfs(A, distances, pointOne.r, pointOne.c, queue);

		while (true) {
			Point head = queue.poll();

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int nextR = head.r + R_OFFSETS[i];
				int nextC = head.c + C_OFFSETS[i];

				if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && distances[nextR][nextC] == -1) {
					distances[nextR][nextC] = distances[head.r][head.c] + 1;

					if (A[nextR][nextC] == 1) {
						return distances[nextR][nextC] - 1;
					}

					queue.offer(new Point(nextR, nextC));
				}
			}
		}
	}

	Point findPointOne(int[][] A) {
		int col = A[0].length;

		for (int r = 0;; r++) {
			for (int c = 0; c < col; c++) {
				if (A[r][c] == 1) {
					return new Point(r, c);
				}
			}
		}
	}

	void dfs(int[][] A, int[][] distances, int r, int c, Queue<Point> queue) {
		int row = A.length;
		int col = A[0].length;

		distances[r][c] = 0;
		queue.offer(new Point(r, c));

		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r + R_OFFSETS[i];
			int nextC = c + C_OFFSETS[i];

			if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && A[nextR][nextC] == 1
					&& distances[nextR][nextC] == -1) {
				dfs(A, distances, nextR, nextC, queue);
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
}
