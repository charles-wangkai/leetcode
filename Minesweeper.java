public class Minesweeper {
	static final int[] R_OFFSETS = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static final int[] C_OFFSETS = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public char[][] updateBoard(char[][] board, int[] click) {
		int row = click[0];
		int col = click[1];

		if (board[row][col] == 'M') {
			board[row][col] = 'X';
		} else {
			board[row][col] = 'B';

			int adjMineNum = countAdjMine(board, row, col);

			if (adjMineNum == 0) {
				for (int i = 0; i < R_OFFSETS.length; i++) {
					int adjRow = row + R_OFFSETS[i];
					int adjCol = col + C_OFFSETS[i];

					if (isInBoard(board, adjRow, adjCol) && board[adjRow][adjCol] == 'E') {
						updateBoard(board, new int[] { adjRow, adjCol });
					}
				}
			} else {
				board[row][col] = Character.forDigit(adjMineNum, 10);
			}
		}

		return board;
	}

	boolean isInBoard(char[][] board, int row, int col) {
		int boardRow = board.length;
		int boardCol = board[0].length;

		return row >= 0 && row < boardRow && col >= 0 && col < boardCol;
	}

	int countAdjMine(char[][] board, int row, int col) {
		int adjMineNum = 0;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int adjRow = row + R_OFFSETS[i];
			int adjCol = col + C_OFFSETS[i];

			if (isInBoard(board, adjRow, adjCol) && board[adjRow][adjCol] == 'M') {
				adjMineNum++;
			}
		}
		return adjMineNum;
	}
}
