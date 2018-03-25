public class MaxIncreaseToKeepCitySkyline {
	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int[] maxRows = new int[row];
		for (int i = 0; i < maxRows.length; i++) {
			maxRows[i] = max(grid, i, 0, 0, 1);
		}

		int[] maxCols = new int[col];
		for (int i = 0; i < maxCols.length; i++) {
			maxCols[i] = max(grid, 0, i, 1, 0);
		}

		int result = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				result += Math.min(maxRows[r], maxCols[c]) - grid[r][c];
			}
		}
		return result;
	}

	int max(int[][] grid, int beginR, int beginC, int offsetR, int offsetC) {
		int row = grid.length;
		int col = grid[0].length;

		int result = Integer.MIN_VALUE;
		for (int r = beginR, c = beginC; r >= 0 && r < row && c >= 0 && c < col; r += offsetR, c += offsetC) {
			result = Math.max(result, grid[r][c]);
		}
		return result;
	}
}
