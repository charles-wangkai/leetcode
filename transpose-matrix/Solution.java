public class Solution {
	public int[][] transpose(int[][] A) {
		int row = A[0].length;
		int col = A.length;
		int[][] result = new int[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				result[r][c] = A[c][r];
			}
		}
		return result;
	}
}
