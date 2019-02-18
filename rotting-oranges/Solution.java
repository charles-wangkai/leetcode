import java.util.Arrays;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int orangesRotting(int[][] grid) {
		int freshNum = countFresh(grid);
		int minute = 0;
		while (freshNum != 0) {
			grid = elapse(grid);
			int nextFreshNum = countFresh(grid);

			if (nextFreshNum == freshNum) {
				return -1;
			}

			freshNum = nextFreshNum;
			minute++;
		}
		return minute;
	}

	int countFresh(int[][] grid) {
		return Arrays.stream(grid).mapToInt(line -> (int) Arrays.stream(line).filter(x -> x == 1).count()).sum();
	}

	int[][] elapse(int[][] grid) {
		int row = grid.length;
		int col = grid[0].length;

		int[][] nextGrid = new int[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				nextGrid[r][c] = grid[r][c];

				if (grid[r][c] == 1) {
					for (int i = 0; i < R_OFFSETS.length; i++) {
						int adjR = r + R_OFFSETS[i];
						int adjC = c + C_OFFSETS[i];

						if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && grid[adjR][adjC] == 2) {
							nextGrid[r][c] = 2;
						}
					}
				}
			}
		}
		return nextGrid;
	}
}
