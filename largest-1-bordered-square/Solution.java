public class Solution {
	public int largest1BorderedSquare(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int[][] rowPrefixSums = buildRowPrefixSums(grid);
		int[][] colPrefixSums = buildColPrefixSums(grid);

		int result = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				for (int size = 1; r + size <= row && c + size <= col; size++) {
					if (computeRowRangeSum(rowPrefixSums, r, c, c + size - 1) == size
							&& computeRowRangeSum(rowPrefixSums, r + size - 1, c, c + size - 1) == size
							&& computeColRangeSum(colPrefixSums, c, r, r + size - 1) == size
							&& computeColRangeSum(colPrefixSums, c + size - 1, r, r + size - 1) == size) {
						result = Math.max(result, size * size);
					}
				}
			}
		}
		return result;
	}

	int[][] buildRowPrefixSums(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int[][] rowPrefixSums = new int[row][col];
		for (int r = 0; r < row; r++) {
			int rowPrefixSum = 0;
			for (int c = 0; c < col; c++) {
				rowPrefixSum += grid[r][c];
				rowPrefixSums[r][c] = rowPrefixSum;
			}
		}
		return rowPrefixSums;
	}

	int[][] buildColPrefixSums(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int[][] colPrefixSums = new int[row][col];
		for (int c = 0; c < col; c++) {
			int colPrefixSum = 0;
			for (int r = 0; r < row; r++) {
				colPrefixSum += grid[r][c];
				colPrefixSums[r][c] = colPrefixSum;
			}
		}
		return colPrefixSums;
	}

	int computeRowRangeSum(int[][] rowPrefixSums, int r, int beginC, int endC) {
		return rowPrefixSums[r][endC] - ((beginC == 0) ? 0 : rowPrefixSums[r][beginC - 1]);
	}

	int computeColRangeSum(int[][] colPrefixSums, int c, int beginR, int endR) {
		return colPrefixSums[endR][c] - ((beginR == 0) ? 0 : colPrefixSums[beginR - 1][c]);
	}
}
