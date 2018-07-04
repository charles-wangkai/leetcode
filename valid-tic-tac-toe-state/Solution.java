import java.util.stream.IntStream;

public class Solution {
	static final int[] R_STARTS = { 0, 1, 2, 0, 0, 0, 0, 0 };
	static final int[] C_STARTS = { 0, 0, 0, 0, 1, 2, 0, 2 };
	static final int[] R_OFFSETS = { 0, 0, 0, 1, 1, 1, 1, 1 };
	static final int[] C_OFFSETS = { 1, 1, 1, 0, 0, 0, 1, -1 };

	public boolean validTicTacToe(String[] board) {
		int numX = count(board, 'X');
		int numO = count(board, 'O');
		boolean winX = isWin(board, 'X');
		boolean winO = isWin(board, 'O');

		return (numX == numO && !winX) || (numX == numO + 1 && !winO);
	}

	int count(String[] board, char piece) {
		int num = 0;
		for (String row : board) {
			for (char ch : row.toCharArray()) {
				if (ch == piece) {
					num++;
				}
			}
		}
		return num;
	}

	boolean isWin(String[] board, char piece) {
		return IntStream.range(0, R_STARTS.length)
				.anyMatch(i -> board[R_STARTS[i]].charAt(C_STARTS[i]) == piece
						&& board[R_STARTS[i] + R_OFFSETS[i]].charAt(C_STARTS[i] + C_OFFSETS[i]) == piece
						&& board[R_STARTS[i] + 2 * R_OFFSETS[i]].charAt(C_STARTS[i] + 2 * C_OFFSETS[i]) == piece);
	}
}
