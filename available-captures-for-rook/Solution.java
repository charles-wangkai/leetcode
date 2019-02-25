import java.util.stream.IntStream;

public class Solution {
	final int[] R_OFFSETS = { -1, 0, 1, 0 };
	final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int numRookCaptures(char[][] board) {
		Point rookPoint = findRook(board);

		return IntStream.range(0, R_OFFSETS.length)
				.map(i -> countCapturedPawn(board, rookPoint, R_OFFSETS[i], C_OFFSETS[i])).sum();
	}

	Point findRook(char[][] board) {
		for (int r = 0;; r++) {
			for (int c = 0; c < board[r].length; c++) {
				if (board[r][c] == 'R') {
					return new Point(r, c);
				}
			}
		}
	}

	int countCapturedPawn(char[][] board, Point rookPoint, int rOffset, int cOffset) {
		int r = rookPoint.r + rOffset;
		int c = rookPoint.c + cOffset;
		while (r >= 0 && r < board.length && c >= 0 && c < board.length) {
			if (board[r][c] == 'B') {
				break;
			}
			if (board[r][c] == 'p') {
				return 1;
			}

			r += rOffset;
			c += cOffset;
		}
		return 0;
	}
}

class Point {
	int r;
	int c;

	Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}