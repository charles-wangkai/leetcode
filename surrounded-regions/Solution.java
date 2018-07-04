import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	final int OFFSET_R[] = { -1, 0, 1, 0 };
	final int OFFSET_C[] = { 0, 1, 0, -1 };
	int row;
	int col;

	public void solve(char[][] board) {
		final char FROM = 'O';
		final char TO = 'X';
		final char TEMP = '_';

		row = board.length;
		if (row == 0) {
			return;
		}
		col = board[0].length;
		if (col == 0) {
			return;
		}

		for (int i = 0; i < col; i++) {
			bfsFill(board, 0, i, FROM, TEMP);
			bfsFill(board, row - 1, i, FROM, TEMP);
		}
		for (int i = 0; i < row; i++) {
			bfsFill(board, i, 0, FROM, TEMP);
			bfsFill(board, i, col - 1, FROM, TEMP);
		}
		fillBoard(board, FROM, TO);
		fillBoard(board, TEMP, FROM);
	}

	void bfsFill(char[][] board, int startR, int startC, char from, char to) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(startR, startC));
		while (!queue.isEmpty()) {
			Point head = queue.poll();
			if (head.r < 0 || head.r >= row || head.c < 0 || head.c >= col
					|| board[head.r][head.c] != from) {
				continue;
			}
			board[head.r][head.c] = to;
			for (int i = 0; i < OFFSET_R.length; i++) {
				int nextR = head.r + OFFSET_R[i];
				int nextC = head.c + OFFSET_C[i];
				queue.offer(new Point(nextR, nextC));
			}
		}
	}

	void fillBoard(char[][] board, char from, char to) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (board[i][j] == from) {
					board[i][j] = to;
				}
			}
		}
	}
}

class Point {
	int r;
	int c;

	public Point(int r, int c) {
		this.r = r;
		this.c = c;
	}
}