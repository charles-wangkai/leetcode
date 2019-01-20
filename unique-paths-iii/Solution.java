public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	int pathNum;

	public int uniquePathsIII(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int step = -1;
		int startR = -1;
		int startC = -1;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (grid[r][c] != -1) {
					step++;

					if (grid[r][c] == 1) {
						startR = r;
						startC = c;
					}
				}
			}
		}

		pathNum = 0;
		search(grid, step, startR, startC);
		return pathNum;
	}

	void search(int[][] grid, int step, int r, int c) {
		if (step == 0) {
			if (grid[r][c] == 2) {
				pathNum++;
			}

			return;
		}

		if (grid[r][c] == 2) {
			return;
		}

		int row = grid.length;
		int col = grid[0].length;

		grid[r][c] = -1;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r + R_OFFSETS[i];
			int nextC = c + C_OFFSETS[i];

			if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && grid[nextR][nextC] != -1) {
				search(grid, step - 1, nextR, nextC);
			}
		}
		grid[r][c] = 0;
	}
}
