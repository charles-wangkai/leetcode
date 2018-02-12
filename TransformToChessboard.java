public class TransformToChessboard {
	public int movesToChessboard(int[][] board) {
		if (!checkValid(board)) {
			return -1;
		}

		int totalMove = 0;

		int rowMove = swapRows(board);
		if (rowMove == -1) {
			return -1;
		}
		totalMove += rowMove;

		transpose(board);

		rowMove = swapRows(board);
		if (rowMove == -1) {
			return -1;
		}
		totalMove += rowMove;

		return totalMove;
	}

	boolean checkValid(int[][] board) {
		int N = board.length;
		for (int r = 1; r < N; r++) {
			boolean sameOrInvert = board[r][0] == board[0][0];
			for (int c = 0; c < N; c++) {
				if ((sameOrInvert && board[r][c] != board[0][c]) || (!sameOrInvert && board[r][c] + board[0][c] != 1)) {
					return false;
				}
			}
		}
		return true;
	}

	void transpose(int[][] board) {
		int N = board.length;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < r; c++) {
				int temp = board[r][c];
				board[r][c] = board[c][r];
				board[c][r] = temp;
			}
		}
	}

	int swapRows(int[][] board) {
		int N = board.length;

		int[] counts = new int[2];
		for (int r = 0; r < N; r++) {
			counts[board[r][0]]++;
		}

		int minMove = Integer.MAX_VALUE;
		for (int startValue : new int[] { 0, 1 }) {
			int[] targetCounts = { N / 2, N / 2 };
			if (N % 2 != 0) {
				targetCounts[startValue]++;
			}

			if (counts[0] != targetCounts[0] || counts[1] != targetCounts[1]) {
				continue;
			}

			int diff = 0;
			int targetValue = startValue;
			for (int r = 0; r < N; r++) {
				if (board[r][0] != targetValue) {
					diff++;
				}

				targetValue = 1 - targetValue;
			}

			if (diff / 2 < minMove) {
				minMove = diff / 2;
			}
		}

		return minMove == Integer.MAX_VALUE ? -1 : minMove;
	}
}
