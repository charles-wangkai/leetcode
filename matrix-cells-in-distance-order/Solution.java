import java.util.Arrays;

public class Solution {
	public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
		int[][] result = new int[R * C][];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result[i * C + j] = new int[] { i, j };
			}
		}

		Arrays.sort(result, (cell1, cell2) -> Integer.compare(computeDistance(cell1[0], cell1[1], r0, c0),
				computeDistance(cell2[0], cell2[1], r0, c0)));

		return result;
	}

	int computeDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
	}
}
