public class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
		int row = matrix.length;
		if (row == 0) {
			return false;
		}
		int col = matrix[0].length;
		if (col == 0) {
			return false;
		}

		int i = row - 1;
		int j = 0;
		while (i >= 0 && j < col) {
			if (matrix[i][j] == target) {
				return true;
			} else if (matrix[i][j] < target) {
				j++;
			} else {
				i--;
			}
		}
		return false;
	}
}
