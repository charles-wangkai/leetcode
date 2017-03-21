import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P_01Matrix {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
		int row = matrix.size();
		int col = matrix.get(0).size();

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int r = 0; r < row; r++) {
			List<Integer> line = new ArrayList<Integer>();
			for (int c = 0; c < col; c++) {
				line.add(-1);
			}
			result.add(line);
		}

		Queue<Point> queue = new LinkedList<Point>();
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (matrix.get(r).get(c) == 0) {
					queue.offer(new Point(r, c));

					result.get(r).set(c, 0);
				}
			}
		}

		while (!queue.isEmpty()) {
			Point head = queue.poll();

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int nextR = head.r + R_OFFSETS[i];
				int nextC = head.c + C_OFFSETS[i];

				if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && result.get(nextR).get(nextC) < 0) {
					queue.offer(new Point(nextR, nextC));

					result.get(nextR).set(nextC, result.get(head.r).get(head.c) + 1);
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