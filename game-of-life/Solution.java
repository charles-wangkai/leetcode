public class Solution {
	static final int[] OFFSET_R = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] OFFSET_C = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public void gameOfLife(int[][] board) {
		int row = board.length;
		if (row == 0) {
			return;
		}
		int col = board[0].length;
		if (col == 0) {
			return;
		}

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				int neighbor = countNeighbor(board, r, c);
				if (getPrevious(board, r, c) == 1) {
					if (neighbor == 2 || neighbor == 3) {
						setCurrent(board, r, c, 1);
					} else {
						setCurrent(board, r, c, 0);
					}
				} else {
					if (neighbor == 3) {
						setCurrent(board, r, c, 1);
					} else {
						setCurrent(board, r, c, 0);
					}
				}
			}
		}

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				board[r][c] = getCurrent(board, r, c);
			}
		}
	}

	int countNeighbor(int[][] board, int r, int c) {
		int neighbor = 0;
		int row = board.length;
		int col = board[0].length;
		for (int i = 0; i < OFFSET_R.length; i++) {
			int neighborR = r + OFFSET_R[i];
			int neighborC = c + OFFSET_C[i];
			if (neighborR >= 0 && neighborR < row && neighborC >= 0 && neighborC < col
					&& getPrevious(board, neighborR, neighborC) == 1) {
				neighbor++;
			}
		}
		return neighbor;
	}

	int getPrevious(int[][] board, int r, int c) {
		return board[r][c] & 1;
	}

	void setCurrent(int[][] board, int r, int c, int current) {
		board[r][c] = (current << 1) + getPrevious(board, r, c);
	}

	int getCurrent(int[][] board, int r, int c) {
		return board[r][c] >> 1;
	}
}
