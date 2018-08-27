public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int surfaceArea(int[][] grid) {
		int N = grid.length;
		int area = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (grid[r][c] != 0) {
					area += 2;

					for (int i = 0; i < R_OFFSETS.length; i++) {
						int adjR = r + R_OFFSETS[i];
						int adjC = c + C_OFFSETS[i];

						if (adjR >= 0 && adjR < N && adjC >= 0 && adjC < N) {
							area += Math.max(0, grid[r][c] - grid[adjR][adjC]);
						} else {
							area += grid[r][c];
						}
					}
				}
			}
		}
		return area;
	}
}
