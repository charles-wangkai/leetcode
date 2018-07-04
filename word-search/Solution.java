public class Solution {
	static final int[] OFFSET_R = { -1, 0, 1, 0 };
	static final int[] OFFSET_C = { 0, 1, 0, -1 };

	public boolean exist(char[][] board, String word) {
		if (word.isEmpty()) {
			return true;
		}
		int row = board.length;
		if (row == 0) {
			return false;
		}
		int col = board[0].length;
		if (col == 0) {
			return false;
		}

		boolean[][] used = new boolean[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (exist(board, word, used, i, j, 0)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean exist(char[][] board, String word, boolean[][] used, int r, int c,
			int index) {
		if (index == word.length()) {
			return true;
		}

		int row = board.length;
		int col = board[0].length;
		if (!(r >= 0 && r < row && c >= 0 && c < col) || used[r][c]
				|| board[r][c] != word.charAt(index)) {
			return false;
		}

		boolean result = false;
		used[r][c] = true;
		for (int i = 0; i < OFFSET_R.length; i++) {
			if (exist(board, word, used, r + OFFSET_R[i], c + OFFSET_C[i],
					index + 1)) {
				result = true;
				break;
			}
		}
		used[r][c] = false;
		return result;
	}
}
