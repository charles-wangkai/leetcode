public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int orderOfLargestPlusSign(int N, int[][] mines) {
		char[][] grid = new char[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				grid[r][c] = '1';
			}
		}

		for (int[] mine : mines) {
			grid[mine[0]][mine[1]] = '0';
		}

		int maxOrder = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				maxOrder = Math.max(maxOrder, getOrder(grid, r, c));
			}
		}
		return maxOrder;
	}

	int getOrder(char[][] grid, int r, int c) {
		for (int order = 1;; order++) {
			if (!isAllOne(grid, r, c, order)) {
				return order - 1;
			}
		}
	}

	boolean isAllOne(char[][] grid, int r, int c, int order) {
		int N = grid.length;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r + R_OFFSETS[i] * (order - 1);
			int nextC = c + C_OFFSETS[i] * (order - 1);
			if (!(nextR >= 0 && nextR < N && nextC >= 0 && nextC < N && grid[nextR][nextC] == '1')) {
				return false;
			}
		}
		return true;
	}
}
