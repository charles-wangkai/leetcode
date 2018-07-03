public class Solution {
	static final int[] R_OFFSETS = { -1, 1 };
	static final int[] C_OFFSETS = { 1, -1 };

	public int[] findDiagonalOrder(int[][] matrix) {
		int row = matrix.length;
		if (row == 0) {
			return new int[0];
		}
		int col = matrix[0].length;
		if (col == 0) {
			return new int[0];
		}

		int r = 0;
		int c = 0;
		int direction = 0;
		int[] result = new int[row * col];
		for (int i = 0; i < result.length; i++) {
			result[i] = matrix[r][c];

			r += R_OFFSETS[direction];
			c += C_OFFSETS[direction];
			if (c == col) {
				r += 2;
				c--;
				direction = swapDirection(direction);
			} else if (r < 0) {
				r = 0;
				direction = swapDirection(direction);
			} else if (r == row) {
				r--;
				c += 2;
				direction = swapDirection(direction);
			} else if (c < 0) {
				c = 0;
				direction = swapDirection(direction);
			}
		}
		return result;
	}

	int swapDirection(int direction) {
		return 1 - direction;
	}
}
