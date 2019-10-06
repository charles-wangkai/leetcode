public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int getMaximumGold(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		boolean[][] visited = new boolean[row][col];

		int result = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] != 0) {
					result = Math.max(result, search(grid, visited, r, c));
				}
			}
		}

		return result;
	}

	static int search(int[][] grid, boolean[][] visited, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;

		visited[r][c] = true;

		int maxSubResult = 0;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int adjR = r + R_OFFSETS[i];
			int adjC = c + C_OFFSETS[i];

			if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && grid[adjR][adjC] != 0 && !visited[adjR][adjC]) {
				maxSubResult = Math.max(maxSubResult, search(grid, visited, adjR, adjC));
			}
		}

		visited[r][c] = false;

		return grid[r][c] + maxSubResult;
	}
}
