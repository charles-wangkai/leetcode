public class Solution {
	static final int[] R_OFFSETS = { -1, -1, -1, 0, 0, 0, 1, 1, 1 };
	static final int[] C_OFFSETS = { -1, 0, 1, -1, 0, 1, -1, 0, 1 };

	public int[][] imageSmoother(int[][] M) {
		int row = M.length;
		int col = M[0].length;

		int[][] smoothed = new int[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				smoothed[r][c] = smooth(M, r, c);
			}
		}
		return smoothed;
	}

	static int smooth(int[][] M, int r, int c) {
		int row = M.length;
		int col = M[0].length;

		int sum = 0;
		int count = 0;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int adjR = r + R_OFFSETS[i];
			int adjC = c + C_OFFSETS[i];

			if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col) {
				sum += M[adjR][adjC];
				count++;
			}
		}
		return sum / count;
	}
}
