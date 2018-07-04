public class Solution {
	static final int[] OFFSET_R = { 0, 1, 0, -1 };
	static final int[] OFFSET_C = { 1, 0, -1, 0 };

	public int[][] generateMatrix(int n) {
		int[][] matrix = new int[n][n];
		int r = 0;
		int c = -1;
		int direction = 0;
		for (int i = 1; i <= n * n; i++) {
			while (true) {
				int nextR = r + OFFSET_R[direction];
				int nextC = c + OFFSET_C[direction];
				if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n
						&& matrix[nextR][nextC] == 0) {
					r = nextR;
					c = nextC;
					break;
				}
				direction = (direction + 1) % OFFSET_R.length;
			}
			matrix[r][c] = i;
		}
		return matrix;
	}
}
