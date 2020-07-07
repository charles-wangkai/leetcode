class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int islandPerimeter(int[][] grid) {
		int row = grid.length;
		if (row == 0) {
			return 0;
		}
		int col = grid[0].length;

		int perimeter = 0;
		for (int r = 0; r < row; ++r) {
			for (int c = 0; c < col; ++c) {
				if (grid[r][c] == 1) {
					for (int i = 0; i < R_OFFSETS.length; ++i) {
						int neighborR = r + R_OFFSETS[i];
						int neighborC = c + C_OFFSETS[i];

						if (!(neighborR >= 0 && neighborR < row && neighborC >= 0 && neighborC < col)
								|| grid[neighborR][neighborC] == 0) {
							++perimeter;
						}
					}
				}
			}
		}

		return perimeter;
	}
}
