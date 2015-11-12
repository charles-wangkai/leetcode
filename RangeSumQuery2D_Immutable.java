public class RangeSumQuery2D_Immutable {
	int[][] sums;

	public RangeSumQuery2D_Immutable(int[][] matrix) {
		int row = matrix.length;
		if (row == 0) {
			return;
		}
		int col = matrix[0].length;

		sums = new int[row + 1][col + 1];
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j <= col; j++) {
				sums[i][j] = sums[i - 1][j] + sums[i][j - 1] - sums[i - 1][j - 1] + matrix[i - 1][j - 1];
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return sums[row2 + 1][col2 + 1] - sums[row1][col2 + 1] - sums[row2 + 1][col1] + sums[row1][col1];
	}
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);