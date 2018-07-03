public class Solution {
	final int[] R_OFFSETS = { -1, 0, 1, 0 };
	final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public int countBattleships(char[][] board) {
		int row = board.length;
		if (row == 0) {
			return 0;
		}
		int col = board[0].length;
		if (col == 0) {
			return 0;
		}

		boolean[][] visited = new boolean[row][col];
		int battleNum = 0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (!visited[r][c] && board[r][c] == 'X') {
					search(board, visited, r, c);
					battleNum++;
				}
			}
		}
		return battleNum;
	}

	void search(char[][] board, boolean[][] visited, int r, int c) {
		int row = board.length;
		int col = board[0].length;

		visited[r][c] = true;
		for (int i = 0; i < R_OFFSETS.length; i++) {
			int nextR = r + R_OFFSETS[i];
			int nextC = c + C_OFFSETS[i];

			if (nextR >= 0 && nextR < row && nextC >= 0 && nextC < col && !visited[nextR][nextC]
					&& board[nextR][nextC] == 'X') {
				search(board, visited, nextR, nextC);
			}
		}
	}
}
