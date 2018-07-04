public class Solution {
	public int countCornerRectangles(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int result = 0;
		for (int c1 = 0; c1 < col; c1++) {
			for (int c2 = c1 + 1; c2 < col; c2++) {
				int count = 0;
				for (int r = 0; r < row; r++) {
					if (grid[r][c1] == 1 && grid[r][c2] == 1) {
						count++;
					}
				}
				result += C(count, 2);
			}
		}
		return result;
	}

	int C(int n, int m) {
		int result = 1;
		for (int i = 0; i < m; i++) {
			result = result * (n - i) / (i + 1);
		}
		return result;
	}
}
