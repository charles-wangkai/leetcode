import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static final int[] R_OFFSETS = { -1, 0, 1, 0 };
	static final int[] C_OFFSETS = { 0, 1, 0, -1 };

	public void solve(char[][] board) {
		int row = board.length;
		if (row == 0) {
			return;
		}
		int col = board[0].length;
		if (col == 0) {
			return;
		}

		fill(board);

		replace(board, 'O', 'X');
		replace(board, '_', 'O');
	}

	void fill(char[][] board) {
		int row = board.length;
		int col = board[0].length;

		Queue<Point> queue = new LinkedList<>();
		for (int r = 0; r < row; ++r) {
			for (int c = 0; c < col; ++c) {
				if ((r == 0 || r == row - 1 || c == 0 || c == col - 1) && board[r][c] == 'O') {
					queue.offer(new Point(r, c));
				}
			}
		}

		while (!queue.isEmpty()) {
			Point head = queue.poll();
			if (!(head.r >= 0 && head.r < row && head.c >= 0 && head.c < col && board[head.r][head.c] == 'O')) {
				continue;
			}

			board[head.r][head.c] = '_';

			for (int i = 0; i < R_OFFSETS.length; ++i) {
				int adjR = head.r + R_OFFSETS[i];
				int adjC = head.c + C_OFFSETS[i];

				queue.offer(new Point(adjR, adjC));
			}
		}
	}

	void replace(char[][] board, char from, char to) {
		int row = board.length;
		int col = board[0].length;

		for (int r = 0; r < row; ++r) {
			for (int c = 0; c < col; ++c) {
				if (board[r][c] == from) {
					board[r][c] = to;
				}
			}
		}
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