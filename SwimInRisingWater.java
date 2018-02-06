import java.util.LinkedList;
import java.util.Queue;

public class SwimInRisingWater {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int swimInWater(int[][] grid) {
		int N = grid.length;

		int lower = grid[0][0];
		int upper = N * N - 1;
		int result = -1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (isValid(grid, middle)) {
				result = middle;

				upper = middle - 1;
			} else {
				lower = middle + 1;
			}
		}
		return result;
	}

	boolean isValid(int[][] grid, int time) {
		int N = grid.length;

		boolean[][] visited = new boolean[N][N];

		Queue<Point> points = new LinkedList<Point>();
		visited[0][0] = true;
		points.offer(new Point(0, 0));
		while (!points.isEmpty()) {
			Point head = points.poll();

			if (head.r == N - 1 && head.c == N - 1) {
				return true;
			}

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int adjR = head.r + R_OFFSETS[i];
				int adjC = head.c + C_OFFSETS[i];

				if (adjR >= 0 && adjR < N && adjC >= 0 && adjC < N && !visited[adjR][adjC]
						&& grid[adjR][adjC] <= time) {
					visited[adjR][adjC] = true;
					points.offer(new Point(adjR, adjC));
				}
			}
		}
		return false;
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
		return r * c;
	}

	@Override
	public boolean equals(Object obj) {
		Point other = (Point) obj;
		return r == other.r && c == other.c;
	}
}