public class MinimumPathSum {
	public int minPathSum(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;
		int[][] minSums = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				minSums[i][j] = grid[i][j]
						+ ((i == 0 && j == 0) ? 0 : Math.min(
								i == 0 ? Integer.MAX_VALUE : minSums[i - 1][j],
								j == 0 ? Integer.MAX_VALUE : minSums[i][j - 1]));
			}
		}
		return minSums[row - 1][col - 1];
	}
}
