public class Solution {
	public void solveSudoku(char[][] board) {
		boolean[][] rows = new boolean[9][10];
		boolean[][] columns = new boolean[9][10];
		boolean[][][] blocks = new boolean[3][3][10];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				if (board[r][c] != '.') {
					fill(board, rows, columns, blocks, r, c, board[r][c] - '0');
				}
			}
		}
		search(board, rows, columns, blocks, 0, 0);
	}

	boolean fill(char[][] board, boolean[][] rows, boolean[][] columns,
			boolean[][][] blocks, int r, int c, int digit) {
		if (rows[r][digit] || columns[c][digit] || blocks[r / 3][c / 3][digit]) {
			return false;
		}
		board[r][c] = (char) ('0' + digit);
		rows[r][digit] = true;
		columns[c][digit] = true;
		blocks[r / 3][c / 3][digit] = true;
		return true;
	}

	void unfill(char[][] board, boolean[][] rows, boolean[][] columns,
			boolean[][][] blocks, int r, int c) {
		int digit = board[r][c] - '0';
		board[r][c] = '.';
		rows[r][digit] = false;
		columns[c][digit] = false;
		blocks[r / 3][c / 3][digit] = false;
	}

	boolean search(char[][] board, boolean[][] rows, boolean[][] columns,
			boolean[][][] blocks, int r, int c) {
		if (r == 9) {
			return true;
		}
		if (c == 9) {
			return search(board, rows, columns, blocks, r + 1, 0);
		}
		if (board[r][c] != '.') {
			return search(board, rows, columns, blocks, r, c + 1);
		}
		for (int digit = 1; digit <= 9; digit++) {
			if (fill(board, rows, columns, blocks, r, c, digit)) {
				if (search(board, rows, columns, blocks, r, c + 1)) {
					return true;
				}
				unfill(board, rows, columns, blocks, r, c);
			}
		}
		return false;
	}
}
