import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
	static int[] R_ADJACENTS = { -1, 0, 1, 0 };
	static int[] C_ADJACENTS = { 0, 1, 0, -1 };

	public List<int[]> pacificAtlantic(int[][] matrix) {
		List<int[]> result = new ArrayList<int[]>();

		int row = matrix.length;
		if (row == 0) {
			return result;
		}
		int col = matrix[0].length;
		if (col == 0) {
			return result;
		}

		boolean[][] pacific = bfs(matrix, 0, 0, new int[] { 0, 1 }, new int[] { 1, 0 });
		boolean[][] atlantic = bfs(matrix, row - 1, col - 1, new int[] { -1, 0 }, new int[] { 0, -1 });

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (pacific[r][c] && atlantic[r][c]) {
					result.add(new int[] { r, c });
				}
			}
		}

		return result;
	}

	boolean[][] bfs(int[][] matrix, int cornerR, int cornerC, int[] offsetRs, int[] offsetCs) {
		int row = matrix.length;
		int col = matrix[0].length;

		boolean[][] reaches = new boolean[row][col];
		Queue<int[]> queue = new LinkedList<int[]>();

		reaches[cornerR][cornerC] = true;
		queue.offer(new int[] { cornerR, cornerC });

		for (int i = 0; i < offsetRs.length; i++) {
			for (int r = cornerR + offsetRs[i], c = cornerC + offsetCs[i]; r >= 0 && r < row && c >= 0
					&& c < col; r += offsetRs[i], c += offsetCs[i]) {
				reaches[r][c] = true;
				queue.offer(new int[] { r, c });
			}
		}

		while (!queue.isEmpty()) {
			int[] head = queue.poll();
			int r = head[0];
			int c = head[1];

			for (int i = 0; i < R_ADJACENTS.length; i++) {
				int nextR = r + R_ADJACENTS[i];
				int nextC = c + C_ADJACENTS[i];

				if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && !reaches[nextR][nextC]
						&& matrix[nextR][nextC] >= matrix[r][c]) {
					reaches[nextR][nextC] = true;
					queue.offer(new int[] { nextR, nextC });
				}
			}
		}

		return reaches;
	}
}
