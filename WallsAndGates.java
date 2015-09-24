import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGates {
	static final int INF = 2147483647;
	static final int[] OFFSET_R = { -1, 0, 1, 0 };
	static final int[] OFFSET_C = { 0, 1, 0, -1 };

	public void wallsAndGates(int[][] rooms) {
		int row = rooms.length;
		if (row == 0) {
			return;
		}
		int col = rooms[0].length;
		if (col == 0) {
			return;
		}

		Queue<Point> queue = new LinkedList<Point>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (rooms[i][j] == 0) {
					queue.offer(new Point(i, j));
				}
			}
		}
		while (!queue.isEmpty()) {
			Point head = queue.poll();
			for (int i = 0; i < OFFSET_R.length; i++) {
				int r = head.r + OFFSET_R[i];
				int c = head.c + OFFSET_C[i];
				if (r >= 0 && r < row && c >= 0 && c < col && rooms[r][c] == INF) {
					rooms[r][c] = rooms[head.r][head.c] + 1;
					queue.offer(new Point(r, c));
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
}