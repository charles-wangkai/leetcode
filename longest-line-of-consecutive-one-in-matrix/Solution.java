public class Solution {
	int maxLength;

	public int longestLine(int[][] M) {
		maxLength = 0;

		int row = M.length;
		if (row > 0) {
			int col = M[0].length;

			for (int r = 0; r < row; r++) {
				search(M, r, 0, 0, 1);
			}

			for (int c = 0; c < col; c++) {
				search(M, 0, c, 1, 0);
			}

			for (int r = 0; r < row; r++) {
				search(M, r, 0, 1, 1);
			}
			for (int c = 0; c < col; c++) {
				search(M, 0, c, 1, 1);
			}

			for (int r = 0; r < row; r++) {
				search(M, r, col - 1, 1, -1);
			}
			for (int c = 0; c < col; c++) {
				search(M, 0, c, 1, -1);
			}
		}

		return maxLength;
	}

	void search(int[][] M, int r, int c, int offsetR, int offsetC) {
		int row = M.length;
		int col = M[0].length;

		int length = 0;
		while (r >= 0 && r < row && c >= 0 && c < col) {
			if (M[r][c] == 1) {
				length++;
				maxLength = Math.max(maxLength, length);
			} else {
				length = 0;
			}

			r += offsetR;
			c += offsetC;
		}
	}
}
