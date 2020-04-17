public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}

		int row = grid.length;
		int col = grid[0].length;

		int islandNum = 0;
		for (int r = 0; r < row; ++r) {
			for (int c = 0; c < col; ++c) {
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

		for (int i = 0; i < R_OFFSETS.length; ++i) {
			flood(grid, r + R_OFFSETS[i], c + C_OFFSETS[i]);
		}
	}

	boolean isInGrid(char[][] grid, int r, int c) {
		int row = grid.length;
		int col = grid[0].length;

		return r >= 0 && r < row && c >= 0 && c < col;
	}
}
