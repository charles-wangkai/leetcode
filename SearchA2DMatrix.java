public class SearchA2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix.length == 0 || matrix[0].length == 0
				|| target < matrix[0][0]) {
			return false;
		}

		int[] firstColumn = new int[matrix.length];
		for (int i = 0; i < firstColumn.length; i++) {
			firstColumn[i] = matrix[i][0];
		}
		int rowIndex = binarySearch(firstColumn, target);

		int[] row = new int[matrix[0].length];
		for (int i = 0; i < row.length; i++) {
			row[i] = matrix[rowIndex][i];
		}
		int columnIndex = binarySearch(row, target);

		return matrix[rowIndex][columnIndex] == target;
	}

	int binarySearch(int[] a, int target) {
		int lower = 0;
		int upper = a.length - 1;
		while (lower <= upper) {
			int middle = (lower + upper) / 2;
			if (a[middle] == target) {
				return middle;
			} else if (a[middle] < target) {
				lower = middle + 1;
			} else {
				upper = middle - 1;
			}
		}
		return upper;
	}
}
