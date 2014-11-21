public class SetMatrixZeroes {
	public void setZeroes(int[][] matrix) {
		int row = matrix.length;
		if (row == 0) {
			return;
		}
		int col = matrix[0].length;
		if (col == 0) {
			return;
		}

		boolean zeroInFirstRow = existsZero(matrix, 0, 0, 0, 1, col);
		boolean zeroInFirstCol = existsZero(matrix, 0, 0, 1, 0, row);

		for (int i = 1; i < row; i++) {
			if (existsZero(matrix, i, 0, 0, 1, col)) {
				matrix[i][0] = 0;
			}
		}

		for (int i = 1; i < col; i++) {
			if (existsZero(matrix, 0, i, 1, 0, row)) {
				matrix[0][i] = 0;
			}
		}

		for (int i = 1; i < row; i++) {
			if (matrix[i][0] == 0) {
				fillZeros(matrix, i, 0, 0, 1, col);
			}
		}

		for (int i = 1; i < col; i++) {
			if (matrix[0][i] == 0) {
				fillZeros(matrix, 0, i, 1, 0, row);
			}
		}

		if (zeroInFirstRow) {
			fillZeros(matrix, 0, 0, 0, 1, col);
		}
		if (zeroInFirstCol) {
			fillZeros(matrix, 0, 0, 1, 0, row);
		}
	}

	boolean existsZero(int[][] matrix, int r, int c, int offsetR, int offsetC,
			int step) {
		for (int i = 0; i < step; i++) {
			if (matrix[r][c] == 0) {
				return true;
			}
			r += offsetR;
			c += offsetC;
		}
		return false;
	}

	void fillZeros(int[][] matrix, int r, int c, int offsetR, int offsetC,
			int step) {
		for (int i = 0; i < step; i++) {
			matrix[r][c] = 0;
			r += offsetR;
			c += offsetC;
		}
	}
}
