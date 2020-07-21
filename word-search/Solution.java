class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public boolean exist(char[][] board, String word) {
		int row = board.length;
		int col = board[0].length;

		boolean[][] used = new boolean[row][col];
		for (int r = 0; r < row; ++r) {
			for (int c = 0; c < col; ++c) {
				if (check(board, word, used, r, c, 0)) {
					return true;
				}
			}
		}

		return false;
	}

	boolean check(char[][] board, String word, boolean[][] used, int r, int c, int index) {
		int row = board.length;
		int col = board[0].length;

		if (index == word.length()) {
			return true;
		}

		if (!(r >= 0 && r < row && c >= 0 && c < col) || used[r][c] || board[r][c] != word.charAt(index)) {
			return false;
		}

		boolean result = false;
		used[r][c] = true;
		for (int i = 0; i < R_OFFSETS.length; ++i) {
			if (check(board, word, used, r + R_OFFSETS[i], c + C_OFFSETS[i], index + 1)) {
				result = true;

				break;
			}
		}
		used[r][c] = false;

		return result;
	}
}
