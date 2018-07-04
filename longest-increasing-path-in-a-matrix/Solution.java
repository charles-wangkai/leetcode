public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int longestIncreasingPath(int[][] matrix) {
		int row = matrix.length;
		if (row == 0) {
			return 0;
		}
		int col = matrix[0].length;

		int[][] maxLengths = new int[row][col];
		int maxLength = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				maxLength = Math.max(maxLength, search(matrix, maxLengths, r, c));
			}
		}
		return maxLength;
	}

	int search(int[][] matrix, int[][] maxLengths, int r, int c) {
		if (maxLengths[r][c] != 0) {
			return maxLengths[r][c];
		}

		int row = matrix.length;
		int col = matrix[0].length;

		int result = 1;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r + R_OFFSETS[i];
			int nextC = c + C_OFFSETS[i];
			if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && matrix[nextR][nextC] > matrix[r][c]) {
				result = Math.max(result, 1 + search(matrix, maxLengths, nextR, nextC));
			}
		}
		maxLengths[r][c] = result;
		return result;
	}
}
