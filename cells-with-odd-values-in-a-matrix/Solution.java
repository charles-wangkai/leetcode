import java.util.stream.IntStream;

public class Solution {
	public int oddCells(int n, int m, int[][] indices) {
		int[][] matrix = new int[n][m];

		for (int[] pair : indices) {
			for (int i = 0; i < m; i++) {
				matrix[pair[0]][i]++;
			}

			for (int i = 0; i < n; i++) {
				matrix[i][pair[1]]++;
			}
		}

		return IntStream.range(0, n).map(i -> (int) IntStream.range(0, m).filter(j -> matrix[i][j] % 2 != 0).count())
				.sum();
	}
}
