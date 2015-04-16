public class NumberOfIslands {
	static final int[] OFFSET_R = { -1, 0, 1, 0 };
	static final int[] OFFSET_C = { 0, 1, 0, -1 };

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}

		int islandNum = 0;
		int row = grid.length;
		int col = grid[0].length;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] == '1') {
					flood(grid, r, c);
					islandNum++;
				}
			}
		}
		return islandNum;
	}

	void flood(char[][] grid, int r, int c) {
		if (!isInGrid(grid, r, c) || grid[r][c] == '0') {
			return;
		}

		grid[r][c] = '0';
		for (int i = 0; i < OFFSET_R.length; i++) {
			flood(grid, r + OFFSET_R[i], c + OFFSET_C[i]);
		}
	}

	boolean isInGrid(char[][] grid, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;
		return r >= 0 && r < row && c >= 0 && c < col;
	}
}
