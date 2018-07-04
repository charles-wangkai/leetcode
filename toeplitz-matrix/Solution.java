import java.util.stream.IntStream;

public class Solution {
	public boolean isToeplitzMatrix(int[][] matrix) {
		return IntStream.range(0, Math.min(matrix.length, matrix[0].length))
				.allMatch(i -> matrix[i][i] == matrix[0][0]);
	}
}
