import java.util.stream.IntStream;

public class Solution {
	public int matrixScore(int[][] A) {
		int row = A.length;
		int col = A[0].length;

		for (int r = 0; r < row; r++) {
			if (A[r][0] == 0) {
				flipRow(A, r);
			}
		}

		for (int c = 0; c < col; c++) {
			int oneCount = countOneInColumn(A, c);
			if (oneCount < row - oneCount) {
				flipCol(A, c);
			}
		}

		return IntStream.range(0, row).map(r -> convertToNumber(A, r)).sum();
	}

	int countOneInColumn(int[][] A, int c) {
		return (int) IntStream.range(0, A.length).filter(r -> A[r][c] == 1).count();
	}

	int convertToNumber(int[][] A, int r) {
		int number = 0;
		for (int c = 0; c < A[0].length; c++) {
			number = number * 2 + A[r][c];
		}
		return number;
	}

	void flipRow(int[][] A, int r) {
		for (int c = 0; c < A[0].length; c++) {
			A[r][c] = 1 - A[r][c];
		}
	}

	void flipCol(int[][] A, int c) {
		for (int r = 0; r < A.length; r++) {
			A[r][c] = 1 - A[r][c];
		}
	}
}
