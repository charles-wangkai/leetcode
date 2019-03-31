import java.util.stream.IntStream;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int numEnclaves(int[][] A) {
		int row = A.length;
		int col = A[0].length;

		boolean[][] visited = new boolean[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if ((r == 0 || r == row - 1 || c == 0 || c == col - 1) && A[r][c] == 1 && !visited[r][c]) {
					search(A, visited, r, c);
				}
			}
		}

		return IntStream.range(0, row)
				.map(r -> (int) IntStream.range(0, col).filter(c -> A[r][c] == 1 && !visited[r][c]).count()).sum();
	}

	void search(int[][] A, boolean[][] visited, int r, int c) {
		int row = A.length;
		int col = A[0].length;

		visited[r][c] = true;

		for (int i = 0; i < R_OFFSETS.length; i++) {
			int adjR = r + R_OFFSETS[i];
			int adjC = c + C_OFFSETS[i];

			if (adjR >= 0 && adjR < row && adjC >= 0 && adjC < col && A[adjR][adjC] == 1 && !visited[adjR][adjC]) {
				search(A, visited, adjR, adjC);
			}
		}
	}
}
