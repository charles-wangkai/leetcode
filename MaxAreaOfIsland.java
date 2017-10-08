public class MaxAreaOfIsland {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int maxAreaOfIsland(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int maxArea = 0;
		boolean[][] visited = new boolean[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				maxArea = Math.max(maxArea, search(grid, visited, r, c));
			}
		}
		return maxArea;
	}

	int search(int[][] grid, boolean[][] visited, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;

		if (!(r >= 0 && r < row && c >= 0 && c < col) || visited[r][c] || grid[r][c] == 0) {
			return 0;
		}

		visited[r][c] = true;
		int result = 1;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			result += search(grid, visited, r + R_OFFSETS[i], c + C_OFFSETS[i]);
		}
		return result;
	}
}
