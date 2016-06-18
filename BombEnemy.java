public class BombEnemy {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int maxKilledEnemies(char[][] grid) {
		int row = grid.length;
		if (row == 0) {
			return 0;
		}
		int col = grid[0].length;
		if (col == 0) {
			return 0;
		}

		int maxKilled = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] == '0') {
					maxKilled = Math.max(maxKilled, findKilled(grid, r, c));
				}
			}
		}
		return maxKilled;
	}

	int findKilled(char[][] grid, int r, int c) {
		int killed = 0;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			killed += kill(grid, r, c, R_OFFSETS[i], C_OFFSETS[i]);
		}
		return killed;
	}

	int kill(char[][] grid, int r, int c, int rOffset, int cOffset) {
		int killed = 0;
		int row = grid.length;
		int col = grid[0].length;
		while (r >= 0 && r < row && c >= 0 && c < col && grid[r][c] != 'Y') {
			if (grid[r][c] == 'X') {
				killed++;
			}

			r += rOffset;
			c += cOffset;
		}
		return killed;
	}
}
