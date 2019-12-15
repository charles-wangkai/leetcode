import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int shortestPath(int[][] grid, int k) {
		int m = grid.length;
		int n = grid[0].length;

		int[][][] minDistances = new int[m][n][k + 1];
		for (int r = 0; r < m; r++) {
			for (int c = 0; c < n; c++) {
				for (int elimRemain = 0; elimRemain <= k; elimRemain++) {
					minDistances[r][c][elimRemain] = Integer.MAX_VALUE;
				}
			}
		}
		minDistances[0][0][k] = 0;

		Queue<Element> queue = new LinkedList<>();
		queue.offer(new Element(0, 0, k));

		while (!queue.isEmpty()) {
			Element head = queue.poll();

			for (int i = 0; i < R_OFFSETS.length; i++) {
				int adjR = head.r + R_OFFSETS[i];
				int adjC = head.c + C_OFFSETS[i];

				if (adjR >= 0 && adjR < m && adjC >= 0 && adjC < n) {
					if (grid[adjR][adjC] == 0) {
						if (minDistances[adjR][adjC][head.elimRemain] == Integer.MAX_VALUE) {
							minDistances[adjR][adjC][head.elimRemain] = minDistances[head.r][head.c][head.elimRemain]
									+ 1;
							queue.offer(new Element(adjR, adjC, head.elimRemain));
						}
					} else {
						if (head.elimRemain != 0
								&& minDistances[adjR][adjC][head.elimRemain - 1] == Integer.MAX_VALUE) {
							minDistances[adjR][adjC][head.elimRemain
									- 1] = minDistances[head.r][head.c][head.elimRemain] + 1;
							queue.offer(new Element(adjR, adjC, head.elimRemain - 1));
						}
					}
				}
			}
		}

		int result = Arrays.stream(minDistances[m - 1][n - 1]).min().getAsInt();

		return (result == Integer.MAX_VALUE) ? -1 : result;
	}
}

class Element {
	int r;
	int c;
	int elimRemain;

	Element(int r, int c, int elimRemain) {
		this.r = r;
		this.c = c;
		this.elimRemain = elimRemain;
	}
}